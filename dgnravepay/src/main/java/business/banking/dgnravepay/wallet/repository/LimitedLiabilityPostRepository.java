package business.banking.dgnravepay.wallet.repository;

import business.banking.dgnravepay.wallet.entity.LimitedLiabilityPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LimitedLiabilityPostRepository extends JpaRepository<LimitedLiabilityPost, Long> {

    Optional<LimitedLiabilityPost> findByUserProprietorId(Long userProprietorId);
}

