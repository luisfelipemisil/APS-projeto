create table users (
  cpf varchar(50),
  senha varchar(50),
  nome varchar(50),
  email varchar(50) primary key,
  cartao_numero varchar(50),
  cartao_validade varchar(50),
  cartao_cod varchar(50)
);


create table pedidos (
  id serial primary key,
  status varchar(50),
  idade int,
  raca varchar(50),
  gato boolean,
  cachorro boolean,
  filhote boolean,
  endereco varchar(50),
  descricao varchar(50),
  cliente_email varchar(50),
  cliente_nome varchar(50)
);

insert into users values
('1234', '1234','maria', 'maria@irias.com.br', '1','1','1');




