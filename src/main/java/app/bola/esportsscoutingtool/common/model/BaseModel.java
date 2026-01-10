package app.bola.esportsscoutingtool.common.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String publicId;
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
	@Column(name = "created_at", nullable = false)
	private LocalDateTime lastModifiedAt = LocalDateTime.now();
	
	@PrePersist
	protected void onCreate() {
		publicId = java.util.UUID.randomUUID().toString();
	}
}
