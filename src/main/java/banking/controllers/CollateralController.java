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

import banking.dto.CollateralDto;
import banking.entities.Collateral;
import banking.entities.Contract;
import banking.response.ErrorResponse;
import banking.response.SuccessResponse;
import banking.responsitories.CollateralRepository;
import banking.responsitories.ContractRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("collateral")
public class CollateralController {
    @Autowired
    private CollateralRepository collateralRepository;

    @Autowired
    private ContractRepository contractRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getcollateral(@PathVariable("id") Long id) {
        Optional<Collateral> collateral = collateralRepository.findById(id);
        if (!collateral.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Address not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(collateral);
    }

    @PostMapping
    public ResponseEntity<Object> createcollateral(@Valid @RequestBody CollateralDto collateralDto) {
        Optional<Contract> contract = contractRepository.findById(collateralDto.getContractId());
        if (!contract.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Address not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Collateral collateral = new Collateral();
        collateral.setName(collateralDto.getName());
        collateral.setOwner(collateralDto.getOwner());
        collateral.setRelationOwnerAndCustomer(collateralDto.getRelationOwnerAndCustomer());
        collateral.setStatus(collateralDto.getStatus());
        collateral.setContract(contract.get());

        Collateral savedCollateral = collateralRepository.save(collateral);
        return ResponseEntity.ok(savedCollateral);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatecollateral(@PathVariable("id") Long id,
            @Valid @RequestBody CollateralDto collateralDto) {
        Optional<Collateral> existingCollateral = collateralRepository.findById(id);
        if (!existingCollateral.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Address not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Optional<Contract> contract = contractRepository.findById(collateralDto.getContractId());
        if (!contract.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Address not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Collateral collateral = new Collateral();
        collateral.setId(id);
        collateral.setName(collateralDto.getName());
        collateral.setOwner(collateralDto.getOwner());
        collateral.setRelationOwnerAndCustomer(collateralDto.getRelationOwnerAndCustomer());
        collateral.setStatus(collateralDto.getStatus());
        collateral.setContract(contract.get());

        Collateral savedCollateral = collateralRepository.save(collateral);
        return ResponseEntity.ok(savedCollateral);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletecollateral(@PathVariable("id") Long id) {
        Optional<Collateral> existingCollateral = collateralRepository.findById(id);
        if (!existingCollateral.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Address not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        collateralRepository.delete(existingCollateral.get());
        SuccessResponse success = new SuccessResponse(200, "Delete address successfull");
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}
