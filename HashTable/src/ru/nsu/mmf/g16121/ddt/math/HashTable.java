package ru.nsu.mmf.g16121.ddt.math;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<T> implements Collection<T> {
    private ArrayList<ArrayList<T>> hashTable;

    public HashTable(int capacity){
        hashTable = new ArrayList<ArrayList<T>>(capacity);
    }

    @Override
    public int size() {
        return hashTable.size();
    }

    @Override
    public boolean isEmpty() {
        return hashTable.isEmpty();
    }

    @Override
    public boolean contains(Object o) {

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
