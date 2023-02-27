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

CREATE SEQUENCE table_animal_id_seq;

ALTER TABLE animais
    ALTER id set NOT NULL,
    ALTER id set DEFAULT nextval('table_animal_id_seq');

ALTER SEQUENCE table_animal_id_seq owned by animais.id;
