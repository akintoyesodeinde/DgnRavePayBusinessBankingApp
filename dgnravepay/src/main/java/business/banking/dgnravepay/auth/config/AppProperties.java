package business.banking.dgnravepay.auth.config;




import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("${app.jwt.access-token-expiration-sec}")
    private long accessTokenExpirationSec;
    @Value("${app.jwt.refresh-token-expiration-sec}")
    private long refreshTokenExpirationSec;


}

