package com.game.main;

import com.game.characters.*;
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

            // Start the game
            boolean gameWon = Game.startActOne(player, scan);

            if (gameWon) {
                System.out.println("Congratulations! You have completed the game.");
                break;
            } else {
                System.out.println("You were defeated. Starting over...");
            }
        }

        scan.close();
    }

    public static Player chooseCharacter(Scanner scan) {

        // Define skills
        Skill fireball = new AttackPowerSkill("Fireball", "A powerful fire attack.", 15, 10);
        Skill heal = new HealSkill("Heal", "Restores a large amount of health.", 20, 30);
        Skill shield = new BuffSkill("Shield", "Greatly increases defense.", 12, 15);

        // Define characters
        Player warrior = new Player(
            "Benimaru", 
            900, 
            200, 
            600, 
            600, 
            Arrays.asList(shield, heal, fireball), 
            "A strong and brave warrior with high defense and healing abilities."
        );
        Player mage = new Player(
            "Zephy", 
            80, 
            25, 
            60, 
            60, 
            Arrays.asList(fireball, heal), 
            "A wise and powerful mage with high attack power and healing abilities."
        );
        Player rogue = new Player(
            "Draven", 
            100, 
            20, 
            40, 
            40, 
            Arrays.asList(fireball, shield), 
            "A stealthy and cunning rogue with balanced attack and defense abilities."
        );

        // Character selection
        while (true) {
            System.out.println("Choose your character:");
            System.out.println("1. Benimaru");
            System.out.println("2. Zephy");
            System.out.println("3. Draven");
            System.out.println("4. Akagi");
            System.out.print("Enter the number of your choice: ");
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
            System.out.println("Enter the number of the character to view more information:");
            System.out.println("[1] Benimaru");
            System.out.println("[2] Zephy");
            System.out.println("[3] Draven");
            System.out.print("Enter the number of your choice: ");
            String input = scan.nextLine();
            Utility.clearScreen();
            switch (input) {
            case "1":
                Utility.displayBoxedMessage("Benimaru");
                System.out.println(
                    "A strong and brave warrior with high defense and healing abilities."
                    );
                break;
            case "2":
                Utility.displayBoxedMessage("Zephy");
                System.out.println("A wise and powerful mage with high attack power and healing abilities.");
                break;
            case "3":
                Utility.displayBoxedMessage("Draven");
                System.out.println("A stealthy and cunning rogue with balanced attack and defense abilities.");
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
