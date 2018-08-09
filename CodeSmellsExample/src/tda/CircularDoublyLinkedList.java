/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import java.util.ArrayList;


/**
 *
 * @author rxsh96
 * @param <E>
 */
public class CircularDoublyLinkedList<E> implements List<E> {

    private Nodo<E> head;
    private Nodo<E> tail;
    private Nodo<E> lastReturned;
    private int efectivo;
    
    public CircularDoublyLinkedList(){
        this.head = this.tail = this.lastReturned = null;
        this.efectivo = 0;
    }
    
    @Override
    public boolean isEmpty() {
        return efectivo == 0;
    }

    @Override
    public int size() {
        return efectivo;
    }

    @Override
    public boolean addFirst(E element) {
        Nodo<E> nodo = new Nodo<>(element);
        if(element == null){
            return false;
        }
        else if(isEmpty()){
            nodo.setNext(nodo);
            nodo.setPrevious(nodo);
            head = tail = nodo;
        }
        else{
           nodo.setPrevious(tail);
           tail.setNext(nodo);
           head.setPrevious(nodo);
           nodo.setNext(head);
           head = nodo;
        }
        lastReturned = head;
        efectivo++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        Nodo<E> nodo = new Nodo<>(element);
        if(element == null){
            return false;
        }
        else if(isEmpty()){
            nodo.setNext(nodo);
            nodo.setPrevious(nodo);
            head = tail = nodo;
        }
        else{
            nodo.setPrevious(tail);
            tail.setNext(nodo);
            head.setPrevious(nodo);
            nodo.setNext(head);
            tail = nodo;
            
        }
        lastReturned = head;
        efectivo++;
        return true;
    }

    @Override
    public E getFirst() {
        if(isEmpty()){
            return null;
        }
        return head.getData();
    }

    @Override
    public E getLast() {
        if(isEmpty()){
            return null;
        }
        return tail.getData();}

    @Override
    public E get(int index) {
        if(isEmpty()){
            return null;
        }
        else if(index >= 0 && index <= efectivo){
            Nodo<E> p = head;
            int contador = 0;
            do{
                if(contador == index){
                    return p.getData();
                }
                p = p.getNext();
                contador++;
            }
            while(p != head);
        }
        return null;
    }
    
    public E getNext(){
        lastReturned = lastReturned.getNext();
        return lastReturned.getData();
    }
    
    public E getPrev(){
        lastReturned = lastReturned.getPrevious();
        return lastReturned.getData();
    }
    
    @Override
    public boolean contains(E element) {
        if(element == null){
            return false;
        }
        else if(isEmpty()){
            return false;
        }
        else{
            Nodo<E> p, q;
            p = head;
            q = tail;
            do{
                if(q.getData().equals(element) || p.getData().equals(element)){
                    return true;
                }
                p = p.getNext();
                q = q.getPrevious();
            }
            while(!(q.getNext() == p) && (p != q));
        }
        return false;
    }

    @Override
    public boolean removeFirst() {
       if(isEmpty()){
            return false;
        }
        if(head == tail){
            tail = head = null;
        }
        else{
            head = head.getNext();
            head.setPrevious(tail);
        }
        efectivo--;
        return true;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()){
            return false;
        }
        if(head == tail){
            tail = head = null;
        }
        else{
            tail = tail.getPrevious();
        }
        efectivo--;
        return true;
    }

    @Override
    public List<E> slicing(int inicio, int fin) {
        List<E> newList = null;
        if(isEmpty()){
            return newList;
        }
        else if(inicio >= 0 && fin <= efectivo){
            newList = new CircularDoublyLinkedList<>();
            Nodo<E> p = head;
            int index = 0;
            do{
                if(index >= inicio && index <= fin){
                    newList.addLast(p.getData());
                }   
                index++;
                p = p.getNext();
            }
            while(p != tail.getNext());
        }
        return newList;
    }

    @Override
    public boolean remove(int index) { 
        if(isEmpty()){
            return false;
        }
        else if(index == 0){
            return removeFirst();
        }
        else if(index == this.indexOf(tail.getData())){
            return removeLast();
        }
        else if(index > 0 && index < efectivo){
            Nodo<E> p = head;
            int contador = 0;
            do{
                if(contador == index){
                    Nodo<E> temp = p.getPrevious();
                    temp.setNext(p.getNext());
                    efectivo--;
                    return true;
                }
                contador++;
                p = p.getNext();
            }
            while(p != tail.getNext());
        }
        return false;}

    @Override
    public E set(int index, E element) {
        if(element == null){
            return null;
        }
        else if(index >= 0 && index < efectivo){
            Nodo<E> p = head;
            int contador = 0;
            do{
                if(contador == index){
                    E temp = p.getData();
                    p.setData(element);
                    return temp;
                }
                contador++;
                p = p.getNext();
            }
            while(p != tail.getNext());
        }
        return null;
    }

    @Override
    public boolean add(int index, E element) {
        if(element == null){
            return false;
        }
        else if(index == 0){
            return addFirst(element);
        }
        else if(index == efectivo){
            return addLast(element);
        }
        else if(index >= 0 && index < efectivo){
            Nodo<E> nodo = new Nodo<>(element);
            Nodo<E> p = head;
            int contador = 0;
            do{
                if(contador == index){
                    p.getPrevious().setNext(nodo);
                    nodo.setNext(p);
                    efectivo++;
                    return true;
                }
                contador++;
                p = p.getNext();
            }
            while(p != head);
        }
        return false;
    }

    @Override
    public int indexOf(E element) { 
        if(isEmpty()){
            return -1;
        }
        else if(element == null){
            return -1;
        }
        else{
            int contador = 0;
            Nodo<E> p = head;
            do{
                if(p.getData().equals(element)){
                    return contador;
                }
                contador++;
                p = p.getNext();
            }
            while(p != tail.getNext());
        }
        return -1;
    }
    
    public static CircularDoublyLinkedList toCircularList(ArrayList list){
        CircularDoublyLinkedList circularList = new CircularDoublyLinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            circularList.addFirst(list.get(i));
        }
        return circularList;
    }
    
    
    @Override
    public String toString(){
        String data = "[";
        if(isEmpty()){
            return "[]";
        }
        else if(efectivo == 1){
            data += tail.getData();
        }
        else{
            Nodo<E> p = head;
            do{
                if(p == tail){
                    data += p.getData();
                }
                else{
                    data += p.getData()+",";
                }
                p = p.getNext();
            }
            while(p != tail.getNext());
        }
        data += "]";
        return data;
    }    
}
