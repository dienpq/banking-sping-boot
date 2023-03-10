package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.models.Bank;

public interface BankRepository extends CrudRepository<Bank, Long> {

}
