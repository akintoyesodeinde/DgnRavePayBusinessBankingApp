package business.banking.dgnravepay.auth.dto;


import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class LoginRequest {
    private String email;
    private String phoneNumber;
    private String password;
    private String deviceFingerprint;
}
