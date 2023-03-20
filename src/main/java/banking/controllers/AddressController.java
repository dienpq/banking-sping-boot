package banking.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import banking.response.ErrorResponse;
import banking.response.SuccessResponse;
import banking.responsitories.AddressRepository;
import banking.responsitories.BankRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BankRepository bankRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAddress(@PathVariable("id") Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Address not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(address);
    }

    @PostMapping
    public ResponseEntity<Object> createAddress(@Valid @RequestBody AddressDto addressDto) {
        Optional<Bank> bank = bankRepository.findById(addressDto.getBankId());
        if (!bank.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Address not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Address address = new Address();
        address.setName(addressDto.getName());
        address.setBank(bank.get());

        Address savedAddress = addressRepository.save(address);
        return ResponseEntity.ok(savedAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAddress(@PathVariable("id") Long id,
            @Valid @RequestBody AddressDto addressDto) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        if (!existingAddress.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Address not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Optional<Bank> bank = bankRepository.findById(addressDto.getBankId());
        if (!bank.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Address not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Address address = new Address();

        address.setId(id);
        address.setName(addressDto.getName());
        address.setBank(bank.get());

        Address savedAddress = addressRepository.save(address);
        return ResponseEntity.ok(savedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable("id") Long id) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        if (!existingAddress.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Address not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        addressRepository.delete(existingAddress.get());
        SuccessResponse error = new SuccessResponse(200, "Delete address successfull");
        return ResponseEntity.status(HttpStatus.OK).body(error);
    }
}
