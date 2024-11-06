package com.game.characters;

import com.game.skills.Skill;
import java.util.List;
import java.util.Random;

public abstract class Character {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int attackPower;
    protected int mana;
    protected int maxMana;
    protected List<Skill> skills;
    protected String backstory;
    protected Random random; // Add a Random object

    public Character(String name, int health, int attackPower, int mana, int maxMana, List<Skill> skills, String backstory) {
        this.name = name;
        this.health = health;
        this.maxHealth = health; // Initialize maxHealth to the initial health value
        this.attackPower = attackPower;
        this.mana = mana;
        this.maxMana = maxMana;
        this.skills = skills;
        this.backstory = backstory;
        this.random = new Random(); // Initialize the Random object
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public String getBackstory() {
        return backstory;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void useMana(int amount) {
        this.mana -= amount;
        if (this.mana < 0) {
            this.mana = 0;
        }
    }

    public void attack(Character target) {
        int minAttackPower = (int) (this.attackPower * 0.8); // Minimum attack power (80% of base attack power)
        int maxAttackPower = (int) (this.attackPower * 1.2); // Maximum attack power (120% of base attack power)
        int randomAttackPower = random.nextInt(maxAttackPower - minAttackPower + 1) + minAttackPower;
        target.takeDamage(randomAttackPower);
        System.out.println(this.name + " attacks " + target.getName() + " for " + randomAttackPower + " damage.");
    }

    public void addSkill(Skill newSkill) {
        this.skills.add(newSkill);
    }

    public abstract void displayCharacterInfo();
}
