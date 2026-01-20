package business.banking.dgnravepay.wallet.repository;


import business.banking.dgnravepay.wallet.entity.LimitedLiabilityPreDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitedLiabilityPreDirectorRepository extends JpaRepository<LimitedLiabilityPreDirector, Long> {
}
