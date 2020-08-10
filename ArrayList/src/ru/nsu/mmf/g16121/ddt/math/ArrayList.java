package ru.nsu.mmf.g16121.ddt.math;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] items;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    /**
     *  специфичный конструктор, принимающий вместимость @param capacity
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Size cannot be negative");
        } else {
            items = (T[]) new Object[capacity];
        }
    }

    public int getCapacity() {
        return items.length;
    }

    /**
     * @return текущую длину списка (не вместимость!)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true - если длина списка == 0, false - иначе
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return индекс первого вхождения указанного элемента в этом списке или -1,
     * если этот список не содержит элемент.
     */
    @Override
    public int indexOf(Object elem) {
        for (int i = 0; i < size; i++)
            if (elem.equals(items[i]))
                return i;
        return -1;
    }

    /**
     * @return индекс последнего вхождения указанного элемента в этом списке или -1,
     * если этот список не содержит элемент.
     */
    @Override
    public int lastIndexOf(Object elem) {
        // see notes on indexOf
        for (int i = size -1; i>=0; i--) {
            if (elem.equals(items[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    //Без реализации
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    //Без реализации
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    //Без реализации
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    /**
     * @return true - если элемент содержится в списке, false - иначе
     */
    @Override
    public boolean contains(Object elem) {
        return indexOf(elem) != -1;
    }

    /**
     * @return Список в формате массива
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object[] toArray() {
        T[] array = (T[]) new Object[size];
        System.arraycopy(items, 0, array, 0, size);
        return array;
    }

    @Override
    public <S> S[] toArray(S[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (S[]) Arrays.copyOf(items, size, array.getClass());
        }

        System.arraycopy(items, 0, array, 0, size);
        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    /**
     * @return элемент списка по индексу
     * @throws  IllegalArgumentException, если индекс выходит за рамки длины списка.
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is out of length");
        }
        return items[index];
    }

    /**
     * Заменяет элемент в указанной позиции в этом списке на указанный элемент.
     * @param index - номер индекса, в который хотим записать элемент
     * @param item - элемент который добавляется
     * @throws  IllegalArgumentException, если индекс выходит за рамки длины списка.
     */
    @Override
    public T set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is out of length");
        }
        T oldElem = get(index);
        items[index] = item;
        return oldElem;
    }

    /**
     * Метод, увеличивающий вместимость в 2 раза от текущей длины массива
     */
    public void increaseCapacity() {
        if (size >= items.length) {
            items = Arrays.copyOf(items, items.length * 2);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            items = Arrays.copyOf(items, minCapacity);
        }
    }

    /**
     * Обрезает емкость экземпляра ArrayList до текущего размера списка.
     * Можно использовать эту операцию для минимизации хранения экземпляра ArrayList.
     */
    public void trimToSize(){
        items = Arrays.copyOf(items, items.length);
    }

    /**
     * @param obj - элемент, добавляемый в конец списка.
     * @return false - если этот элемент уже добавлялся, true - если добавили элемент и размер изменился.
     */
    @Override
    public boolean add(T obj) {
        increaseCapacity();
        items[size] = obj;
        size++;

        return true;
    }


    /**
     * @param index - индекс по которому происходит добавление элемента списка.
     * @param elem - добавляемый элемент
     * @throws  IllegalArgumentException, если индекс выходит за рамки длины списка.
     */
    @Override
    public void add(int index, T elem) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is out of length");
        }
        if (size == items.length) {
            increaseCapacity();
        }
        if (index != size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }
        items[index] = elem;
        size++;
    }

    /**
     * Удаляет элемент в указанной позиции в этом списке.
     * @param index - индекс по которому происходит удаление элемента списка.
     * @throws  IllegalArgumentException, если индекс выходит за рамки длины списка.
     * @return элемент, который удалили
     */

    @Override
    public T remove(int index) {
        T element = get(index);
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        size--;
        return element;
    }

    /**
     * Удаляет первое вхождение указанного элемента из этого списка, если он присутствует.
     * @param obj - Элемент, который удаляется из списка
     * @return true - если удалили элемент, false - иначе
     */
    @Override
    public boolean remove(Object obj){
        int index = this.indexOf(obj);
        if (index >= 0) {
            this.remove(index);
            return true;
        }
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
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    /**
     * Удаляет из этого списка все его элементы, содержащиеся в указанной коллекции.
     * @param collection - элементы удаления
     * @return true - если удалили элемент, false - иначе
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean flag = true;
        if (collection != null) {
            for (Object obj : collection) {
                flag &= remove(obj);
            }
            return flag;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    /**
     * Очистка списка
     */
    @Override
    public void clear() {
        if (size > 0) {
            Arrays.fill(items, 0, size, null);
            size = 0;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object elem) {
        // проверили что передали сам объект
        if (elem == this) {
            return true;
        }
        // отсеяли null и объекты других классов
        if (elem == null || elem.getClass() != this.getClass()){
            return false;
        }
        // привели объект к ArrayList
        ArrayList<T> arrayList = (ArrayList<T>) elem;
        // проверили равенство ссылок и полей
        return items == arrayList.items && size == arrayList.size;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + size;
        hash = prime * hash + Arrays.hashCode(items);
        return hash;
    }

    @Override
    public String toString(){
        return Arrays.toString(items);
    }

    @Override
    public Iterator<T> iterator() {
        T[] copy = Arrays.copyOf(items, size);
        return Arrays.asList(copy).iterator();
    }
}
