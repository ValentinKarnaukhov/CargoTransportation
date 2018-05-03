DROP TABLE IF EXISTS DRIVER;
DROP TABLE IF EXISTS ORDER_WAYPOINT;
DROP TABLE IF EXISTS CARGO;
DROP TABLE IF EXISTS ORDERS;
DROP TABLE IF EXISTS TRUCK;
DROP TABLE IF EXISTS CITY;
DROP TABLE IF EXISTS USER;

create table USER (
  user_id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(30) NOT NULL,
  email VARCHAR(30) NOT NULL,
  password VARCHAR(100) NOT NULL,
  role ENUM('ADMIN','MANAGER','DRIVER') NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT TRUE ,
  PRIMARY KEY (user_id),
  UNIQUE (email)
);

create table CITY(
  city_id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  PRIMARY KEY (city_id),
  UNIQUE (name)
);

create table TRUCK(
  truck_id BIGINT NOT NULL AUTO_INCREMENT,
  reg_number VARCHAR(30) NOT NULL,
  max_drivers INT(30) NOT NULL,
  capacity INT(11) NOT NULL,
  status ENUM('OK','BROKEN') NOT NULL,
  city_id BIGINT NOT NULL,
  PRIMARY KEY (truck_id),
  UNIQUE (reg_number),
  FOREIGN KEY (city_id) REFERENCES CITY(city_id)
);

create table ORDERS(
  order_id BIGINT NOT NULL AUTO_INCREMENT,
  truck_id BIGINT NOT NULL,
  is_complete BOOLEAN NOT NULL,
  begin DATETIME NOT NULL,
  ended DATETIME NOT NULL ,
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
  status ENUM('REST','WORD','DRIVE') NOT NULL ,
  city_id BIGINT NOT NULL,
  truck_id BIGINT,
  order_id BIGINT,
  user_id BIGINT NOT NULL,
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



