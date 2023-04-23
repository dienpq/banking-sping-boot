package banking.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
    @NotNull
    private Double priceRemaining;

    private Double latePayment;

    private Double amountPaid;

    private LocalDateTime closingStatement;

    private LocalDateTime dueDate;

    private String des;

    @NotNull
    private Long typeLoanId;

    @NotNull
    private Long userId;
}
