package ma.enset.billingservice.restClient;

import ma.enset.billingservice.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerServiceRestClient {
    @GetMapping(path = "/api/customers")
    Customer getCustomer();

    @GetMapping(path = "/api/customer/{id}")
    Customer getCustomer(@PathVariable String id);
}
