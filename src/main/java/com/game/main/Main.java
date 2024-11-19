package com.game.main;

import com.game.characters.*;
import com.game.items.Armor;
import com.game.items.Potion;
import com.game.items.Weapon;
import com.game.utils.Utility;
import com.game.scenes.*;
import com.game.skills.*;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        Utility.displayDelay(3);
        Utility.clearScreen();
        DialogueScene.displayTitle();
        try {
            Thread.sleep(3000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            

            Utility.clearScreen();
            Utility.displayAsciiArt("Main Menu");
            System.out.println("[1] Start Game");
            System.out.println("[2] Exit");
            System.out.print("\nEnter choice: ");
            try {
                int mainChoice = scan.nextInt();
                scan.nextLine(); // Consume newline

                if (mainChoice == 1) {
                    Utility.clearScreen();

                    // Display Character Information
                    displayCharacterInformation(scan);

                    // Character Selection
                    Player player = chooseCharacter(scan);
                    Utility.clearScreen();
                    Utility.displayBoxedMessage("You've chosen the hero, " + player.getName() + "!");
                    Utility.displayAsciiArt(player.getName());
                    Utility.displayClearDelay();
                    player.displayCharacterInfo();

                    // Add Starting items to player inventory
                    player.getInventory().addItem(new Armor("Steel Armor", "Provides excellent protection.", 10));
                    player.getInventory().addItem(new Weapon("Sword", "A sharp blade.", 15));
                    player.getInventory().addItem(new Potion("Small Health Potion", "Restores 20 health.", 20, 0, 5, false));
                    player.getInventory().addItem(new Potion("Small Mana Potion", "Restores 20 mana.", 0, 20, 5, true));

                    // Start the game
                    boolean gameWon = Game.prologue(player, scan);

                    if (gameWon) {
                        Utility.displayAsciiArt("VICTORY! (^_^)");
                        Utility.printWithDelay("Congratulations! You have completed the game.");
                        Utility.displayDelay(3);
                        break;
                    } else {
                        Utility.displayAsciiArt("DEFEAT (x_x)");
                        Utility.printWithDelay("You were defeated. Starting over...");
                        Utility.displayDelay(2);
                    }
                } else if (mainChoice == 2) {
                    Utility.clearScreen();
                    Utility.displayAsciiArt("( -_- ) Goodbye!");
                    Utility.printWithDelay("Exiting the game. Goodbye!");
                    Utility.displayDelay(3);
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                    Utility.displayDelay(2);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine(); // Clear the invalid input
            }
        }

        scan.close();
    }

    public static Player chooseCharacter(Scanner scan) {
        // Define skills for the characters
        Skill flameStrike = new DamageSkill("Flame Strike", "A powerful fire attack.", 10, 15);
        Skill divineHealing = new HealSkill("Divine Healing", "Restores a large amount of health.", 20, 30);
        Skill ironWall = new BuffSkill("Iron Wall", "Greatly increases defense.", 12, 15);
    
        Skill arcaneBlast = new DamageSkill("Arcane Blast", "A powerful magical attack.", 15, 10);
        Skill mysticHeal = new HealSkill("Mystic Heal", "Restores a large amount of health.", 20, 30);
    
        Skill shadowStrike = new DamageSkill("Shadow Strike", "A powerful shadow attack.", 15, 10);
        Skill evasion = new BuffSkill("Evasion", "Greatly increases defense.", 12, 15);
    
        // Define skills for Saitama
        Skill normalPunch = new AttackPowerSkill("Normal Punch", "A powerful punch attack.", 10, 10);
        Skill consecutiveNormalPunches = new AttackPowerSkill("Consecutive Normal Punches", "A devastating punch attack.", 0, 10);
        Skill seriousPunch = new AttackPowerSkill("Serious Punch", "A series of powerful attacks.", 0, 20);
    
        // Define characters
        Player warrior = new Player(
            "Rimuru", 
            500, // Increased HP
            50, // Increased Attack Power
            100, 
            100, 
            Arrays.asList(ironWall, divineHealing, flameStrike), 
            "A strong and brave warrior with high defense and healing abilities."
        );
        Player mage = new Player(
            "Nyvra", 
            400, // Increased HP
            60, // Increased Attack Power
            150, // Increased Mana
            150, 
            Arrays.asList(arcaneBlast, mysticHeal), 
            "A wise and powerful mage with high attack power and healing abilities."
        );
        Player rogue = new Player(
            "Draziel", 
            450, // Increased HP
            55, // Increased Attack Power
            120, // Increased Mana
            120, 
            Arrays.asList(shadowStrike, evasion), 
            "A stealthy and cunning rogue with balanced attack and defense abilities."
        );
    
        Player OP = new Player(
            "Saitama",
            9999,
            9000,
            9999,
            9999,
            Arrays.asList(normalPunch, consecutiveNormalPunches, seriousPunch),
            "A hero for fun"
        );
    
        // Character selection
        while (true) {
            System.out.println("Choose your character:");
            System.out.println("[1] Rimuru");
            System.out.println("[2] Nyvra");
            System.out.println("[3] Draziel");
            System.out.println("[4] Saitama");
            System.out.print("\nEnter the Hero of your choice: ");
            try {
                int choice = scan.nextInt();
                scan.nextLine(); // Consume newline
    
                switch (choice) {
                    case 1:
                        return warrior;
                    case 2:
                        return mage;
                    case 3:
                        return rogue;
                    case 4:
                        return OP;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scan.nextLine(); // Consume the invalid input
            }
        }
    }

    public static void displayCharacterInformation(Scanner scan){
        while (true) {
            System.out.println("Enter the number of the character to view more information:\n");
            System.out.println("[1] Rimuru");
            System.out.println("[2] Nyvra");
            System.out.println("[3] Draziel");
            System.out.println("[4] Saitama");
            System.out.print("\nEnter the number of your choice: ");
            String input = scan.nextLine();
            Utility.clearScreen();
            switch (input) {
            case "1":
                System.out.println("Hero Name: " + "Rimuru");
                System.out.println("Health: " + "500");
                System.out.println("Attack Power: " + "50");
                System.out.println("Mana: " + "100/100");
                System.out.println("Backstory: " + "A strong and brave warrior with high defense and healing abilities.");
                System.out.println("Skills:" + "\n - Iron Wall: Greatly increases defense." + "\n - Divine Healing: Restores a large amount of health." + "\n - Flame Strike: A powerful fire attack.");
                break;
            case "2":
                System.out.println("Hero Name: " + "Nyvra");
                System.out.println("Health: " + "400");
                System.out.println("Attack Power: " + "60");
                System.out.println("Mana: " + "150/150");
                System.out.println("Backstory: " + "A wise and powerful mage with high attack power and healing abilities.");
                System.out.println("Skills:" + "\n - Arcane Blast: A powerful magical attack." + "\n - Mystic Heal: Restores a large amount of health.");
                break;
            case "3":
                System.out.println("Hero Name: " + "Draziel");
                System.out.println("Health: " + "450");
                System.out.println("Attack Power: " + "55");
                System.out.println("Mana: " + "120/120");
                System.out.println("Backstory: " + "A stealthy and cunning rogue with balanced attack and defense abilities.");
                System.out.println("Skills:" + "\n - Shadow Strike: A powerful shadow attack." + "\n - Evasion: Greatly increases defense.");
                break;
            case "4":
                System.out.println("Hero Name: " + "Saitama");
                System.out.println("Health: " + "9999");
                System.out.println("Attack Power: " + "9000");
                System.out.println("Mana: " + "9999/9999");
                System.out.println("Backstory: " + "A hero for fun");
                System.out.println("Skills:" + "\n - Punch: A powerful punch attack." + "\n - Serious Punch: A devastating punch attack." + "\n - Serious Series: A series of powerful attacks.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            System.out.println();
            Utility.displayBoxedMessagesInline("1. Go back", "2. Exit");
            System.out.print("Choose an option: ");
            input = scan.nextLine();
            Utility.clearScreen();
            switch (input) {
                case "1":
                    break;
                case "2":
                    return;
                default:
                    System.out.println("Invalid Choice!\n");
                    continue;
            }
        }
    }
}