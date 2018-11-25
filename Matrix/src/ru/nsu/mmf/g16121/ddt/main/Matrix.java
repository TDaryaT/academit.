package ru.nsu.mmf.g16121.ddt.main;

import ru.nsu.mmf.g16121.ddt.vector.Vector;

import java.util.Arrays;

import static ru.nsu.mmf.g16121.ddt.vector.Vector.addVector;
import static ru.nsu.mmf.g16121.ddt.vector.Vector.scalarMul;
import static ru.nsu.mmf.g16121.ddt.vector.Vector.subVector;

public class Matrix {
    private Vector[] row;

    public Matrix(int n, int m) {
        if (n <= 0) {
            throw new IllegalArgumentException("You cannot create a matrix of size 0 or less");
        }
        row = new Vector[n];
        for (int i = 0; i < n; ++i) {
            row[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        int rowCount = matrix.getRowCount();
        row = new Vector[rowCount];
        for (int i = 0; i < rowCount; ++i) {
            row[i] = new Vector(matrix.row[i]);
        }
    }

    public Matrix(double[][] matrix) {
        int rowCount = matrix.length;
        if (rowCount == 0) {
            throw new IllegalArgumentException("You cannot create a matrix of size 0");
        }
        row = new Vector[rowCount];

        int columnCount = 1;
        for (double[] aMatrix : matrix) {
            if (aMatrix.length == 0) {
                throw new IllegalArgumentException("You cannot create a row of matrix of size 0");
            }
            if (aMatrix.length > columnCount) {
                columnCount = aMatrix.length;
            }
        }

        for (int i = 0; i < rowCount; ++i) {
            row[i] = new Vector(Arrays.copyOf(matrix[i], columnCount));
        }
    }

    public Matrix(Vector[] vectors) {
        int rowCount = vectors.length;
        if (rowCount <= 0) {
            throw new IllegalArgumentException("You cannot create a matrix of size 0 or less");
        }
        row = new Vector[rowCount];
        int columnCount = 1;
        for (Vector vector : vectors) {
            if (vector.getSize() == 0) {
                throw new IllegalArgumentException("You cannot create a row of matrix of size 0");
            }
            if (vector.getSize() > columnCount) {
                columnCount = vector.getSize();
            }
        }
        for (int i = 0; i < rowCount; ++i) {
            row[i] = new Vector(columnCount);
            row[i].addVector(vectors[i]);
        }
    }

    public int getRowCount() {
        return row.length;
    }

    public int getColumnCount() {
        int rowCount = getRowCount();
        int columnCount = 1;
        for (int i = 0; i < rowCount; ++i) {
            if (row[i].getSize() > columnCount) {
                columnCount = row[i].getSize();
            }
        }
        return columnCount;
    }

    public Vector getRow(int i) {
        if (i < 0 || i >= this.getRowCount()) {
            throw new IllegalArgumentException("Illegal index of row");
        }
        return new Vector(row[i]);
    }

    public void setRow(int i, Vector vector) {
        if (i < 0 || i >= this.getRowCount()) {
            throw new IllegalArgumentException("Illegal index of row");
        }
        int size = vector.getSize();
        for (int j = 0; j < size; ++j) {
            row[i].setCoordinate(j, vector.getCoordinate(j));
        }
    }

    public Vector getColumn(int i) {
        if (i < 0 || i >= this.getColumnCount()) {
            throw new IllegalArgumentException("Illegal index of column");
        }
        int height = this.getRowCount();
        Vector vector = new Vector(height);
        for (int j = 0; j < height; ++j) {
            vector.setCoordinate(j, this.row[j].getCoordinate(i));
        }
        return vector;
    }

    public void transposition() {
        int rowCount = this.getRowCount();
        int columnCount = this.getColumnCount();
        for (int i = 0; i < rowCount; ++i) {
            for (int j = i; j < columnCount; ++j) {
                double support = row[i].getCoordinate(j);
                row[i].setCoordinate(j, getRow(j).getCoordinate(i));
                row[j].setCoordinate(i, support);
            }
        }
    }

    public void scalarMultiplication(double scalar) {
        int rowCount = this.getRowCount();
        for (int i = 0; i < rowCount; ++i) {
            this.row[i].scalarMul(scalar);
        }
    }

    public double getDeterminant() {
        int height = this.getRowCount();
        if (height != this.getColumnCount()) {
            throw new IllegalArgumentException("determinant can be computed from the elements of a square matrix only");
        }
        if (height == 1) {
            return getRow(0).getCoordinate(0);
        } else if (height == 2) {
            return getRow(0).getCoordinate(0) * getRow(1).getCoordinate(1) -
                    getRow(0).getCoordinate(1) * getRow(1).getCoordinate(0);
        } else if (height == 3) {
            return getRow(0).getCoordinate(0) * getRow(1).getCoordinate(1) *
                    getRow(2).getCoordinate(2) + getRow(2).getCoordinate(0) *
                    getRow(0).getCoordinate(1) * getRow(1).getCoordinate(2) +
                    getRow(0).getCoordinate(2) * getRow(1).getCoordinate(0) *
                            getRow(2).getCoordinate(1) - getRow(0).getCoordinate(2) *
                    getRow(1).getCoordinate(1) * getRow(2).getCoordinate(0) -
                    getRow(1).getCoordinate(0) * getRow(0).getCoordinate(1) *
                            getRow(2).getCoordinate(2) - getRow(0).getCoordinate(0) *
                    getRow(2).getCoordinate(1) * getRow(1).getCoordinate(2);
        } else {
            int decompositionIndex = 0;
            double determinant = 0;
            for (int i = 0; i < height; i++) {
                determinant += Math.pow(-1, i) * getRow(i).getCoordinate(decompositionIndex) *
                        getMinor(this, i, decompositionIndex);
            }
            return determinant;
        }
    }

    private static double getMinor(Matrix matrix, int firstIndex, int secondIndex) {
        int length = matrix.getRowCount() - 1;
        Matrix result = new Matrix(length, length);
        for (int i = 0, i2 = 0; i < length + 1; i++) {
            for (int j = 0, j2 = 0; j < length + 1; j++) {
                if (i != firstIndex && j != secondIndex) {
                    result.row[i2].setCoordinate(j2, matrix.getRow(i).getCoordinate(j));
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
        int size = getRowCount();
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(row[i].toString()).append(",\n");
        }
        stringBuilder.append(row[size - 1]).append("}");
        return stringBuilder.toString();
    }

    public Vector mul(Vector vector) {
        int height = this.getRowCount();
        int width = this.getColumnCount();
        Vector mulResult = new Vector(height);
        for (int i = 0; i < height; ++i) {
            double support = 0;
            for (int j = 0; j < width; ++j) {
                support += this.getRow(i).getCoordinate(j) * vector.getCoordinate(j);
            }
            mulResult.setCoordinate(i, support);
        }
        return mulResult;
    }

    public void sum(Matrix matrix) {
        int height = this.getRowCount();
        if (height != matrix.getRowCount() || this.getColumnCount() != matrix.getColumnCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        for (int i = 0; i < height; ++i) {
            Vector vector = addVector(this.getRow(i), matrix.getRow(i));
            this.setRow(i, vector);
        }
    }

    public void sub(Matrix matrix) {
        int height = this.getRowCount();
        if (height != matrix.getRowCount() || this.getColumnCount() != matrix.getColumnCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        for (int i = 0; i < height; ++i) {
            Vector vector = subVector(this.getRow(i), matrix.getRow(i));
            this.setRow(i, vector);
        }
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getRowCount() || matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        Matrix matrix = new Matrix(matrix1);
        matrix.sum(matrix2);
        return matrix;
    }

    public static Matrix sub(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getRowCount() || matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        Matrix matrix = new Matrix(matrix1);
        matrix.sub(matrix2);
        return matrix;
    }

    public static Matrix mul(Matrix matrix1, Matrix matrix2) {
        int rowCount = matrix1.getRowCount();
        if (rowCount != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        Matrix mul = new Matrix(rowCount, rowCount);
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < rowCount; ++j) {
                double support = scalarMul(matrix1.getRow(i), matrix2.getColumn(j));
                mul.row[i].setCoordinate(j, support);
            }
        }
        return mul;
    }
}
