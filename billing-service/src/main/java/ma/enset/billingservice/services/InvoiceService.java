package ma.enset.billingservice.services;

import ma.enset.billingservice.dto.InvoiceRequestDTO;
import ma.enset.billingservice.dto.InvoiceResponseDTO;

import java.util.List;

public interface InvoiceService {
    //save
    InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO);
    //get invoice by id
    InvoiceResponseDTO getInvoice(String id);
    //list invoices of customer
    List<InvoiceResponseDTO> getInvoicesOfCustomer(String customerId);
    //update invoice
    InvoiceResponseDTO updateInvoice(String id,InvoiceRequestDTO invoiceRequestDTO);
    List<InvoiceResponseDTO> getAllInvoices();

    void deleteInvoice(String id);
}
