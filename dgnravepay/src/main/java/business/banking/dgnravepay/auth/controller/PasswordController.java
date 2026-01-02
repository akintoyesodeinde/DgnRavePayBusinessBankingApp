package business.banking.dgnravepay.auth.controller;


import business.banking.dgnravepay.auth.dto.CreatePasswordApiResponse;
import business.banking.dgnravepay.auth.dto.CreatePasswordRequestDto;
import business.banking.dgnravepay.auth.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class PasswordController {

    private final PasswordService passwordService;

    @PostMapping("/create-password")
    public CreatePasswordApiResponse createPassword(@RequestBody CreatePasswordRequestDto dto) {

        passwordService.createPassword(dto);

        return new CreatePasswordApiResponse(
                true,
                "Password Created successfully",
                "login"
        );
    }

}
