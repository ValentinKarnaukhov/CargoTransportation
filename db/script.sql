DROP TABLE IF EXISTS DRIVER;
DROP TABLE IF EXISTS ORDER_WAYPOINT;
DROP TABLE IF EXISTS CARGO;
DROP TABLE IF EXISTS TRUCK;
DROP TABLE IF EXISTS ORDERS;
DROP TABLE IF EXISTS CITY;
DROP TABLE IF EXISTS USER;

create table USER (
  user_id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(30) NOT NULL,
  email VARCHAR(30) NOT NULL,
  password VARCHAR(100) NOT NULL,
  role ENUM('ADMIN','MANAGER','DRIVER') DEFAULT 'MANAGER' NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT TRUE ,
  PRIMARY KEY (user_id),
  UNIQUE (email)
);

create table CITY(
  city_id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  latitude double,
  longitude double,
  PRIMARY KEY (city_id),
  UNIQUE (name)
);

create table ORDERS(
  order_id BIGINT NOT NULL AUTO_INCREMENT,
  is_complete BOOLEAN NOT NULL,
  PRIMARY KEY (order_id)
);

create table TRUCK(
  truck_id BIGINT NOT NULL AUTO_INCREMENT,
  reg_number VARCHAR(30) NOT NULL,
  max_drivers INT(30) NOT NULL,
  capacity INT(11) NOT NULL,
  status ENUM('OK','BROKEN') DEFAULT 'OK' NOT NULL,
  city_id BIGINT NOT NULL,
  order_id BIGINT,
  enabled BOOLEAN,
  PRIMARY KEY (truck_id),
  UNIQUE (reg_number),
  FOREIGN KEY (city_id) REFERENCES CITY(city_id),
  FOREIGN KEY (order_id) REFERENCES ORDERS(order_id)
);



create table CARGO(
  cargo_id BIGINT NOT NULL AUTO_INCREMENT,
  number VARCHAR(30) NOT NULL ,
  name VARCHAR(30) NOT NULL ,
  weight INT(11) NOT NULL ,
  status ENUM('PREPARE','SHIPPED','DONE') NOT NULL ,
  order_id BIGINT NOT NULL ,
  PRIMARY KEY (cargo_id),
  FOREIGN KEY (order_id) REFERENCES ORDERS(order_id)
);

create table ORDER_WAYPOINT(
  order_waypoint_id BIGINT NOT NULL AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  city_id BIGINT NOT NULL,
  cargo_id BIGINT NOT NULL,
  operation ENUM('LOADING','UNLOADING') NOT NULL ,
  status ENUM('PROGRESS','DONE') NOT NULL ,
  PRIMARY KEY (order_waypoint_id),
  FOREIGN KEY (order_id) REFERENCES ORDERS(order_id),
  FOREIGN KEY (city_id) REFERENCES CITY(city_id),
  FOREIGN KEY (cargo_id) REFERENCES CARGO(cargo_id)
);

create table DRIVER(
  driver_id BIGINT NOT NULL AUTO_INCREMENT,
  personal_code VARCHAR(30) NOT NULL ,
  first_name VARCHAR(30) NOT NULL ,
  last_name VARCHAR(30) NOT NULL ,
  worked_time INT(11) NOT NULL ,
  status ENUM('REST','WORK','DRIVE') DEFAULT 'REST' NOT NULL ,
  city_id BIGINT NOT NULL,
  truck_id BIGINT,
  user_id BIGINT NOT NULL,
  start DATETIME,
  PRIMARY KEY (driver_id),
  UNIQUE (personal_code),
  FOREIGN KEY (city_id) REFERENCES CITY(city_id),
  FOREIGN KEY (truck_id) REFERENCES TRUCK(truck_id),
  FOREIGN KEY (user_id) REFERENCES USER(user_id)
);

create table ORDER_HISTORY(
  history_id BIGINT NOT NULL AUTO_INCREMENT,
  order_id BIGINT NOT NULL ,
  truck_id BIGINT NOT NULL,
  PRIMARY KEY (history_id),
  FOREIGN KEY (order_id) REFERENCES ORDERS(order_id),
  FOREIGN KEY (truck_id) REFERENCES TRUCK(truck_id)
);

create table DRIVER_ORDER_HISTORY(
  history_id BIGINT NOT NULL ,
  driver_id BIGINT NOT NULL,
  PRIMARY KEY (history_id, driver_id),
  FOREIGN KEY (history_id) REFERENCES ORDER_HISTORY(history_id),
  FOREIGN KEY (driver_id) REFERENCES DRIVER(driver_id)
);




INSERT INTO USER(username, email, password, role) VALUES ('admin',
                                                          'admin@admin.com',
                                                          '$2a$10$/YL1sca6O7ZFhtrAwUKULOv8FVYz96VXCl896CMbyCWikzrRCvs6q',
                                                          'ADMIN');



ALTER TABLE logistic.cargo CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE logistic.city CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE logistic.driver CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE logistic.order_waypoint CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE logistic.orders CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE logistic.truck CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE logistic.user CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;

