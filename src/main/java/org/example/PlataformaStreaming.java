package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlataformaStreaming {

    public void registrarUsuario(CuentaUsuario usuario) throws SQLException {
        String sql = "INSERT INTO suscripciones (correo, meses, tipo_plan, total_pago) VALUES (?, ?, ?, ?)";
        PreparedStatement comando = Main.conexion.prepareStatement(sql);

        comando.setString(1, usuario.getCorreoElectronico());
        comando.setInt(2, usuario.getMesesActivo());
        comando.setString(3, usuario.getPlan().getClass().getSimpleName());
        comando.setDouble(4, usuario.obtenerTotalAPagar());

        comando.executeUpdate();
        System.out.println("Usuario registrado.");
    }

    public void imprimirReporte() throws SQLException {
        System.out.println("---Reporte de Cuentas---");
        String sql = "SELECT * FROM suscripciones";
        PreparedStatement comando = Main.conexion.prepareStatement(sql);
        ResultSet resultados = comando.executeQuery();

        double totalRecaudado = 0;
        boolean hayDatos = false;

        while (resultados.next()) {
            hayDatos = true;
            int id = resultados.getInt("id_usuario");
            String correo = resultados.getString("correo");
            int meses = resultados.getInt("meses");
            String tipoPlan = resultados.getString("tipo_plan");
            double total = resultados.getDouble("total_pago");

            System.out.println("ID: " + id + " | Correo: " + correo + " | Meses: " + meses + " | Plan: " + tipoPlan + " | Pago: $" + total);
            totalRecaudado += total;
        }

        if (!hayDatos) {
            System.out.println("No hay usuarios registrados actualmente.");
        } else {
            System.out.println("-----------------------------");
            System.out.println("Dinero total recaudado: $" + totalRecaudado);
        }
    }

    public void eliminarUsuario(int idUsuario) throws SQLException {
        String sql = "DELETE FROM suscripciones WHERE id_usuario = ?";
        PreparedStatement comando = Main.conexion.prepareStatement(sql);
        comando.setInt(1, idUsuario);

        int filas = comando.executeUpdate();
        if (filas > 0) {
            System.out.println("Usuario con ID " + idUsuario + " eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún usuario con ese ID.");
        }
    }

    public void editarMesesPlan(int idUsuario, int nuevosMeses) throws SQLException {
        String sqlBusqueda = "SELECT tipo_plan FROM suscripciones WHERE id_usuario = ?";
        PreparedStatement comandoBusqueda = Main.conexion.prepareStatement(sqlBusqueda);
        comandoBusqueda.setInt(1, idUsuario);
        ResultSet resultado = comandoBusqueda.executeQuery();

        if (resultado.next()) {
            String tipoPlan = resultado.getString("tipo_plan");
            PlanSuscripcion planTemporal = null;

            if (tipoPlan.equals("PlanBasico")) planTemporal = new PlanBasico();
            else if (tipoPlan.equals("PlanEstandar")) planTemporal = new PlanEstandar();
            else if (tipoPlan.equals("PlanPremium")) planTemporal = new PlanPremium();

            if (planTemporal != null) {
                double nuevoTotal = planTemporal.calcularCosto(nuevosMeses);

                String sqlUpdate = "UPDATE suscripciones SET meses = ?, total_pago = ? WHERE id_usuario = ?";
                PreparedStatement comandoUpdate = Main.conexion.prepareStatement(sqlUpdate);
                comandoUpdate.setInt(1, nuevosMeses);
                comandoUpdate.setDouble(2, nuevoTotal);
                comandoUpdate.setInt(3, idUsuario);

                comandoUpdate.executeUpdate();
                System.out.println("Suscripcion actualizada con éxito. Nuevo total a pagar: $" + nuevoTotal);
            }
        } else {
            System.out.println("No se encontró ningún usuario con ese ID.");
        }
    }
}