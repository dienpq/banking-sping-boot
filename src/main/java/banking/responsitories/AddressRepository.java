package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.models.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
