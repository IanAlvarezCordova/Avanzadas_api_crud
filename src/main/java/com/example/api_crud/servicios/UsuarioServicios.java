package com.example.api_crud.servicios;

import com.example.api_crud.modelos.Usuario;
import com.example.api_crud.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*componente de servicios de Spring gestiona la logica de negocio*/
@Service
public class UsuarioServicios {
    /*inyectar automaticamente una instancia de UsuarioRepositorio para acceder a la BD*/
    @Autowired
    private UsuarioRepositorio repositorio;

    /*Métodos del servicio para obtener todos los usuarios*/
    public List<Usuario> obtenerTodos() {
        return repositorio.findAll();
    }

    /*Método para guardar un nuevo usuario*/
    public Usuario guardarUsuario(Usuario usuario) {
        return repositorio.save(usuario);
    }
    /*Método para obtener un usuario por su ID*/
    public Object obtenerPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    /*Método para actualizar un usuario existente*/
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        // Verificar si el usuario con el ID proporcionado existe
        Usuario usuario1 = (Usuario) repositorio.findById(id).orElse(null);
        if (usuario1 != null) {
            usuario1.setNombre(usuario.getNombre());
            usuario1.setEmail(usuario.getEmail());
            return (Usuario) repositorio.save(usuario1);
        }
        return null; // o lanzar una excepción si no se encuentra el usuario
    }
    /*Método para eliminar un usuario por su ID*/
    public void eliminarUsuario(Long id) {
        repositorio.deleteById(id);
    }

}
