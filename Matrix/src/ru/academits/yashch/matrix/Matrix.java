package ru.academits.yashch.matrix;

import ru.academits.yashch.vector.Vector;

import java.util.Arrays;

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
        int max = 0;

        for (double[] e : array) {
            if (e.length > max) {
                max = e.length;
            }
        }

        rows = new Vector[array.length];

        for (int i = 0; i < getRowsCount(); ++i) {
            rows[i] = new Vector(max, array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        int max = 0;

        for (Vector e : vectorsArray) {
            if (e.getSize() > max) {
                max = e.getSize();
            }
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

    private void checkIndex(int index) {
        if (index < 0 || index >= getRowsCount()) {
            throw new ArrayIndexOutOfBoundsException("Индекс строки " + index + " выходит за допустимые границы : " + 0 + "-" + (getRowsCount() - 1));
        }
    }

    public Vector getRow(int index) {
        checkIndex(index);

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        checkIndex(index);

        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Длина вектора - " + vector.getSize() + " должна быть равна длине строки - " + getColumnsCount());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new ArrayIndexOutOfBoundsException("Индекс столбца " + index + " выходит за допустимые границы : " + 0 + "-" + (getColumnsCount() - 1));
        }

        double[] newVectorComponents = new double[getRowsCount()];

        for (int i = 0; i < getRowsCount(); ++i) {
            newVectorComponents[i] = rows[i].getComponent(index);
        }

        return new Vector(newVectorComponents);
    }

    public void transpose() {
        if (getRowsCount() == 0) {
            return;
        }

        int oldColumnsCount = getColumnsCount();
        int oldRowsCount = getRowsCount();
        int newColumnsCount = getRowsCount();
        int newRowsCount = getColumnsCount();

        if (getColumnsCount() > getRowsCount()) {
            int a = oldColumnsCount - oldRowsCount;
            rows = Arrays.copyOf(rows, oldRowsCount + a);

            for (int i = oldRowsCount; i < getRowsCount(); ++i) {
                rows[i] = new Vector(oldRowsCount);
            }

        } else if (getColumnsCount() < getRowsCount()) {
            for (int i = 0; i < newRowsCount; ++i) {
                Vector tempVector = new Vector(rows[i]);
                rows[i] = new Vector(newColumnsCount);
                for (int j = 0; j < tempVector.getSize(); ++j) {
                    rows[i].setComponent(j, tempVector.getComponent(j));
                }
            }
        }

        int smallSide = Math.min(oldRowsCount, oldColumnsCount);
        int bigSide = Math.max(oldRowsCount, oldColumnsCount);

        for (int i = 0; i < smallSide; ++i) {
            for (int j = i; j < bigSide; ++j) {
                double temp = rows[i].getComponent(j);
                rows[i].setComponent(j, getColumn(i).getComponent(j));
                rows[j].setComponent(i, temp);
            }
        }

        if (getRowsCount() != newRowsCount) {
            rows = Arrays.copyOf(rows, newRowsCount);
            return;
        }

        if (getColumnsCount() != newColumnsCount) {
            for (int i = 0; i < oldRowsCount; ++i) {
                Vector tempVector = new Vector(rows[i]);
                rows[i] = new Vector(newColumnsCount);
                for (int j = 0; j < rows[i].getSize(); ++j) {
                    rows[i].setComponent(j, tempVector.getComponent(j));
                }
            }
        }
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

    private double getPartDeterminant(Matrix matrix, int columnNumber) {
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

    private double getSecondOrderDeterminant(Matrix matrix) {
        return matrix.rows[0].getComponent(0) * matrix.rows[1].getComponent(1) - matrix.rows[1].getComponent(0) * matrix.rows[0].getComponent(1);
    }

    @Override
    public String toString() {
        if (getRowsCount() == 0) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Vector e : rows) {
            sb.append(e.toString()).append(",");
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
            double sum = 0;

            for (int j = 0; j < getColumnsCount(); ++j) {
                sum += rows[i].getComponent(j) * vector.getComponent(j);
            }

            resultingVector.setComponent(i, sum);
        }

        return resultingVector;
    }

    public void add(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() && getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Матрицы должны быть одиноковой размерности");
        }

        for (int i = 0; i < getRowsCount(); ++i) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() && getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Матрицы должны быть одиноковой размерности");
        }

        for (int i = 0; i < getRowsCount(); ++i) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        Matrix newMatrix = new Matrix(matrix1);

        newMatrix.add(matrix2);

        return newMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix newMatrix = new Matrix(matrix1);

        newMatrix.subtract(matrix2);

        return newMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Число столбцов первой матрицы должно быть равно числу строк второй.");
        }

        Matrix newMatrix = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < newMatrix.getRowsCount(); ++i) {
            for (int j = 0; j < newMatrix.getColumnsCount(); ++j) {
                double sum = Vector.getScalarProduct(matrix1.getRow(i), matrix2.getColumn(j));
                newMatrix.rows[i].setComponent(j, sum);
            }
        }

        return newMatrix;
    }
}