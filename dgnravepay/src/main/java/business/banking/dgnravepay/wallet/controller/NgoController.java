package business.banking.dgnravepay.wallet.controller;


import business.banking.dgnravepay.wallet.dto.*;
import business.banking.dgnravepay.wallet.entity.Ngo;
import business.banking.dgnravepay.wallet.service.NgoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;



@RestController
@RequestMapping("/api/cac/ngo")
@RequiredArgsConstructor
public class NgoController {

    private final NgoService service;

    /* ===== CAC VERIFICATION ===== */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createNgo(
            @ModelAttribute @Valid NgoRequestDto dto,
            @RequestParam("cacIt1Form") MultipartFile cacIt1Form) throws IOException {
        service.createNgo(dto, cacIt1Form);
        return ResponseEntity.ok().build();
    }


    /* ===== ACCOUNT NAME ===== */
    @PostMapping("/account-name")
    public ResponseEntity<Void> accountName(
            @RequestBody NgoAccountNameRequestDto dto) {

        service.setAccountName(dto);
        return ResponseEntity.ok().build();
    }

    /* ===== CONFIRM ===== */
    @GetMapping("/confirm/{userProprietorId}")
    public ResponseEntity<NgoConfirmDto> confirm(
            @PathVariable Long userProprietorId) {

        return ResponseEntity.ok(service.confirm(userProprietorId));
    }


    /* =================SUBMIT CONFIRMED DETAILS PAGE ================= */
    @PostMapping("/submit-confirmed-details")
    public ResponseEntity<WalletUpgradeResponseDto> submitConfirmedDetailes(
            @RequestBody NgoSubmitConfirmedDetailsDto dto) {

        return ResponseEntity.ok(service.submitConfirmedDetails(dto));
    }





    /* ===== DOWNLOAD DOCUMENT ===== */
    @GetMapping("/cac-it1/{userProprietorId}")
    public ResponseEntity<byte[]> download(
            @PathVariable Long userProprietorId) {

        byte[] file = service.downloadCacIt1(userProprietorId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=cacIt1Form.png")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length)
                .body(file);
    }


    @PutMapping(value = "/cac-it1/{userProprietorId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ngo> replace(
            @PathVariable Long userProprietorId,
            @RequestParam("file") MultipartFile file) throws IOException {

        Ngo replace = service.replaceCacIt1(userProprietorId, file);
        return ResponseEntity.ok(replace); // 204
    }




    /* ===== ADD DIRECTOR ===== */
    @PostMapping(value = "/director/{userProprietorId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addDirector(
            @PathVariable Long userProprietorId,
            @ModelAttribute @Valid NgoDirectorRequestDto dto,
            @RequestParam("proofOfIdentity") MultipartFile proofOfIdentity,
            @RequestParam("proofOfAddress") MultipartFile proofOfAddress,
            @RequestParam("proofOfSignature") MultipartFile proofOfSignature) throws IOException {

        service.addDirector(
                userProprietorId,
                dto,
                proofOfIdentity,
                proofOfAddress,
                proofOfSignature);

        return ResponseEntity.ok().build();
    }
}
