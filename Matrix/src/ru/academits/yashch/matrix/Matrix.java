package ru.academits.yashch.matrix;

import ru.academits.yashch.vector.Vector;

public class Matrix {
    private Vector[] lines;

    public Matrix(int n, int m) {
        lines = new Vector[n];

        for (int i = 0; i < getHeight(); ++i) {
            lines[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        lines = new Vector[matrix.getHeight()];

        for (int i = 0; i < getHeight(); ++i) {
            lines[i] = new Vector(matrix.lines[i]);
        }
    }

    public Matrix(double[][] array) {
        int max = 0;

        for (double[] e : array) {
            if (e.length > max) {
                max = e.length;
            }
        }

        lines = new Vector[array.length];

        for (int i = 0; i < getHeight(); ++i) {
            lines[i] = new Vector(max, array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        int max = 0;

        for (Vector e : vectorsArray) {
            if (e.getSize() > max) {
                max = e.getSize();
            }
        }

        lines = new Vector[vectorsArray.length];

        for (int i = 0; i < getHeight(); ++i) {
            lines[i] = new Vector(max);

            for (int j = 0; j < vectorsArray[i].getSize(); ++j) {
                lines[i].setComponent(j, vectorsArray[i].getComponent(j));
            }
        }
    }

    public int getHeight() {
        return lines.length;
    }

    public int getWidth() {
        return lines[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс не может быть меньше нуля.");
        }

        if (index >= getHeight()) {
            throw new ArrayIndexOutOfBoundsException("Строки с таким индексом нет.");
        }

        return new Vector(lines[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс не может быть меньше нуля.");
        }

        if (index >= getHeight()) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит за пределы матрицы");
        }

        if (getWidth() != vector.getSize()) {
            throw new IllegalArgumentException("Длина вектора не равна длине строки.");
        }

        lines[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс не может быть меньше нуля.");
        }

        if (index >= getWidth()) {
            throw new ArrayIndexOutOfBoundsException("Столбца с таким индексом нет");
        }

        double[] newVectorComponents = new double[getHeight()];

        for (int i = 0; i < getHeight(); ++i) {
            newVectorComponents[i] = lines[i].getComponent(index);
        }

        return new Vector(newVectorComponents);
    }

    public void transpose() {
        Matrix transposedMatrix = new Matrix(getWidth(), getHeight());

        for (int i = 0; i < getWidth(); ++i) {
            transposedMatrix.setRow(i, getColumn(i));
        }

        this.lines = transposedMatrix.lines;
    }

    public void multiply(double number) {
        for (Vector e : lines) {
            e.multiplyByScalar(number);
        }
    }


    public double getDeterminant() {
        if (getHeight() != getWidth()) {
            throw new IllegalArgumentException("Определитель можно вычислить только для квадратной матрицы");
        }

        if (getWidth() == 1) {
            return lines[0].getComponent(0);
        }

        if (getWidth() == 2) {
            return getSecondOrderDeterminant(this);
        }

        double sum = 0;

        for (int i = 0; i < getWidth(); ++i) {
            sum += lines[0].getComponent(i) * Math.pow(-1, 1 + i + 1) * getPartDeterminant(this, i);
        }

        return sum;
    }

    private double getPartDeterminant(Matrix matrix, int columnNumber) {
        Matrix newMatrix = new Matrix(matrix.getHeight() - 1, matrix.getWidth() - 1);

        for (int i = 0, j = 1; i < newMatrix.getHeight(); ++i, ++j) {
            for (int k = 0, m = 0; k < newMatrix.getWidth(); ++k, ++m) {
                if (m == columnNumber) {
                    ++m;
                }

                newMatrix.lines[i].setComponent(k, matrix.lines[j].getComponent(m));
            }
        }

        if (newMatrix.getHeight() > 2) {
            return newMatrix.getDeterminant();
        }

        return getSecondOrderDeterminant(newMatrix);
    }

    private double getSecondOrderDeterminant(Matrix matrix) {
        return matrix.lines[0].getComponent(0) * matrix.lines[1].getComponent(1) - matrix.lines[1].getComponent(0) * matrix.lines[0].getComponent(1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Vector e : lines) {
            sb.append(e.toString()).append(",");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");

        return sb.toString();
    }

    public Vector multiplyByVector(Vector vector) {
        if (getWidth() != vector.getSize()) {
            throw new IllegalArgumentException("Число стобцов в матрице не совпадает с числом строк в векторе-столбце.");
        }

        Matrix newMatrix = new Matrix(this);

        for (int i = 0; i < getHeight(); ++i) {
            for (int j = 0; j < getWidth(); ++j) {
                newMatrix.lines[i].setComponent(j, newMatrix.lines[i].getComponent(j) * vector.getComponent(j));
            }
        }

        Vector newVectorColumn = new Vector(getWidth());

        for (int i = 0; i < getHeight(); ++i) {
            double sum = 0;

            for (int j = 0; j < getWidth(); ++j) {
                sum += newMatrix.lines[i].getComponent(j);
            }

            newVectorColumn.setComponent(i, sum);
        }

        return newVectorColumn;
    }

    public void add(Matrix matrix) {
        if (getHeight() != matrix.getHeight() && getWidth() != matrix.getWidth()) {
            throw new IllegalArgumentException("Матрицы должны быть одиноковой размерности");
        }

        for (int i = 0; i < getHeight(); ++i) {
            lines[i].add(matrix.lines[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (getHeight() != matrix.getHeight() && getWidth() != matrix.getWidth()) {
            throw new IllegalArgumentException("Матрицы должны быть одиноковой размерности");
        }

        for (int i = 0; i < getHeight(); ++i) {
            lines[i].subtract(matrix.lines[i]);
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
        if (matrix1.getWidth() != matrix2.getHeight()) {
            throw new IllegalArgumentException("Число столбцов первой матрицы должно быть равно числу строк второй.");
        }

        Matrix newMatrix = new Matrix(matrix1.getHeight(), matrix2.getWidth());

        for (int i = 0; i < newMatrix.getHeight(); ++i) {
            for (int j = 0; j < newMatrix.getWidth(); ++j) {
                double sum = Vector.getScalarProduct(matrix1.getRow(i), matrix2.getColumn(j));
                newMatrix.lines[i].setComponent(j, sum);
            }
        }

        return newMatrix;
    }
}
