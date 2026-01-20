package business.banking.dgnravepay.wallet.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;



@Data
@AllArgsConstructor
@Getter
@Setter
public class WalletUpgradeResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String accountNumber;   // ✅ STRING
    private String bankName;
    private String bvn;
    private String businessName;
    private String businessAddress;
    private String rcNumber;
    private LocalDate dateOfBirth;
    private String tin;
    private String nin;
    private String serialNumber;
    private String subPartnerCode;
    private String terminalId;      // ✅ STRING
    private String agentCategory;
    private String businessCategory;
    private String referenceId;
}
