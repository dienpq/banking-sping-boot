package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.entities.Bank;

public interface BankRepository extends CrudRepository<Bank, Long> {

}
