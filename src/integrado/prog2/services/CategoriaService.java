/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrado.prog2.services;

import integrado.prog2.entities.Categoria;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Categoria.
 * Se encarga de almacenar las categorías en memoria y aplicar validaciones.
 */
public class CategoriaService {
    private List<Categoria> categorias;

    public CategoriaService() {
        this.categorias = new ArrayList<>();
    }

    /**
     * Listar todas las categorías no eliminadas.
     */
    public List<Categoria> listar() {
        List<Categoria> activas = new ArrayList<>();
        for (Categoria c : categorias) {
            if (!c.isEliminado()) {
                activas.add(c);
            }
        }
        return activas;
    }

    /**
     * Crear una nueva categoría validando que el nombre no esté vacío
     * y que no exista otra categoría con el mismo nombre.
     */
    public Categoria crear(String nombre, String descripcion) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        for (Categoria c : categorias) {
            if (c.getNombre().equalsIgnoreCase(nombre) && !c.isEliminado()) {
                throw new IllegalArgumentException("Ya existe una categoría con ese nombre");
            }
        }
        Categoria nueva = new Categoria(nombre, descripcion);
        categorias.add(nueva);
        return nueva;
    }

    /**
     * Editar una categoría existente por ID.
     * Si no existe o está eliminada, se lanza excepción.
     */
    
    public boolean editar(int id, String nuevoNombre, String nuevaDescripcion) {
    Categoria categoria = buscarPorId(id);
    if (categoria == null || categoria.isEliminado()) {
        System.out.println("No se encontró la categoría con ID " + id);
        return false;
    }
    if (nuevoNombre != null && !nuevoNombre.isBlank()) {
        categoria.setNombre(nuevoNombre);
    }
    if (nuevaDescripcion != null && !nuevaDescripcion.isBlank()) {
        categoria.setDescripcion(nuevaDescripcion);
    }
    return true;
}





    /**
     * Eliminar lógicamente una categoría por ID.
     * No se borra físicamente, solo se marca como eliminada.
     */
    public void eliminar(int id) {
        Categoria categoria = buscarPorId(id);
        if (categoria == null || categoria.isEliminado()) {
            throw new IllegalArgumentException("Categoría no encontrada o ya eliminada");
        }
        categoria.setEliminado(true);
    }

    /**
     * Buscar una categoría por ID dentro de la colección.
     */
    public Categoria buscarPorId(int id) {
        for (Categoria c : categorias) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}

