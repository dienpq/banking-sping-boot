package banking.reponsitories;

import org.springframework.data.repository.CrudRepository;

import banking.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
