package business.banking.dgnravepay.wallet.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "document_upload_audit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentUploadAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long userProprietorId;
    private String documentType;
    private String fileName;
    private String contentType;
    private Long fileSize;

    // ADD THIS: Stores the actual file at the time of upload
    @Lob
    @Column(columnDefinition = "bytea")
    private byte[] fileData;

    @CreationTimestamp
    private LocalDateTime uploadTimestamp;

    // Virtual field for the download link (calculated in the Controller/DTO)
    @Transient
    private String downloadUrl;
}