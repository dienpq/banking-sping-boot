package banking.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import banking.common.RandomCodeGenerator;
import banking.dto.ContractDto;
import banking.dto.LoanDto;
import banking.dto.UpdateStatusContractDto;
import banking.entities.Contract;
import banking.entities.Loan;
import banking.reponsitories.ContractRepository;
import banking.reponsitories.LoanRepository;
import banking.response.ErrorResponse;
import banking.response.SuccessResponse;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
//@WebMvcTest(controllers = ContractController.class)
@ExtendWith(MockitoExtension.class)
public class ContractControllerTest {
    @InjectMocks
    private ContractController contractController;

    @Mock
    private ContractRepository contractRepository;


    @Mock
    private LoanRepository loanRepository;

    @Test
    public void testGetContract_whenContractExists() {
        // Given
        Long id = 1L;
        Contract contract = new Contract();
        contract.setId(id);
        when(contractRepository.findById(id)).thenReturn(Optional.of(contract));

        // When
        ResponseEntity<Object> response = contractController.getContract(id);

//        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(contract, response.getBody());

    }
    @Test
    public void testCreateContract() {
        ContractDto contractDto = new ContractDto();
        contractDto.setLoanId(1L);
        contractDto.setLoanPurpose("test purpose");
        contractDto.setPriceLoan(1000.0);
        contractDto.setTimeLoan(12);


        Loan loan = new Loan();
        loan.setId(1L);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        Contract contract = new Contract();
        contract.setId(contractDto.getLoanId());
        contract.setLoanPurpose(contractDto.getLoanPurpose());
        contract.setPriceLoan(contractDto.getPriceLoan());
        contract.setTimeLoan(contractDto.getTimeLoan());

        contract.setLoan(loan);

//        when(contractRepository.save(contract)).thenReturn(contract);
        when(contractRepository.save(any(Contract.class))).thenReturn(contract);

        ResponseEntity<Object> response = contractController.createContract(contractDto);
        assertEquals(ResponseEntity.ok(contract), response);
    }

    @Test
    void testCreateContract2() {
        ContractDto contractDto = new ContractDto();
        contractDto.setLoanId(1L);
        contractDto.setLoanPurpose("test purpose");
        contractDto.setPriceLoan(1000.0);
        contractDto.setTimeLoan(12);


        Loan loan = new Loan();
        loan.setId(1L);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        Contract contract = new Contract();
        contract.setId(contractDto.getLoanId());
        contract.setLoanPurpose(contractDto.getLoanPurpose());
        contract.setPriceLoan(contractDto.getPriceLoan());
        contract.setTimeLoan(contractDto.getTimeLoan());

        contract.setLoan(loan);
        when(contractRepository.save(any(Contract.class))).thenReturn(contract);
        // when(contractRepository.save(contract)).thenReturn(contract);

        ResponseEntity<Object> response = contractController.createContract(contractDto);
        assertEquals(ResponseEntity.ok(contract), response);
    }

    @Test
    void testCreateContract_loanNotFound() {
        ContractDto contractDto = new ContractDto();
        contractDto.setLoanId(1L);

        when(loanRepository.findById(1L)).thenReturn(Optional.empty());

        ErrorResponse error = new ErrorResponse(404, "Loan not found");
        ResponseEntity<Object> expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        ResponseEntity<Object> response = contractController.createContract(contractDto);
        assertEquals(expectedResponse, response);
    }
    @Test
    void testUpdateContract() {
        Long id = 1L;
        ContractDto contractDto = new ContractDto();
        contractDto.setLoanId(1L);
        contractDto.setLoanPurpose("test purpose");
        contractDto.setPriceLoan(1000.0);
        contractDto.setTimeLoan(12);


        Contract existingContract = new Contract();
        existingContract.setId(id);

        when(contractRepository.findById(id)).thenReturn(Optional.of(existingContract));

        Loan loan = new Loan();
        loan.setId(1L);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        Contract updatedContract = new Contract();
        updatedContract.setId(id);
        updatedContract.setLoanPurpose(contractDto.getLoanPurpose());
        updatedContract.setPriceLoan(contractDto.getPriceLoan());
        updatedContract.setTimeLoan(contractDto.getTimeLoan());

        updatedContract.setLoan(loan);

        // when(contractRepository.save(updatedContract)).thenReturn(updatedContract);
        when(contractRepository.save(any(Contract.class))).thenReturn(updatedContract);

        ResponseEntity<Object> response = contractController.updateContract(id, contractDto);
        assertEquals(ResponseEntity.ok(updatedContract), response);
    }

    @Test
    void testUpdateContract_contractNotFound() {
        Long id = 1L;
        ContractDto contractDto = new ContractDto();

        when(contractRepository.findById(id)).thenReturn(Optional.empty());

        ErrorResponse error = new ErrorResponse(404, "Contract not found");
        ResponseEntity<Object> expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        ResponseEntity<Object> response = contractController.updateContract(id, contractDto);
        assertEquals(expectedResponse, response);
    }

