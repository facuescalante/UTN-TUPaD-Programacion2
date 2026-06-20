/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrado.prog2.entities;

/**
 * Representa un detalle dentro de un pedido.
 * Contiene cantidad, producto y subtotal calculado automáticamente.
 */
public class DetallePedido extends Base {
    private int cantidad;
    private double subtotal;
    private Producto producto;

    public DetallePedido(int id, int cantidad, Producto producto) {
        super();
        this.id = id;
        this.cantidad = cantidad;
        this.producto = producto;
        this.subtotal = calcularSubtotal();
    }

    /**
     * Calcula el subtotal en base a cantidad * precio del producto.
     */
    private double calcularSubtotal() {
        return cantidad * producto.getPrecio();
    }

    public double getSubtotal() { return subtotal; }

    @Override
    public String toString() {
        return "DetallePedido [ID=" + id + ", Producto=" + producto.getNombre() + 
               ", Cantidad=" + cantidad + ", Subtotal=" + subtotal + "]";
    }
}

