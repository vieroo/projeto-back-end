CREATE TABLE pedido (
    id BIGSERIAL PRIMARY KEY,

    cliente_id BIGINT NOT NULL,

    unidade_id BIGINT NOT NULL,

    status VARCHAR(50) NOT NULL,

    canal_pedido VARCHAR(50) NOT NULL,

    total NUMERIC(10,2) NOT NULL,

    CONSTRAINT fk_pedido_cliente
        FOREIGN KEY (cliente_id)
            REFERENCES usuario(id),

    CONSTRAINT fk_pedido_unidade
        FOREIGN KEY (unidade_id)
            REFERENCES unidade(id)
);