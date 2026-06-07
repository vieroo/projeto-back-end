CREATE TABLE auditorias (
        id BIGSERIAL PRIMARY KEY,
        usuario VARCHAR(255) NOT NULL,
        acao VARCHAR(100) NOT NULL,
        entidade VARCHAR(100) NOT NULL,
        entidade_id BIGINT,
        data_hora TIMESTAMP NOT NULL
);