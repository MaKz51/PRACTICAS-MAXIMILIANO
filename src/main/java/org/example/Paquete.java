package org.example;

public abstract class Paquete {
    protected String destinatario;
    protected double pesoKg;
    protected EstrategiaEnvio estrategia;

    public Paquete(String destinatario, double pesoKg, EstrategiaEnvio estrategia) {
        if (pesoKg <= 0){
            throw new IllegalArgumentException("El peso debe ser mayor o igual a 0");
        }
        this.destinatario = destinatario;
        this.pesoKg = pesoKg;
        this.estrategia = estrategia;
    }
    public String getDestinatario() { return destinatario; }
    public double getPesoKg() { return pesoKg; }
    public EstrategiaEnvio getEstrategia() { return estrategia; }

    public abstract double obtenerCostoTotal();
}
