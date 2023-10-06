package com.datamasters.modelo;

public class List<T> {
    private Node<T> first;
    private int size;

    public List() {
        first = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void add(T t) {
        Node<T> newNode = new Node<>(t);
        if (first == null) {
            first = newNode;
        } else {
            Node<T> current = first;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    public void delete(T t) {
        if (first == null) {
            return;
        }
        if (first.getValue().equals(t)) {
            first = first.getNext();
            size--;
            return;
        }
        Node<T> previous = first;
        Node<T> current = first.getNext();
        while (current != null && !current.getValue().equals(t)) {
            previous = current;
            current = current.getNext();
        }
        if (current != null) {
            previous.setNext(current.getNext());
            size--;
        }
    }

    public T getAt(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Index: " + position + ", Size: " + size);
        }
        Node<T> current = first;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }

    public void clear() {
        first = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public List<T> getArrayList() {
        List<T> arrayList = new List<>();
        Node<T> current = first;
        while (current != null) {
            arrayList.add(current.getValue());
            current = current.getNext();
        }
        return arrayList;
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
