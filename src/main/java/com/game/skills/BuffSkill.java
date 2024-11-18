package com.game.skills;

import com.game.characters.Character;
import com.game.utils.Utility;

public class BuffSkill extends Skill {
    private int buffAmount;

    public BuffSkill() {
        super("Buff", "Increases the player's defense.", 8); // Default values
        this.buffAmount = 10; // Default buff amount
    }

    public BuffSkill(String name, String description, int manaCost, int buffAmount) {
        super(name, description, manaCost);
        this.buffAmount = buffAmount;
    }

    public int getBuffAmount() {
        return buffAmount;
    }

    public void setBuffAmount(int buffAmount) {
        this.buffAmount = buffAmount;
    }

    @Override
    public void applyEffect(Character user, Character target) {
        if (user.getMana() >= getManaCost()) {
            user.useMana(getManaCost());
            user.increaseDefense(buffAmount);
            Utility.printWithDelay(user.getName() + "'s defense increased by " + buffAmount + ".\n");
        } else {
            Utility.printWithDelay("Not enough mana to use this skill.");
        }
    }

    @Override
    public void enhanceSkill(int level) {
        // Enhance the skill based on the level
        setBuffAmount(getBuffAmount() + level * 3); // Example enhancement logic
        setManaCost(getManaCost() + level); // Example enhancement logic
        setDescription(getDescription() + " (Enhanced)");
    }

    @Override
    public String getStats() {
        return "Increases defense by " + buffAmount;
    }
}