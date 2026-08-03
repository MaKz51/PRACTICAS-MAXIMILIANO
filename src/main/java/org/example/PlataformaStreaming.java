package org.example;

import java.util.ArrayList;

public class PlataformaStreaming {
    private static ArrayList<CuentaUsuario> usuarios;

    public PlataformaStreaming() {
        this.usuarios = new ArrayList<>();
    }

    public static void registrarUsuario(CuentaUsuario usuario) {
        usuarios.add(usuario);
        System.out.println(" Usuario registrado exitosamente.");
    }

    public void imprimirReporte() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados actualmente.");
            return;
        }

        double totalRecaudado = 0;
        System.out.println("---  Reporte de Cuentas ---");

        for (CuentaUsuario usuario : usuarios) {
            double aPagar = usuario.obtenerTotalAPagar();
            totalRecaudado += aPagar;

            // getClass().getSimpleName() extrae el nombre de la clase (ej. "PlanPremium")
            System.out.println("Correo: " + usuario.getCorreoElectronico() +
                    " | Meses: " + usuario.getMesesActivo() +
                    " | Plan: " + usuario.getPlan().getClass().getSimpleName() +
                    " | Total a pagar: $" + aPagar);
        }

        System.out.println("-----------------------------");
        System.out.println(" Dinero total recaudado: $" + totalRecaudado);
    }
}