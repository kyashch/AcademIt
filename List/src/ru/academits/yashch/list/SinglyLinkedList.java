package ru.academits.yashch.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count = 0;

    public int getSize() {
        return count;
    }

    public T getFirstElementData() {
        return head.getData();
    }

    public T getDataByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Элемента с таким индексом нет");
        }

        int i = 0;
        ListItem<T> p = head;

        while (i < index) {
            p = p.getNext();
            ++i;
        }

        return p.getData();
    }

    public T setDataByIndex(int index, T data) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Элемента с таким индексом нет");
        }

        int i = 0;
        ListItem<T> p = head;

        while (i < index) {
            p = p.getNext();
            ++i;
        }

        T oldData = p.getData();
        p.setData(data);

        return oldData;
    }

    public T deleteItemByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Элемента с таким индексом нет");
        }

        int i = 0;
        T returnDate;

        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (i == index) {
                returnDate = p.getData();

                if (prev == null) {
                    deleteFirstElement();
                } else if (p.getNext() == null) {
                    prev.setNext(null);
                } else {
                    prev.setNext(p.getNext());
                }

                --count;
                return returnDate;
            }

            ++i;
        }

        return null;
    }

    public void insertItemToStart(ListItem<T> a) {
        if (a == null) {
            throw new NullPointerException("Передана пустая сслыка");
        }

        a.setNext(head);
        head = a;

        ++count;
    }

    public void insertItemByIndex(ListItem<T> a, int index) {
        if (index < 0 || index > count) {
            throw new IllegalArgumentException("Передан неверный индекс");
        }

        if (a == null) {
            throw new NullPointerException("Передана пустая сслыка");
        }

        if (index == 0) {
            insertItemToStart(a);
            return;
        }

        int i = 1;
        ListItem<T> p = head;

        while (i < index) {
            p = p.getNext();
            ++i;
        }

        if (p.getNext() == null) {
            p.setNext(a);
        } else {
            a.setNext(p.getNext());
            p.setNext(a);
        }

        ++count;
    }

    public boolean deleteItemByData(T data) {
        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (p.getData() == data) {

                if (prev == null) {
                    deleteFirstElement();
                } else if (p.getNext() == null) {
                    prev.setNext(null);
                } else {
                    prev.setNext(p.getNext());
                }

                --count;
                return true;
            }
        }

        return false;
    }

    public T deleteFirstElement() {
        if (head == null) {
            throw new NullPointerException("Первый элемент отсутствует");
        }

        T data = head.getData();
        head = head.getNext();

        --count;
        return data;
    }

    public void expandList() {
        ListItem<T> p = head.getNext();
        head.setNext(null);

        for (; ; ) {
            ListItem<T> k = p.getNext();

            insertItemToStart(p);
            --count;

            if (k == null) {
                return;
            }

            p = k;
        }
    }

    public SinglyLinkedList<T> copyList(SinglyLinkedList<T> list) {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();
        int i = 0;

        for (ListItem<T> p = list.head; p != null; p = p.getNext()) {
            ListItem<T> thisItem = new ListItem<>(p.getData());

            newList.insertItemByIndex(thisItem, i);

            ++i;
        }

        return newList;
    }
}
