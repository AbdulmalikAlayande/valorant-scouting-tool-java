package app.bola.esportsscoutingtool.repository;

import app.bola.esportsscoutingtool.common.repository.BaseRepository;
import app.bola.esportsscoutingtool.model.ScoutingReport;

import java.util.Optional;

/**
 * Repository for {@link ScoutingReport}
 * Repository class for managing scouting reports in the Esports Scouting Tool application.
 */
public interface ScoutingReportRepository extends BaseRepository<ScoutingReport, Long> {
	
	Optional<ScoutingReport> findByReportRequestId(Long requestId);
}
