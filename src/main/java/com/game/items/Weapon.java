package com.game.items;

import com.game.characters.Character;

public class Weapon implements Item {
    private String name;
    private String description;
    private int attackIncrease;
    private int quantity;

    public Weapon(String name, String description, int attackIncrease) {
        this.name = name;
        this.description = description;
        this.attackIncrease = attackIncrease;
        this.quantity = 1; // Default quantity for weapon
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
            character.increaseAttackPower(attackIncrease);
            System.out.println(character.getName() + " equipped " + name + " and increased attack power by " + attackIncrease + ".");
        } else {
            System.out.println("No " + name + " left to use.");
        }
    }
}
