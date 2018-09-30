package ru.nsu.mmf.g16121.ddt.math;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public static Range getIntersection(Range range1, Range range2) {
        if ((range1.to < range2.from) || (range2.to < range1.from)) {
            return null;
        } else if ((range2.from <= range1.from) && (range1.to <= range2.to)) {
            return range1;
        } else if ((range1.from < range2.from) && (range2.to < range1.to)) {
            return range2;
        } else if ((range2.from > range1.from) && (range2.to > range1.to)) {
            return new Range(range2.from, range1.to);
        } else {
            return new Range(range1.from, range2.to);
        }
    }

    public static Range[] getAssociation(Range range1, Range range2) {
        if (getIntersection(range1, range2) == null) {
            return new Range[]{range1, range2};
        } else {
            if ((range1.from < range2.from) && (range1.to < range2.to)) {
                return new Range[]{new Range(range1.from, range2.to)};
            } else if ((range1.from < range2.from) && (range1.to > range2.to)) {
                return new Range[]{range1};
            } else if ((range2.from < range1.from) && (range2.to < range1.to)) {
                return new Range[]{range2};
            } else {
                return new Range[]{new Range(range2.from, range1.to)};
            }
        }
    }

    public static Range[] getAddition(Range range1, Range range2) {
        if (getIntersection(range1, range2) == null) {
            return new Range[]{range1};
        } else if (range1.from > range2.from && range1.to < range2.to) {
            return null;
        } else if (range2.from > range1.from && range2.to < range1.to) {
            return new Range[]{new Range(range1.from, range2.from), new Range(range2.to, range1.to)};
        } else if (range2.from > range1.from) {
            return new Range[]{new Range(range1.from, range2.from)};
        } else {
            return new Range[]{new Range(range2.to, range1.from)};
        }
    }

    public void print() {
        System.out.print("[" + this.from + "," + this.to + "] ");
    }
}
