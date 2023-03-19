package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @NotNull
    private String fullname;

    @NotNull
    private String birthday;

    @NotNull
    private String gender;

    @NotNull
    private String phone;

    @NotNull
    private String email;

    @NotNull
    private Double price;
}
