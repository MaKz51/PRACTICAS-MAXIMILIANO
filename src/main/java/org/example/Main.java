package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Estacionamiento estacionamiento = new Estacionamiento();
    static Scanner teclado = new Scanner(System.in);

    static String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;database=ESTACIONAMIENTO_POO;user=sa;password=Maxmi2017Th;encrypt=true;trustServerCertificate=true;";
    public static Connection conexion;

    public static void main(String[] args) throws Exception {
    conexion = DriverManager.getConnection(url);
    int opcion = 0;

    do {
        menu();
        opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion) {
            case 1:
                nuevoVehiculo();
                break;
            case 2:
                estacionamiento.imprimirReporte();
                break;
            case 3:
                System.out.println("Bye Bye.");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }while (opcion != 3);

    conexion.close();

    }


    public static void nuevoVehiculo() throws Exception {
        System.out.println("Escriba el tipo de vehículo.");
        System.out.println("- Auto");
        System.out.println("- Moto");
        System.out.println("- Camion");
        String opcionVehiculo = teclado.next();
        System.out.println("Escriba las placas.");
        String placasVehiculos = teclado.next();
        System.out.println("Escriba las horas estacionado.");
        int horitas = teclado.nextInt();
        if (opcionVehiculo.equalsIgnoreCase("Auto")) {
            estacionamiento.registrarVehiculo(new Auto(placasVehiculos, horitas));
        } else if (opcionVehiculo.equalsIgnoreCase("Moto")) {
            estacionamiento.registrarVehiculo(new Moto(placasVehiculos, horitas));
        } else if (opcionVehiculo.equalsIgnoreCase("Camion")) {
            estacionamiento.registrarVehiculo(new Camion(placasVehiculos, horitas));
        } else {
            System.out.println("Tipo de vehículo no válido.");
        }

    }

    public static void menu() {
        System.out.println("======================MENÚ======================");
        System.out.println("1) Registrar nuevo vehiculo");
        System.out.println("2) Imprimir Reporte");
        System.out.println("3) Salir");
    }
}