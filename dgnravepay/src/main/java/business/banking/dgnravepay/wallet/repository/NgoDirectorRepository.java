package business.banking.dgnravepay.wallet.repository;


import business.banking.dgnravepay.wallet.entity.NgoDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NgoDirectorRepository
        extends JpaRepository<NgoDirector, Long> {
}
