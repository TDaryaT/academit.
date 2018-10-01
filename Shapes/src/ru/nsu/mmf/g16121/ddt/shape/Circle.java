package ru.nsu.mmf.g16121.ddt.shape;

public class Circle implements Shape {
    private double radius;
    private final int diamCoefficient = 2;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {

        return diamCoefficient * radius;
    }

    @Override
    public double getHeight() {
        return diamCoefficient * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return diamCoefficient * Math.PI * radius;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Circle p = (Circle) obj;
        return radius == p.radius;
    }

    @Override
    public String toString() {
        return "[" + radius + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        return prime + Double.hashCode(radius);
    }
}
