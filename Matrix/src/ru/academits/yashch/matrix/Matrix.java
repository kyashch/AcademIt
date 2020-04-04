package ru.academits.yashch.matrix;

import ru.academits.yashch.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк должно быть больше нуля");
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов должно быть больше нуля");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < getRowsCount(); ++i) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < getRowsCount(); ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк матрицы должно быть больше нуля");
        }

        int max = 0;

        for (double[] e : array) {
            if (e.length > max) {
                max = e.length;
            }
        }

        if (max == 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы должно быть больше нуля");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < getRowsCount(); ++i) {
            rows[i] = new Vector(max, array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("Количество строк матрицы должно быть больше нуля");
        }

        int max = 0;

        for (Vector e : vectorsArray) {
            if (e.getSize() > max) {
                max = e.getSize();
            }
        }

        if (max == 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы должно быть больше нуля");
        }

        rows = new Vector[vectorsArray.length];

        for (int i = 0; i < getRowsCount(); ++i) {
            rows[i] = new Vector(max);

            for (int j = 0; j < vectorsArray[i].getSize(); ++j) {
                rows[i].setComponent(j, vectorsArray[i].getComponent(j));
            }
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    private void checkRowIndex(int index) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Индекс строки " + index + " выходит за допустимые границы : " + 0 + "-" + (getRowsCount() - 1));
        }
    }

    private void checkColumnIndex(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс столбца " + index + " выходит за допустимые границы : " + 0 + "-" + (getColumnsCount() - 1));
        }
    }

    public Vector getRow(int index) {
        checkRowIndex(index);

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        checkRowIndex(index);

        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Длина вектора - " + vector.getSize() + " должна быть равна длине строки - " + getColumnsCount());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        checkColumnIndex(index);

        Vector newVector = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); ++i) {
            newVector.setComponent(i, rows[i].getComponent(index));
        }

        return newVector;
    }

    public void transpose() {
        Vector[] newVectorsArray = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); ++i) {
            newVectorsArray[i] = getColumn(i);
        }

        rows = newVectorsArray;
    }

    public void multiply(double number) {
        for (Vector e : rows) {
            e.multiplyByScalar(number);
        }
    }

    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new UnsupportedOperationException("Определитель можно вычислить только для квадратной матрицы");
        }

        if (getColumnsCount() == 1) {
            return rows[0].getComponent(0);
        }

        if (getColumnsCount() == 2) {
            return getSecondOrderDeterminant(this);
        }

        double sum = 0;

        for (int i = 0; i < getColumnsCount(); ++i) {
            sum += rows[0].getComponent(i) * Math.pow(-1, 1 + i + 1) * getPartDeterminant(this, i);
        }

        return sum;
    }

    private static double getPartDeterminant(Matrix matrix, int columnNumber) {
        Matrix newMatrix = new Matrix(matrix.getRowsCount() - 1, matrix.getColumnsCount() - 1);

        for (int i = 0, j = 1; i < newMatrix.getRowsCount(); ++i, ++j) {
            for (int k = 0, m = 0; k < newMatrix.getColumnsCount(); ++k, ++m) {
                if (m == columnNumber) {
                    ++m;
                }

                newMatrix.rows[i].setComponent(k, matrix.rows[j].getComponent(m));
            }
        }

        if (newMatrix.getRowsCount() > 2) {
            return newMatrix.getDeterminant();
        }

        return getSecondOrderDeterminant(newMatrix);
    }

    private static double getSecondOrderDeterminant(Matrix matrix) {
        return matrix.rows[0].getComponent(0) * matrix.rows[1].getComponent(1)
                - matrix.rows[1].getComponent(0) * matrix.rows[0].getComponent(1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Vector e : rows) {
            sb.append(e).append(",");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");

        return sb.toString();
    }

    public Vector multiplyByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Число стобцов в матрице не совпадает с числом строк в векторе-столбце.");
        }

        Vector resultingVector = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); ++i) {
            resultingVector.setComponent(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultingVector;
    }

    private static void checkMatrixSizes(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Матрицы должны иметь одиноковое количество строк" + System.lineSeparator() +
                    "Количество строк первой матрицы = " + matrix1.getRowsCount() + System.lineSeparator() +
                    "Количество строк второй матрицы = " + matrix2.getRowsCount());
        }

        if (matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Матрицы должны иметь одиноковое количество столбцов" + System.lineSeparator() +
                    "Количество столбцов первой матрицы = " + matrix1.getColumnsCount() + System.lineSeparator() +
                    "Количество столбцов второй матрицы = " + matrix2.getColumnsCount());
        }
    }

    public void add(Matrix matrix) {
        checkMatrixSizes(this, matrix);

        for (int i = 0; i < getRowsCount(); ++i) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatrixSizes(this, matrix);

        for (int i = 0; i < getRowsCount(); ++i) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatrixSizes(matrix1, matrix2);

        Matrix newMatrix = new Matrix(matrix1);

        newMatrix.add(matrix2);

        return newMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatrixSizes(matrix1, matrix2);

        Matrix newMatrix = new Matrix(matrix1);

        newMatrix.subtract(matrix2);

        return newMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы" + System.lineSeparator() +
                    "Количество столбцов первой матрицы = " + matrix1.getColumnsCount() + System.lineSeparator() +
                    "Количество строк второй матрицы = " + matrix2.getRowsCount());
        }

        Matrix newMatrix = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < newMatrix.getRowsCount(); ++i) {
            for (int j = 0; j < newMatrix.getColumnsCount(); ++j) {
                double sum = Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j));
                newMatrix.rows[i].setComponent(j, sum);
            }
        }

        return newMatrix;
    }
}