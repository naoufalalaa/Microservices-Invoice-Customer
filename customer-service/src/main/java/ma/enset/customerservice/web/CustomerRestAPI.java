package ma.enset.customerservice.web;

import lombok.AllArgsConstructor;
import ma.enset.customerservice.dto.CustomerResponseDTO;
import ma.enset.customerservice.dto.CustomerRequestDTO;
import ma.enset.customerservice.dto.CustomerResponseDTO;
import ma.enset.customerservice.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CustomerRestAPI {
    private CustomerService customerService;

    @GetMapping(path = "/customers")
    public List<CustomerResponseDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping(path = "/customer")
    public CustomerResponseDTO saveCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){
        customerRequestDTO.setId(UUID.randomUUID().toString());
        return customerService.save(customerRequestDTO);
    }

    @GetMapping(path = "/customer/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable String id){
        return customerService.getCustomer(id);
    }
}
