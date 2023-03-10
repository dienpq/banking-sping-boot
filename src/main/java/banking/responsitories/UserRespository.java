package banking.responsitories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import banking.models.User;

@Repository
public interface UserRespository extends CrudRepository<User, Long> {

}
