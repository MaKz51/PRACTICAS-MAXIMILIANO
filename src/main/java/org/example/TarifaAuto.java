package org.example;

public class TarifaAuto implements Tarifa {
    public TarifaAuto() {
    }

    public double calcular(int horas) {
        return (double)horas * (double)2.0F;
    }
}