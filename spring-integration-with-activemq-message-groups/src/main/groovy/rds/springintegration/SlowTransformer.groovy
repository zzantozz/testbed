package rds.springintegration

import org.slf4j.LoggerFactory
import org.springframework.integration.Message
import org.springframework.integration.annotation.Transformer
import org.springframework.integration.support.MessageBuilder
/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 4/27/13
 * Time: 10:38 AM
 */
class SlowTransformer {
    def log = LoggerFactory.getLogger(SlowTransformer)
    long delay
    String name

    @Transformer
    Message<?> delay(Message<?> message) {
        log.info("$message.payload at delay transformer $name, group=$message.headers.JMSXGroupID")
        sleep delay
        MessageBuilder.fromMessage(message).setHeader(name, System.currentTimeMillis()).build()
    }
}
