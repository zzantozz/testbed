package rds.springintegration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.integration.core.MessagingTemplate
import org.springframework.integration.core.PollableChannel
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
    @Configuration
    @ImportResource('/applicationContext.xml')
    @SuppressWarnings("GrMethodMayBeStatic")
    static class Beans {
        @Bean def template() { new MessagingTemplate() }
    }

    @Autowired MessagingTemplate messaging
    @Autowired PollableChannel outbound

    def 'messages go from point A to point B'() {
        given:
        def msg = new GenericMessage('nothing special')

        when:
        messaging.send 'inbound', msg

        then:
        def msgOut = outbound.receive(5000)
        msgOut
        msgOut.payload == 'nothing special'
    }
}
