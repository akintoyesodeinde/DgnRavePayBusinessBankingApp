package business.banking.dgnravepay.auth.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class OtpRateLimiter {

    private final RedisTemplate<String, String> redisTemplate;
    private static final int MAX_ATTEMPTS = 5;

    public void checkLimit(String phoneNumber) {
        String key = "otp:rate:" + phoneNumber;

        Long count = redisTemplate.opsForValue().increment(key);

        if (count == 1) {
            redisTemplate.expire(key, Duration.ofMinutes(10));
        }

        if (count > MAX_ATTEMPTS) {
            throw new IllegalStateException("Too many OTP requests. Try later.");
        }
    }
}
