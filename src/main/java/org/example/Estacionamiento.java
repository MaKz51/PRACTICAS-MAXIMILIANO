package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Estacionamiento {
    private ArrayList<Vehiculo> vehiculos = new ArrayList();

    public Estacionamiento() {
    }

    public void registrarVehiculo(Vehiculo vehiculo) throws Exception {
        String sql = "INSERT INTO vehiculos (placa, tipo, horas) VALUES (?, ?, ?)";
        PreparedStatement comando = Main.conexion.prepareStatement(sql);
        comando.setString(1, vehiculo.getPlaca());

        comando.setString(2, vehiculo.getClass().getSimpleName());
        comando.setInt(3, vehiculo.getHorasEstacionado());

        comando.executeUpdate();
        System.out.println("Vehiculo registrado en la base de datos");
    }

    public void imprimirReporte() throws Exception {
        double total = 0;
        System.out.println("===== ESTACIONAMIENTO GALERIAS =====");

        String sql = "SELECT * FROM vehiculos";
        PreparedStatement comando = Main.conexion.prepareStatement(sql);
        ResultSet resultado = comando.executeQuery();

        while (resultado.next()) {
            String placa = resultado.getString("placa");
            String tipo = resultado.getString("tipo");
            int horas = resultado.getInt("horas");

            Vehiculo v = null;
            if (tipo.equalsIgnoreCase("Auto")) {
                v = new Auto(placa, horas);
            } else if (tipo.equalsIgnoreCase("Moto")) {
                v = new Moto(placa, horas);
            } else if (tipo.equalsIgnoreCase("Camion")) {
                v = new Camion(placa, horas);
            }

            if (v != null) {
                double costo = v.calcularCostoEstacionamiento();
                System.out.println("-----------------------------------");
                System.out.println("Tipo: ");
                System.out.println("Placa: " + v.getPlaca());
                System.out.println("Horas: " + v.getHorasEstacionado());
                System.out.println("Costo: $" + costo);
                total += costo;
            }
        }

        System.out.println("-----------------------------------");
        System.out.println("TOTAL: $" + total);
    }
}