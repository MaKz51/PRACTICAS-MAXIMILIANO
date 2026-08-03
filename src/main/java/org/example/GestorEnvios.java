package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorEnvios {

    public void guardarPaquete(Paquete paquete) throws SQLException {
        String sql = "INSERT INTO historial_paquetes (destinatario, peso, tipo_envio, costo_total) VALUES (?, ?, ?, ?)";
        PreparedStatement comando = Main.conexion.prepareStatement(sql);

        comando.setString(1, paquete.getDestinatario());
        comando.setDouble(2, paquete.getPesoKg());
        comando.setString(3, paquete.getEstrategia().obtenerTipoEnvio());
        comando.setDouble(4, paquete.obtenerCostoTotal());

        comando.executeUpdate();
        System.out.println("Paquete registrado.");
    }

    public void verHistorial() throws SQLException {
        System.out.println("\n===== HISTORIAL DE PAQUETES =====");
        String sql = "SELECT * FROM historial_paquetes";
        PreparedStatement comando = Main.conexion.prepareStatement(sql);
        ResultSet resultados = comando.executeQuery();

        double gananciasTotales = 0;
        boolean hayDatos = false;

        while (resultados.next()) {
            hayDatos = true;
            int guia = resultados.getInt("id_guia");
            String dest = resultados.getString("destinatario");
            double peso = resultados.getDouble("peso");
            String tipo = resultados.getString("tipo_envio");
            double costo = resultados.getDouble("costo_total");

            System.out.println("Guía #" + guia + " | Destinatario: " + dest + " | " + peso + "kg | " + tipo + " | Total: $" + costo);
            gananciasTotales += costo;
        }

        if (!hayDatos) {
            System.out.println("No hay paquetes registrados aún.");
        } else {
            System.out.println("---------------------------------");
            System.out.println("GANANCIAS TOTALES: $" + gananciasTotales);
        }
    }

    public void eliminarPaquete(int idGuia) throws SQLException {
        String sql = "DELETE FROM historial_paquetes WHERE id_guia = ?";
        PreparedStatement comando = Main.conexion.prepareStatement(sql);
        comando.setInt(1, idGuia);

        int filas = comando.executeUpdate();
        if (filas > 0) {
            System.out.println("Paquete con guía #" + idGuia + " eliminado.");
        } else {
            System.out.println("No se encontró ninguna guía con ese número.");
        }
    }
}