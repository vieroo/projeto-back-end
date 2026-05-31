CREATE TABLE fidelidade (
    id BIGSERIAL PRIMARY KEY,

    cliente_id BIGINT NOT NULL UNIQUE,

    pontos INTEGER NOT NULL DEFAULT 0,

    CONSTRAINT fk_fidelidade_cliente
        FOREIGN KEY (cliente_id)
            REFERENCES usuario(id)
);