
--SELECT 

SELECT * FROM acessorio a 

SELECT * FROM categoria c 

SELECT * FROM cliente c 

SELECT * FROM contato c 

SELECT * FROM devolucao d 

SELECT * FROM endereco e 

SELECT * FROM locacao l 

SELECT * FROM marca m 

SELECT * FROM modelo m 

SELECT * FROM motorista m 

SELECT * FROM usuario u

SELECT * FROM veiculo v

SELECT * FROM endereco WHERE end_cliente_id = 3;

SELECT * FROM locacao l WHERE loc_cliente_id = 8;

SELECT * FROM locacao 
			 INNER JOIN cliente c ON locacao.loc_cliente_id = cli_id ;
			

			
			 
			 
			 
SELECT * FROM locacao l WHERE loc_id  = 3;
		
INSERT INTO contato 
( con_tipo, con_telefone, con_email, con_cliente_id, con_motorista_id)
VALUES 
--('CELULAR','62985915530', 'rogerio@gmail.com',1, null),
--('CELULAR','62985915531', 'gabriel@gmail.com',2, null),
--('CELULAR','62985915532', 'aires@gmail.com',3, null),
--('CELULAR','62985915533', 'lucas@gmail.com',4, null),
--('CELULAR','62985915534', 'joseluiz@gmail.com',5, null),
--('CELULAR','62985915535', 'halley@gmail.com',6, null),
--('CELULAR','62985915536', 'hauber@gmail.com',8, null),
--('CELULAR','62985915537', 'eugenio@gmail.com',7, null),
--('CELULAR','62985915538', 'katia@gmail.com',9, null),
--('CELULAR','62985915539', 'bete@gmail.com',10, null),
--('CELULAR','62985915530', 'rogerio.mot@gmail.com',null, 1),
--('CELULAR','62985915531', 'gabriel.mot@gmail.com',null, 2),
--('CELULAR','62985915532', 'aires.mot@gmail.com',null, 3),
--('CELULAR','62985915533', 'lucas.mot@gmail.com',null, 4),
('CELULAR','62985915540', 'fiorentina.mot@gmail.com',null, 7);

INSERT INTO locacao
(loc_cliente_id, loc_motorista_id, loc_veiculo_id, loc_usuario_cadastro_id, loc_valor_multa, loc_tanque_cheio,
loc_data_retirada, loc_data_devolucao_prevista, loc_km_inicial, loc_observacoes, loc_valor_total_acessorios,
loc_valor_locacao, loc_valor_caucao, loc_valor_seguro, loc_status, loc_reserva)
VALUES(1, 2, 1, 1, 50, false, '12/12/2020', '20/12/2020', '123456', 'nenhuma observação', 75, 150, 200, 35, 'ATIVO', false);




CREATE TABLE marca (
    mar_id SERIAL PRIMARY KEY,
    mar_nome VARCHAR(60) NOT NULL,
    mar_status VARCHAR(20) NOT null,
    CONSTRAINT mar_nome_unico UNIQUE (mar_nome)
);

-- Inserir dados na tabela de marcas.

INSERT INTO marca
(mar_nome, mar_status)
VALUES
('chevrolet','INATIVO'),
('volkswagen','INATIVO'),
('fiat','INATIVO'),
('renault', 'INATIVO'),
('ford',    'INATIVO'),
('toyota','INATIVO'),
('hyundai', 'INATIVO'),
('jeep','INATIVO'),
('suzuki',  'INATIVO'),
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

CREATE TABLE modelo (
    mod_id serial PRIMARY KEY,
    mod_nome varchar(60) NOT NULL,
    mod_status varchar(20) NOT NULL, 
    mod_marca_id integer NOT NULL,
    FOREIGN KEY (mod_marca_id) REFERENCES marca (mar_id),
    CONSTRAINT mod_nome_unico UNIQUE (mod_nome)
);

-- Inserir dados na tabela de Modelos

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
('civic','INATIVO',32),
('punto','INATIVO',5),
('fit','INATIVO',32),
('spin','INATIVO',3),
('C3','INATIVO',25),
('cruze','INATIVO',3),
('logan','INATIVO',6),
('agile','INATIVO',3),
('city','INATIVO',32),
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

CREATE TABLE categoria (
    cat_id serial PRIMARY KEY,
    cat_nome varchar(60)NOT NULL,
    cat_valor numeric(7,2) NOT NULL,
    cat_status varchar(20) NOT NULL,
    CONSTRAINT cat_nome_unico UNIQUE (cat_nome),
    CONSTRAINT cat_valor_valido CHECK (cat_valor > 0)
);

-- Inserir dados na tabela de  categoria

INSERT INTO categoria 
(cat_nome, cat_valor, cat_status)
VALUES
('ECONOMICO', 70.00, 'ATIVO'),
('EXECUTIVO', 160.00, 'ATIVO'),
('INTERMEDIARIO', 90.00, 'ATIVO'),
('SUV', 120.00, 'ATIVO'),
('UTILITARIO', 200.00, 'ATIVO');


