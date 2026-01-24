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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/cac/limited-pre")
@RequiredArgsConstructor
public class LimitedLiabilityPreController {

    private final LimitedLiabilityPreService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> create(
            @ModelAttribute @Valid LimitedLiabilityPreRequestDto dto,
            @RequestParam("memorandum") MultipartFile memorandum,
            @RequestParam("cac3") MultipartFile cac3,
            @RequestParam("cac2") MultipartFile cac2,
            @RequestParam("cac7") MultipartFile cac7,
            @RequestParam("incorporation") MultipartFile incorporation,
            @RequestParam("boardResolution") MultipartFile boardResolution) throws IOException {

        service.create(dto, memorandum, cac3, cac2, cac7, incorporation, boardResolution);


        return ResponseEntity.ok(new ApiResponse(
                "Limited Liability Created successfully",
                LocalDateTime.now()
        ));
    }

    @PostMapping("/account-name")
    public ResponseEntity<ApiResponse> accountName(
            @RequestBody LimitedPreAccountNameRequestDto dto) {

        service.setAccountName(dto);

        return ResponseEntity.ok(new ApiResponse(
                "Business Account Name Created successfully",
                LocalDateTime.now()
        ));
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
            @PathVariable LimitedLiabilityPreDocumentType type) {

        byte[] file = service.download(userProprietorId, type);

        return buildFileResponse(file, "Document");

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







    @PutMapping(
            value = "/document/{userProprietorId}/{type}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse> replaceDocument(
            @PathVariable Long userProprietorId,
            @PathVariable LimitedLiabilityPreDocumentType type,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

                service.replaceDocument(userProprietorId, type, file);

        return ResponseEntity.ok(new ApiResponse(
                "Document updated successfully",
                LocalDateTime.now()
        ));
    }


    @PostMapping(
            value = "/director/{userProprietorId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> addDirector(
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

        return ResponseEntity.ok(new ApiResponse(
                "Director Created successfully",
                LocalDateTime.now()
        ));
    }



    @PostMapping(
            value = "/principal/{userProprietorId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> addPrincipal(
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

        return ResponseEntity.ok(new ApiResponse(
                "Principal Owner Created successfully",
                LocalDateTime.now()
        ));
    }
    
}
