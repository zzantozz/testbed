package rds.testbed.dynamicproperties;

import org.impalaframework.config.StringPropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 5/19/12
 * Time: 2:21 PM
 */
public class SpringWithImpalaDynamicProperties {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Something bean = context.getBean(Something.class);
        bean.printValues();
        System.setProperty("another.dynamic.value", "updated");
        bean.printValues();
        System.setProperty("a.dynamic.value", "updated");
        bean.printValues();
    }

    @Component
    public static class Something {
        private final String staticValue;
        private final StringPropertyValue dynamicValue1;
        private StringPropertyValue dynamicValue2;

        @Autowired
        public Something(@Value("${a.static.value}") String staticValue,
                         @Qualifier("a.dynamic.value") StringPropertyValue dynamicValue1,
                         @Qualifier("another.dynamic.value") StringPropertyValue dynamicValue2) {
            this.staticValue = staticValue;
            this.dynamicValue1 = dynamicValue1;
            this.dynamicValue2 = dynamicValue2;
        }

        public void printValues() {
            System.out.printf("static=[%s] dynamic1=[%s] dynamic2=[%s]%n", staticValue, dynamicValue1.getValue(), dynamicValue2.getValue());
        }
    }
}
