package banking.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import banking.dto.AddressDto;
import banking.entities.Address;
import banking.entities.Bank;
import banking.responsitories.AddressRepository;
import banking.responsitories.BankRepository;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BankRepository bankRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable("id") Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody AddressDto addressDto) {
        Optional<Bank> bank = bankRepository.findById(addressDto.getBankId());
        Address address = new Address();
        address.setBank(bank.get());
        Address savedAddress = addressRepository.save(address);
        return ResponseEntity.created(URI.create("/addresses/" + savedAddress.getId()))
                .body(savedAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable("id") Long id, @RequestBody Address address) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        if (!existingAddress.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        address.setId(id);
        Address savedAddress = addressRepository.save(address);
        return ResponseEntity.ok(savedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        if (!existingAddress.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        addressRepository.delete(existingAddress.get());
        return ResponseEntity.noContent().build();
    }
}
