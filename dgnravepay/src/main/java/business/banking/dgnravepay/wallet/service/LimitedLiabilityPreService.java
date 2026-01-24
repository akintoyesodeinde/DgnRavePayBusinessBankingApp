package business.banking.dgnravepay.wallet.service;


import business.banking.dgnravepay.wallet.client.WalletUpgradeClient;
import business.banking.dgnravepay.wallet.dto.*;
import business.banking.dgnravepay.wallet.entity.*;
import business.banking.dgnravepay.wallet.enums.BusinessOwnershipStructure;
import business.banking.dgnravepay.wallet.enums.BusinessRegistrationType;
import business.banking.dgnravepay.wallet.enums.LimitedLiabilityPreDocumentType;
import business.banking.dgnravepay.wallet.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;



@Service
@RequiredArgsConstructor
public class LimitedLiabilityPreService {

    private final LimitedLiabilityPreRepository repository;
    private final LimitedLiabilityPreDirectorRepository directorRepository;
    private final LimitedLiabilityPrePrincipalRepository principalRepository;
    private final UserProprietorRepository userRepo;
    private final LimitedPreAccountNameService accountNameService;
    private final UserProprietorRepository userProprietorRepository;
    private final WalletUpgradeClient walletUpgradeClient;
    private final UserWalletUpgradeRepository userWalletUpgradeRepository;

    /* ===== CREATE CAC VERIFICATION ===== */
    public void create(
            LimitedLiabilityPreRequestDto dto,
            MultipartFile memo,
            MultipartFile cac3,
            MultipartFile cac2,
            MultipartFile cac7,
            MultipartFile incorporation,
            MultipartFile boardResolution) throws IOException {

        UserProprietor proprietor = userRepo.findById(dto.getUserProprietorId())
                .orElseThrow();

        LimitedLiabilityPre e = LimitedLiabilityPre.builder()
                .userProprietor(proprietor)
                .legalName(dto.getLegalName())
                .registrationNumberRc(dto.getRegistrationNumberRc())
                .taxIdentificationNumber(dto.getTaxIdentificationNumber())
                .businessRegistrationType(BusinessRegistrationType.LIMITED_LIABILITY_RC)
                .businessOwnershipStructure(BusinessOwnershipStructure.PRIVATELY_HELD_PRE_2017)
                .memorandumOfAssociation(memo.getBytes())
                .formCac3(cac3.getBytes())
                .formCac2(cac2.getBytes())
                .formCac7(cac7.getBytes())
                .certificateOfIncorporation(incorporation.getBytes())
                .boardResolution(boardResolution.getBytes())
                .build();

        repository.save(e);
    }

    /* ===== ACCOUNT NAME ===== */
    public void setAccountName(LimitedPreAccountNameRequestDto dto) {

        LimitedLiabilityPre e = repository.findByUserProprietorId(dto.getUserProprietorId())
                .orElseThrow();

        String name = Boolean.TRUE.equals(dto.getUseLegalName())
                ? accountNameService.legal(e.getLegalName())
                : accountNameService.custom(e.getLegalName(), dto.getCustomSuffix());

        e.setBusinessAccountName(name);
        repository.save(e);
    }

    /* ===== CONFIRM PAGE ===== */
    public LimitedPreConfirmDto confirm(Long userProprietorId) {

        LimitedLiabilityPre e = repository.findByUserProprietorId(userProprietorId)
                .orElseThrow();


        return new LimitedPreConfirmDto(
                e.getLegalName(),
                e.getRegistrationNumberRc(),
                e.getBusinessAccountName(),
                e.getTaxIdentificationNumber(),
                e.getMemorandumOfAssociation() != null,
                e.getFormCac3() != null,
                e.getFormCac2() != null,
                e.getFormCac7() != null,
                e.getCertificateOfIncorporation() != null,
                e.getBoardResolution() != null
        );
    }




