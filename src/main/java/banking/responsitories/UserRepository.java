package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
