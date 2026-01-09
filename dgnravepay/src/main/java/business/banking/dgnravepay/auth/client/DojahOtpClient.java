package business.banking.dgnravepay.auth.client;

import java.util.List; // Add this import

import business.banking.dgnravepay.auth.dto.SendOtpResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class DojahOtpClient {

    private final RestClient restClient;

    public DojahOtpClient() {
        this.restClient = RestClient.builder()
                .baseUrl("https://sandbox.dojah.io/api/v1/messaging")
                .defaultHeader("AppId", "66b8b14bbe8f00003fdff5da")
                .defaultHeader("Authorization", "test_sk_1Ik0EXfSufZ24akeQKaAymyhp")
                .build();
    }




    public SendOtpResponseDto sendOtp(String phoneNumber) {
        try {
            Map<String, Object> body = Map.of(
                    "sender_id", "DgnRavePay",
                    "destination", phoneNumber,
                    "channel", "sms",
                    "length", 6,
                    "expiry", 10,
                    "priority", false
            );

            Map response = restClient.post()
                    .uri("/otp")
                    .body(body)
                    .retrieve()
                    .onStatus(status -> status.value() == 424, (request, resp) -> {
                        // This forces the 424 to be handled by our GlobalExceptionHandler
                        throw new HttpClientErrorException(HttpStatus.FAILED_DEPENDENCY, "Dojah Insufficient Balance");
                    })
                    .body(Map.class);

            if (response == null || !response.containsKey("entity")) {
                throw new RuntimeException("Invalid response from Dojah provider.");
            }

            List<Map<String, Object>> entityList = (List<Map<String, Object>>) response.get("entity");
            Map<String, Object> first = entityList.get(0);

            return new SendOtpResponseDto(
                    first.get("reference_id").toString(),
                    first.get("status").toString(),
                    first.get("destination").toString()
            );

        } catch (HttpClientErrorException e) {
            // Re-throw so the GlobalExceptionHandler catches it
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Could not process OTP request: " + e.getMessage());
        }
    }



//
//    public SendOtpResponseDto sendOtp(String phoneNumber) {
//
//        Map<String, Object> body = Map.of(
//                "sender_id", "DOJAH",
//                "destination", phoneNumber,
//                "channel", "sms",
//                "length", 6,
//                "expiry", 10,
//                "priority", true
//        );
//
//        Map response = restClient.post()
//                .uri("/otp")
//                .body(body)
//                .retrieve()
//                .body(Map.class);
//
//        List<Map<String, Object>> entityList =
//                (List<Map<String, Object>>) response.get("entity");
//
//        Map<String, Object> first = entityList.get(0);
//
//        String referenceId = first.get("reference_id").toString();
//        String status = first.get("status").toString();
//        String destination = first.get("destination").toString();
//
//        return new SendOtpResponseDto(referenceId, status, destination);
//    }
//




    public boolean validateOtp(String referenceId, String code) {
        Map response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/otp/validate")
                        .queryParam("reference_id", referenceId)
                        .queryParam("code", code)
                        .build())
                .retrieve()
                .body(Map.class);

        Map entity = (Map) response.get("entity");
        return Boolean.TRUE.equals(entity.get("valid"));
    }
}















//package business.banking.dgnravepay.auth.client;
//
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestClient;
//
//import java.util.Map;
//
//@Component
//public class DojahOtpClient {
//
//    private final RestClient restClient;
//
//    public DojahOtpClient() {
//        this.restClient = RestClient.builder()
//                .baseUrl("https://sandbox.dojah.io/api/v1/messaging")
//                .defaultHeader("AppId", "66b8b14bbe8f00003fdff5da")
//                .defaultHeader("Authorization", "test_sk_1Ik0EXfSufZ24akeQKaAymyhp")
//                .build();
//    }
//
//    public String sendOtp(String phoneNumber) {
//        Map<String, Object> body = Map.of(
//                "sender_id", "DOJAH",
//                "destination", phoneNumber,
//                "channel", "sms",
//                "length", 6,
//                "expiry", 10,
//                "priority",true
//        );
//
//        Map response = restClient.post()
//                .uri("/otp")
//                .body(body)
//                .retrieve()
//                .body(Map.class);
//
//        Map entity = (Map) response.get("entity");
//        return entity.get("reference_id").toString();
//    }
//
//    public boolean validateOtp(String referenceId, String code) {
//        Map response = restClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/otp/validate")
//                        .queryParam("reference_id", referenceId)
//                        .queryParam("code", code)
//                        .build())
//                .retrieve()
//                .body(Map.class);
//
//        Map entity = (Map) response.get("entity");
//        return Boolean.TRUE.equals(entity.get("valid"));
//    }
//}