    /* =====SUBMIT CONFIRMED DETAILS PAGE ===== */
    @Transactional
    public WalletUpgradeResponseDto submitConfirmedDetails(LimitedPreSubmitConfirmedDetailsDto dto) {

        LimitedLiabilityPre e = repository.findByUserProprietorId(dto.getUserProprietorId())
                .orElseThrow();

        UserProprietor user = userRepo.findById(dto.getUserProprietorId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 🔹 Build Wallet Upgrade Request
        WalletUpgradeRequest request = new WalletUpgradeRequest(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getBvn(),
                user.getNin(),
                user.getDateOfBirth(),
                e.getLegalName(),
                user.getState()
        );

        // 🔹 Call External API
//        WalletUpgradeResponseDto response =
//                walletUpgradeClient.walletUpgrade(request, user.getEmail());

        WalletUpgradeResponseDto response =
                walletUpgradeClient.walletUpgrade(request, "payments@dgnravepay.com");

        // 🔹 Persist Wallet Upgrade
        UserWalletUpgrade upgrade = UserWalletUpgrade.builder()
                .firstName(response.getFirstName())
                .lastName(response.getLastName())
                .email(response.getEmail())
                .phoneNumber(response.getPhoneNumber())
                .accountNumber(response.getAccountNumber())
                .bankName(response.getBankName())
                .bvn(response.getBvn())
                .businessName(response.getBusinessName())
                .businessAddress(response.getBusinessAddress())
                .rcNumber(response.getRcNumber())
                .dateOfBirth(response.getDateOfBirth())
                .tin(response.getTin())
                .nin(response.getNin())
                .serialNumber(response.getSerialNumber())
                .subPartnerCode(response.getSubPartnerCode())
                .terminalId(response.getTerminalId())
                .agentCategory(response.getAgentCategory())
                .businessCategory(response.getBusinessCategory())
                .build();

        userWalletUpgradeRepository.save(upgrade);

        return response;
    }






    /* ===== DOWNLOAD DOCUMENT ===== */
    public byte[] download(Long userProprietorId, LimitedLiabilityPreDocumentType doc) {

        LimitedLiabilityPre e = repository.findByUserProprietorId(userProprietorId)
                .orElseThrow();

        return switch (doc) {
            case MEMO -> e.getMemorandumOfAssociation();
            case CAC3 -> e.getFormCac3();
            case CAC2 -> e.getFormCac2();
            case CAC7 -> e.getFormCac7();
            case INCORP -> e.getCertificateOfIncorporation();
            case BOARD -> e.getBoardResolution();

        };
    }


    /* ===== REPLACE DOCUMENT ===== */
    public void replaceDocument(
            Long userProprietorId,
            LimitedLiabilityPreDocumentType type,
            MultipartFile file
    ) throws IOException {

        LimitedLiabilityPre entity = repository
                .findByUserProprietorId(userProprietorId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Limited liability record not found"));

        byte[] bytes = file.getBytes();

        switch (type) {
            case MEMO -> entity.setMemorandumOfAssociation(bytes);
            case CAC3 -> entity.setFormCac3(bytes);
            case CAC2 -> entity.setFormCac2(bytes);
            case CAC7 -> entity.setFormCac7(bytes);
            case INCORP -> entity.setCertificateOfIncorporation(bytes);
            case BOARD -> entity.setBoardResolution(bytes);
        }

        repository.save(entity);
    }




    /* ===== ADD DIRECTOR ===== */
    public void addDirector(
            Long userProprietorId,
            LimitedPreDirectorRequestDto dto,
            MultipartFile id,
            MultipartFile address,
            MultipartFile signature) throws IOException {

        LimitedLiabilityPre e = repository.findByUserProprietorId(userProprietorId)
                .orElseThrow();

        LimitedLiabilityPreDirector d = LimitedLiabilityPreDirector.builder()
                .limitedLiabilityPre(e)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .percentageOwned(dto.getPercentageOwned())
                .dateOfBirth(dto.getDateOfBirth())
                .houseAddress(dto.getHouseAddress())
                .nationality(dto.getNationality())
                .documentType(dto.getDocumentType())
                .documentNumber(dto.getDocumentNumber())
                .proofOfIdentity(id.getBytes())
                .proofOfAddress(address.getBytes())
                .proofOfSignature(signature.getBytes())
                .build();

        directorRepository.save(d);
    }



    /* ===== ADD Principal Owner ===== */
    public void addPrincipal(
            Long userProprietorId,
            LimitedPrePrincipalRequestDto dto,
            MultipartFile id,
            MultipartFile address,
            MultipartFile signature) throws IOException {

        LimitedLiabilityPre e = repository.findByUserProprietorId(userProprietorId)
                .orElseThrow();

        LimitedLiabilityPrePrincipal d = LimitedLiabilityPrePrincipal.builder()
                .limitedLiabilityPre(e)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .percentageOwned(dto.getPercentageOwned())
                .dateOfBirth(dto.getDateOfBirth())
                .houseAddress(dto.getHouseAddress())
                .nationality(dto.getNationality())
                .documentType(dto.getDocumentType())
                .documentNumber(dto.getDocumentNumber())
                .proofOfIdentity(id.getBytes())
                .proofOfAddress(address.getBytes())
                .proofOfSignature(signature.getBytes())
                .build();

        principalRepository.save(d);
    }
}
