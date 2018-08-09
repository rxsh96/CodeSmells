/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

/**
 *
 * @author rxsh96
 * @param <E>
 */
public interface List<E> {
    
    boolean isEmpty();
    int size();
    boolean addFirst(E element);
    boolean addLast(E element);
    E getFirst();
    E getLast();
    E get(int index);
    boolean contains(E element);
    boolean removeFirst();
    boolean removeLast();
    List<E> slicing(int inicio, int fin);
    boolean remove(int index);
    E set(int index, E element);
    boolean add(int index, E element);
    int indexOf(E element);
    
}
