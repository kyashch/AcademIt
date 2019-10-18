public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double getIntervalLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        double epsilon = 1.0e-10;
        return (number >= from - epsilon) && (number <= to + epsilon);
    }

    public static Range getRangeIntersection(Range[] array) {
        if (array.length != 2) {
            System.out.print("Недопустимое количество объектов");
            return null;
        }

        double epsilon = 1.0e-10;

        if ((array[0].getTo() < array[1].getFrom() - epsilon) || (array[0].getFrom() > array[1].getTo() + epsilon)) {
            return null;
        }

        double newFrom = (array[0].getFrom() > array[1].getFrom()) ? array[0].getFrom() : array[1].getFrom();
        double newTo = (array[0].getTo() < array[1].getTo()) ? array[0].getTo() : array[1].getTo();

        return new Range(newFrom, newTo);
    }

    public static Range[] getRangeAssociation(Range[] array) {
        if (array.length != 2) {
            System.out.print("Недопустимое количество объектов");
            return null;
        }

        double epsilon = 1.0e-10;

        if ((array[0].getTo() < array[1].getFrom() - epsilon) || (array[0].getFrom() > array[1].getTo() + epsilon)) {
            return array;
        }

        double newFrom = (array[0].getFrom() < array[1].getFrom()) ? array[0].getFrom() : array[1].getFrom();
        double newTo = (array[0].getTo() > array[1].getTo()) ? array[0].getTo() : array[1].getTo();

        return new Range[]{new Range(newFrom, newTo)};
    }

    public static Range[] getRangeDifference(Range[] array) {
        if (array.length != 2) {
            System.out.print("Недопустимое количество объектов");
            return null;
        }

        double epsilon = 1.0e-10;

        if ((array[0].getTo() < array[1].getFrom() - epsilon) || (array[0].getFrom() > array[1].getTo() + epsilon)) {
            return new Range[]{array[0]};
        }

        if ((array[0].getFrom() < array[1].getFrom() - epsilon) && (array[0].getTo() > array[1].getTo() + epsilon)) {
            Range firstRange = new Range(array[0].getFrom(), array[1].getFrom() - epsilon);
            Range secondRange = new Range(array[1].getTo() + epsilon, array[0].getTo());

            return new Range[]{firstRange, secondRange};
        }

        if ((array[0].getFrom() >= array[1].getFrom() - epsilon) && (array[0].getTo() <= array[1].getTo() + epsilon)) {
            return null;
        }

        double newFrom;
        double newTo;

        if (array[0].getFrom() < array[1].getFrom() - epsilon) {
            newFrom = array[0].getFrom();
            newTo = array[1].getFrom() - epsilon;
        } else {
            newFrom = array[1].getTo() + epsilon;
            newTo = array[0].getTo();
        }

        return new Range[]{new Range(newFrom, newTo)};
    }

    public static void main(String[] args) {
        Range range1 = new Range(8, 9);
        Range range2 = new Range(6, 10);

        System.out.println(range1.getIntervalLength());
        System.out.println(range2.isInside(5));

       /*Range[] array1 = {range1, range2};

        Range[] newRange = getRangeDifference(array1);

        if (newRange == null) {
            System.out.print("NULL");
        } else {
            System.out.println("Начало - " + newRange[0].getFrom() + "Конец - " + newRange[0].getTo());
        }

            System.out.println("Начало - " + newRange[1].getFrom() + "Конец - " + newRange[1].getTo());
        }*/
    }
}
