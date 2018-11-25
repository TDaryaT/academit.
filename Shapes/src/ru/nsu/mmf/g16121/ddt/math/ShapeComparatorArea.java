package ru.nsu.mmf.g16121.ddt.math;

import ru.nsu.mmf.g16121.ddt.shape.Shape;

import java.util.Comparator;

public class ShapeComparatorArea implements Comparator<Shape> {

    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}
