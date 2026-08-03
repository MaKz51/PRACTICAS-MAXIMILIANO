package org.example;

public class PlanEstandar implements PlanSuscripcion {
    @Override
    public double calcularCosto(int meses) {
        return 9.0 * meses;
    }
}