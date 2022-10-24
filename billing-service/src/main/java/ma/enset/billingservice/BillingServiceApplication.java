package ma.enset.billingservice;

import ma.enset.billingservice.dto.InvoiceRequestDTO;
import ma.enset.billingservice.services.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(InvoiceService invoiceService) {
        return args -> {
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(9000), "C0001"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(100), "C0001"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(800), "C0001"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(1720), "C0001"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(8290), "C0002"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(19), "C0002"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(1923), "C0002"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(2000), "C0003"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(32), "C0004"));
        };
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
