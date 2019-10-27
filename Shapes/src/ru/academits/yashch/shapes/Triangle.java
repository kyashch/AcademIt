package ru.academits.yashch.shapes;

public class Triangle implements Shapes {
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
        double AB = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        double AC = Math.sqrt(Math.pow((x3 - x1), 2) + Math.pow((y3 - y1), 2));
        double BC = Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2));

        double triangleSemiPerimeter = (AB + AC + BC) / 2;

        return Math.sqrt(triangleSemiPerimeter * (triangleSemiPerimeter - AB) * (triangleSemiPerimeter - AC) * (triangleSemiPerimeter - BC));
    }

    @Override
    public double getPerimeter() {
        double AB = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        double AC = Math.sqrt(Math.pow((x3 - x1), 2) + Math.pow((y3 - y1), 2));
        double BC = Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2));

        return AB + AC + BC;
    }

    @Override
    public String toString() {
        return "Треугольник :\nКоординаты первой точки = " + x1 + ", " + y1 + "\nКоординаты второй точки = " + x2 + ", " + y2 + "\nКоординаты третьей точки = " + x3 + ", " + y3;
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
