package com.datamasters.modelo;
import java.util.ArrayList;
public class List<T> {
protected  ArrayList<T> list;
    public List()
    {
        list = new ArrayList<>();
    }
    public int getSize()
    {
        return list.size();
    }
    public void add(T element)
    {
        list.add(element);
    }
    public void delete(T element)
    {
        list.remove(element);
    }
    public T getAt(int index)
    {
        if(index < 0 || index >= list.size() || !list.isEmpty())
        {
            return list.get(index);
        }
        else {
            throw  new IndexOutOfBoundsException("Index: " + index + ", Size: " + list.size());
        }
    }
    public void setAt(int index, T element)
    {
        if(index < 0 || index >= list.size()|| !list.isEmpty())
        {
            list.set(index, element);
        }
        else {
            throw  new IndexOutOfBoundsException("Index: " + index + ", Size: " + list.size());
        }
    }
    public void clear()
    {
        list.clear();
    }
    public boolean isEmpty()
    {
        return list.isEmpty();
    }
    public boolean contains(T element)
    {
        return list.contains(element);
    }
    public int indexOf(T element)
    {
        return list.indexOf(element);
    }
    public int lastIndexOf(T element)
    {
        return list.lastIndexOf(element);
    }
    public T[] toArray()
    {
        return (T[]) list.toArray();
    }
    public T[] toArray(T[] array)
    {
        return list.toArray(array);
    }
    public T getFirst()
    {
        return list.get(0);
    }
    public T getLast()
    {
        return list.get(list.size() - 1);
    }
    public void removeFirst()
    {
        list.remove(0);
    }
    public void removeLast()
    {
        list.remove(list.size() - 1);
    }
    public ArrayList<T> getArrayList()
    {
        return new ArrayList<>(list);
    }
    public void setArrayList(ArrayList<T> list)
    {
        this.list = list;
    }
    public void addAll(List<T> list)
    {
        this.list.addAll(list.getArrayList());
    }
}
