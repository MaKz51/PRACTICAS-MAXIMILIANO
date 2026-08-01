package org.example;

public abstract class Vehiculo {
    private String placa;
    private int horasEstacionado;
    private Tarifa tarifa;

    public Vehiculo(String placa, int horasEstacionado, Tarifa tarifa) {
        this.placa = placa;
        this.horasEstacionado = horasEstacionado;
        this.tarifa = tarifa;
    }

    public String getPlaca() {
        return this.placa;
    }

    public int getHorasEstacionado() {
        return this.horasEstacionado;
    }

    public double calcularCostoEstacionamiento() {
        return this.tarifa.calcular(this.horasEstacionado);
    }
}