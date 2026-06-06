package com.raizesdonordeste.application.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PagamentoMockService {

    private final Random random = new Random();

    public boolean processarPagamento() {
        return random.nextDouble() > 0.3;
    }
}
