package business.banking.dgnravepay.auth.dto;

import lombok.Data;

@Data
public class ValidateOtpRequestDto {
    private String referenceId;
    private String code;
    private String deviceFingerprint;
}
