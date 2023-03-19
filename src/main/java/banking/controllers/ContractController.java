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

import banking.entities.Contract;
import banking.responsitories.ContractRepository;

@RestController
@RequestMapping("contract")
public class ContractController {
    @Autowired
    private ContractRepository contractRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContract(@PathVariable("id") Long id) {
        Optional<Contract> contract = contractRepository.findById(id);
        return contract.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
        Contract savedContract = contractRepository.save(contract);
        if (savedContract == null) {
            throw new RuntimeException("Failed to save contract");
        }
        return ResponseEntity.created(URI.create("/contracts/" + savedContract.getId()))
                .body(savedContract);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable("id") Long id, @RequestBody Contract contract) {
        Optional<Contract> existingContract = contractRepository.findById(id);
        if (!existingContract.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        contract.setId(id);
        Contract savedContract = contractRepository.save(contract);
        return ResponseEntity.ok(savedContract);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable("id") Long id) {
        Optional<Contract> existingContract = contractRepository.findById(id);
        if (!existingContract.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        contractRepository.delete(existingContract.get());
        return ResponseEntity.noContent().build();
    }
}