CREATE TABLE veiculo (
    vei_id serial PRIMARY KEY,
    vei_placa varchar(7) NOT NULL,
    vei_ano_fabricacao integer NOT NULL,
    vei_ano_modelo integer NOT NULL,
    vei_tipo_combustivel varchar(20) NOT NULL,
    vei_renavan varchar(11) NOT NULL,
    vei_preco_compra numeric(8,2) NOT NULL,
    vei_preco_venda numeric(8,2) NOT NULL,
    vei_tipo varchar(20) NOT NULL,
    vei_status varchar(20) NOT NULL,
    vei_num_passageiro integer NOT NULL,
    vei_km BIGINT NOT NULL,
    vei_categoria_id integer NOT NULL,
    vei_modelo_id integer NOT NULL,
    vei_situacao VARCHAR(20) NOT null,
    CONSTRAINT vei_ano_fabricacao_valido CHECK (vei_ano_fabricacao >= 1886),
    CONSTRAINT vei_ano_modelo_valido CHECK (vei_ano_modelo >= 1886),
    CONSTRAINT vei_km_valido CHECK (vei_km > 0),
    CONSTRAINT vei_num_passageiros CHECK (vei_num_passageiro > 0),
    CONSTRAINT vei_placa_unica UNIQUE (vei_placa),
    CONSTRAINT vei_renavan_unico UNIQUE (vei_renavan),
	CONSTRAINT vei_valor_compra_valido CHECK (vei_preco_compra > (0)),
	CONSTRAINT vei_valor_venda_valido CHECK (vei_preco_venda > (0)),   
	CONSTRAINT fk_vei_veiculo_vei_categoria_id FOREIGN KEY (vei_categoria_id) REFERENCES categoria(cat_id),
	CONSTRAINT fk_vei_veiculo_vei_modelo_id FOREIGN KEY (vei_modelo_id) REFERENCES modelo(mod_id)	
);

INSERT INTO veiculo
(vei_placa, vei_ano_fabricacao, vei_ano_modelo, vei_tipo_combustivel, vei_renavan, vei_preco_compra, vei_preco_venda, vei_tipo, vei_status, vei_num_passageiro, vei_km, vei_categoria_id, vei_modelo_id, vei_situacao)
VALUES('ASD1111', 2020, 2020, 'GASOLINA', '12345678911', 105000, 105000, 'SUV', 'ATIVO',5, 123456, 4, 30, 'DISPONIVEL');


-- Criar tabela de Acessórios

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


CREATE TABLE usuario (
    usu_id SERIAL PRIMARY KEY,
    usu_nome VARCHAR(60) NOT NULL,
    usu_cpf CHAR(15) NOT NULL,
    usu_email VARCHAR(60) NOT NULL,
    usu_senha VARCHAR(10) NOT NULL,
    usu_perfil VARCHAR(20) NOT NULL,
    usu_data_cadastro DATE NOT NULL,
    usu_status VARCHAR(20) NOT NULL,
	CONSTRAINT usu_cpf_unico UNIQUE (usu_cpf),
	CONSTRAINT usu_email_unico UNIQUE (usu_email)  
);

-- Inserir dados na tabela de usuários

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


CREATE TABLE cliente (
    cli_id SERIAL PRIMARY KEY,
    cli_razao_social VARCHAR(100) NULL,
    cli_nome_fantasia VARCHAR(100) null, 
    cli_nome varchar(100) null,
    cli_status VARCHAR(20) not null,
    cli_cpf_cnpj VARCHAR(14) not null,
    cli_rg VARCHAR(20) null,
    cli_rg_orgao_emissor VARCHAR(6) null,
    cli_tipo VARCHAR(20) not null,
    CONSTRAINT cli_cpf_cnpj_unico UNIQUE (cli_cpf_cnpj),
    CONSTRAINT cli_rg_unico UNIQUE (cli_rg)
);

-- Inserir dados da tabela de Clientes 

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
( null,null,'Bete Tie Senai','ATIVO','69520032070','22111222','ssp_go','PESSOA_FISICA'),
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
('Beltrano da Silva','31055417052','21111222','ssp_sp','05677045271','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','CAT_A','ATIVO'),
 ('Caetano da fonseca','83955013073','21222333','ssp_rs','46158627079','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','CAT_B','ATIVO'),
 ('Fiorentina','86846801030','21333444','ssp_rs','94660948800','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','CAT_C','ATIVO'),
('Rui Barbosa','04143583097','21444555','ssp_rs','94823952751','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','CAT_D','ATIVO');

SELECT * FROM motorista m 

