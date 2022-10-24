package ma.enset.billingeurekadiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BillingEurekaDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingEurekaDiscoveryApplication.class, args);
    }

}
