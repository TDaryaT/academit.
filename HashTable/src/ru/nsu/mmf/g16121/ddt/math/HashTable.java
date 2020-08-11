package ru.nsu.mmf.g16121.ddt.math;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] hashTable;

    @SuppressWarnings("unchecked")
    public HashTable() {
        hashTable = (ArrayList<T>[]) new ArrayList[100];
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        hashTable = (ArrayList<T>[]) new ArrayList[capacity];
    }

    @Override
    public int size() {
        return hashTable.length;
    }

    @Override
    public boolean isEmpty() {
        return hashTable.length == 0;
    }

    /**
     * Проверка на то, есть ли в таблице данный объект
     * @param o - искомый объект
     * @return true, если есть, false - иначе
     */
    @Override
    public boolean contains(Object o) {
        int hash = o.hashCode();
        if (hashTable[hash] == null) {
            return false;
        } else {
            for (T elem : hashTable[hash]) {
                if (elem.equals(o)) {
                    return true;
                }
            }
        }
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

    /**
     * Добавление элемента в хэш-таблицу
     * @param t - добавляемый элемент
     * @return true, если таблица изменилась
     */
    @Override
    public boolean add(T t) {
        if (contains(t)) {
            return false;
        }

        int hash = t.hashCode();
        if (hashTable[hash] == null) {
            hashTable[hash] = new ArrayList<>();
        }
        hashTable[hash].add(t);
        return true;
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
