CREATE role master WITH password 'pass';

CREATE database users_db WITH owner master;

ALTER ROLE master WITH LOGIN;

GRANT ALL PRIVILEGES ON DATABASE users_db TO master;

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE users (
    id integer DEFAULT nextval('users_id_seq'::regclass) NOT NULL PRIMARY KEY,
    name VARCHAR (255) NOT NULL,
    age integer
);

INSERT INTO users (name, age) VALUES ('Martin', 33);
INSERT INTO users (name, age) VALUES ('John', 20);
INSERT INTO users (name, age) VALUES ('Megan', 10);
INSERT INTO users (name, age) VALUES ('Mark', 4);
INSERT INTO users (name, age) VALUES ('Dominic', 17);
INSERT INTO users (name, age) VALUES ('Anna', 87);
INSERT INTO users (name, age) VALUES ('Jane', 44);
INSERT INTO users (name, age) VALUES ('Barbara', 18);
INSERT INTO users (name, age) VALUES ('Leo', 40);


