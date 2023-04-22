package banking.reponsitories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import banking.entities.ExpiryDate;

public interface ExpiryDateReponsitory extends CrudRepository<ExpiryDate, Long> {
    List<ExpiryDate> findAllByTypeLoanId(Long typeLoanId);

}
