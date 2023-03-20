
package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommitmentContentDto {
    @NotNull
    private String des;

    @NotNull
    private Long typeLoanId;
}
