/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrado.prog2.services;

import integrado.prog2.entities.Pedido;
import integrado.prog2.entities.Producto;
import integrado.prog2.entities.Usuario;
import integrado.prog2.enums.Estado;
import integrado.prog2.enums.FormaPago;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Pedido.
 * Se encarga de almacenar los pedidos en memoria y aplicar validaciones.
 */
public class PedidoService {
    private final List<Pedido> pedidos;

    public PedidoService() {
        this.pedidos = new ArrayList<>();
    }

    /**
     * Listar todos los pedidos no eliminados.
     */
    public List<Pedido> listar() {
        List<Pedido> activos = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (!p.isEliminado()) {
                activos.add(p);
            }
        }
        return activos;
    }

    /**
     * Crear un nuevo pedido asociado a un usuario.
     * El pedido inicia con estado PENDIENTE y total = 0.
     */
    public Pedido crear(Usuario usuario) {
        if (usuario == null || usuario.isEliminado()) {
            throw new IllegalArgumentException("Usuario inválido");
        }
        Pedido nuevo = new Pedido(usuario);
        pedidos.add(nuevo);
        usuario.agregarPedido(nuevo); // relación 1:N
        return nuevo;
    }

    /**
     * Agregar un detalle a un pedido existente.
     * Valida cantidad y stock del producto.
     */
    public void agregarDetalle(int pedidoId, int cantidad, Producto producto) {
        Pedido pedido = buscarPorId(pedidoId);
        if (pedido == null || pedido.isEliminado()) {
            throw new IllegalArgumentException("Pedido no encontrado o eliminado");
        }
        pedido.addDetallePedido(cantidad, producto);
    }

    /**
     * Cambiar el estado de un pedido.
     */
    public void cambiarEstado(int pedidoId, Estado nuevoEstado) {
        Pedido pedido = buscarPorId(pedidoId);
        if (pedido == null || pedido.isEliminado()) {
            throw new IllegalArgumentException("Pedido no encontrado o eliminado");
        }
        pedido.setEstado(nuevoEstado);
    }

    /**
     * Definir la forma de pago de un pedido.
     */
    public void definirFormaPago(int pedidoId, FormaPago formaPago) {
        Pedido pedido = buscarPorId(pedidoId);
        if (pedido == null || pedido.isEliminado()) {
            throw new IllegalArgumentException("Pedido no encontrado o eliminado");
        }
        pedido.setFormaPago(formaPago);
    }

    /**
     * Eliminar lógicamente un pedido por ID.
     */
    public void eliminar(int id) {
        Pedido pedido = buscarPorId(id);
        if (pedido == null || pedido.isEliminado()) {
            throw new IllegalArgumentException("Pedido no encontrado o ya eliminado");
        }
        pedido.setEliminado(true);
    }

    /**
     * Buscar un pedido por ID dentro de la colección.
     */
    private Pedido buscarPorId(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
