package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDto {
    @NotNull
    private String name;

    @NotNull
    private String hotline;

    @NotNull
    private String email;

    private String des;
}
