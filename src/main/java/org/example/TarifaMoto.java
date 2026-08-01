package org.example;

public class TarifaMoto implements Tarifa {
    public TarifaMoto() {
    }

    public double calcular(int horas) {
        return (double)horas * (double)1.0F;
    }
}