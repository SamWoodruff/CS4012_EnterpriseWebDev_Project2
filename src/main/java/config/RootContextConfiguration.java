package config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;


@Configuration
@ComponentScan(
        basePackages = "site",
        excludeFilters = @ComponentScan.Filter(Controller.class)
)
public class RootContextConfiguration {
}
