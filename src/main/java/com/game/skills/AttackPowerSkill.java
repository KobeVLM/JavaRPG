package com.game.skills;

import com.game.characters.Player;
import com.game.characters.Enemy;
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
    public void applyEffect(Player player, Enemy enemy) {
        if (player.getMana() >= getManaCost()) {
            player.useMana(getManaCost());
            player.increaseAttackPower(attackPowerIncrease);
            Utility.printWithDelay("Your attack power increased by " + attackPowerIncrease + ".\n");
        } else {
            System.out.println("Not enough mana to use this skill.");
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
