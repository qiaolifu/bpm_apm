package bpm;


import bpm.config.AppDispatcherServletConfiguration;
import bpm.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import({
ApplicationConfiguration.class,
AppDispatcherServletConfiguration.class
})
@SpringBootApplication( exclude = {SecurityAutoConfiguration.class})
public class BpmApplication {
    public static void main(String[] args) {
        SpringApplication.run(BpmApplication.class, args);
    }

}
