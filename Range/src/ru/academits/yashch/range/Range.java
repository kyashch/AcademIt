package ru.academits.yashch.range;

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

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        double epsilon = 1.0e-10;
        return (number >= from - epsilon) && (number <= to + epsilon);
    }

    public Range getIntersection(Range range) {
        if ((to <= range.from) || (from >= range.to)) {
            return null;
        }

        double newFrom = Math.max(from, range.from);
        double newTo = Math.min(to, range.to);

        return new Range(newFrom, newTo);
    }

    public Range[] getAssociation(Range range) {
        if ((to < range.from) || (from > range.to)) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        double newFrom = Math.min(from, range.from);
        double newTo = Math.max(to, range.to);

        return new Range[]{new Range(newFrom, newTo)};
    }

    public Range[] getDifference(Range range) {
        if ((to < range.from) || (from > range.to)) {
            return new Range[]{new Range(from, to)};
        }

        if ((from < range.from) && (to > range.to)) {
            Range firstRange = new Range(from, range.from);
            Range secondRange = new Range(range.to, to);

            return new Range[]{firstRange, secondRange};
        }

        if ((from >= range.from) && (to <= range.to)) {
            return new Range[]{};
        }

        if (from < range.from) {
            return new Range[]{new Range(from, range.from)};
        } else {
            return new Range[]{new Range(range.to, to)};
        }
    }
}
