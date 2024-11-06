package com.game.scenes;

import java.util.InputMismatchException;
import com.game.characters.Player;
import com.game.skills.Skill;
import com.game.utils.Utility;
import com.game.characters.Enemy;
import java.util.Scanner;

public class BattleScene {

    public static boolean startBattle(Player player, Enemy enemy, Scanner scan) {
        Utility.clearScreen();
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
        
        System.out.println(player.getName() + " \t\t\t\t\t\t\t" + enemy.getName());
        System.out.print("Health:\t" + getHealthBar(player.getHealth(), player.getMaxHealth()) + "\t [" + player.getHealth() + "/" + player.getMaxHealth() + "]");
        System.out.print("\t\tHealth:\t" + getHealthBar(enemy.getHealth(), enemy.getMaxHealth()) + "\t [" + enemy.getHealth() + "/" + enemy.getMaxHealth() + "]");
        System.out.print("\nMana:\t" + getManaBar(player.getMana(), player.getMaxMana()) + "\t [" + player.getMana() + "/" + player.getMaxMana() + "]");
        System.out.print("\t\tMana:\t" + getManaBar(enemy.getMana(), enemy.getMaxMana()) + "\t [" + enemy.getMana() + "/" + enemy.getMaxMana() + "]");

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println();
            playerTurn(player, enemy, scan);
            if (enemy.getHealth() > 0) {
                try {
                    displayHealth(player, enemy);
                    Utility.printSpace(1);
                    Utility.barrier();
                    Utility.printSpace(1);
                    Utility.displayDelay(1);
                    Utility.displayBoxedMessage(enemy.getName() + " Turn");
                    Thread.sleep(2000); // 2 seconds delay before the enemy attacks
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Utility.clearScreen();
                enemyTurn(player, enemy);
            }
            displayHealth(player, enemy);
        }

        if (player.getHealth() > 0) {
            Utility.printWithDelay("\u001B[92m\n\nYou defeated the " + enemy.getName() + "!\u001B[0m");
            Utility.displayDelay(2);
            return true;
        } else {
            System.out.println("\u001B[91m\n\nYou were defeated by the " + enemy.getName() + "...\u001B[0m");
            return false;
        }
    }

    private static void playerTurn(Player player, Enemy enemy, Scanner scan) {
        Utility.printSpace(1);
        Utility.barrier();
        Utility.printSpace(1);
        Utility.displayDelay(1);
        Utility.displayBoxedMessage(player.getName() + " Turn");
        System.out.println();
        System.out.println("[1] Attack");
        System.out.println("[2] Use Skill");
        System.out.print("\nChoose an action: ");
        try {
            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline
            Utility.clearScreen();

            switch (choice) {
                case 1:
                    player.attack(enemy);
                    Utility.printWithDelay("You attacked " + enemy.getName() + " for " + player.getAttackPower() + " damage.\n");
                    break;
                case 2:
                    useSkill(player, enemy, scan);
                    break;
                default:
                    Utility.printWithDelay("Invalid choice. You missed your turn.");
                    break;
            }
        } catch (InputMismatchException e) {
            Utility.clearScreen();
            System.out.println("Invalid input. You missed your turn.");
            scan.nextLine(); // Consume the invalid input
        }
    }

    private static void useSkill(Player player, Enemy enemy, Scanner scan) {
        System.out.println("Choose a skill:\n");
        for (int i = 0; i < player.getSkills().size(); i++) {
            Skill skill = player.getSkills().get(i);
            System.out.println( "["+ (i + 1) + "] " + skill.getName() + " \t- [" + skill.getStats() + "] " + " [Mana Cost: " + skill.getManaCost() + "]");
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

    private static void enemyTurn(Player player, Enemy enemy) {
        if (enemy.isStunned()) {
            Utility.printWithDelay("The " + enemy.getName() + " is stunned and cannot move!");
            enemy.reduceStunDuration();
        } else {
            enemy.attack(player);
            Utility.printWithDelay("The " + enemy.getName() + " attacked you for " + enemy.getAttackPower() + " damage.\n");
        }
    }

    private static void displayHealth(Player player, Enemy enemy) {
        String currentHealth = "Player Health: " + player.getHealth();
        Utility.printSpace(2);
        Utility.barrier();
        Utility.printSpace(1);
        Utility.displayBoxedMessage(currentHealth);
        System.out.println(player.getName() + " \t\t\t\t\t\t\t" + enemy.getName());
        System.out.print("Health:\t" + getHealthBar(player.getHealth(), player.getMaxHealth()) + "\t [" + player.getHealth() + "/" + player.getMaxHealth() + "]");
        System.out.print("\t\tHealth:\t" + getHealthBar(enemy.getHealth(), enemy.getMaxHealth()) + "\t [" + enemy.getHealth() + "/" + enemy.getMaxHealth() + "]");
        System.out.print("\nMana:\t" + getManaBar(player.getMana(), player.getMaxMana()) + "\t [" + player.getMana() + "/" + player.getMaxMana() + "]");
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
