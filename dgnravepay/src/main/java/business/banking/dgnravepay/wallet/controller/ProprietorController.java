package business.banking.dgnravepay.wallet.controller;


import business.banking.dgnravepay.wallet.dto.UserProprietorRequestDto;
import business.banking.dgnravepay.wallet.dto.UserProprietorResponseDto;
import business.banking.dgnravepay.wallet.entity.UserProprietor;
import business.banking.dgnravepay.wallet.enums.UtilityDocumentType;
import business.banking.dgnravepay.wallet.mapper.UserProprietorMapper;
import business.banking.dgnravepay.wallet.service.UserProprietorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;

import java.io.IOException;

@RestController
@RequestMapping("/api/proprietor")
@RequiredArgsConstructor
public class ProprietorController {

    private final UserProprietorService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserProprietorResponseDto> create(
            @ModelAttribute @Valid UserProprietorRequestDto dto,
            @RequestParam("logo") MultipartFile logo,
            @RequestParam("utilityBill") MultipartFile utilityBill) throws IOException {

        UserProprietor saved = service.create(dto, logo, utilityBill);

        return ResponseEntity
                .status(201)
                .body(UserProprietorMapper.toDto(saved));
    }
}
