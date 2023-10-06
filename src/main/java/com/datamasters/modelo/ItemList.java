package com.datamasters.modelo;

public class ItemList {
    private final List<Item> items;

    public ItemList() {
        items = new List<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.delete(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
