-- Insertes para testes do projeto MULA CAR

-- Tabela de usuários

CREATE TABLE usuario (
    usu_id SERIAL PRIMARY KEY,
    usu_nome VARCHAR(60) not null,
    usu_cpf VARCHAR(15) not null constraint usu_cpf_unico unique,
    usu_email VARCHAR(60) not null constraint usu_email_unico unique,
    usu_senha VARCHAR(10) not null,
    usu_perfil VARCHAR(20) not null,
    usu_data_cadastro DATE not null,
    usu_status VARCHAR(20) not null
);

INSERT INTO usuario 
( usu_nome, usu_cpf, usu_email, usu_senha, usu_perfil, usu_data_cadastro, usu_status)
VALUES
( 'Rogerio Reis ADS3', '64066522076', 'rogerio@gmail.com', '123456','ADMINISTRADOR', '2020-11-10', 'ATIVO'),
( 'Gabriel Cunha ADS3', '82916982019', 'gabriel@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-10', 'ATIVO'),
( 'Aires Ribeiro ADS3', '10464305055', 'aires@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-06', 'ATIVO'),
( 'Lucas França ADS3', '20860026027', 'lucas@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-10', 'ATIVO'),
( 'Jose Luiz Senai', '63591997005', 'jose@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-06', 'ATIVO'),
( 'Halley Wesley Senai', '33447265876', 'halley@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-10', 'ATIVO'),
( 'Eugenio Messala Senai', '07403428609', 'eugenio@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-10', 'ATIVO'),
( 'Heuber Senai', '49707802260', 'heuber@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-06', 'ATIVO'),
( 'Katia Forvile Senai', '23766661396', 'katia@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-10', 'ATIVO'),
( 'Bete Tie Senai', '69520032070', 'bete@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-10', 'ATIVO');

-- Tabela de marcas de veículos

CREATE TABLE marca (
    mar_id SERIAL PRIMARY KEY,
    mar_nome VARCHAR(60) NOT NULL CONSTRAINT marca_unica UNIQUE,
    mar_status VARCHAR(20) NOT NULL
);

INSERT INTO marca
(mar_nome, mar_status)
VALUES
('chevrolet','INATIVO').
	('volkswagen','INATIVO'),
	('fiat','INATIVO'),
	('renault',	'INATIVO'),
	('ford',	'INATIVO'),
	('toyota','INATIVO'),
	('hyundai',	'INATIVO'),
	('jeep','INATIVO'),
	('suzuki',	'INATIVO'),
	('mercedes_bens','INATIVO'),
	('audi','INATIVO'),
	('ferrari','INATIVO'),
	('volvo','INATIVO'),
	('kia','INATIVO'),
	('chery','INATIVO'),
	('lifan','INATIVO'),
	('subaru','INATIVO'),
	('nissan','INATIVO'),
	('peugeot','INATIVO'),
    ('alfa_romeu','INATIVO'),
	('mitsubishi','INATIVO'),
	('jac','INATIVO'),
	('citroen','INATIVO'),
	('rolls_royce','INATIVO'),
	('bentley','INATIVO'),
	('troller','INATIVO'),
	('ram','INATIVO'),
	('massarati','INATIVO'),
	('porshe','INATIVO'),
	('jaguar','INATIVO'),
	('land_rover','INATIVO'),
	('honda','INATIVO');

-- Tabela de modelos de veículos 
CREATE TABLE modelo (
    mod_id serial PRIMARY KEY,
    mod_nome varchar(60) NOT NULL CONSTRAINT modelo_unico UNIQUE,
    mod_status varchar(20) NOT NULL, 
    mod_marca_id integer NOT NULL,
    FOREIGN KEY (mod_marca_id) REFERENCES marca (mar_id)
);

