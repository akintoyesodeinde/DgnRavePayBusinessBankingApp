package business.banking.dgnravepay.wallet.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CityRecord(
        int id,
        String name
) {}
