package business.banking.dgnravepay.wallet.client;


import business.banking.dgnravepay.wallet.entity.CityRecord;
import business.banking.dgnravepay.wallet.entity.StateRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CscApiClient {

    private final HttpClient client;
    private final ObjectMapper mapper = new ObjectMapper();

    private static final String BASE = "https://api.countrystatecity.in/v1";

    private static final String API_KEY = "d5251bd36a0a34789b3198dee6e53216c38f99829be443cf201c5b17c2e5934a";

    private HttpRequest request(String uri) {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("X-CSCAPI-KEY", API_KEY)
                .GET()
                .build();
    }

    public List<StateRecord> getNigeriaStates() {
        return send(BASE + "/countries/NG/states", StateRecord.class);
    }

    public List<CityRecord> getNigeriaCities(String stateIso2) {
        return send(
                BASE + "/countries/NG/states/" + stateIso2 + "/cities",
                CityRecord.class
        );
    }

    private <T> List<T> send(String url, Class<T> clazz) {
        try {
            HttpResponse<String> response =
                    client.send(
                            request(url),
                            HttpResponse.BodyHandlers.ofString()
                    );

            //  PUT IT RIGHT HERE
            if (response.statusCode() != 200) {
                throw new RuntimeException(
                        "CSC API ERROR: " + response.statusCode() + " → " + response.body()
                );
            }

            return mapper.readValue(
                    response.body(),
                    mapper.getTypeFactory()
                            .constructCollectionType(List.class, clazz)
            );

        } catch (Exception e) {
            throw new RuntimeException("CSC API FAILED", e);
        }
    }

}

