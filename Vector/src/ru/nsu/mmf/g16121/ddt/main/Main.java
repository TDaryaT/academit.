package ru.nsu.mmf.g16121.ddt.main;

import ru.nsu.mmf.g16121.ddt.vector.Vector;

import static ru.nsu.mmf.g16121.ddt.vector.Vector.addVector;
import static ru.nsu.mmf.g16121.ddt.vector.Vector.mul;
import static ru.nsu.mmf.g16121.ddt.vector.Vector.subVector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(3);
        vector1.setCoordinate(0, 1);
        vector1.setCoordinate(1, 0);
        vector1.setCoordinate(2, 0);
        System.out.println("vector 1 = " + vector1.toString());
        System.out.println("его размерность = " + vector1.getSize());
        System.out.println("его длина = " + vector1.length());
        int i = 1;
        System.out.println("его " + (i + 1) + "координата = " + vector1.getCoordinate(i));
        System.out.println();

        Vector vector2 = new Vector(vector1);
        System.out.println("vector 2 (copy vector 1) = " + vector2.toString());
        System.out.println("его размерность = " + vector2.getSize());
        System.out.println("его длина = " + vector2.length());
        System.out.println();

        double[] support = {1, 2, 3};
        Vector vector3 = new Vector(support);
        System.out.println("Vector 3 = " + vector3.toString());
        System.out.println("его размерность = " + vector3.getSize());
        System.out.println("его длина = " + vector3.length());
        vector3.subVector(vector1);
        System.out.println("vector 3 = vector 3 - vector 1 = " + vector3.toString());
        System.out.println();

        Vector vector4 = new Vector(4, support);
        System.out.println("Vector 4 = " + vector4.toString());
        System.out.println("его размерность = " + vector4.getSize());
        System.out.println("его длина = " + vector4.length());
        vector4.addVector(vector1);
        System.out.println("vector 4 = vector 4 + vector 1 = " + vector4.toString());
        vector1.addVector(vector4);
        System.out.println("vector 1 = vector 1 + new vector 4 = " + vector1.toString());
        System.out.println();

        int alpha = 6;
        vector1.mul(6);
        System.out.println("vector 1 * " + alpha + " = " + vector1);
        vector1.turnVector();
        System.out.println("vector 1 = развернутый вектор vector1 * " + alpha + " это " + vector1);
        System.out.println();

        System.out.println("теперь проверим статические методы:");
        Vector vector5 = new Vector(addVector(vector1, vector3));
        System.out.println("vector 5 = vector 1 + vector 3 = " + vector5);
        Vector vector6 = new Vector(subVector(vector1, vector3));
        System.out.println("vector 6 = vector 1 - vector 3 = " + vector6);
        System.out.println("vector 5 * vector 6 = " + mul(vector5, vector6));
    }
}
