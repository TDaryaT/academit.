package ru.nsu.mmf.g16121.ddt.main;

import ru.nsu.mmf.g16121.ddt.math.*;

public class Main {
    public static void main(String[] args) {
        MyList<String> myList = new MyList<String>();

        myList.addFirst("Hello");
        myList.addFirst("velvet");
        myList.addFirst("blue");
        myList.addFirst("green");

        System.out.println(myList);
        System.out.println();
        System.out.println("Количество элементов в списке: " + myList.getSize());
        System.out.println("Первый элемент в списке: " + myList.getFirst());
        System.out.println();

        System.out.println("Развернем список: ");
        myList.turn();
        System.out.println(myList);
        System.out.println();

        int i = 2;
        System.out.println((i + 1) + "й элемент списка " + myList.get(i));
        System.out.println();

        System.out.println("Удалим первый элемент списка: ");
        System.out.println("Удаленный элемент списка: " + myList.remove());
        System.out.println(myList);
        System.out.println();

        String s = "red";
        i = 2;
        System.out.println("Изменим элемент списка: " + s + " на место " + (i + 1));
        System.out.println("Старый элемент списка: " + myList.set(i, "red"));
        System.out.println(myList);
        System.out.println();

        i = 1;
        System.out.println("Удалим элемент " + (i + 1));
        System.out.println("Удаленный элемент списка : " + myList.remove(i));
        System.out.println(myList);
        System.out.println();


        s = "purple";
        System.out.println("Вставим на то же место другой элемент : " + s);
        myList.add(i, s);
        System.out.println(myList);
        System.out.println();

        System.out.println("Теперь удалим из списка по содержанию элемента: " + s);
        if (myList.remove(s)) {
            System.out.println("Удалено");
        } else {
            System.out.println("Нет такого элемента в списке");
        }
        System.out.println(myList);
        System.out.println();

        System.out.println("Скопируем получившийся список: ");
        MyList <String> myList2 = myList.getCopy();
        System.out.println(myList2);
        System.out.println("Изменим копию (удалим первый элемент) " + myList2.remove() + ": ");
        System.out.println(myList2);
        System.out.println("Исходный список: ");
        System.out.println(myList);
    }
}
