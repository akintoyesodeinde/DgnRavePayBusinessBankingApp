package business.banking.dgnravepay.auth.repository;

import business.banking.dgnravepay.auth.entity.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, UUID> {

    Optional<LoginUser> findByUserName(String userName);

    Optional<LoginUser> findByEmail(String email);
}
