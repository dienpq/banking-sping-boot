package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.entities.ExpiryDate;

public interface ExpiryDateResponsitory extends CrudRepository<ExpiryDate, Long> {

}
