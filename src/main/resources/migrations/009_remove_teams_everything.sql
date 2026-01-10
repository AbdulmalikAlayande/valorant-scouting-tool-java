BEGIN;

-- 1) Drop the view that blocks column type changes
DROP VIEW IF EXISTS team_summary CASCADE;

-- 2) Drop helper trigger/function created in migration 002
DROP TRIGGER IF EXISTS update_teams_updated_at ON teams;
DROP FUNCTION IF EXISTS update_updated_at_column();
DROP FUNCTION IF EXISTS get_or_create_team_fk(VARCHAR, VARCHAR);

-- 3) Drop indexes created in migration 002 (safe to drop even if missing)
DROP INDEX IF EXISTS idx_report_requests_team_fk;
DROP INDEX IF EXISTS idx_matches_team_fk;
DROP INDEX IF EXISTS idx_matches_opponent_fk;
DROP INDEX IF EXISTS idx_scouting_reports_team_fk;

DROP INDEX IF EXISTS idx_teams_team_id;
DROP INDEX IF EXISTS idx_teams_name;
DROP INDEX IF EXISTS idx_teams_organization;

-- 4) Remove FK columns added by migration 002
ALTER TABLE report_requests
    DROP COLUMN IF EXISTS team_fk_id;

ALTER TABLE matches
    DROP COLUMN IF EXISTS team_fk_id,
    DROP COLUMN IF EXISTS opponent_fk_id;

ALTER TABLE scouting_reports
    DROP COLUMN IF EXISTS team_fk_id;

-- 5) Drop the teams table itself
DROP TABLE IF EXISTS teams CASCADE;

COMMIT;