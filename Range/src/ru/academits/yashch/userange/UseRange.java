package ru.academits.yashch.userange;

import ru.academits.yashch.range.Range;

public class UseRange {
    public static void main(String[] args) {
        Range range1 = new Range(8, 9);
        Range range2 = new Range(5, 11);
        Range range3 = new Range(8, 15);

        range1.setFrom(5);
        range1.setTo(10);

        System.out.println(range1.getLength());
        System.out.println(range2.isInside(5));
        System.out.println(range1.getFrom());
        System.out.println(range2.getTo());

        System.out.println("Пересечение :");

        Range c = range2.getIntersection(range3);
        if (c == null) {
            System.out.println("NULL");
        } else {
            System.out.println(c.getFrom() + " " + c.getTo());
        }

        System.out.println("Объединение :");

        Range[] arrayRange1 = range2.getAssociation(range3);

        if (arrayRange1.length == 2) {
            System.out.println(arrayRange1[0].getFrom() + " " + arrayRange1[0].getTo());
            System.out.println(arrayRange1[1].getFrom() + " " + arrayRange1[1].getTo());
        } else {
            System.out.println(arrayRange1[0].getFrom() + " " + arrayRange1[0].getTo());
        }

        System.out.println("Вычитание :");

        Range[] arrayRange2 = range2.getDifference(range3);

        if (arrayRange2.length == 2) {
            System.out.println(arrayRange2[0].getFrom() + " " + arrayRange2[0].getTo());
            System.out.println(arrayRange2[1].getFrom() + " " + arrayRange2[1].getTo());
        } else if (arrayRange2.length == 1) {
            System.out.println(arrayRange2[0].getFrom() + " " + arrayRange2[0].getTo());
        } else {
            System.out.println("Пустой массив");
        }
    }
}

