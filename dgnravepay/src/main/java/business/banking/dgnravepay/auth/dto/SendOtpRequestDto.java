package business.banking.dgnravepay.auth.dto;



import lombok.Data;

@Data
public class SendOtpRequestDto {
    private String phoneNumber;
    private String deviceFingerprint;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
