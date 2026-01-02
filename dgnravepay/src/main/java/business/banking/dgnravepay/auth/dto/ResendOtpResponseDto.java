package business.banking.dgnravepay.auth.dto;



import lombok.*;

@Data
@AllArgsConstructor
public class ResendOtpResponseDto {
    private String referenceId;
    private String status;
    private String destination;
}
