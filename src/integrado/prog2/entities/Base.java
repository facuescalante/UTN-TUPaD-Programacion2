/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrado.prog2.entities;

import java.time.LocalDateTime;

/**
 * Clase abstracta que sirve como base para todas las entidades del sistema.
 * Contiene atributos comunes como id, eliminado y fecha de creación.
 * El id se genera automáticamente mediante un contador estático.
 */
public abstract class Base {
    private static int contador = 0; // contador global para IDs únicos
    protected int id;
    protected boolean eliminado;
    protected LocalDateTime createdAt;

    /**
     * Constructor vacío que inicializa los valores por defecto.
     * - id: autogenerado
     * - eliminado: false
     * - createdAt: fecha y hora actual
     */
    public Base() {
        this.id = ++contador;
        this.eliminado = false;
        this.createdAt = LocalDateTime.now();
    }

    // Métodos de acceso
    public int getId() { return id; }
    public boolean isEliminado() { return eliminado; }
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Cada clase hija debe implementar su propio toString()
    @Override
    public abstract String toString();
}
