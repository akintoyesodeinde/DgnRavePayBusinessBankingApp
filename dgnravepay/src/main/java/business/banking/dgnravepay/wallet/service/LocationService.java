package business.banking.dgnravepay.wallet.service;


import business.banking.dgnravepay.wallet.client.CscApiClient;
import business.banking.dgnravepay.wallet.entity.CityRecord;
import business.banking.dgnravepay.wallet.entity.StateRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final CscApiClient client;

    public List<StateRecord> nigeriaStates() {
        return client.getNigeriaStates();
    }

    public List<CityRecord> nigeriaCities(String stateIso2) {
        return client.getNigeriaCities(stateIso2);
    }
}
