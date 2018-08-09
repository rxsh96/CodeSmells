/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import entities.Oferta;
import entities.Vehiculo;
import java.util.ArrayList;
import tda.CircularDoublyLinkedList;

/**
 *
 * @author rxsh96
 */
public class TempoData {
    
    private CircularDoublyLinkedList<Vehiculo> vehiculosDisponibles = new CircularDoublyLinkedList<>();
    private ArrayList<Oferta> ofertasDisponibles = new ArrayList<>();
  
    
    public TempoData(){
    }

    public CircularDoublyLinkedList<Vehiculo> getVehiculosDisponibles() {
        return vehiculosDisponibles;
    }

    public void setVehiculosDisponibles(CircularDoublyLinkedList<Vehiculo> vehiculosDisponibles) {
        this.vehiculosDisponibles = vehiculosDisponibles;
    }

    public ArrayList<Oferta> getOfertasDisponibles() {
        return ofertasDisponibles;
    }

    public void setOfertasDisponibles(ArrayList<Oferta> ofertasDisponibles) {
        this.ofertasDisponibles = ofertasDisponibles;
    }
    
}
