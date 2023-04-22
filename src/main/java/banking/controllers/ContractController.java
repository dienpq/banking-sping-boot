package banking.controllers;

import java.time.LocalDateTime;
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

import banking.common.RandomCodeGenerator;
import banking.dto.ContractDto;
import banking.dto.UpdateStatusContractDto;
import banking.entities.Contract;
import banking.entities.Loan;
import banking.reponsitories.ContractRepository;
import banking.reponsitories.LoanRepository;
import banking.response.ErrorResponse;
import banking.response.SuccessResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("contract")
public class ContractController {
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getContract(@PathVariable("id") Long id) {
        Optional<Contract> contract = contractRepository.findById(id);
        if (!contract.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Contract not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(contract);
    }

    @PostMapping
    public ResponseEntity<Object> createContract(@Valid @RequestBody ContractDto contractDto) {
        Optional<Loan> loan = loanRepository.findById(contractDto.getLoanId());
        if (!loan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        RandomCodeGenerator code = new RandomCodeGenerator(12);

        Contract contract = new Contract();

        contract.setId(contractDto.getLoanId());
        contract.setCode(code.generateRandomCode());
        contract.setFullname(contractDto.getFullname());
        contract.setGender(contractDto.getGender());
        contract.setBirthday(contractDto.getBirthday());
        contract.setNational(contractDto.getNational());
        contract.setIdNumber(contractDto.getIdNumber());
        contract.setIssuedDate(contractDto.getIssuedDate());
        contract.setIssuedPlace(contractDto.getIssuedPlace());
        contract.setPermanentAddress(contractDto.getPermanentAddress());
        contract.setCurrentResidence(contractDto.getCurrentResidence());
        contract.setPhone(contractDto.getPhone());
        contract.setEmail(contractDto.getEmail());
        contract.setMarital(contractDto.getMarital());
        contract.setAcademicLevel(contractDto.getAcademicLevel());
        contract.setHomeOwnership(contractDto.getHomeOwnership());
        contract.setVehicles(contractDto.getVehicles());
        contract.setNameCompany(contractDto.getNameCompany());
        contract.setPhoneCompany(contractDto.getPhoneCompany());
        contract.setAddressCompany(contractDto.getAddressCompany());
        contract.setJob(contractDto.getJob());
        contract.setTypeContractJob(contractDto.getTypeContractJob());
        contract.setTypeReceiveWage(contractDto.getTypeReceiveWage());
        contract.setLoanPurpose(contractDto.getLoanPurpose());
        contract.setPriceLoan(contractDto.getPriceLoan());
        contract.setTimeLoan(contractDto.getTimeLoan());
        contract.setDebtPaymentMethod(contractDto.getDebtPaymentMethod());
        contract.setOtherSuggestions(contractDto.getOtherSuggestions());
        contract.setWage(contractDto.getWage());
        contract.setDividend(contractDto.getDividend());
        contract.setProfit(contractDto.getProfit());
        contract.setPropertyRentalIncome(contractDto.getPropertyRentalIncome());
        contract.setOtherIncome(contractDto.getOtherIncome());
        contract.setWageWifeOrHusband(contractDto.getWageWifeOrHusband());
        contract.setDividendWifeOrHusband(contractDto.getDividendWifeOrHusband());
        contract.setProfitWifeOrHusband(contractDto.getProfitWifeOrHusband());
        contract.setPropertyRentalIncomeWifeOrHusband(contractDto.getPropertyRentalIncomeWifeOrHusband());
        contract.setOtherIncomeWifeOrHusband(contractDto.getOtherIncomeWifeOrHusband());
        contract.setWageSupporter(contractDto.getWageSupporter());
        contract.setDividendSupporter(contractDto.getDividendSupporter());
        contract.setProfitSupporter(contractDto.getProfitSupporter());
        contract.setPropertyRentalIncomeSupporter(contractDto.getPropertyRentalIncomeSupporter());
        contract.setOtherIncomeSupporter(contractDto.getOtherIncomeSupporter());
        contract.setCostOfLiving(contractDto.getCostOfLiving());
        contract.setInterestPaymentsOnLoans(contractDto.getInterestPaymentsOnLoans());
        contract.setOtherCosts(contractDto.getOtherCosts());
        contract.setOtherExtraordinaryIncome(contractDto.getOtherExtraordinaryIncome());
        contract.setStatus(0);
        contract.setLoan(loan.get());
        contract.setCreatedAt(LocalDateTime.now());
        contract.setUpdatedAt(LocalDateTime.now());

        Contract savedContract = contractRepository.save(contract);
        return ResponseEntity.ok(savedContract);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateContract(@PathVariable("id") Long id,
            @Valid @RequestBody ContractDto contractDto) {
        Optional<Contract> existingContract = contractRepository.findById(id);
        if (!existingContract.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Contract not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Optional<Loan> loan = loanRepository.findById(contractDto.getLoanId());
        if (!loan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Contract contract = new Contract();

        contract.setId(id);
        contract.setFullname(contractDto.getFullname());
        contract.setGender(contractDto.getGender());
        contract.setBirthday(contractDto.getBirthday());
        contract.setNational(contractDto.getNational());
        contract.setIdNumber(contractDto.getIdNumber());
        contract.setIssuedDate(contractDto.getIssuedDate());
        contract.setIssuedPlace(contractDto.getIssuedPlace());
        contract.setPermanentAddress(contractDto.getPermanentAddress());
        contract.setCurrentResidence(contractDto.getCurrentResidence());
        contract.setPhone(contractDto.getPhone());
        contract.setEmail(contractDto.getEmail());
        contract.setMarital(contractDto.getMarital());
        contract.setAcademicLevel(contractDto.getAcademicLevel());
        contract.setHomeOwnership(contractDto.getHomeOwnership());
        contract.setVehicles(contractDto.getVehicles());
        contract.setNameCompany(contractDto.getNameCompany());
        contract.setPhoneCompany(contractDto.getPhoneCompany());
        contract.setAddressCompany(contractDto.getAddressCompany());
        contract.setJob(contractDto.getJob());
        contract.setTypeContractJob(contractDto.getTypeContractJob());
        contract.setTypeReceiveWage(contractDto.getTypeReceiveWage());
        contract.setLoanPurpose(contractDto.getLoanPurpose());
        contract.setPriceLoan(contractDto.getPriceLoan());
        contract.setTimeLoan(contractDto.getTimeLoan());
        contract.setDebtPaymentMethod(contractDto.getDebtPaymentMethod());
        contract.setOtherSuggestions(contractDto.getOtherSuggestions());
        contract.setWage(contractDto.getWage());
        contract.setDividend(contractDto.getDividend());
        contract.setProfit(contractDto.getProfit());
        contract.setPropertyRentalIncome(contractDto.getPropertyRentalIncome());
        contract.setOtherIncome(contractDto.getOtherIncome());
        contract.setWageWifeOrHusband(contractDto.getWageWifeOrHusband());
        contract.setDividendWifeOrHusband(contractDto.getDividendWifeOrHusband());
        contract.setProfitWifeOrHusband(contractDto.getProfitWifeOrHusband());
        contract.setPropertyRentalIncomeWifeOrHusband(contractDto.getPropertyRentalIncomeWifeOrHusband());
        contract.setOtherIncomeWifeOrHusband(contractDto.getOtherIncomeWifeOrHusband());
        contract.setWageSupporter(contractDto.getWageSupporter());
        contract.setDividendSupporter(contractDto.getDividendSupporter());
        contract.setProfitSupporter(contractDto.getProfitSupporter());
        contract.setPropertyRentalIncomeSupporter(contractDto.getPropertyRentalIncomeSupporter());
        contract.setOtherIncomeSupporter(contractDto.getOtherIncomeSupporter());
        contract.setCostOfLiving(contractDto.getCostOfLiving());
        contract.setInterestPaymentsOnLoans(contractDto.getInterestPaymentsOnLoans());
        contract.setOtherCosts(contractDto.getOtherCosts());
        contract.setOtherExtraordinaryIncome(contractDto.getOtherExtraordinaryIncome());
        contract.setStatus(0);
        contract.setLoan(loan.get());
        contract.setUpdatedAt(LocalDateTime.now());

        Contract savedContract = contractRepository.save(contract);
        return ResponseEntity.ok(savedContract);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Object> updateStatusContract(@PathVariable("id") Long id,
            @Valid @RequestBody UpdateStatusContractDto updateStatusContractDto) {
        Optional<Contract> existingContract = contractRepository.findById(id);
        if (!existingContract.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Contract not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Contract contract = existingContract.get();
        contract.setStatus(updateStatusContractDto.getStatus());
        System.out.println(updateStatusContractDto);
        SuccessResponse success = new SuccessResponse(200, "Update status contract successfull");
        contractRepository.save(contract);
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContract(@PathVariable("id") Long id) {
        Optional<Contract> existingContract = contractRepository.findById(id);
        if (!existingContract.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Contract not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        contractRepository.delete(existingContract.get());
        SuccessResponse success = new SuccessResponse(200, "Delete contract successfull");
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<Object> getStatusContract(@PathVariable("id") Long id) {
        Optional<Contract> contract = contractRepository.findById(id);
        if (!contract.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Contract not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(contract);
    }
}
