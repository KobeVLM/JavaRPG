package com.game.scenes;

import java.util.InputMismatchException;
import java.util.List;
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
        if (enemy.getName().equalsIgnoreCase("goblin")) {
            // Specific logic for goblin
        }

        Utility.displayDelay(2);

        System.out.println(player.getName() + " \t\t\t\t\t\t\t" + enemy.getName());
        System.out.print("Health:\t" + getHealthBar(player.getHealth(), player.getMaxHealth()) + "\t [" + player.getHealth() + "/" + player.getMaxHealth() + "]");
        System.out.print("\t\tHealth:\t" + getHealthBar(enemy.getHealth(), enemy.getMaxHealth()) + "\t [" + enemy.getHealth() + "/" + enemy.getMaxHealth() + "]");
        System.out.print("\nMana:\t" + getManaBar(player.getMana(), player.getMaxMana()) + "\t [" + player.getMana() + "/" + player.getMaxMana() + "]");
        System.out.print("\t\tMana:\t" + getManaBar(enemy.getMana(), enemy.getMaxMana()) + "\t [" + enemy.getMana() + "/" + enemy.getMaxMana() + "]");

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            playerTurn(player, enemy, scan);
            if (enemy.getHealth() > 0) {
                enemyTurn(player, enemy);
            }
            displayHealth(player, enemy);
        }

        if (player.getHealth() > 0) {
            System.out.println(player.getName() + " has defeated " + enemy.getName() + "!");
            return true;
        } else {
            System.out.println(player.getName() + " has been defeated by " + enemy.getName() + "...");
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
        System.out.println("[3] Open Inventory");
        System.out.print("\nChoose an action: ");
        try {
            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline
            Utility.clearScreen();

            switch (choice) {
                case 1:
                    player.attack(enemy);
                    break;
                case 2:
                    useSkill(player, enemy, scan);
                    break;
                case 3:
                    openInventory(player, scan);
                    break;
                default:
                    Utility.printWithDelay("Invalid choice. You missed your turn.");
                    break;
            }
        } catch (InputMismatchException e) {
            Utility.printWithDelay("Invalid input. You missed your turn.");
            scan.nextLine(); // Consume the invalid input
        }
    }

    private static void useSkill(Player player, Enemy enemy, Scanner scan) {
        System.out.println("Choose a skill:\n");
        for (int i = 0; i < player.getSkills().size(); i++) {
            Skill skill = player.getSkills().get(i);
            System.out.println("[" + (i + 1) + "] " + skill.getName() + " - " + skill.getDescription() + " (Mana Cost: " + skill.getManaCost() + ")");
        }

        try {
            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline
            Utility.clearScreen();

            if (choice > 0 && choice <= player.getSkills().size()) {
                Skill chosenSkill = player.getSkills().get(choice - 1);
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
        System.out.println("Choose an item type:\n");
        System.out.println("[1] Armor");
        System.out.println("[2] Weapon");
        System.out.println("[3] Potion");
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
                default:
                    Utility.clearScreen();
                    Utility.printWithDelay("Invalid choice. You missed your turn.");
                    Utility.displayDelay(2);
                    break;
            }
        } catch (InputMismatchException e) {
            Utility.clearScreen();
            Utility.printWithDelay("Invalid input. You missed your turn.");
            Utility.displayDelay(2);
            scan.nextLine(); // Consume the invalid input
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

        System.out.print("\nEnter the number of the armor to equip/unequip: ");
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