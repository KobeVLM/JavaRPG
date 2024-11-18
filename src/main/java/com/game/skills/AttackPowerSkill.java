package com.game.skills;

import com.game.characters.Character;
import com.game.utils.Utility;

public class AttackPowerSkill extends Skill {
    private int attackPowerIncrease;

    public AttackPowerSkill() {
        super("Increase Attack Power", "Increases the player's attack power.", 10); // Default values
        this.attackPowerIncrease = 5; // Default attack power increase
    }

    public AttackPowerSkill(String name, String description, int manaCost, int attackPowerIncrease) {
        super(name, description, manaCost);
        this.attackPowerIncrease = attackPowerIncrease;
    }

    public int getAttackPowerIncrease() {
        return attackPowerIncrease;
    }

    public void setAttackPowerIncrease(int attackPowerIncrease) {
        this.attackPowerIncrease = attackPowerIncrease;
    }

    @Override
    public void applyEffect(Character user, Character target) {
        if (user.getMana() >= getManaCost()) {
            user.useMana(getManaCost());
            user.increaseAttackPower(attackPowerIncrease);
            Utility.printWithDelay(user.getName() + "'s attack power increased by " + attackPowerIncrease + ".\n");
        } else {
            Utility.printWithDelay("Not enough mana to use this skill.");
        }
    }

    @Override
    public void enhanceSkill(int level) {
        // Enhance the skill based on the level
        setAttackPowerIncrease(getAttackPowerIncrease() + level * 2); // Example enhancement logic
        setManaCost(getManaCost() + level); // Example enhancement logic
        setDescription(getDescription() + " (Enhanced)");
    }

    @Override
    public String getStats() {
        return "Increases attack power by " + attackPowerIncrease;
    }
}