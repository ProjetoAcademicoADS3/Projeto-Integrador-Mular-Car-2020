
-- DROP TYPE categoria;

CREATE TYPE categoria AS (
	cat_id int4,
	cat_nome varchar(60),
	cat_valor numeric(7,2),
	cat_status varchar(20));

-- DROP TYPE cliente;

CREATE TYPE cliente AS (
	cli_id int4,
	cli_razao_social varchar(100),
	cli_nome_fantasia varchar(100),
	cli_nome varchar(100),
	cli_status varchar(20),
	cli_cpf_cnpj varchar(14),
	cli_rg varchar(20),
	cli_rg_orgao_emissor varchar(6),
	cli_tipo varchar(20));

-- DROP TYPE contato;

CREATE TYPE contato AS (
	con_id int4,
	con_tipo varchar(20),
	con_telefone varchar(11),
	con_email varchar(60),
	con_cliente_id int4,
	con_motorista_id int4);

-- DROP TYPE contrato;

CREATE TYPE contrato AS (
	con_id int4,
	con_cliente_id int4,
	con_motorista_id int4,
	con_veiculo_id int4,
	con_usuario_cadastro_id int4,
	con_valor_multa numeric(7,2),
	con_tanque_cheio bool,
	con_data_retirada timestamp,
	con_data_devolucao_prevista timestamp,
	con_data_devolucao_realizada timestamp,
	con_km_inicial int8,
	con_km_final int8,
	con_observacoes text,
	con_valores_adicionais numeric,
	con_valor_locacao numeric(7,2),
	con_valor_caucao numeric(7,2),
	con_valor_seguro numeric(7,2),
	con_status varchar(20));

-- DROP TYPE endereco;

CREATE TYPE endereco AS (
	end_id int4,
	end_tipo varchar(20),
	end_cep bpchar(8),
	end_rua varchar(60),
	end_numero int4,
	end_complemento varchar(60),
	end_bairro varchar(60),
	end_cidade varchar(60),
	end_uf bpchar(2),
	end_cliente_id int4,
	end_motorista_id int4);

-- DROP TYPE marca;

CREATE TYPE marca AS (
	mar_id int4,
	mar_nome varchar(60),
	mar_status varchar(20));

-- DROP TYPE modelo;

CREATE TYPE modelo AS (
	mod_id int4,
	mod_nome varchar(60),
	mod_marca_id int4,
	mod_status varchar(20));

-- DROP TYPE motorista;

CREATE TYPE motorista AS (
	mot_id int4,
	mot_nome varchar(60),
	mot_cpf varchar(11),
	mot_rg varchar(20),
	mot_rg_orgao_emissor varchar(6),
	mot_cnh_numero varchar(20),
	mot_cnh_data_validade date,
	mot_cnh_imagem varchar(100),
	mot_cnh_categoria varchar(5));

-- DROP TYPE usuario;

CREATE TYPE usuario AS (
	usu_id int4,
	usu_nome varchar(60),
	usu_email varchar(60),
	usu_senha varchar(10),
	usu_perfil varchar(20),
	usu_data_cadastro date,
	usu_status varchar(20),
	usu_cpf varchar(15));

-- DROP TYPE veiculo;

CREATE TYPE veiculo AS (
	vei_id int4,
	vei_placa varchar(7),
	vei_ano_fabricacao int4,
	vei_ano_modelo int4,
	vei_tipo_combustivel varchar(20),
	vei_renavan bpchar(11),
	vei_preco_compra numeric(8,2),
	vei_preco_venda numeric(8,2),
	vei_tipo varchar(20),
	vei_status varchar(20),
	vei_num_passageiro int4,
	vei_km int8,
	vei_categoria_id int4,
	vei_modelo_id int4,
	vei_situacao varchar(20));

-- DROP SEQUENCE public.categoria_cat_id_seq;


-- Drop table

-- DROP TABLE categoria;

CREATE TABLE categoria (
	cat_id serial NOT NULL,
	cat_nome varchar(60) NOT NULL,
	cat_valor numeric(7,2) NOT NULL,
	cat_status varchar(20) NOT NULL,
	CONSTRAINT categoria_pkey PRIMARY KEY (cat_id),
	CONSTRAINT categoria_unica UNIQUE (cat_nome),
	CONSTRAINT valor_valido CHECK ((cat_valor > (0)::numeric))
);


-- public.cliente definition

-- Drop table

-- DROP TABLE cliente;

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


-- public.contrato definition

-- Drop table

-- DROP TABLE contrato;

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


-- public.marca definition

-- Drop table

-- DROP TABLE marca;

CREATE TABLE marca (
	mar_id serial NOT NULL,
	mar_nome varchar(60) NOT NULL,
	mar_status varchar(20) NOT NULL,
	CONSTRAINT marca_pkey PRIMARY KEY (mar_id),
	CONSTRAINT nome_unico UNIQUE (mar_nome)
);


-- public.motorista definition

-- Drop table

-- DROP TABLE motorista;

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


-- public.usuario definition

-- Drop table

-- DROP TABLE usuario;

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


-- public.contato definition

-- Drop table

-- DROP TABLE contato;

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


-- public.endereco definition

-- Drop table

-- DROP TABLE endereco;

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


-- public.modelo definition

-- Drop table

-- DROP TABLE modelo;

CREATE TABLE modelo (
	mod_id serial NOT NULL,
	mod_nome varchar(60) NOT NULL,
	mod_marca_id int4 NOT NULL,
	mod_status varchar(20) NOT NULL,
	CONSTRAINT modelo_pkey PRIMARY KEY (mod_id),
	CONSTRAINT modelo_unico UNIQUE (mod_nome),
	CONSTRAINT modelo_mod_marca_id_fkey FOREIGN KEY (mod_marca_id) REFERENCES marca(mar_id)
);


-- public.veiculo definition

-- Drop table

-- DROP TABLE veiculo;

CREATE TABLE veiculo (
	vei_id serial NOT NULL,
	vei_placa varchar(7) NOT NULL,
	vei_ano_fabricacao int4 NOT NULL,
	vei_ano_modelo int4 NOT NULL,
	vei_tipo_combustivel varchar(20) NOT NULL,
	vei_renavan bpchar(11) NOT NULL,
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
