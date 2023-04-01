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
    private Double priceRemaining;

    private String des;

    @NotNull
    private Long typeLoanId;

    @NotNull
    private Long userId;
}
