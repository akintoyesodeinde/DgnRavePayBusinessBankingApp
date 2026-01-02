package business.banking.dgnravepay.auth.dto;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidateOtpResponseDto {
    private boolean success;
    private String status;
}
