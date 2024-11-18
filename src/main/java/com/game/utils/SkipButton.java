package com.game.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkipButton {
    private static boolean skip = false;
    private static JFrame frame;

    public static boolean isSkip() {
        return skip;
    }

    public static void resetSkip() {
        skip = false;
    }

    public static void createSkipButton() {
        if (frame == null) {
            frame = new JFrame("Skip Button");
            JButton skipButton = new JButton("Skip");
            skipButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    skip = true;
                }
            });

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 100);
            frame.setLayout(new java.awt.FlowLayout());
            frame.add(skipButton);
            frame.setVisible(true);
            frame.toFront();
            frame.requestFocus();
        }
    }

    public static void closeSkipButton() {
        if (frame != null) {
            frame.dispose();
            frame = null;
        }
    }
}