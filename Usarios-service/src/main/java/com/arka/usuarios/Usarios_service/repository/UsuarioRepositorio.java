package com.arka.usuarios.Usarios_service.repository;

import com.arka.usuarios.Usarios_service.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    List<Usuario> findAllByOrderByNombreAsc();
 }
