package business.banking.dgnravepay.auth.client;

import business.banking.dgnravepay.auth.dto.LoginResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Map;


@Component
public class ErrandPayClient {

    private final RestClient restClient;

    public ErrandPayClient() {
        this.restClient = RestClient.builder()
                .baseUrl("https://staging.agentauthapi.errandpay.com/api/v1")
                .build();
    }

    public LoginResponseDto login(String email, String password) {
try{
        Map<String, Object> body = Map.of(
                "loginToken", email,          // THIS IS THE FIX
                "password", password
        );

        Map<String, Object> response = restClient.post()
                .uri("/login")
                .body(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, resp) -> {
                    byte[] bytes = resp.getBody().readAllBytes();
                    throw HttpClientErrorException.create(
                            resp.getStatusCode(),
                            "ErrandPay API Error",
                            resp.getHeaders(),
                            bytes,
                            null
                    );
                })
                .body(new ParameterizedTypeReference<>() {});

        if (response == null || !response.containsKey("data")) {
            throw new RuntimeException("Invalid login response from ErrandPay");
        }

        Map<String, Object> data = safeMap(response.get("data"), "data");

        return new LoginResponseDto(
                str(data.get("id")),
                str(data.get("firstName")),
                str(data.get("lastName")),
                str(data.get("userName")),
                str(data.get("email")),
                str(data.get("phoneNumber")),
                str(data.get("status")),
                str(data.get("roleCode")),
                str(data.get("referalCode")),
                str(data.get("userReferalCode")),
                str(data.get("countryCode")),

                bool(data.get("isPINSet")),
                bool(data.get("isKycCompleted")),

                str(data.get("accessToken")),
                str(data.get("tokenType")),
                parseOffsetDateTime(data.get("expiresIn")),
                str(data.get("refreshToken")),

                str(data.get("agentCode")),
                bool(data.get("isEmailConfirmed")),
                bool(data.get("isPhoneNumberConfirmed")),
                str(data.get("lga")),
                str(data.get("address")),
                str(data.get("state")),

                bool(data.get("isSecurityQuestionSet")),
                bool(data.get("isPushNotificationSet")),
                bool(data.get("isDeviceTokenValidated")),
                bool(data.get("isDefaultPassword")),
                str(data.get("tierCode")),
                bool(data.get("isKycSentToThirdParty")),
                bool(data.get("isPoolType")),
                str(data.get("category")),
                bool(data.get("activePendingLien")),
                str(data.get("bankName")),
                str(data.get("uplineName")),
                str(data.get("name")),
                str(response.get("referenceId"))
        );
} catch (HttpClientErrorException e) {
    // Let the GlobalExceptionHandler handle these specific HTTP errors
    throw e;
} catch (Exception e) {
    // General business logic or parsing errors
    throw new RuntimeException("Internal error processing Login: " + e.getMessage());
}
    }

    /* ================= HELPERS ================= */

    @SuppressWarnings("unchecked")
    private Map<String, Object> safeMap(Object obj, String field) {
        if (!(obj instanceof Map<?, ?> map)) {
            throw new RuntimeException("Missing or invalid field: " + field);
        }
        return (Map<String, Object>) map;
    }

    private String str(Object v) {
        return v == null ? null : v.toString();
    }

    private Boolean bool(Object v) {
        if (v == null) return null;
        if (v instanceof Boolean b) return b;
        return Boolean.parseBoolean(v.toString());
    }

    private LocalDateTime parseOffsetDateTime(Object v) {
        if (v == null) return null;
        return OffsetDateTime.parse(v.toString()).toLocalDateTime();
    }
}









//@Component
//public class ErrandPayClient {
//
//    private final RestClient restClient;
//
//    public ErrandPayClient() {
//        this.restClient = RestClient.builder()
//                .baseUrl("https://staging.agentauthapi.errandpay.com/api/v1")
//                .build();
//    }
//
//    public LoginResponseDto login(String loginToken, String password) {
//    try{
//        Map<String, Object> body = Map.of(
//                "loginToken", loginToken,          //  FIXED
//                "password", password
//        );
//
//        Map<String, Object> response = restClient.post()
//                .uri("/login")
//                .body(body)
//                .retrieve()
//                .onStatus(HttpStatusCode::isError, (req, resp) -> {
//                    byte[] bytes = resp.getBody().readAllBytes();
//                    throw HttpClientErrorException.create(
//                            resp.getStatusCode(),
//                            "ErrandPay API Error",
//                            resp.getHeaders(),
//                            bytes,
//                            null
//                    );
//                })
//                .body(new ParameterizedTypeReference<>() {});
//
//        if (response == null || !response.containsKey("data")) {
//            throw new RuntimeException("Invalid login response from ErrandPay");
//        }
//
//        Map<String, Object> data = safeMap(response.get("data"), "data");
//        Map<String, Object> user = safeMap(data.get("user"), "user");
//
//        return new LoginResponseDto(
//                str(user.get("id")),
//                str(user.get("firstName")),
//                str(user.get("lastName")),
//                str(user.get("userName")),
//                str(user.get("email")),
//                str(user.get("phoneNumber")),
//                str(user.get("status")),
//                str(user.get("roleCode")),
//                str(user.get("referalCode")),
//                str(user.get("userReferalCode")),
//                str(user.get("countryCode")),
//                bool(user.get("isPINSet")),
//                bool(user.get("isKycCompleted")),
//                str(data.get("accessToken")),
//                str(data.get("tokenType")),
//                parseDateTime(data.get("expiresIn")),
//                str(data.get("refreshToken")),
//                str(user.get("agentCode")),
//                bool(user.get("isEmailConfirmed")),
//                bool(user.get("isPhoneNumberConfirmed")),
//                str(user.get("lga")),
//                str(user.get("address")),
//                str(user.get("state")),
//                bool(user.get("isSecurityQuestionSet")),
//                bool(user.get("isPushNotificationSet")),
//                bool(user.get("isDeviceTokenValidated")),
//                bool(user.get("isDefaultPassword")),
//                str(user.get("tierCode")),
//                bool(user.get("isKycSentToThirdParty")),
//                bool(user.get("activePendingLien")),
//                str(user.get("bankName")),
//                str(user.get("uplineName")),
//                bool(user.get("isPoolType")),
//                str(user.get("category")),
//                str(user.get("name"))
//        );
//    } catch (HttpClientErrorException e) {
//        // Let the GlobalExceptionHandler handle these specific HTTP errors
//        throw e;
//    } catch (Exception e) {
//        // General business logic or parsing errors
//        throw new RuntimeException("Internal error processing Login: " + e.getMessage());
//    }
//    }
//
//    /* ================= HELPERS ================= */
//
//    @SuppressWarnings("unchecked")
//    private Map<String, Object> safeMap(Object obj, String field) {
//        if (!(obj instanceof Map<?, ?> map)) {
//            throw new RuntimeException("Missing or invalid field: " + field);
//        }
//        return (Map<String, Object>) map;
//    }
//
//    private String str(Object v) {
//        return v == null ? null : v.toString();
//    }
//
//    private Boolean bool(Object v) {
//        if (v == null) return null;
//        if (v instanceof Boolean b) return b;
//        return Boolean.parseBoolean(v.toString());
//    }
//
//    private LocalDateTime parseDateTime(Object v) {
//        if (v == null) return null;
//        return LocalDateTime.parse(v.toString());
//    }
//}
