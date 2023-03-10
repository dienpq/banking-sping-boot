package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.models.TypeLoan;

public interface TypeLoanRepository extends CrudRepository<TypeLoan, Long> {

}
