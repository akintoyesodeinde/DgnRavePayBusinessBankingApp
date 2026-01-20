package business.banking.dgnravepay.wallet.repository;



import business.banking.dgnravepay.wallet.entity.UserWalletUpgrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserWalletUpgradeRepository extends JpaRepository<UserWalletUpgrade, UUID> {
}
