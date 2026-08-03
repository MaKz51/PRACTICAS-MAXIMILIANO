package org.example;

public class PlanPremium implements PlanSuscripcion{
    @Override
    public double calcularCosto(int meses) {

        return (14.0 * meses) + 3.0;
    }
}
