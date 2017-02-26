CREATE SCHEMA IF NOT EXISTS pingbus;

CREATE TABLE IF NOT EXISTS pingbus.station(
  ID        SERIAL      NOT NULL,
  TTA       TIME        NOT NULL,
  RATING    INT         NULL,
  PRIMARY KEY (ID)
);

INSERT INTO pingbus.station (TTA, RATING) VALUES ('10:30', 2), ('10:28', 2),
  ('10:23', 2), ('10:28', 0), ('1:27', 1), ('2:18', 4), ('18:12', 0), ('20:02', 2);