package ru.nsu.mmf.g16121.ddt.main;

import ru.nsu.mmf.g16121.ddt.shape.Rectangle;
import ru.nsu.mmf.g16121.ddt.shape.Square;
import ru.nsu.mmf.g16121.ddt.shape.Triangle;
import ru.nsu.mmf.g16121.ddt.shape.Circle;
import ru.nsu.mmf.g16121.ddt.shape.Shape;

public class Main {
    public static void main(String[] args) {
        Shape s1 = new Square(2);
        System.out.println("Площадь квадрата = " + s1.getArea());
        System.out.println("Ширина = " + s1.getWidth());
        System.out.println("Высота = " + s1.getHeight());
        System.out.println("Периметр = " + s1.getPerimeter());

        Shape s2 = new Triangle(0, 0, 3, 0, 0, 4);
        System.out.println("Площадь треугольника = " + s2.getArea());
        System.out.println("Ширина = " + s2.getWidth());
        System.out.println("Высота = " + s2.getHeight());
        System.out.println("Периметр = " + s2.getPerimeter());

        Shape s3 = new Rectangle(10, 20);
        System.out.println("Площадь прямоугольника = " + s3.getArea());
        System.out.println("Ширина = " + s3.getWidth());
        System.out.println("Высота = " + s3.getHeight());
        System.out.println("Периметр = " + s3.getPerimeter());

        Shape s4 = new Circle(5);
        System.out.println("Площадь круга = " + s4.getArea());
        System.out.println("Ширина = " + s4.getWidth());
        System.out.println("Высота = " + s4.getHeight());
        System.out.println("Периметр = " + s4.getPerimeter());
    }
}
