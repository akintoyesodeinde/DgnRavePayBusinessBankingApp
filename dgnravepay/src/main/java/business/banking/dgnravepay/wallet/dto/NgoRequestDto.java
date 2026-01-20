package business.banking.dgnravepay.wallet.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
public class NgoRequestDto {
    private Long userProprietorId;
    private String legalName;
    private String registrationNumberIt;
}
