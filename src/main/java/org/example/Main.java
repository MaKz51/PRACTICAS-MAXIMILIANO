package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static PlataformaStreaming plataforma = new PlataformaStreaming();
    public static Connection conexion;

    static String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;database=STREAMING_POO;user=sa;password=Maxmi2017Th;encrypt=true;trustServerCertificate=true;";
    public static void main(String[] args) throws SQLException {
            conexion = DriverManager.getConnection(url);
            boolean ejecutar = true;

            do {
                System.out.println("\n-------Java Stream-------");
                System.out.println("1. Registrarse (Nuevo usuario)");
                System.out.println("2. Ver lista de usuarios");
                System.out.println("3. Editar meses de suscripción");
                System.out.println("4. Eliminar un usuario");
                System.out.println("5. Salir");
                System.out.print("¿Qué desea hacer?: ");
                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        registrarNuevo();
                        break;
                    case 2:
                        plataforma.imprimirReporte();
                        break;
                    case 3:
                        System.out.print("Ingrese el ID del usuario a editar: ");
                        int idEditar = sc.nextInt();
                        System.out.print("Ingrese la nueva cantidad de meses contratados: ");
                        int nuevosMeses = sc.nextInt();
                        plataforma.editarMesesPlan(idEditar, nuevosMeses);
                        break;
                    case 4:
                        System.out.print("Ingrese el ID del usuario a eliminar: ");
                        int idEliminar = sc.nextInt();
                        plataforma.eliminarUsuario(idEliminar);
                        break;
                    case 5:
                        System.out.println("Saliendo del programa... ¡Hasta luego!");
                        ejecutar = false;
                        break;
                    default:
                        System.out.println("Opción no válida, inténtelo de nuevo.");
                        break;
                }
            } while (ejecutar);

            conexion.close();


    }

    public static void registrarNuevo() throws SQLException {
        System.out.print("\nIngrese el correo electrónico: ");
        String correo = sc.nextLine();

        System.out.print("Ingrese los meses contratados: ");
        int meses = sc.nextInt();

        System.out.println("\nSeleccione un plan: ");
        System.out.println("1. Plan Básico ($5.00/mes)");
        System.out.println("2. Plan Estándar ($9.00/mes)");
        System.out.println("3. Plan Premium ($14.00/mes + $3.00 única vez)");
        System.out.print("Tipo de plan (1-3): ");
        int tipoPlan = sc.nextInt();

        PlanSuscripcion planSuscripcion = null;

        if (tipoPlan == 1) {
            planSuscripcion = new PlanBasico();
        } else if (tipoPlan == 2) {
            planSuscripcion = new PlanEstandar();
        } else if (tipoPlan == 3) {
            planSuscripcion = new PlanPremium();
        } else {
            System.out.println("Opción no válida. Se asignará Plan Básico por defecto.");
            planSuscripcion = new PlanBasico();
        }

        CuentaCliente nuevaCuenta = new CuentaCliente(correo, meses, planSuscripcion);
        plataforma.registrarUsuario(nuevaCuenta);
    }
}