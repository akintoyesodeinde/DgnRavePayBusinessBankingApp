package business.banking.dgnravepay.wallet.repository;


import business.banking.dgnravepay.wallet.entity.SoleProprietorship;
import business.banking.dgnravepay.wallet.entity.UserProprietor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProprietorRepository extends JpaRepository<UserProprietor, Long> {
    //Optional<UserProprietor> findByUserProprietorId(Long userProprietorId);
}
