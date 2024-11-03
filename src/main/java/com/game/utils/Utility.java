package com.game.utils;

import com.github.lalyos.jfiglet.FigletFont;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Utility {
    private static Clip clip;

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void displayDelay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            }          
        }

    public static void displayClearDelay() {
        try {
            Thread.sleep(2000);
            clearScreen();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void printWithDelay(String message) {
        for (char c : message.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void displayBoxedMessage(String message) {
        int length = message.length();
        
        // Unicode box-drawing characters
        char topLeft = '┌';
        char topRight = '┐';
        char bottomLeft = '└';
        char bottomRight = '┘';
        char horizontal = '─';
        char vertical = '│';
        
        // Top border
        System.out.print(topLeft);
        for (int i = 0; i < length; i++) {
            System.out.print(horizontal);
        }
        System.out.println(topRight);
        
        // Text with vertical borders
        System.out.println(vertical + message + vertical);
        
        // Bottom border
        System.out.print(bottomLeft);
        for (int i = 0; i < length; i++) {
            System.out.print(horizontal);
        }
        System.out.println(bottomRight);
    }

    public static void displayBoxedMessagesInline(String... messages) {
        int totalLength = 0;
        for (String message : messages) {
            totalLength += message.length() + 3; // Adding 3 for spacing and borders
        }
    
        // Unicode box-drawing characters
        char topLeft = '┌';
        char topRight = '┐';
        char bottomLeft = '└';
        char bottomRight = '┘';
        char horizontal = '─';
        char vertical = ' ';
    
        // Top border
        System.out.print(topLeft);
        for (int i = 0; i < totalLength; i++) {
            System.out.print(horizontal);
        }
        System.out.println(topRight);
    
        // Text with vertical borders
        System.out.print(vertical);
        for (String message : messages) {
            System.out.print(" " + message + " " + vertical);
        }
        System.out.println();
    
        // Bottom border
        System.out.print(bottomLeft);
        for (int i = 0; i < totalLength; i++) {
            System.out.print(horizontal);
        }
        System.out.println(bottomRight);
    }

    public static void displayAsciiArt(String text) {
        try {
            String asciiArt = FigletFont.convertOneLine(text);
            System.out.println(asciiArt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playSound(String soundFileName) {
        new Thread(() -> {
            try {
                URL soundFile = Utility.class.getResource("/sounds/" + soundFileName);
                if (soundFile == null) {
                    System.err.println("Sound file not found: " + soundFileName);
                    return;
                }
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}