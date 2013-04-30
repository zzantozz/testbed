package rds.springintegration

import org.springframework.integration.jms.DefaultJmsHeaderMapper
import org.springframework.integration.jms.JmsHeaderMapper
import org.springframework.jms.support.converter.MessageConversionException
import org.springframework.jms.support.converter.SimpleMessageConverter

import javax.jms.JMSException
import javax.jms.Message
import javax.jms.Session
/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 4/29/13
 * Time: 8:55 PM
 */
class GroupingMessageConverter extends SimpleMessageConverter {
    JmsHeaderMapper headerMapper = new DefaultJmsHeaderMapper()

    @Override
    Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        org.springframework.integration.Message siMessage = object as org.springframework.integration.Message
        def jmsMessage = super.toMessage(object, session)
        headerMapper.fromHeaders(siMessage.headers, jmsMessage)
        jmsMessage
    }
}
