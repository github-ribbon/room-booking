CREATE TABLE campus (
  campus_id character varying(10) NOT NULL,
  name character varying(50) NOT NULL,
  description character varying(300),
  CONSTRAINT campus_pkey PRIMARY KEY (campus_id)
);

CREATE TABLE building (
  campus_id character varying(10) NOT NULL,
  building_id character varying(10) NOT NULL,
  name character varying(50) NOT NULL,
  description character varying(300),  
  CONSTRAINT building_pkey PRIMARY KEY (campus_id, building_id),
  CONSTRAINT fk_building_campus_id FOREIGN KEY (campus_id)
      REFERENCES campus (campus_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE room (  
  campus_id character varying(10) NOT NULL,
  building_id character varying(10) NOT NULL,
  room_id character varying(10) NOT NULL,
  description character varying(300),
  CONSTRAINT room_pkey PRIMARY KEY (room_id, campus_id, building_id),
  CONSTRAINT fk_room_campus_id FOREIGN KEY (campus_id, building_id)
      REFERENCES building (campus_id, building_id)
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE room_attribute_type (
  room_attribute_type_id character varying(50) NOT NULL,
  description character varying(300),
  CONSTRAINT room_attribute_type_pkey PRIMARY KEY (room_attribute_type_id)
);



CREATE TABLE room_attribute (    
  campus_id character varying(255) NOT NULL,
  building_id character varying(10) NOT NULL,
  room_id character varying(10) NOT NULL,
  room_attribute_type_id character varying(50) NOT NULL,
  value character varying(50) NOT NULL,
  CONSTRAINT room_attribute_pkey PRIMARY KEY (room_id, room_attribute_type_id, 
  campus_id, building_id),
  CONSTRAINT fk_room_attribute_room_attribute_type_id FOREIGN KEY (room_attribute_type_id)
      REFERENCES room_attribute_type (room_attribute_type_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_room_attribute_room_id FOREIGN KEY (room_id, campus_id, building_id)
      REFERENCES room (room_id, campus_id, building_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE bookable_room (
  campus_id character varying(10) NOT NULL,
  building_id character varying(10) NOT NULL,
  room_id character varying(10) NOT NULL,
  is_robot boolean NOT NULL,
  CONSTRAINT bookable_room_pkey PRIMARY KEY (room_id, campus_id, building_id),
  CONSTRAINT fk_bookable_room_room_id FOREIGN KEY (room_id, campus_id, building_id)
      REFERENCES room (room_id, campus_id, building_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE person (
  person_id integer NOT NULL,
  family_name character varying(50) NOT NULL,
  given_name character varying(50) NOT NULL,
  CONSTRAINT person_pkey PRIMARY KEY (person_id)
);

CREATE TABLE person_group (
  person_group_id character varying(50) NOT NULL,
  description character varying(300),
  CONSTRAINT person_group_pkey PRIMARY KEY (person_group_id)
);

CREATE TABLE person_group_person (
  person_group_id character varying(50) NOT NULL,
  person_id integer NOT NULL,
  CONSTRAINT person_group_person_pkey PRIMARY KEY (person_group_id, person_id),
  CONSTRAINT fk_person_group_person_person_group_id FOREIGN KEY (person_group_id)
      REFERENCES person_group (person_group_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_person_group_person_person_id FOREIGN KEY (person_id)
      REFERENCES person (person_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE usr (
 usr_id character varying(50) NOT NULL,
  email character varying(50) NOT NULL,
  is_enabled boolean NOT NULL,
  password character varying(50) NOT NULL,
  person_id integer,
    CONSTRAINT usr_pkey PRIMARY KEY (usr_id),
  CONSTRAINT fk_usr_person_id FOREIGN KEY (person_id)
      REFERENCES person (person_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT usr_email_key UNIQUE (email)
);

CREATE TABLE usr_group (
  usr_group_id character varying(50) NOT NULL,
  description character varying(300),  
  CONSTRAINT usr_group_pkey PRIMARY KEY (usr_group_id)
);

CREATE TABLE usr_group_usr (
  usr_id character varying(50) NOT NULL,
  usr_group_id character varying(50) NOT NULL,
  CONSTRAINT usr_group_usr_pkey PRIMARY KEY (usr_id, usr_group_id),
  CONSTRAINT fk_usr_group_usr_usr_group_id FOREIGN KEY (usr_group_id)
      REFERENCES usr_group (usr_group_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_usr_group_usr_usr_id FOREIGN KEY (usr_id)
      REFERENCES usr (usr_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE authority (
  authority_id character varying(20) NOT NULL,
  CONSTRAINT authority_pkey PRIMARY KEY (authority_id)
);

CREATE TABLE usr_group_auth (
  authority_id character varying(20) NOT NULL,
  usr_group_id character varying(50) NOT NULL,
  CONSTRAINT usr_group_auth_pkey PRIMARY KEY (authority_id, usr_group_id),
  CONSTRAINT fk_usr_group_auth_authority_id FOREIGN KEY (authority_id)
      REFERENCES authority (authority_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_usr_group_auth_usr_group_id FOREIGN KEY (usr_group_id)
      REFERENCES usr_group (usr_group_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE room_manager(  
  campus_id character varying(10) NOT NULL,
  building_id character varying(10) NOT NULL,
  room_id character varying(10) NOT NULL,
  usr_group_id character varying(50) NOT NULL,
  CONSTRAINT room_manager_pkey PRIMARY KEY (room_id, usr_group_id, campus_id, building_id),
  CONSTRAINT fk_room_manager_room_id FOREIGN KEY (room_id, campus_id, building_id)
      REFERENCES bookable_room (room_id, campus_id, building_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_room_manager_usr_group_id FOREIGN KEY (usr_group_id)
      REFERENCES usr_group (usr_group_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE db_status (
  db_status_id character varying(1) NOT NULL,
  CONSTRAINT db_status_pkey PRIMARY KEY (db_status_id)
);

CREATE TABLE reservation (
  reservation_id integer NOT NULL,
  campus_id character varying(10) NOT NULL,
  building_id character varying(10) NOT NULL,
  room_id character varying(10) NOT NULL,
  created timestamp NOT NULL,
  modified timestamp NOT NULL,
  db_status_id character varying(1) NOT NULL,
  person_group_id character varying(50) NOT NULL,
  purpose character varying(300),
  reservation_date timestamp NOT NULL,
  reservation_parent_id integer,
  time_from timestamp NOT NULL,
  time_to timestamp NOT NULL,
  usr_id character varying(50) NOT NULL,
  CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id),
  CONSTRAINT fk_reservation_db_status_id FOREIGN KEY (db_status_id)
      REFERENCES db_status (db_status_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_reservation_person_group_id FOREIGN KEY (person_group_id)
      REFERENCES person_group (person_group_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_reservation_reservation_parent_id FOREIGN KEY (reservation_parent_id)
      REFERENCES reservation (reservation_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_reservation_room_id FOREIGN KEY (room_id, campus_id, building_id)
      REFERENCES bookable_room (room_id, campus_id, building_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_reservation_usr_id FOREIGN KEY (usr_id)
      REFERENCES usr (usr_id) 
      ON UPDATE NO ACTION ON DELETE NO ACTION
);








CREATE SEQUENCE person_seq
  INCREMENT 1
  MAXVALUE 9223372036854775807
  START 10000;

CREATE SEQUENCE reservation_seq
  INCREMENT 1
  MAXVALUE 9223372036854775807
  START 10000;
