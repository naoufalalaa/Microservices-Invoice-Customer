package ma.enset.customerservice.services;

import ma.enset.customerservice.dto.CustomerRequestDTO;
import ma.enset.customerservice.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    public CustomerResponseDTO getCustomer(String id);
    public CustomerResponseDTO updateCustomer(String id,CustomerRequestDTO customerRequestDTO);
    public void deleteCustomer(String id);
    public List<CustomerResponseDTO> getAllCustomers();
}
