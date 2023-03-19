
package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommitmentContent {
    @NotNull
    private String des;

    @NotNull
    private Long typeLoanId;
}
