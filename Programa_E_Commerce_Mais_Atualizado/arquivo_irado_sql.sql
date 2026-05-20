DROP DATABASE IF EXISTS teste;
CREATE DATABASE teste;
USE teste;

-- 1. TABELAS INDEPENDENTES (Não possuem Chaves Estrangeiras)

CREATE TABLE cliente (
    cli_cpf VARCHAR(11) PRIMARY KEY,
    cli_nome VARCHAR(255) NOT NULL,
    cli_email VARCHAR(255) NOT NULL,
    cli_telefone VARCHAR(20) NOT NULL,
    cli_dat_cad DATE NOT NULL
);

CREATE TABLE fornecedor (
    frn_cnpj VARCHAR(14) PRIMARY KEY,
    frn_nome VARCHAR(255) NOT NULL,
    frn_telefone VARCHAR(20) NOT NULL,
    frn_email VARCHAR(255) NOT NULL
);

CREATE TABLE categoria (
    cat_nome VARCHAR(50) PRIMARY KEY,
    cat_desc VARCHAR(255) NOT NULL
);

-- 2. TABELAS DEPENDENTES (Possuem Chaves Estrangeiras)

CREATE TABLE produto (
    pdt_sku VARCHAR(10) PRIMARY KEY,
    pdt_nome VARCHAR(255) NOT NULL,
    pdt_desc VARCHAR(255) NOT NULL,      -- NOVO: Descrição do Produto
    pdt_preco DOUBLE(10, 2) NOT NULL,
    pdt_quant INTEGER NOT NULL,
    pdt_status VARCHAR(20) NOT NULL,
    frn_cnpj VARCHAR(14),     
    pdt_categoria VARCHAR(255) NOT NULL,
    -- NOVO: Ligação com o Fornecedor
    CONSTRAINT fk_produto_fornecedor
    FOREIGN KEY (frn_cnpj) REFERENCES fornecedor(frn_cnpj)
);

-- NOVO: Tabela associativa para permitir que 1 Produto tenha Várias Categorias
CREATE TABLE produto_categoria (
    pdt_sku VARCHAR(10),
    cat_nome VARCHAR(50),
    PRIMARY KEY (pdt_sku, cat_nome),
    CONSTRAINT fk_pc_produto FOREIGN KEY (pdt_sku) REFERENCES produto(pdt_sku),
    CONSTRAINT fk_pc_categoria FOREIGN KEY (cat_nome) REFERENCES categoria(cat_nome)
);

CREATE TABLE pedido (
    pdd_id VARCHAR(10) PRIMARY KEY,
    pdd_data DATE NOT NULL,              -- NOVO: Data do pedido
    pdd_valor DOUBLE(10, 2) NOT NULL,    -- NOVO: Valor total
    pdd_status VARCHAR(50) NOT NULL,     -- NOVO: Status (Pago, Enviado, etc)
    cli_cpf VARCHAR(11) NOT NULL,        -- NOVO: Ligação de quem é o Cliente
    CONSTRAINT fk_pedido_cliente
    FOREIGN KEY (cli_cpf) REFERENCES cliente(cli_cpf)
);

-- Tabela associativa entre Pedido e Produto (A sua antiga 'providenciar')
CREATE TABLE providenciar (
    prov_id_relacao INTEGER PRIMARY KEY AUTO_INCREMENT,
    pdd_id VARCHAR(10),
    pdt_sku VARCHAR(10),
    CONSTRAINT fk_prov_pedido FOREIGN KEY (pdd_id) REFERENCES pedido(pdd_id),
    CONSTRAINT fk_prov_produto FOREIGN KEY (pdt_sku) REFERENCES produto(pdt_sku)
);

DROP TABLE fornecedor;
DROP TABLE categoria;
DROP TABLE cliente;
DROP TABLE providenciar;
DROP TABLE produto;
DROP TABLE pedido;

select * from produto;
select * from fornecedor;
select * from categoria;
select * from pedido;
Select * from providenciar;
Select * from cliente;