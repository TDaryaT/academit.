package ru.nsu.mmf.g16121.ddt.math;

public class List<T> {
    private ListItem<T> head;
    private int count;

    public List() {
    }

    public int getCount() {
        return count;
    }

    public T getFirstElem() {
        return head.getData();
    }

    public void insertFirstElem(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public T delFirstElem() {
        T oldData = head.getData();
        head = head.getNext();
        count--;
        return oldData;
    }

    public T getDataFrom(int i) {
        if (i >= count || i < 0) {
            throw new IllegalArgumentException("Data with this index does not exist");
        }
        int counter = 0;
        T data = null;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (counter == i) {
                data = p.getData();
                break;
            } else {
                counter++;
            }
        }
        return data;
    }

    public T setDataFrom(int i, T data) {
        if (i >= count || i < 0) {
            throw new IllegalArgumentException("Data with this index does not exist");
        }

        int counter = 0;
        T oldData = null;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (counter == i) {
                oldData = p.getData();
                p.setData(data);
                break;
            } else {
                counter++;
            }
        }
        return oldData;
    }

    public T delDataFrom(int i) {
        if (i >= count || i < 0) {
            throw new IllegalArgumentException("Data with this index does not exist");
        }

        int counter = 0;
        T oldData = null;
        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (counter == i) {
                oldData = p.getData();
                if (prev != null) {
                    prev.setNext(p.getNext());
                } else {
                    head = head.getNext();
                }
                count--;
                break;
            } else {
                counter++;
            }
        }
        return oldData;
    }

    public void insertDataFrom(int i, T data) {
        if (i >= count || i < 0) {
            throw new IllegalArgumentException("Data with this index does not exist");
        }

        int counter = 0;
        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (counter == i) {
                p = new ListItem<>(data, p);
                if (prev != null) {
                    prev.setNext(p);
                }
                count++;
                break;
            } else {
                counter++;
            }
        }
    }

    public boolean delData(T data) {
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

    public void turn() {
        ListItem<T> p = head;
        ListItem<T> prev = null;
        for (ListItem<T> next = p.getNext(); next != null; prev = p, p = next, next = next.getNext()) {
            p.setNext(prev);
        }
        p.setNext(prev);
        head = p;

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        ListItem<T> p = head;
        for (; p.getNext() != null; p = p.getNext()) {
            stringBuilder.append(p.getData().toString()).append(",\n");
        }
        stringBuilder.append(p.getData().toString()).append("}");
        return stringBuilder.toString();
    }
}
