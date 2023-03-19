
package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommitmentContentDto {
    @NotNull
    private String des;

    @NotNull
    private Long typeLoanId;
}
