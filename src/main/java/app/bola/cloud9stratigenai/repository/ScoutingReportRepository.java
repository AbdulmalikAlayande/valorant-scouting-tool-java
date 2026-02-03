package app.bola.cloud9stratigenai.repository;

import app.bola.cloud9stratigenai.common.repository.BaseRepository;
import app.bola.cloud9stratigenai.model.ScoutingReport;

import java.util.Optional;

/**
 * Repository for {@link ScoutingReport}
 * Repository class for managing scouting reports in the Esports Scouting Tool application.
 */
public interface ScoutingReportRepository extends BaseRepository<ScoutingReport, Long> {
	
	Optional<ScoutingReport> findByReportRequestId(Long requestId);

	boolean existsByReportRequestPublicId(String publicId);
}
