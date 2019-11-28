package ru.academits.yashch.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора (n) должна быть больше нуля.");
        }

        components = new double[n];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.getSize());
    }

    public Vector(double[] components) {
        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int n, double[] components) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора (n) должна быть больше нуля.");
        }

        this.components = new double[n];

        System.arraycopy(components, 0, this.components, 0, Math.min(n, components.length));
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        String vectorString = Arrays.toString(components);
        vectorString = vectorString.replace("[", "{").replace("]", "}");

        return vectorString;
    }

    public void add(Vector vector) {
        if (getSize() < vector.getSize()) {
            components = Arrays.copyOf(components, vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); ++i) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (getSize() < vector.getSize()) {
            components = Arrays.copyOf(components, vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); ++i) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double number) {
        for (int i = 0; i < getSize(); ++i) {
            components[i] *= number;
        }
    }

    public void reverse() {
        this.multiplyByScalar(-1);
    }

    public double getLength() {
        double length = 0;

        for (double e : components) {
            length += Math.pow(e, 2);
        }

        return Math.sqrt(length);
    }

    public double getComponent(int componentIndex) {
        return components[componentIndex];
    }

    public void setComponent(int componentIndex, double component) {
        components[componentIndex] = component;
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

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    public static Vector getVectorAddition(Vector vector1, Vector vector2) {
        Vector newVector1 = new Vector(vector1);
        Vector newVector2 = new Vector(vector2);

        newVector1.add(newVector2);

        return newVector1;
    }

    public static Vector getVectorSubtraction(Vector vector1, Vector vector2) {
        Vector newVector1 = new Vector(vector1);
        Vector newVector2 = new Vector(vector2);

        newVector1.subtract(newVector2);

        return newVector1;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double sum = 0;

        for (int i = 0; i < Math.min(vector1.getSize(), vector2.getSize()); ++i) {
            sum += vector1.components[i] * vector2.components[i];
        }

        return sum;
    }
}
