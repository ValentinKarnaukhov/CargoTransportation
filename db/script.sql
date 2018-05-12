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



create table TRUCK(
  truck_id BIGINT NOT NULL AUTO_INCREMENT,
  reg_number VARCHAR(30) NOT NULL,
  max_drivers INT(30) NOT NULL,
  capacity INT(11) NOT NULL,
  status ENUM('OK','BROKEN') DEFAULT 'OK' NOT NULL,
  city_id BIGINT NOT NULL,
  enabled BOOLEAN,
  PRIMARY KEY (truck_id),
  UNIQUE (reg_number),
  FOREIGN KEY (city_id) REFERENCES CITY(city_id)
);

create table ORDERS(
  order_id BIGINT NOT NULL AUTO_INCREMENT,
  is_complete BOOLEAN NOT NULL,
  truck_id BIGINT,
  PRIMARY KEY (order_id),
  FOREIGN KEY (truck_id) REFERENCES TRUCK(truck_id)
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
  order_id BIGINT,
  user_id BIGINT NOT NULL,
  start DATETIME,
  PRIMARY KEY (driver_id),
  UNIQUE (personal_code),
  FOREIGN KEY (city_id) REFERENCES CITY(city_id),
  FOREIGN KEY (truck_id) REFERENCES TRUCK(truck_id),
  FOREIGN KEY (order_id) REFERENCES ORDERS(order_id),
  FOREIGN KEY (user_id) REFERENCES USER(user_id)
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

INSERT INTO CITY(name, latitude, longitude) value ('Абакан',	53.720976,	91.44242300000001);
INSERT INTO CITY(name, latitude, longitude) value ('Архангельск',	64.539304,	40.518735);
INSERT INTO CITY(name, latitude, longitude) value ('Астана',	71.430564,	51.128422);
INSERT INTO CITY(name, latitude, longitude) value ('Барнаул',	53.356132,	83.74961999999999);
INSERT INTO CITY(name, latitude, longitude) value ('Белгород',	50.597467,	36.588849);
INSERT INTO CITY(name, latitude, longitude) value ('Бийск',	 52.541444,	85.219686 );
INSERT INTO CITY(name, latitude, longitude) value ('Бишкек',	42.871027,	74.59452 );
INSERT INTO CITY(name, latitude, longitude) value ('Благовещенск',	50.290658,	127.527173 );
INSERT INTO CITY(name, latitude, longitude) value ('Братск',	56.151382,	101.634152  );
INSERT INTO CITY(name, latitude, longitude) value ('Брянск',	53.2434,	34.364198 );
INSERT INTO CITY(name, latitude, longitude) value ('Великий Новгород',	58.521475,	31.275475);
INSERT INTO CITY(name, latitude, longitude) value ('Владивосток',	43.134019,	131.928379);
INSERT INTO CITY(name, latitude, longitude) value ('Владимир',	56.129042,	40.40703 );
INSERT INTO CITY(name, latitude, longitude) value ('Волгоград',	48.707103,	44.516939 );
INSERT INTO CITY(name, latitude, longitude) value ('Екатеринбург',	56.838002,	60.597295);
INSERT INTO CITY(name, latitude, longitude) value ('Иваново',	57.000348,	40.973921);
INSERT INTO CITY(name, latitude, longitude) value ('Ижевск',	56.852775,	53.211463 );
INSERT INTO CITY(name, latitude, longitude) value ('Иркутск',	52.286387,	104.280);
INSERT INTO CITY(name, latitude, longitude) value ('Казань',	55.795793,	49.106585 );
INSERT INTO CITY(name, latitude, longitude) value ('Калининград', 55.916229,	37.854467);
INSERT INTO CITY(name, latitude, longitude) value ('Калуга',	54.507014,	36.252277 );
INSERT INTO CITY(name, latitude, longitude) value ('Киров',	54.079033,	34.323163 );
INSERT INTO CITY(name, latitude, longitude) value ('Москва',	55.755773,	37.617761);
INSERT INTO CITY(name, latitude, longitude) value ('Мурманск',	68.9695629,	33.07454 );
INSERT INTO CITY(name, latitude, longitude) value ('Новороссийск',	44.723489,	37.76866 );
INSERT INTO CITY(name, latitude, longitude) value ('Омск	', 54.989342,	73.368212  );
INSERT INTO CITY(name, latitude, longitude) value ('Орел	', 52.970306,	36.063514 );
INSERT INTO CITY(name, latitude, longitude) value ('Оренбург',	51.76806,	55.097449 );
INSERT INTO CITY(name, latitude, longitude) value ('Пенза',	53.194546,	45.019529 );
INSERT INTO CITY(name, latitude, longitude) value ('Первоуральск',	56.908099,	59.942935  );
INSERT INTO CITY(name, latitude, longitude) value ('Псков',	57.819365,	28.331786 );
INSERT INTO CITY(name, latitude, longitude) value ('Ростов-на-Дону',	47.227151,	39.744972 );
INSERT INTO CITY(name, latitude, longitude) value ('Рыбинск',	58.13853,	38.573586 );
INSERT INTO CITY(name, latitude, longitude) value ('Самара',	53.195533,	50.101801 	);
INSERT INTO CITY(name, latitude, longitude) value ('Санкт-Петербург',	59.938806,	30.314278 );
INSERT INTO CITY(name, latitude, longitude) value ('Саратов',	51.531528,	46.03582 );
INSERT INTO CITY(name, latitude, longitude) value ('Сочи	', 43.581509,	39.722882);
INSERT INTO CITY(name, latitude, longitude) value ('Ставрополь',	45.044502,	41.969065 );
INSERT INTO CITY(name, latitude, longitude) value ('Тамбов',	52.721246,	41.452238 );
INSERT INTO CITY(name, latitude, longitude) value ('Тверь',	56.859611,	35.911896 );
INSERT INTO CITY(name, latitude, longitude) value ('Томск',	56.495116,	84.972128);
INSERT INTO CITY(name, latitude, longitude) value ('Ульяновск',	54.317002,	48.402243 );
INSERT INTO CITY(name, latitude, longitude) value ('Уфа',	54.734768,	55.957838 );
INSERT INTO CITY(name, latitude, longitude) value ('Чебоксары',	56.1439,	47.248887);
INSERT INTO CITY(name, latitude, longitude) value ('Шахты',	47.708485,	40.215958);
INSERT INTO CITY(name, latitude, longitude) value ('Ярославль',	57.626569,	39.893822);
















