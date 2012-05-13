package rds.testbed.springDataJpa;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 5/13/12
 * Time: 12:52 AM
 */
public class SpringDataJpaMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        ICustomerService service = context.getBean(ICustomerService.class);
        Customer customerToCreate = new Customer();
        customerToCreate.setFirstName("bob");
        service.createCustomer(customerToCreate);
        Customer loaded = service.getCustomer(customerToCreate.getId());
        System.out.println(loaded);
    }
}
