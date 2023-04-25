package banking.controllers;

import banking.dto.LoanDto;
import banking.dto.PaymentDto;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanControllerTest {
    @InjectMocks
    private LoanController loanController;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TypeLoanRepository typeLoanRepository;

    @Test
    public void testGetLoan() {
        Long id = 1L;
        Loan loan = new Loan();
        loan.setId(id);
        when(loanRepository.findById(id)).thenReturn(Optional.of(loan));
        ResponseEntity<Object> response = loanController.getLoan(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Optional.of(loan), response.getBody());

    }

    @Test
    public void testGetLoanNotFound() {
        Long id = 1L;

        when(loanRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = loanController.getLoan(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals(404, error.getStatusCode());
        assertEquals("Loan not found", error.getMessage());
    }


    @Test
    public void testCreateLoan() {
        Long userId = 1L;
        Long typeLoanId = 1L;
        LoanDto loanDto = new LoanDto();
        loanDto.setUserId(userId);
        loanDto.setTypeLoanId(typeLoanId);

        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        TypeLoan typeLoan = new TypeLoan();
        typeLoan.setId(typeLoanId);
        when(typeLoanRepository.findById(typeLoanId)).thenReturn(Optional.of(typeLoan));

        Loan loan = new Loan();
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        ResponseEntity<Object> response = loanController.createLoan(loanDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loan, response.getBody());
    }

    @Test
    public void testCreateLoanUserNotFound() {
        Long userId = 1L;
        Long typeLoanId = 1L;
        LoanDto loanDto = new LoanDto();
        loanDto.setUserId(userId);
        loanDto.setTypeLoanId(typeLoanId);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = loanController.createLoan(loanDto);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals("User not found", error.getMessage());
    }

    @Test
    public void testCreateLoanTypeLoanNotFound() {
        Long userId = 1L;
        Long typeLoanId = 1L;
        LoanDto loanDto = new LoanDto();
        loanDto.setUserId(userId);
        loanDto.setTypeLoanId(typeLoanId);

        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        when(typeLoanRepository.findById(typeLoanId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = loanController.createLoan(loanDto);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals("Type loan not found", error.getMessage());
    }
    @Test
    public void testUpdateLoan() {
        Long id = 1L;
        Long userId = 1L;
        Long typeLoanId = 1L;
        LoanDto loanDto = new LoanDto();
        loanDto.setUserId(userId);
        loanDto.setTypeLoanId(typeLoanId);

        Loan existingLoan = new Loan();
        existingLoan.setId(id);
        when(loanRepository.findById(id)).thenReturn(Optional.of(existingLoan));

        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        TypeLoan typeLoan = new TypeLoan();
        typeLoan.setId(typeLoanId);
        when(typeLoanRepository.findById(typeLoanId)).thenReturn(Optional.of(typeLoan));

        Loan loan = new Loan();
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        ResponseEntity<Object> response = loanController.updateLoan(id, loanDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loan, response.getBody());
    }


    @Test
    public void testUpdateLoanNotFound() {
        Long id = 1L;
        Long userId = 1L;
        Long typeLoanId = 1L;
        LoanDto loanDto = new LoanDto();
        loanDto.setUserId(userId);
        loanDto.setTypeLoanId(typeLoanId);

        when(loanRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = loanController.updateLoan(id, loanDto);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals("Loan not found", error.getMessage());
    }
    @Test
    public void testUpdateLoanUserNotFound() {
        Long id = 1L;
        Long userId = 1L;
        Long typeLoanId = 1L;
        LoanDto loanDto = new LoanDto();
        loanDto.setUserId(userId);
        loanDto.setTypeLoanId(typeLoanId);

        Loan existingLoan = new Loan();
        existingLoan.setId(id);
        when(loanRepository.findById(id)).thenReturn(Optional.of(existingLoan));

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = loanController.updateLoan(id, loanDto);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals("User not found", error.getMessage());
    }

    @Test
    public void testUpdateLoanTypeLoanNotFound() {
        Long id = 1L;
        Long userId = 1L;
        Long typeLoanId = 1L;
        LoanDto loanDto = new LoanDto();
        loanDto.setUserId(userId);
        loanDto.setTypeLoanId(typeLoanId);

        Loan existingLoan = new Loan();
        existingLoan.setId(id);
        when(loanRepository.findById(id)).thenReturn(Optional.of(existingLoan));

        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        when(typeLoanRepository.findById(typeLoanId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = loanController.updateLoan(id, loanDto);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals("Type loan not found", error.getMessage());
    }
    @Test
    public void testDeleteLoan() {
        Long id = 1L;

        Loan existingLoan = new Loan();
        existingLoan.setId(id);
        when(loanRepository.findById(id)).thenReturn(Optional.of(existingLoan));

        ResponseEntity<Object> response = loanController.deleteLoan(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        SuccessResponse success = (SuccessResponse) response.getBody();
        assertEquals("Delete loan successfull", success.getMessage());

        verify(loanRepository, times(1)).delete(existingLoan);
    }

    @Test
    public void testDeleteLoanNotFound() {
        Long id = 1L;

        when(loanRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = loanController.deleteLoan(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals("Loan not found", error.getMessage());
    }

    @Test
    public void testGetAllLoanByStatusAndUser() {
        Long id = 1L;
        String status = "all";

        User user = new User();
        user.setId(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        List<Loan> loans = new ArrayList<>();
        Loan loan = new Loan();
        Contract contract = new Contract();
        contract.setStatus(1);
        loan.setContract(contract);
        loans.add(loan);
        when(loanRepository.findAllByUser(user)).thenReturn(loans);

        ResponseEntity<Object> response = loanController.getAllLoanBySatusAndUser(status);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testGetAllLoanByStatusAndUserNotFound() {
        Long id = 1L;
        String status = "all";

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = loanController.getAllLoanBySatusAndUser(status);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals("Loan not found", error.getMessage());
    }
    @Test
    public void testGetAllLoanByStatusAndUserTypeLoanNotFound() {
        Long id = 1L;
        String status = "all";

        User user = new User();
        user.setId(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        List<Loan> loans = new ArrayList<>();
        when(loanRepository.findAllByUser(user)).thenReturn(loans);

        ResponseEntity<Object> response = loanController.getAllLoanBySatusAndUser(status);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals("Type loan not found", error.getMessage());
    }

//    @InjectMocks
//    private LoanController loanController1;

    @Mock
    private ContractRepository contractRepository;
    @Test
    public void testGetContractByLoan() {
        Long id = 1L;

        // Test case: contract not found
        when(contractRepository.findContractByLoanId(id)).thenReturn(Optional.empty());
        ResponseEntity<Object> response = loanController.getContractByLoan(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals(404, error.getStatusCode());
        assertEquals("Contract not found", error.getMessage());

        // Test case: contract found
        Contract contract = new Contract();
        when(contractRepository.findContractByLoanId(id)).thenReturn(Optional.of(contract));
        response = loanController.getContractByLoan(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Optional<Contract> optionalContract = (Optional<Contract>) response.getBody();
        assertTrue(optionalContract.isPresent());
        assertEquals(contract, optionalContract.get());
    }

    @Test
    public void testCalculateInterest() {
        Long id = 1L;

        // Test case: loan not found
        when(loanRepository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity<Object> response = loanController.calculateInterest(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals(404, error.getStatusCode());
        assertEquals("Loan not found", error.getMessage());

        // Test case: contract not found
        Loan loan = new Loan();
        when(loanRepository.findById(id)).thenReturn(Optional.of(loan));
        when(contractRepository.findContractByLoanId(id)).thenReturn(Optional.empty());
        response = loanController.calculateInterest(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        error = (ErrorResponse) response.getBody();
        assertEquals(404, error.getStatusCode());
        assertEquals("Contract not found", error.getMessage());

        // Test case: calculate interest
        Contract contract = new Contract();
        contract.setPriceLoan(1000.0);
        contract.setTimeLoan(10);
        contract.setInterestLoan(5.0);
        contract.setPenaltyInterestRate(10.0);
        when(contractRepository.findContractByLoanId(id)).thenReturn(Optional.of(contract));
        loan.setPriceRemaining(500.0);
        loan.setLatePayment(5.0);
        loan.setAmountPaid(100.0);
        response = loanController.calculateInterest(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Double> paymentInfo = (Map<String, Double>) response.getBody();
        assertEquals(2.5, paymentInfo.get("paymentMin")); // Update expected value
        assertEquals(205.0, paymentInfo.get("paymentAll"));
    }



    @Test
    public void testClosingStatementLoanNotFound() {
        Long id = 1L;
        when(loanRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = loanController.closingStatement(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals(404, error.getStatusCode());
        assertEquals("Loan not found", error.getMessage());
    }

    @Test
    public void testClosingStatementContractNotFound() {
        Long id = 1L;
        Loan loan = new Loan();
        when(loanRepository.findById(id)).thenReturn(Optional.of(loan));
        when(contractRepository.findContractByLoanId(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = loanController.closingStatement(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals(404, error.getStatusCode());
        assertEquals("Contract not found", error.getMessage());
    }

    @Test
    public void testClosingStatementSuccess() {
        Long id = 1L;
        Loan existingLoan = new Loan();
        existingLoan.setId(id);
        existingLoan.setCode("code");
        existingLoan.setPriceRemaining(1000.0);
        existingLoan.setLatePayment(0.0);
        existingLoan.setAmountPaid(0.0);
        existingLoan.setClosingStatement(LocalDateTime.now());
        existingLoan.setDueDate(LocalDateTime.now().plusMonths(1));
        existingLoan.setUser(new User());
        existingLoan.setTypeLoan(new TypeLoan());

        when(loanRepository.findById(id)).thenReturn(Optional.of(existingLoan));

        Contract contract = new Contract();
        contract.setPriceLoan(1000.0);
        contract.setTimeLoan(10);
        contract.setInterestLoan(10.0);
        contract.setPenaltyInterestRate(10.0);

        when(contractRepository.findContractByLoanId(id)).thenReturn(Optional.of(contract));

        Loan savedLoan = new Loan();
        savedLoan.setId(id);

        when(loanRepository.save(any())).thenReturn(savedLoan);

        ResponseEntity<Object> response = loanController.closingStatement(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedLoan, response.getBody());
    }


    @BeforeEach
    public void initClosingStatementTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPaymentLoanNotFound() {
        Long id = 1L;
        PaymentDto paymentDto = new PaymentDto();
        when(loanRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = loanController.payment(id, paymentDto);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals(404, error.getStatusCode());
        assertEquals("Loan not found", error.getMessage());
    }

    @Test
    public void testPaymentContractNotFound() {
        Long id = 1L;
        PaymentDto paymentDto = new PaymentDto();
        Loan loan = new Loan();
        when(loanRepository.findById(id)).thenReturn(Optional.of(loan));
        when(contractRepository.findContractByLoanId(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = loanController.payment(id, paymentDto);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals(404, error.getStatusCode());
        assertEquals("Contract not found", error.getMessage());
    }

    @Test
    public void testPaymentUserNotFound() {
        Long id = 1L;
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPrice(100.0); // Set a value for the price field
        Loan loan = new Loan();
        loan.setAmountPaid(0.0);
        when(loanRepository.findById(id)).thenReturn(Optional.of(loan));
        Contract contract = new Contract();
        when(contractRepository.findContractByLoanId(id)).thenReturn(Optional.of(contract));
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = loanController.payment(id, paymentDto);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals(404, error.getStatusCode());
        assertEquals("User not found", error.getMessage());
    }

    @Test
    public void testPaymentSuccess() {
        Long id = 1L;

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPrice(100.0);
        Loan existingLoan = new Loan();
        existingLoan.setId(id);
        existingLoan.setCode("code");
        existingLoan.setPriceRemaining(1000.0);
        existingLoan.setLatePayment(0.0);
        existingLoan.setAmountPaid(0.0);
        existingLoan.setClosingStatement(LocalDateTime.now());
        existingLoan.setDueDate(LocalDateTime.now().plusMonths(1));
        existingLoan.setUser(new User());
        existingLoan.setTypeLoan(new TypeLoan());

        when(loanRepository.findById(id)).thenReturn(Optional.of(existingLoan));

        Contract contract = new Contract();

        when(contractRepository.findContractByLoanId(id)).thenReturn(Optional.of(contract));

        Loan savedLoan = new Loan();
        savedLoan.setId(id);

        when(loanRepository.save(any())).thenReturn(savedLoan);

        User existingUser = new User();
        existingUser.setId(id);
        existingUser.setAccount("account");
        existingUser.setPassword("password");
        existingUser.setFullname("fullname");

// Convert LocalDateTime to String
        existingUser.setBirthday(LocalDateTime.now().toString());

        existingUser.setGender("gender");
        existingUser.setPhone("phone");
        existingUser.setEmail("email");
        existingUser.setBankAccount("bankAccount");
        existingUser.setPrice(1000.0);

        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));

        when(userRepository.save(any())).thenReturn(existingUser);

        ResponseEntity<Object> response = loanController.payment(id, paymentDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedLoan, response.getBody());
    }



}
