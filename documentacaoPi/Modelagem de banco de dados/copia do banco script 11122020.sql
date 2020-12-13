
CREATE TABLE marca (
    mar_id SERIAL PRIMARY KEY,
    mar_nome VARCHAR(60) NOT NULL CONSTRAINT  nome_unico UNIQUE ,
    mar_status VARCHAR(20) NOT NULL  
);

create table modelo (
    mod_id serial primary key,
    mod_nome varchar(60) not null constraint modelo_unico unique,
    mod_status varchar(20),
    mod_marca_id integer not null,
    foreign key (mod_marca_id) references marca (mar_id)
);
ALTER TABLE modelo DROP COLUMN mod_status;
ALTER TABLE modelo ADD COLUMN mod_status varchar(20); 
SELECT * FROM modelo m2 

CREATE TABLE veiculo (
    vei_id serial PRIMARY KEY,
    vei_placa varchar(7) not null constraint placa_unica unique,
    vei_ano_fabricacao integer not null constraint ano_fabricacao_valido check (vei_ano_fabricacao >= 1886),
    vei_ano_modelo integer not null constraint ano_modelo_valido check (vei_ano_modelo >= 1886),                                                                         
    vei_tipo_combustivel varchar(20) not null,
    vei_renavan char(11) not null constraint renavan_unico unique,
    vei_preco_compra NUMERIC(7,2)NOT NULL CONSTRAINT valor_compra_valido CHECK (vei_preco_compra > 0),
    vei_preco_venda NUMERIC(7,2)NOT NULL CONSTRAINT valor_venda_valido CHECK (vei_preco_venda > 0),
    vei_tipo varchar (20) not null, --Tipo - somente os tipos: hatch, sedan, SUV e pickup
    vei_status varchar (20) not null,
    vei_num_passageiro integer not null constraint num_passageiros check (vei_num_passageiro > 0 ),
    vei_km BIGINT not null constraint km_valido check(vei_km > 0),
    vei_categoria_id INTEGER not null,
    vei_modelo_id INTEGER not null,
    foreign key (vei_categoria_id) references categoria (cat_id),
    foreign key (vei_modelo_id) references modelo (mod_id)
);
ALTER TABLE veiculo ADD COLUMN vei_situacao varchar(20)NOT NULL
ALTER TABLE veiculo ALTER COLUMN vei_preco_compra TYPE NUMERIC (8,2);
ALTER TABLE veiculo ALTER COLUMN vei_preco_venda TYPE NUMERIC (8,2);
SELECT * FROM veiculo v 


CREATE TABLE usuario (
    usu_id SERIAL PRIMARY KEY,
    usu_nome VARCHAR(60) not null,
    usu_cpf CHAR(11) not null constraint usu_cpf_unico unique,
    usu_email VARCHAR(60) not null constraint usu_email_unico unique,
    usu_senha VARCHAR(10) not null,
    usu_perfil VARCHAR(20) not null,
    usu_data_cadastro DATE not null,
    usu_status VARCHAR(20) not null
);
ALTER TABLE usuario DROP COLUMN usu_cpf;
ALTER TABLE usuario ADD COLUMN usu_cpf varchar(15) CONSTRAINT usu_cpf_unico UNIQUE; 
SELECT * FROM usuario u 

CREATE TABLE cliente (
    cli_id SERIAL PRIMARY KEY,
    cli_razao_social VARCHAR(100),
    cli_nome_fantasia VARCHAR(100),
    cli_nome VARCHAR (100),
    cli_status VARCHAR(20) not null,
    cli_cpf_cnpj VARCHAR(14) not null constraint cli_cpf_cnpj_unico unique,
    cli_rg VARCHAR(20) not null constraint cli_rg_unico unique,
    cli_rg_orgao_emissor VARCHAR(6) not null,
    cli_tipo VARCHAR(20) not null
);
ALTER TABLE cliente ALTER COLUMN cli_rg DROP NOT NULL; 
ALTER TABLE cliente ALTER COLUMN cli_rg_orgao_emissor DROP NOT NULL; 

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
ALTER TABLE motorista ADD COLUMN mot_status varchar(20); 


