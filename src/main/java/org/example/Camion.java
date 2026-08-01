package org.example;

public class Camion extends Vehiculo {
    public Camion(String placa, int horasEstacionado) {
        super(placa, horasEstacionado, new TarifaCamion());
    }
}
