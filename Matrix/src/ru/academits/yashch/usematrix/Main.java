package ru.academits.yashch.usematrix;

import ru.academits.yashch.matrix.Matrix;
import ru.academits.yashch.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(2, 4);
        System.out.println(matrix1);

        Matrix matrix2 = new Matrix(matrix1);
        System.out.println(matrix2);

        double[][] array1 = {{1, 2, 5}, {1, 7, 9}, {2, 6, 8}};

        Matrix matrix3 = new Matrix(array1);
        System.out.println(matrix3);

        Vector vector1 = new Vector(4);
        Vector vector2 = new Vector(2, new double[]{1, 4});
        Vector vector3 = new Vector(1);

        Vector[] vectorsArray = {vector1, vector2, vector3};

        Matrix matrix4 = new Matrix(vectorsArray);
        System.out.println(matrix4);

        System.out.println(matrix4.getRowsCount());
        System.out.println(matrix4.getColumnsCount());

        Vector vector4 = new Vector(new double[]{1, 5, 7, 4});

        System.out.println(matrix4.getRow(1));
        matrix4.setRow(0, vector4);
        System.out.println(matrix4.getRow(0));

        System.out.println(matrix4.getColumn(1));

        double[][] array2 = {{1, 5, 7, 2}, {7, 2, 1, 6}, {0, 3, 1, 6}, {5, 2, 6, 8}};

        Matrix matrix5 = new Matrix(array2);

        matrix5.transpose();

        System.out.println(matrix5);

        double[][] array3 = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};

        Matrix matrix6 = new Matrix(array3);

        matrix6.multiply(0);
        System.out.println(matrix6);

        double[][] array4 = {{1, 6, 2}, {8, 0, 9}, {3, 6, 7}};
        Vector vector5 = new Vector(new double[]{1, 7, 2});
        Matrix matrix7 = new Matrix(array4);

        System.out.println(matrix7.multiplyByVector(vector5));

        double[][] array5 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        double[][] array6 = {{4, 4, 4}, {4, 4, 4}, {4, 4, 4}};

        Matrix matrix8 = new Matrix(array5);
        Matrix matrix9 = new Matrix(array6);

        matrix8.add(matrix9);

        System.out.println(matrix8);

        matrix8.subtract(matrix9);

        System.out.println(matrix8);

        System.out.println(Matrix.getSum(matrix8, matrix9));
        System.out.println(Matrix.getDifference(matrix9, matrix8));

        double[][] array7 = {{1, 7, 3, 5}, {3, 3, 2, 7}, {2, 8, 9, 3}};
        double[][] array8 = {{7, 6, 2}, {1, 3, 3}, {4, 6, 4}, {1, 5, 2}};

        Matrix matrix10 = new Matrix(array7);
        Matrix matrix11 = new Matrix(array8);

        System.out.println(Matrix.getProduct(matrix10, matrix11));

        Matrix matrix12 = new Matrix(new double[][]{{4}});
        System.out.println(matrix12.getDeterminant());

        Matrix matrix13 = new Matrix(new double[][]{{5, 3}, {3, 6}});
        System.out.println(matrix13.getDeterminant());

        Matrix matrix14 = new Matrix(new double[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        System.out.println(matrix14.getDeterminant());

        Matrix matrix15 = new Matrix(new double[][]{{1, 5, 6, 8}, {1, 1, 5, 7}, {6, 1, 9, 3}, {1, 7, 4, 2}});
        System.out.println(matrix15.getDeterminant());

        System.out.println("-----------------------------------");

        Matrix matrix16 = new Matrix(array8);
        System.out.println(matrix16.getRow(3));

        double[] array9 = new double[]{0, 1, 5};
        matrix16.setRow(0, new Vector(array9));
        System.out.println(matrix16.getRow(0));

        System.out.println(matrix16);
        matrix16.transpose();
        System.out.println(matrix16);

        Matrix matrix17 = new Matrix(new double[][]{{2}});
        matrix17.transpose();
        System.out.println(matrix17);

        Matrix matrix18 = new Matrix(new double[][]{{2, 3, 5}, {1, 0, 3}, {9, 15, 2}});
        Vector vector18 = new Vector(new double[]{1, 5, 8});
        System.out.println(matrix18.multiplyByVector(vector18));

        Matrix matrix19 = new Matrix(new double[][]{{2, 3, 5}, {1, 0, 3}, {9, 15, 2}, {1, 5, 2}, {10, 12, 15}, {11, 22, 55}, {30, 1, 5}});
        matrix19.transpose();
        System.out.println(matrix19);

        System.out.println("--------------------");

        Matrix matrix20 = new Matrix(new double[][]{{1, 5, 6, 8}, {1, 1, 5, 7}, {6, 1, 9, 3}, {1, 7, 4, 2}});
        System.out.println(matrix20);
        matrix20.transpose();
        System.out.println(matrix20);
    }
}