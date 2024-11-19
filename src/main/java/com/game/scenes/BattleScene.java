package com.game.scenes;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.game.characters.Player;
import com.game.skills.Skill;
import com.game.utils.Utility;
import com.game.characters.Enemy;
import com.game.items.Item;
import com.game.items.Armor;
import com.game.items.Weapon;
import com.game.items.Potion;

public class BattleScene {

    public static boolean startBattle(Player player, Enemy enemy, Scanner scan) {
        Utility.clearScreen();
        int initialMaxHealth = player.getMaxHealth(); // Store the initial max health
        int initialMaxMana = player.getMaxMana(); // Store the initial max mana
    
        // Display enemy information
        if (enemy.getName().equalsIgnoreCase("goblin")) {
            System.out.println("A wild Goblin appears!");
            Utility.printSpace(3);
            System.out.println("Enemy Name: " + enemy.getName());
            System.out.println("Health: " + enemy.getHealth());
            System.out.println("Attack Power: " + enemy.getAttackPower());
            System.out.println("Mana: " + enemy.getMana() + "/" + enemy.getMaxMana());
            Utility.printWithDelay("Description: \n" + enemy.getBackstory());
            Utility.displayDelay(2);
            Utility.clearScreen();
        } else if (enemy.getName().equalsIgnoreCase("orc")) {
            System.out.println("A giant Orc has appeared!");
            Utility.printSpace(3);
            System.out.println("Enemy Name: " + enemy.getName());
            System.out.println("Health: " + enemy.getHealth());
            System.out.println("Attack Power: " + enemy.getAttackPower());
            System.out.println("Mana: " + enemy.getMana() + "/" + enemy.getMaxMana());
            Utility.printWithDelay("Description: \n" + enemy.getBackstory());
            Utility.displayDelay(2);
            Utility.clearScreen();
        } else if (enemy.getName().equalsIgnoreCase("general1") || enemy.getName().equalsIgnoreCase("general2")) {
            System.out.println("A powerful General has appeared!");
            Utility.printSpace(3);
            System.out.println("Enemy Name: " + enemy.getName());
            System.out.println("Health: " + enemy.getHealth());
            System.out.println("Attack Power: " + enemy.getAttackPower());
            System.out.println("Mana: " + enemy.getMana() + "/" + enemy.getMaxMana());
            Utility.printWithDelay("Description: \n" + enemy.getBackstory());
            Utility.displayDelay(2);
            Utility.clearScreen();
        } else {
            System.out.println("A Mysterious " + enemy.getName() + " appears!");
            Utility.printSpace(3);
            System.out.println("Enemy Name: " + enemy.getName());
            System.out.println("Health: " + enemy.getHealth());
            System.out.println("Attack Power: " + enemy.getAttackPower());
            System.out.println("Mana: " + enemy.getMana() + "/" + enemy.getMaxMana());
            Utility.printWithDelay("Description: \n" + enemy.getBackstory());
            Utility.displayDelay(2);
            Utility.clearScreen();
        }
    
        Utility.displayDelay(2);
    
        System.out.println(player.getName() + " \t\t\t\t\t\t" + enemy.getName());
        System.out.print("Health:\t" + getHealthBar(player.getHealth(), initialMaxHealth) + "\t [" + player.getHealth() + "/" + initialMaxHealth + "]");
        System.out.print("\t\tHealth:\t" + getHealthBar(enemy.getHealth(), enemy.getMaxHealth()) + "\t [" + enemy.getHealth() + "/" + enemy.getMaxHealth() + "]");
        System.out.print("\nMana:\t" + getManaBar(player.getMana(), initialMaxMana) + "\t [" + player.getMana() + "/" + initialMaxMana + "]");
        System.out.print("\t\tMana:\t" + getManaBar(enemy.getMana(), enemy.getMaxMana()) + "\t [" + enemy.getMana() + "/" + enemy.getMaxMana() + "]");
    
        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println();
            playerTurn(player, enemy, scan);
            if (enemy.getHealth() > 0) {
                try {
                    displayHealth(player, enemy, initialMaxHealth, initialMaxMana);
                    Utility.printSpace(1);
                    Utility.barrier();
                    Utility.printSpace(1);
                    Utility.displayDelay(1);
                    Utility.displayBoxedMessage(enemy.getName() + "'s Turn");
                    Thread.sleep(2000); // 2 seconds delay before the enemy attacks
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Utility.clearScreen();
                enemyTurn(player, enemy);
            }
            displayHealth(player, enemy, initialMaxHealth, initialMaxMana);
        }
    
        if (player.getHealth() > 0) {
            Utility.printWithDelay("\u001B[92m\n\nYou defeated the enemy " + enemy.getName() + "!\u001B[0m");
            Utility.displayDelay(2);
            return true;
        } else {
            Utility.printWithDelay("\u001B[91m\n\nYou were defeated by the enemy " + enemy.getName() + "...\u001B[0m");
            Utility.displayDelay(2);
            return false;
        }
    }

