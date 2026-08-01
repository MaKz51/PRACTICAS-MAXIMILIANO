package org.example;

public class Auto extends Vehiculo {
    public Auto(String placa, int horasEstacionado) {
        super(placa, horasEstacionado, new TarifaAuto());
    }
}
