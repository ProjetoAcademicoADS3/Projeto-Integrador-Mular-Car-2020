CREATE TABLE marca (
    mar_id SERIAL PRIMARY KEY,
    mar_nome VARCHAR(60) NOT NULL CONSTRAINT marca_unica UNIQUE,
    mar_status VARCHAR(20) NOT NULL
);

CREATE TABLE modelo (
    mod_id serial PRIMARY KEY,
    mod_nome varchar(60) NOT NULL CONSTRAINT modelo_unico UNIQUE,
    mod_status varchar(20) NOT NULL, 
    mod_marca_id integer NOT NULL,
    FOREIGN KEY (mod_marca_id) REFERENCES marca (mar_id)
);

CREATE TABLE categoria (
    cat_id serial PRIMARY KEY,
    cat_nome varchar(60)NOT NULL CONSTRAINT categoria_unica UNIQUE,
    cat_valor numeric(7,2) NOT NULL CONSTRAINT valor_valido CHECK (cat_valor > 0),
    cat_status varchar(20) NOT NULL
);

CREATE TABLE veiculo (
    vei_id serial PRIMARY KEY,
    vei_placa varchar(7) not null constraint placa_unica unique,
    vei_ano_fabricacao integer not null constraint ano_fabricacao_valido check (vei_ano_fabricacao > 1886
                                                                               and vei_ano_fabricacao <= current_date),
    vei_ano_modelo integer not null constraint ano_modelo_valido check (vei_ano_modelo > 1886 and 
                                                                        vei_ano_modelo <= current_date + 1),
    vei_tipo_combustivel varchar(20) not null,
    vei_renavan char(11) not null constraint renavan_unico unique check(vei_renavan > 0),
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

CREATE TABLE endereco (
    end_id SERIAL PRIMARY KEY,
    end_tipo VARCHAR(20) not null,
    end_cep CHAR(8) not null,
    end_rua VARCHAR(60)not null,
    end_numero INTEGER not null,
    end_complemento VARCHAR(60),
    end_bairro VARCHAR(60) not null,
    end_cidade VARCHAR(60) not null,
    end_uf CHAR(2) not null,
    end_cliente_id INTEGER,
    end_motorista_id INTEGER,
    foreign key (end_cliente_id) references cliente (cli_id),
    foreign key (end_motorista_id) references motorista (mot_id)
);

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

CREATE TABLE contrato (
    con_id SERIAL PRIMARY KEY,
    con_cliente_id INTEGER not null,
    con_motorista_id INTEGER not null,
    con_veiculo_id INTEGER not null,
    con_usuario_cadastro_id INTEGER not null,
    con_valor_multa NUMERIC(7,2) not null constraint valor_multa_valido check (con_valor_multa >= 0),
    con_tanque_cheio BOOLEAN not null,
    con_data_retirada TIMESTAMP not null constraint con_data_retirada_valida check (con_data_retirada >= current_date) ,
    con_data_devolucao_prevista TIMESTAMP not null constraint data_devolucao_valida check (con_data_devolucao_prevista
                                                                                          >= con_data_retirada),
    con_data_devolucao_realizada TIMESTAMP constraint data_devolucao_realizada_valida check (con_data_devolucao_realizada >= con_data_retirada),
    con_km_inicial BIGINT not null constraint con_km_inicial_valida check (con_km_inicial > 0),
    con_km_final BIGINT constraint con_km_final_valida check (con_km_final > con_km_inicial),
    con_observacoes TEXT,
    con_valores_adicionais NUMERIC constraint con_valores_adicionais_valido check (con_valores_adicionais >= 0),
    con_valor_locacao NUMERIC(7,2) not null constraint con_valor_locacao_valido check (con_valor_locacao > 0),
    con_valor_caucao NUMERIC(7,2) not null constraint con_valor_caucao_valido check (con_valor_caucao > 0),
    con_valor_seguro NUMERIC(7,2) not null constraint con_valor_seguro_valido check (con_valor_seguro > 0),
    con_status VARCHAR(20) not null
);
 