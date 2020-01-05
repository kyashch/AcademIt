package ru.academits.yashch.matrix;

import ru.academits.yashch.vector.Vector;

public class Matrix {
    private double[][] matrixArray;

    public Matrix(int n, int m) {
        matrixArray = new double[n][m];
    }

    public Matrix(Matrix matrix) {
        matrixArray = new double[matrix.matrixArray.length][matrix.matrixArray[0].length];

        for (int i = 0; i < matrix.matrixArray.length; ++i) {
            System.arraycopy(matrix.matrixArray[i], 0, matrixArray[i], 0, matrixArray[i].length);
        }
    }

    public Matrix(double[][] array) {
        int max = 0;

        for (double[] e : array) {
            if (e.length > max) {
                max = e.length;
            }
        }

        matrixArray = new double[array.length][max];

        for (int i = 0; i < array.length; ++i) {
            System.arraycopy(array[i], 0, matrixArray[i], 0, array[i].length);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        int max = 0;

        for (Vector e : vectorsArray) {
            if (e.getSize() > max) {
                max = e.getSize();
            }
        }

        matrixArray = new double[vectorsArray.length][max];

        for (int i = 0; i < vectorsArray.length; ++i) {
            for (int j = 0; j < vectorsArray[i].getSize(); ++j) {
                matrixArray[i][j] = vectorsArray[i].getComponent(j);
            }
        }
    }

    public String getSize() {
        return "Высота матрицы = " + matrixArray.length + System.lineSeparator() + "Ширина матрицы = " + matrixArray[0].length;
    }

    public Vector getStringVector(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть меньше нуля.");
        }

        if (index > matrixArray.length) {
            throw new IllegalArgumentException("Строки с таким индексом нет.");
        }

        return new Vector(matrixArray[index]);
    }

