package business.banking.dgnravepay.auth.dto;



import lombok.Data;

@Data
public class SendOtpRequestDto {
    private String phoneNumber;
    private String deviceFingerprint;
}
