package ru.academits.yashch.shapes;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private static double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    @Override
    public double getWidth() {
        double max = Math.max(Math.max(x1, x2), x3);
        double min = Math.min(Math.min(x1, x2), x3);

        return max - min;
    }

    @Override
    public double getHeight() {
        double max = Math.max(Math.max(y1, y2), y3);
        double min = Math.min(Math.min(y1, y2), y3);

        return max - min;
    }

    @Override
    public double getArea() {
        double side1 = getSideLength(x1, y1, x2, y2);
        double side2 = getSideLength(x1, y1, x3, y3);
        double side3 = getSideLength(x2, y2, x3, y3);

        double triangleSemiPerimeter = (side1 + side2 + side3) / 2;

        return Math.sqrt(triangleSemiPerimeter * (triangleSemiPerimeter - side1) * (triangleSemiPerimeter - side2) * (triangleSemiPerimeter - side3));
    }

    @Override
    public double getPerimeter() {
        double side1 = getSideLength(x1, y1, x2, y2);
        double side2 = getSideLength(x1, y1, x3, y3);
        double side3 = getSideLength(x2, y2, x3, y3);

        return side1 + side2 + side3;
    }

    @Override
    public String toString() {
        return "Треугольник :" + System.lineSeparator() + "Координаты первой точки = " + x1 + ", " + y1 + System.lineSeparator() + "Координаты второй точки = " + x2 + ", " + y2 + System.lineSeparator() + "Координаты третьей точки = " + x3 + ", " + y3;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;

        return x1 == triangle.x1 && x2 == triangle.x2 && x3 == triangle.x3 && y1 == triangle.y1 && y2 == triangle.y2 && y3 == triangle.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }
}
