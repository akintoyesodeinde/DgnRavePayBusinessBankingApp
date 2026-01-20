package business.banking.dgnravepay.wallet.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LimitedPreAccountNameRequestDto {
    private Long userProprietorId;
    private Boolean useLegalName;
    private String customSuffix;
}
