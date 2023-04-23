package banking.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import banking.common.RandomCodeGenerator;
import banking.dto.PaymentDto;
import banking.dto.LoanDto;
import banking.entities.Contract;
import banking.entities.Loan;
import banking.entities.TypeLoan;
import banking.entities.User;
import banking.reponsitories.ContractRepository;
import banking.reponsitories.LoanRepository;
import banking.reponsitories.TypeLoanRepository;
import banking.reponsitories.UserRepository;
import banking.response.ErrorResponse;
import banking.response.SuccessResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("loan")
public class LoanController {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TypeLoanRepository typeLoanRepository;

    @Autowired
    private ContractRepository contractRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLoan(@PathVariable("id") Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        if (!loan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(loan);
    }

    @PostMapping
    public ResponseEntity<Object> createLoan(@Valid @RequestBody LoanDto loanDto) {
        Optional<User> user = userRepository.findById(loanDto.getUserId());
        if (!user.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Optional<TypeLoan> typeLoan = typeLoanRepository.findById(loanDto.getTypeLoanId());
        if (!typeLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Type loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        RandomCodeGenerator code = new RandomCodeGenerator(12);
        LocalDateTime now = LocalDateTime.now();

        Loan loan = new Loan();
        loan.setCode(code.generateRandomCode());
        loan.setPriceRemaining(loanDto.getPriceRemaining());
        loan.setLatePayment(0.0);
        loan.setAmountPaid(0.0);
        loan.setClosingStatement(now);
        loan.setDueDate(now.plusMonths(1));
        loan.setUser(user.get());
        loan.setTypeLoan(typeLoan.get());

        Loan savedLoan = loanRepository.save(loan);
        return ResponseEntity.ok(savedLoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLoan(@PathVariable("id") Long id, @Valid @RequestBody LoanDto loanDto) {
        Optional<Loan> existingLoan = loanRepository.findById(id);
        if (!existingLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Optional<User> user = userRepository.findById(loanDto.getUserId());
        if (!user.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Optional<TypeLoan> typeLoan = typeLoanRepository.findById(loanDto.getTypeLoanId());
        if (!typeLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Type loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Loan loan = new Loan();

        loan.setId(id);
        loan.setCode(existingLoan.get().getCode());
        loan.setPriceRemaining(loanDto.getPriceRemaining());
        loan.setLatePayment(loanDto.getLatePayment());
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setClosingStatement(loanDto.getClosingStatement());
        loan.setDueDate(loanDto.getDueDate());
        loan.setUser(user.get());
        loan.setTypeLoan(typeLoan.get());

        Loan savedLoan = loanRepository.save(loan);
        return ResponseEntity.ok(savedLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLoan(@PathVariable("id") Long id) {
        Optional<Loan> existingLoan = loanRepository.findById(id);
        if (!existingLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        loanRepository.delete(existingLoan.get());
        SuccessResponse success = new SuccessResponse(200, "Delete loan successfull");
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @GetMapping("list")
    public ResponseEntity<Object> getAllLoanBySatusAndUser(@RequestParam("status") String status) {
        Long id = (long) 1;
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Iterable<Loan> loans = loanRepository.findAllByUser(user.get());
        if (!loans.iterator().hasNext()) {
            ErrorResponse error = new ErrorResponse(404, "Type loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        List<Map<String, Object>> loanList = new ArrayList<>();
        for (Loan loan : loans) {
            if (status.equals("all") || loan.getContract().getStatus() == Integer.parseInt(status)) {
                Contract contract = loan.getContract();
                Map<String, Object> loanMap = new HashMap<>();
                loanMap.put("loan", loan);
                loanMap.put("contract", contract);
                loanList.add(loanMap);
            }
        }
        return ResponseEntity.ok(loanList);
    }

    @GetMapping("/{id}/contract")
    public ResponseEntity<Object> getContractByLoan(@PathVariable("id") Long id) {
        Optional<Contract> contract = contractRepository.findContractByLoanId(id);
        if (!contract.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Contract not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(contract);
    }

    @GetMapping("/{id}/calculate-interest")
    public ResponseEntity<Object> calculateInterest(@PathVariable("id") Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        if (!loan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        Optional<Contract> contract = contractRepository.findContractByLoanId(id);
        if (!contract.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Contract not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        Double priceLoan = contract.get().getPriceLoan(); // Số tiền vay
        Integer timeLoan = contract.get().getTimeLoan(); // Thời hạn vay
        Double interestLoan = contract.get().getInterestLoan(); // Lãi suất

        Double tienGocHangThang = priceLoan / timeLoan;
        Double tienLaiThangNay = loan.get().getPriceRemaining() * (interestLoan / 100) / timeLoan;
        Double tienPhatTraMuon = loan.get().getLatePayment() * contract.get().getPenaltyInterestRate() / 100 * 30;
        Double soTienDaTra = loan.get().getAmountPaid();

        Double paymentMin = tienGocHangThang + tienLaiThangNay - soTienDaTra;
        Double paymentAll = tienGocHangThang + tienLaiThangNay + tienPhatTraMuon - soTienDaTra;

        Map<String, Double> paymentInfo = new HashMap<>();
        paymentInfo.put("paymentMin", paymentMin >= 0 ? paymentMin : 0);
        paymentInfo.put("paymentAll", paymentAll >= 0 ? paymentAll : 0);

        return ResponseEntity.ok(paymentInfo);
    }

    @PostMapping("/{id}/closing-statement")
    public ResponseEntity<Object> closingStatement(@PathVariable("id") Long id) {
        Optional<Loan> existingLoan = loanRepository.findById(id);
        if (!existingLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Optional<Contract> contract = contractRepository.findContractByLoanId(id);
        if (!contract.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Contract not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Double priceLoan = contract.get().getPriceLoan(); // Số tiền vay
        Integer timeLoan = contract.get().getTimeLoan(); // Thời hạn vay
        Double interestLoan = contract.get().getInterestLoan(); // Lãi suất

        Double tienGocHangThang = priceLoan / timeLoan;
        Double tienLaiThangNay = existingLoan.get().getPriceRemaining() * (interestLoan / 100) / timeLoan;
        Double tienPhatTraMuon = existingLoan.get().getLatePayment() * contract.get().getPenaltyInterestRate() / 100
                * 30;
        Double soTienDaTra = existingLoan.get().getAmountPaid();

        Double priceRemaining = existingLoan.get().getPriceRemaining() - priceLoan / timeLoan;

        Loan loan = new Loan();

        loan.setId(id);
        loan.setCode(existingLoan.get().getCode());
        loan.setPriceRemaining(priceRemaining);
        loan.setLatePayment(0.0);
        loan.setAmountPaid(0.0);
        loan.setClosingStatement(LocalDateTime.now());
        loan.setDueDate(existingLoan.get().getDueDate().plusMonths(1));
        loan.setUser(existingLoan.get().getUser());
        loan.setTypeLoan(existingLoan.get().getTypeLoan());

        Double total = tienGocHangThang + tienLaiThangNay + tienPhatTraMuon - soTienDaTra;
        System.out.println(soTienDaTra);
        if (total <= 0) {
            // Trả dư
            loan.setAmountPaid(-total);
        } else {
            // Trả thiếu
            loan.setLatePayment(total);
        }

        Loan savedLoan = loanRepository.save(loan);
        return ResponseEntity.ok(savedLoan);
    }

    @PostMapping("/{id}/payment")
    public ResponseEntity<Object> payment(@PathVariable("id") Long id, @Valid @RequestBody PaymentDto paymentDto) {
        Optional<Loan> existingLoan = loanRepository.findById(id);
        if (!existingLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Optional<Contract> contract = contractRepository.findContractByLoanId(id);
        if (!contract.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Contract not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Loan loan = new Loan();

        loan.setId(id);
        loan.setCode(existingLoan.get().getCode());
        loan.setPriceRemaining(existingLoan.get().getPriceRemaining());
        loan.setLatePayment(existingLoan.get().getLatePayment());
        loan.setAmountPaid(existingLoan.get().getAmountPaid() + paymentDto.getPrice());
        loan.setClosingStatement(existingLoan.get().getClosingStatement());
        loan.setDueDate(existingLoan.get().getDueDate());
        loan.setDes(paymentDto.getDes());
        loan.setUser(existingLoan.get().getUser());
        loan.setTypeLoan(existingLoan.get().getTypeLoan());

        Loan savedLoan = loanRepository.save(loan);

        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        User user = new User();

        user.setId(id);
        user.setAccount(existingUser.get().getAccount());
        user.setPassword(existingUser.get().getPassword());
        user.setFullname(existingUser.get().getFullname());
        user.setBirthday(existingUser.get().getBirthday());
        user.setGender(existingUser.get().getGender());
        user.setPhone(existingUser.get().getPhone());
        user.setEmail(existingUser.get().getEmail());
        user.setBankAccount(existingUser.get().getBankAccount());
        user.setPrice(existingUser.get().getPrice() - paymentDto.getPrice());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.ok(savedLoan);
    }

}
