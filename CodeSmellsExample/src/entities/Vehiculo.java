/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rxsh96
 */

/* EJEMPLO DE DATA CLASS */
public class Vehiculo {
    
    /*Ejemplo de Primitive Obsession*/
    
    private String placa;
    private String marca;
    private String modelo;
    private String tipoMotor;
    private String year;
    private String tipo;
    private String recorrido;
    private String color;
    private String tipoCombustible;
    private String vidrios;
    private String transmision;
    private String precio;
    private String imageURL;
    private ArrayList<Oferta> ofertas;
    
    public Vehiculo(){
        this.ofertas = new ArrayList<>();
    }
    
    public Vehiculo(String placa){
        this.placa = placa;
        this.ofertas = new ArrayList<>();
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(String recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public String getVidrios() {
        return vidrios;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List getOfertas() {
        return ofertas;
    }

    public void setOfertas(Oferta ofertas) {
        this.ofertas.add(ofertas);
    }

    @Override
    public String toString(){
        return placa+"|"+marca+"|"+modelo+"|"+tipoMotor+"|"+year+"|"+tipo+"|"+recorrido
                +"|"+color+"|"+tipoCombustible+"|"+vidrios+"|"+transmision+"|"+
                precio+"|"+imageURL;
    }
    
}
