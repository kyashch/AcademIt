package ru.academits.yashch.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count = 0;

    public int getSize() {
        return count;
    }

    public T getFirstElementData() {
        if (count == 0) {
            throw new NullPointerException("Список пуст");
        }

        return head.getData();
    }

    public T getDataByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Элемента с таким индексом нет");
        }

        return getItemByIndex(index).getData();
    }

    public T setDataByIndex(int index, T data) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Элемента с таким индексом нет");
        }

        ListItem<T> indexItem = getItemByIndex(index);

        T oldData = indexItem.getData();
        indexItem.setData(data);

        return oldData;
    }

    private ListItem<T> getItemByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Элемента с таким индексом нет");
        }

        ListItem<T> item = head;

        for (int i = 0; i < index; ++i) {
            item = item.getNext();
        }

        return item;
    }

    public T deleteItemByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Элемента с таким индексом нет");
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
            throw new IndexOutOfBoundsException("Передан неверный индекс");
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
        if (getFirstElementData().equals(data)) {
            deleteFirstElement();
            return true;
        }

        for (ListItem<T> thisItem = head.getNext(), previousItem = head; thisItem != null; previousItem = thisItem, thisItem = thisItem.getNext()) {
            if (thisItem.getData().equals(data)) {
                previousItem.setNext(thisItem.getNext());
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

    public void reverse() {
        ListItem<T> thisItem = head.getNext();
        head.setNext(null);

        for (; ; ) {
            ListItem<T> nextItem = thisItem.getNext();

            insertItemToStart(thisItem);
            --count;

            if (nextItem == null) {
                return;
            }

            thisItem = nextItem;
        }
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();

        newList.head = new ListItem<>(head.getData());
        ++newList.count;

        for (ListItem<T> copyListItem = head.getNext(), newListItem = newList.head; copyListItem != null; newListItem = newListItem.getNext(), copyListItem = copyListItem.getNext()) {
            newListItem.setNext(new ListItem<>(copyListItem.getData()));
            ++newList.count;
        }

        return newList;
    }
}
