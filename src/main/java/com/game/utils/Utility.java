package com.game.utils;

import com.game.main.Main;
import com.github.lalyos.jfiglet.FigletFont;

import javax.sound.sampled.*;
import javax.swing.JFrame;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Utility {
    private static Clip clip;
    private static boolean spacebarPressed = false;

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
        SkipButton.resetSkip(); // Reset the skip flag
        StringBuilder output = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (SkipButton.isSkip()) {
                // If skip is triggered, print the rest of the string instantly
                System.out.println(message.substring(output.length()));
                return;
            }
            output.append(c);
            System.out.print(c);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
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
        try {
            URL soundFile = Main.class.getResource("/sounds/" + soundFileName);
            if (soundFile == null) {
                System.err.println("Sound file not found: " + soundFileName);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.err.println("Audio format not supported: " + format);
                return;
            }

            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void waitForSoundToComplete() {
        if (clip != null) {
            final Object lock = new Object();
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    synchronized (lock) {
                        lock.notify();
                    }
                }
            });
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public static void printSpace(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println();
        }
    }

    public static void barrier() {
        System.out.println("===============================================================================================================");
    }
}