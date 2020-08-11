package ru.nsu.mmf.g16121.ddt.main;

import ru.nsu.mmf.g16121.ddt.math.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> myArrayList1 = new MyArrayList<>();
        System.out.println("Пример 1:");
        System.out.println("Пустой конструктор: " + myArrayList1.toString());
        System.out.println("Пустая ли коллекция? "+ myArrayList1.isEmpty());
        System.out.println();

        myArrayList1.add("Hello");
        myArrayList1.add("world");
        System.out.println("Добавили два слова в коллекцию: " + myArrayList1.toString());
        System.out.println("Пустая ли коллекция? "+ myArrayList1.isEmpty());
        System.out.println();

        System.out.println("Размер коллекции: " + myArrayList1.size());
        System.out.println("Вместимость коллекции: " + myArrayList1.getCapacity());
        myArrayList1.trimToSize();
        System.out.println("Привели вместимость к количеству элементов: " + myArrayList1.getCapacity());
        System.out.println();

        System.out.println("Метод toArray():");
        String[] array1 = myArrayList1.toArray(new String[0]);
        for (String item : array1) {
            System.out.println(item);
        }
        System.out.println();

        System.out.println("Метод toArray(S[] a)");
        String[] array2 = new String[myArrayList1.size()];
        array2 = myArrayList1.toArray(array2);

        for (String item : array2) {
            System.out.println(item);
        }
        System.out.println();

        System.out.print("Работа итератора: ");
        for (String elem : myArrayList1){
            System.out.print(elem);
            System.out.print(" ");
        }
        System.out.println();

        System.out.println();
        System.out.println("Пример 2:");
        MyArrayList<Integer> myArrayList2 = new MyArrayList<>(3);
        System.out.println("Пустой конструктор с вместимостью " +
                myArrayList2.getCapacity() + " : " + myArrayList2.toString());

        myArrayList2.add(3);
        myArrayList2.add(4);
        myArrayList2.add(3);
        System.out.println("Добавили 3 элемента в коллекцию: " + myArrayList2.toString());
        System.out.println("Пустой ли массив? "+ myArrayList2.isEmpty());
        System.out.println();

        System.out.println("Индекс элемента " + 3 + " с начала коллекции: " + myArrayList2.indexOf(3));
        System.out.println("Индекс элемента " + 3 + " с конца коллекции: " + myArrayList2.lastIndexOf(3));
        System.out.println("Индекс элемента " + 5 + " с начала коллекции: " + myArrayList2.indexOf(5));
        System.out.println("Индекс элемента " + 5 + " с конца коллекции: " + myArrayList2.lastIndexOf(5));
        System.out.println();

        System.out.println("Принадлежит ли элемент " + 5 + " коллекции? " + myArrayList2.contains(5));
        System.out.println("Принадлежит ли элемент " + 4 + " коллекции? " + myArrayList2.contains(4));
        System.out.println();

        System.out.print("Запишем в первый элемент число " + 6 + ": ");
        myArrayList2.set(0,6);
        System.out.println(myArrayList2.toString());
        System.out.println();

        System.out.println("Увеличим вместимость c " + myArrayList2.getCapacity());
        myArrayList2.increaseCapacity();
        System.out.println("Получили вместимость: " + myArrayList2.getCapacity());
        System.out.print("Увеличим вместимость до ");
        myArrayList2.ensureCapacity(10);
        System.out.println(myArrayList2.getCapacity());
        System.out.println();

        System.out.print("Вставим элемент " + 7 + " по индексу " + 1 + ": ");
        myArrayList2.add(1,7);
        System.out.println(myArrayList2.toString());

        System.out.print("Удалим элемент по индексу " + 0 + " элемент " + myArrayList2.remove(0) + ": ");
        System.out.println(myArrayList2.toString());

        System.out.print("Удалим элемент " + 7 + ": ");
        myArrayList2.remove((Integer) 7);
        System.out.println(myArrayList2.toString());
        System.out.println();

        System.out.println("Пример 3: ");
        MyArrayList<Integer> myArrayList3 = new MyArrayList<>();
        myArrayList3.addAll(myArrayList2);
        System.out.println("Добавили коллекцию 2 в коллекцию 3: " + myArrayList3.toString());
        System.out.println("Есть ли коллекция 2 в коллекции 3? "+ myArrayList3.containsAll(myArrayList2));
        System.out.println("Есть ли коллекция 1 в коллекции 3? "+ myArrayList3.containsAll(myArrayList1));
        myArrayList3.addAll(1,myArrayList2);
        System.out.println("Добавили коллекцию 2 в коллекцию 3 начиная с индекса 1: " + myArrayList3.toString());
        System.out.println();

        myArrayList3.add(7);
        myArrayList3.add(8);
        myArrayList3.add(9);
        System.out.println("Добавили новые элементы в коллекцию: " + myArrayList3.toString());
        myArrayList3.removeAll(myArrayList2);
        System.out.println("Удалили коллекцию 2 из коллекции 3: " + myArrayList3.toString());
        myArrayList3.retainAll(myArrayList2);
        System.out.println("Оставили в коллекции 3 только элементы коллекции 2: " + myArrayList3.toString());
        System.out.println();

        System.out.println("Хэш коллекции 3: " + myArrayList3.hashCode());
        myArrayList3.clear();
        System.out.println("Очистили 3 коллекцию: " + myArrayList3.toString());
    }
}
