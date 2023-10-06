package com.datamasters.modelo;
/*
This is our personal implementation of double ciruclar linked list in Java
Robert has studied this topic in the past and he has implemented this code from scratch
You can find the Udemy mastering data structures and algorithms course from Abdul Bari:
https://www.udemy.com/course/datastructurescncpp/
 */
public class List<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public List() {
        first = null;
        last = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void add(T t) {
        Node<T> newNode = new Node<>(t);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
            first.setNext(last);
            last.setPrevious(first);
        } else {
            newNode.setPrevious(last);
            last.setNext(newNode);
            last = newNode;
            last.setNext(first);
            first.setPrevious(last);
        }
        size++;
    }

    public void delete(T t) {
        if (isEmpty()) {
            return;
        }
        Node<T> current = first;
        while (current != null) {
            if (current.getValue().equals(t)) {
                if (size == 1) {
                    first = null;
                    last = null;
                } else if (current == first) {
                    first = current.getNext();
                    first.setPrevious(last);
                    last.setNext(first);
                } else if (current == last) {
                    last = current.getPrevious();
                    last.setNext(first);
                    first.setPrevious(last);
                } else {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                }
                size--;
                return;
            }
            current = current.getNext();
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
        last = null;
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
            if(current == first) {
                break;
            }
        }
        return arrayList;
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> previous;
        public Node(T value) {
            this.value = value;
            this.next = null;
            this.previous = null;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getNext() {
            return this.next;
        }
        public void setNext(Node<T> next) {
            this.next = next;
        }
        public  Node<T> getPrevious() {
            return this.previous;
        }
        public  void setPrevious(Node<T> previous) {
            this.previous = previous;
        }


    }
}
