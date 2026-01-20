package business.banking.dgnravepay.wallet.mapper;

import business.banking.dgnravepay.wallet.dto.UserProprietorResponseDto;
import business.banking.dgnravepay.wallet.entity.UserProprietor;

public final class UserProprietorMapper {

    private UserProprietorMapper() {}

    public static UserProprietorResponseDto toDto(UserProprietor entity) {
        return UserProprietorResponseDto.builder()
                .id(entity.getId())
                .lastName(entity.getLastName())
                .firstName(entity.getFirstName())
                .bvn(entity.getBvn())
                .nin(entity.getNin())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .occupation(entity.getOccupation())
                .nationality(entity.getNationality())
                .politicallyExposedPerson(entity.getPoliticallyExposedPerson())
                .businessName(entity.getBusinessName())
                .industryCategory(entity.getIndustryCategory())
                .industrySubCategory(entity.getIndustrySubCategory())
                .staffSize(entity.getStaffSize())
                .sourceOfFund(entity.getSourceOfFund())
                .purposeOfAccount(entity.getPurposeOfAccount())
                .utilityDocumentType(entity.getUtilityBillType())
                .country(entity.getCountry())
                .state(entity.getState())
                .stateIso2(entity.getStateIso2())
                .city(entity.getCity())
                .dateOfBirth(entity.getDateOfBirth())
                .build();
    }
}
