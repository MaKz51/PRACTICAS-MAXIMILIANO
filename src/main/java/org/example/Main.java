package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static Scanner teclado = new Scanner(System.in);
    public static Connection conexion;
    static GestorEnvios gestor = new GestorEnvios();

    static String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;database=SISTEMA_ENVIOS_POO;user=sa;password=Maxmi2017Th;encrypt=true;trustServerCertificate=true;";

    public static void main(String[] args) throws SQLException {

            conexion = DriverManager.getConnection(url);
            int opcion;
            do {
                mostrarMenu();
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        capturarNuevoPaquete();
                        break;
                    case 2:
                        gestor.verHistorial();
                        break;
                    case 3:
                        System.out.print("Ingrese el número de Guía a eliminar: ");
                        int guiaBorrar = teclado.nextInt();
                        gestor.eliminarPaquete(guiaBorrar);
                        break;
                    case 0:
                        System.out.println("Bye Bye");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (opcion != 0);

            conexion.close();

    }

    public static void mostrarMenu() {
        System.out.println("=== PAQUETERIA EL COYOTE===");
        System.out.println("1) Registrar un nuevo paquete");
        System.out.println("2) Ver historial y las ganancias");
        System.out.println("3) Eliminar un paquete (Cancelar envío)");
        System.out.println("0) Salir");
        System.out.print("Seleccionar su opción: ");
    }

    public static void capturarNuevoPaquete() throws SQLException {
        System.out.println("\n--- REGISTRO DE PAQUETE ---");
        System.out.print("Nombre del destinatario: ");
        String destinatario = teclado.nextLine();

        System.out.print("Peso del paquete en KG: ");
        double peso = teclado.nextDouble();

        System.out.println("\nTipos de Envío disponibles:");
        System.out.println("1. Estándar");
        System.out.println("2. Express");
        System.out.println("3. Internacional");
        System.out.print("Seleccione el tipo de envío: ");
        int tipoOpcion = teclado.nextInt();

        EstrategiaEnvio estrategiaSeleccionada;

        if (tipoOpcion == 1) {
            estrategiaSeleccionada = new EnvioEstandar();
        } else if (tipoOpcion == 2) {
            estrategiaSeleccionada = new EnvioExpress();
        } else if (tipoOpcion == 3) {
            estrategiaSeleccionada = new EnvioInternacional();
        } else {
            System.out.println("Opción inválida. Se asignará envío Estándar por defecto.");
            estrategiaSeleccionada = new EnvioEstandar();
        }

        Paquete nuevoPaquete = new PaqueteNormal(destinatario, peso, estrategiaSeleccionada);
        gestor.guardarPaquete(nuevoPaquete);
    }
}