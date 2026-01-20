package business.banking.dgnravepay.wallet.repository;


import business.banking.dgnravepay.wallet.entity.SoleProprietorship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SoleProprietorshipRepository extends JpaRepository<SoleProprietorship, Long> {

    Optional<SoleProprietorship> findByUserProprietorId(Long userProprietorId);
}
