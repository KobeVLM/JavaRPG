package com.game.worlds;

public class World {
    private String name;
    private String description;

    public World(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void explore() {
        System.out.println("Exploring " + name + ": " + description);
    }
}
