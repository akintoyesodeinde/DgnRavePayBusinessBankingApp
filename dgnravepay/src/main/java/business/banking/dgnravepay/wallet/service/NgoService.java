package business.banking.dgnravepay.wallet.service;


import business.banking.dgnravepay.wallet.client.WalletUpgradeClient;
import business.banking.dgnravepay.wallet.dto.*;
import business.banking.dgnravepay.wallet.entity.Ngo;
import business.banking.dgnravepay.wallet.entity.NgoDirector;
import business.banking.dgnravepay.wallet.entity.UserProprietor;
import business.banking.dgnravepay.wallet.entity.UserWalletUpgrade;
import business.banking.dgnravepay.wallet.enums.BusinessRegistrationType;
import business.banking.dgnravepay.wallet.repository.NgoDirectorRepository;
import business.banking.dgnravepay.wallet.repository.NgoRepository;
import business.banking.dgnravepay.wallet.repository.UserProprietorRepository;
import java.io.IOException;

import business.banking.dgnravepay.wallet.repository.UserWalletUpgradeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class NgoService {

    private final NgoRepository ngoRepository;
    private final NgoDirectorRepository directorRepository;
    private final UserProprietorRepository userRepo;
    private final NgoAccountNameService accountNameService;
    private final UserProprietorRepository userProprietorRepository;
    private final WalletUpgradeClient walletUpgradeClient;
    private final UserWalletUpgradeRepository userWalletUpgradeRepository;


    /* ===== CAC VERIFICATION ===== */
    public void createNgo(
            NgoRequestDto dto,
            MultipartFile cacIt1Form) throws IOException {

        UserProprietor proprietor = userRepo.findById(dto.getUserProprietorId())
                .orElseThrow();

        Ngo ngo = Ngo.builder()
                .userProprietor(proprietor)
                .legalName(dto.getLegalName())
                .registrationNumberIt(dto.getRegistrationNumberIt())
                .businessRegistrationType(BusinessRegistrationType.NGO_IT)
                .cacIt1Form(cacIt1Form.getBytes())
                .build();

        ngoRepository.save(ngo);
    }

    /* ===== ACCOUNT NAME PAGE ===== */
    public void setAccountName(NgoAccountNameRequestDto dto) {

        Ngo ngo = ngoRepository.findByUserProprietorId(dto.getUserProprietorId())
                .orElseThrow();

        String name = Boolean.TRUE.equals(dto.getUseLegalName())
                ? accountNameService.legal(ngo.getLegalName())
                : accountNameService.custom(ngo.getLegalName(), dto.getCustomSuffix());

        ngo.setBusinessAccountName(name);
        ngoRepository.save(ngo);
    }

    /* ===== CONFIRM PAGE ===== */
    @Transactional
    public NgoConfirmDto confirm(Long userProprietorId) {

        Ngo ngo = ngoRepository.findByUserProprietorId(userProprietorId)
                .orElseThrow();



        return new NgoConfirmDto(
                ngo.getLegalName(),
                ngo.getRegistrationNumberIt(),
                ngo.getBusinessAccountName(),
                ngo.getCacIt1Form() != null
        );
    }




    /* =====SUBMIT CONFIRMED DETAILS PAGE ===== */
    @Transactional
    public WalletUpgradeResponseDto submitConfirmedDetails(NgoSubmitConfirmedDetailsDto dto) {

        Ngo ngo = ngoRepository.findByUserProprietorId(dto.getUserProprietorId())
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
                ngo.getLegalName(),
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







    /* ===== DOCUMENT ===== */
    public byte[] downloadCacIt1(Long userProprietorId) {
        return ngoRepository.findByUserProprietorId(userProprietorId)
                .map(Ngo::getCacIt1Form)
                .orElseThrow();
    }

    public void replaceCacIt1(
            Long userProprietorId,
            MultipartFile file) throws IOException {

        Ngo ngo = ngoRepository.findByUserProprietorId(userProprietorId)
                .orElseThrow(() ->
                        new IllegalArgumentException("NGO record not found"));

        ngo.setCacIt1Form(file.getBytes());
        ngoRepository.save(ngo);
    }

    /* ===== ADD DIRECTOR ===== */
    public void addDirector(
            Long userProprietorId,
            NgoDirectorRequestDto dto,
            MultipartFile id,
            MultipartFile address,
            MultipartFile signature) throws IOException {

        Ngo ngo = ngoRepository.findByUserProprietorId(userProprietorId)
                .orElseThrow();

        NgoDirector director = NgoDirector.builder()
                .ngo(ngo)
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

        directorRepository.save(director);
    }
}
