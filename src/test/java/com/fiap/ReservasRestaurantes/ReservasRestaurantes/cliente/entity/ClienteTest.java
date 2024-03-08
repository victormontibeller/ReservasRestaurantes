package com.fiap.ReservasRestaurantes.ReservasRestaurantes.cliente.entity;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class ClienteTest {
    
    @Test
    void clienteTest() {
        int a = 1;
        int b = 2;
        Assert.isTrue(a != b, "A should be different from B");
    }
}
