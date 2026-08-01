package org.example;

public class TarifaCamion implements Tarifa {
    public TarifaCamion() {
    }

    public double calcular(int horas) {
        return (double)horas * (double)4.0F + (double)5.0F;
    }
}