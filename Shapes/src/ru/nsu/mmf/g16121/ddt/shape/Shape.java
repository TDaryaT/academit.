package ru.nsu.mmf.g16121.ddt.shape;

public interface Shape {
    double getWidth();

    double getHeight();

    double getArea();

    double getPerimeter();

    String toString();

    boolean equals(Object obj);

    int hashCode();
}
