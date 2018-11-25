package ru.nsu.mmf.g16121.ddt.main;

import ru.nsu.mmf.g16121.ddt.math.*;

public class Main {
    public static void main(String[] args) {
        List<String> list = new List<String>();

        list.insertFirstElem("Hello");
        list.insertFirstElem("velvet");
        list.insertFirstElem("blue");
        list.insertFirstElem("green");

        System.out.println(list.toString());
        System.out.println();
        System.out.println("Количество элементов в списке: " + list.getCount());
        System.out.println("Первый элемент в списке: " + list.getFirstElem());
        System.out.println();

        System.out.println("Развернем список: ");
        list.turn();
        System.out.println(list.toString());
        System.out.println();

        int i = 2;
        System.out.println((i + 1) + "й элемент списка " + list.getDataFrom(i));
        System.out.println();

        System.out.println("Удалим первый элемент списка: ");
        System.out.println("Удаленный элемент списка: " + list.delFirstElem());
        System.out.println(list.toString());
        System.out.println();

        String s = "red";
        i = 2;
        System.out.println("Изменим элемент списка: " + s + " на место " + (i + 1));
        System.out.println("Старый элемент списка: " + list.setDataFrom(i, "red"));
        System.out.println(list.toString());
        System.out.println();

        i = 1;
        System.out.println("Удалим элемент по индексу : " + (i + 1));
        System.out.println("Удаленный элемент списка : " + list.delDataFrom(i));
        System.out.println(list.toString());
        System.out.println();

        s = "purple";
        System.out.println("Вставим на то же место другой элемент : " + s);
        list.insertDataFrom(i, s);
        System.out.println(list.toString());
        System.out.println();

        System.out.println("Теперь удалим из списка по содежанию элемента: " + s);
        if (list.delData(s)) {
            System.out.println("Удалено");
            System.out.println(list.toString());
        } else {
            System.out.println("Нет такого элемента в списке");
            System.out.println(list.toString());
        }
    }
}
