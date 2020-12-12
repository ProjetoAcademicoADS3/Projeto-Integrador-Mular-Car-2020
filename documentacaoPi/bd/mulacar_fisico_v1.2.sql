
CREATE TABLE marca (
    mar_id SERIAL PRIMARY KEY,
    mar_nome VARCHAR(60) NOT NULL,
    mar_status VARCHAR(20) NOT null,
    CONSTRAINT mar_nome_unico UNIQUE (mar_nome)
);

CREATE TABLE modelo (
    mod_id serial PRIMARY KEY,
    mod_nome varchar(60) NOT NULL,
    mod_status varchar(20) NOT NULL, 
    mod_marca_id integer NOT NULL,
    FOREIGN KEY (mod_marca_id) REFERENCES marca (mar_id),
    CONSTRAINT mod_nome_unico UNIQUE (mod_nome)
);

CREATE TABLE categoria (
    cat_id serial PRIMARY KEY,
    cat_nome varchar(60)NOT NULL,
    cat_valor numeric(7,2) NOT NULL,
    cat_status varchar(20) NOT NULL,
    CONSTRAINT cat_nome_unico UNIQUE (cat_nome),
    CONSTRAINT cat_valor_valido CHECK (cat_valor > 0)
);

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

CREATE TABLE motorista (
	mot_id serial primary key,
	mot_nome varchar(60) NOT NULL,
	mot_cpf varchar(11) NOT NULL,
	mot_rg varchar(20) NOT NULL,
	mot_rg_orgao_emissor varchar(6) NOT NULL,
	mot_cnh_numero varchar(20) NOT NULL,
	mot_cnh_data_validade date NOT NULL,
	mot_cnh_imagem varchar(100) NOT NULL,
	mot_cnh_categoria varchar(5) NOT NULL,
	CONSTRAINT mot_cnh_data_validade_valida CHECK ((mot_cnh_data_validade > CURRENT_DATE)),
	CONSTRAINT mot_cnh_unica UNIQUE (mot_cnh_numero),
	CONSTRAINT mot_cpf_unico UNIQUE (mot_cpf),
	CONSTRAINT mot_rg_unico UNIQUE (mot_rg)
);

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
    loc_valor_total_locacao NUMERIC(7,2) NOT NULL,
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
 


