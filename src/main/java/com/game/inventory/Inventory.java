package com.game.inventory;

import com.game.items.Item;
import java.util.List;

public interface Inventory {
    void addItem(Item item);
    void removeItem(Item item);
    List<Item> getItems();
    void displayInventory();
}
