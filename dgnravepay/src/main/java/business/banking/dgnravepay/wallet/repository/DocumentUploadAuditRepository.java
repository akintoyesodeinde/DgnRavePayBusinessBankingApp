package business.banking.dgnravepay.wallet.repository;



import business.banking.dgnravepay.wallet.entity.DocumentUploadAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DocumentUploadAuditRepository extends JpaRepository<DocumentUploadAudit, UUID> {

    // Finds all history for a user, newest first
    List<DocumentUploadAudit> findByUserProprietorIdOrderByUploadTimestampDesc(Long userProprietorId);
}