package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Loan {
    @NotNull
    private String code;

    @NotNull
    private Double price;

    @NotNull
    private Double priceRemaining;

    private String des;

    private Long typeLoanId;

    @NotNull
    private Long userId;

    @NotNull
    private Long contractId;
}
