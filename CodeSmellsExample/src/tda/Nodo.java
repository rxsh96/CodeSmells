/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

/**
 *
 * @author rxsh9
 */

/* EJEMPLO DE SHUTGUN SURGERY */
public class Nodo<E> {
    
    private E data;
    private Nodo<E> next;
    private Nodo<E> previous;
    
    public Nodo(E data){
        this.data = data;
        this.next = this.previous = null;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Nodo<E> getNext() {
        return next;
    }

    public void setNext(Nodo<E> next) {
        this.next = next;
    }

    public Nodo<E> getPrevious() {
        return previous;
    }

    public void setPrevious(Nodo<E> previous) {
        this.previous = previous;
    }
}
