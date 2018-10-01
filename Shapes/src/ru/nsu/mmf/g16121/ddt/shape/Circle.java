package ru.nsu.mmf.g16121.ddt.shape;

public class Circle extends Shape {
    private double radius;
    private final int diamCoefficient = 2;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth() {

        return diamCoefficient * radius;
    }

    public double getHeight() {
        return diamCoefficient * radius;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getPerimeter() {
        return diamCoefficient * Math.PI * radius;
    }
}
