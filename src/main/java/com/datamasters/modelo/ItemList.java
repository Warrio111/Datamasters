package com.datamasters.modelo;

public class ItemList extends List<Item>{
    public ItemList() {
        super(); // Call the constructor of the parent class
    }
    public void addItem(Item item) {
        add(item);
    }

    public void removeItem(Item item) {
        delete(item);
    }

    public List<Item> getItems() {
        return this;
    }

    public Item getItemByCode(String code) {
        for (int i = 0; i < getSize(); i++) {
            Item item = getAt(i);
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
