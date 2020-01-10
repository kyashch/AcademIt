package ru.academits.yashch.main;

import ru.academits.yashch.list.ListItem;
import ru.academits.yashch.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list0 = new SinglyLinkedList<>();
        ListItem<Integer> a = new ListItem<>(3);
        ListItem<Integer> b = new ListItem<>(4);
        ListItem<Integer> c = new ListItem<>(5);

        c.setNext(a.getNext());
        a.setNext(b);
        a.setData(5);

        System.out.println(a.getData());

        list0.insertItemToStart(new ListItem<>(2, a));

        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.insertItemToStart(new ListItem<>(-1));

        for (int i = 0; i < 16; ++i) {
            list.insertItemByIndex(new ListItem<>(i), i);
        }

        list.deleteItemByData(10);
        System.out.println(list.deleteFirstElement());
        System.out.println(list.setDataByIndex(12, 100));
        System.out.println(list.deleteItemByIndex(12));

        for (int i = 0; i < list.getSize(); ++i) {
            System.out.println(list.getDataByIndex(i));
        }

        System.out.println(list.getFirstElementData());

        System.out.println("----------------------------------");

        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();

        list1.insertItemToStart(new ListItem<>(100));

        for (int i = 0; i < 40; ++i) {
            list1.insertItemByIndex(new ListItem<>(i), i);
        }

        for (int i = 0; i < list1.getSize(); ++i) {
            System.out.println(list1.getDataByIndex(i));
        }

        if (list1.deleteItemByData(100)) {
            System.out.println("true");
        }

        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        list2 = list2.copyList(list1);

        list1.expandList();
        list1.expandList();

        for (int i = 0; i < list1.getSize(); ++i) {
            System.out.println(list1.getDataByIndex(i));
        }

        list2.expandList();

        for (int i = 0; i < list2.getSize(); ++i) {
            System.out.println(list2.getDataByIndex(i));
        }
    }
}
