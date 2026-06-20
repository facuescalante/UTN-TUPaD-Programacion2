package integrado.prog2.entities;

import java.util.ArrayList;
import java.util.List;
import integrado.prog2.enums.Rol;

public class Usuario extends Base {
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasena;
    private Rol rol;
    private boolean eliminado;

    // Relación 1:N con Pedido
    private List<Pedido> pedidos = new ArrayList<>();

    public Usuario(String nombre, String apellido, String mail, String celular, String contrasena) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contrasena = contrasena;
        this.rol = Rol.USUARIO;
        this.eliminado = false;
    }

    // --- Getters y Setters ---
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public boolean isEliminado() { return eliminado; }
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }

    // --- Relación con Pedido ---
    public void agregarPedido(Pedido pedido) {
        if (pedido != null) {
            pedidos.add(pedido);
        }
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    @Override
    public String toString() {
        return "Usuario [ID=" + id + ", Nombre=" + nombre + " " + apellido +
               ", Mail=" + mail + ", Rol=" + rol + "]";
    }
}