    private static void playerTurn(Player player, Enemy enemy, Scanner scan) {
        boolean turnOver = false;
        while (!turnOver) {

            Utility.printSpace(1);
            Utility.barrier();
            Utility.printSpace(1);
            Utility.displayDelay(1);
            Utility.displayBoxedMessage(player.getName() + "'s Turn");
            System.out.println();
            System.out.println("[1] Attack");
            System.out.println("[2] Use Skill");
            System.out.println("[3] Open Inventory");
            System.out.print("\nChoose an action: ");
            try {
                int choice = scan.nextInt();
                scan.nextLine(); // Consume newline
                Utility.clearScreen();
    
                switch (choice) {
                    case 1:
                        int attackPower = player.attack(enemy);
                        Utility.printWithDelay("You attacked " + enemy.getName() + " for " + attackPower + " damage.\n");
                        turnOver = true;
                        break;
                    case 2:
                        useSkill(player, enemy, scan);
                        turnOver = true;
                        break;
                    case 3:
                        displayHealth(player, enemy, player.getMaxHealth(), player.getMaxMana());
                        openInventory(player, scan);
                        break;
                    default:
                        Utility.printWithDelay("Invalid choice. You missed your turn.");
                        turnOver = true;
                        break;
                }
            } catch (InputMismatchException e) {
                Utility.clearScreen();
                Utility.printWithDelay("Invalid input. You missed your turn.");
                scan.nextLine(); // Consume the invalid input
                turnOver = true;
            }
        }
    }

    private static void useSkill(Player player, Enemy enemy, Scanner scan) {
        System.out.println("Choose a skill:\n");
        for (int i = 0; i < player.getSkills().size(); i++) {
            Skill skill = player.getSkills().get(i);
            System.out.println("[" + (i + 1) + "] " + skill.getName() + " - " + "(" + skill.getStats() + ")" + " (Mana Cost: " + skill.getManaCost() + ")");
        }

        try {
            System.out.print("\nSkill Choice: ");
            int skillChoice = scan.nextInt();
            scan.nextLine(); // Consume newline
            Utility.clearScreen();
            if (skillChoice > 0 && skillChoice <= player.getSkills().size()) {
                Skill chosenSkill = player.getSkills().get(skillChoice - 1);
                chosenSkill.applyEffect(player, enemy);
            } else {
                Utility.printWithDelay("Invalid choice. You missed your turn.");
            }
        } catch (InputMismatchException e) {
            Utility.printWithDelay("Invalid input. You missed your turn.");
            scan.nextLine(); // Consume the invalid input
        }
    }

    private static void openInventory(Player player, Scanner scan) {
        boolean done = false;

        Utility.printSpace(2);
        Utility.barrier();
        while (!done) {
            System.out.println("\nChoose an item type:\n");
            System.out.println("[1] Armor");
            System.out.println("[2] Weapon");
            System.out.println("[3] Potion");
            System.out.println("[4] Back to main menu");
            System.out.print("\nEnter choice: ");
            try {
                int choice = scan.nextInt();
                scan.nextLine(); // Consume newline
                Utility.clearScreen();
    
                switch (choice) {
                    case 1:
                        manageArmor(player, scan);
                        break;
                    case 2:
                        manageWeapon(player, scan);
                        break;
                    case 3:
                        managePotion(player, scan);
                        break;
                    case 4:
                        done = true;
                        break;
                    default:
                        Utility.printWithDelay("Invalid choice. Please try again.");
                        Utility.displayDelay(2);
                        break;
                }
            } catch (InputMismatchException e) {
                Utility.clearScreen();
                Utility.printWithDelay("Invalid input. Please try again.");
                Utility.displayDelay(2);
                scan.nextLine(); // Consume the invalid input
            }
        }
    }

