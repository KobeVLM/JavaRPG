package com.game.main;

import java.util.Arrays;
import com.game.scenes.*;
import com.game.utils.Utility;
import com.game.utils.SkipButton;
import com.game.characters.Player;
import com.game.characters.Enemy;
import com.game.skills.*;
import java.util.Scanner;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("unused")
public class Game {
    public static boolean prologue(Player player, Scanner scan) {
        SkipButton.resetSkip(); // Reset the skip flag
        SkipButton.createSkipButton(); // Create the skip button

        Utility.displayClearDelay();
        Utility.displayAsciiArt(player.getName() + " Journey Begins ");
        Utility.displayDelay(2);
        Utility.displayBoxedMessage("Starting World");
        Utility.displayAsciiArt("Ember Isles");
        try {
            Thread.sleep(2000); // 2 seconds delay
            Utility.playSound("Prologue.wav");
            Utility.printWithDelay(
                "Scene: \n" +
                player.getName() +
                " stands alone at the edge of the Ember Isles, looking out over the lava flows with a hardened expression. \n " +
                "His thoughts are consumed with vengeance, the Abyssal Lord's creatures responsible for his family's death.\n " +
                "The wind howls, carrying ash and embers as " + player.getName() + " begins his journey.");
            Utility.waitForSoundToComplete();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Utility.displayClearDelay();

        Utility.printWithDelay(player.getName() + ": \n\"It's been too long. I've waited for this moment... to end the creature that took everything from me.\"");

        Utility.displayDelay(2);

        System.out.println("\n\n\nChoose your response:");
        System.out.println("[1] \"I'll make them suffer.\"  ");
        System.out.println("[2] \"Focus, don't lose yourself in anger.\" \n");
        System.out.print("Choice: ");
        int choice = scan.nextInt();

        Enemy lavafiend = new Enemy(
            "Lava Fiend",
            300, // Increased HP
            30, // Increased Attack Power
            50, // Increased Mana
            50,
            Arrays.asList(
                new DamageSkill("Fireball", "Launches a fireball at the enemy.", 10, 20),
                new HealSkill("Ember Heal", "Heals the Lava Fiend.", 20, 30)
            ),
            "Spawned from the Ember Isles' molten depths, the Lava Fiend is a fiery creature infused with dark magic. " +
            "Guarding hidden treasures, its cunning and agility make it a dangerous adversary despite its small size."
        );

        try {
            Thread.sleep(1500); // 1.5 seconds delay
            Utility.printSpace(3);
            Utility.printWithDelay(player.getName() + ": \n\"Creatures of the Abyss... You'll be the first to fall.\"");
            Thread.sleep(1500); // 1.5 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SkipButton.closeSkipButton(); // Close the skip button

        // Example of a battle in Prologue
        if (!BattleScene.startBattle(player, lavafiend, scan)) {
            return false;
        }

        // Dialogue
        try {
            Thread.sleep(200); // 2 seconds delay
            Utility.clearScreen();
            Utility.printWithDelay("After the fight, " + player.getName() + " stands among the ashes of the defeated enemies, breathing heavily.");
            Thread.sleep(100); // 1.5 seconds delay
            Utility.printSpace(3);
            Utility.printWithDelay(player.getName() + ": \n\"This is just the beginning. I'll find the Abyssal Lord, no matter the cost.\"");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Utility.displayClearDelay();
        // End of Act Prologue
        Utility.displayAsciiArt("ACT Prologue - Completed");
        Utility.displayDelay(2);

        // Proceed to Act I
        return startActOne(player, scan);
    }

    public static boolean startActOne(Player player, Scanner scan) {
        SkipButton.resetSkip(); // Reset the skip flag

        Utility.displayClearDelay();
        Utility.displayAsciiArt("ACT I - The Frosted Peaks");
        Utility.displayDelay(2);
        Utility.displayBoxedMessage("WORLD");
        Utility.displayAsciiArt("Frosted Peaks");
        try {
            Thread.sleep(2000); // 2 seconds delay
            Utility.playSound("Act1.wav");
            Utility.printWithDelay(
                "Scene: \n" +
                "After leaving the Ember Isles, " + player.getName() + " travels to the Frosted Peaks, a cold and treacherous mountain range. " +
                "As he navigates the icy terrain, he encounters Zephy, a powerful mage.");
            Utility.waitForSoundToComplete();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Utility.displayClearDelay();
        
        SkipButton.createSkipButton(); // Create the skip button

        Utility.printWithDelay(player.getName() + ": \n\"We'll see if you can keep up. Just don't get in my way.\"");
        Utility.printSpace(2);
        Utility.displayDelay(2);
        Utility.printWithDelay("Zephy" + ": \n\"Keep up? Watch yourself, human. I'll do more than that.\"");
        Utility.displayClearDelay();

        Utility.printWithDelay("The two form an uneasy alliance, and as they venture deeper into the Frosted Peaks, they come face-to-face with Thalios the Frost Giant.");
        Utility.printSpace(3);
        Utility.displayDelay(2);
        Enemy general1 = new Enemy(
            "Thalios", 
            600,  // Increased HP
            40, // Increased Attack Power
            80, // Increased Mana
            80,
            Arrays.asList(),
            "Thalios, a once-noble frost giant, was frozen for centuries until the Abyssal Lord revived him. Now, he wields the icy magic of the Frosted Peaks to destroy those who dare defy his master."
        );
        Utility.printWithDelay(general1.getName() + "\n\"Mortals. You seek to defy the Abyssal Lord? Foolish. The cold will claim you long before you reach him!\"");
        Utility.printSpace(3);
        Utility.displayDelay(2);
        Utility.printWithDelay(player.getName() + ": \n\"I've come too far to die here.\"");
        Utility.displayDelay(2);
        SkipButton.closeSkipButton(); // Close the skip button

        // Example of a battle in Act I
        if (!BattleScene.startBattle(player, general1, scan)) {
            return false;
        }

        Utility.displayClearDelay();
        // End of Act I
        Utility.displayAsciiArt("ACT I - Completed");
        Utility.displayDelay(2);

        // Proceed to Act II
        return startActTwo(player, scan);
    }

    public static boolean startActTwo(Player player, Scanner scan) {
        SkipButton.resetSkip(); // Reset the skip flag

        Utility.displayClearDelay();
        Utility.displayAsciiArt("ACT II - Meeting Draven");
        Utility.displayDelay(2);
        Utility.displayBoxedMessage("WORLD");
        Utility.displayAsciiArt("Abyssal Depths");
        try {
            Thread.sleep(2000); // 2 seconds delay
            Utility.playSound("Act2.wav");
            Utility.printWithDelay(
                "Scene: \n" +
                "After defeating Thalios, " + player.getName() + " and Zephy travel to the Abyssal Depths, a dark, twisted realm corrupted by the Abyssal Lord's magic. " +
                "As they navigate the shadows, they notice a figure lurking in the darkness - Draven.");
            Utility.waitForSoundToComplete();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Utility.displayClearDelay();
        SkipButton.createSkipButton(); // Create the skip button

        Utility.printWithDelay("Zephy" + ": \n\"Someone's following us...\"");
        Utility.printSpace(2);
        Utility.displayDelay(2);
        Utility.printWithDelay("Draven" + ": \n\"You're not as stealthy as you think, mage.\"");
        Utility.displayClearDelay();

        Utility.printWithDelay("The three form an uneasy alliance, and as they venture deeper into the Abyssal Depths, they come face-to-face with the Abyssal Lord's minions.");
        Utility.printSpace(3);
        Utility.displayDelay(2);
        Enemy general2 = new Enemy(
            "Abyssal Minion", 
            700, // Increased HP
            50, // Increased Attack Power
            100, // Increased Mana
            100,
            Arrays.asList(),
            "The Abyssal Minion, a creature of pure darkness, serves the Abyssal Lord with unwavering loyalty. Its power is unmatched, and it will stop at nothing to destroy those who oppose its master."
        );
        Utility.printWithDelay(general2.getName() + "\n\"You dare challenge the Abyssal Lord? Prepare to be consumed by darkness!\"");
        Utility.printSpace(3);
        Utility.displayDelay(2);
        Utility.printWithDelay(player.getName() + ": \n\"We won't back down. Not now, not ever.\"");
        Utility.displayDelay(2);

        SkipButton.closeSkipButton(); // Close the skip button

        // Example of a battle in Act II
        if (!BattleScene.startBattle(player, general2, scan)) {
            return false;
        }

        Utility.displayClearDelay();
        // End of Act II
        Utility.displayAsciiArt("ACT II - Completed");
        Utility.displayDelay(2);

        // Proceed to Act III
        return startActThree(player, scan);
    }

    public static boolean startActThree(Player player, Scanner scan) {
        SkipButton.resetSkip(); // Reset the skip flag

        Utility.displayClearDelay();
        Utility.displayAsciiArt("ACT III - The Final Battle");
        Utility.displayDelay(2);
        Utility.displayBoxedMessage("WORLD");
        Utility.displayAsciiArt("Abyssal Lord's Lair");
        try {
            Thread.sleep(2000); // 2 seconds delay
            Utility.playSound("Act3.wav");
            Utility.printWithDelay(
                "Scene: \n" +
                "After defeating the Abyssal Minion, " + player.getName() + ", Zephy, and Draven travel to the Abyssal Lord's Lair, the heart of darkness. " +
                "As they approach the throne room, they prepare for the final battle against the Abyssal Lord.");
            Utility.waitForSoundToComplete();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Utility.displayClearDelay();

        SkipButton.createSkipButton(); // Create the skip button

        Utility.printWithDelay(player.getName() + ": \n\"This is it. The final battle.\"");
        Utility.printSpace(2);
        Utility.displayDelay(2);
        Utility.printWithDelay("Zephy" + ": \n\"We must be careful. The Abyssal Lord is powerful.\"");
        Utility.displayClearDelay();

        Utility.printWithDelay("As they enter the throne room, they come face-to-face with the Abyssal Lord.");
        Utility.printSpace(3);
        Utility.displayDelay(2);
        Enemy abyssalLord = new Enemy(
            "Abyssal Lord", 8000, 150, 500, 500, Arrays.asList(),
            "The Abyssal Lord, a being of pure darkness, has brought chaos and destruction to the world. Its power is unmatched, and it will stop at nothing to destroy those who oppose it."
        );
        Utility.printWithDelay(abyssalLord.getName() + "\n\"You dare challenge me? Prepare to be destroyed!\"");
        Utility.printSpace(3);
        Utility.displayDelay(2);
        Utility.printWithDelay(player.getName() + ": \n\"We won't back down. Not now, not ever.\"");
        Utility.displayDelay(2);

        SkipButton.closeSkipButton(); // Close the skip button

        // Example of a battle in Act III
        if (!BattleScene.startBattle(player, abyssalLord, scan)) {
            return false;
        }

        Utility.displayClearDelay();
        // End of Act III
        Utility.displayAsciiArt("ACT III - Completed");
        Utility.displayDelay(2);

        // Proceed to Final Act
        return startActFinal(player, scan);
    }

    public static boolean startActFinal(Player player, Scanner scan) {
        SkipButton.resetSkip(); // Reset the skip flag

        Utility.displayClearDelay();
        Utility.displayAsciiArt("FINAL ACT - The End");
        Utility.displayDelay(2);
        Utility.displayBoxedMessage("WORLD");
        Utility.displayAsciiArt("Abyssal Lord's Lair");
        try {
            Thread.sleep(2000); // 2 seconds delay
            Utility.playSound("FinalAct.wav");
            Utility.printWithDelay(
                "Scene: \n" +
                "After defeating the Abyssal Lord, " + player.getName() + ", Zephy, and Draven stand victorious. " +
                "The world is saved, and peace is restored.");
            Utility.waitForSoundToComplete();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Utility.displayClearDelay();

        SkipButton.createSkipButton(); // Create the skip button

        Utility.printWithDelay(player.getName() + ": \n\"We did it. The world is safe.\"");
        Utility.printSpace(2);
        Utility.displayDelay(2);
        Utility.printWithDelay("Zephy" + ": \n\"Yes, but we must remain vigilant. There will always be new threats.\"");
        Utility.displayClearDelay();

        Utility.printWithDelay("As they leave the Abyssal Lord's Lair, they know that their journey is far from over.");
        Utility.printSpace(3);
        Utility.displayDelay(2);
        Utility.printWithDelay(player.getName() + ": \n\"We'll face whatever comes next, together.\"");
        Utility.displayDelay(2);

        SkipButton.closeSkipButton(); // Close the skip button

        Utility.displayClearDelay();
        // End of Final Act
        Utility.displayAsciiArt("THE END");
        Utility.displayDelay(2);

        return true;
    }
}