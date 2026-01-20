package business.banking.dgnravepay.wallet.client;

import business.banking.dgnravepay.auth.service.LoginUserService;
import business.banking.dgnravepay.wallet.dto.WalletUpgradeRequest;
import business.banking.dgnravepay.wallet.dto.WalletUpgradeResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class WalletUpgradeClient {

    // FALLBACK LOGGER: If log.info still shows red/error, use this line:
     private static final Logger log = LoggerFactory.getLogger(WalletUpgradeClient.class);

    private final LoginUserService loginUserService;

    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://staging.agentauthapi.errandpay.com/api/v1")
            .defaultHeader("epKey", "ep_test_PEIFwchQhpHJvkMPjKSJbcrO")
            .defaultHeader("User-Role", "SuperAgent")
            .build();

    public WalletUpgradeResponseDto walletUpgrade(WalletUpgradeRequest request, String email) {

        String accessToken = loginUserService.getAccessTokenByEmail(email);

        Map<String, Object> body = Map.of(
                "FirstName", request.getFirstName(),
                "LastName", request.getLastName(),
                "Email", request.getEmail(),
                "PhoneNumber", request.getPhoneNumber(),
                "BVN", request.getBVN(),
                "NIN", request.getNIN(),
                "dateOfBirth", request.getDateOfBirth().toString(),
                "BusinessName", request.getBusinessName(),
                "BusinessAddress", request.getBusinessAddress()
        );

        log.info("Initiating Wallet Upgrade for email: {} to ErrandPay", email);

        // 1. Execute API Call
        Map<String, Object> responseMap = restClient.post()
                .uri("/AddThirdPartyAgent")
                // Added space after Bearer to fix potential 401/403 errors
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .body(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, resp) -> {
                    byte[] bytes = resp.getBody().readAllBytes();
                    String errorBody = new String(bytes);

                    // THIS PRINTS TO INTELLIJ TERMINAL
                    log.error("CRITICAL: ErrandPay API returned error status: {}. Body: {}",
                            resp.getStatusCode(), errorBody);

                    throw HttpClientErrorException.create(
                            resp.getStatusCode(),
                            "Wallet Upgrade API Error",
                            resp.getHeaders(),
                            bytes,
                            null
                    );
                })
                .body(new ParameterizedTypeReference<Map<String, Object>>() {});

        // 2. Safely extract "data" object
        Map<String, Object> data = safeMap(responseMap.get("data"), "data");

        // 3. Map to DTO
        return new WalletUpgradeResponseDto(
                str(data.get("firstName")),
                str(data.get("lastName")),
                str(data.get("email")),
                str(data.get("phoneNumber")),
                str(data.get("accountNumber")),
                str(data.get("bankName")),
                str(data.get("bvn")),
                str(data.get("businessName")),
                str(data.get("businessAddress")),
                str(data.get("rcNumber")),
                LocalDate.parse(data.get("dateOfBirth").toString()),
                str(data.get("tin")),
                str(data.get("nin")),
                str(data.get("serialNumber")),
                str(data.get("subPartnerCode")),
                str(data.get("terminalId")),
                str(data.get("agentCategory")),
                str(data.get("businessCategory")),
                str(responseMap.get("referenceId"))
        );
    }

    /* ================= HELPERS ================= */

    @SuppressWarnings("unchecked")
    private Map<String, Object> safeMap(Object obj, String field) {
        if (!(obj instanceof Map<?, ?> map)) {
            log.error("Parsing Error: Expected Map for field '{}' but got {}", field, obj);
            throw new RuntimeException("Missing or invalid field in API response: " + field);
        }
        return (Map<String, Object>) map;
    }

    private String str(Object v) {
        return v == null ? null : v.toString();
    }

    private Integer parseSafeInt(Object v) {
        if (v == null) return null;
        try {
            return Integer.parseInt(v.toString());
        } catch (NumberFormatException e) {
            log.warn("Could not parse value {} to Integer", v);
            return null;
        }
    }
}










