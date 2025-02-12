package com.arka.usuarios.Usarios_service.controllers;

import com.arka.usuarios.Usarios_service.controller.UsarioControlador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PruebaControlador {
    @Autowired
    private UsarioControlador usarioControlador;

    @Test
    void testInyeccionControlador(){
        assertThat(usarioControlador).isNotNull();
    }
}
