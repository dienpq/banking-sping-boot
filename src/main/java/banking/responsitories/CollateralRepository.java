package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.models.User;

public interface CollateralRepository extends CrudRepository<User, Long> {

}