    @Test
    void testUpdateContract_loanNotFound() {
        Long id = 1L;
        ContractDto contractDto = new ContractDto();
        contractDto.setLoanId(1L);

        Contract existingContract = new Contract();
        existingContract.setId(id);

        when(contractRepository.findById(id)).thenReturn(Optional.of(existingContract));
        when(loanRepository.findById(1L)).thenReturn(Optional.empty());

        ErrorResponse error = new ErrorResponse(404, "Loan not found");
        ResponseEntity<Object> expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        ResponseEntity<Object> response = contractController.updateContract(id, contractDto);
        assertEquals(expectedResponse, response);
    }
    @Test
    void testUpdateStatusContract() {
        Long id = 1L;
        UpdateStatusContractDto updateStatusContractDto = new UpdateStatusContractDto();
        updateStatusContractDto.setStatus(1);

        Contract existingContract = new Contract();
        existingContract.setId(id);

        when(contractRepository.findById(id)).thenReturn(Optional.of(existingContract));

        Contract updatedContract = new Contract();
        updatedContract.setId(id);
        updatedContract.setStatus(updateStatusContractDto.getStatus());

        when(contractRepository.save(updatedContract)).thenReturn(updatedContract);

        SuccessResponse success = new SuccessResponse(200, "Update status contract successfull");
        ResponseEntity<Object> expectedResponse = ResponseEntity.status(HttpStatus.OK).body(success);

        ResponseEntity<Object> response = contractController.updateStatusContract(id, updateStatusContractDto);
        assertEquals(expectedResponse, response);
    }
    @Test
    void testUpdateStatusContract_contractNotFound() {
        Long id = 1L;
        UpdateStatusContractDto updateStatusContractDto = new UpdateStatusContractDto();

        when(contractRepository.findById(id)).thenReturn(Optional.empty());

        ErrorResponse error = new ErrorResponse(404, "Contract not found");
        ResponseEntity<Object> expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        ResponseEntity<Object> response = contractController.updateStatusContract(id, updateStatusContractDto);
        assertEquals(expectedResponse, response);
    }
    @Test
    void testDeleteContract() {
        Long id = 1L;

        Contract existingContract = new Contract();
        existingContract.setId(id);

        when(contractRepository.findById(id)).thenReturn(Optional.of(existingContract));

        SuccessResponse success = new SuccessResponse(200, "Delete contract successfull");
        ResponseEntity<Object> expectedResponse = ResponseEntity.status(HttpStatus.OK).body(success);

        ResponseEntity<Object> response = contractController.deleteContract(id);
        assertEquals(expectedResponse, response);
    }
    @Test
    void testDeleteContract_contractNotFound() {
        Long id = 1L;

        when(contractRepository.findById(id)).thenReturn(Optional.empty());

        ErrorResponse error = new ErrorResponse(404, "Contract not found");
        ResponseEntity<Object> expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        ResponseEntity<Object> response = contractController.deleteContract(id);
        assertEquals(expectedResponse, response);
    }
    @Test
    void testGetStatusContract() {
        Long id = 1L;

        Contract existingContract = new Contract();
        existingContract.setId(id);

        when(contractRepository.findById(id)).thenReturn(Optional.of(existingContract));
        ResponseEntity<Object> expectedResponse = ResponseEntity.ok(Optional.of(existingContract));
//        ResponseEntity<Object> expectedResponse = ResponseEntity.ok(existingContract);

        ResponseEntity<Object> response = contractController.getStatusContract(id);
        assertEquals(expectedResponse, response);
    }
    @Test
    void testGetStatusContract_contractNotFound() {
        Long id = 1L;

        when(contractRepository.findById(id)).thenReturn(Optional.empty());

        ErrorResponse error = new ErrorResponse(404, "Contract not found");
        ResponseEntity<Object> expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        ResponseEntity<Object> response = contractController.getStatusContract(id);
        assertEquals(expectedResponse, response);
    }



//    @Test
//    public void testCreateContract() {
//        // Tạo đối tượng ContractDto giả định
//        ContractDto contractDto = new ContractDto();
//        contractDto.setLoanId(1L);
//        contractDto.setGender("male");
//
//        // Tạo đối tượng Mock của ContractRepository
//        ContractRepository contractRepositoryMock = mock(ContractRepository.class);
//        Contract savedContract = new Contract();
//        savedContract.setId(1L);
//        savedContract.setGender("male");
//
//        when(contractRepositoryMock.save(any(Contract.class))).thenReturn(savedContract);
//
//        // Tạo đối tượng của controller và sử dụng phương thức createContract() để tạo một Contract mới
//        ContractController contractController = new ContractController();
//        //contractController.setContractRepository(contractRepositoryMock);
//        ResponseEntity<Object> responseEntity = contractController.createContract(contractDto);
//
//        // Kiểm tra kết quả trả về từ phương thức createContract()
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertTrue(responseEntity.getBody() instanceof Contract);
//        Contract createdContract = (Contract) responseEntity.getBody();
//        assertEquals(1L, createdContract.getId());
//        assertEquals("male", createdContract.getGender());
//
//        // Kiểm tra xem phương thức save() của repository đã được gọi và trả về Contract mong muốn chưa
//        verify(contractRepositoryMock, times(1)).save(any(Contract.class));
//    }



}
