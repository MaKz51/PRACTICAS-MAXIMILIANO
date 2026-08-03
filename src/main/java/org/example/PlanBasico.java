package org.example;

public class PlanBasico implements PlanSuscripcion{
    @Override
    public double calcularCosto(int meses) {
        return 5.00 * meses;
    }
}
