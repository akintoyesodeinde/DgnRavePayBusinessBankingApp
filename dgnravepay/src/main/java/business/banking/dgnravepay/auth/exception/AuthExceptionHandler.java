package business.banking.dgnravepay.auth.exception;

import business.banking.dgnravepay.auth.dto.ApiErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice(basePackages = "business.banking.dgnravepay.auth")
public class AuthExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(AuthExceptionHandler.class);

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpClientError(HttpClientErrorException ex) {
        String responseBody = ex.getResponseBodyAsString();
        log.error("Dojah API External Error: Status Code: {}, Response Body: {}", ex.getStatusCode(), responseBody);

        String customMessage;

        // Handle MTN Network (424 Failed Dependency)
        if (ex.getStatusCode() == HttpStatus.FAILED_DEPENDENCY) {
            customMessage = "MTN Network/Route Error (424): Insufficient balance or provider rejection.";
        }
        // Handle Airtel/Other Networks (400 Bad Request or others)
        else {
            customMessage = "External Service Error (" + ex.getStatusCode().value() + "): " + ex.getStatusText();
        }

        // Append raw details if Dojah provided them
        if (responseBody != null && !responseBody.isBlank()) {
            customMessage += " - Provider Details: " + responseBody;
        } else {
            customMessage += " - Provider returned no additional details.";
        }

        ApiErrorResponse error = new ApiErrorResponse(
                ex.getStatusCode().value(),
                customMessage,
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, ex.getStatusCode());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(RuntimeException ex) {
        log.warn("Application Logic Error: {}", ex.getMessage());
        ApiErrorResponse error = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralError(Exception ex) {
        log.error("Unhandled Exception: ", ex);
        ApiErrorResponse error = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "A system error occurred. Please contact the administrator.",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}












//package business.banking.dgnravepay.auth.exception;
//
//
//
//import business.banking.dgnravepay.auth.dto.ApiErrorResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.client.HttpClientErrorException;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    // 1. Catch the specific 424 Failed Dependency from Dojah
//    @ExceptionHandler(HttpClientErrorException.class)
//    public ResponseEntity<ApiErrorResponse> handleHttpClientError(HttpClientErrorException ex) {
//        // Check if the error code is specifically 424
//        if (ex.getStatusCode() == HttpStatus.FAILED_DEPENDENCY) {
//            ApiErrorResponse error = new ApiErrorResponse(
//                    HttpStatus.FAILED_DEPENDENCY.value(),
//                    "SMS Provider Error: Insufficient Balance or Route Issue. Please contact support.",
//                    System.currentTimeMillis()
//            );
//            return new ResponseEntity<>(error, HttpStatus.FAILED_DEPENDENCY);
//        }
//
//        // Handle other client errors (400, 401, 404, etc.)
//        ApiErrorResponse error = new ApiErrorResponse(
//                ex.getStatusCode().value(),
//                "External Service Error: " + ex.getStatusText(),
//                System.currentTimeMillis()
//        );
//        return new ResponseEntity<>(error, ex.getStatusCode());
//    }
//
//    // 2. Catch generic RuntimeExceptions (the ones we throw manually)
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ApiErrorResponse> handleRuntimeException(RuntimeException ex) {
//        ApiErrorResponse error = new ApiErrorResponse(
//                HttpStatus.BAD_REQUEST.value(),
//                ex.getMessage(),
//                System.currentTimeMillis()
//        );
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
//
//    // 3. Global Fallback for everything else
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiErrorResponse> handleGeneralError(Exception ex) {
//        // You can log the actual error here for your own eyes: ex.printStackTrace();
//        ApiErrorResponse error = new ApiErrorResponse(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "An internal server error occurred.",
//                System.currentTimeMillis()
//        );
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}