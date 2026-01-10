ALTER TABLE scouting_reports
-- DROP COLUMN IF EXISTS team_fk_id,
DROP COLUMN IF EXISTS team_id,
DROP COLUMN IF EXISTS team_name,
DROP COLUMN IF EXISTS total_matches,
DROP COLUMN IF EXISTS total_games,
DROP COLUMN IF EXISTS win_rate,
DROP COLUMN IF EXISTS current_streak,
DROP COLUMN IF EXISTS top_agents,
DROP COLUMN IF EXISTS map_performance,
DROP COLUMN IF EXISTS player_stats,
DROP COLUMN IF EXISTS actionable_insights,
DROP COLUMN IF EXISTS time_window;

ALTER TABLE scouting_reports
ADD COLUMN IF NOT EXISTS report_type VARCHAR(50) NOT NULL DEFAULT 'full_scouting',
ADD COLUMN IF NOT EXISTS report_data JSONB NOT NULL DEFAULT '{}'::jsonb,
ADD COLUMN IF NOT EXISTS generated_report TEXT NOT NULL DEFAULT ''::text;

CREATE INDEX IF NOT EXISTS idx_scouting_reports_type
ON scouting_reports(report_type);

CREATE INDEX IF NOT EXISTS idx_scouting_reports_data
ON scouting_reports USING gin(report_data);

-- Ensure report_request_id is UNIQUE for ON CONFLICT to work
ALTER TABLE scouting_reports
ADD CONSTRAINT scouting_reports_request_id_unique
UNIQUE (report_request_id);