package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
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
