package ru.nsu.mmf.g16121.ddt.math;

import java.util.Arrays;

public class ArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] items;
    private int length;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Size cannot be negative");
        }
        items = (E[]) new Object[capacity];
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int indexOf(Object elem) {
        for (int i = 0; i < length; i++)
            if (elem.equals(items[i]))
                return i;
        return -1;
    }

    public boolean contains(Object e) {
        return indexOf(e) != -1;
    }

    public Object[] toArray() {
        E[] array = (E[]) new Object[length];
        System.arraycopy(items, 0, array, 0, length);
        return array;
    }

    public E get(int index) {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException("index is out of length");
        }
        return items[index];
    }

    public void set(int index, E item) {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException("index is out of length");
        }
        items[index] = item;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void add(E obj) {
        if (length >= items.length) {
            increaseCapacity();
        }
        items[length] = obj;
        ++length;
    }


    public void add(int index, E elem) {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException("index is out of length");
        }
        if (length == items.length) {
            increaseCapacity();
        }
        if (index != length) {
            System.arraycopy(items, index, items, index + 1, length - index);
        }
        items[index] = elem;
        length++;
    }

    public void remove(int index) {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException("index is out of length");
        }
        if (index < length - 1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
        }
        --length;
    }

    protected void removeRange(int fromIndex, int toIndex) {
        int change = toIndex - fromIndex;
        if (change > 0) {
            System.arraycopy(items, toIndex, items, fromIndex, length - toIndex);
            length -= change;
        } else if (change < 0) {
            throw new IllegalArgumentException("Illegal range");
        }
    }

    public void clear() {
        if (length > 0) {
            Arrays.fill(items, 0, length, null);
            length = 0;
        }
    }

    public void trimToSize() {
        if (length != items.length) {
            E[] newData = (E[]) new Object[length];
            System.arraycopy(items, 0, newData, 0, length);
            items = newData;
        }
    }


}
