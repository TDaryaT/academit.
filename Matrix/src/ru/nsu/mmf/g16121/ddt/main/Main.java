package ru.nsu.mmf.g16121.ddt.main;

import ru.nsu.mmf.g16121.ddt.math.Matrix;
import ru.nsu.mmf.g16121.ddt.vector.Vector;

import java.util.Arrays;

import static ru.nsu.mmf.g16121.ddt.math.Matrix.mul;
import static ru.nsu.mmf.g16121.ddt.math.Matrix.sub;
import static ru.nsu.mmf.g16121.ddt.math.Matrix.sum;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 4);
        System.out.println(matrix1.toString());
        System.out.println();

        double[][] matrix = {
                {1, 2, 7, 21},
                {5, 5, 9, 11},
                {3, 8, 11, 4},
                {2, 3, 1, 12}
        };
        Matrix matrix2 = new Matrix(matrix);
        System.out.println(matrix2.toString());
        System.out.println();

        System.out.println("Определитель = " + matrix2.getDeterminant());
        System.out.println("Количество столбцов = " + matrix2.getColumnCount());
        System.out.println("Количество строк = " + matrix2.getRowCount());
        System.out.println();

        Matrix matrix3 = new Matrix(matrix2);
        System.out.println("Конструктор копирования(матрица 3): ");
        System.out.println(matrix3.toString());
        System.out.println();

        int i = 0;
        System.out.println("Строка номер " + (i + 1) + ":");
        System.out.println(matrix2.getRow(i).toString());
        System.out.println();

        double[] value = {20, 2, 5, 6};
        System.out.println("изменим ее на " + Arrays.toString(value));
        matrix2.setRow(i, new Vector(value));
        System.out.println(matrix2.getRow(0).toString());
        System.out.println();

        i = 1;
        System.out.println("Столбец номер " + (i + 1) + ":");
        System.out.println(matrix2.getColumn(i).toString());
        System.out.println();

        System.out.println("В итоге получили матрицу: ");
        System.out.println(matrix2.toString());
        System.out.println();

        System.out.println("Трансформируем матрицу: ");
        matrix2.transposition();
        System.out.println(matrix2.toString());
        System.out.println();

        double alpha = 6;
        System.out.println("Умножим матрицу на скаляр: " + alpha);
        matrix2.scalarMultiplication(alpha);
        System.out.println(matrix2.toString());
        System.out.println();

        System.out.println("Умножение матрицы на вектор ");
        Vector vector = new Vector(new double[]{0, 0, 0, 1});
        System.out.println(vector.toString() + ":");
        Vector mulOnVec = matrix2.mul(vector);
        System.out.println(mulOnVec.toString());
        System.out.println();

        System.out.println("Матрица, созданная из массива векторов:");
        Vector[] vectors = new Vector[]{new Vector(new double[]{1, 4, 2}), new Vector(new double[]{3, 6, 7})};
        Matrix matrix4 = new Matrix(vectors);
        System.out.println(matrix4.toString());

        System.out.println("Сложим матрицу 2 и 3(статически):");
        Matrix matrix5 = sum(matrix2, matrix3);
        System.out.println(matrix5.toString());
        System.out.println();

        System.out.println("Вычтем из матрицы 2 матрицу 3(статически):");
        Matrix matrix6 = sub(matrix2, matrix3);
        System.out.println(matrix6.toString());
        System.out.println();

        System.out.println("Прибавим к матрице 2 матрицу 3:");
        matrix2.sum(matrix3);
        System.out.println("Теперь матрица 2 имеет вид: ");
        System.out.println(matrix2.toString());
        System.out.println();

        System.out.println("Теперь вычтем из новой матрицы 2 матрицу 3:");
        matrix2.sub(matrix3);
        System.out.println("Теперь матрица 2 имеет вид: ");
        System.out.println(matrix2.toString());
        System.out.println();

        System.out.println("Умножим матрицу (единичную) на матрицу 3:");
        matrix1 = new Matrix(new double[][]{
                {1,0,0,0},
                {0,1,0,0},
                {0,0,1,0},
                {0,0,0,1}
        });
        Matrix matrix7 = mul(matrix1, matrix3);
        System.out.println(matrix7.toString());
    }
}
