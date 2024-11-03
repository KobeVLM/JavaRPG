package com.game.characters;

import java.util.List;

import com.game.skills.Skill;

public class General extends Character {
    public General(String name, int health, int attackPower, int mana, int maxMana, List<Skill> skills, String backstory) {
        super(name, health, attackPower, mana, maxMana, skills, backstory);
    }

    @Override
    public void displayCharacterInfo() {
        System.out.println("General Name: " + name);
        System.out.println("Health: " + health);
        System.out.println("Attack Power: " + attackPower);
        System.out.println("Mana: " + mana + "/" + maxMana);
        System.out.println("Backstory: " + backstory);
        System.out.println("Skills:");
        for (Skill skill : skills) {
            System.out.println(" - " + skill.getName() + ": " + skill.getDescription());
        }
    }
}