package business.banking.dgnravepay.wallet.controller;


import business.banking.dgnravepay.wallet.dto.*;
import business.banking.dgnravepay.wallet.entity.LimitedLiabilityPre;
import business.banking.dgnravepay.wallet.enums.LimitedLiabilityPreDocumentType;
import business.banking.dgnravepay.wallet.service.LimitedLiabilityPreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/cac/limited-pre")
@RequiredArgsConstructor
public class LimitedLiabilityPreController {

    private final LimitedLiabilityPreService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> create(
            @ModelAttribute @Valid LimitedLiabilityPreRequestDto dto,
            @RequestParam("memorandum") MultipartFile memorandum,
            @RequestParam("cac3") MultipartFile cac3,
            @RequestParam("cac2") MultipartFile cac2,
            @RequestParam("cac7") MultipartFile cac7,
            @RequestParam("incorporation") MultipartFile incorporation,
            @RequestParam("boardResolution") MultipartFile boardResolution) throws IOException {

        service.create(dto, memorandum, cac3, cac2, cac7, incorporation, boardResolution);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/account-name")
    public ResponseEntity<Void> accountName(
            @RequestBody LimitedPreAccountNameRequestDto dto) {

        service.setAccountName(dto);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/confirm/{userProprietorId}")
    public ResponseEntity<LimitedPreConfirmDto> confirm(
            @PathVariable Long userProprietorId) {

        return ResponseEntity.ok(service.confirm(userProprietorId));
    }



    /* =================SUBMIT CONFIRMED DETAILS PAGE ================= */
    @PostMapping("/submit-confirmed-details")
    public ResponseEntity<WalletUpgradeResponseDto> submitConfirmedDetailes(
            @RequestBody LimitedPreSubmitConfirmedDetailsDto dto) {

        return ResponseEntity.ok(service.submitConfirmedDetails(dto));
    }



    @GetMapping("/document/{userProprietorId}/{type}")
    public ResponseEntity<byte[]> download(
            @PathVariable Long userProprietorId,
            @PathVariable String type) {

        byte[] file = service.download(userProprietorId, type);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=document.png")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length)
                .body(file);

    }


    @PutMapping(
            value = "/document/{userProprietorId}/{type}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<LimitedLiabilityPre> replaceDocument(
            @PathVariable Long userProprietorId,
            @PathVariable LimitedLiabilityPreDocumentType type,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        LimitedLiabilityPre updated =
                service.replaceDocument(userProprietorId, type, file);

        return ResponseEntity.ok(updated);
    }


    @PostMapping(
            value = "/director/{userProprietorId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addDirector(
            @PathVariable Long userProprietorId,
            @ModelAttribute @Valid LimitedPreDirectorRequestDto dto,
            @RequestParam("proofOfIdentity")  MultipartFile proofOfIdentity,
            @RequestParam("proofOfAddress")  MultipartFile proofOfAddress,
            @RequestParam("proofOfSignature")  MultipartFile proofOfSignature) throws IOException {

        service.addDirector(
                userProprietorId,
                dto,
                proofOfIdentity,
                proofOfAddress,
                proofOfSignature);

        return ResponseEntity.ok().build();
    }



    @PostMapping(
            value = "/principal/{userProprietorId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addPrincipal(
            @PathVariable Long userProprietorId,
            @ModelAttribute @Valid LimitedPrePrincipalRequestDto dto,
            @RequestParam("proofOfIdentity") MultipartFile proofOfIdentity,
            @RequestParam("proofOfAddress") MultipartFile proofOfAddress,
            @RequestParam("proofOfSignature") MultipartFile proofOfSignature) throws IOException {

        service.addPrincipal(
                userProprietorId,
                dto,
                proofOfIdentity,
                proofOfAddress,
                proofOfSignature);

        return ResponseEntity.ok().build();
    }




}
