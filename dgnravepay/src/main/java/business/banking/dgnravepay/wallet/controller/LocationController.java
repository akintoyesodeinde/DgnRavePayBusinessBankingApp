package business.banking.dgnravepay.wallet.controller;


import business.banking.dgnravepay.wallet.dto.CountryDto;
import business.banking.dgnravepay.wallet.entity.CityRecord;
import business.banking.dgnravepay.wallet.entity.StateRecord;
import business.banking.dgnravepay.wallet.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService service;

    @GetMapping("/states/ng")
    public List<StateRecord> states() {
        return service.nigeriaStates();
    }

    @GetMapping("/cities/ng/{stateIso2}")
    public List<CityRecord> cities(@PathVariable String stateIso2) {
        return service.nigeriaCities(stateIso2);
    }
}
