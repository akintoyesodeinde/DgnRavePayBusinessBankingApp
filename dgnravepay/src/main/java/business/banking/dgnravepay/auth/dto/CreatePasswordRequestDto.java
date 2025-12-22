package business.banking.dgnravepay.auth.dto;


import lombok.Data;

@Data
public class CreatePasswordRequestDto {
    private String phoneNumber;
    private String password;
    private String confirmPassword;
}
