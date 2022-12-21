package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;

public class Tank {
    private Texture tank1;
    private Texture tank2;
    private int tankVal1;
    private int tankVal2;

    private int health;
    private int power;

    //constructor
    public Tank(int health, int power) {

        this.health = health;
        this.power = power;
    }

    //getters setters
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) { // update after every hit
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) { //updates with move
        this.power = power;
    }


    public Tank(Texture tank1, Texture tank2) {
        this.tank1 = tank1;
    }

    public int getTankVal1() {
        return tankVal1;
    }

    public int getTankVal2() {
        return tankVal2;
    }

    public void setTankVal2(int tankVal2) {
        this.tankVal2 = tankVal2;
    }

    public void setTankVal1(int tankVal1) {
        this.tankVal1 = tankVal1;
    }

    public Texture getTank1() {
        return tank1;
    }

    public void setTank1(Texture tank1) {
        this.tank1 = tank1;
    }

    public Texture getTank2() {
        return tank2;
    }

    public void setTank2(Texture tank2) {
        this.tank2 = tank2;
    }

}
