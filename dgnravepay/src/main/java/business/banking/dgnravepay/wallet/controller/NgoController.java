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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/cac/ngo")
@RequiredArgsConstructor
public class NgoController {

    private final NgoService service;

    /* ===== CAC VERIFICATION ===== */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> createNgo(
            @ModelAttribute @Valid NgoRequestDto dto,
            @RequestParam("cacIt1Form") MultipartFile cacIt1Form) throws IOException {
        service.createNgo(dto, cacIt1Form);

        return ResponseEntity.ok(new ApiResponse(
                "NGO Created successfully",
                LocalDateTime.now()
        ));
    }


    /* ===== ACCOUNT NAME ===== */
    @PostMapping("/account-name")
    public ResponseEntity<ApiResponse> accountName(
            @RequestBody NgoAccountNameRequestDto dto) {

        service.setAccountName(dto);
        return ResponseEntity.ok(new ApiResponse(
                "Business Account Name Created successfully",
                LocalDateTime.now()
        ));
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

        return buildFileResponse(file, "cacIt1Form");

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





    @PutMapping(value = "/cac-it1/{userProprietorId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> replace(
            @PathVariable Long userProprietorId,
            @RequestParam("file") MultipartFile file) throws IOException {

        service.replaceCacIt1(userProprietorId, file);


        return ResponseEntity.ok(new ApiResponse(
                "Document updated successfully",
                LocalDateTime.now()
        ));
    }




    /* ===== ADD DIRECTOR ===== */
    @PostMapping(value = "/director/{userProprietorId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> addDirector(
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

        return ResponseEntity.ok(new ApiResponse(
                "Director Created successfully",
                LocalDateTime.now()
        ));
    }
}
