package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
