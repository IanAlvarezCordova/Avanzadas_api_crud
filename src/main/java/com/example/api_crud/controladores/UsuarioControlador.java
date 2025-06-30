package com.example.api_crud.controladores;

import com.example.api_crud.modelos.Usuario;
import com.example.api_crud.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
// Habilitar CORS globalmente para este controlador
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicios servicio;

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return servicio.obtenerTodos();
    }

    // Crear un usuario
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return servicio.guardarUsuario(usuario);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        Usuario usuario = (Usuario) servicio.obtenerPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    // Editar un usuario por ID
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetalles) {
        Usuario usuarioExistente = (Usuario) servicio.obtenerPorId(id);

        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar los datos del usuario existente
        usuarioExistente.setNombre(usuarioDetalles.getNombre());
        usuarioExistente.setEmail(usuarioDetalles.getEmail());

        // Guardar los cambios
        Usuario usuarioActualizado = servicio.guardarUsuario(usuarioExistente);

        return ResponseEntity.ok(usuarioActualizado);
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        servicio.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}

