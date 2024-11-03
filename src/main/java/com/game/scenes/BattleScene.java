package com.game.scenes;

import java.util.InputMismatchException;
import com.game.characters.Player;
import com.game.utils.Utility;
import com.game.characters.Enemy;
import java.util.Scanner;

public class BattleScene {

    public static boolean startBattle(Player player, Enemy enemy, Scanner scan) {
        Utility.clearScreen();
        if (enemy.getName().equalsIgnoreCase("goblin")) {
            System.out.println("A wild Goblin appears!");
        } else if (enemy.getName().equalsIgnoreCase("orc")) {
            System.out.println("A giant Orc has appeared!");
        } else if (enemy.getName().equalsIgnoreCase("general")) {
            System.out.println("A powerful General has appeared!");
        } else {
            System.out.println("A Mysterious " + enemy.getName() + " appears!");
        }
        System.out.print("\nPlayer Health:\t" + getHealthBar(player.getHealth(), player.getMaxHealth()) + "\t [" + player.getHealth() + "/" + player.getMaxHealth() + "]");
        System.out.print("\t\tEnemy Health:\t" + getHealthBar(enemy.getHealth(), enemy.getMaxHealth()) + "\t [" + enemy.getHealth() + "/" + enemy.getMaxHealth() + "]");
        System.out.print("\nPlayer Mana:\t" + getManaBar(player.getMana(), player.getMaxMana()) + "\t [" + player.getMana() + "/" + player.getMaxMana() + "]");
        System.out.print("\t\tEnemy Mana:\t" + getManaBar(enemy.getMana(), enemy.getMaxMana()) + "\t [" + enemy.getMana() + "/" + enemy.getMaxMana() + "]");

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println();
            playerTurn(player, enemy, scan);
            if (enemy.getHealth() > 0) {
                enemyTurn(player, enemy);
            }
            displayHealth(player, enemy);
        }

        if (player.getHealth() > 0) {
            Utility.printWithDelay("\nYou defeated the " + enemy.getName() + "!");
            return true;
        } else {
            System.out.println("\nYou were defeated by the " + enemy.getName() + "...");
            return false;
        }
    }

    private static void playerTurn(Player player, Enemy enemy, Scanner scan) {
        System.out.println("\nYour turn:");
        System.out.println("1. Attack");
        System.out.println("2. Use Skill");
        System.out.print("Choose an action: ");
        try {
            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline
            Utility.clearScreen();

            switch (choice) {
                case 1:
                    player.attack(enemy);
                    System.out.println("You attacked the " + enemy.getName() + " for " + player.getAttackPower() + " damage.");
                    break;
                case 2:
                    useSkill(player, enemy, scan);
                    break;
                default:
                    System.out.println("Invalid choice. You missed your turn.");
                    break;
            }
        } catch (InputMismatchException e) {
            Utility.clearScreen();
            System.out.println("Invalid input. You missed your turn.");
            scan.nextLine(); // Consume the invalid input
        }
    }

    private static void useSkill(Player player, Enemy enemy, Scanner scan) {
        System.out.println("Choose a skill:");
        for (int i = 0; i < player.getSkills().size(); i++) {
            System.out.println((i + 1) + ". " + player.getSkills().get(i).getName() + " (Mana Cost: " + player.getSkills().get(i).getManaCost() + ")");
        }
        try {
            int skillChoice = scan.nextInt();
            scan.nextLine(); // Consume newline
            Utility.clearScreen();
            if (skillChoice > 0 && skillChoice <= player.getSkills().size()) {
                player.getSkills().get(skillChoice - 1).applyEffect(player, enemy);
            } else {
                System.out.println("Invalid choice. You missed your turn.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. You missed your turn.");
            scan.nextLine(); // Consume the invalid input
        }
    }

    private static void enemyTurn(Player player, Enemy enemy) {
        if (enemy.isStunned()) {
            System.out.println("The " + enemy.getName() + " is stunned and cannot move!");
            enemy.reduceStunDuration();
        } else {
            enemy.attack(player);
            System.out.println("The " + enemy.getName() + " attacked you for " + enemy.getAttackPower() + " damage.");
        }
    }

    private static void displayHealth(Player player, Enemy enemy) {
        String currentHealth = "Player Health: " + player.getHealth();
        Utility.displayBoxedMessage(currentHealth);
        System.out.print("\nPlayer Health:\t" + getHealthBar(player.getHealth(), player.getMaxHealth()) + "\t [" + player.getHealth() + "/" + player.getMaxHealth() + "]");
        System.out.print("\t\tEnemy Health:\t" + getHealthBar(enemy.getHealth(), enemy.getMaxHealth()) + "\t [" + enemy.getHealth() + "/" + enemy.getMaxHealth() + "]");
        System.out.print("\nPlayer Mana:\t" + getManaBar(player.getMana(), player.getMaxMana()) + "\t [" + player.getMana() + "/" + player.getMaxMana() + "]");
        System.out.print("\t\tEnemy Mana:\t" + getManaBar(enemy.getMana(), enemy.getMaxMana()) + "\t [" + enemy.getMana() + "/" + enemy.getMaxMana() + "]");
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
