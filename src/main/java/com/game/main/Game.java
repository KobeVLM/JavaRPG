package com.game.main;

import java.util.Arrays;
import com.game.scenes.*;
import com.game.utils.Utility;
import com.game.characters.Player;
import com.game.characters.Enemy;
import com.game.skills.*;
import java.util.Scanner;

public class Game {
    public static boolean startActOne(Player player, Scanner scan) {
        player.heal(1000); // Example of healing the player after a battle

        Utility.displayClearDelay();
        Utility.displayAsciiArt("ACT I");
        Utility.displayDelay(2);

        // Example of a battle in Act I
        Enemy goblin = new Enemy("Goblin", 50, 10, 20, 20, Arrays.asList(), "A weak but cunning creature.");
        if (!BattleScene.startBattle(player, goblin, scan)) {
            return false;
        }

        Utility.displayClearDelay();
        // Continue with the story progression
        Utility.displayAsciiArt("ACT I - Part 2");
        Utility.displayDelay(2);

        // Another example of a battle in Act I
        Enemy orc = new Enemy("Orc", 80, 15, 30, 30, Arrays.asList(), "A strong and fierce warrior.");
        if (!BattleScene.startBattle(player, orc, scan)) {
            return false;
        }

        Utility.displayClearDelay();
        // Continue with the story progression
        Utility.displayAsciiArt("ACT I - Part 3");
        Utility.displayDelay(2);

        // Final battle in Act I
        Enemy general = new Enemy("General", 120, 20, 40, 40, Arrays.asList(), "A powerful general.");
        if (!BattleScene.startBattle(player, general, scan)) {
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
        player.heal(1000); // Example of healing the player after a battle

        Utility.displayClearDelay();
        Utility.displayAsciiArt("ACT II");
        Utility.displayDelay(2);

        // Example of a battle in Act II
        Enemy dragon = new Enemy("Dragon", 200, 25, 50, 50, Arrays.asList(), "A legendary beast.");
        if (!BattleScene.startBattle(player, dragon, scan)) {
            return false;
        }

        Utility.displayClearDelay();
        // Continue with the story progression
        Utility.displayAsciiArt("ACT II - Part 2");
        Utility.displayDelay(2);

        // Another example of a battle in Act II
        Enemy demon = new Enemy("Demon", 250, 30, 60, 60, Arrays.asList(), "A dark and powerful entity.");
        if (!BattleScene.startBattle(player, demon, scan)) {
            return false;
        }

        Utility.displayClearDelay();
        // Continue with the story progression
        Utility.displayAsciiArt("ACT II - Part 3");
        Utility.displayDelay(2);

        // Final battle in Act II
        Enemy darkLord = new Enemy("Dark Lord", 300, 35, 70, 70, Arrays.asList(), "The ultimate evil.");
        if (!BattleScene.startBattle(player, darkLord, scan)) {
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
        player.heal(10000); // Example of healing the player after a battle

        Utility.displayClearDelay();
        Utility.displayAsciiArt("ACT III");
        Utility.displayDelay(2);

        // Example of a battle in Act III
        Enemy titan = new Enemy("Titan", 400, 40, 80, 80, Arrays.asList(), "A colossal giant.");
        if (!BattleScene.startBattle(player, titan, scan)) {
            return false;
        }

        Utility.displayClearDelay();
        // Continue with the story progression
        Utility.displayAsciiArt("ACT III - Part 2");
        Utility.displayDelay(2);

        // Another example of a battle in Act III
        Enemy god = new Enemy("God", 500, 45, 90, 90, Arrays.asList(), "An almighty deity.");
        if (!BattleScene.startBattle(player, god, scan)) {
            return false;
        }

        Utility.displayClearDelay();
        // Continue with the story progression
        Utility.displayAsciiArt("ACT III - Part 3");
        Utility.displayDelay(2);

        // Final battle in Act III
        Enemy creator = new Enemy("Creator", 1000, 50, 100, 100, Arrays.asList(), "The creator of all.");
        if (!BattleScene.startBattle(player, creator, scan)) {
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
        player.heal(50000); // Example of healing the player after a battle

        Utility.displayClearDelay();
        Utility.displayAsciiArt("FINAL ACT");
        Utility.displayDelay(2);

        // Final boss battle
        Enemy finalBoss = new Enemy("Final Boss", 5000, 100, 200, 200, Arrays.asList(), "The ultimate challenge.");
        if (!BattleScene.startBattle(player, finalBoss, scan)) {
            return false;
        }

        Utility.displayClearDelay();
        // End of the game
        Utility.displayAsciiArt("GAME COMPLETED");
        Utility.displayDelay(2);
        return true;
    }
}
