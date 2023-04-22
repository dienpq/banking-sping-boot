package banking.reponsitories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import banking.entities.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long> {
    Optional<Contract> findContractByLoanId(Long id);
}
