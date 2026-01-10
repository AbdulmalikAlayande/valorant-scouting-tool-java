ALTER TABLE report_requests
  ALTER COLUMN id TYPE bigint;

-- If the id uses a sequence (serial), ensure the sequence is bigint-capable and still used:
ALTER SEQUENCE report_requests_id_seq AS bigint;

ALTER TABLE report_requests
  ALTER COLUMN id SET DEFAULT nextval('report_requests_id_seq');