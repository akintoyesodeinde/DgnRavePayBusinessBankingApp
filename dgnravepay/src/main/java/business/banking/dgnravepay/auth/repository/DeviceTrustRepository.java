package business.banking.dgnravepay.auth.repository;


import business.banking.dgnravepay.auth.entity.DeviceTrustEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface DeviceTrustRepository extends JpaRepository<DeviceTrustEntity, UUID> {

    Optional<DeviceTrustEntity> findByUserIdAndDeviceFingerprintAndPhoneNumber(UUID userId, String deviceFingerprint, String phoneNumber);
}






//
//
//package business.banking.dgnravepay.auth.repository;
//
//
//import business.banking.dgnravepay.auth.entity.DeviceTrustEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository
//public interface DeviceTrustRepository extends JpaRepository<DeviceTrustEntity, UUID> {
//
//    Optional<DeviceTrustEntity> findByUserIdAndDeviceFingerprintAndPhoneNumber(UUID userId, String fingerprint, String phoneNumber);
//}
