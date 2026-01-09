package business.banking.dgnravepay.auth.exception;

import business.banking.dgnravepay.auth.dto.ApiErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 1. Catch API Client Errors (Dojah 4xx errors)
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpClientError(HttpClientErrorException ex) {
        // This log will show up in your DigitalOcean Logs
        log.error("Dojah API External Error: Status Code: {}, Response Body: {}",
                ex.getStatusCode(), ex.getResponseBodyAsString());

        if (ex.getStatusCode() == HttpStatus.FAILED_DEPENDENCY) {
            ApiErrorResponse error = new ApiErrorResponse(
                    424,
                    "SMS Provider Error: Insufficient Balance or Route Issue. Please contact support. Dojah returned: " + ex.getResponseBodyAsString(),
                    System.currentTimeMillis()
            );
            return new ResponseEntity<>(error, HttpStatus.FAILED_DEPENDENCY);
        }

        ApiErrorResponse error = new ApiErrorResponse(
                ex.getStatusCode().value(),
                "External Service Error: " + ex.getStatusText(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, ex.getStatusCode());
    }

    // 2. Catch Business Logic Errors (Custom RuntimeExceptions)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(RuntimeException ex) {
        log.warn("Business Logic Error: {}", ex.getMessage());
        ApiErrorResponse error = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 3. Global Fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralError(Exception ex) {
        log.error("Unexpected System Error: ", ex);
        ApiErrorResponse error = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An internal server error occurred. Please check logs.",
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