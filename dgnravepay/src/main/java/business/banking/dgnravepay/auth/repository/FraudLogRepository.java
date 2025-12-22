package business.banking.dgnravepay.auth.repository;

import business.banking.dgnravepay.auth.entity.FraudLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;


@Repository
public interface FraudLogRepository extends JpaRepository<FraudLogEntity, UUID> {
}
