package ma.enset.customerservice;

import ma.enset.customerservice.dto.CustomerRequestDTO;
import ma.enset.customerservice.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerService customerService) {
        return args -> {
            customerService.save(new CustomerRequestDTO("C0001", "Mohamed", "m@email"));
            customerService.save(new CustomerRequestDTO("C0002", "Ahmed", "a@email"));
            customerService.save(new CustomerRequestDTO("C0003", "Ali", "ali@email"));
            customerService.save(new CustomerRequestDTO("C0004", "Naoufal", "n@email"));
        };
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