    public void setStringVector(int index, Vector vector) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть меньше нуля.");
        }

        if (index > matrixArray.length) {
            throw new IllegalArgumentException("Столбца с таким индексом нет");
        }

        if (matrixArray[index].length != vector.getSize()) {
            throw new IllegalArgumentException("Длина вектора не равна длине строки.");
        }

        for (int i = 0; i < vector.getSize(); ++i) {
            matrixArray[index][i] = vector.getComponent(i);
        }
    }

    public Vector getColumnVector(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть меньше нуля.");
        }

        if (index > matrixArray[0].length) {
            throw new IllegalArgumentException("Столбца с таким индексом нет");
        }

        double[] newVectorComponents = new double[matrixArray.length];

        for (int i = 0; i < matrixArray.length; ++i) {
            newVectorComponents[i] = matrixArray[i][index];
        }

        return new Vector(newVectorComponents);
    }

    public void transpose() {
        double[][] transposedMatrixArray = new double[matrixArray[0].length][matrixArray.length];

        for (int i = 0; i < transposedMatrixArray.length; ++i) {
            for (int j = 0; j < transposedMatrixArray[i].length; ++j) {
                transposedMatrixArray[i][j] = matrixArray[j][i];
            }
        }

        matrixArray = transposedMatrixArray;
    }

    public void multiplyByScalar(double number) {
        for (int i = 0; i < matrixArray.length; ++i) {
            for (int j = 0; j < matrixArray[i].length; ++j) {
                matrixArray[i][j] *= number;
            }
        }
    }


    public double getMatrixDeterminant() {
        if (matrixArray.length != matrixArray[0].length) {
            throw new IllegalArgumentException("Определитель можно вычислить только для квадратной матрицы");
        }

        double sum = 0;

        for (int i = 0; i < matrixArray.length; ++i) {
            sum += matrixArray[0][i] * Math.pow(-1, 1 + i + 1) * getMatrixPartDeterminant(this, i);
        }

        return sum;
    }

    private double getMatrixPartDeterminant(Matrix matrix, int columnNumber) {
        Matrix newMatrix = new Matrix(matrix.matrixArray.length - 1, matrix.matrixArray.length - 1);

        for (int i = 0, j = 1; i < newMatrix.matrixArray.length; ++i, ++j) {
            for (int k = 0, m = 0; k < newMatrix.matrixArray[i].length; ++k, ++m) {
                if (m == columnNumber) {
                    ++m;
                }

                newMatrix.matrixArray[i][k] = matrix.matrixArray[j][m];
            }
        }

        if (newMatrix.matrixArray.length > 2) {
            return newMatrix.getMatrixDeterminant();
        }

        return getSecondOrderMatrixDeterminant(newMatrix);
    }

    private double getSecondOrderMatrixDeterminant(Matrix matrix) {
        return matrix.matrixArray[0][0] * matrix.matrixArray[1][1] - matrix.matrixArray[1][0] * matrix.matrixArray[0][1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (double[] e : matrixArray) {
            sb.append("{");

            for (double a : e) {
                sb.append(a).append(", ");
            }

            sb.delete(sb.length() - 2, sb.length());
            sb.append("}").append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");

        return sb.toString();
    }

    public void multiplyByVector(Vector vector) {
        if (matrixArray[0].length != vector.getSize()) {
            throw new IllegalArgumentException("Число стобцов в матрице не совпадает с числом строк в векторе-столбце.");
        }

        for (int i = 0; i < matrixArray[0].length; ++i) {
            for (int j = 0; j < matrixArray.length; ++j) {
                matrixArray[j][i] *= vector.getComponent(i);
            }
        }

        double[][] newVectorColumn = new double[matrixArray.length][1];

        for (int i = 0; i < matrixArray.length; ++i) {
            double sum = 0;

            for (int j = 0; j < matrixArray[0].length; ++j) {
                sum += matrixArray[i][j];
            }

            newVectorColumn[i][0] = sum;
        }

        matrixArray = newVectorColumn;
    }

    public void add(Matrix matrix) {
        if (matrixArray.length != matrix.matrixArray.length && matrixArray[0].length != matrix.matrixArray[0].length) {
            throw new IllegalArgumentException("Матрицы должны быть одиноковой размерности");
        }

        for (int i = 0; i < matrixArray.length; ++i) {
            for (int j = 0; j < matrixArray[i].length; ++j) {
                matrixArray[i][j] += matrix.matrixArray[i][j];
            }
        }
    }

    public void subtract(Matrix matrix) {
        if (matrixArray.length != matrix.matrixArray.length && matrixArray[0].length != matrix.matrixArray[0].length) {
            throw new IllegalArgumentException("Матрицы должны быть одиноковой размерности");
        }

        for (int i = 0; i < matrixArray.length; ++i) {
            for (int j = 0; j < matrixArray[i].length; ++j) {
                matrixArray[i][j] -= matrix.matrixArray[i][j];
            }
        }
    }

    public static Matrix getMatrixAddition(Matrix matrix1, Matrix matrix2) {
        Matrix newMatrix = new Matrix(matrix1);

        newMatrix.add(matrix2);

        return newMatrix;
    }

    public static Matrix getMatrixSubtraction(Matrix matrix1, Matrix matrix2) {
        Matrix newMatrix = new Matrix(matrix1);

        newMatrix.subtract(matrix2);

        return newMatrix;
    }

    public static Matrix getMatrixProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrixArray[0].length != matrix2.matrixArray.length) {
            throw new IllegalArgumentException("Число столбцов первой матрицы должно быть равно числу строк второй.");
        }

        Matrix newMatrix = new Matrix(matrix1.matrixArray.length, matrix2.matrixArray[0].length);

        for (int i = 0; i < newMatrix.matrixArray.length; ++i) {
            for (int j = 0; j < newMatrix.matrixArray[i].length; ++j) {
                for (int k = 0; k < matrix1.matrixArray[0].length; ++k) {
                    newMatrix.matrixArray[i][j] += matrix1.matrixArray[i][k] * matrix2.matrixArray[k][j];
                }
            }
        }

        return newMatrix;
    }
}
