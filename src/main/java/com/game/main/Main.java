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
                    DialogueScene.displayTitle();
                    try {
                        Thread.sleep(2000); // 2 seconds delay
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
                    player.getInventory().addItem(new Potion("Health Potion", "Restores 20 health.", 40, 99));

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
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine(); // Clear the invalid input
            }
        }

        scan.close();
    }

    public static Player chooseCharacter(Scanner scan) {


        // Define skills for Benimaru, Zephy, and Draven
        Skill enhance = new AttackPowerSkill("Fireball", "A powerful fire attack.", 15, 10);
        Skill heal = new HealSkill("Heal", "Restores a large amount of health.", 20, 30);
        Skill shield = new BuffSkill("Shield", "Greatly increases defense.", 12, 15);

        // Define skills for Saitama
        Skill punch = new AttackPowerSkill("Punch", "A powerful punch attack.", 10, 10);
        Skill seriousPunch = new AttackPowerSkill("Serious Punch", "A devastating punch attack.", 0, 10);
        Skill seriousSeries = new AttackPowerSkill("Serious Series", "A series of powerful attacks.", 0, 20);


        // Define characters
        Player warrior = new Player(
            "Benimaru", 
            100, 
            300, 
            100, 
            100, 
            Arrays.asList(shield, heal, enhance), 
            "A strong and brave warrior with high defense and healing abilities."
        );
        Player mage = new Player(
            "Zephy", 
            80, 
            25, 
            60, 
            60, 
            Arrays.asList(enhance, heal), 
            "A wise and powerful mage with high attack power and healing abilities."
        );
        Player rogue = new Player(
            "Draven", 
            100, 
            20, 
            40, 
            40, 
            Arrays.asList(enhance, shield), 
            "A stealthy and cunning rogue with balanced attack and defense abilities."
        );

        Player OP = new Player(
            "Saitama",
            100000,
            10,
            9999999,
            99999999,
            Arrays.asList(punch, seriousPunch, seriousSeries),
            "A hero for fun"
        );

        // Character selection
        while (true) {
            System.out.println("Choose your character:");
            System.out.println("1. Benimaru");
            System.out.println("2. Zephy");
            System.out.println("3. Draven");
            System.out.println("4. Saitama");
            System.out.print("\nEnter the number of your choice: ");
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
            System.out.println("[1] Benimaru");
            System.out.println("[2] Zephy");
            System.out.println("[3] Draven");
            System.out.println("[4] Saitama");
            System.out.print("\nEnter the number of your choice: ");
            String input = scan.nextLine();
            Utility.clearScreen();
            switch (input) {
            case "1":
                Utility.displayBoxedMessage("Benimaru");
                System.out.println("Hero Name: " + "Benimaru");
                System.out.println("Health: " + "100");
                System.out.println("Attack Power: " + "20");
                System.out.println("Mana: " + "100/100");
                System.out.println("Backstory: " + "A strong and brave warrior with high defense and healing abilities.");
                System.out.println("Skills:" + "\n - Enhanced: A powerful fire attack." + "\n - Heal: Restores a large amount of health." + "\n - Shield: Greatly increases defense.");
                break;
            case "2":
                Utility.displayBoxedMessage("Zephy");
                System.out.println("Hero Name: " + "Zephy");
                System.out.println("Health: " + "80");
                System.out.println("Attack Power: " + "25");
                System.out.println("Mana: " + "60/60");
                System.out.println("Backstory: " + "A wise and powerful mage with high attack power and healing abilities.");
                System.out.println("Skills:" + "\n - Enhanced: A powerful fire attack." + "\n - Heal: Restores a large amount of health.");
                break;
            case "3":
                Utility.displayBoxedMessage("Draven");
                System.out.println("Hero Name: " + "Draven");
                System.out.println("Health: " + "100");
                System.out.println("Attack Power: " + "20");
                System.out.println("Mana: " + "40/40");
                System.out.println("Backstory: " + "A stealthy and cunning rogue with balanced attack and defense abilities.");
                System.out.println("Skills:" + "\n - Enhanced: A powerful fire attack." + "\n - Shield: Greatly increases defense.");
                break;
            case "4":
                Utility.displayBoxedMessage("Saitama");
                System.out.println("Hero Name: " + "Saitama");
                System.out.println("Health: " + "100000");
                System.out.println("Attack Power: " + "10");
                System.out.println("Mana: " + "9999999/99999999");
                System.out.println("Backstory: " + "A hero for fun");
                System.out.println("Skills:" + "\n - Punch: A powerful punch attack." + "\n - Serious Punch: A devastating punch attack." + "\n - Serious Series: A series of powerful attacks.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            System.out.println("Choose an option:");
            Utility.displayBoxedMessagesInline("1. Go back", "2. Exit");
            input = scan.nextLine();
            Utility.clearScreen();
            if (input.equals("2")) {
            break;
            }
        }
    }
}
