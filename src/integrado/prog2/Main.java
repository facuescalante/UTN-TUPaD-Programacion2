package integrado.prog2;

import integrado.prog2.entities.*;
import integrado.prog2.enums.*;
import integrado.prog2.services.*;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

    private static CategoriaService categoriaService = new CategoriaService();
    private static ProductoService productoService = new ProductoService();
    private static UsuarioService usuarioService = new UsuarioService();
    private static PedidoService pedidoService = new PedidoService();

    static {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Categorías");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1 ->
                    menuCategorias();
                case 2 ->
                    menuProductos();
                case 3 ->
                    menuUsuarios();
                case 4 ->
                    menuPedidos();
                case 0 ->
                    salir = true;
                default ->
                    System.out.println("Opción inválida");
            }
        }
    }

    // ================== SUBMENÚ CATEGORÍAS ==================
    private static void menuCategorias() {
        System.out.println("\n--- Gestión de Categorías ---");
        System.out.println("1. Listar");
        System.out.println("2. Crear");
        System.out.println("3. Editar");
        System.out.println("4. Eliminar");
        System.out.print("Seleccione una opción: ");
        int opcion = leerEntero();

        try {
            switch (opcion) {
                case 1 -> {
                    if (categoriaService.listar().isEmpty()) {
                        System.out.println("\n--- RESULTADO ---");
                        System.out.println("No hay categorías cargadas.");
                        System.out.println("-----------------\n");
                    } else {
                        System.out.println("\n=== LISTA DE CATEGORÍAS ===");
                        categoriaService.listar().forEach(System.out::println);
                        System.out.println("===========================\n");
                    }
                }
                case 2 -> {
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = scanner.nextLine();
                    Categoria c = categoriaService.crear(nombre, descripcion);
                    System.out.println("\nCategoría creada: " + c + "\n");
                }
                case 3 -> {
                    System.out.print("ID a editar: ");
                    int id = leerEntero();
                    System.out.print("Nuevo nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Nueva descripción: ");
                    String descripcion = scanner.nextLine();
                    if (categoriaService.editar(id, nombre, descripcion)) {
                        System.out.println("\nCategoría editada correctamente.\n");
                    }
                }
                case 4 -> {
                    System.out.print("ID a eliminar: ");
                    int id = leerEntero();
                    categoriaService.eliminar(id);
                    System.out.println("\nCategoría eliminada correctamente.\n");
                }
                default ->
                    System.out.println("Opción inválida");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage() + "\n");
        } catch (Exception e) {
            System.out.println("\nOcurrió un error inesperado: " + e.getMessage() + "\n");
        }
    }

    // ================== SUBMENÚ PRODUCTOS ==================
    private static void menuProductos() {
        System.out.println("\n--- Gestión de Productos ---");
        System.out.println("1. Listar");
        System.out.println("2. Crear");
        System.out.println("3. Editar");
        System.out.println("4. Eliminar");
        System.out.print("Seleccione una opción: ");
        int opcion = leerEntero();

        switch (opcion) {
            case 1 -> {
                if (productoService.listar().isEmpty()) {
                    System.out.println("\n--- RESULTADO ---");
                    System.out.println("No hay productos cargados.");
                    System.out.println("-----------------\n");
                } else {
                    System.out.println("\n=== LISTA DE PRODUCTOS ===");
                    productoService.listar().forEach(System.out::println);
                    System.out.println("==========================\n");
                }
            }
            case 2 -> {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Precio: ");
                double precio = leerDouble();
                System.out.print("Stock: ");
                int stock = leerEntero();
                System.out.print("Descripción: ");
                String descripcion = scanner.nextLine();
                System.out.print("Imagen: ");
                String imagen = scanner.nextLine();
                System.out.print("Disponible (Sí/No): ");
                String disponibleStr = scanner.nextLine().trim().toLowerCase();
                boolean disponible = disponibleStr.equals("sí") || disponibleStr.equals("si");
                System.out.print("ID Categoría: ");
                int idCategoria = leerEntero();

                try {
                    Categoria categoria = categoriaService.buscarPorId(idCategoria);
                    Producto p = productoService.crear(nombre, precio, stock, descripcion, imagen, disponible, categoria);
                    System.out.println("\nProducto creado: " + p + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println("\nError: " + e.getMessage() + "\n");
                }
            }
            case 3 -> {
                System.out.print("ID a editar: ");
                int id = leerEntero();
                System.out.print("Nuevo nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Nuevo precio: ");
                double precio = leerDouble();
                System.out.print("Nuevo stock: ");
                int stock = leerEntero();
                if (productoService.editar(id, nombre, precio, stock)) {
                    System.out.println("\nProducto editado correctamente.\n");
                }
            }
            case 4 -> {
                System.out.print("ID a eliminar: ");
                int id = leerEntero();
                productoService.eliminar(id);
                System.out.println("\nProducto eliminado correctamente.\n");
            }
        }
    }

    // ================== SUBMENÚ USUARIOS ==================
    private static void menuUsuarios() {
        System.out.println("\n--- Gestión de Usuarios ---");
        System.out.println("1. Listar");
        System.out.println("2. Crear");
        System.out.println("3. Editar");
        System.out.println("4. Eliminar");
        System.out.print("Seleccione una opción: ");
        int opcion = leerEntero();

        switch (opcion) {
            case 1 -> {
                if (usuarioService.listar().isEmpty()) {
                    System.out.println("\n--- RESULTADO ---");
                    System.out.println("No hay usuarios cargados.");
                    System.out.println("-----------------\n");
                } else {
                    System.out.println("\n=== LISTA DE USUARIOS ===");
                    usuarioService.listar().forEach(System.out::println);
                    System.out.println("=========================\n");
                }
            }
            case 2 -> {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();
                System.out.print("Mail: ");
                String mail = scanner.nextLine();
                System.out.print("Celular: ");
                String celular = scanner.nextLine();
                System.out.print("Contraseña: ");
                String contrasena = scanner.nextLine(); // ✅ usar "contrasena" sin ñ

                Usuario u = usuarioService.crear(nombre, apellido, mail, celular, contrasena);
                System.out.println("\nUsuario creado: " + u + "\n");
            }

            case 3 -> {
                System.out.print("ID a editar: ");
                int id = leerEntero();
                System.out.print("Nuevo nombre: ");
                String nombre = scanner.nextLine();
                usuarioService.editar(id, nombre, null, null, null, null, null);
                System.out.println("\nUsuario editado correctamente.\n");
            }
            case 4 -> {
                System.out.print("ID a eliminar: ");
                int id = leerEntero();
                usuarioService.eliminar(id);
                System.out.println("\nUsuario eliminado correctamente.\n");
            }
        }
    }

    // ================== SUBMENÚ PEDIDOS ==================
    private static void menuPedidos() {
        System.out.println("\n--- Gestión de Pedidos ---");
        System.out.println("1. Listar");
        System.out.println("2. Crear");
        System.out.println("3. Agregar Detalle");
        System.out.println("4. Cambiar Estado");
        System.out.println("5. Definir Forma de Pago");
        System.out.println("6. Eliminar");
        System.out.print("Seleccione una opción: ");
        int opcion = leerEntero();

        switch (opcion) {
            case 1 -> {
                if (pedidoService.listar().isEmpty()) {
                    System.out.println("\n--- RESULTADO ---");
                    System.out.println("No hay pedidos cargados.");
                    System.out.println("-----------------\n");
                } else {
                    System.out.println("\n=== LISTA DE PEDIDOS ===");
                    pedidoService.listar().forEach(System.out::println);
                    System.out.println("========================\n");
                }
            }
            case 2 -> {
                System.out.print("ID Usuario: ");
                int idUsuario = leerEntero();
                try {
                    Usuario usuario = usuarioService.buscarPorId(idUsuario); // ahora público
                    Pedido p = pedidoService.crear(usuario);
                    System.out.println("\nPedido creado: " + p + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println("\nError: " + e.getMessage() + "\n");
                }
            }

            case 3 -> {
                System.out.print("ID Pedido: ");
                int pedidoId = leerEntero();
                System.out.print("Cantidad: ");
                int cantidad = leerEntero();

                if (productoService.listar().isEmpty()) {
                    System.out.println("\nError: No hay productos cargados. Cree un producto primero.\n");
                    return;
                }

                System.out.println("\n=== LISTA DE PRODUCTOS ===");
                productoService.listar().forEach(System.out::println);
                System.out.println("==========================\n");

                System.out.print("ID Producto: ");
                int idProducto = leerEntero();
                Producto producto = productoService.buscarPorId(idProducto);

                try {
                    pedidoService.agregarDetalle(pedidoId, cantidad, producto);
                    System.out.println("\nDetalle agregado correctamente.\n");
                } catch (IllegalArgumentException e) {
                    System.out.println("\nError: " + e.getMessage() + "\n");
                }
            }

            case 4 -> {
                System.out.print("ID Pedido: ");
                int pedidoId = leerEntero();
                pedidoService.cambiarEstado(pedidoId, Estado.CONFIRMADO);
                System.out.println("\nEstado cambiado correctamente.\n");
            }
            case 5 -> {
                System.out.print("ID Pedido: ");
                int pedidoId = leerEntero();

                System.out.println("\nSeleccione forma de pago:");
                System.out.println("1. Tarjeta");
                System.out.println("2. Efectivo");
                System.out.println("3. Transferencia");
                System.out.print("Opción: ");
                int opcionPago = leerEntero();

                FormaPago formaPago;
                switch (opcionPago) {
                    case 1 ->
                        formaPago = FormaPago.TARJETA;
                    case 2 ->
                        formaPago = FormaPago.EFECTIVO;
                    case 3 ->
                        formaPago = FormaPago.TRANSFERENCIA;
                    default -> {
                        System.out.println("Opción inválida");
                        return;
                    }
                }

                try {
                    pedidoService.definirFormaPago(pedidoId, formaPago);
                    System.out.println("\nForma de pago definida correctamente.\n");
                } catch (IllegalArgumentException e) {
                    System.out.println("\nError: " + e.getMessage() + "\n");
                }
            }

            case 6 -> {
                System.out.print("ID Pedido: ");
                int pedidoId = leerEntero();
                pedidoService.eliminar(pedidoId);
                System.out.println("\nPedido eliminado correctamente.\n");
            }
            default ->
                System.out.println("Opción inválida");
        }
    }

    // ================== MÉTODOS DE VALIDACIÓN ==================
    private static int leerEntero() {
        while (true) {
            String input = scanner.nextLine();
            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            } else {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }

    private static double leerDouble() {
        while (true) {
            String input = scanner.nextLine();
            if (input.matches("\\d+(\\.\\d+)?")) {
                return Double.parseDouble(input);
            } else {
                System.out.print("Entrada inválida. Ingrese un número decimal: ");
            }
        }
    }
}
