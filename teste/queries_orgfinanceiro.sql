-- Criar database
DROP DATABASE IF EXISTS orgfinancas;
CREATE DATABASE IF NOT EXISTS orgfinancas;
USE orgfinancas;

-- Criar tabelas
DROP TABLE IF EXISTS tb_pessoa;
CREATE TABLE IF NOT EXISTS tb_pessoa (
	id_pessoa INT AUTO_INCREMENT PRIMARY KEY,
    nm_nome VARCHAR(100) NOT NULL,
    dt_inclusao DATETIME NOT NULL,
    dt_alteracao DATETIME NULL
);

DROP TABLE IF EXISTS tb_gasto_categoria;
CREATE TABLE IF NOT EXISTS tb_gasto_categoria (
	id_gasto_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nm_nome VARCHAR(50) NOT NULL,
    dt_inclusao DATETIME NOT NULL,
    dt_alteracao DATETIME NULL
);

DROP TABLE IF EXISTS tb_gasto;
CREATE TABLE IF NOT EXISTS tb_gasto (
	id_gasto INT AUTO_INCREMENT PRIMARY KEY,
    id_pessoa INT NOT NULL,
    id_gasto_categoria INT NOT NULL,
    dt_emissao DATE NOT NULL,
    nr_valor DECIMAL(15,2) NOT NULL,
    tx_descricao VARCHAR(500),
    dt_inclusao DATETIME NOT NULL,
    dt_alteracao DATETIME NULL,
    CONSTRAINT fk01_pessoa FOREIGN KEY (id_pessoa) REFERENCES tb_pessoa(id_pessoa),
    CONSTRAINT fk02_gasto_categoria FOREIGN KEY (id_gasto_categoria) REFERENCES tb_gasto_categoria(id_gasto_categoria)
);

-- Populando tabelas
INSERT INTO orgfinancas.tb_pessoa (nm_nome, dt_inclusao)
VALUES 	('João Silva', current_timestamp()),
		('José de Souza', current_timestamp()),
		('Maria Oliveira', current_timestamp()),
		('Antônio Oliveira', current_timestamp());

INSERT INTO orgfinancas.tb_gasto_categoria (nm_nome, dt_inclusao)
VALUES	('CONTA', current_timestamp()),
		('ALIMENTAÇÃO', current_timestamp()),
		('TRANSPORTE', current_timestamp()),
		('SAÚDE', current_timestamp()),
		('LAZER', current_timestamp()),
		('PETS', current_timestamp()),
		('OUTROS', current_timestamp());

INSERT INTO tb_gasto (id_pessoa, id_gasto_categoria, dt_emissao, nr_valor, tx_descricao, dt_inclusao)
VALUES
-- João Silva 
    (1, 1, '2023-07-01', 150.73, 'Pagamento de conta de luz', current_timestamp()),
    (1, 2, '2023-07-05', 250.49, 'Compras no supermercado', current_timestamp()),
    (1, 3, '2023-07-10', 60.15, 'Passagem de ônibus', current_timestamp()),
    (1, 4, '2023-06-15', 120.89, 'Consulta médica', current_timestamp()),
    (1, 5, '2023-05-25', 80.02, 'Cinema com amigos', current_timestamp()),
-- José de Souza
    (2, 2, '2023-07-03', 200.31, 'Compras no supermercado', current_timestamp()),
    (2, 3, '2023-07-07', 50.99, 'Passagem de ônibus', current_timestamp()),
    (2, 6, '2023-06-12', 40.27, 'Compra de ração para cachorro', current_timestamp()),
    (2, 1, '2023-06-20', 180.55, 'Pagamento de conta de água', current_timestamp()),
    (2, 7, '2023-05-15', 300.68, 'Gastos diversos', current_timestamp()),
-- Maria Oliveira
    (3, 1, '2023-07-02', 150.77, 'Pagamento de conta de luz', current_timestamp()),
    (3, 4, '2023-07-08', 120.32, 'Consulta médica', current_timestamp()),
    (3, 5, '2023-06-14', 80.65, 'Jantar em restaurante', current_timestamp()),
    (3, 6, '2023-06-25', 40.98, 'Compra de ração para cachorro', current_timestamp()),
    (3, 7, '2023-05-20', 300.74, 'Gastos diversos', current_timestamp()),
-- Antônio Oliveira
    (4, 2, '2023-07-04', 250.38, 'Compras no supermercado', current_timestamp()),
    (4, 3, '2023-07-11', 60.42, 'Passagem de ônibus', current_timestamp()),
    (4, 4, '2023-06-17', 120.56, 'Consulta médica', current_timestamp()),
    (4, 5, '2023-06-23', 80.85, 'Cinema com amigos', current_timestamp()),
    (4, 1, '2023-05-30', 180.91, 'Pagamento de conta de água', current_timestamp());

-- Consultas
SELECT * FROM orgfinancas.tb_pessoa;
SELECT * FROM orgfinancas.tb_gasto_categoria;
SELECT * FROM orgfinancas.tb_gasto;