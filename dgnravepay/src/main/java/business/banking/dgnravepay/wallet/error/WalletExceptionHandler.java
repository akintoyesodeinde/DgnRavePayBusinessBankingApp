package business.banking.dgnravepay.wallet.error;

import business.banking.dgnravepay.wallet.client.WalletUpgradeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;

import lombok.extern.slf4j.Slf4j;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "business.banking.dgnravepay.wallet")
public class WalletExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(WalletExceptionHandler.class);

    // 1. CAPTURE EXTERNAL API ERRORS (This will show ErrandPay's response in Postman)
    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ApiErrorResponse> handleRestClientException(RestClientResponseException ex) {
        log.error("External API Error: Status {}, Body: {}", ex.getStatusCode(), ex.getResponseBodyAsString());

        return ResponseEntity.status(ex.getStatusCode()).body(
                ApiErrorResponse.builder()
                        .timestamp(Instant.now())
                        .status(ex.getStatusCode().value())
                        .error("External Service Error")
                        .message(ex.getMessage()) // This reveals the root cause
                        .developerMessage(ex.getResponseBodyAsString()) // Send raw body to Postman
                        .build()
        );
    }

    // 2. LOG GENERIC ERRORS (This will show the stack trace in IntelliJ)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(Exception ex) {
        log.error("ROOT CAUSE: ", ex); // This prints the full stack trace in IntelliJ terminal

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiErrorResponse.builder()
                        .timestamp(Instant.now())
                        .status(500)
                        .error("Internal Server Error")
                        .message(ex.getMessage()) // No longer hides the message
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));

        return ResponseEntity.badRequest().body(
                ApiErrorResponse.builder()
                        .timestamp(Instant.now())
                        .status(400)
                        .error("Validation Error")
                        .message("Invalid request parameters")
                        .validationErrors(errors)
                        .build()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex) {

        return ResponseEntity.badRequest().body(
                ApiErrorResponse.builder()
                        .timestamp(Instant.now())
                        .status(400)
                        .error("Business Rule Violation")
                        .message(ex.getMessage())
                        .build()
        );
    }

//    @ExceptionHandler(Exception.class)
////    public ResponseEntity<ApiErrorResponse> handleGeneric(Exception ex) {
////
////        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
////                ApiErrorResponse.builder()
////                        .timestamp(Instant.now())
////                        .status(500)
////                        .error("Internal Server Error")
////                        .message("An unexpected error occurred")
////                        .build()
////        );
////    }
}
