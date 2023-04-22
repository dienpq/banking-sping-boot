package banking.reponsitories;

import org.springframework.data.repository.CrudRepository;

import banking.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
