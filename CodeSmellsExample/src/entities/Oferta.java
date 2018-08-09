/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author rxsh96
 */

/* EJEMPLO DE DATA CLASS */
public class Oferta {
    
    private String placa;
    private String correo;
    private String precioOfertado;
    
    public Oferta(){}
    
    public Oferta(String placa, String correo, String precioOfertado){
        this.placa = placa;
        this.correo = correo;
        this.precioOfertado = precioOfertado;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPrecioOfertado() {
        return precioOfertado;
    }

    public void setPrecioOfertado(String precioOfertado) {
        this.precioOfertado = precioOfertado;
    }
    
    @Override
    public String toString(){
        return placa+"|"+correo+"|"+precioOfertado;
    }
    
}
