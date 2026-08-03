package org.example;

public abstract class CuentaUsuario {
    private String correoElectronico;
    private int mesesActivo;
    private PlanSuscripcion plan;

    public CuentaUsuario (String correoElectronico,  int mesesActivo, PlanSuscripcion plan) {
        this.correoElectronico = correoElectronico;
        this.mesesActivo = mesesActivo;
        this.plan = plan;
    }

    public double obtenerTotalAPagar(){
        return plan.calcularCosto(mesesActivo);
    }
    //  getters
    public String getCorreoElectronico(){
        return correoElectronico;
    }

    public int getMesesActivo(){
        return mesesActivo;
    }

    public PlanSuscripcion getPlan(){
        return plan;
    }

}