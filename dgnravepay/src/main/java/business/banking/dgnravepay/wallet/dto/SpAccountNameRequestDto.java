package business.banking.dgnravepay.wallet.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SpAccountNameRequestDto {
    private Long userProprietorId;
    private Boolean useLegalName;
    private String customSuffix;
}
