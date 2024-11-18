package com.game.skills;

import com.game.characters.Character;
import com.game.utils.Utility;

public class DamageSkill extends Skill {
    private int damageAmount;

    public DamageSkill() {
        super("Damage Skill", "Deals damage to the enemy.", 10); // Default values
        this.damageAmount = 10; // Default damage amount
    }

    public DamageSkill(String name, String description, int manaCost, int damageAmount) {
        super(name, description, manaCost);
        this.damageAmount = damageAmount;
    }

    public int getDamageAmount() {
        return damageAmount;
    }

    public void setDamageAmount(int damageAmount) {
        this.damageAmount = damageAmount;
    }

    @Override
    public void applyEffect(Character user, Character target) {
        if (user.getMana() >= getManaCost()) {
            user.useMana(getManaCost());
            target.takeDamage(damageAmount);
            Utility.printWithDelay(user.getName() + " dealt " + damageAmount + " damage to " + target.getName() + ".\n");
        } else {
            Utility.printWithDelay("Not enough mana to use this skill.");
        }
    }

    @Override
    public void enhanceSkill(int level) {
        // Enhance the skill based on the level
        setDamageAmount(getDamageAmount() + level * 2); // Example enhancement logic
        setManaCost(getManaCost() + level); // Example enhancement logic
        setDescription(getDescription() + " (Enhanced)");
    }

    @Override
    public String getStats() {
        return "Deals " + damageAmount + " damage to the enemy";
    }
}