INSERT INTO modelo 
( mod_nome, mod_status, mod_marca_id)
VALUES
('uno','INATIVO',5),
('palio','INATIVO',5),
('fox','INATIVO',4),
('cross_Fox','INATIVO',4),
('siena','INATIVO',5),
('celta','INATIVO',3),
('voyage','INATIVO',4),
('HB20','INATIVO',9),
('corsa','INATIVO',4),
('onix','INATIVO',3),
('sandero','INATIVO',6),
('fiesta','INATIVO',7),
('cobalt','INATIVO',3),
('ka','INATIVO',7),
('corolla','INATIVO',8),
('civic','INATIVO',34),
('punto','INATIVO',5),
('fit','INATIVO',34),
('spin','INATIVO',3),
('C3','INATIVO',25),
('cruze','INATIVO',3),
('logan','INATIVO',6),
('agile','INATIVO',3),
('city','INATIVO',34),
('idea','INATIVO',5),
('march','INATIVO',20),
('space_fox','INATIVO',4),
('focus','INATIVO',7),
('santa_fe','INATIVO',9),
('captiva','INATIVO',3),
('versa','INATIVO',20),
('equinox','INATIVO',3),
('tracker','INATIVO',3),
('trailblazer','INATIVO',3),
('camaro','INATIVO',3),
('prisma','INATIVO',3);

-- insert de categoria de veículo
CREATE TABLE categoria (
    cat_id serial PRIMARY KEY,
    cat_nome varchar(60)NOT NULL CONSTRAINT categoria_unica UNIQUE,
    cat_valor numeric(7,2) NOT NULL CONSTRAINT valor_valido CHECK (cat_valor > 0),
    cat_status varchar(20) NOT NULL
);

INSERT INTO categoria 
(cat_nome, cat_valor, cat_status)
VALUES
('ECONOMICO', 70.00, 'ATIVO'),
('EXECUTIVO', 160.00, 'ATIVO'),
('INTERMEDIARIO', 90.00, 'ATIVO'),
('SUV', 120.00, 'ATIVO'),
('UTILITARIO', 200.00, 'ATIVO');

-- insert de cliente
CREATE TABLE cliente (
    cli_id SERIAL PRIMARY KEY,
    cli_razao_social VARCHAR(100),
    cli_nome_fantasia VARCHAR(100),
    cli_nome VARCHAR (100),
    cli_status VARCHAR(20) not null,
    cli_cpf_cnpj VARCHAR(14) not null constraint cli_cpf_cnpj_unico unique,
    cli_rg VARCHAR(20) constraint cli_rg_unico unique,
    cli_rg_orgao_emissor VARCHAR(6) ,
    cli_tipo VARCHAR(20) not null
);

INSERT INTO cliente(
cli_razao_social, 
cli_nome_fantasia,
cli_nome, 
cli_status,
cli_cpf_cnpj,
cli_rg,
cli_rg_orgao_emissor,
cli_tipo)
VALUES
( null,null,'Rogerio Tadeu dos Reis','ATIVO','64066522076','21244108','ssp_sp','PESSOA_FISICA'),
( null,null,'Gabriel Cunha ADS3','ATIVO','82916982019','11222333','ssp_rs','pessoa_fisica'),
( null,null,'Aires Ribeiro ADS3','ATIVO','10464305055','11333444','ssp_go','PESSOA_FISICA'),
( null,null,'Lucas França ADS3','ATIVO','20860026027','11444555','ssp_go','PESSOA_FISICA'),
( null,null,'Jose Luiz Senai','ATIVO','63591997005','11555666','ssp_go','PESSOA_FISICA'),
( null,null,'Halley Wesley Senai','ATIVO','33447265876','11666777','ssp_go','PESSOA_FISICA'),
( null,null,'Eugenio Messala Senai','ATIVO','07403428609','11777888','ssp_go','PESSOA_FISICA'),
( null,null,'Heuber Senai','ATIVO','49707802260','11888999','ssp_go','PESSOA_FISICA'),
( null,null,'Katia Forvile Senai','ATIVO','23766661396','11999000','ssp_sp','PESSOA_FISICA'),
( null,null,'Bete Tie Senai','ATIVO','69520032070','22111222','ssp_go','PESSOA_FISICA');
( 'Rogerio Tadeu dos Reis ME','SOFTWARE & SOLUCOES', null,'ATIVO','36912128000179',null,null,'PESSOA_JURIDICA'),
( 'Gabriel Cunha ADS3 LTDA','SOFTWARE EVOLUTION', null,'ATIVO','00252301000102',null,null,'PESSOA_JURIDICA'),
( 'Aires Ribeiro ADS3 SA','ARIB IMOVEIS', null,'ATIVO','71863172000137',null,null,'PESSOA_JURIDICA'),
( 'Lucas França ADS3 LTDA','SOFTWARE & BIOLOGIA', null,'ATIVO','93342637000147',null,null,'PESSOA_JURIDICA'),
( 'Jose Luiz Senai SA','ALGORITMOS COM ALTO DESEMPENHO', null,'ATIVO','14207420000158',null,null,'PESSOA_JURIDICA'),
( 'Halley Wesley Senai LTDA','SOLUCOES EM MBD', null,'ATIVO','95362055000158',null,null,'PESSOA_JURIDICA'),
( 'Eugenio Messala Senai SA','PROGRAMANDO O FURUTO', null,'ATIVO','59511926000194',null,null,'PESSOA_JURIDICA'),
( 'Heuber Senai SA','GESTAO & SOFTWARE', null,'ATIVO','92705147000103',null,null,'PESSOA_JURIDICA'),
( 'Katia Forvile Senai LTDA','SKIL & SOFTWARE', null,'ATIVO','71828437000166',null,null,'PESSOA_JURIDICA'),
( 'Bete Tie Senai SA','SOFTWARE & TESTES', null,'ATIVO','94346804000190',null,null,'PESSOA_JURIDICA');

