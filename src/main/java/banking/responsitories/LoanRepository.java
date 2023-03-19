package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.entities.Loan;

public interface LoanRepository extends CrudRepository<Loan, Long> {

}
