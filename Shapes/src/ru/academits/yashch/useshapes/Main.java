package ru.academits.yashch.useshapes;

import ru.academits.yashch.shapes.*;

import java.util.Arrays;

public class Main {
    private static void shapeMaxAreaSearch(Shapes[] shapesArray) {
        AreaComparator comp = new AreaComparator();
        Arrays.sort(shapesArray, comp);

        System.out.println(shapesArray[shapesArray.length - 1].toString());
        System.out.println("Площадь фигуры = " + shapesArray[shapesArray.length - 1].getArea());
    }

    private static void shapeSecondLargestPerimeterSearch(Shapes[] shapesArray) {
        PerimeterComparator comp = new PerimeterComparator();
        Arrays.sort(shapesArray, comp);

        System.out.println(shapesArray[shapesArray.length - 2].toString());
        System.out.println("Периметр фигуры = " + shapesArray[shapesArray.length - 2].getPerimeter());
    }

    public static void main(String[] args) {
        Shapes[] shapesArray = {
                new Square(6),
                new Circle(10),
                new Rectangle(3, 9),
                new Square(40),
                new Triangle(1, 1, 5, 10, 10, 1),
                new Rectangle(40, 60)};

        shapeSecondLargestPerimeterSearch(shapesArray);
        shapeMaxAreaSearch(shapesArray);

        Shapes rectangle1 = new Rectangle(6, 3);

        System.out.println(rectangle1.getHeight());
        System.out.println(rectangle1.getWidth());
        System.out.println(rectangle1.getPerimeter());

        for (Shapes e : shapesArray) {
            System.out.println(e.getPerimeter());
        }

        Shapes square1 = new Square(10);
        Square square2 = new Square(10);

        System.out.println(square1.hashCode());
        System.out.println(square2.hashCode());
    }
}
