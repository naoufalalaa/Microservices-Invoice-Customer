package ma.enset.customerservice.services;

import ma.enset.customerservice.dto.CustomerRequestDTO;
import ma.enset.customerservice.dto.CustomerResponseDTO;
import ma.enset.customerservice.entities.Customer;
import ma.enset.customerservice.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImp implements CustomerService {
    private CustomerRepository customerRepository;
    ModelMapper modelMapper;


    public CustomerServiceImp(CustomerRepository customerRepository,ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = modelMapper.map(customerRequestDTO, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);

        CustomerResponseDTO customerResponseDTO = modelMapper.map(savedCustomer,CustomerResponseDTO.class);
        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO getCustomer(String id) {
        //check if customer exists
        Customer customer = customerRepository.findById(id).orElse(null);
        // check if customer exists
        if(customer == null) throw new RuntimeException("Customer not found");
        CustomerResponseDTO customerResponseDTO = modelMapper.map(customer,CustomerResponseDTO.class);
        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO updateCustomer(String id, CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRepository.findById(id).orElse(null);
        // check if customer exists
        if(customer == null) throw new RuntimeException("Customer not found");

        customer.setId(customerRequestDTO.getId());
        customer.setName(customerRequestDTO.getName());
        customer.setEmail(customerRequestDTO.getEmail());

        Customer savedCustomer = customerRepository.save(customer);

       // CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(savedCustomer);
        CustomerResponseDTO customerResponseDTO = modelMapper.map(savedCustomer, CustomerResponseDTO.class);
        return customerResponseDTO;
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDTO> customerResponseDTOS = customers.stream().map(customer -> {
            //CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(customer);
            return modelMapper.map(customer,CustomerResponseDTO.class);
        }).collect(Collectors.toList());
        return customerResponseDTOS;
    }
}