-- insert de motoristas

CREATE TABLE motorista (
    mot_id SERIAL PRIMARY KEY,
    mot_nome VARCHAR(60) not null,
    mot_cpf VARCHAR(11) not null constraint mot_cpf_unico unique,
    mot_rg VARCHAR(20) not null constraint mot_rg_unico unique,
    mot_rg_orgao_emissor VARCHAR(6) not null,
    mot_cnh_numero VARCHAR(20) not null constraint cnh_unica unique,
    mot_cnh_data_validade DATE not null constraint cnh_data_validade_valida check(mot_cnh_data_validade > current_date),
    mot_cnh_imagem VARCHAR(100) not null ,
    mot_cnh_categoria VARCHAR(5) not null
);

INSERT INTO motorista (
mot_nome, mot_cpf, mot_rg, mot_rg_orgao_emissor, mot_cnh_numero, mot_cnh_data_validade, mot_cnh_imagem,
mot_cnh_categoria)
VALUES
('Rogerio','64066522076','21244108','ssp_sp','12345678901','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','AC'),
 ('Gabriel','82916982019','11222333','ssp_rs','12345678902','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','AC'),
 ('Aires','10464305055','11333444','ssp_rs','12345678903','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','AC'),
('Lucas','20860026027','11444555','ssp_rs','12345678904','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','AC');


-- insert contatos

CREATE TABLE contato (
    con_id SERIAL PRIMARY KEY,
    con_tipo VARCHAR(20) not null,
    con_telefone VARCHAR(11) not null,
    con_email VARCHAR(60) not null constraint cont_mail_unico unique,
    con_cliente_id INTEGER ,
    con_motorista_id INTEGER ,
	foreign key (con_cliente_id) references cliente (cli_id),
    foreign key (con_motorista_id) references motorista (mot_id)    
);

INSERT INTO contato 
( con_tipo, con_telefone, con_email, con_cliente_id, con_motorista_id)
VALUES 
('RESIDENCIAL','62985915530', 'rogerio@gmail.com',51, null),
('COMERCIAL','62985915531', 'gabriel@gmail.com',52, null),
('RESIDENCIAL','62985915532', 'aires@gmail.com',53, null),
('COMERCIAL','62985915533', 'lucas@gmail.com',54, null),
('RESIDENCIAL','62985915534', 'joseluiz@gmail.com',55, null),
('COMERCIAL','62985915535', 'halley@gmail.com',56, null),
('RESIDENCIAL','62985915536', 'hauber@gmail.com',58, null),
('COMERCIAL','62985915537', 'eugenio@gmail.com',57, null),
('RESIDENCIAL','62985915538', 'katia@gmail.com',59, null),
('COMERCIAL','62985915539', 'bete@gmail.com',60, null),
('RESIDENCIAL','62985915530', 'rogerio.mot@gmail.com',null, 5),
('COMERCIAL','62985915531', 'gabriel.mot@gmail.com',null, 6),
('RESIDENCIAL','62985915532', 'aires.mot@gmail.com',null, 7),
('COMERCIAL','62985915533', 'lucas.mot@gmail.com',null, 8);



















