package ru.nsu.mmf.g16121.ddt.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        coordinates = new double[n];
    }

    public Vector(Vector vector) {
        int n = vector.getSize();
        if (n == 0) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        coordinates = new double[n];
        coordinates = Arrays.copyOf(vector.coordinates, n);
    }

    public Vector(double[] coordinates) {
        int n = coordinates.length;
        if (n == 0) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        this.coordinates = new double[n];
        this.coordinates = Arrays.copyOf(coordinates, n);
    }

    public Vector(int n, double[] coordinates) {
        if (n <= 0 || coordinates.length == 0) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        this.coordinates = new double[n];
        this.coordinates = Arrays.copyOf(coordinates, n);
    }

    public int getSize() {
        return coordinates.length;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        int size = getSize();
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(coordinates[i]).append(", ");
        }
        stringBuilder.append(coordinates[size - 1]).append("}");
        return stringBuilder.toString();
    }

    public void addVector(Vector vector) {
        boolean isSmallerSize = this.getSize() <= vector.getSize();
        int size = (isSmallerSize) ? this.getSize() : vector.getSize();
        for (int i = 0; i < size; i++) {
            this.coordinates[i] += vector.coordinates[i];
        }
        if (isSmallerSize) {
            int vectorSize = vector.getSize();
            coordinates = Arrays.copyOf(coordinates, vectorSize);
            for (int i = size; i < vectorSize; ++i) {
                coordinates[i] += vector.coordinates[i];
            }
        }
    }

    public void subVector(Vector vector) {
        boolean isSmallerSize = this.getSize() <= vector.getSize();
        int size = (isSmallerSize) ? this.getSize() : vector.getSize();
        for (int i = 0; i < size; i++) {
            this.coordinates[i] -= vector.coordinates[i];
        }
        if (isSmallerSize) {
            int vectorSize = vector.getSize();
            coordinates = Arrays.copyOf(coordinates, vectorSize);
            for (int i = size; i < vectorSize; ++i) {
                coordinates[i] -= vector.coordinates[i];
            }
        }
    }

    public void mul(double number) {
        int size = coordinates.length;
        for (int i = 0; i < size; i++) {
            this.coordinates[i] *= number;
        }
    }

    public void turnVector() {
        this.mul(-1.0);
    }

    public double length() {
        double support = 0;
        for (double elem : this.coordinates) {
            support += Math.pow(elem, 2);
        }
        return Math.sqrt(support);
    }

    public double getCoordinate(int index) {
        return coordinates[index];
    }

    public void setCoordinate(int index, double elem) {
        coordinates[index] = elem;
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
        int size = coordinates.length;
        for (int i = 0; i < size; i++) {
            if (this.getCoordinate(i) != p.getCoordinate(i)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        final int prime = 37;
        return prime + Arrays.hashCode(coordinates);
    }

    public static Vector addVector(Vector vector1, Vector vector2) {
        Vector vector = new Vector(1);
        vector.addVector(vector1);
        vector.addVector(vector2);
        return vector;
    }

    public static Vector subVector(Vector vector1, Vector vector2) {
        Vector vector = new Vector(1);
        vector.addVector(vector1);
        vector.subVector(vector2);
        return vector;
    }

    public static double mul(Vector vector1, Vector vector2) {
        if (vector1.getSize() != vector2.getSize()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        double mul = 0;
        int size = vector1.getSize();
        for (int i = 0; i< size; ++i){
            mul += vector1.coordinates[i]*vector2.coordinates[i];
        }
        return mul;
    }
}
