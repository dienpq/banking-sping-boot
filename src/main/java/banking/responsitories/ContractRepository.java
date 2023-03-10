package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.models.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long> {

}
