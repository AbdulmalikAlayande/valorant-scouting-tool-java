-- 1) Convert id column type to bigint
BEGIN;

-- If anything still depends on the column, this avoids the "used by a view/rule" failure.
-- If you already ran migration 009, this is a no-op.
DROP VIEW IF EXISTS team_summary CASCADE;

ALTER TABLE scouting_reports
ALTER COLUMN id TYPE bigint;

-- 2) Make sure the underlying sequence is bigint-capable
-- (Assumes default sequence name "scouting_reports_id_seq"; adjust if different)
ALTER SEQUENCE scouting_reports_id_seq AS bigint;

-- 3) Ensure the id default still points to the sequence
ALTER TABLE scouting_reports
    ALTER COLUMN id SET DEFAULT nextval('scouting_reports_id_seq');

COMMIT;