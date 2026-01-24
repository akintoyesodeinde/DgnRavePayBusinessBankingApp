package business.banking.dgnravepay.wallet.controller;


import business.banking.dgnravepay.wallet.dto.*;
import business.banking.dgnravepay.wallet.service.SoleProprietorshipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;

import business.banking.dgnravepay.wallet.entity.DocumentUploadAudit;




@RestController
@RequestMapping("/api/cac/sole-proprietorship")
@RequiredArgsConstructor
public class SoleProprietorshipController {

    private final SoleProprietorshipService service;



    /* ================= CAC VERIFICATION ================= */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> create(
            @ModelAttribute @Valid SoleProprietorshipRequestDto dto,
            @RequestParam("certApplication") MultipartFile certApplication,
            @RequestParam("certBusinessName") MultipartFile certBusinessName
    ) throws IOException {

        service.create(dto, certApplication, certBusinessName);

        return ResponseEntity.ok(new ApiResponse(
                "Sole Proprietorship Created successfully",
                LocalDateTime.now()
        ));
    }

    /* ================= ACCOUNT NAME PAGE ================= */
    @PostMapping("/account-name")
    public ResponseEntity<ApiResponse> setAccountName(
            @RequestBody SpAccountNameRequestDto dto) {

        service.setAccountName(dto);
        return ResponseEntity.ok(new ApiResponse(
                "Business Account Name Created successfully",
                LocalDateTime.now()
        ));
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






    /* ================= DOCUMENT DOWNLOADS ================= */

    @GetMapping("/certificate-application/{userProprietorId}")
    public ResponseEntity<byte[]> downloadApplication(@PathVariable Long userProprietorId) {
        byte[] file = service.downloadCertificateApplication(userProprietorId);
        return buildFileResponse(file, "certificate_application");
    }

    @GetMapping("/certificate-business-name/{userProprietorId}")
    public ResponseEntity<byte[]> downloadBusinessName(@PathVariable Long userProprietorId) {
        byte[] file = service.downloadCertificateBusinessName(userProprietorId);
        return buildFileResponse(file, "certificate_business_name");
    }

    /**
     * Helper method to dynamically determine Content-Type and Filename Extension
     */
    private ResponseEntity<byte[]> buildFileResponse(byte[] file, String baseFileName) {
        if (file == null || file.length == 0) {
            return ResponseEntity.notFound().build();
        }

        String mimeType;
        try (ByteArrayInputStream is = new ByteArrayInputStream(file)) {
            mimeType = URLConnection.guessContentTypeFromStream(is);
        } catch (IOException e) {
            mimeType = null;
        }

        // Fallback logic if URLConnection fails to guess
        if (mimeType == null) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        // Determine extension based on mimeType for the filename
        String extension = switch (mimeType) {
            case "image/png" -> ".png";
            case "image/jpeg" -> ".jpg";
            case "application/pdf" -> ".pdf";
            case "application/msword" -> ".doc";
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document" -> ".docx";
            default -> "";
        };

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                // 'inline' allows Postman Preview to work for Images and PDFs
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + baseFileName + extension)
                .contentLength(file.length)
                .body(file);
    }



    /* =========================
           DOCUMENT EDIT (REPLACE)
           ========================= */
    @PutMapping(
            value = "/certificate-application/{userProprietorId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse> replaceCertificateApplication(
            @PathVariable Long userProprietorId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        service.replaceCertificateApplication(userProprietorId, file);

        return ResponseEntity.ok(new ApiResponse(
                "Document updated and archived successfully",
                LocalDateTime.now()
        ));
    }




    @PutMapping(value = "/certificate-business-name/{userProprietorId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> replaceBusinessName(
            @PathVariable Long userProprietorId,
            @RequestParam("file") MultipartFile file) throws IOException {

        service.replaceCertificateBusinessName(userProprietorId, file);

        return ResponseEntity.ok(new ApiResponse(
                "Document updated and archived successfully",
                LocalDateTime.now()
        ));
    }
}