CREATE TABLE endereco (
    end_id SERIAL PRIMARY KEY,
    end_tipo VARCHAR(20) not null,
    end_cep CHAR(8) not null,
    end_rua VARCHAR(60)not null,
    end_numero varchar(60) not null,
    end_complemento VARCHAR(60),
    end_bairro VARCHAR(60) not null,
    end_cidade VARCHAR(60) not null,
    end_uf CHAR(2) not null,
    end_cliente_id INTEGER,
    end_motorista_id INTEGER,
    foreign key (end_cliente_id) references cliente (cli_id),
    foreign key (end_motorista_id) references motorista (mot_id)
);

DROP table



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

CREATE TABLE locacao (
    loc_id SERIAL PRIMARY KEY,
    loc_cliente_id INTEGER NOT NULL,
    loc_motorista_id INTEGER NULL,
    loc_veiculo_id INTEGER NOT NULL,
    loc_usuario_cadastro_id INTEGER NOT NULL,
    loc_valor_multa numeric(7,2) NULL,
    loc_tanque_cheio BOOLEAN NULL,
    loc_data_retirada TIMESTAMP NOT NULL, 
    loc_data_devolucao_prevista TIMESTAMP NULL,
    loc_km_inicial varchar(20) NULL,
    loc_observacoes text NULL,
    loc_valores_acessorios NUMERIC(7,2) NULL,
    loc_valor_locacao NUMERIC(7,2) NOT NULL,
    loc_valor_caucao NUMERIC(7,2) NULL,
    loc_valor_seguro NUMERIC(7,2) NOT NULL,
    loc_status VARCHAR(20) NOT NULL,
    loc_reserva boolean,
    CONSTRAINT fk_locacao_cliente_id FOREIGN KEY (loc_cliente_id) REFERENCES cliente(cli_id),
    CONSTRAINT fk_locacao_motorista_id FOREIGN KEY (loc_motorista_id) REFERENCES motorista(mot_id),
    CONSTRAINT fk_locacao_veiculo_id FOREIGN KEY (loc_veiculo_id) REFERENCES veiculo(vei_id),
    CONSTRAINT fk_locacao_usuario_cadastro_id FOREIGN KEY (loc_usuario_cadastro_id) REFERENCES usuario(usu_id)
);

INSERT INTO locacao
(loc_cliente_id, loc_motorista_id, loc_veiculo_id, loc_usuario_cadastro_id, loc_valor_multa, loc_tanque_cheio,
loc_data_retirada, loc_data_devolucao_prevista, loc_km_inicial, loc_observacoes, loc_valores_acessorios,
loc_valor_locacao, loc_valor_caucao, loc_valor_seguro, loc_status, loc_reserva)
VALUES(54, 5, 9, 24, 50, false, '09/12/2020', '10/12/2020', '123456', 'nenhuma observação', 75, 150, 200, 35, 'ATIVO', false);

SELECT * FROM cliente c 
SELECT * FROM motorista m
SELECT * FROM veiculo v
SELECT * FROM usuario u 
SELECT * FROM locacao l 
SELECT * FROM endereco e 


DROP TABLE contrato;

INSERT INTO usuario 
( usu_nome, usu_cpf, usu_email, usu_senha, usu_perfil, usu_data_cadastro, usu_status) 
VALUES
( 'Rogerio Reis ADS', '64066522076', 'rogerio@gmail.com', '123456','ADMINISTRADOR', '2020-11-10', 'ATIVO'),
( 'Gabriel Cunha ADS', '82916982019', 'gabriel@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-10', 'ATIVO'),
( 'Aires Ribeiro ADS', '10464305055', 'aires@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-06', 'ATIVO'),
( 'Lucas França ADS', '20860026027', 'lucas@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-10', 'ATIVO'),
( 'Jose Luiz Senai', '63591997005', 'jose@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-06', 'ATIVO'),
( 'Halley Wesley Senai', '33447265876', 'halley@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-10', 'ATIVO'),
( 'Eugenio Messala Senai', '07403428609', 'eugenio@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-10', 'ATIVO'),
( 'Heuber Senai', '49707802260', 'heuber@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-06', 'ATIVO'),
( 'Katia Forvile Senai', '23766661396', 'katia@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-10', 'ATIVO'),
( 'Bete Tie Senai', '695.200.320-70', 'bete@gmail.com', '123456', 'ADMINISTRADOR', '2020-11-10', 'ATIVO');

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

SELECT * FROM marca m 

INSERT INTO modelo 
( mod_nome, mod_status, mod_marca_id)
VALUES
('gol','ATIVO',4);

SELECT mar_nome , mod_nome
FROM marca m JOIN modelo m2 ON mod_marca_id = mar_id;

