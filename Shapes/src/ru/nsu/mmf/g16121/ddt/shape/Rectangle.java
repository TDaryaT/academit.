package ru.nsu.mmf.g16121.ddt.shape;

public class Rectangle extends Shape {
    private double width;
    private double height;
    private final static int perimeterCoefficient = 2;

    public Rectangle(double side1, double side2) {
        this.width = side1;
        this.height = side2;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getArea() {
        return height * width;
    }

    public double getPerimeter() {
        return perimeterCoefficient * (height + width);
    }
}
