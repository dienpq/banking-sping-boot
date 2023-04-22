package banking.reponsitories;

import org.springframework.data.repository.CrudRepository;

import banking.entities.Loan;
import banking.entities.User;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    Iterable<Loan> findAllByUser(User user);
}
