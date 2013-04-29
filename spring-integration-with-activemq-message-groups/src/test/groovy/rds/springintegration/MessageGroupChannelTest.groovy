package rds.springintegration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.integration.channel.QueueChannel
import org.springframework.integration.core.MessagingTemplate
import org.springframework.integration.message.GenericMessage
import org.springframework.integration.support.MessageBuilder
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 4/27/13
 * Time: 10:21 AM
 */
@ContextConfiguration
class MessageGroupChannelTest extends Specification {
    private static final int TIME_TO_PROCESS_ONE_MESSAGE = 1250
    private static final int MAX_ALLOWED_TIME_PER_MESSAGE = 2500

    @Configuration
    @ImportResource('/applicationContext.xml')
    @SuppressWarnings("GrMethodMayBeStatic")
    static class Beans {
        @Bean def template() { new MessagingTemplate() }
    }

    @Autowired MessagingTemplate messaging
    @Autowired QueueChannel outbound
    String randomness = UUID.randomUUID()

    def setup() {
        // wait for all messages to process through in case of failures
        // this is a slow way to do it, but i can't find another simple way to do it when a JMS queue is involved
        sleep MAX_ALLOWED_TIME_PER_MESSAGE
        while (outbound.queueSize > 0) {
            println 'Waiting for old messages to clear out...'
            outbound.clear()
            sleep MAX_ALLOWED_TIME_PER_MESSAGE
        }
        println 'Outbound channel seems clear, starting test'
    }

    def 'messages go from inbound to outbound'() {
        given:
        def msg = new GenericMessage("inbound to outbound test $randomness")

        when:
        messaging.send 'inbound', msg

        then:
        def msgOut = outbound.receive(2000)
        msgOut
        msgOut.payload == "inbound to outbound test $randomness"
    }

    def 'a single message incurs typical processing delays'() {
        given:
        def msg = new GenericMessage("delay test $randomness")

        when:
        messaging.send 'inbound', msg

        then:
        !outbound.receive(1000)
        def msgOut = outbound.receive(1000)
        msgOut
        msgOut.headers.A
        msgOut.headers.B
        msgOut.headers.C
        msgOut.headers.D
        msgOut.headers.E
    }

    def 'five messages processed serially shows linear increase in delay'() {
        given:
        def messages = multipleMessages 5, randomness, { 'default' }

        when:
        long start = System.currentTimeMillis()
        messages.each{ messaging.send 'inbound', it }

        then:
        long elapsed = waitForNMessagesOrTimeToProcessMSerially 5, 5, start
        outbound.queueSize == 5
        elapsed >= 5 * TIME_TO_PROCESS_ONE_MESSAGE
        elapsed <= 5 * MAX_ALLOWED_TIME_PER_MESSAGE
    }

    def 'sending five messages with different groups has the same delay as a single message'() {
        given:
        def messages = multipleMessages 5, randomness, { "group-$it" }

        when:
        long start = System.currentTimeMillis()
        messages.each{ messaging.send 'inbound', it }

        then:
        long elapsed = waitForNMessagesOrTimeToProcessMSerially 5, 1, start
        outbound.queueSize == 5
        elapsed >= TIME_TO_PROCESS_ONE_MESSAGE
        elapsed <= MAX_ALLOWED_TIME_PER_MESSAGE
    }

    def 'sending 60 messages in 20 different groups has the same delay as 3 messages (because concurrency is capped at 20)'() {
        given:
        def messages = multipleMessages 60, randomness, { 'group-' + (it % 20) }

        when:
        long start = System.currentTimeMillis()
        messages.each{ messaging.send 'inbound', it }

        then:
        long elapsed = waitForNMessagesOrTimeToProcessMSerially 60, 3, start
        outbound.queueSize == 60
        elapsed >= 3 * TIME_TO_PROCESS_ONE_MESSAGE
        elapsed <= 3 * MAX_ALLOWED_TIME_PER_MESSAGE
    }

    def 'sending 50 messages in 25 different groups has the same delay as 3 messages (because concurrency is capped at 20)'() {
        given:
        def messages = multipleMessages 50, randomness, { 'group-' + (it % 25) }

        when:
        long start = System.currentTimeMillis()
        messages.each{ messaging.send 'inbound', it }

        then:
        long elapsed = waitForNMessagesOrTimeToProcessMSerially 50, 3, start
        outbound.queueSize == 50
        elapsed >= 3 * TIME_TO_PROCESS_ONE_MESSAGE
        elapsed <= 3 * MAX_ALLOWED_TIME_PER_MESSAGE
    }

    /**
     * Creates multiple messages for tests given the number of messages to create, a base payload to use, and a closure
     * that will calculate which message group to put the message in. The group closure will receive one argument
     * when called, which is the sequence number of the message, counting from 1. The same number will be included in
     * the message payload. As an example, this:
     *
     *   multipleMessages(3, 'foo', { "group-$it" })
     *
     * would create the following messages:
     *
     *   payload | message group
     *   '1 foo' | 'group-1'
     *   '2 foo' | 'group-2'
     *   '3 foo' | 'group-3'
     *
     */
    def multipleMessages(int number, String payload, Closure groupClosure) {
        def messages = (1..number).collect{ new GenericMessage("$it $payload") }
        int count = 1
        messages.collect{ MessageBuilder.fromMessage(it).setHeader('JMSXGroupID', groupClosure(count++)).build() }
    }

    long waitForNMessagesOrTimeToProcessMSerially(int messages, int serially, long startTime) {
        waitToProcessNMessagesSeriallyOrMActualMessages(startTime, serially, messages)
    }

    long waitToProcessNMessagesSeriallyOrMActualMessages(long startTime, int serial, int actual) {
        long elapsed = System.currentTimeMillis() - startTime
        while (outbound.queueSize < actual && elapsed < serial * MAX_ALLOWED_TIME_PER_MESSAGE) {
            sleep 50
            elapsed = System.currentTimeMillis() - startTime
        }
        println "Exited waiting with queue size=$outbound.queueSize and elapsed=$elapsed"
        elapsed
    }
}
