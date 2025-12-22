package business.banking.dgnravepay.auth.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class OtpCacheService {

    private final RedisTemplate<String, String> redisTemplate;

    public void saveOtp(String referenceId, Duration ttl) {
        redisTemplate.opsForValue()
                .set("otp:" + referenceId, "VALID", ttl);
    }

    public boolean exists(String referenceId) {
        return Boolean.TRUE.equals(
                redisTemplate.hasKey("otp:" + referenceId)
        );
    }

    public void delete(String referenceId) {
        redisTemplate.delete("otp:" + referenceId);
    }
}
