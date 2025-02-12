package com.arka.usuarios.Usarios_service.controllers;

import com.arka.usuarios.Usarios_service.controller.UsarioControlador;
import com.arka.usuarios.Usarios_service.entities.Usuario;
import com.arka.usuarios.Usarios_service.service.UsuarioServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;



@WebMvcTest(com.arka.usuarios.Usarios_service.controller.UsarioControlador.class)

public class PruebasPeticiones {

    @Autowired
    private MockMvc mockMvc; //simulare peticion http


    @MockBean  // Simula el servicio que usa el controlador
    private UsuarioServicio usarioService;

    //puerba crear usuario
    @Test
    void testCrearUsuario() throws Exception {
        String usuarioJson = """
        {
            "nombre": "Jose Ramirez",
            "email": "miguel@example.com",
            "password": "12345"
        }
    """;

        // Simular respuesta del servicio
        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(5L);  // Simula un ID generado por la BD
        usuarioMock.setNombre("Jose Ramirez");
        usuarioMock.setEmail("miguel@example.com");
        usuarioMock.setPassword("12345");

        // Simular que el servicio devuelve este usuario al llamarlo
        when(usarioService.crearUsuario(any(Usuario.class))).thenReturn(usuarioMock);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                .andExpect(status().isOk())  // ✅ Espera 200 OK (según tu API)
                .andExpect(content().string(containsString("Jose Ramirez")));  // ✅ Verifica que la respuesta contiene el nombre
    }

    //prueba Get
    @Test
    void testObtenerUsuarios() throws Exception {
        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));  // Esperamos JSON como respuesta
    }

    //Prueba Put
    @Test
    void testActualizarUsuario() throws Exception {
        String usuarioActualizado = """
        {
            "nombre": "Jose Ramirez actualizado",
            "email": "miguel@example.com",
            "password": "12345"
        }
    """;

        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(3L);  // Simula un ID existente en la BD
        usuarioMock.setNombre("Jose Ramirez actualizado");
        usuarioMock.setEmail("miguel@example.com");
        usuarioMock.setPassword("12345");
        when(usarioService.actualizarUsuario(eq(3L), any(Usuario.class)))
                .thenReturn(usuarioMock);
        mockMvc.perform(put("/usuarios/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioActualizado))
                .andExpect(status().isOk())  // ✅ Espera 200 OK
                .andExpect(content().string(containsString("Jose Ramirez actualizado")));
    }

    //Prueba Buscar usuarioByNombre
    @Test
    void testBuscarUsuarioPorNombre() throws Exception {
        mockMvc.perform(get("/usuarios/buscar")
                        .param("nombre", "Juan"))  // Simula GET /usuarios/buscar?nombre=Juan
                .andExpect(status().isOk())  // Esperamos 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));  // Esperamos JSON como respuesta
    }

    //Prueba Delate
    @Test
    void testEliminarUsuario() throws Exception {
        when(usarioService.obtenerUsuarioPorId(1L)).thenReturn(new Usuario());

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isOk())  // ✅ Ahora espera 200 OK
                .andExpect(content().string("Se ha eliminado el usuario con Id1"));
    }
}
