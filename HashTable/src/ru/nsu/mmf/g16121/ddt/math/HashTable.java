package ru.nsu.mmf.g16121.ddt.math;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private final ArrayList<T>[] hashTable;
    private int size;
    private int modCount;

    @SuppressWarnings("unchecked")
    public HashTable() {
        hashTable = (ArrayList<T>[]) new ArrayList[10];
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        hashTable = (ArrayList<T>[]) new ArrayList[capacity];
    }

    /**
     * @return Количество элементов, находящихся в таблице
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Приведение хэша к размеру хэш-таблицы
     * @param o - объект, для которого хотим определить позицию в таблице
     * @return индекс для объекта в таблице
     */
    private int getIndex(Object o){
        return Math.abs(o.hashCode()%hashTable.length);
    }

    /**
     * @return Количество элементов в таблице
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Проверка на то, есть ли в таблице данный объект
     * @param o - искомый объект
     * @return true, если есть, false - иначе
     */
    @Override
    public boolean contains(Object o) {
        int hash = getIndex(o);
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
        return new MyHashTableIterator();
    }
    /**
     * Для реализации обхода коллекции
     */
    private class MyHashTableIterator implements Iterator<T>{
        private int currentIndex = -1; //счетчик по списку
        private int currentHash = 0; //счетчик по массиву
        private final int modCount = HashTable.this.modCount;
        private int count = 0; //количество элементов хэш-таблицы
        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Collection is over");
            }
            if (modCount != HashTable.this.modCount) {
                throw new ConcurrentModificationException("Collection size changed during crawl");
            }
            while (hashTable[currentHash] == null || hashTable[currentHash].size() - 1 == currentIndex){
                ++currentHash;
                currentIndex = -1;
            }
                //если мы идем по списку
            if (currentIndex < hashTable[currentHash].size() - 1) {
                ++currentIndex;
            }
            ++count;
            return hashTable[currentHash].get(currentIndex);
        }
    }

    /**
     * Перевод коллекции в массив объектов
     * @return массив объектов
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for(Object elem : this){
            array[i] = elem;
            i++;
        }
        return array;
    }

    /**
     * Перевод коллекции в конкретный массив
     * @param a - массив, в который копируем исходную коллекцию
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        T1[] asArray = (T1[]) toArray();
        if (a.length < size){
            return asArray;
        }
        System.arraycopy(asArray,0,a,0,size);
        return a;
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

        int hash = getIndex(t);
        if (hashTable[hash] == null) {
            hashTable[hash] = new ArrayList<>();
        }
        hashTable[hash].add(t);
        size++;
        modCount++;
        return true;
    }

    /**
     * Удаление элемента из таблицы
     * @param o - параметр удаления
     * @return true - если удалилосьБ false - иначе
     */
    @Override
    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        }

        int hash = getIndex(o);
        modCount++;
        size--;
        return hashTable[hash].remove(o);
    }

    /**
     * Содержится ли вся коллекция в данной
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection can't be empty");
        }
        if (c.isEmpty()) {
            return false;
        }
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Добавление всей коллекции в таблицу
     * @return true, если все элементы теперь в таблице,
     * и хотя бы один из них добавился
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Collection can't be empty");
        }
        if (c.isEmpty()) {
            return false;
        }
        boolean flag = false;
        for (T elem : c){
            if (add(elem)){
                flag = true;
            }
        }

        return flag;
    }
    /**
     * Удаление всей коллекции в таблицу
     * @return true, если все элементы теперь не в таблице,
     * и хотя бы один из них удалился
     */

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection can't be empty");
        }
        if (c.isEmpty()) {
            return false;
        }
        boolean flag = false;
        for (Object elem : c){
            if (remove(elem)){
                flag = true;
            }
        }

        return flag;
    }

    /**
     * удаляет из этой коллекции все ее элементы,
     * которые не содержатся в указанной коллекции.
     *
     * @param c - коллекция, содержащая элементы, которые будут сохранены в этой коллекции
     * @return true, если эта коллекция изменилась в результате вызова
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection can't be empty");
        }
        boolean flag = false;
        for (ArrayList<T> list : hashTable) {
            if (list != null) {
                flag = list.retainAll(c);
                size -= list.size();
                modCount++;
            }
        }

        return flag;
    }

    @Override
    public void clear() {
        if (size > 0) {
            ++modCount;
        }
        Arrays.fill(hashTable, null);
        size = 0;
    }

    @Override
    public String toString(){
        if (size == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        for (ArrayList<T> arrayList : hashTable){
            if (arrayList != null) {
                stringBuilder.append(index).append(arrayList.toString()).append('\n');
            } else {
                stringBuilder.append(index).append("[]\n");
            }
            index++;
        }

        return stringBuilder.toString();
    }
}
