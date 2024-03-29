package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeLoanDto {
    @NotNull
    private String name;

    @NotNull
    private String invitation;

    private String des;

    @NotNull
    private Long bankId;
}
