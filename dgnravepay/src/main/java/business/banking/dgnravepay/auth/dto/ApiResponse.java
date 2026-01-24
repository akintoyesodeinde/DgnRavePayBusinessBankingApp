package business.banking.dgnravepay.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private LocalDateTime timestamp;
}
