package business.banking.dgnravepay.auth.client;

import business.banking.dgnravepay.auth.dto.SendOtpResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class DojahOtpClient {

    private final RestClient restClient;

    public DojahOtpClient() {
        this.restClient = RestClient.builder()
                .baseUrl("https://sandbox.dojah.io/api/v1/messaging")
                .defaultHeader("AppId", "66b8b14bbe8f00003fdff5da")
                .build();
    }

    public SendOtpResponseDto sendOtp(String phoneNumber) {
        try {
            Map<String, Object> body = Map.of(
                    "sender_id", "DOJAH",
                    "destination", phoneNumber,
                    "channel", "sms",
                    "length", 6,
                    "expiry", 10,
                    "priority", false
            );



// ... inside the sendOtp method
            Map<String, Object> response = restClient.post()
                    .uri("/otp")
                    .body(body)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (request, resp) -> {
                        byte[] bodyBytes = resp.getBody().readAllBytes();
                        throw HttpClientErrorException.create(
                                resp.getStatusCode(),
                                "Dojah API Error",
                                resp.getHeaders(),
                                bodyBytes,
                                null
                        );
                    })
                    // This tells Spring exactly what the Map keys and values are
                    .body(new ParameterizedTypeReference<Map<String, Object>>() {});

            if (response == null || !response.containsKey("entity")) {
                throw new RuntimeException("Invalid response structure from Dojah provider.");
            }

            List<Map<String, Object>> entityList = (List<Map<String, Object>>) response.get("entity");
            if (entityList == null || entityList.isEmpty()) {
                throw new RuntimeException("Dojah returned an empty entity list.");
            }

            Map<String, Object> first = entityList.get(0);

            return new SendOtpResponseDto(
                    String.valueOf(first.get("reference_id")),
                    String.valueOf(first.get("status")),
                    String.valueOf(first.get("destination"))
            );

        } catch (HttpClientErrorException e) {
            // Let the GlobalExceptionHandler handle these specific HTTP errors
            throw e;
        } catch (Exception e) {
            // General business logic or parsing errors
            throw new RuntimeException("Internal error processing OTP: " + e.getMessage());
        }
    }





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
