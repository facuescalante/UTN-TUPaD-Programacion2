/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrado.prog2.exception;

/**
 * Excepción personalizada para indicar que un producto
 * no tiene stock suficiente para satisfacer un pedido.
 * Se lanza al intentar agregar un detalle con cantidad mayor al stock disponible.
 */
public class StockInvalidoException extends RuntimeException {
    public StockInvalidoException(String mensaje) {
        super(mensaje);
    }
}
