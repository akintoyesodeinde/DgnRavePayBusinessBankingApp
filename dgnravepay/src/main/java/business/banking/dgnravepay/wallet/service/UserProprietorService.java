package business.banking.dgnravepay.wallet.service;


import business.banking.dgnravepay.wallet.dto.UserProprietorRequestDto;
import business.banking.dgnravepay.wallet.entity.UserProprietor;
import business.banking.dgnravepay.wallet.enums.UtilityDocumentType;
import business.banking.dgnravepay.wallet.repository.UserProprietorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class UserProprietorService {

    private final UserProprietorRepository repository;
    private final IndustryCatalog industryCatalog;

    public UserProprietor create(
            UserProprietorRequestDto dto,
            MultipartFile logo,
            MultipartFile utilityBill) throws IOException {

        // VALIDATE SUB-CATEGORY BELONGS TO CATEGORY
        if (!industryCatalog
                .getSubCategories(dto.getIndustryCategory())
                .contains(dto.getIndustrySubCategory())) {

            throw new IllegalArgumentException(
                    "Invalid sub-category for selected industry");
        }

        UserProprietor entity = UserProprietor.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .bvn(dto.getBvn())
                .nin(dto.getNin())
                .email(dto.getEmail())
                .occupation(dto.getOccupation())
                .nationality(dto.getNationality())
                .politicallyExposedPerson(dto.getPoliticallyExposedPerson())
                .businessName(dto.getBusinessName())
                .industryCategory(dto.getIndustryCategory())
                .industrySubCategory(dto.getIndustrySubCategory())
                .staffSize(dto.getStaffSize())
                .sourceOfFund(dto.getSourceOfFund())
                .purposeOfAccount(dto.getPurposeOfAccount())
                .country("NG")
                .state(dto.getState())
                .stateIso2(dto.getStateIso2())
                .city(dto.getCity())
                .phoneNumber(dto.getPhoneNumber())
                .dateOfBirth(dto.getDateOfBirth())
                .companyLogo(logo != null ? logo.getBytes() : null)
                .utilityBill(utilityBill != null ? utilityBill.getBytes() : null)
                .utilityBillType(dto.getUtilityDocumentType())
                .build();

        return repository.save(entity);
    }
}
