package com.game.characters;

import java.util.List;

public class NPC {
    private String name;
    private String backstory;
    private List<String> dialogues;

    public NPC(String name, String backstory, List<String> dialogues) {
        this.name = name;
        this.backstory = backstory;
        this.dialogues = dialogues;
    }

    public String getName() {
        return name;
    }

    public String getBackstory() {
        return backstory;
    }

    public List<String> getDialogues() {
        return dialogues;
    }

    public void displayCharacterInfo() {
        System.out.println("NPC Name: " + name);
        System.out.println("Backstory: " + backstory);
        System.out.println("Dialogues:");
        for (String dialogue : dialogues) {
            System.out.println(" - " + dialogue);
        }
    }

    public void speak() {
        for (String dialogue : dialogues) {
            System.out.println(name + ": " + dialogue);
        }
    }
}
