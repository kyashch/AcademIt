package ru.academits.yashch.usematrix;

import ru.academits.yashch.matrix.Matrix;
import ru.academits.yashch.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 4);
        System.out.println(matrix1);

        double[][] array = {{3, 5, 7}, {1, 3}};
        Matrix matrix2 = new Matrix(array);
        System.out.println(matrix2);

        Matrix matrix3 = new Matrix(matrix2);
        System.out.println(matrix3);

        Vector vector1 = new Vector(new double[]{1, 4, 7, 8});
        Vector vector2 = new Vector(new double[]{6, 1, 3});
        Vector[] vectorsArray = {vector1, vector2};

        Matrix matrix4 = new Matrix(vectorsArray);
        System.out.println(matrix4);

        Matrix matrix5 = new Matrix(new double[][]{{4, 6, 7, 9}, {3, 4, 1, 0}, {0, 2, 1, 6}, {5, 6, 9, 9}});
        System.out.println(matrix5.getSize());

        System.out.println(matrix5.getStringVector(2));

        matrix5.setStringVector(0, vector1);
        System.out.println(matrix5);
        System.out.println(matrix5.getColumnVector(0));

        Matrix matrix6 = new Matrix(new double[][]{{2, 4, 1, 0}, {5, 3, 2, 1}, {1, 2, 1, 6}, {4, 7, 8, 1}});
        matrix6.transpose();
        System.out.println(matrix6);

        Matrix matrix7 = new Matrix(new double[][]{{2, 2, 2}, {2, 2, 2}, {2, 2, 2}, {2, 2, 2}});
        matrix7.multiplyByScalar(2);
        System.out.println(matrix7);

        Matrix matrix8 = new Matrix(new double[][]{{2, 3, 1, 9, 20}, {1, 6, 0, 9, 30}, {8, 3, 5, 1, 40}, {1, 6, 8, 3, 50}, {1, 6, 3, 2, 60}});
        System.out.println(matrix8.getMatrixDeterminant());

        Matrix matrix9 = new Matrix(new double[][]{{2, 6, 1, 5}, {7, 0, 4, 2}, {2, 3, 9, 8}});
        Vector vector3 = new Vector(new double[]{2, 5, 7, 1});
        matrix9.multiplyByVector(vector3);
        System.out.println(matrix9);

        Matrix matrix10 = new Matrix(new double[][]{{2, 6, 1, 3}, {7, 0, 4, 2}, {2, 3, 9, 8}});
        Matrix matrix11 = new Matrix(new double[][]{{1, 6, 1, 1}, {6, 3, 0, 2}, {7, 2, 1, 8}});

        matrix10.add(matrix11);
        matrix10.subtract(matrix11);

        System.out.println(matrix10);

        Matrix matrix12 = Matrix.getMatrixAddition(matrix10, matrix11);
        System.out.println(matrix12);

        Matrix matrix13 = Matrix.getMatrixSubtraction(matrix12, matrix11);
        System.out.println(matrix13);

        Matrix matrix14 = new Matrix(new double[][]{{2, 6, 1, 3}, {7, 0, 4, 2}, {2, 3, 9, 8}});
        Matrix matrix15 = new Matrix(new double[][]{{1, 6, 1}, {3, 0, 2}, {2, 1, 8}, {1, 6, 2}});

        Matrix matrix16 = Matrix.getMatrixProduct(matrix14, matrix15);
        System.out.print(matrix16);
    }
}
