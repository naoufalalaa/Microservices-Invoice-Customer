package ma.enset.billingservice.services;

import ma.enset.billingservice.dto.InvoiceRequestDTO;
import ma.enset.billingservice.dto.InvoiceResponseDTO;
import ma.enset.billingservice.entities.Customer;
import ma.enset.billingservice.entities.Invoice;
import ma.enset.billingservice.repositories.InvoiceRepository;
import ma.enset.billingservice.restClient.CustomerServiceRestClient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    //repository
    private InvoiceRepository invoiceRepository;
    //model mapper
    ModelMapper modelMapper;
    //customer service
    CustomerServiceRestClient customerServiceRestClient;
    // constructor
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, ModelMapper modelMapper, CustomerServiceRestClient customerServiceRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
        this.customerServiceRestClient = customerServiceRestClient;
    }
    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {

        // get customer
        Customer customer = customerServiceRestClient.getCustomer(invoiceRequestDTO.getCustomerId());
        //check if customer exists
        if(customer == null) throw new RuntimeException("Customer not found");

        // create invoice
        Invoice invoice = modelMapper.map(invoiceRequestDTO, Invoice.class);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setInvoiceDate(new Date());
        // set customer
        invoice.setCustomer(customer);
        // save invoice
        Invoice savedInvoice = invoiceRepository.save(invoice);
        // map invoice to invoiceResponseDTO
        InvoiceResponseDTO invoiceResponseDTO = modelMapper.map(savedInvoice, InvoiceResponseDTO.class);
        return invoiceResponseDTO;
    }

    @Override
    public InvoiceResponseDTO getInvoice(String id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        // fetch customer
        Customer customer = customerServiceRestClient.getCustomer(invoice.getCustomerId());
        // check if customer exists
        if(customer == null) throw new RuntimeException("Customer not found");
        //set customer
        invoice.setCustomer(customer);
        // map invoice to invoiceResponseDTO
        InvoiceResponseDTO invoiceResponseDTO = modelMapper.map(invoice, InvoiceResponseDTO.class);
        return invoiceResponseDTO;
    }

    @Override
    public List<InvoiceResponseDTO> getInvoicesOfCustomer(String customerId) {
        Customer customer = customerServiceRestClient.getCustomer(customerId);
        // check if customer exists
        if(customer == null) throw new RuntimeException("Customer not found");

        List<Invoice> invoices = invoiceRepository.findAllByCustomerId(customerId);
        invoices.forEach(invoice -> invoice.setCustomer(customer));

        // map invoice to invoiceResponseDTO
        List<InvoiceResponseDTO> invoiceResponseDTOS = invoices.stream().map(invoice ->
            modelMapper.map(invoice, InvoiceResponseDTO.class)).collect(Collectors.toList()
        );
        return invoiceResponseDTOS;
    }

    @Override
    public InvoiceResponseDTO updateInvoice(String id, InvoiceRequestDTO invoiceRequestDTO) {
        Invoice invoice = invoiceRepository.findById(id).get();
        invoice.setAmount(invoiceRequestDTO.getAmount());
        Invoice savedInvoice = invoiceRepository.save(invoice);
        InvoiceResponseDTO invoiceResponseDTO = modelMapper.map(savedInvoice, InvoiceResponseDTO.class);
        return invoiceResponseDTO;
    }

    @Override
    public List<InvoiceResponseDTO> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        //check if invoices exists
        if(invoices == null) throw new RuntimeException("Invoices not found");
        invoices.forEach(invoice -> {
            Customer customer = customerServiceRestClient.getCustomer(invoice.getCustomerId());
            //check if customer exists
            if(customer == null) throw new RuntimeException("Customer not found");
            invoice.setCustomer(customer);
        });
        List<InvoiceResponseDTO> invoiceResponseDTOS = invoices.stream().map(invoice ->
                modelMapper.map(invoice, InvoiceResponseDTO.class)).collect(Collectors.toList()
        );
        return invoiceResponseDTOS;
    }

    @Override
    public void deleteInvoice(String id) {
        invoiceRepository.deleteById(id);
    }
}
