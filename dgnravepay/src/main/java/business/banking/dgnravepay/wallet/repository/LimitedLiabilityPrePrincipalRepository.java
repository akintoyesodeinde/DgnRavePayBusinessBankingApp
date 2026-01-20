package business.banking.dgnravepay.wallet.repository;


import business.banking.dgnravepay.wallet.entity.LimitedLiabilityPrePrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitedLiabilityPrePrincipalRepository extends JpaRepository<LimitedLiabilityPrePrincipal, Long> {
}

