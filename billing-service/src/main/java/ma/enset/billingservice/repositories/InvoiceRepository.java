package ma.enset.billingservice.repositories;

import ma.enset.billingservice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,String> {
    List<Invoice> findAllByCustomerId(String customerId);
}
