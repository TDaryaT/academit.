package ru.nsu.mmf.g16121.ddt;

import java.util.Scanner;

import ru.nsu.mmf.g16121.ddt.math.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(-7, 0);

        System.out.println("Начало диапазона: " + range.getFrom());
        System.out.println("Конец диапазона: " + range.getTo());
        System.out.println("Длинна диапазона: " + range.getLength());

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число: ");
        double number = scanner.nextDouble();
        if (range.isInside(number)) {
            System.out.println("Число принадлежит диапазону");
        } else {
            System.out.println("Число не принадлежит диапазону");
        }
        System.out.println();

        System.out.print("Изменим границы диапазона. Введите начало диапазона: ");
        range.setFrom(scanner.nextDouble());
        System.out.print("Введите конец диапазона: ");
        range.setTo(scanner.nextDouble());

        System.out.println("Используем функции для нового диапозона: ");

        System.out.println("Начало диапазона: " + range.getFrom());
        System.out.println("Конец диапазона: " + range.getTo());
        System.out.println("Длинна диапазона: " + range.getLength());

        System.out.print("Введите число: ");
        number = scanner.nextDouble();
        if (range.isInside(number)) {
            System.out.println("Число принадлежит диапазону");
        } else {
            System.out.println("Число не принадлежит диапазону");
        }

        Range range1 = new Range(1, 6);
        Range range2 = new Range(5, 8);

        System.out.print("Пересечение диапозонов: ");
        range1.print();
        System.out.print("и ");
        range2.print();
        System.out.println(":");
        range = Range.getIntersection(range1, range2);
        if (range != null) {
            range.print();
        }
        System.out.println();

        System.out.print("Объединение диапозонов: ");
        range1.print();
        System.out.print("и ");
        range2.print();
        System.out.println(":");
        Range[] add = Range.getAssociation(range1, range2);
        for (Range elem : add) {
            elem.print();
            System.out.print(" ");
        }
        System.out.println();

        System.out.print("Разность диапозонов: ");
        range1.print();
        System.out.print("и ");
        range2.print();
        System.out.println(":");
        Range[] del = Range.getAddition(range1, range2);
        if (del != null) {
            for (Range elem : del) {
                elem.print();
                System.out.print(" ");
            }
        }
    }
}
