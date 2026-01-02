package business.banking.dgnravepay.auth.client;

import java.util.List; // Add this import
import org.springframework.stereotype.Component;
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




    public String sendOtp(String phoneNumber) {
        Map<String, Object> body = Map.of(
                "sender_id", "DOJAH",
                "destination", phoneNumber,
                "channel", "sms",
                "length", 6,
                "expiry", 10,
                "priority", true
        );

        Map response = restClient.post()
                .uri("/otp")
                .body(body)
                .retrieve()
                .body(Map.class);

        // FIX: Dojah returns "entity" as a List (ArrayList), not a Map
        List<Map<String, Object>> entityList = (List<Map<String, Object>>) response.get("entity");

        // Get the first item from the list
        Map<String, Object> firstEntity = entityList.get(0);

        return firstEntity.get("reference_id").toString();
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
