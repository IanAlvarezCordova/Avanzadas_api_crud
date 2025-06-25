package com.example.api_crud.repositorios;

import com.example.api_crud.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio para manejar las operaciones CRUD de la entidad Usuario
public interface UsuarioRepositorio extends JpaRepository <Usuario, Long> {

}
