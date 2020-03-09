package ru.academits.yashch.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count = 0;

    public int getSize() {
        return count;
    }

    public T getFirstElementData() {
        if (count == 0) {
            throw new NoSuchElementException("Первый элемент отсутствует");
        }

        return head.getData();
    }

    public T getDataByIndex(int index) {
        return getItemByIndex(index).getData();
    }

    public T setDataByIndex(int index, T data) {
        ListItem<T> indexItem = getItemByIndex(index);

        T oldData = indexItem.getData();
        indexItem.setData(data);

        return oldData;
    }

    private ListItem<T> getItemByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс - " + index + " выходит за допустимые границы списка : " + 0 + "-" + (count - 1));
        }

        ListItem<T> item = head;

        for (int i = 0; i < index; ++i) {
            item = item.getNext();
        }

        return item;
    }

    public T deleteItemByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс - " + index + " выходит за допустимые границы списка : " + 0 + "-" + (count - 1));
        }

        if (index == 0) {
            return deleteFirstElement();
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);
        ListItem<T> indexItem = previousItem.getNext();

        previousItem.setNext(indexItem.getNext());
        --count;
        return indexItem.getData();
    }

    private void insertItemToStart(ListItem<T> newItem) {
        newItem.setNext(head);
        head = newItem;
        ++count;
    }

    public void insertItemToStart(T data) {
        insertItemToStart(new ListItem<>(data));
    }

    private void insertItemByIndex(ListItem<T> newItem, int index) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Индекс - " + index + " выходит за допустимые границы списка : " + 0 + "-" + count);
        }

        if (index == 0) {
            insertItemToStart(newItem);
            return;
        }

        ListItem<T> previousIndexItem = getItemByIndex(index - 1);

        newItem.setNext(previousIndexItem.getNext());
        previousIndexItem.setNext(newItem);

        ++count;
    }

    public void insertItemByIndex(T data, int index) {
        insertItemByIndex(new ListItem<>(data), index);
    }

    public boolean deleteItemByData(T data) {
        if (count == 0) {
            return false;
        }

        if (Objects.equals(data, getFirstElementData())) {
            deleteFirstElement();
            return true;
        }

        for (ListItem<T> thisItem = head.getNext(), previousItem = head; thisItem != null; previousItem = thisItem, thisItem = thisItem.getNext()) {
            if (Objects.equals(thisItem.getData(), data)) {
                previousItem.setNext(thisItem.getNext());
                --count;
                return true;
            }
        }

        return false;
    }

    public T deleteFirstElement() {
        if (head == null) {
            throw new NoSuchElementException("Первый элемент отсутствует");
        }

        T data = head.getData();
        head = head.getNext();

        --count;
        return data;
    }

    public void reverse() {
        if (count <= 1) {
            return;
        }

        ListItem<T> thisItem = head.getNext();
        head.setNext(null);

        for (; ; ) {
            ListItem<T> nextItem = thisItem.getNext();
            thisItem.setNext(head);
            head = thisItem;

            if (nextItem == null) {
                return;
            }

            thisItem = nextItem;
        }
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();

        if (count == 0) {
            return newList;
        }

        newList.head = new ListItem<>(head.getData());
        newList.count = count;

        for (ListItem<T> copyListItem = head.getNext(), newListItem = newList.head; copyListItem != null; newListItem = newListItem.getNext(), copyListItem = copyListItem.getNext()) {
            newListItem.setNext(new ListItem<>(copyListItem.getData()));
        }

        return newList;
    }
}