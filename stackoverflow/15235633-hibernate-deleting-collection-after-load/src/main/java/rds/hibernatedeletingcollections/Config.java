package rds.hibernatedeletingcollections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 3/5/13
 * Time: 9:45 PM
 */
@Configuration
@ComponentScan
@EnableJpaRepositories
@EnableTransactionManagement
@EnableWebMvc
public class Config {
    @Bean
    ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