INSERT INTO CITY(name, latitude, longitude) value ('Abacan',	53.720976,	91.44242300000001);
INSERT INTO CITY(name, latitude, longitude) value ('Arhangel\'sk',	64.539304,	40.518735);
INSERT INTO CITY(name, latitude, longitude) value ('Astana',	71.430564,	51.128422);
INSERT INTO CITY(name, latitude, longitude) value ('Barnaul',	53.356132,	83.74961999999999);
INSERT INTO CITY(name, latitude, longitude) value ('Belgorod',	50.597467,	36.588849);
INSERT INTO CITY(name, latitude, longitude) value ('Biysk',	 52.541444,	85.219686 );
INSERT INTO CITY(name, latitude, longitude) value ('Bishkek',	42.871027,	74.59452 );
INSERT INTO CITY(name, latitude, longitude) value ('Blagoveshensk',	50.290658,	127.527173 );
INSERT INTO CITY(name, latitude, longitude) value ('Bratsk',	56.151382,	101.634152  );
INSERT INTO CITY(name, latitude, longitude) value ('Bryans',	53.2434,	34.364198 );
INSERT INTO CITY(name, latitude, longitude) value ('Velikiy Novgorod',	58.521475,	31.275475);
INSERT INTO CITY(name, latitude, longitude) value ('Vladivostok',	43.134019,	131.928379);
INSERT INTO CITY(name, latitude, longitude) value ('Vladimir',	56.129042,	40.40703 );
INSERT INTO CITY(name, latitude, longitude) value ('Vlogograd',	48.707103,	44.516939 );
INSERT INTO CITY(name, latitude, longitude) value ('Ekatirinburg',	56.838002,	60.597295);
INSERT INTO CITY(name, latitude, longitude) value ('Ivanovo',	57.000348,	40.973921);
INSERT INTO CITY(name, latitude, longitude) value ('Izhevsk',	56.852775,	53.211463 );
INSERT INTO CITY(name, latitude, longitude) value ('Irkutsk',	52.286387,	104.280);
INSERT INTO CITY(name, latitude, longitude) value ('Kazan\'',	55.795793,	49.106585 );
INSERT INTO CITY(name, latitude, longitude) value ('Kaliningrad', 55.916229,	37.854467);
INSERT INTO CITY(name, latitude, longitude) value ('Kaluga',	54.507014,	36.252277 );
INSERT INTO CITY(name, latitude, longitude) value ('Kirov',	54.079033,	34.323163 );
INSERT INTO CITY(name, latitude, longitude) value ('Moscow',	55.755773,	37.617761);
INSERT INTO CITY(name, latitude, longitude) value ('Murmansk',	68.9695629,	33.07454 );
INSERT INTO CITY(name, latitude, longitude) value ('Novorossiysk',	44.723489,	37.76866 );
INSERT INTO CITY(name, latitude, longitude) value ('Omsk', 54.989342,	73.368212  );
INSERT INTO CITY(name, latitude, longitude) value ('Orel	', 52.970306,	36.063514 );
INSERT INTO CITY(name, latitude, longitude) value ('Orenburg',	51.76806,	55.097449 );
INSERT INTO CITY(name, latitude, longitude) value ('Penza',	53.194546,	45.019529 );
INSERT INTO CITY(name, latitude, longitude) value ('Pervoural\'sk',	56.908099,	59.942935  );
INSERT INTO CITY(name, latitude, longitude) value ('Pskov',	57.819365,	28.331786 );
INSERT INTO CITY(name, latitude, longitude) value ('Rostov-na-Donu',	47.227151,	39.744972 );
INSERT INTO CITY(name, latitude, longitude) value ('Ribinsk',	58.13853,	38.573586 );
INSERT INTO CITY(name, latitude, longitude) value ('Samara',	53.195533,	50.101801 	);
INSERT INTO CITY(name, latitude, longitude) value ('Saint-Petersburg',	59.938806,	30.314278 );
INSERT INTO CITY(name, latitude, longitude) value ('Saratov',	51.531528,	46.03582 );
INSERT INTO CITY(name, latitude, longitude) value ('Sochi', 43.581509,	39.722882);
INSERT INTO CITY(name, latitude, longitude) value ('Stavropol\'',	45.044502,	41.969065 );
INSERT INTO CITY(name, latitude, longitude) value ('Tambov',	52.721246,	41.452238 );
INSERT INTO CITY(name, latitude, longitude) value ('Tver\'',	56.859611,	35.911896 );
INSERT INTO CITY(name, latitude, longitude) value ('Tomsk',	56.495116,	84.972128);
INSERT INTO CITY(name, latitude, longitude) value ('Ul\'yanovsk',	54.317002,	48.402243 );
INSERT INTO CITY(name, latitude, longitude) value ('Ufa',	54.734768,	55.957838 );
INSERT INTO CITY(name, latitude, longitude) value ('Chebocksari',	56.1439,	47.248887);
INSERT INTO CITY(name, latitude, longitude) value ('Shahti',	47.708485,	40.215958);
INSERT INTO CITY(name, latitude, longitude) value ('Yaroslavl\'',	57.626569,	39.893822);
















