package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.entities.Collateral;

public interface CollateralRepository extends CrudRepository<Collateral, Long> {

}
