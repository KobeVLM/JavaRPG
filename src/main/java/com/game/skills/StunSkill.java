package com.game.skills;

import com.game.characters.Player;
import com.game.utils.Utility;
import com.game.characters.Enemy;

public class StunSkill extends Skill {
    public StunSkill() {
        super("Stun", "Stuns the enemy for one turn.", 12); // Default values
    }

    public StunSkill(String name, String description, int manaCost) {
        super(name, description, manaCost);
    }

    @Override
    public void applyEffect(Player player, Enemy enemy) {
        if (player.getMana() >= getManaCost()) {
            player.useMana(getManaCost());
            enemy.applyStun(1); // Stun the enemy for 1 turn
            Utility.printWithDelay("The enemy is stunned and cannot move!");
        } else {
            System.out.println("Not enough mana to use this skill.");
        }
    }

    @Override
    public void enhanceSkill(int level) {
        setManaCost(getManaCost() - level); // Example enhancement logic
        setDescription(getDescription() + " (Enhanced)");
    }

    @Override
    public String getStats() {
        return "Stuns the enemy for 1 turn";
    }
}
