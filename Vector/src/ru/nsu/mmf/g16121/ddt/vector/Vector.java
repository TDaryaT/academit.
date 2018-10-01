package ru.nsu.mmf.g16121.ddt.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinate;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        coordinate = new double[n];
    }

    public Vector(Vector vector) {
        int n = vector.coordinate.length;
        coordinate = new double[n];
        System.arraycopy(vector.coordinate, 0, coordinate, 0, n);

    }

    public Vector(double[] coordinate) {
        this.coordinate = new double[coordinate.length];
        System.arraycopy(coordinate, 0, this.coordinate, 0, coordinate.length);
    }

    public Vector(int n, double[] coordinate) {
        this.coordinate = new double[n];
        System.arraycopy(coordinate, 0, this.coordinate, 0, coordinate.length);
    }

    public int getSize() {
        return coordinate.length;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        int size = getSize();
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(coordinate[i]).append(", ");
        }
        stringBuilder.append(coordinate[size - 1]).append("}");
        return stringBuilder.toString();
    }

    public void addVector(Vector vector) {
        int size = vector.getSize();
        for (int i = 0; i < size; i++) {
            this.coordinate[i] += vector.coordinate[i];
        }
    }

    public void subVector(Vector vector) {
        int size = vector.getSize();
        for (int i = 0; i < size; i++) {
            this.coordinate[i] -= vector.coordinate[i];
        }
    }

    public void mulVector(double number) {
        int size = coordinate.length;
        for (int i = 0; i < size; i++) {
            this.coordinate[i] *= number;
        }
    }

    public void turnVector() {
        int size = coordinate.length;
        for (int i = 0; i < size; i++) {
            this.coordinate[i] *= (-1.0);
        }
    }

    public double length() {
        int size = coordinate.length;
        double support = 0;
        for (int i = 0; i < size; i++) {
            support += Math.pow(this.coordinate[i], 2);
        }
        return Math.sqrt(support);
    }

    public double getCoordinate(int index) {
        return coordinate[index];
    }

    public void setCoordinate(int index, double elem) {
        coordinate[index] = elem;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Vector p = (Vector) obj;
        if (this.getSize() != p.getSize()) {
            return false;
        }
        int size = coordinate.length;
        for (int i = 0; i < size; i++) {
            if (this.getCoordinate(i) != p.getCoordinate(i)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        final int prime = 37;
        return prime + Arrays.hashCode(coordinate);
    }

    public static Vector addVector(Vector vector1, Vector vector2) {
        int size = vector1.getSize();
        Vector vector = vector1;
        for (int i = 0; i < size; i++) {
            vector.coordinate[i] += vector2.coordinate[i];
        }
        return vector;
    }

    public static Vector subVector(Vector vector1, Vector vector2) {
        int size = vector1.getSize();
        Vector vector = vector1;
        for (int i = 0; i < size; i++) {
            vector.coordinate[i] -= vector2.coordinate[i];
        }
        return vector;
    }

    public static Vector mulVector(Vector vector, double number) {
        int size = vector.getSize();
        Vector vector2 = vector;
        for (int i = 0; i < size; i++) {
            vector2.coordinate[i] *= number;
        }
        return vector2;
    }
}
