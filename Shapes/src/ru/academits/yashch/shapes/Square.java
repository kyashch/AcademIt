package ru.academits.yashch.shapes;

public class Square implements Shape {
    private double squareSide;

    public Square(double squareSide) {
        this.squareSide = squareSide;
    }

    @Override
    public double getWidth() {
        return squareSide;
    }

    @Override
    public double getHeight() {
        return squareSide;
    }

    @Override
    public double getArea() {
        return squareSide * squareSide;
    }

    @Override
    public double getPerimeter() {
        return squareSide * 4;
    }

    @Override
    public String toString() {
        return "Квадрат :" + System.lineSeparator() + "Сторона = " + squareSide;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Square square = (Square) o;

        return squareSide == square.squareSide;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(squareSide);

        return hash;
    }
}