    private static void manageArmor(Player player, Scanner scan) {
        System.out.println("Armor Inventory:\n");
        List<Item> armors = player.getInventory().getItems().stream()
                .filter(item -> item instanceof Armor)
                .collect(Collectors.toList());

        if (armors.isEmpty()) {
            System.out.println("No armor items in inventory.");
            return;
        }

        for (int i = 0; i < armors.size(); i++) {
            Item armor = armors.get(i);
            System.out.println("[" + (i + 1) + "] " + armor.getName() + " - " + armor.getDescription());
        }

        System.out.print("\nEnter the number of the Armor to equip/unequip: ");
        try {
            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline
            Utility.clearScreen();

            if (choice > 0 && choice <= armors.size()) {
                Armor chosenArmor = (Armor) armors.get(choice - 1);
                chosenArmor.use(player);
            } else {
                Utility.printWithDelay("Invalid choice. You missed your turn.");
            }
        } catch (InputMismatchException e) {
            Utility.printWithDelay("Invalid input. You missed your turn.");
            scan.nextLine(); // Consume the invalid input
        }
    }

    private static void manageWeapon(Player player, Scanner scan) {
        System.out.println("Weapon Inventory:\n");
        List<Item> weapons = player.getInventory().getItems().stream()
                .filter(item -> item instanceof Weapon)
                .collect(Collectors.toList());

        if (weapons.isEmpty()) {
            System.out.println("No weapon items in inventory.");
            return;
        }

        for (int i = 0; i < weapons.size(); i++) {
            Item weapon = weapons.get(i);
            System.out.println("[" + (i + 1) + "] " + weapon.getName() + " - " + weapon.getDescription());
        }

        System.out.print("\nEnter the number of the weapon to equip/unequip: ");
        try {
            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline
            Utility.clearScreen();

            if (choice > 0 && choice <= weapons.size()) {
                Weapon chosenWeapon = (Weapon) weapons.get(choice - 1);
                chosenWeapon.use(player);
            } else {
                Utility.printWithDelay("Invalid choice. You missed your turn.");
            }
        } catch (InputMismatchException e) {
            Utility.printWithDelay("Invalid input. You missed your turn.");
            scan.nextLine(); // Consume the invalid input
        }
    }

    private static void managePotion(Player player, Scanner scan) {
        System.out.println("Potion Inventory:\n");
        List<Item> potions = player.getInventory().getItems().stream()
                .filter(item -> item instanceof Potion)
                .collect(Collectors.toList());

        if (potions.isEmpty()) {
            System.out.println("No potion items in inventory.");
            return;
        }

        for (int i = 0; i < potions.size(); i++) {
            Item potion = potions.get(i);
            System.out.println("[" + (i + 1) + "] " + potion.getName() + " - " + potion.getDescription());
        }

        System.out.print("\nEnter the number of the potion to use: ");
        try {
            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline
            Utility.clearScreen();

            if (choice > 0 && choice <= potions.size()) {
                Potion chosenPotion = (Potion) potions.get(choice - 1);
                chosenPotion.use(player);
            } else {
                Utility.printWithDelay("Invalid choice. You missed your turn.");
            }
        } catch (InputMismatchException e) {
            Utility.printWithDelay("Invalid input. You missed your turn.");
            scan.nextLine(); // Consume the invalid input
        }
    }

