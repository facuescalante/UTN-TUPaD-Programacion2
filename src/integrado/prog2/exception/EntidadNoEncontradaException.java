/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrado.prog2.exception;

/**
 * Excepción personalizada para indicar que una entidad
 * no fue encontrada en la colección en memoria.
 * Se utiliza en los servicios cuando se busca por ID y no existe.
 */
public class EntidadNoEncontradaException extends RuntimeException {
    public EntidadNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}

