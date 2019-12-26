package ru.academits.yashch.usevector;

import ru.academits.yashch.vector.Vector;

import static ru.academits.yashch.vector.Vector.*;

public class Main {
    public static void main(String[] args) {
        Vector v1 = new Vector(2);

        Vector v2 = new Vector(v1);
        System.out.println(v2);

        double[] array = {3, 5};
        Vector v3 = new Vector(array);
        System.out.println(v3);

        Vector v4 = new Vector(3, array);
        System.out.println(v4);

        System.out.println(v4.getSize());

        System.out.println("Операции с векторами :");

        double[] array1 = {6};
        double[] array2 = {4, 5, 7};

        Vector vc1 = new Vector(array1);
        Vector vc2 = new Vector(array2);

        vc1.add(vc2);
        System.out.println(vc1);

        double[] ar1 = {3};
        double[] ar2 = {4, 5};

        Vector vec1 = new Vector(ar1);
        Vector vec2 = new Vector(ar2);

        vec1.subtract(vec2);
        System.out.println(vec1);

        double[] arr = {3, 6, 2};
        Vector vector5 = new Vector(arr);

        vector5.multiplyByScalar(4);
        System.out.println(vector5);

        vector5.reverse();
        System.out.println(vector5);

        System.out.println(vector5.getLength());

        double[] a = {1, 2, 9};
        double[] b = {3};

        Vector vector6 = new Vector(a);
        Vector vector9 = new Vector(b);

        vector6.setComponent(0, 5);
        System.out.println(vector6.getComponent(0));

        Vector vector7 = new Vector(a);
        Vector vector8 = new Vector(a);

        System.out.println(vector7.equals(vector8));
        System.out.println(vector7.hashCode());
        System.out.println(vector8.hashCode());

        System.out.println("Статические методы :");

        Vector newVector1 = getVectorAddition(vector7, vector9);
        System.out.println(newVector1);

        Vector newVector2 = getVectorSubtraction(vector7, vector9);
        System.out.println(newVector2);

        System.out.println(getScalarProduct(vector7, vector9));

        System.out.println(vector7);

        double[] vc = {1, 6, 4};
        Vector vv = new Vector(4, vc);
        System.out.println(vv);

        Vector vvv = new Vector(4, a);
        System.out.println(vvv);
    }
}
