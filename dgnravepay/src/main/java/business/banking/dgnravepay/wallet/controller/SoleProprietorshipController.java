package business.banking.dgnravepay.wallet.controller;


import business.banking.dgnravepay.wallet.dto.*;
import business.banking.dgnravepay.wallet.entity.SoleProprietorship;
import business.banking.dgnravepay.wallet.service.SoleProprietorshipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/cac/sole-proprietorship")
@RequiredArgsConstructor
public class SoleProprietorshipController {

    private final SoleProprietorshipService service;



    /* ================= CAC VERIFICATION ================= */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> create(
            @ModelAttribute @Valid SoleProprietorshipRequestDto dto,
            @RequestParam("certApplication") MultipartFile certApplication,
            @RequestParam("certBusinessName") MultipartFile certBusinessName
    ) throws IOException {

        service.create(dto, certApplication, certBusinessName);
        return ResponseEntity.ok().build();
    }

    /* ================= ACCOUNT NAME PAGE ================= */
    @PostMapping("/account-name")
    public ResponseEntity<Void> setAccountName(
            @RequestBody SpAccountNameRequestDto dto) {

        service.setAccountName(dto);
        return ResponseEntity.ok().build();
    }

    /* ================= CONFIRM DETAILS PAGE ================= */
    @GetMapping("/confirm/{userProprietorId}")
    public ResponseEntity<SoleProprietorshipConfirmDto> confirm(
            @PathVariable Long userProprietorId) {

        return ResponseEntity.ok(service.confirm(userProprietorId));
    }



    /* =================SUBMIT CONFIRMED DETAILS PAGE ================= */
    @PostMapping("/submit-details")
    public ResponseEntity<WalletUpgradeResponseDto> submitConfirmedDetailes(
            @RequestBody SpSubmitConfirmedDetailsDto dto) {

        return ResponseEntity.ok(service.submitConfirmedDetails(dto));
    }


    /* ================= DOCUMENT DOWNLOAD ================= */
    @GetMapping("/certificate-application/{userProprietorId}")
    public ResponseEntity<byte[]> downloadApplication(
            @PathVariable Long userProprietorId) {

        byte[] file = service.downloadCertificateApplication(userProprietorId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=certificate_application.png")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length)
                .body(file);
    }


    @GetMapping("/certificate-business-name/{userProprietorId}")
    public ResponseEntity<byte[]> downloadBusinessName(
            @PathVariable Long userProprietorId) {

        byte[] file = service.downloadCertificateBusinessName(userProprietorId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=certificate_business_name.png")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length)
                .body(file);
    }



    /* ================= DOCUMENT EDIT ================= */

    /* ================= DOCUMENT EDIT ================= */
    @PutMapping(
            value = "/certificate-application/{userProprietorId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<SoleProprietorship> replaceCertificateApplication(
            @PathVariable Long userProprietorId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        SoleProprietorship updated =
                service.replaceCertificateApplication(userProprietorId, file);

        return ResponseEntity.ok(updated);
    }



    @PutMapping(value = "/certificate-business-name/{userProprietorId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SoleProprietorship> replaceBusinessName(
            @PathVariable Long userProprietorId,
            @RequestParam("file") MultipartFile file) throws IOException {

        SoleProprietorship updated = service.replaceCertificateBusinessName(userProprietorId, file);
        return ResponseEntity.ok(updated);
    }
}
