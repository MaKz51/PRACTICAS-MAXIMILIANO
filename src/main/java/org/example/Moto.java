package org.example;

public class Moto extends Vehiculo {
    public Moto(String placa, int horasEstacionado) {
        super(placa, horasEstacionado, new TarifaMoto());
    }
}
