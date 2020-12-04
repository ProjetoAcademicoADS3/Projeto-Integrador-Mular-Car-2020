--projeto MULA CAR
--feito diagrama de classe também
CREATE TABLE usuario (
    usu_id serial NOT NULL,
    usu_nome varchar(60) NOT NULL,
    usu_email varchar(60) NOT NULL,
    usu_senha varchar(10) NOT NULL,
    usu_perfil varchar(20) NOT NULL,
    usu_data_cadastro date NOT NULL,
    usu_status varchar(20) NOT NULL,
    usu_cpf varchar(15) NULL,
    CONSTRAINT usu_cpf_unico UNIQUE (usu_cpf),
    CONSTRAINT usu_email_unico UNIQUE (usu_email),
    CONSTRAINT usuario_pkey PRIMARY KEY (usu_id)
);

INSERT INTO usuario 
( usu_nome, usu_cpf, usu_email, usu_senha, usu_perfil, usu_data_cadastro, usu_status)VALUES
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


--feito diagrama de classe também
CREATE TABLE marca (
    mar_id serial NOT NULL,
    mar_nome varchar(60) NOT NULL,
    mar_status varchar(20) NOT NULL,
    CONSTRAINT marca_pkey PRIMARY KEY (mar_id),
    CONSTRAINT nome_unico UNIQUE (mar_nome)
);

INSERT INTO marca
(mar_nome, mar_status)VALUES
('chevrolet','INATIVO'),
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
	

--feito diagrama de classe também	
CREATE TABLE modelo (
    mod_id serial NOT NULL,
    mod_nome varchar(60) NOT NULL,
    mod_marca_id int4 NOT NULL,
    mod_status varchar(20) NOT NULL,
    CONSTRAINT modelo_pkey PRIMARY KEY (mod_id),
    CONSTRAINT modelo_unico UNIQUE (mod_nome),
    CONSTRAINT modelo_mod_marca_id_fkey FOREIGN KEY (mod_marca_id) REFERENCES marca(mar_id)
);

INSERT INTO modelo 
(mod_nome, mod_status, mod_marca_id)VALUES
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


--feito diagrama de classe também
CREATE TABLE categoria (
    cat_id serial NOT NULL,
    cat_nome varchar(60) NOT NULL,
    cat_valor numeric(7,2) NOT NULL,
    cat_status varchar(20) NOT NULL,
    CONSTRAINT categoria_pkey PRIMARY KEY (cat_id),
    CONSTRAINT categoria_unica UNIQUE (cat_nome),
    CONSTRAINT valor_valido CHECK ((cat_valor > (0)::numeric))
);

INSERT INTO categoria 
(cat_nome, cat_valor, cat_status)VALUES
('ECONOMICO', 70.00, 'ATIVO'),
('EXECUTIVO', 160.00, 'ATIVO'),
('INTERMEDIARIO', 90.00, 'ATIVO'),
('SUV', 120.00, 'ATIVO'),
('UTILITARIO', 200.00, 'ATIVO');


--feito diagrama de classe também
CREATE TABLE veiculo (
    vei_id serial NOT NULL,
    vei_placa varchar(7) NOT NULL,
    vei_ano_fabricacao int4 NOT NULL,
    vei_ano_modelo int4 NOT NULL,
    vei_tipo_combustivel varchar(20) NOT NULL,
    vei_renavan varchar(11) NOT NULL,
    vei_preco_compra numeric(8,2) NOT NULL,
    vei_preco_venda numeric(8,2) NOT NULL,
    vei_tipo varchar(20) NOT NULL,
    vei_status varchar(20) NOT NULL,
    vei_num_passageiro int4 NOT NULL,
    vei_km int8 NOT NULL,
    vei_categoria_id int4 NULL,
    vei_modelo_id int4 NULL,
    vei_situacao varchar(20) NOT NULL,
    CONSTRAINT ano_fabricacao_valido CHECK ((vei_ano_fabricacao >= 1886)),
    CONSTRAINT ano_modelo_valido CHECK ((vei_ano_modelo >= 1886)),
    CONSTRAINT km_valido CHECK ((vei_km > 0)),
    CONSTRAINT num_passageiros CHECK ((vei_num_passageiro > 0)),
    CONSTRAINT placa_unica UNIQUE (vei_placa),
    CONSTRAINT renavan_unico UNIQUE (vei_renavan),
    CONSTRAINT valor_compra_valido CHECK ((vei_preco_compra > (0)::numeric)),
    CONSTRAINT valor_venda_valido CHECK ((vei_preco_venda > (0)::numeric)),
    CONSTRAINT veiculo_pkey PRIMARY KEY (vei_id),
    CONSTRAINT veiculo_vei_categoria_id_fkey FOREIGN KEY (vei_categoria_id) REFERENCES categoria(cat_id),
    CONSTRAINT veiculo_vei_modelo_id_fkey FOREIGN KEY (vei_modelo_id) REFERENCES modelo(mod_id)
);


--feito diagrama de classe também
CREATE TABLE cliente (
    cli_id serial NOT NULL,
    cli_razao_social varchar(100) NULL,
    cli_nome_fantasia varchar(100) NULL,
    cli_nome varchar(100) NULL,
    cli_status varchar(20) NOT NULL,
    cli_cpf_cnpj varchar(14) NOT NULL,
    cli_rg varchar(20) NULL,
    cli_rg_orgao_emissor varchar(6) NULL,
    cli_tipo varchar(20) NOT NULL,
    CONSTRAINT cli_cpf_cnpj_unico UNIQUE (cli_cpf_cnpj),
    CONSTRAINT cli_rg_unico UNIQUE (cli_rg),
    CONSTRAINT cliente_pkey PRIMARY KEY (cli_id)
);

