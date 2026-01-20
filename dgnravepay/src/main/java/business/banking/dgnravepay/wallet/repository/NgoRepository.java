package business.banking.dgnravepay.wallet.repository;


import business.banking.dgnravepay.wallet.entity.Ngo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NgoRepository extends JpaRepository<Ngo, Long> {
    Optional<Ngo> findByUserProprietorId(Long userProprietorId);
}
