package ru.academits.yashch.main;

import ru.academits.yashch.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();

        for(int i = 9; i > 0; --i){
            list1.insertItemToStart(i);
        }

        System.out.println(list1.getSize());
        System.out.println(list1.getFirstElementData());
        System.out.println(list1.getDataByIndex(3));
        System.out.println(list1.setDataByIndex(3, 10));
        System.out.println(list1.getDataByIndex(3));

        System.out.println("---------------------------------------");

        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();

        for(int i = 13; i >= 0; --i){
            list2.insertItemToStart(i);
        }

        for(int i = 0; i < list2.getSize(); ++i){
            System.out.println(list2.getDataByIndex(i));
        }

        System.out.println(list2.deleteItemByIndex(0));
        System.out.println("---------------------------------------");

        for(int i = 0; i < list2.getSize(); ++i){
            System.out.println(list2.getDataByIndex(i));
        }

        list2.insertItemByIndex(100, 0);

        for(int i = 0; i < list2.getSize(); ++i){
            System.out.println(list2.getDataByIndex(i));
        }

        System.out.println(list2.deleteItemByData(100));

        for(int i = 0; i < list2.getSize(); ++i){
            System.out.println(list2.getDataByIndex(i));
        }

        System.out.println("---------------------------------------");
        System.out.println(list2.deleteFirstElement());
        System.out.println("---------------------------------------");

        for(int i = 0; i < list2.getSize(); ++i){
            System.out.println(list2.getDataByIndex(i));
        }

        list2.insertItemToStart(1);
        list2.insertItemToStart(0);

        System.out.println("---------------------------------------");

        for(int i = 0; i < list2.getSize(); ++i){
            System.out.println(list2.getDataByIndex(i));
        }

        System.out.println("---------------------------------------");

        list2.reverse();

        for(int i = 0; i < list2.getSize(); ++i){
            System.out.println(list2.getDataByIndex(i));
        }

        System.out.println(list2.getSize());

        System.out.println("---------------------------------------");

        SinglyLinkedList<Integer> newList = list2.copy();

        for(int i = 0; i < newList.getSize(); ++i){
            System.out.println(newList.getDataByIndex(i));
        }

        System.out.println(newList.getSize());

    }
}
