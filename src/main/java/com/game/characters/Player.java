package com.game.characters;

import com.game.skills.Skill;
import java.util.List;

public class Player extends Character {
    public Player(String name, int health, int attackPower, int mana, int maxMana, List<Skill> skills, String backstory) {
        super(name, health, attackPower, mana, maxMana, skills, backstory);
    }

    @Override
    public void displayCharacterInfo() {
        System.out.println("Player Name: " + name);
        System.out.println("Health: " + health);
        System.out.println("Attack Power: " + attackPower);
        System.out.println("Mana: " + mana + "/" + maxMana);
        System.out.println("Backstory: " + backstory);
        System.out.println("Skills:");
        for (Skill skill : skills) {
            System.out.println(" - " + skill.getName() + ": " + skill.getDescription() + " (Mana Cost: " + skill.getManaCost() + ")");
        }
        inventory.displayInventory(); // Display inventory
    }

    public void increaseAttackPower(int amount) {
        this.attackPower += amount;
    }

    public void heal(int amount) {
        this.health += amount;
    }

    public void increaseDefense(int amount) {
        // Implement defense logic
    }
}