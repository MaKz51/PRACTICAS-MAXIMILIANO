package org.example;

public class CuentaCliente extends CuentaUsuario{
    public CuentaCliente(String correoElectronico, int mesesActivo, PlanSuscripcion plan){
        super(correoElectronico, mesesActivo, plan);
    }
}