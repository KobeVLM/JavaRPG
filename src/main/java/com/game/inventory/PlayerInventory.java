package com.game.inventory;

import com.game.items.Item;
import java.util.ArrayList;
import java.util.List;

public class PlayerInventory implements Inventory {
    private List<Item> items;

    public PlayerInventory() {
        this.items = new ArrayList<>();
    }

    @Override
    public void addItem(Item item) {
        for (Item existingItem : items) {
            if (existingItem.getName().equals(item.getName())) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                System.out.println(item.getName() + " quantity increased to " + existingItem.getQuantity());
                return;
            }
        }
        items.add(item);
        System.out.println(item.getName() + "[x" + item.getQuantity() + "]" + " added to inventory.");
    }

    @Override
    public void removeItem(Item item) {
        items.remove(item);
        System.out.println(item.getName() + " removed from inventory.");
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public void displayInventory() {
        System.out.println("Inventory:");
        for (Item item : items) {
            System.out.println(" - " + item.getName() + " (" + item.getQuantity() + "): " + item.getDescription());
        }
    }
}
