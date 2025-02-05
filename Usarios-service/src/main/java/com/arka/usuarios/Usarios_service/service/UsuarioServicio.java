package com.arka.usuarios.Usarios_service.service;

import com.arka.usuarios.Usarios_service.entities.Usuario;
import com.arka.usuarios.Usarios_service.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        if (usuarioRepositorio.existsById(id)) {
            usuario.setId(id);
            return usuarioRepositorio.save(usuario);
        }
        return null;
    }

    public void eliminarUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
    }

    public List<Usuario> buscarUsuariosPorNombre(String nombre){
        return usuarioRepositorio.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Usuario> listarUsuariosOrdenados(){
        return usuarioRepositorio.findAllByOrderByNombreAsc();
    }


}
