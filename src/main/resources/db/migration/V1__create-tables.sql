CREATE TABLE usuario (
    id VARCHAR NOT NULL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    sexo CHAR(1) NOT NULL,
    CPF CHAR(14) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(250) NOT NULL
);

CREATE TABLE onibus (
    id VARCHAR NOT NULL PRIMARY KEY,
    placa VARCHAR(7) NOT NULL UNIQUE,
    modelo VARCHAR(50) NOT NULL,
    capacidade INTEGER NOT NULL
);

CREATE TABLE passagem (
    id VARCHAR NOT NULL PRIMARY KEY,
    id_onibus VARCHAR NOT NULL,
    id_usuario VARCHAR NOT NULL,
    data_viagem DATE NOT NULL,
    hora_viagem TIME NOT NULL,
    preco DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (id_onibus) REFERENCES onibus(id),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);