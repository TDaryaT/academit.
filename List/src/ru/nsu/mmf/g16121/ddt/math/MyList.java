package ru.nsu.mmf.g16121.ddt.math;

public class MyList<T> {
    private ListItem<T> head;
    private int count;

    public MyList() {
    }

    /**
     * @return получение размера списка
     */
    public int getSize() {
        return count;
    }

    /**
     * @return получение значения первого элемента
     */
    public T getFirst() {
        return head.getData();
    }

    /**
     * @param data вставка этого элемента в начало
     */
    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    /**
     * Удаление первого элемента списка
     *
     * @return - удаленный элемент
     */
    public T remove() {
        T oldData = head.getData();
        head = head.getNext();
        count--;
        return oldData;
    }

    /**
     * @param index - индекс элемента
     * @return получение значения по указанному индексу.
     */
    public T get(int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Data with this index does not exist");
        }
        if (index == 0) {
            return getFirst();
        }

        int counter = 0;
        T data = head.getData();
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (counter == index) {
                data = p.getData();
                break;
            } else {
                counter++;
            }
        }
        return data;
    }

    /**
     * Изменение значения по указанному индексу
     *
     * @param index - индекс элемента вставки
     * @param data  - значение вставки
     * @return выдает старое значение
     */
    public T set(int index, T data) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Data with this index does not exist");
        }

        int counter = 0;
        T oldData = head.getData();
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (counter == index) {
                oldData = p.getData();
                p.setData(data);
                break;
            } else {
                counter++;
            }
        }
        return oldData;
    }

    /**
     * Удаление элемента по индексу
     *
     * @param index - индекс удаления
     * @return выдает значение удаленного элемента
     */
    public T remove(int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Data with this index does not exist");
        }

        if (index == 0) {
            return remove();
        }

        int counter = 0;
        T oldData = null;
        for (ListItem<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            if (counter == index) {
                oldData = p.getData();
                prev.setNext(p.getNext());
                count--;
                break;
            } else {
                counter++;
            }
        }
        return oldData;
    }

    /**
     * Вставка элемента по индексу
     *
     * @param index - индекс вставки
     * @param data  - элемент вставки
     */
    public void add(int index, T data) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Data with this index does not exist");
        }
        if (index == 0){
            addFirst(data);
            return;
        }

        int counter = 0;
        for (ListItem<T> p = head.getNext(), prev = head; prev != null; prev = p, p = p.getNext()) {
            if (counter == index) {
                p = new ListItem<>(data, p);
                prev.setNext(p);
                count++;
                break;
            } else {
                counter++;
            }
        }
    }

    /**
     * Удаление узла по значению
     *
     * @param data - элемент удаления
     * @return выдает true, если элемент был удален
     */
    public boolean remove(T data) {
        boolean del = false;
        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (p.getData().equals(data)) {
                del = true;
                p = p.getNext();
                if (prev != null) {
                    prev.setNext(p);
                }
                count--;
                break;
            }
        }
        return del;
    }

    /**
     * Разворот списка за линейное время
     */
    public void turn() {
        ListItem<T> p = head;
        ListItem<T> prev = null;
        for (ListItem<T> next = p.getNext(); next != null; prev = p, p = next, next = next.getNext()) {
            p.setNext(prev);
        }
        p.setNext(prev);
        head = p;

    }

    /**
     * @return скопированный список
     */
    public MyList<T> getCopy() {
        MyList<T> copy = new MyList<>();
        if (head == null) {
            return copy;
        }

        ListItem<T> copyItem = new ListItem<>(head.getData());
        ListItem<T> next = head.getNext();
        copy.head = copyItem;

        int i = 1;
        while (i < getSize()) {
            copyItem.setNext(new ListItem<>(next.getData()));
            next = next.getNext();
            copyItem = copyItem.getNext();
            i++;
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        ListItem<T> p = head;
        for (; p.getNext() != null; p = p.getNext()) {
            stringBuilder.append(p.getData().toString()).append(", ");
        }

        stringBuilder.append(p.getData().toString()).append("}");
        return stringBuilder.toString();
    }
}
