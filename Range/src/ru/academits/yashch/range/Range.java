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
        if ((this.to < range.from) || (this.from > range.to)) {
            return null;
        }

        double newFrom = Math.max(this.from, range.from);
        double newTo = Math.min(this.to, range.to);

        return new Range(newFrom, newTo);
    }

    public Range[] getAssociation(Range range) {
        if ((this.to < range.from) || (this.from > range.to)) {
            return new Range[]{new Range(this.from, this.to), new Range(range.from, range.to)};
        }

        double newFrom = Math.min(this.from, range.from);
        double newTo = Math.max(this.to, range.to);

        return new Range[]{new Range(newFrom, newTo)};
    }

    public Range[] getDifference(Range range) {
        double epsilon = 1.0e-10;

        if ((this.to < range.from) || (this.from > range.to)) {
            return new Range[]{new Range(this.from, this.to)};
        }

        if ((this.from < range.from) && (this.to > range.to)) {
            Range firstRange = new Range(this.from, range.from - epsilon);
            Range secondRange = new Range(range.to + epsilon, this.to);

            return new Range[]{firstRange, secondRange};
        }

        if ((this.from >= range.from) && (this.to <= range.to)) {
            return new Range[]{};
        }

        double newFrom;
        double newTo;

        if (this.from < range.from) {
            newFrom = this.from;
            newTo = range.from - epsilon;
        } else {
            newFrom = range.to + epsilon;
            newTo = this.to;
        }

        return new Range[]{new Range(newFrom, newTo)};
    }
}