//package business.banking.dgnravepay.wallet.client;
//parseSafeInt
//import business.banking.dgnravepay.auth.service.LoginUserService;
//import business.banking.dgnravepay.wallet.dto.WalletUpgradeRequest;
//import business.banking.dgnravepay.wallet.dto.WalletUpgradeResponseDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestClient;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.OffsetDateTime;
//import java.util.List;
//import java.util.Map;
//
//
//@Component
//@RequiredArgsConstructor
//public class WalletUpgradeClient {
//
//    private final LoginUserService loginUserService;
//
//    private final RestClient restClient = RestClient.builder()
//            .baseUrl("https://staging.agentauthapi.errandpay.com/api/v1")
//            .defaultHeader("epKey", "ep_test_PEIFwchQhpHJvkMPjKSJbcrO")
//            .defaultHeader("User-Role", "SuperAgent")
//            .build();
//
//    public WalletUpgradeResponseDto walletUpgrade(
//            WalletUpgradeRequest request,
//            String email
//    ) {
//
//        String accessToken = loginUserService.getAccessTokenByEmail(email);
//
//        Map<String, Object> body = Map.of(
//                "FirstName", request.getFirstName(),
//                "LastName", request.getLastName(),
//                "Email", request.getEmail(),
//                "PhoneNumber", request.getPhoneNumber(),
//                "BVN", request.getBVN(),
//                "NIN", request.getNIN(),
//                "dateOfBirth", request.getDateOfBirth().toString(), //  FIX
//                "BusinessName", request.getBusinessName(),
//                "BusinessAddress", request.getBusinessAddress()
//        );
//
//        Map<String, Object> response = restClient.post()
//                .uri("/AddThirdPartyAgent")
//                .header(HttpHeaders.AUTHORIZATION, "Bearer" + accessToken)
//                .body(body)
//                .retrieve()
//                .onStatus(HttpStatusCode::isError, (req, resp) -> {
//                    byte[] bytes = resp.getBody().readAllBytes();
//                    throw HttpClientErrorException.create(
//                            resp.getStatusCode(),
//                            "Wallet Upgrade API Error",
//                            resp.getHeaders(),
//                            bytes,
//                            null
//                    );
//                })
//                .body(new ParameterizedTypeReference<>() {});
//
//        Map<String, Object> data = safeMap(response.get("data"), "data");
//
//        return new WalletUpgradeResponseDto(
//                str(data.get("firstName")),
//                str(data.get("lastName")),
//                str(data.get("email")),
//                str(data.get("phoneNumber")),
//                str(data.get("accountNumber")),   // STRING
//                str(data.get("bankName")),
//                str(data.get("bvn")),
//                str(data.get("businessName")),
//                str(data.get("businessAddress")),
//                str(data.get("rcNumber")),
//                LocalDate.parse(data.get("dateOfBirth").toString()),
//                str(data.get("tin")),
//                str(data.get("nin")),
//                str(data.get("serialNumber")),
//                str(data.get("subPartnerCode")),
//                str(data.get("terminalId")),       //  STRING
//                str(data.get("agentCategory")),
//                str(data.get("businessCategory")),
//                str(response.get("referenceId"))
//        );
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
//    private LocalDate parseOffsetDate(Object v) {
//        if (v == null) return null;
//        return OffsetDateTime.parse(v.toString()).toLocalDate();
//    }
//}





//@Component
//public class WalletUpgradeClient {
//
//    //private final RestClient walletRestClient;
//    private final LoginUserService loginUserService;
//
//    private final RestClient restClient;
//
//    public WalletUpgradeClient(LoginUserService loginUserService) {
//        this.loginUserService = loginUserService;
//        this.restClient = RestClient.builder()
//                .baseUrl("https://staging.agentauthapi.errandpay.com/api/v1")
//                .defaultHeader("epKey", "ep_test_PEIFwchQhpHJvkMPjKSJbcrO")
//                .defaultHeader("User-Role", "SuperAgent")
//                .build();
//    }
//
//
//
//    public WalletUpgradeResponseDto walletUpgrade(
//            WalletUpgradeRequest request,
//            String email
//    ) {
//try {
//    String accessToken = loginUserService.getAccessTokenByEmail(email);
//
//    Map<String, Object> body = Map.of(
//            "FirstName", request.getFirstName(),
//            "LastName", request.getLastName(),
//            "Email", request.getEmail(),
//            "PhoneNumber", request.getPhoneNumber(),
//            "BVN", request.getBVN(),
//            "NIN", request.getNIN(),
//            "dateOfBirth", request.getDateOfBirth(),
//            "BusinessName", request.getBusinessName(),
//            "BusinessAddress", request.getBusinessAddress()
//    );
//
//
//    Map<String, Object> response = restClient.post()
//            .uri("/AddThirdPartyAgent")
//            .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
//            .body(body)
//            .retrieve()
//            .onStatus(HttpStatusCode::isError, (req, resp) -> {
//                byte[] bytes = resp.getBody().readAllBytes();
//                throw HttpClientErrorException.create(
//                        resp.getStatusCode(),
//                        "ErrandPay API Error",
//                        resp.getHeaders(),
//                        bytes,
//                        null
//                );
//            })
//            .body(new ParameterizedTypeReference<>() {
//            });
//
//
//    if (response == null || !response.containsKey("data")) {
//        throw new RuntimeException("Invalid login response from ErrandPay");
//    }
//
//
////        Map<String, Object> entity =
////                ((List<Map<String, Object>>) response.get("entity")).get(0);
//
//    Map<String, Object> data = safeMap(response.get("data"), "data");
//
//
//    return new WalletUpgradeResponseDto(
//            str(data.get("firstName")),
//            str(data.get("lastName")),
//            str(data.get("email")),
//            str(data.get("phoneNumber")),
//            toInt(data.get("accountNumber")),
//            str(data.get("bankName")),
//            str(data.get("bvn")),
//            str(data.get("businessName")),
//            str(data.get("businessAddress")),
//            str(data.get("rcNumber")),
//            parseOffsetDate(data.get("dateOfBirth")),
//            str(data.get("tin")),
//            str(data.get("nin")),
//            str(data.get("serialNumber")),
//            str(data.get("subPartnerCode")),
//            toInt(data.get("terminalId")),
//            str(data.get("agentCategory")),
//            str(data.get("businessCategory")),
//            str(response.get("referenceId"))
//    );
//
//}catch (HttpClientErrorException e) {
//                // Let the GlobalExceptionHandler handle these specific HTTP errors
//                throw e;
//            } catch (Exception e) {
//                // General business logic or parsing errors
//                throw new RuntimeException("Internal error processing Wallet Upgrade: " + e.getMessage());
//            }
//    }



    /* ================= HELPERS ================= */
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
//
//    private LocalDate parseOffsetDate(Object v) {
//        if (v == null) return null;
//        return OffsetDateTime.parse(v.toString()).toLocalDate();
//    }
//
//
//    private Integer toInt(Object o) { return o == null ? null : Integer.parseInt(o.toString()); }
//    private LocalDate toDate(Object o) { return o == null ? null : LocalDate.parse(o.toString()); }
//}
//
//
//









