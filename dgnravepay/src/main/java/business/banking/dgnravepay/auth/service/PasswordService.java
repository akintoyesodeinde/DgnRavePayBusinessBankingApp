package business.banking.dgnravepay.auth.service;


import business.banking.dgnravepay.auth.dto.CreatePasswordRequestDto;
import business.banking.dgnravepay.auth.entity.UserEntity;
import business.banking.dgnravepay.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public void createPassword(CreatePasswordRequestDto dto) {

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        UserEntity user = userRepo.findByPhoneNumber(dto.getPhoneNumber())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        if (!user.isVerified()) {
            throw new IllegalStateException("Phone number not verified");
        }

        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        userRepo.save(user);
    }
}
