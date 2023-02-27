CREATE TABLE animais (
    name varchar(20),
    especie varchar(20),
    endereco varchar(20),
    descricao varchar(20),
    filhote boolean,
    status varchar(20),
    id int PRIMARY KEY,
    img varchar(20)
);

CREATE TABLE users (
    name varchar(20),
    cpf varchar(11) PRIMARY KEY,
    senha varchar(20),
    email varchar(20)
);

CREATE TABLE veterinarios (
    name varchar(20),
    cpf varchar(11) PRIMARY KEY,
    senha varchar(20),
    email varchar(20)
);

CREATE TABLE colaboradores (
    name varchar(20),
    cpf varchar(11) PRIMARY KEY,
    senha varchar(20),
    email varchar(20)
);

CREATE SEQUENCE table_animal_id_seq;

ALTER TABLE animais
    ALTER id set NOT NULL,
    ALTER id set DEFAULT nextval('table_animal_id_seq');

ALTER SEQUENCE table_animal_id_seq owned by animais.id;
