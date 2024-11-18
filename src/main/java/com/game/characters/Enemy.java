package com.game.characters;

import com.game.skills.Skill;
import com.game.skills.DamageSkill;
import com.game.skills.HealSkill;
import java.util.List;

public class Enemy extends Character {
    private int stunDuration;

    public Enemy(String name, int health, int attackPower, int mana, int maxMana, List<Skill> skills, String backstory) {
        super(name, health, attackPower, mana, maxMana, skills, backstory);
        this.stunDuration = 0;
    }

    @Override
    public void displayCharacterInfo() {
        System.out.println("Enemy Name: " + name);
        System.out.println("Health: " + health);
        System.out.println("Attack Power: " + attackPower);
        System.out.println("Mana: " + mana + "/" + maxMana);
        System.out.println("Backstory: " + backstory);
        System.out.println("Skills:");
        if (skills != null) {
            for (Skill skill : skills) {
                System.out.println(" - " + skill.getName() + ": " + skill.getDescription() + " (Mana Cost: " + skill.getManaCost() + ")");
            }
        } else {
            System.out.println(" - No skills available.");
        }
    }

    public void applyStun(int duration) {
        this.stunDuration = duration;
    }

    public boolean isStunned() {
        return stunDuration > 0;
    }

    public void reduceStunDuration() {
        if (stunDuration > 0) {
            stunDuration--;
        }
    }

    public void useDamageSkill(Player player) {
        for (Skill skill : skills) {
            if (skill instanceof DamageSkill) {
                skill.applyEffect(this, player); // Corrected parameter order
                return;
            }
        }
        System.out.println("No damage skill available.");
    }

    public void useHealSkill() {
        for (Skill skill : skills) {
            if (skill instanceof HealSkill) {
                skill.applyEffect(this, this); // Apply effect to the enemy itself
                return;
            }
        }
        System.out.println("No heal skill available.");
    }
}