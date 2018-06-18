ALTER TABLE cargo ADD external BOOLEAN DEFAULT false;

create table if not exists EXTERNAL (
  external_id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30),
  weight INT,
  city_from BIGINT,
  city_to BIGINT,
  PRIMARY KEY (external_id),
  FOREIGN KEY (city_from) REFERENCES CITY(city_id),
  FOREIGN KEY (city_to) REFERENCES CITY(city_id)
);