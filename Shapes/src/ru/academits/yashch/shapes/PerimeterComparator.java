package ru.academits.yashch.shapes;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shapes> {
    @Override
    public int compare(Shapes o1, Shapes o2) {
        return Double.compare(o1.getPerimeter(), o2.getPerimeter());
    }
}
