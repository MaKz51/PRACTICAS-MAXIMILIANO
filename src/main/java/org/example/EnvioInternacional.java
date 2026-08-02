package org.example;

public class EnvioInternacional implements EstrategiaEnvio {
    private boolean cobroAduanaAplicado;

    public EnvioInternacional() {
        this.cobroAduanaAplicado = false;
    }

    @Override
    public double calcularCosto(double pesoKg){
        double costoBase = pesoKg *6.00;

        if(cobroAduanaAplicado){
            costoBase += 10.00;
            this.cobroAduanaAplicado = true;
        }
        return costoBase;
    }
    @Override
    public String obtenerTipoEnvio() {
        return "Envio Internacional";
    }

}
