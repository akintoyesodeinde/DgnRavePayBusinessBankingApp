package business.banking.dgnravepay.auth.dto;


import lombok.Data;

@Data
public class PasswordLoginRequest {
    private String password;
    private String deviceFingerprint;
}