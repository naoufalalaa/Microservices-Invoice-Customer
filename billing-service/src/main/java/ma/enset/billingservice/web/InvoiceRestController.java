package ma.enset.billingservice.web;

import ma.enset.billingservice.dto.InvoiceRequestDTO;
import ma.enset.billingservice.dto.InvoiceResponseDTO;
import ma.enset.billingservice.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceRestController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(path = "/invoices")
    public List<InvoiceResponseDTO> getAllInvoices(){
        return invoiceService.getAllInvoices();
    }

    @GetMapping(path = "/invoices/{id}")
    public InvoiceResponseDTO getInvoice(@PathVariable String id){
        return invoiceService.getInvoice(id);
    }

    @GetMapping(path = "/invoices/customer/{customerId}")
    public List<InvoiceResponseDTO> getInvoicesOfCustomer(@PathVariable String customerId){
        return invoiceService.getInvoicesOfCustomer(customerId);
    }

    @PostMapping(path = "/invoices")
    public InvoiceResponseDTO saveInvoice(@RequestBody InvoiceRequestDTO invoiceRequestDTO){
        return invoiceService.save(invoiceRequestDTO);
    }

    //update invoice
    @PutMapping(path = "/invoices/{id}")
    public InvoiceResponseDTO updateInvoice(@PathVariable String id,@RequestBody InvoiceRequestDTO invoiceRequestDTO){
        return invoiceService.updateInvoice(id,invoiceRequestDTO);
    }


}