package com.example.api_crud.modelos;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;


/*Indica que esta clase es una entidad de JPA*/
@Entity
public class Usuario {
    /*Identificar la clave primaria*/
    @Id
    /*Generar el valor de la clave primaria de forma automática*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private String email;
    /*Constructor por defecto y otro con parámetros para inicializar los campos*/
    public Usuario() {
    }

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
    // Getters y Setters para acceder y modificar los campos de la entidad
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
