package ru.nsu.mmf.g16121.ddt.main;

import ru.nsu.mmf.g16121.ddt.math.HashTable;

import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args){
        Integer[] integers = {1,2,3};
        HashTable<Integer> hashTable = new HashTable<>();

        System.out.println("Пример 1");
        System.out.println("Добавление коллекции: ");
        for (Integer elem : integers){
            System.out.println(elem + " кэш: " + elem.hashCode());
        }
        System.out.println();

        Collections.addAll(hashTable, integers);
        System.out.println("Таблица пуста? " + hashTable.isEmpty());
        System.out.println("Количество элементов в таблице: " + hashTable.size());
        System.out.println("Кэш-таблица: ");
        System.out.println(hashTable);

        System.out.println("Элемент " + 5 + " принадлежит таблице? " + hashTable.contains(5));
        System.out.println("Элемент " + 2 + " принадлежит таблице? " + hashTable.contains(2));
        System.out.println();

        hashTable.add(5);
        hashTable.add(15);
        hashTable.add(10);
        System.out.println("Добавили элементы 5, 15 и 10");
        System.out.println("Кэш-таблица: ");
        System.out.println(hashTable);

        System.out.println("Проверка итератора: ");
        for (Integer elem: hashTable){
            System.out.print(elem + " ");
        }
        System.out.println();

        System.out.println("toArray: ");
        System.out.println(Arrays.toString(hashTable.toArray()));

        System.out.println("Удалим элемент 5");
        hashTable.remove(5);
        System.out.println("Кэш-таблица: ");
        System.out.println(hashTable);

        System.out.println();
        System.out.println("Пример 2");
        HashTable<String> hashTable1 = new HashTable<>(20);
        String string = "World";
        System.out.println("Добавление строки: " + string + " ее кэш: " + string.hashCode());
        System.out.println("Нормализация по длине массива: " + string.hashCode()%20);
        hashTable1.add(string);
        System.out.println("Кэш-таблица: ");
        System.out.println(hashTable1);

        /*
        System.out.println("toArray(T1[] a)");
        String[] array2 = new String[30];
        array2 = hashTable1.toArray(array2);
        System.out.println(Arrays.toString(array2));
        System.out.println();
         */
    }
}
