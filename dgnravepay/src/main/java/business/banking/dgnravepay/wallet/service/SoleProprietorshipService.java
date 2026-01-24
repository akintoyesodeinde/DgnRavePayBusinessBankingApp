package business.banking.dgnravepay.wallet.service;


import business.banking.dgnravepay.auth.dto.SendOtpResponseDto;
import business.banking.dgnravepay.auth.entity.OtpRequestEntity;
import business.banking.dgnravepay.wallet.client.WalletUpgradeClient;
import business.banking.dgnravepay.wallet.dto.*;
import business.banking.dgnravepay.wallet.entity.SoleProprietorship;
import business.banking.dgnravepay.wallet.entity.UserProprietor;
import business.banking.dgnravepay.wallet.entity.UserWalletUpgrade;
import business.banking.dgnravepay.wallet.enums.BusinessRegistrationType;
import business.banking.dgnravepay.wallet.repository.SoleProprietorshipRepository;
import business.banking.dgnravepay.wallet.repository.UserProprietorRepository;
import business.banking.dgnravepay.wallet.repository.UserWalletUpgradeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import business.banking.dgnravepay.wallet.entity.DocumentUploadAudit;
import business.banking.dgnravepay.wallet.repository.DocumentUploadAuditRepository;
//import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class SoleProprietorshipService {
    private final SoleProprietorshipRepository repository;
    private final UserProprietorRepository userRepo;
    private final BnAccountNameService accountNameService;
    private final UserProprietorRepository userProprietorRepository;
    private final WalletUpgradeClient walletUpgradeClient;
    private final UserWalletUpgradeRepository userWalletUpgradeRepository;
    private final DocumentUploadAuditRepository auditRepository;

    /* =========================
       CAC VERIFICATION PAGE
       ========================= */
    public SoleProprietorship create(
            SoleProprietorshipRequestDto dto,
            MultipartFile certApplication,
            MultipartFile certBusinessName) throws IOException {

        UserProprietor proprietor = userRepo.findById(dto.getUserProprietorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user proprietor"));

        SoleProprietorship entity = SoleProprietorship.builder()
                .userProprietor(proprietor)
                .legalName(dto.getLegalName())
                .registrationNumberBn(dto.getRegistrationNumberBn())
                .businessRegistrationType(BusinessRegistrationType.SOLE_PROPRIETORSHIP_BN)
                .certificateApplicationForm(
                        certApplication != null ? certApplication.getBytes() : null)
                .certificateOfBusinessName(
                        certBusinessName != null ? certBusinessName.getBytes() : null)
                .build();

        return repository.save(entity);
    }

    /* =========================
       ACCOUNT NAME PAGE
       ========================= */
    public void setAccountName(SpAccountNameRequestDto dto) {

        SoleProprietorship entity = repository
                .findByUserProprietorId(dto.getUserProprietorId())
                .orElseThrow(() -> new IllegalArgumentException("BN record not found"));

        String accountName = Boolean.TRUE.equals(dto.getUseLegalName())
                ? accountNameService.legal(entity.getLegalName())
                : accountNameService.custom(entity.getLegalName(), dto.getCustomSuffix());

        entity.setBusinessAccountName(accountName);
        repository.save(entity);
    }




    /* =========================
       CONFIRM DETAILS PAGE
       ========================= */
    public SoleProprietorshipConfirmDto confirm(Long userProprietorId) {

        SoleProprietorship sp = repository.findByUserProprietorId(userProprietorId)
                .orElseThrow(() -> new IllegalArgumentException("BN record not found"));


        // 🔹 Return confirmation UI DTO
        return new SoleProprietorshipConfirmDto(
                sp.getLegalName(),
                sp.getRegistrationNumberBn(),
                sp.getBusinessAccountName(),
                sp.getCertificateApplicationForm() != null,
                sp.getCertificateOfBusinessName() != null
        );
    }






    /* =========================
       SUBMIT CONFIRMED DETAILS PAGE
       ========================= */
    @Transactional
    public WalletUpgradeResponseDto submitConfirmedDetails(SpSubmitConfirmedDetailsDto dto) {
try{
        SoleProprietorship sp = repository.findByUserProprietorId(dto.getUserProprietorId())
                .orElseThrow(() -> new IllegalArgumentException("BN record not found"));

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
                sp.getLegalName(),
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
                .referenceId(response.getReferenceId())
                .build();

        userWalletUpgradeRepository.save(upgrade);

        return response;
        }catch (HttpClientErrorException e) {
            // Let the GlobalExceptionHandler handle these specific HTTP errors
            throw e;
        } catch (Exception e) {
            // General business logic or parsing errors
            throw new RuntimeException("Internal error processing Wallet Upgrade: " + e.getMessage());
        }
    }




    /* =========================
       DOCUMENT DOWNLOADS
       ========================= */
    public byte[] downloadCertificateApplication(Long userProprietorId) {
        return repository.findByUserProprietorId(userProprietorId)
                .map(SoleProprietorship::getCertificateApplicationForm)
                .orElseThrow();
    }

    public byte[] downloadCertificateBusinessName(Long userProprietorId) {
        return repository.findByUserProprietorId(userProprietorId)
                .map(SoleProprietorship::getCertificateOfBusinessName)
                .orElseThrow();
    }

    /* =========================
       DOCUMENT REPLACEMENT
       ========================= */
    @Transactional
    public void replaceCertificateApplication(Long userProprietorId, MultipartFile file) throws IOException {

        // 1. Update Current Version
        SoleProprietorship entity = repository.findByUserProprietorId(userProprietorId)
                .orElseThrow(() -> new IllegalArgumentException("Record not found"));


        entity.setCertificateApplicationForm(file.getBytes());
        repository.save(entity);


    }





    public void replaceCertificateBusinessName(
            Long userProprietorId,
            MultipartFile file) throws IOException {

        SoleProprietorship entity = repository.findByUserProprietorId(userProprietorId)
                .orElseThrow(() -> new IllegalArgumentException("Record not found"));


        entity.setCertificateOfBusinessName(file.getBytes());

        repository.save(entity);



    }




}
