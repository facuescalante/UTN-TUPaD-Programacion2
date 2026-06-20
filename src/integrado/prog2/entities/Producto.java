/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrado.prog2.entities;

/**
 * Representa un producto del catálogo.
 * Relación N:1 con Categoria: cada producto pertenece a una categoría.
 */
public class Producto extends Base {
    private String nombre;
    private double precio;
    private int stock;
    private String descripcion;
    private String imagen;
    private boolean disponible;
    private Categoria categoria;
    private boolean eliminado;

    public Producto(String nombre, double precio, int stock, String descripcion,
                    String imagen, boolean disponible, Categoria categoria) {
        super();
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.disponible = disponible;
        this.categoria = categoria;
        this.eliminado = false;
    }

    // --- Getters y Setters ---
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public boolean isEliminado() { return eliminado; }
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }

    @Override
    public String toString() {
        return "Producto [ID=" + id + ", Nombre=" + nombre +
               ", Precio=" + precio + ", Stock=" + stock + "]";
    }
}
