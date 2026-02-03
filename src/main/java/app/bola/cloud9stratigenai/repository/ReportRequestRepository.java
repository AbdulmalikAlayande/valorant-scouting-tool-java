package app.bola.cloud9stratigenai.repository;

import app.bola.cloud9stratigenai.common.repository.BaseRepository;
import app.bola.cloud9stratigenai.model.ReportRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository for {@link ReportRequest}
 * Repository class for managing scouting reports in the Esports Scouting Tool application.
 */
public interface ReportRequestRepository extends BaseRepository<ReportRequest, Long> {
	
	List<ReportRequest> findByStatusOrderByCreatedAtDesc(ReportRequest.ReportStatus status);
	
	Optional<ReportRequest> findFirstByStatusOrderByCreatedAtAsc(ReportRequest.ReportStatus status);
	
	@Query("SELECT r FROM ReportRequest r WHERE r.status = :status AND r.createdAt > :since")
	List<ReportRequest> findRecentByStatus(@Param("status") ReportRequest.ReportStatus status, @Param("since") LocalDateTime since);
	
}
