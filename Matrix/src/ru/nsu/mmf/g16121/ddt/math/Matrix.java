package ru.nsu.mmf.g16121.ddt.math;

import ru.nsu.mmf.g16121.ddt.vector.Vector;

import java.util.Arrays;

import static ru.nsu.mmf.g16121.ddt.vector.Vector.addVector;
import static ru.nsu.mmf.g16121.ddt.vector.Vector.scalarMul;
import static ru.nsu.mmf.g16121.ddt.vector.Vector.subVector;

public class Matrix {
    private Vector[] lines;

    public Matrix(int n, int m) {
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        lines = new Vector[n];
        for (int i = 0; i < n; ++i) {
            lines[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        lines = new Vector[matrix.lines.length];
        lines = Arrays.copyOf(matrix.lines, matrix.lines.length);
    }

    public Matrix(double[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        if (width == 0) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        lines = new Vector[height];
        for (int i = 0; i < height; ++i) {
            lines[i] = new Vector(Arrays.copyOf(matrix[i], width));
        }
    }

    public Matrix(Vector[] vectors) {
        int height = vectors.length;
        lines = new Vector[height];
        for (int i = 0; i < height; ++i) {
            lines[i] = new Vector(vectors[i]);
        }
    }

    public int height() {
        return lines.length;
    }

    public int width() {
        return lines[0].getSize();
    }

    public Vector getLine(int i) {
        return lines[i];
    }

    public void setLine(int i, Vector vector) {
        int size = vector.getSize();
        for (int j = 0; j < size; ++j) {
            lines[i].setCoordinate(j, vector.getCoordinate(j));
        }
    }

    public Vector getColumn(int i) {
        int height = this.height();
        Vector vector = new Vector(height);
        for (int j = 0; j < height; ++j) {
            vector.setCoordinate(j, this.lines[j].getCoordinate(i));
        }
        return vector;
    }

    public void transposition() {
        Matrix matrix = new Matrix(this);
        int height = this.height();
        for (int i = 0; i < height; ++i) {
            this.setLine(i, matrix.getColumn(i));
        }
    }

    public void scalarMultiplication(double scalar) {
        int height = this.height();
        int width = this.width();
        for (int i = 0; i < height; ++i) {
            Vector vector = new Vector(getLine(i));
            vector.scalarMul(scalar);
            this.setLine(i, vector);
        }
    }

    public double getDeterminant() {
        int height = this.height();
        if (height != this.width()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        if (height == 1) {
            return getLine(0).getCoordinate(0);
        } else if (height == 2) {
            return getLine(0).getCoordinate(0) * getLine(1).getCoordinate(1) -
                    getLine(0).getCoordinate(1) * getLine(1).getCoordinate(0);
        } else if (height == 3) {
            return getLine(0).getCoordinate(0) * getLine(1).getCoordinate(1) *
                    getLine(2).getCoordinate(2) + getLine(2).getCoordinate(0) *
                    getLine(0).getCoordinate(1) * getLine(1).getCoordinate(2) +
                    getLine(0).getCoordinate(2) * getLine(1).getCoordinate(0) *
                            getLine(2).getCoordinate(1) - getLine(0).getCoordinate(2) *
                    getLine(1).getCoordinate(1) * getLine(2).getCoordinate(0) -
                    getLine(1).getCoordinate(0) * getLine(0).getCoordinate(1) *
                            getLine(2).getCoordinate(2) - getLine(0).getCoordinate(0) *
                    getLine(2).getCoordinate(1) * getLine(1).getCoordinate(2);
        } else {
            int decompositionIndex = 0;
            double determinant = 0;
            for (int i = 0; i < height; i++) {
                determinant += Math.pow(-1, i) * getLine(i).getCoordinate(decompositionIndex) *
                        getMinor(this, i, decompositionIndex);
            }
            return determinant;
        }
    }

    private static double getMinor(Matrix matrix, int firstIndex, int secondIndex) {
        int length = matrix.height() - 1;
        Matrix result = new Matrix(length, length);
        for (int i = 0, i2 = 0; i < length + 1; i++) {
            for (int j = 0, j2 = 0; j < length + 1; j++) {
                if (i != firstIndex && j != secondIndex) {
                    result.getLine(i2).setCoordinate(j2, matrix.getLine(i).getCoordinate(j));
                    j2++;
                    if (j2 == length) {
                        j2 = 0;
                        i2++;
                    }
                }
            }
        }
        return result.getDeterminant();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        int size = height();
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(lines[i].toString()).append(", ");
        }
        stringBuilder.append(lines[size - 1]).append("}");
        return stringBuilder.toString();
    }

    public Vector mul(Vector vector) {
        int height = this.height();
        int width = this.width();
        Vector mulResult = new Vector(height);
        for (int i = 0; i < height; ++i) {
            double support = 0;
            for (int j = 0; j < width; ++j) {
                support += this.getLine(i).getCoordinate(j) * vector.getCoordinate(j);
            }
            mulResult.setCoordinate(i, support);
        }
        return mulResult;
    }

    public void sum(Matrix matrix) {
        int height = this.height();
        if (height != matrix.height() || this.width() != matrix.width()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        for (int i = 0; i < height; ++i) {
            Vector vector = addVector(this.getLine(i), matrix.getLine(i));
            this.setLine(i, vector);
        }
    }

    public void sub(Matrix matrix) {
        int height = this.height();
        if (height != matrix.height() || this.width() != matrix.width()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        for (int i = 0; i < height; ++i) {
            Vector vector = subVector(this.getLine(i), matrix.getLine(i));
            this.setLine(i, vector);
        }
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.height() != matrix2.height() || matrix1.width() != matrix2.width()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        Matrix matrix = new Matrix(matrix1);
        matrix.sum(matrix2);
        return matrix;
    }

    public static Matrix sub(Matrix matrix1, Matrix matrix2) {
        if (matrix1.height() != matrix2.height() || matrix1.width() != matrix2.width()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        Matrix matrix = new Matrix(matrix1);
        matrix.sub(matrix2);
        return matrix;
    }

    public static Vector mul(Matrix matrix1, Matrix matrix2) {
        int height = matrix1.height();
        if (height != matrix2.width()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        Vector vector = new Vector(height);
        for (int i = 0; i < height; ++i) {
            double support = scalarMul(matrix1.getLine(i), matrix2.getColumn(i));
            vector.setCoordinate(i, support);
        }
        return vector;
    }
}
