CREATE TABLE estoque (
    id BIGSERIAL PRIMARY KEY,
    quantidade INTEGER NOT NULL,

    produto_id BIGINT NOT NULL,
    unidade_id BIGINT NOT NULL,

    CONSTRAINT fk_estoque_produto
        FOREIGN KEY (produto_id)
            REFERENCES produto(id),

    CONSTRAINT fk_estoque_unidade
        FOREIGN KEY (unidade_id)
            REFERENCES unidade(id)
);