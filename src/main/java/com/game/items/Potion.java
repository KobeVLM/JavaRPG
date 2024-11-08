package com.game.items;

import com.game.characters.Character;

public class Potion implements Item {
    private String name;
    private String description;
    private int healAmount;
    private int quantity;

    public Potion(String name, String description, int healAmount, int quantity) {
        this.name = name;
        this.description = description;
        this.healAmount = healAmount;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void use(Character character) {
        if (quantity > 0) {
            character.heal(healAmount);
            quantity--;
            System.out.println(character.getName() + " used " + name + " and healed for " + healAmount + " health. Remaining: " + quantity);
        } else {
            System.out.println("No " + name + " left to use.");
        }
    }
}
