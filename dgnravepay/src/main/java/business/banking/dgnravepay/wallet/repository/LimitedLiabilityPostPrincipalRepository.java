package business.banking.dgnravepay.wallet.repository;


import business.banking.dgnravepay.wallet.entity.LimitedLiabilityPostPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitedLiabilityPostPrincipalRepository extends JpaRepository<LimitedLiabilityPostPrincipal, Long> {
}

