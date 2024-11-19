package com.game.items;

import com.game.characters.Character;

public class Potion implements Item {
    private String name;
    private String description;
    private int healAmount;
    private int manaAmount; // New attribute for mana amount
    private int quantity;
    private boolean isManaPotion; // New attribute to specify if it's a mana potion

    public Potion(String name, String description, int healAmount, int manaAmount, int quantity, boolean isManaPotion) {
        this.name = name;
        this.description = description;
        this.healAmount = healAmount;
        this.manaAmount = manaAmount; // Initialize mana amount
        this.quantity = quantity;
        this.isManaPotion = isManaPotion; // Initialize potion type
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
            if (isManaPotion) {
                character.useMana(manaAmount);
                System.out.println(character.getName() + " used " + name + " and restored " + manaAmount + " mana. Remaining: " + quantity);
            } else {
                character.heal(healAmount);
                System.out.println(character.getName() + " used " + name + " and healed for " + healAmount + " health. Remaining: " + quantity);
            }
            quantity--;
        } else {
            System.out.println("No " + name + " left to use.");
        }
    }
}