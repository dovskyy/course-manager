package pl.dovskyy.studentmanager.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.dovskyy.studentmanager.repository.StudentRepository;

@Configuration
public class StartConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            //code to be executed at start goes here
        };
    }
}