    private static void enemyTurn(Player player, Enemy enemy) {
        if (enemy.isStunned()) {
            Utility.printWithDelay("The enemy " + enemy.getName() + " is stunned and cannot move!");
            enemy.reduceStunDuration();
        } else {
            Random random = new Random();
            int action = random.nextInt(100); // Generate a random number between 0 and 99
            int healthPercentage = (int) ((enemy.getHealth() / (double) enemy.getMaxHealth()) * 100);
    
            if (healthPercentage < 30) {
                // If enemy's health is below 30%, 40% chance to use a healing skill
                if (action < 40) {
                    enemy.useHealSkill();
                } else {
                    // 60% chance to attack normally
                    int attackPower = enemy.attack(player);
                    Utility.printWithDelay("The enemy " + enemy.getName() + " attacked you for " + attackPower + " damage.\n");
                }
            } else if (healthPercentage < 70) {
                // If enemy's health is below 70%, 30% chance to use a damage skill
                if (action < 30) {
                    enemy.useDamageSkill(player);
                } else {
                    // 70% chance to attack normally
                    int attackPower = enemy.attack(player);
                    Utility.printWithDelay("The enemy " + enemy.getName() + " attacked you for " + attackPower + " damage.\n");
                }
            } else {
                // If enemy's health is above 70%, 30% chance to use a damage skill
                if (action < 30) {
                    enemy.useDamageSkill(player);
                } else {
                    // 70% chance to attack normally
                    int attackPower = enemy.attack(player);
                    Utility.printWithDelay("The enemy " + enemy.getName() + " attacked you for " + attackPower + " damage.\n");
                }
            }
        }
    }

    private static void displayHealth(Player player, Enemy enemy, int initialMaxHealth, int initialMaxMana) {
        String currentHealth = "Player Health: " + player.getHealth();
        Utility.printSpace(2);
        Utility.barrier();
        Utility.printSpace(1);
        Utility.displayBoxedMessage(currentHealth);
        System.out.println(player.getName() + " \t\t\t\t\t\t" + enemy.getName());
        System.out.print("Health:\t" + getHealthBar(player.getHealth(), initialMaxHealth) + "\t [" + player.getHealth() + "/" + initialMaxHealth + "]");
        System.out.print("\t\tHealth:\t" + getHealthBar(enemy.getHealth(), enemy.getMaxHealth()) + "\t [" + enemy.getHealth() + "/" + enemy.getMaxHealth() + "]");
        System.out.print("\nMana:\t" + getManaBar(player.getMana(), initialMaxMana) + "\t [" + player.getMana() + "/" + initialMaxMana + "]");
        System.out.print("\t\t Mana:\t" + getManaBar(enemy.getMana(), enemy.getMaxMana()) + "\t [" + enemy.getMana() + "/" + enemy.getMaxMana() + "]");
        System.out.println();
    }

    private static String getHealthBar(int currentHealth, int maxHealth) {
        int percentage = (int) ((currentHealth / (double) maxHealth) * 100);
        String color;
        if (percentage > 75) {
            color = "\u001B[32m"; // Green
        } else if (percentage > 35) {
            color = "\u001B[33m"; // Yellow
        } else {
            color = "\u001B[31m"; // Red
        }
        StringBuilder healthBar = new StringBuilder(color + "[");
        int totalBars = 20;
        int filledBars = (int) ((percentage / 100.0) * totalBars);
        for (int i = 0; i < totalBars; i++) {
            if (i < filledBars) {
                healthBar.append("█");
            } else {
                healthBar.append(" ");
            }
        }
        healthBar.append("]\u001B[0m"); // Reset color
        return healthBar.toString();
    }

    private static String getManaBar(int currentMana, int maxMana) {
        int percentage = (int) ((currentMana / (double) maxMana) * 100);
        String color;
        if (percentage > 75) {
            color = "\u001B[34m"; // Blue
        } else if (percentage > 35) {
            color = "\u001B[36m"; // Cyan
        } else {
            color = "\u001B[35m"; // Purple
        }
        StringBuilder manaBar = new StringBuilder(color + "[");
        int totalBars = 20;
        int filledBars = (int) ((percentage / 100.0) * totalBars);
        for (int i = 0; i < totalBars; i++) {
            if (i < filledBars) {
                manaBar.append("█");
            } else {
                manaBar.append(" ");
            }
        }
        manaBar.append("]\u001B[0m"); // Reset color
        return manaBar.toString();
    }
}