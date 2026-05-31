CREATE TABLE item_pedido (
        id BIGSERIAL PRIMARY KEY,

        quantidade INTEGER NOT NULL,

        pedido_id BIGINT NOT NULL,

        produto_id BIGINT NOT NULL,

        CONSTRAINT fk_item_pedido_pedido
            FOREIGN KEY (pedido_id)
                REFERENCES pedido(id),

        CONSTRAINT fk_item_pedido_produto
            FOREIGN KEY (produto_id)
                REFERENCES produto(id)
);