package business.banking.dgnravepay.wallet.service;


import org.springframework.stereotype.Service;

@Service
public class RcAccountNameService {

    public String legal(String legalName) {
        return legalName;
    }

    public String custom(String legalName, String suffix) {
        return legalName + " - " + suffix;
    }
}
