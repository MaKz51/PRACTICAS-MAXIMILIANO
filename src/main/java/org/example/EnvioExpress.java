package org.example;

public class EnvioExpress implements EstrategiaEnvio {
    @Override
    public double calcularCosto(double pesoKg) {
        return (pesoKg * 4.50) +3;
    }

    @Override
    public String obtenerTipoEnvio() {
        return "Express";
    }
}
