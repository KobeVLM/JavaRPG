package com.game.items;

import com.game.characters.Character;

public class Armor implements Item {
    private String name;
    private String description;
    private int defenseIncrease;
    private int quantity;

    public Armor(String name, String description, int defenseIncrease) {
        this.name = name;
        this.description = description;
        this.defenseIncrease = defenseIncrease;
        this.quantity = 1; // Default quantity for armor
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
            character.increaseDefense(defenseIncrease);
            System.out.println(character.getName() + " equipped " + name + " and increased defense by " + defenseIncrease + ".");
        } else {
            System.out.println("No " + name + " left to use.");
        }
    }
}
