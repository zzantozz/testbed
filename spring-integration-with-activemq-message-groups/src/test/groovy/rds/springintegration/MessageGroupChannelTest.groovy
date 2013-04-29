package rds.springintegration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.integration.channel.QueueChannel
import org.springframework.integration.core.MessagingTemplate
import org.springframework.integration.message.GenericMessage
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
    long testStart

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
        // Timing is everything.
        testStart = System.currentTimeMillis()
    }

    def 'messages go from inbound to outbound'() {
        given:
        def msg = new GenericMessage("inbound to outbound test $randomness")

        when:
        messaging.send 'inbound', msg

        then:
        def msgOut = outbound.receive(MAX_ALLOWED_TIME_PER_MESSAGE)
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
        msgOut.payload == "delay test $randomness"
        msgOut.headers.A
        msgOut.headers.B
        msgOut.headers.C
        msgOut.headers.D
        msgOut.headers.E
    }

    def 'five messages processed serially shows linear increase in delay'() {
        given:
        def messages = multipleMessages 5, randomness, 1

        when:
        messages.each{ messaging.send 'inbound', it }

        then:
        long elapsed = waitForNMessagesOrTimeToProcessMSerially 5, 5
        outbound.queueSize == 5
        elapsed >= 5 * TIME_TO_PROCESS_ONE_MESSAGE
        elapsed <= 5 * MAX_ALLOWED_TIME_PER_MESSAGE
    }

    def 'sending five messages with different groups has the same delay as a single message'() {
        given:
        def messages = multipleMessages 5, randomness, 5

        when:
        messages.each{ messaging.send 'inbound', it }

        then:
        long elapsed = waitForNMessagesOrTimeToProcessMSerially 5, 1
        outbound.queueSize == 5
        elapsed >= TIME_TO_PROCESS_ONE_MESSAGE
        elapsed <= MAX_ALLOWED_TIME_PER_MESSAGE
    }

    def 'sending 60 messages in 20 different groups has the same delay as 3 messages (because concurrency is capped at 20)'() {
        given:
        def messages = multipleMessages 60, randomness, 20

        when:
        messages.each{ messaging.send 'inbound', it }

        then:
        long elapsed = waitForNMessagesOrTimeToProcessMSerially 60, 3
        outbound.queueSize == 60
        elapsed >= 3 * TIME_TO_PROCESS_ONE_MESSAGE
        elapsed <= 3 * MAX_ALLOWED_TIME_PER_MESSAGE
    }

    def 'sending 50 messages in 25 different groups has the same delay as 3 messages (because concurrency is capped at 20)'() {
        given:
        def messages = multipleMessages 50, randomness, 25

        when:
        messages.each{ messaging.send 'inbound', it }

        then:
        long elapsed = waitForNMessagesOrTimeToProcessMSerially 50, 3
        outbound.queueSize == 50
        elapsed >= 3 * TIME_TO_PROCESS_ONE_MESSAGE
        elapsed <= 3 * MAX_ALLOWED_TIME_PER_MESSAGE
    }

    /**
     * Creates multiple messages for tests given the number of messages to create, a base payload to use, and the number
     * of groups to divide the messages into. The payload will be prepended with the sequence number of the generated
     * message, starting from 1. Groups are also numbered starting from 1. As an example, this:
     *
     *   multipleMessages(4, 'foo', 2)
     *
     * would create the following messages:
     *
     *   payload | message group
     *   '1 foo' | 'group-1'
     *   '2 foo' | 'group-2'
     *   '3 foo' | 'group-1'
     *   '4 foo' | 'group-2'
     *
     */
    def multipleMessages(int messageCount, String payload, int groupCount) {
        int count = 0
        (1..messageCount).collect{
            def groupNumber = (count++ % groupCount) + 1
            // Don't use a GString for the group id!
            new GenericMessage("$count $payload", [JMSXGroupID: 'group-' + groupNumber])
        }
    }

    long waitForNMessagesOrTimeToProcessMSerially(int messages, int serially) {
        long elapsed = System.currentTimeMillis() - testStart
        while (outbound.queueSize < messages && elapsed < serially * MAX_ALLOWED_TIME_PER_MESSAGE) {
            sleep 50
            elapsed = System.currentTimeMillis() - testStart
        }
        println "Exited waiting with queue size=$outbound.queueSize and elapsed=$elapsed"
        elapsed
    }
}
