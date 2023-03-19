package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {
    @NotNull
    private String account;

    @NotNull
    private String password;
}
