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
        outbound.clear()
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
        def messages = multipleMessages(5, randomness)

        when:
        long start = System.currentTimeMillis()
        messages.each{ messaging.send 'inbound', it }

        then:
        long elapsed = System.currentTimeMillis() - start
        while (outbound.queueSize < 5 && elapsed < 20000) {
            sleep 50
            elapsed = System.currentTimeMillis() - start
        }
        outbound.queueSize == 5
        elapsed > 5 * 1200
        elapsed < 15000
    }

    def multipleMessages(int number, String payload) {
        (1..number).collect{ new GenericMessage("$it $payload") }
    }
}