INSERT INTO cliente(
cli_razao_social, 
cli_nome_fantasia,
cli_nome, 
cli_status,
cli_cpf_cnpj,
cli_rg,
cli_rg_orgao_emissor,
cli_tipo)VALUES
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


--feito diagrama de classe também
CREATE TABLE motorista (
    mot_id serial NOT NULL,
    mot_nome varchar(60) NOT NULL,
    mot_cpf varchar(11) NOT NULL,
    mot_rg varchar(20) NOT NULL,
    mot_rg_orgao_emissor varchar(6) NOT NULL,
    mot_cnh_numero varchar(20) NOT NULL,
    mot_cnh_data_validade date NOT NULL,
    mot_cnh_imagem varchar(100) NOT NULL,
    mot_cnh_categoria varchar(5) NOT NULL,
    CONSTRAINT cnh_data_validade_valida CHECK ((mot_cnh_data_validade > CURRENT_DATE)),
    CONSTRAINT cnh_unica UNIQUE (mot_cnh_numero),
    CONSTRAINT mot_cpf_unico UNIQUE (mot_cpf),
    CONSTRAINT mot_rg_unico UNIQUE (mot_rg),
    CONSTRAINT motorista_pkey PRIMARY KEY (mot_id)
);

INSERT INTO motorista (
mot_nome, mot_cpf, mot_rg, mot_rg_orgao_emissor, mot_cnh_numero, mot_cnh_data_validade, mot_cnh_imagem,
mot_cnh_categoria)
VALUES
('Rogerio Tadeu dos Reis','64066522076','21244108','ssp_sp','12345678901','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','AC'),
 ('Gabriel','82916982019','11222333','ssp_rs','12345678902','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','AC'),
 ('Aires','10464305055','11222333','ssp_rs','12345678902','28/11/2024',
 'C:\Users\roger\Pictures\Miniaturas\kawazaki2.jpg','AC'),


--feito diagrama de classe também
CREATE TABLE contato (
    con_id serial NOT NULL,
    con_tipo varchar(20) NOT NULL,
    con_telefone varchar(11) NOT NULL,
    con_email varchar(60) NOT NULL,
    con_cliente_id int4 NULL,
    con_motorista_id int4 NULL,
    CONSTRAINT cont_mail_unico UNIQUE (con_email),
    CONSTRAINT contato_pkey PRIMARY KEY (con_id),
    CONSTRAINT contato_con_cliente_id_fkey FOREIGN KEY (con_cliente_id) REFERENCES cliente(cli_id),
    CONSTRAINT contato_con_motorista_id_fkey FOREIGN KEY (con_motorista_id) REFERENCES motorista(mot_id)
);



CREATE TABLE endereco (
    end_id serial NOT NULL,
    end_tipo varchar(20) NOT NULL,
    end_cep bpchar(8) NOT NULL,
    end_rua varchar(60) NOT NULL,
    end_numero int4 NOT NULL,
    end_complemento varchar(60) NULL,
    end_bairro varchar(60) NOT NULL,
    end_cidade varchar(60) NOT NULL,
    end_uf bpchar(2) NOT NULL,
    end_cliente_id int4 NULL,
    end_motorista_id int4 NULL,
    CONSTRAINT endereco_pkey PRIMARY KEY (end_id),
    CONSTRAINT endereco_end_cliente_id_fkey FOREIGN KEY (end_cliente_id) REFERENCES cliente(cli_id),
    CONSTRAINT endereco_end_motorista_id_fkey FOREIGN KEY (end_motorista_id) REFERENCES motorista(mot_id)
);



CREATE TABLE contrato (
    con_id serial NOT NULL,
    con_cliente_id int4 NOT NULL,
    con_motorista_id int4 NOT NULL,
    con_veiculo_id int4 NOT NULL,
    con_usuario_cadastro_id int4 NOT NULL,
    con_valor_multa numeric(7,2) NOT NULL,
    con_tanque_cheio bool NOT NULL,
    con_data_retirada timestamp NOT NULL,
    con_data_devolucao_prevista timestamp NOT NULL,
    con_data_devolucao_realizada timestamp NULL,
    con_km_inicial int8 NOT NULL,
    con_km_final int8 NULL,
    con_observacoes text NULL,
    con_valores_adicionais numeric NULL,
    con_valor_locacao numeric(7,2) NOT NULL,
    con_valor_caucao numeric(7,2) NOT NULL,
    con_valor_seguro numeric(7,2) NOT NULL,
    con_status varchar(20) NOT NULL,
    CONSTRAINT con_data_retirada_valida CHECK ((con_data_retirada >= CURRENT_DATE)),
    CONSTRAINT con_km_final_valida CHECK ((con_km_final > con_km_inicial)),
    CONSTRAINT con_km_inicial_valida CHECK ((con_km_inicial > 0)),
    CONSTRAINT con_valor_caucao_valido CHECK ((con_valor_caucao > (0)::numeric)),
    CONSTRAINT con_valor_locacao_valido CHECK ((con_valor_locacao > (0)::numeric)),
    CONSTRAINT con_valor_seguro_valido CHECK ((con_valor_seguro > (0)::numeric)),
    CONSTRAINT con_valores_adicionais_valido CHECK ((con_valores_adicionais >= (0)::numeric)),
    CONSTRAINT contrato_pkey PRIMARY KEY (con_id),
    CONSTRAINT data_devolucao_realizada_valida CHECK ((con_data_devolucao_realizada >= con_data_retirada)),
    CONSTRAINT data_devolucao_valida CHECK ((con_data_devolucao_prevista >= con_data_retirada)),
    CONSTRAINT valor_multa_valido CHECK ((con_valor_multa >= (0)::numeric))
);