INSERT INTO modelo 
( mod_nome, mod_status, mod_marca_id)
VALUES
('uno','ATIVO',5),
('palio','ATIVO',5),
('fox','ATIVO',4),
('cross_Fox','ATIVO',4),
('siena','ATIVO',5),
('celta','ATIVO',3),
('voyage','ATIVO',4),
('HB20','ATIVO',9),
('corsa','ATIVO',4),
('onix','ATIVO',3),
('sandero','ATIVO',6),
('fiesta','ATIVO',7),
('cobalt','ATIVO',3),
('ka','ATIVO',7),
('corolla','ATIVO',8),
('civic','ATIVO',34),
('punto','ATIVO',5),
('fit','ATIVO',34),
('spin','ATIVO',3),
('C3','ATIVO',25),
('cruze','ATIVO',3),
('logan','ATIVO',6),
('agile','ATIVO',3),
('city','ATIVO',34),
('idea','ATIVO',5),
('march','ATIVO',20),
('space_fox','ATIVO',4),
('focus','ATIVO',7),
('santa_fe','ATIVO',9),
('captiva','ATIVO',3),
('versa','ATIVO',20),
('equinox','ATIVO',3),
('tracker','ATIVO',3),
('trailblazer','ATIVO',3),
('camaro','ATIVO',3),
('prisma','ATIVO',3);
SELECT * FROM modelo m 

ALTER TABLE categoria ADD COLUMN cat_status varchar(30) NOT NULL;


SELECT * FROM categoria c2 
INSERT INTO categoria 
(cat_nome, cat_valor, cat_status)
VALUES
('ECONOMICO', 70.00, 'ATIVO'),
('EXECUTIVO', 160.00, 'ATIVO'),
('INTERMEDIARIO', 90.00, 'ATIVO'),
('SUV', 120.00, 'ATIVO'),
('UTILITARIO', 200.00, 'ATIVO');

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
( '','', 'Rogerio Tadeu dos Reis','ATIVO','64066522076','21244108','ssp_sp','PESSOA_FISICA'),
( '','', 'Gabriel Cunha ADS3','ATIVO','82916982019','11222333','ssp_rs','pessoa_fisica'),
( '','', 'Aires Ribeiro ADS3','ATIVO','10464305055','11333444','ssp_go','PESSOA_FISICA'),
( '','', 'Lucas França ADS3','ATIVO','20860026027','11444555','ssp_go','PESSOA_FISICA'),
( '','', 'Jose Luiz Senai','ATIVO','63591997005','11555666','ssp_go','PESSOA_FISICA'),
( '','', 'Halley Wesley Senai','ATIVO','33447265876','11666777','ssp_go','PESSOA_FISICA'),
( '','', 'Eugenio Messala Senai','ATIVO','07403428609','11777888','ssp_go','PESSOA_FISICA'),
( '','', 'Heuber Senai','ATIVO','49707802260','11888999','ssp_go','PESSOA_FISICA'),
( '','', 'Katia Forvile Senai','ATIVO','23766661396','11999000','ssp_sp','PESSOA_FISICA'),
( '','', 'Bete Tie Senai','ATIVO','69520032070','22111222','ssp_go','PESSOA_FISICA');
SELECT * FROM cliente c


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
( null,null, 'Rogerio Tadeu dos Reis','ATIVO','64066522076','21244108','ssp_sp','PESSOA_FISICA'),
( null,null, 'Gabriel Cunha ADS3','ATIVO','82916982019','11222333','ssp_rs','pessoa_fisica'),
( null,null, 'Aires Ribeiro ADS3','ATIVO','10464305055','11333444','ssp_go','PESSOA_FISICA'),
( null,null, 'Lucas França ADS3','ATIVO','20860026027','11444555','ssp_go','PESSOA_FISICA'),
( null,null, 'Jose Luiz Senai','ATIVO','63591997005','11555666','ssp_go','PESSOA_FISICA'),
( null,null, 'Halley Wesley Senai','ATIVO','33447265876','11666777','ssp_go','PESSOA_FISICA'),
( null,null, 'Eugenio Messala Senai','ATIVO','07403428609','11777888','ssp_go','PESSOA_FISICA'),
( null,null, 'Heuber Senai','ATIVO','49707802260','11888999','ssp_go','PESSOA_FISICA'),
( null,null, 'Katia Forvile Senai','ATIVO','23766661396','11999000','ssp_sp','PESSOA_FISICA'),
( null,null, 'Bete Tie Senai','ATIVO','69520032070','22111222','ssp_go','PESSOA_FISICA'),
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

