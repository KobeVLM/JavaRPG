package com.game.skills;

import com.game.characters.Player;
import com.game.utils.Utility;
import com.game.characters.Enemy;

public class HealSkill extends Skill {
    private int healAmount;

    public HealSkill() {
        super("Heal", "Restores health to the player.", 15); // Default values
        this.healAmount = 20; // Default heal amount
    }

    public HealSkill(String name, String description, int manaCost, int healAmount) {
        super(name, description, manaCost);
        this.healAmount = healAmount;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public void applyEffect(Player player, Enemy enemy) {
        if (player.getMana() >= getManaCost()) {
            player.useMana(getManaCost());
            player.heal(healAmount);
            Utility.printWithDelay("You healed for " + healAmount + " health.\n");
        } else {
            System.out.println("Not enough mana to use this skill.");
        }
    }

    @Override
    public void enhanceSkill(int level) {
        setHealAmount(getHealAmount() + level * 5); // Example enhancement logic
        setManaCost(getManaCost() + level); // Example enhancement logic
        setDescription(getDescription() + " (Enhanced)");
    }

    @Override
    public String getStats() {
        return "Heals " + healAmount + " HP";
    }
}
