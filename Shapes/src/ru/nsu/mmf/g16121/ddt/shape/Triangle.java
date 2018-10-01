package ru.nsu.mmf.g16121.ddt.shape;

public class Triangle extends Shape {
    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    public double getSide1() {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public double getSide2() {
        return Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
    }

    public double getSide3() {
        return Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
    }

    public double getWidth() {
        return getMax(x1, x2, x3) - getMin(x1, x2, x3);
    }

    public double getHeight() {
        return getMax(y1, y2, y3) - getMin(y1, y2, y3);
    }

    public double getArea() {
        double semiPerimeter = getPerimeter(this) * 0.5;
        return Math.sqrt(semiPerimeter * (semiPerimeter - this.getSide1()) *
                (semiPerimeter - this.getSide2()) * (semiPerimeter - this.getSide3()));
    }

    public double getPerimeter(Triangle triangle) {
        return triangle.getSide1() + triangle.getSide2() + triangle.getSide3();
    }

    private static double getMax(double... numbers) {
        double max = numbers[0];
        for (double elem : numbers) {
            if (elem > max) {
                max = elem;
            }
        }
        return max;
    }

    private static double getMin(double... numbers) {
        double min = numbers[0];
        for (double elem : numbers) {
            if (elem < min) {
                min = elem;
            }
        }
        return min;
    }
}
