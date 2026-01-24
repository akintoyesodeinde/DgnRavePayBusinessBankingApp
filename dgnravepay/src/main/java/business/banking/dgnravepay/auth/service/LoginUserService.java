package business.banking.dgnravepay.auth.service;

import business.banking.dgnravepay.auth.entity.LoginUser;
import business.banking.dgnravepay.auth.repository.LoginUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Slf4j
@Service
public class LoginUserService {

    private final LoginUserRepository loginUserRepository;

    public LoginUserService(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }

    public String getAccessTokenByEmail(String email) {
        log.info("Attempting to retrieve valid token for: {}", email);

        // 1. Get the absolute latest record by creation date
        LoginUser user = loginUserRepository.findFirstByEmailOrderByCreatedAtDesc(email)
                .orElseThrow(() -> new RuntimeException("No login history found for email: " + email));

        // 2. Check if token exists
        if (user.getAccessToken() == null || user.getAccessToken().isBlank()) {
            log.error("Found login record for {} but accessToken is empty", email);
            throw new RuntimeException("Access token is missing in the database for user: " + email);
        }

        // 3. Verify Expiration Logic
        // Using the method we created in the Entity
        if (!user.isTokenValid()) {
            log.warn("Token for {} expired at {}. Current time is {}",
                    email, user.getExpiresIn(), LocalDateTime.now());
            throw new RuntimeException("The access token for " + email + " has expired. Please login again.");
        }

        log.info("Valid token retrieved for {}. (Expires at: {})", email, user.getExpiresIn());
        return user.getAccessToken();
    }
}