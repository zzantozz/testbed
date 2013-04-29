package org.springframework.integration.jms.config;

import org.springframework.integration.channel.ChannelInterceptor;
import org.springframework.integration.jms.AbstractJmsChannel;
import org.springframework.integration.jms.HeaderMappingSubscribableJmsChannel;
import org.springframework.integration.jms.SubscribableJmsChannel;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.AbstractMessageListenerContainer;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 4/28/13
 * Time: 2:28 PM
 */
public class BetterJmsChannelFactoryBean extends JmsChannelFactoryBean {
    private boolean headerMapping = false;

    public void setHeaderMapping(boolean headerMapping) {
        this.headerMapping = headerMapping;
    }

    @Override
    protected AbstractJmsChannel createInstance() throws Exception {
        AbstractJmsChannel instance = super.createInstance();
        if (headerMapping) {
            Assert.state(instance instanceof SubscribableJmsChannel, "You should configure this as a subscribable channel, then set headerMapping=true");
            AbstractMessageListenerContainer container = get(instance, "container", AbstractMessageListenerContainer.class);
            JmsTemplate template = get(instance, "jmsTemplate", JmsTemplate.class);
            HeaderMappingSubscribableJmsChannel channel = new HeaderMappingSubscribableJmsChannel(container, template);
            List<ChannelInterceptor> interceptors = get(this, "interceptors", List.class);
            if (!CollectionUtils.isEmpty(interceptors)) {
                channel.setInterceptors(interceptors);
            }
            channel.setBeanName(get(this, "beanName", String.class));
            channel.afterPropertiesSet();
            return channel;
        } else {
            return instance;
        }
    }

    private <T> T get(Object target, String fieldName, Class<T> expectedType) {
        Field field = ReflectionUtils.findField(target.getClass(), fieldName);
        ReflectionUtils.makeAccessible(field);
        Object value = ReflectionUtils.getField(field, target);
        if (value != null) Assert.isInstanceOf(expectedType, value, "Field didn't contain expected type: ");
        return (T) value;
    }
}
