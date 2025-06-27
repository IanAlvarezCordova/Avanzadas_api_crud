package com.example.api_crud.controladores;

import com.example.api_crud.modelos.Usuario;
import com.example.api_crud.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
/**Deinifir la ruta para los endpoints*/
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    /*Inyeccion del servicio*/
    @Autowired
    private UsuarioServicios servicio;

    /*Crear los endpoints*/
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return servicio.guardarUsuario(usuario);
    }
    /*Endpoint para obtener todos los usuarios*/
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return servicio.obtenerTodos();
    }

    /*Endpoint para obtener el usuario por id*/
    @RequestMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = (Usuario) servicio.obtenerPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*Actualizar Usuario*/
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioActualizado = servicio.actualizarUsuario(id, usuario);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok(usuarioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*Eliminar Usuario*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        servicio.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
