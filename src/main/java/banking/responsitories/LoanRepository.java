package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.models.Loan;

public interface LoanRepository extends CrudRepository<Loan, Long> {

}
