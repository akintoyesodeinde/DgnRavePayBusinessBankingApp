package business.banking.dgnravepay.wallet.controller;


import business.banking.dgnravepay.wallet.dto.*;
import business.banking.dgnravepay.wallet.entity.LimitedLiabilityPost;
import business.banking.dgnravepay.wallet.enums.LimitedLiabilityDocumentPostType;
import business.banking.dgnravepay.wallet.service.LimitedLiabilityPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/cac/limited-post")
@RequiredArgsConstructor
public class LimitedLiabilityPostController {
    private final LimitedLiabilityPostService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> create(
            @ModelAttribute @Valid LimitedLiabilityPostRequestDto dto,
            @RequestParam("memorandum") MultipartFile memorandum,
            @RequestParam("incorporation") MultipartFile incorporation,
            @RequestParam("boardResolution") MultipartFile boardResolution) throws IOException {

        service.create(dto, memorandum, incorporation, boardResolution);
        return ResponseEntity.ok().build();
    }




    @PostMapping("/account-name")
    public ResponseEntity<Void> accountName(
            @RequestBody LimitedPostAccountNameRequestDto dto) {

        service.setAccountName(dto);
        return ResponseEntity.ok().build();
    }

    /* =================SUBMIT CONFIRMED DETAILS PAGE ================= */
    @PostMapping("/submit-confirmed-details")
    public ResponseEntity<WalletUpgradeResponseDto> submitConfirmedDetailes(
            @RequestBody LimitedPostSubmitConfirmedDetailsDto dto) {

        return ResponseEntity.ok(service.submitConfirmedDetails(dto));
    }


    @GetMapping("/confirm/{userProprietorId}")
    public ResponseEntity<LimitedPostConfirmDto> confirm(
            @PathVariable Long userProprietorId) {

        return ResponseEntity.ok(service.confirm(userProprietorId));
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
    public ResponseEntity<LimitedLiabilityPost> replaceDocument(
            @PathVariable Long userProprietorId,
            @PathVariable LimitedLiabilityDocumentPostType type,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        LimitedLiabilityPost updated =
                service.replaceDocument(userProprietorId, type, file);

        return ResponseEntity.ok(updated);
    }



    @PostMapping(
            value = "/director/{userProprietorId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addDirector(
            @PathVariable Long userProprietorId,
            @ModelAttribute @Valid LimitedPostDirectorRequestDto dto,
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



    @PostMapping(
            value = "/principal/{userProprietorId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addPrincipal(
            @PathVariable Long userProprietorId,
            @ModelAttribute @Valid LimitedPostPrincipalRequestDto dto,
            @RequestParam("proofOfIdentity") MultipartFile proofOfIdentity,
            @RequestParam("proofOfAddress")  MultipartFile proofOfAddress,
            @RequestParam("proofOfSignature")  MultipartFile proofOfSignature) throws IOException {

        service.addPrincipal(
                userProprietorId,
                dto,
                proofOfIdentity,
                proofOfAddress,
                proofOfSignature);

        return ResponseEntity.ok().build();
    }



}
