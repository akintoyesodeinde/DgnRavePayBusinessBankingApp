package business.banking.dgnravepay.wallet.controller;

import business.banking.dgnravepay.wallet.enums.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/enums")
public class EnumController {

    @GetMapping("/nationality")
    public List<Map<String, String>> getNationality() {
        return Arrays.stream(Nationality.values())
                .map(e -> Map.of(
                        "label", e.name().replace("_", " "),
                        "value", e.name()
                ))
                .toList();
    }


    @GetMapping("/business-ownership-structure")
    public List<Map<String, String>> getBusinessOwnershipStructure() {
        return Arrays.stream(BusinessOwnershipStructure.values())
                .map(e -> Map.of(
                        "label", e.name().replace("_", " "),
                        "value", e.name()
                ))
                .toList();
    }




    @GetMapping("/business-registration-type")
    public List<Map<String, String>> getBusinessRegistrationType() {
        return Arrays.stream(BusinessRegistrationType.values())
                .map(e -> Map.of(
                        "label", e.name().replace("_", " "),
                        "value", e.name()
                ))
                .toList();
    }




    @GetMapping("/document-type")
    public List<Map<String, String>> getDocumentType() {
        return Arrays.stream(DocumentType.values())
                .map(e -> Map.of(
                        "label", e.name().replace("_", " "),
                        "value", e.name()
                ))
                .toList();
    }





    @GetMapping("/ltd-liability-document-post-type")
    public List<Map<String, String>> getLimitedLiabilityDocumentPostType() {
        return Arrays.stream(LimitedLiabilityDocumentPostType.values())
                .map(e -> Map.of(
                        "label", e.name().replace("_", " "),
                        "value", e.name()
                ))
                .toList();
    }



    @GetMapping("/ltd-liability-pre-document-type")
    public List<Map<String, String>> getLimitedLiabilityPreDocumentType() {
        return Arrays.stream(LimitedLiabilityPreDocumentType.values())
                .map(e -> Map.of(
                        "label", e.name().replace("_", " "),
                        "value", e.name()
                ))
                .toList();
    }




    @GetMapping("/purpose-of-account")
    public List<Map<String, String>> getPurposeOfAccount() {
        return Arrays.stream(PurposeOfAccount.values())
                .map(e -> Map.of(
                        "label", e.name().replace("_", " "),
                        "value", e.name()
                ))
                .toList();
    }



    @GetMapping("/source-of-fund")
    public List<Map<String, String>> getSourceOfFund() {
        return Arrays.stream(SourceOfFund.values())
                .map(e -> Map.of(
                        "label", e.name().replace("_", " "),
                        "value", e.name()
                ))
                .toList();
    }




    @GetMapping("/staff-size")
    public List<Map<String, String>> getStaffSize() {
        return Arrays.stream(StaffSize.values())
                .map(e -> Map.of(
                        "label", e.name().replace("_", " "),
                        "value", e.name()
                ))
                .toList();
    }




    @GetMapping("/utility-document-type")
    public List<Map<String, String>> getUtilityDocumentType() {
        return Arrays.stream(UtilityDocumentType.values())
                .map(e -> Map.of(
                        "label", e.name().replace("_", " "),
                        "value", e.name()
                ))
                .toList();
    }


}
