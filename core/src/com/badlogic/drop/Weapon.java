package com.badlogic.drop;

public class Weapon {
    private String type;
    private int cost;
    private int hitpoint;

    public Weapon(String type, int cost, int hitpoint) {
        this.type = type;
        this.cost = cost;
        this.hitpoint = hitpoint;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public void setHitpoint(int hitpoint) {
        this.hitpoint = hitpoint;
    }
}
