package app.bola.cloud9stratigenai.common.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "public_id", nullable = false, updatable = false, length = 36)
	private String publicId;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Column(name = "last_modified_at", nullable = false)
	private LocalDateTime lastModifiedAt = LocalDateTime.now();
	
	@PrePersist
	protected void onCreate() {
		publicId = java.util.UUID.randomUUID().toString();
		lastModifiedAt = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		lastModifiedAt = LocalDateTime.now();
	}
}