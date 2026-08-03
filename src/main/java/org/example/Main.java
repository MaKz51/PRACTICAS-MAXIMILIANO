package org.example;

import java.util.Scanner;

public class Main {
    void main() {
        Scanner sc = new Scanner(System.in);
        PlataformaStreaming plataforma = new PlataformaStreaming();
        boolean ejecutar = true;
        try {
            do {
                System.out.println("------- Java Stream -------");
                System.out.println("Que desea hacer?");
                System.out.println("1. Registrarse");
                System.out.println("2. Ver lista de usuarios");
                System.out.println("3. Salir");
                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el correo electronico: ");
                        String correo = sc.nextLine();

                        System.out.println("Ingrese los meses contratados: ");
                        int meses = sc.nextInt();

                        System.out.println("Seleccione un plan: ");
                        System.out.println("1. Plan Basico ($5.00/mes)");
                        System.out.println("1. Plan Estandar ($9.00/mes)");
                        System.out.println("1. Plan Premium ($14.00/mes + $3.00 unica vez)");
                        System.out.println("Tipo de plan: ");
                        int tipoPlan = sc.nextInt();

                        PlanSuscripcion planSuscripcion = null;

                        if (tipoPlan == 1) {
                            planSuscripcion = new PlanBasico();
                        } else if (tipoPlan == 2) {
                            planSuscripcion = new PlanEstandar();
                        } else if (tipoPlan == 3) {
                            planSuscripcion = new PlanPremium();
                        } else {
                            System.out.println("Opcion no valida. Se canceló el proceso.");
                        }
                        CuentaCliente nuevaCuenta = new CuentaCliente(correo, meses, planSuscripcion);

                        PlataformaStreaming.registrarUsuario(nuevaCuenta);
                        break;
                    case 2:
                        plataforma.imprimirReporte();
                        break;
                    case 3:
                        System.out.println("Saliendo del programa...");
                        ejecutar = false;
                        break;
                    default:
                        System.out.println("Opcion no valida, intentelo de nuevo");
                        break;
                }
            } while (ejecutar);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}