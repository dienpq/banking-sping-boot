package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.models.User;

public interface UserRespository extends CrudRepository<User, Long> {
}
