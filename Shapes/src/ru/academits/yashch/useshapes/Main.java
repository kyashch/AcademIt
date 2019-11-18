package ru.academits.yashch.useshapes;

import ru.academits.yashch.shapes.*;

import java.util.Arrays;

public class Main {
    private static void getShapeWithMaxArea(Shape[] shapesArray) {
        AreaComparator comparator = new AreaComparator();
        Arrays.sort(shapesArray, comparator);

        System.out.println(shapesArray[shapesArray.length - 1].toString());
        System.out.println("Площадь фигуры = " + shapesArray[shapesArray.length - 1].getArea());
    }

    private static void getShapeWithSecondLargestPerimeter(Shape[] shapesArray) {
        PerimeterComparator comparator = new PerimeterComparator();
        Arrays.sort(shapesArray, comparator);

        System.out.println(shapesArray[shapesArray.length - 2].toString());
        System.out.println("Периметр фигуры = " + shapesArray[shapesArray.length - 2].getPerimeter());
    }

    public static void main(String[] args) {
        Shape[] shapesArray = {
                new Square(6),
                new Circle(10),
                new Rectangle(3, 9),
                new Square(40),
                new Triangle(1, 1, 5, 10, 10, 1),
                new Rectangle(40, 60)};

        getShapeWithSecondLargestPerimeter(shapesArray);
        getShapeWithMaxArea(shapesArray);

        Shape rectangle1 = new Rectangle(6, 3);

        System.out.println(rectangle1.getHeight());
        System.out.println(rectangle1.getWidth());
        System.out.println(rectangle1.getPerimeter());

        for (Shape e : shapesArray) {
            System.out.println(e);
        }

        Shape square1 = new Square(10);
        Square square2 = new Square(10);

        Shape triangle1 = new Triangle(1, 1, 5, 1, 5, 6);

        System.out.println(square1.hashCode());
        System.out.println(square2.hashCode());

        System.out.println(triangle1);
        System.out.println(triangle1.getPerimeter());
        System.out.println(triangle1.getArea());

        System.out.println(shapesArray[3]);
    }
}
