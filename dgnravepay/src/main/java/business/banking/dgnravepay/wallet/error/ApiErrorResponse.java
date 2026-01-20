package business.banking.dgnravepay.wallet.error;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Map;


@Getter
@Builder
public class ApiErrorResponse {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String developerMessage; // Added to capture raw API responses
    private Map<String, String> validationErrors;
}
