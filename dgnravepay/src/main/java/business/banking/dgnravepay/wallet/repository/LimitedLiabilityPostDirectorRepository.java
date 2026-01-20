package business.banking.dgnravepay.wallet.repository;



import business.banking.dgnravepay.wallet.entity.LimitedLiabilityPostDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitedLiabilityPostDirectorRepository  extends JpaRepository<LimitedLiabilityPostDirector, Long> {
}
