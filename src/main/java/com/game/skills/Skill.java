package com.game.skills;

import com.game.characters.Player;
import com.game.characters.Enemy;

public abstract class Skill {
    private String name;
    private String description;
    private int manaCost;

    public Skill(String name, String description, int manaCost) {
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public void use() {
        System.out.println("Using skill: " + name + " - " + description);
    }

    public abstract void applyEffect(Player player, Enemy enemy);

    public abstract void enhanceSkill(int level);
}
