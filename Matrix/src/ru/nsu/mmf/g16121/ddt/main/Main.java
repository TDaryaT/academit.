package ru.nsu.mmf.g16121.ddt.main;

import ru.nsu.mmf.g16121.ddt.math.Matrix;
import ru.nsu.mmf.g16121.ddt.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 4);
        System.out.println(matrix1.toString());

        double[][] matrix = {
                {1, 2, 7, 21},
                {5, 5, 9, 11},
                {3, 8, 11, 4},
                {2, 3, 1, 12}
        };
        Matrix matrix2 = new Matrix(matrix);
        System.out.println(matrix2.toString());
        System.out.println("Определитель = " + matrix2.getDeterminant());

        Matrix matrix3 = new Matrix(matrix2);
        System.out.println(matrix3.toString());

        Vector[] vectors = new Vector[]{new Vector(new double[]{1, 4, 2}), new Vector(new double[]{3, 6, 7})};
        Matrix matrix4 = new Matrix(vectors);
        System.out.println(matrix4.toString());
    }
}
