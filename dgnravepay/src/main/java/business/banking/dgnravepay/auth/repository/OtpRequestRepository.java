package business.banking.dgnravepay.auth.repository;


import business.banking.dgnravepay.auth.entity.OtpRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface OtpRequestRepository extends JpaRepository<OtpRequestEntity, UUID> {

    Optional<OtpRequestEntity> findByReferenceId(String referenceId);

    Optional<OtpRequestEntity> findTopByPhoneNumberOrderByCreatedAtDesc(String phoneNumber);
}
