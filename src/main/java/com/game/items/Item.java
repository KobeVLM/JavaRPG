package com.game.items;

import com.game.characters.Character;

public interface Item {
    String getName();
    String getDescription();
    int getQuantity();
    void setQuantity(int quantity);
    void use(Character character);
}
