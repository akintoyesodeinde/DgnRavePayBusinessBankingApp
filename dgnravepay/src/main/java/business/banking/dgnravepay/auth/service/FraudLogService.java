package business.banking.dgnravepay.auth.service;


import business.banking.dgnravepay.auth.entity.FraudLogEntity;
import business.banking.dgnravepay.auth.repository.FraudLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FraudLogService {

    private final FraudLogRepository repo;

    public void logFailedLogin(String phone, String reason) {
        save(phone, null, reason);
    }

    public void logSuspiciousDevice(String phone, String fingerprint) {
        save(phone, fingerprint, "UNTRUSTED_DEVICE");
    }

    private void save(String phone, String fingerprint, String reason) {
        FraudLogEntity log = new FraudLogEntity();
        log.setId(UUID.randomUUID());
        log.setPhoneNumber(phone);
        log.setDeviceFingerprint(fingerprint);
        log.setReason(reason);
        log.setCreatedAt(Instant.now());
        repo.save(log);
    }
}
