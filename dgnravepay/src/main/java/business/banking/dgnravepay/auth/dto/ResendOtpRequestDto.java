package business.banking.dgnravepay.auth.dto;

import lombok.Data;

@Data
public class ResendOtpRequestDto {
    private String phoneNumber;
    private String deviceFingerprint;
}
