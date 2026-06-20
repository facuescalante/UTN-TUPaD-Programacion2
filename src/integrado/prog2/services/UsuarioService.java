/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrado.prog2.services;

import integrado.prog2.entities.Usuario;
import integrado.prog2.enums.Rol;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Usuario.
 * Se encarga de almacenar los usuarios en memoria y aplicar validaciones.
 */
public class UsuarioService {
    private List<Usuario> usuarios;

    public UsuarioService() {
        this.usuarios = new ArrayList<>();
    }

    /**
     * Listar todos los usuarios no eliminados.
     */
    public List<Usuario> listar() {
        List<Usuario> activos = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (!u.isEliminado()) {
                activos.add(u);
            }
        }
        return activos;
    }

    /**
     * Crear un nuevo usuario validando:
     * - Nombre y apellido no vacíos
     * - Mail no vacío y único
     */
    public Usuario crear(String nombre, String apellido, String mail, String celular, String contrasena) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        if (mail == null || mail.isBlank()) {
            throw new IllegalArgumentException("El mail no puede estar vacío");
        }
        for (Usuario u : usuarios) {
            if (u.getMail().equalsIgnoreCase(mail) && !u.isEliminado()) {
                throw new IllegalArgumentException("Ya existe un usuario con ese mail");
            }
        }

        Usuario nuevo = new Usuario(nombre, apellido, mail, celular, contrasena);
        usuarios.add(nuevo);
        return nuevo;
    }

    /**
     * Editar un usuario existente por ID.
     * Permite actualizar uno o más campos.
     * Si se modifica el mail, se valida que siga siendo único.
     */
    public void editar(int id, String nuevoNombre, String nuevoApellido, String nuevoMail,
                       String nuevoCelular, String nuevaContrasena, Rol nuevoRol) {
        Usuario usuario = buscarPorId(id);
        if (usuario == null || usuario.isEliminado()) {
            throw new IllegalArgumentException("Usuario no encontrado o eliminado");
        }

        if (nuevoNombre != null && !nuevoNombre.isBlank()) {
            usuario.setNombre(nuevoNombre);
        }
        if (nuevoApellido != null && !nuevoApellido.isBlank()) {
            usuario.setApellido(nuevoApellido);
        }
        if (nuevoMail != null && !nuevoMail.isBlank()) {
            for (Usuario u : usuarios) {
                if (u.getMail().equalsIgnoreCase(nuevoMail) && u.getId() != id && !u.isEliminado()) {
                    throw new IllegalArgumentException("Ya existe otro usuario con ese mail");
                }
            }
            usuario.setMail(nuevoMail);
        }
        if (nuevoCelular != null && !nuevoCelular.isBlank()) {
            usuario.setCelular(nuevoCelular);
        }
        if (nuevaContrasena != null && !nuevaContrasena.isBlank()) {
            usuario.setContrasena(nuevaContrasena);
        }
        if (nuevoRol != null) {
            usuario.setRol(nuevoRol);
        }
    }

    /**
     * Eliminar lógicamente un usuario por ID.
     */
    public void eliminar(int id) {
        Usuario usuario = buscarPorId(id);
        if (usuario == null || usuario.isEliminado()) {
            throw new IllegalArgumentException("Usuario no encontrado o ya eliminado");
        }
        usuario.setEliminado(true);
    }

    /**
     * Buscar un usuario por ID dentro de la colección.
     */
    public Usuario buscarPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }
}