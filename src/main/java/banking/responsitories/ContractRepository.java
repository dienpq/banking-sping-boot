package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.entities.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long> {

}
