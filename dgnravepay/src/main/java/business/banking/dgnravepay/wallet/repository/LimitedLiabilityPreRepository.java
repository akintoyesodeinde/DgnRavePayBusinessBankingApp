package business.banking.dgnravepay.wallet.repository;


import business.banking.dgnravepay.wallet.entity.LimitedLiabilityPre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitedLiabilityPreRepository extends JpaRepository<LimitedLiabilityPre, Long> {

    Optional<LimitedLiabilityPre> findByUserProprietorId(Long userProprietorId);
}
