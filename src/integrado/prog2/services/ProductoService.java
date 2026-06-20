/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrado.prog2.services;

import integrado.prog2.entities.Producto;
import integrado.prog2.entities.Categoria;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Producto. Se encarga de
 * almacenar los productos en memoria y aplicar validaciones.
 */
public class ProductoService {

    private List<Producto> productos;

    public ProductoService() {
        this.productos = new ArrayList<>();
    }

    /**
     * Listar todos los productos no eliminados.
     */
    public List<Producto> listar() {
        List<Producto> activos = new ArrayList<>();
        for (Producto p : productos) {
            if (!p.isEliminado()) {
                activos.add(p);
            }
        }
        return activos;
    }

    /**
     * Crear un nuevo producto validando: - Nombre no vacío - Precio >= 0 -
     * Stock >= 0 - Categoría existente y no eliminada
     */
    public Producto crear(String nombre, double precio, int stock, String descripcion, String imagen, boolean disponible, Categoria categoria) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (precio < 0 || stock < 0) {
            throw new IllegalArgumentException("Precio y stock deben ser >= 0");
        }
        if (categoria == null || categoria.isEliminado()) {
            throw new IllegalArgumentException("La categoría no existe o está eliminada");
        }

        Producto nuevo = new Producto(nombre, precio, stock, descripcion, imagen, disponible, categoria);
        productos.add(nuevo);
        return nuevo;
    }

    /**
     * Editar un producto existente por ID. Permite actualizar uno o más campos.
     */
    public boolean editar(int id, String nuevoNombre, Double nuevoPrecio, Integer nuevoStock) {
        Producto producto = buscarPorId(id);
        if (producto == null || producto.isEliminado()) {
            System.out.println("No se encontró el producto con ID " + id);
            return false;
        }
        if (nuevoNombre != null && !nuevoNombre.isBlank()) {
            producto.setNombre(nuevoNombre);
        }
        if (nuevoPrecio != null && nuevoPrecio >= 0) {
            producto.setPrecio(nuevoPrecio);
        }
        if (nuevoStock != null && nuevoStock >= 0) {
            producto.setStock(nuevoStock);
        }
        return true;
    }

    /**
     * Eliminar lógicamente un producto por ID.
     */
    public void eliminar(int id) {
        Producto producto = buscarPorId(id);
        if (producto == null || producto.isEliminado()) {
            throw new IllegalArgumentException("Producto no encontrado o ya eliminado");
        }
        producto.setEliminado(true);
    }

    /**
     * Buscar un producto por ID dentro de la colección.
     */
    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
