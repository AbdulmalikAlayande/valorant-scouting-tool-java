ALTER TABLE report_requests
  ADD COLUMN IF NOT EXISTS public_id varchar(36);

ALTER TABLE report_requests
  ADD COLUMN IF NOT EXISTS last_modified_at timestamp NOT NULL DEFAULT now();


ALTER TABLE scouting_reports
  ADD COLUMN IF NOT EXISTS public_id varchar(36);

ALTER TABLE scouting_reports
  ADD COLUMN IF NOT EXISTS last_modified_at timestamp NOT NULL DEFAULT now();

ALTER TABLE scouting_reports
  ADD COLUMN IF NOT EXISTS summary text NOT NULL DEFAULT ''::text;


CREATE EXTENSION IF NOT EXISTS pgcrypto;

UPDATE report_requests
SET public_id = COALESCE(public_id, gen_random_uuid()::text);

UPDATE scouting_reports
SET public_id = COALESCE(public_id, gen_random_uuid()::text);


ALTER TABLE report_requests
  ALTER COLUMN public_id SET NOT NULL;

ALTER TABLE scouting_reports
  ALTER COLUMN public_id SET NOT NULL;

CREATE UNIQUE INDEX IF NOT EXISTS ux_report_requests_public_id
  ON report_requests(public_id);

CREATE UNIQUE INDEX IF NOT EXISTS ux_scouting_reports_public_id
  ON scouting_reports(public_id);