//@Component
//public class WalletUpgradeClient {
//
//    private final RestClient restClient;
//    private final LoginUserService loginUserService;
//
//    public WalletUpgradeClient(
//            RestClient restClient,
//            LoginUserService loginUserService
//    ) {
//        this.restClient = restClient;
//        this.loginUserService = loginUserService;
//    }
//
//    public WalletUpgradeResponseDto walletUpgrade(
//            WalletUpgradeRequest req,
//            String email
//    ) {
//
//        //  Fetch access token from login_user table
//        String accessToken = loginUserService.getAccessTokenByEmail(email);
//
//        Map<String, Object> body = Map.of(
//                "dateOfBirth", req.getDateOfBirth(),
//                "Email", req.getEmail(),
//                "FirstName", req.getFirstName(),
//                "LastName", req.getLastName(),
//                "PhoneNumber", req.getPhoneNumber(),
//                "RCNumber", req.getRCNumber(),
//                "BusinessAddress", req.getBusinessAddress(),
//                "BVN", req.getBVN(),
//                "NIN", req.getNIN(),
//                "BusinessName", req.getBusinessName()
//        );
//
//        Map<String, Object> response = restClient.post()
//                .uri("/otp")
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
//                .body(body)
//                .retrieve()
//                .onStatus(HttpStatusCode::isError, (request, resp) -> {
//                    byte[] bodyBytes = resp.getBody().readAllBytes();
//                    throw HttpClientErrorException.create(
//                            resp.getStatusCode(),
//                            "Wallet Upgrade API Error",
//                            resp.getHeaders(),
//                            bodyBytes,
//                            null
//                    );
//                })
//                .body(new ParameterizedTypeReference<>() {});
//
//        if (response == null || !response.containsKey("entity")) {
//            throw new RuntimeException("Invalid response from Wallet Upgrade API");
//        }
//
//        List<Map<String, Object>> entityList =
//                (List<Map<String, Object>>) response.get("entity");
//
//        if (entityList.isEmpty()) {
//            throw new RuntimeException("Empty entity list returned from Wallet Upgrade API");
//        }
//
//        Map<String, Object> first = entityList.get(0);
//
//        return new WalletUpgradeResponseDto(
//                String.valueOf(first.get("firstName")),
//                String.valueOf(first.get("lastName")),
//                String.valueOf(first.get("email")),
//                String.valueOf(first.get("phoneNumber")),
//
//                toInteger(first.get("accountNumber")),     // Integer
//                String.valueOf(first.get("bankName")),
//                toInteger(first.get("bvn")),               // Integer
//
//                String.valueOf(first.get("businessName")),
//                String.valueOf(first.get("businessAddress")),
//                String.valueOf(first.get("rcNumber")),
//
//                toLocalDate(first.get("dateOfBirth")),     //  LocalDate
//
//                String.valueOf(first.get("tin")),
//                toInteger(first.get("nin")),                // Integer
//
//                String.valueOf(first.get("serialNumber")),
//                String.valueOf(first.get("subPartnerCode")),
//                toInteger(first.get("terminalId")),         //  Integer
//
//                String.valueOf(first.get("agentCategory")),
//                String.valueOf(first.get("businessCategory"))
//        );
//    }
//
//    // =========================
//    // 🔧 Helper Conversion Methods
//    // =========================
//
//    private Integer toInteger(Object value) {
//        if (value == null) return null;
//        if (value instanceof Number number) {
//            return number.intValue();
//        }
//        return Integer.parseInt(value.toString());
//    }
//
//    private LocalDate toLocalDate(Object value) {
//        if (value == null) return null;
//        return LocalDate.parse(value.toString());
//    }
//}
