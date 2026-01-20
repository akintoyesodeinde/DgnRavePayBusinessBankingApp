package business.banking.dgnravepay.auth.service;

import business.banking.dgnravepay.auth.entity.LoginUser;
import business.banking.dgnravepay.auth.repository.LoginUserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService {

    private final LoginUserRepository loginUserRepository;

    public LoginUserService(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }

    public String getAccessTokenByEmail(String email) {
        LoginUser user = loginUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Login user not found: " + email)
                );

        if (user.getAccessToken() == null) {
            throw new RuntimeException("Access token is missing for user: " + email);
        }

        return user.getAccessToken();
    }
}
