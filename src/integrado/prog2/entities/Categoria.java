/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrado.prog2.entities;

import java.util.ArrayList;
import java.util.List;

public class Categoria extends Base {
    private String nombre;
    private String descripcion;
    private List<Producto> productos;

    public Categoria(String nombre, String descripcion) {
        super(); // genera ID automáticamente
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Categoria [ID=" + id + ", Nombre=" + nombre + ", Desc=" + descripcion + "]";
    }
}