CREATE TABLE endereco (
    end_id SERIAL PRIMARY KEY,
    end_tipo VARCHAR(20) NOT NULL,
    end_cep CHAR(8) NOT NULL,
    end_rua VARCHAR(60) NOT NULL,
    end_numero VARCHAR(60) NOT NULL,
    end_complemento VARCHAR(60) NULL,
    end_bairro VARCHAR(60) NOT NULL,
    end_cidade VARCHAR(60) NOT NULL,                                            
    end_uf CHAR(2) NOT NULL,
    end_cliente_id INTEGER NULL,
    end_motorista_id INTEGER NULL,
    CONSTRAINT fk_endereco_cliente_id FOREIGN KEY (end_cliente_id) REFERENCES cliente(cli_id),
    CONSTRAINT fk_endereco_motorista_id FOREIGN KEY (end_motorista_id) REFERENCES motorista(mot_id)
);


CREATE TABLE contato (
    con_id SERIAL PRIMARY KEY,
    con_tipo VARCHAR(20) NOT NULL,
    con_telefone VARCHAR(11) NOT NULL,
    con_email VARCHAR(60) NOT NULL,
    con_cliente_id INTEGER NULL,
    con_motorista_id INTEGER NULL,
    CONSTRAINT cont_email_unico UNIQUE (con_email),
    CONSTRAINT fk_contato_cliente_id FOREIGN KEY (con_cliente_id) REFERENCES cliente(cli_id),
    CONSTRAINT fk_contato_motorista_id FOREIGN KEY (con_motorista_id) REFERENCES motorista(mot_id)
);


CREATE TABLE locacao (
    loc_id SERIAL PRIMARY KEY,
    loc_cliente_id INTEGER NOT NULL,
    loc_motorista_id INTEGER NOT NULL,
    loc_veiculo_id INTEGER NOT NULL,
    loc_usuario_cadastro_id INTEGER NOT NULL,
    loc_valor_multa numeric(7,2) NULL,
    loc_tanque_cheio BOOLEAN NULL,
    loc_data_retirada TIMESTAMP NOT NULL, 
    loc_data_devolucao_prevista TIMESTAMP NULL,
    loc_km_inicial varchar(20) NULL,
    loc_observacoes text NULL,
    loc_valor_total_acessorios NUMERIC(7,2) NULL,
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


CREATE TABLE devolucao (
    dev_id SERIAL PRIMARY KEY,
    dev_data_devolucao_realizada TIMESTAMP NOT NULL,
    dev_tanque_cheio boolean NOT NULL,
    dev_km_final BIGINT NULL,
    dev_observacao TEXT,
    dev_valor_multa NUMERIC(7,2) NULL,
    dev_valor_combustivel NUMERIC(7,2) NULL,
    dev_valor_pago NUMERIC(7,2) NOT NULL,
    dev_locacao_id integer NOT NULL,
    dev_usuario_cadastro_id INTEGER NOT NULL,
    CONSTRAINT fk_devolucao_locacao_id FOREIGN KEY (dev_locacao_id) REFERENCES locacao(loc_id),
    CONSTRAINT fk_devolucao_usuario_id FOREIGN KEY (dev_usuario_cadastro_id) REFERENCES usuario(usu_id)
);

-- inser enviado por Gabriel
INSERT INTO locacao (loc_cliente_id, loc_motorista_id, loc_veiculo_id, loc_usuario_cadastro_id, loc_valor_multa, loc_tanque_cheio, loc_data_retirada, loc_data_devolucao_prevista, loc_km_inicial, loc_observacoes, loc_valor_total_acessorios, loc_valor_locacao, loc_valor_caucao,loc_valor_seguro, loc_status, loc_reserva)
values
(1, 5, 1, 2, 0, true, '2021-12-18', '2021-12-25', '234378', 'Observacao teste', 356.90, 2897, 4345.5, 260.73, 'AGUARDANDO', true),
(2, 6, 2, 2, 0, false, '2020-12-05', '2020-12-26', '16488', 'Observacao teste2', 256.90, 1897, 3345.5, 189.80, 'VIGENTE', false),
(3, 7, 3, 2, 0, true, '2021-01-04', '2021-01-27', '36488', 'Observacao teste3', 256.90, 1897, 3345.5, 123.66, 'AGUARDANDO', true),
(4, 8, 4, 2, 0, false, '2021-08-12', '2021-12-25', '46488', 'Observacao teste4', 256.90, 1897, 3345.5, 234.55, 'VIGENTE', false),
(5, 5, 5, 2, 0, true, '2020-10-29', '2020-11-09', '45637', 'Observacao teste5', 256.90, 1897, 3345.5, 454.65, 'PRAZO_EXPIRADO', true),
(6, 6, 6, 2, 0, false, '2021-12-14', '2021-12-25', '76635', 'Observacao teste6', 256.90, 1897, 3345.5, 122.23, 'VIGENTE', false),
(7, 7, 1, 2, 0, true, '2020-12-10', '2020-11-16', '35556', 'Observacao teste7', 256.90, 1897, 3345.5, 878.34, 'PRAZO_EXPIRADO', false),
(8, 8, 2, 2, 0, false, '2020-06-15', '2020-05-25', '16488', 'Observacao teste8', 256.90, 1897, 3345.5, 232.50, 'FINALIZADO', false);


SELECT * FROM veiculo v 

