package org.springframework.integration.jms;

import org.springframework.integration.Message;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.jms.listener.AbstractMessageListenerContainer;

import javax.jms.JMSException;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 4/28/13
 * Time: 2:00 PM
 */
public class HeaderMappingSubscribableJmsChannel extends SubscribableJmsChannel {
    private JmsHeaderMapper headerMapper = new DefaultJmsHeaderMapper();

    public HeaderMappingSubscribableJmsChannel(AbstractMessageListenerContainer container, JmsTemplate jmsTemplate) {
        super(container, jmsTemplate);
    }

    @Override
    protected boolean doSend(Message<?> message, long timeout) {
        MessagePostProcessor messagePostProcessor = new HeaderMappingMessagePostProcessor(message, this.headerMapper);
        try {
            DynamicJmsTemplateProperties.setPriority(message.getHeaders().getPriority());
            getJmsTemplate().convertAndSend(message, messagePostProcessor);
        }
        finally {
            DynamicJmsTemplateProperties.clearPriority();
        }
        return true;
    }

    private static class HeaderMappingMessagePostProcessor implements MessagePostProcessor {
        private final Message<?> integrationMessage;
        private final JmsHeaderMapper headerMapper;

        private HeaderMappingMessagePostProcessor(Message<?> integrationMessage, JmsHeaderMapper headerMapper) {
            this.integrationMessage = integrationMessage;
            this.headerMapper = headerMapper;
        }

        public javax.jms.Message postProcessMessage(javax.jms.Message jmsMessage) throws JMSException {
            this.headerMapper.fromHeaders(this.integrationMessage.getHeaders(), jmsMessage);
            return jmsMessage;
        }
    }
}
