package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.models.Collateral;

public interface CollateralRepository extends CrudRepository<Collateral, Long> {

}
