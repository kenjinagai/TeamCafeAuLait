package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application.
 *
 * @author Kenji Nagai
 *
 */
@EnableJpaRepositories(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = JpaRepository.class))
@PropertySource({ "classpath:Application.properties" })
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    /**
     * main.
     *
     * @param args command arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}