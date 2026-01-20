package business.banking.dgnravepay.wallet.dto;




import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletUpgradeRequest {
    private String FirstName;
    private String LastName;
    private String Email;
    private String PhoneNumber;
    private String BVN;
    private String NIN;
    private LocalDate dateOfBirth;
    private String BusinessName;
    private String BusinessAddress;
    //private String RCNumber;
}








//
//@Data
//@AllArgsConstructor
//public class WalletUpgradeRequest {
//    private String FirstName;
//    private String LastName;
//    private String Email;
//    private String PhoneNumber;
//    private Integer BVN;
//    private Integer NIN;
//    private LocalDate dateOfBirth;
//    //private Integer AccountNumber;
//    private String BankName;
//    private String BusinessName;
//    private String BusinessAddress;
//    private String RCNumber;
//    //private String serialNumber;
//    //private String terminalId;
//    //private String businessCategory;
//    //private String agentCategory;
//
//}
//
//
//
