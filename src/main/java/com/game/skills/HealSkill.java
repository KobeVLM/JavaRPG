package com.game.skills;

import com.game.characters.Character;
import com.game.utils.Utility;

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
    public void applyEffect(Character user, Character target) {
        if (user.getMana() >= getManaCost()) {
            user.useMana(getManaCost());
            user.heal(healAmount);
            Utility.printWithDelay(user.getName() + " healed for " + healAmount + " health.\n");
        } else {
            Utility.printWithDelay("Not enough mana to use this skill.");
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