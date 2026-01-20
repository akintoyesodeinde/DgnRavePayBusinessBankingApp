package business.banking.dgnravepay.wallet.service;


import org.springframework.stereotype.Service;

@Service
public class AccountNameService {

    public String useLegalName(String legalName) {
        return legalName;
    }

    public String customize(String legalName, String custom) {
        return legalName + " - " + custom;
    }
}