CREATE TABLE motorista (
	mot_id serial primary key,
	mot_nome varchar(60) NOT NULL,
	mot_cpf varchar(11) NOT NULL,
	mot_rg varchar(20) NOT NULL,
	mot_rg_orgao_emissor varchar(6) NOT NULL,
	mot_cnh_numero varchar(20) NOT NULL,
	mot_cnh_data_validade date NOT NULL,
	mot_cnh_imagem varchar(200) NOT NULL,
	mot_cnh_categoria varchar(5) NOT NULL,
    mot_status varchar(20) NOT NULL,
	CONSTRAINT mot_cnh_data_validade_valida CHECK ((mot_cnh_data_validade > CURRENT_DATE)),
	CONSTRAINT mot_cnh_unica UNIQUE (mot_cnh_numero),
	CONSTRAINT mot_cpf_unico UNIQUE (mot_cpf),
	CONSTRAINT mot_rg_unico UNIQUE (mot_rg)
);

INSERT INTO motorista (
mot_nome, mot_cpf, mot_rg, mot_rg_orgao_emissor, mot_cnh_numero, mot_cnh_data_validade, mot_cnh_imagem,
mot_cnh_categoria, mot_status)
VALUES
('Rogerio','64066522076','21244108','ssp_sp','12345678901','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','CAT_A', 'status'),
 ('Gabriel','82916982019','11222333','ssp_rs','12345678902','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','CAT_B','status'),
 ('Aires','10464305055','11333444','ssp_rs','12345678903','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','CAT_C','status'),
('Lucas','20860026027','11444555','ssp_rs','12345678904','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','CAT_D','status');

SELECT current_date 
DROP CONSTRAINT mot_cnh_data_validade_valida

SELECT * FROM motorista 


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

CREATE TABLE acessorio(
    ace_id serial PRIMARY KEY,
    ace_nome varchar(60) NOT NULL CONSTRAINT acessorio_unico UNIQUE,
    ace_valor numeric(7,2) NOT NULL CONSTRAINT valor_valido CHECK(ace_valor > 0),
    ace_status varchar(20) NOT null
);

-- Inserir dados na tabela de acessórios
INSERT INTO acessorio (ace_nome, ace_valor, ace_status)VALUES
    ('CADEIRINHA', 200.00, 'ATIVO'),
    ('SUPORTE PARA BICICLETA', 250.00, 'ATIVO');
   
CREATE TABLE devolucao(
    dev_id serial NOT NULL,
    dev_locacao_id integer NOT NULL,
    dev_motorista_id integer NOT NULL,
    dev_veiculo_id integer NOT NULL,
    dev_usuario_cadastro_id integer NOT NULL,
    dev_valor_multa numeric(7,2) NOT NULL,
    dev_tanque_cheio boolean NOT NULL,
    dev_data_retirada timestamp NOT NULL,
    dev_data_devolucao_realizada timestamp NULL,
    dev_km_inicial integer NOT NULL,
    dev_km_final integer NULL,
    dev_observacoes text NULL,
    CONSTRAINT dev_km_final_valida CHECK (dev_km_final > locacao (loc_km_inicial)),
    CONSTRAINT locacao_pkey PRIMARY KEY (dev_id),
    CONSTRAINT data_devolucao_realizada_valida CHECK ((dev_data_devolucao_realizada >= locacao.loc_data_retirada)),
    CONSTRAINT valor_multa_valido CHECK (dev_valor_multa >= (0))
);   

SELECT * FROM cliente c2	 
SELECT * FROM motorista m 							
SELECT * FROM categoria c
SELECT * FROM veiculo v 
SELECT * FROM contato c3
SELECT * FROM endereco e2 
SELECT * FROM usuario u 
SELECT * FROM locacao l 
SELECT * FROM acessorio 

SELECT * FROM marca m2 

SELECT * FROM marca WHERE upper(mar_nome) = 'AUDI';

SELECT mar_id FROM marca m2 WHERE upper(mar_nome) = 'AUDI';

SELECT * FROM modelo m2

SELECT * FROM modelo WHERE upper (mod_nome) = 'UNO';

SELECT * FROM veiculo WHERE vei_categoria_id = 2;

SELECT * from veiculo WHERE vei_categoria_id = 3 AND vei_situacao = 'DISPONIVEL';

