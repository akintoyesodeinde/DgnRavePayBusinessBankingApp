package business.banking.dgnravepay.wallet.entity;



public record CountryRecord(
        int id,
        String name,
        String iso2,
        String iso3,
        String phonecode,
        String capital,
        String currency,
        String nativeName,
        String emoji
) {}
