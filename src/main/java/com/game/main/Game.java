package com.game.main;

import java.util.Arrays;
import com.game.scenes.*;
import com.game.utils.Utility;
import com.game.worlds.EmberIsles;
import com.game.characters.Player;
import com.game.characters.Enemy;
import com.game.skills.*;
import java.util.Scanner;


@SuppressWarnings("unused")
public class Game {
    public static boolean prologue(Player player, Scanner scan) {
        player.heal(100); // Example of healing the player after a battle

        Utility.displayClearDelay();
        Utility.displayAsciiArt(player.getName() +" Journey Begins ");
        Utility.displayDelay(2);
        Utility.displayBoxedMessage("Starting World");
        Utility.displayAsciiArt("Ember Isles");
        try {
            Thread.sleep(2000); // 2 seconds delay
            Utility.printWithDelay(
                "Scene: \n" + 
                player.getName() + 
                " stands alone at the edge of the Ember Isles, looking out over the lava flows with a hardened expression. \n " + 
                "His thoughts are consumed with vengeance, the Abyssal Lord's creatures responsible for his family's death.\n "+ 
                "The wind howls, carrying ash and embers as " + player.getName() + " begins his journey.");
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
        50, 
        10, 
        20, 
        20, 
        Arrays.asList(), 
        "Spawned from the Ember Isles' molten depths, the Lava Fiend is a fiery creature infused with dark magic. " +
        "Guarding hidden treasures, its cunning and agility make it a dangerous adversary despite its small size."
        );

        try {
            Thread.sleep(2000); // 2 seconds delay
            Utility.clearScreen();
            Utility.printWithDelay(player.getName() + " presses forward, encountering a group of " + lavafiend.getName() + "s.");
            Thread.sleep(1500); // 1.5 seconds delay
            Utility.printSpace(3);
            Utility.printWithDelay(player.getName() + ": \n\"Creatures of the Abyss... You'll be the first to fall.\"");
            Thread.sleep(1500); // 1.5 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Example of a battle in Prolugue
        
        if (!BattleScene.startBattle(player, lavafiend, scan)) {
            return false;
        }

        // Dialogue
        try {
            Thread.sleep(200); // 2 seconds delay
            Utility.clearScreen();
            Utility.printWithDelay("After the fight, " + player.getName() +" stands among the ashes of the defeated enemies, breathing heavily.");
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

        Utility.displayClearDelay();
        Utility.displayAsciiArt("ACT I " +"- Meeting Zephy");
        Utility.displayDelay(2);
        Utility.displayBoxedMessage("WORLD");
        Utility.displayAsciiArt("Frosted Peaks");
        try {
            Thread.sleep(2000); // 2 seconds delay
            Utility.printWithDelay(
                "Scene: \n" + 
                player.getName() + 
                " reaches the frozen landscape of the Frosted Peaks. The bitter cold contrasts with the fiery wrath that drives him forward. " + 
                "As he makes his way through the snow, he notices a figure in the distance, an Elf moving gracefully across the ice.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Utility.displayClearDelay();
        
        Utility.printWithDelay(player.getName() + ": \n\"Who's that? Another traveler?\"\n");

        Utility.displayDelay(2);

        Utility.printWithDelay("\nAs " + player.getName() + " approaches, Zephy turns to face him, her eyes sharp but weary. She stands poised, ready for anything.");

        Utility.displayClearDelay();

        Utility.printWithDelay(player.getName() + ": \n\"You don't look like the creatures I'm used to fighting. You're not with the Abyssal Lord, are you?\"");
        Utility.printSpace(3);
        Utility.printWithDelay("Zephy: \n\"I'm not with anyone but myself. But you... you have the look of someone seeking vengeance.\"");

        Utility.displayDelay(2);
        
        System.out.println("\n\n\nChoose your response:");
        System.out.println("[1] \"I'm here to kill the Abyssal Lord.\"  ");
        System.out.println("[2] \"None of your business, elf.\" \n");
        System.out.print("Choice: ");
        int choice = scan.nextInt();

        if (choice == 1){
            Utility.displayClearDelay();
            Utility.printWithDelay(player.getName() + ": \n\"The Abyssal Lord is responsible for the death of my family. I'm going to end him.\"");
            Utility.printSpace(2);
            Utility.displayDelay(2);
            Utility.printWithDelay("Zephy" + ": \n\"Then we have something in common. He destroyed my kingdom, twisted my people. I can't let him continue.\"");
            Utility.displayClearDelay();
            Utility.printWithDelay(player.getName() + ": \n\"We'll see if you can keep up. Just don't get in my way.\"");
            Utility.printSpace(2);
            Utility.displayDelay(2);
            Utility.printWithDelay("Zephy" + ": \n\"Keep up? Watch yourself, human. I'll do more than that.\"");   
            Utility.displayClearDelay(); 
        }

        Utility.printWithDelay("The two form an uneasy alliance, and as they venture deeper into the Frosted Peaks, they come face-to-face with Thalios the Frost Giant.");
        Utility.printSpace(3);
        Utility.displayDelay(2);
        Enemy general1 = new Enemy("Thalios", 400, 40, 100, 100, Arrays.asList(), 
        "Thalios, a once-noble frost giant, was frozen for centuries until the Abyssal Lord revived him. Now, he wields the icy magic of the Frosted Peaks to destroy those who dare defy his master."
        );
        Utility.printWithDelay(general1.getName() + "\n\"Mortals. You seek to defy the Abyssal Lord? Foolish. The cold will claim you long before you reach him!\"");
        Utility.printSpace(3);
        Utility.displayDelay(2);
        Utility.printWithDelay(player.getName() + ": \n\"I've come too far to die here.\"");
        Utility.displayDelay(2);
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

        Utility.displayClearDelay();
        Utility.displayAsciiArt("ACT II " + "- Meeting Draven");
        Utility.displayDelay(2);
        Utility.displayBoxedMessage("WORLD");
        Utility.displayAsciiArt("Abyssal Depths");
        try {
            Thread.sleep(2000); // 2 seconds delay
            Utility.printWithDelay(
                "Scene: \n" + 
                "After defeating Thalios, " + player.getName() + " and Zephy travel to the Abyssal Depths, a dark, twisted realm corrupted by the Abyssal Lord's magic. " + 
                " As they navigate the shadows, they notice a figure lurking in the darkness - Draven.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Utility.displayClearDelay();
        
        Utility.printWithDelay("Zephy" + ": \n\"Someone's following us...\"");
        Utility.printSpace(3);
        Utility.printWithDelay("Draven steps out from the shadows, twirling a dagger between his fingers.");
        Utility.displayClearDelay();
        Utility.printWithDelay("Draven" + ": \n\"Looks like you two could use some help. I've been tracking you for a while now.\"");
        Utility.printSpace(3);
        Utility.printWithDelay(player.getName() + ": \n\"We don't need your help.\"");
        Utility.displayClearDelay();
        Utility.printWithDelay("Draven" + ": \n\"Maybe not, but you'll take it if you want to survive down here. The Abyssal Lord's minions are getting stronger.\"");

        System.out.println("\n\n\nChoose your response:");
        System.out.println("[1] \"Fine, but I don't trust you.\"  ");
        System.out.println("[2] \"I don't have time for games, rogue.\" \n");
        System.out.print("Choice: ");
        int choice = scan.nextInt();

        if (choice == 1){
            Utility.displayClearDelay();
            Utility.printWithDelay(player.getName() + ": \n\"Fine. You'd better pull your weight.\"");
            Utility.printSpace(2);
            Utility.printWithDelay("Draven" + ": \n\"Trust me, you'll want me around when things get messy. And it's about to get messy.\"");
            Utility.displayClearDelay();
        }
        // Example of a battle in Act II
        Enemy seaCreature = new Enemy("Sea Creatures", 200, 40, 80, 80, Arrays.asList(), "A colossal giant.");
        if (!BattleScene.startBattle(player, seaCreature, scan)) {
            return false;
        }

        Utility.displayClearDelay();
        // End of Act II
        Utility.displayAsciiArt("ACT II - Completed");
        Utility.displayDelay(2);

        // Proceed to Final Act
        return startActThree(player, scan);
    }

    public static boolean startActThree(Player player, Scanner scan) {
        //Heal player after the battle
        player.heal(1000);
        player.setMaxHealth(player.getHealth());


        // Clear screen and display the opening for Act III
        Utility.displayClearDelay();
        Utility.displayAsciiArt("ACT III " + "- Return to the Frosted Peaks");
        Utility.displayDelay(2);
        Utility.displayBoxedMessage("WORLD");
        Utility.displayAsciiArt("Frosted Peaks");
        
        // Scene setup
        try {
            Thread.sleep(2000); // 2 seconds delay
            Utility.printWithDelay(
                "Scene: \n" + 
                player.getName() + ", Zephy, and Draven find themselves overwhelmed by the Abyssal Lord's power. " +
                "Realizing they're not strong enough to defeat him alone, they decide to return to the Frosted Peaks to seek help. " + 
                "In the sacred Silver Citadel, they find Seraphina, a paladin chosen by the Radiant Goddess to defend the realm."
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Utility.displayClearDelay();
        
        // Dialogue with Seraphina
        Utility.printWithDelay("Seraphina" + ": \n\"You three look worse for wear. What brings you to the Citadel?\"");
        Utility.printSpace(3);
        Utility.printWithDelay(player.getName() + ": \n\"We need your help. The Abyssal Lord is too strong for the three of us. Join us, and together we'll bring him down.\"");
        Utility.displayClearDelay();
        Utility.printWithDelay("Seraphina" + ": \n\"The Abyssal Lord's corruption spreads through these lands... My goddess has given me a sacred duty to stop it. If you fight for the light, I'll stand with you.\"");
        Utility.printSpace(3);
        Utility.printWithDelay("Zephy" + ": \n\"We could use your healing, Paladin. The Abyssal Lord's forces are relentless.\"");
        Utility.displayClearDelay();
        Utility.printWithDelay("Draven" + ": \n\"Let's hope your faith is as strong as your sword.\"");
        Utility.printSpace(3);
        Utility.printWithDelay("Seraphina" + ": \n\"Stronger.\"");
        Utility.displayClearDelay();
    
        // Setting up the final battle
        System.out.println("\n\n\nThe four heroes unite, each bringing their unique skills to the final battle ahead. They prepare to face the Abyssal Lord once and for all.");
        Utility.displayClearDelay();
    
        // End of Act III
        Utility.displayAsciiArt("ACT III - Completed");
        Utility.displayDelay(2);
        

        // Proceed to the final act
        return startActFinal(player, scan);
    }
    

    public static boolean startActFinal(Player player, Scanner scan) {

        // Display the introduction for the final act
        Utility.displayClearDelay();
        Utility.displayAsciiArt("FINAL ACT - The Abyssal Lord Showdown");
        Utility.displayDelay(2);
        Utility.displayBoxedMessage("WORLD");
        Utility.displayAsciiArt("Abyssal Depths - The Lair");
    
        // Scene setup and Abyssal Lord dialogue
        try {
            Thread.sleep(2000); // 2 seconds delay
            Utility.printWithDelay(
                "Scene: \n" + 
                "The group confronts the Abyssal Lord in his lair, deep within the Abyssal Depths. " +
                "The Abyssal Lord's form is massive, surrounded by swirling dark waters, and his voice echoes with malevolent power."
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Utility.displayClearDelay();
        
        // Abyssal Lord dialogue and heroes' responses
        Utility.printWithDelay("Abyssal Lord" + ": \n\"You've gathered your little band of heroes... but it will make no difference. You will all drown in despair.\"");
        Utility.printSpace(3);
        Utility.printWithDelay(player.getName() + ": \n\"Not today.\"");
        Utility.printSpace(2);
        Utility.printWithDelay("Zephy" + ": \n\"Your corruption ends here.\"");
        Utility.printSpace(2);
        Utility.printWithDelay("Draven" + ": \n\"I'll enjoy taking you down.\"");
        Utility.printSpace(2);
        Utility.printWithDelay("Seraphina" + ": \n\"By the light of the Radiant Goddess, we will defeat you.\"");
        Utility.displayClearDelay();  
        Utility.printWithDelay("The final battle begins. The Abyssal Lord unleashes his powerful abilities, and the heroes must work together to bring him down.");
        Utility.displayDelay(1);
        // Start the final battle 
        Enemy abyssalLord = new Enemy("Abyssal Lord", 1000, 80, 150, 150, Arrays.asList(), "The dark ruler of the Abyssal Depths.");
        if (!BattleScene.startBattle(player, abyssalLord, scan)) {
            return false; // Player lost the battle
        }

        Utility.displayClearDelay();
        Utility.printWithDelay("As the Abyssal Lord prepares his ultimate attack, Maelstrom of Despair, the team must combine their strength.");
        Utility.printSpace(2);

        System.out.println("\n\n\nChoose your response:\n");
        System.out.println("[1] \"Focus all attacks on the Abyssal Lord!\"  ");
        System.out.println("[2] \"Defend and heal through the Maelstrom!\" \n");
        System.out.print("Choice: ");
        int choice = scan.nextInt();

        // Ending dialogue between characters
        Utility.displayClearDelay();
        Utility.printSpace(2);
        Utility.printWithDelay("Benimaru: \n\"It's over... Finally.\"");
        Utility.printSpace(2);
        Utility.printWithDelay("Zephy: \n\"The world can heal now.\"");
        Utility.printSpace(2);
        Utility.printWithDelay("Draven: \n\"Not bad for a ragtag group of misfits.\"");
        Utility.printSpace(2);
        Utility.printWithDelay("Seraphina: \n\"The light has prevailed... for now.\"\n");

        return true; // Player won the final battle
    }
}
