package ru.nsu.mmf.g16121.ddt.math;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] items;
    private int size;
    private int modCount; //необходим для подсчета количества изменений для класса итератора

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * специфичный конструктор, принимающий вместимость @param capacity
     */
    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Size cannot be negative");
        } else {
            items = (T[]) new Object[capacity];
        }
    }

    /**
     * @return вместимость списка
     */
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
        for (int i = size - 1; i >= 0; i--) {
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
    public T[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S> S[] toArray(S[] array) {
        if (array.length < size) {
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
     * @throws IllegalArgumentException, если индекс выходит за рамки длины списка.
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
     *
     * @param index - номер индекса, в который хотим записать элемент
     * @param item  - элемент который добавляется
     * @throws IllegalArgumentException, если индекс выходит за рамки длины списка.
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
    public void trimToSize() {
        if(size < items.length){
        items = Arrays.copyOf(items, size);
        }
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
        modCount++;

        return true;
    }


    /**
     * Вставляет указанный элемент в указанную позицию в этом списке.
     * Сдвигает элемент, который в данный момент находится в этой
     * позиции (если есть), и все последующие элементы вправо
     * (добавляет единицу к их индексам).
     *
     * @param index - индекс по которому происходит добавление элемента списка.
     * @param elem  - добавляемый элемент
     * @throws IllegalArgumentException, если индекс выходит за рамки длины списка.
     */
    @Override
    public void add(int index, T elem) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index is out of length");
        }
        if (index == size) {
            add(elem);
        } else {
            increaseCapacity();
            System.arraycopy(items, index, items, index + 1, size - index);
            items[index] = elem;
            size++;
            modCount++;
        }

    }

    /**
     * Удаляет элемент в указанной позиции в этом списке.
     *
     * @param index - индекс по которому происходит удаление элемента списка.
     * @return элемент, который удалили
     * @throws IllegalArgumentException, если индекс выходит за рамки длины списка.
     */

    @Override
    public T remove(int index) {
        modCount++;
        T element = get(index);

        int length = size - index - 1;
        if (length > 0) {
            System.arraycopy(items, index + 1, items, index, length);
        }
        size--;
        items[size] = null;
        return element;
    }

    /**
     * Удаляет первое вхождение указанного элемента из этого списка, если он присутствует.
     *
     * @param obj - Элемент, который удаляется из списка
     * @return true - если удалили элемент, false - иначе
     */
    @Override
    public boolean remove(Object obj) {
        int index = this.indexOf(obj);

        if (index >= 0) {
            this.remove(index);
            return true;
        }

        return false;
    }

    /**
     * @param collection - коллекция, которую проверяем на то, что она есть в списке.
     * @return true - если все элементы коллекции в списке, false - иначе.
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection can't be empty");
        }

        for (Object e : collection) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Добавление коллекции в конец списка
     *
     * @param collection - добавляемая коллекция
     * @return true, если эта коллекция изменилась в результате вызова
     */
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection can't be empty");
        }
        if (collection.isEmpty()) {
            return false;
        }
        ensureCapacity(size + collection.size());
        modCount++;

        for (T elem : collection) {
            items[size] = elem;
            size++;
        }

        return true;
    }

    /**
     * Вставляет все элементы указанной коллекции в этот список
     * в указанную позицию. Сдвигает элемент, который в данный
     * момент находится в этой позиции (если есть), и любые
     * последующие элементы вправо (увеличивает их индексы).
     * Новые элементы появятся в этом списке в том порядке,
     * в котором они возвращены итератором указанной коллекции.
     *
     * @param index      -  индекс, по которому нужно вставить первый элемент из указанной коллекции
     * @param collection - коллекция, содержащая элементы для добавления в этот список
     * @return true, если этот список изменился в результате вызова
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection can't be empty");
        }

        if (collection.isEmpty()) {
            return false;
        }
        if (index == size) {
            return addAll(collection);
        }

        int length = collection.size();
        ensureCapacity(size + length);
        System.arraycopy(items, index, items, index + length, size - index);

        size = size + length;
        modCount++;

        int i = index;
        for (T elem : collection) {
            items[i] = elem;
            i++;
        }

        return true;
    }

    /**
     * Удаляет из этого списка все его элементы,
     * содержащиеся в указанной коллекции.
     *
     * @param collection - элементы удаления
     * @return true - если удалили элемент, false - иначе
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection can't be empty");
        }
        boolean flag = true;
        for (Object obj : collection) {
            flag &= remove(obj);
        }
        return flag;
    }

    /**
     * удаляет из этого списка все ее элементы,
     * которые не содержатся в указанной коллекции.
     *
     * @param collection - коллекция, содержащая элементы, которые будут сохранены в этой коллекции
     * @return true, если эта коллекция изменилась в результате вызова
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection can't be empty");
        }

        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (!collection.contains(items[i])) {
                remove(i);
                i--;
                flag = true;
            }
        }

        return flag;
    }

    /**
     * Очистка списка
     */
    @Override
    public void clear() {
        modCount++;
        if (size > 0) {
            Arrays.fill(items,  null);
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
        if (elem == null || elem.getClass() != this.getClass()) {
            return false;
        }
        // привели объект к ArrayList
        MyArrayList<T> arrayList = (MyArrayList<T>) elem;
        // проверили равенство ссылок и полей
        return items == arrayList.items && size == arrayList.size;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + size;
        hash = prime * hash + modCount;
        hash = prime * hash + Arrays.hashCode(items);
        return hash;
    }

    @Override
    public String toString() {
        Iterator<T> iterator = iterator();
        if (!iterator.hasNext()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        while (true) {
            stringBuilder.append(iterator.next());

            if (!iterator.hasNext()) {
                return stringBuilder.append(']').toString();
            }

            stringBuilder.append(',').append(' ');
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int modCount = MyArrayList.this.modCount;

        @Override
        public boolean hasNext() {
            return currentIndex < size - 1;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Collection is over");
            }
            if (modCount != MyArrayList.this.modCount) {
                throw new ConcurrentModificationException("Collection size changed during crawl");
            }

            currentIndex++;
            return items[currentIndex];
        }
    }
}
