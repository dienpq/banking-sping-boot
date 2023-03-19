package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BankDto {
    @NotNull
    private String name;

    @NotNull
    private String hotline;

    @NotNull
    private String email;

    private String des;
}
