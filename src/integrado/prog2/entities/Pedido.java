/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrado.prog2.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import integrado.prog2.enums.Estado;
import integrado.prog2.enums.FormaPago;
import integrado.prog2.interfaces.Calculable;

public class Pedido extends Base implements Calculable {
    private static int detalleIdCounter = 0;
    private LocalDate fecha;
    private Estado estado;
    private FormaPago formaPago;
    private double total;
    private List<DetallePedido> detalles;
    private Usuario usuario;
    private boolean eliminado;

    public Pedido(Usuario usuario) {
        super();
        this.usuario = usuario;
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.total = 0.0;
        this.detalles = new ArrayList<>();
        this.eliminado = false;
    }

    // --- Métodos de negocio ---
    public void addDetallePedido(int cantidad, Producto producto) {
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser > 0");
        if (producto.getStock() < cantidad) throw new IllegalArgumentException("Stock insuficiente");

        DetallePedido detalle = new DetallePedido(++detalleIdCounter, cantidad, producto);
        detalles.add(detalle);
        calcularTotal();
    }

    @Override
    public void calcularTotal() {
        total = detalles.stream().mapToDouble(DetallePedido::getSubtotal).sum();
    }

    // --- Getters y Setters necesarios ---
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public double getTotal() {
        return total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Pedido [ID=" + id + ", Usuario=" + usuario.getNombre() +
               ", Estado=" + estado + ", FormaPago=" + formaPago +
               ", Total=" + total + "]";
    }
}
