package ru.nsu.mmf.g16121.ddt.main;

import ru.nsu.mmf.g16121.ddt.shape.*;

import java.util.Arrays;

public class Main {
    private static Shape getShapeWithMaxArea(Shape... shapes) {
        Arrays.sort(shapes, new ShapeComparatorArea());
        return shapes[shapes.length - 1];
    }

    private static Shape getShapeWithSecondPerimeter(Shape... shapes) {
        Arrays.sort(shapes, new ShapeComparatorPerimeter());
        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape s1 = new Square(2);
        System.out.println("Площадь квадрата = " + s1.getArea());
        System.out.println("Ширина = " + s1.getWidth());
        System.out.println("Высота = " + s1.getHeight());
        System.out.println("Периметр = " + s1.getPerimeter());
        System.out.println(s1.toString());
        System.out.println("hash = " + s1.hashCode());
        System.out.println();

        Shape s2 = new Triangle(0, 0, 3, 0, 0, 4);
        System.out.println("Площадь треугольника = " + s2.getArea());
        System.out.println("Ширина = " + s2.getWidth());
        System.out.println("Высота = " + s2.getHeight());
        System.out.println("Периметр = " + s2.getPerimeter());
        System.out.println(s2.toString());
        System.out.println("hash = " + s2.hashCode());
        System.out.println(s2.equals(s1));
        System.out.println();

        Shape s3 = new Rectangle(10, 20);
        System.out.println("Площадь прямоугольника = " + s3.getArea());
        System.out.println("Ширина = " + s3.getWidth());
        System.out.println("Высота = " + s3.getHeight());
        System.out.println("Периметр = " + s3.getPerimeter());
        System.out.println(s3.toString());
        System.out.println("hash = " + s3.hashCode());
        System.out.println(s3.equals(s2));
        System.out.println();

        Shape s4 = new Circle(5);
        System.out.println("Площадь круга = " + s4.getArea());
        System.out.println("Ширина = " + s4.getWidth());
        System.out.println("Высота = " + s4.getHeight());
        System.out.println("Периметр = " + s4.getPerimeter());
        System.out.println(s4.toString());
        System.out.println("hash = " + s4.hashCode());
        System.out.println(s4.equals(s3));
        System.out.println();

        Shape s5 = new Square(6);
        Shape s6 = new Triangle(0, 7, 8, 4, 9, 3);
        Shape s7 = new Rectangle(3, 20);
        Shape s8 = new Circle(7);

        Shape maxShape = getShapeWithMaxArea(s1, s2, s3, s4, s5, s6, s7, s8);
        System.out.println("Площадь максимальной фигуры = " + maxShape.getArea());
        System.out.println("Ширина = " + maxShape.getWidth());
        System.out.println("Высота = " + maxShape.getHeight());
        System.out.println("Периметр = " + maxShape.getPerimeter());
        System.out.println();


        Shape secondShape = getShapeWithSecondPerimeter(s1, s2, s3, s4, s5, s6, s7, s8);
        System.out.println("Площадь второй по величине фигуры = " + secondShape.getArea());
        System.out.println("Ширина = " + secondShape.getWidth());
        System.out.println("Высота = " + secondShape.getHeight());
        System.out.println("Периметр = " + secondShape.getPerimeter());
    }
}
