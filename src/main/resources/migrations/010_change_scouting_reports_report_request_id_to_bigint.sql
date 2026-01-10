ALTER TABLE scouting_reports
  ALTER COLUMN report_request_id TYPE bigint
  USING report_request_id::bigint;

ALTER TABLE scouting_reports
  ADD CONSTRAINT scouting_reports_report_request_id_fk
  FOREIGN KEY (report_request_id)
  REFERENCES report_requests(id)
  ON DELETE CASCADE;

ALTER TABLE scouting_reports
  ADD CONSTRAINT scouting_reports_report_request_id_unique
  UNIQUE (report_request_id);

COMMIT;