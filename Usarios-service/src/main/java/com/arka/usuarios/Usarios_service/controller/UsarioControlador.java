package com.arka.usuarios.Usarios_service.controller;

import com.arka.usuarios.Usarios_service.entities.Usuario;
import com.arka.usuarios.Usarios_service.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios(){
        return usuarioServicio.obtenerTodosLosUsuarios();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id){
        if (usuarioServicio.obtenerUsuarioPorId(id) != null ){
            usuarioServicio.eliminarUsuario(id);
            return ResponseEntity.ok("Se ha eliminado el usuario con Id" + id);
        }else {
            return ResponseEntity.status(404).body("No se encontro el usuario con id : " + id);
        }
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id){
        return usuarioServicio.obtenerUsuarioPorId(id);
    }
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioServicio.crearUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);  // ✅ Devuelve el usuario en el cuerpo de la respuesta
    }

    @GetMapping("/buscar")
    public List<Usuario> buscarUsuariosPorNombre(@RequestParam String nombre){
        return usuarioServicio.buscarUsuariosPorNombre(nombre);
    }

    @GetMapping("/ordenados")
    public List<Usuario> listarUsuariosOrdenados(){
        return usuarioServicio.listarUsuariosOrdenados();
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id,@RequestBody Usuario usuario){
        return usuarioServicio.actualizarUsuario(id,usuario);
    }
}
