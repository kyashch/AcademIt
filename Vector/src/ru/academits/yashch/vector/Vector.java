package ru.academits.yashch.vector;

import java.util.Arrays;

public class Vector {
    private double[] vectorComponents;

    public Vector(int n) {
        if (n <= 0 || n > 3) {
            throw new IllegalArgumentException("Неверная размерность вектора.");
        }

        this.vectorComponents = new double[n];
    }

    public Vector(Vector vector) {
        vectorComponents = vector.vectorComponents;
    }

    public Vector(double[] vectorComponents) {
        if (vectorComponents.length > 3) {
            throw new IllegalArgumentException("Неверная размерность массива");
        }
        this.vectorComponents = vectorComponents;
    }

    public Vector(int n, double[] vectorComponents) {
        if (n <= 0 || n > 3) {
            throw new IllegalArgumentException("Неверная размерность вектора.");
        }

        this.vectorComponents = new double[n];

        for (int i = 0; i <= n - 1; ++i) {
            if (vectorComponents.length - 1 < i) {
                return;
            }
            this.vectorComponents[i] = vectorComponents[i];
        }
    }

    public int getSize() {
        return vectorComponents.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(vectorComponents);
    }

    public void vectorAddition(Vector vector) {
        if (getSize() < vector.getSize()) {
            vectorComponents = Arrays.copyOf(vectorComponents, vector.getSize());
        }

        for (int i = 0; i <= vector.getSize() - 1; ++i) {
            vectorComponents[i] += vector.vectorComponents[i];
        }
    }

    public void vectorSubtraction(Vector vector) {
        if (getSize() < vector.getSize()) {
            vectorComponents = Arrays.copyOf(vectorComponents, vector.getSize());
        }

        for (int i = 0; i <= vector.getSize() - 1; ++i) {
            vectorComponents[i] -= vector.vectorComponents[i];
        }
    }

    public void multiplyingVectorByScalar(double number) {
        for (int i = 0; i <= getSize() - 1; ++i) {
            vectorComponents[i] *= number;
        }
    }

    public void vectorReversal() {
        for (int i = 0; i <= getSize() - 1; ++i) {
            vectorComponents[i] *= -1;
        }
    }

    public double getLength() {
        double length = 0;

        for (double e : vectorComponents) {
            length += Math.pow(e, 2);
        }

        return Math.sqrt(length);
    }

    public double getComponent(int numberComponent) {
        return vectorComponents[numberComponent];
    }

    public void setComponent(int numberComponent, double component) {
        vectorComponents[numberComponent] = component;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return getSize() == vector.getSize() && Arrays.equals(vectorComponents, vector.vectorComponents);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(vectorComponents);

        return hash;
    }

    public static Vector vectorAddition(Vector vector1, Vector vector2) {
        Vector readyVector = new Vector(Math.max(vector1.getSize(), vector2.getSize()), vector1.vectorComponents);

        for (int i = 0; i <= vector2.getSize() - 1; ++i) {
            readyVector.vectorComponents[i] += vector2.vectorComponents[i];
        }

        return readyVector;
    }

    public static Vector vectorSubtraction(Vector vector1, Vector vector2) {
        Vector readyVector = new Vector(Math.max(vector1.getSize(), vector2.getSize()), vector1.vectorComponents);

        for (int i = 0; i <= vector2.getSize() - 1; ++i) {
            readyVector.vectorComponents[i] -= vector2.vectorComponents[i];
        }

        return readyVector;
    }

    public static double getScalarProductOfVectors(Vector vector1, Vector vector2) {
        if (vector1.getSize() < vector2.getSize()) {
            vector1.vectorComponents = Arrays.copyOf(vector1.vectorComponents, vector2.getSize());
        }

        double sum = 0;

        for (int i = 0; i <= vector2.getSize() - 1; ++i) {
            sum += vector1.vectorComponents[i] * vector2.vectorComponents[i];
        }

        return sum;
    }
}
