package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpiryDateDto {
    @NotNull
    private Long amountMonth;

    @NotNull
    private Float interest;

    @NotNull
    private Long typeLoanId;
}
