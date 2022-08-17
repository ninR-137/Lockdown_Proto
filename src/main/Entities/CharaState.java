package main.Entities;

import static main.Constants.*;
import static main.Constants.soldier;

public class CharaState {
    /**
     * There are 2 roles
     * Active (Nurses and Doctors)
     * Passive (Tax payer and Soldiers) / (Nurses and Doctors -> when no enemies)
     */
    private int role;
    private int id;
    private int health, damage, attackSpeed, walkingSpeed;
    public CharaState(int id,int role){
        this.id = id;
        this.role = role;

        switch (id) {
            case 0 -> {
                health = 100;
                damage = 0;
                attackSpeed = 0;
                walkingSpeed = 0;
            }
            case 1 -> {
                health = 120;
                damage = 20;
                attackSpeed = 2;
                walkingSpeed = 0;
            }
            case 2 -> {
                health = 180;
                damage = 50;
                attackSpeed = 1;
                walkingSpeed = 0;
            }
            case 3 -> {
                health = 250;
                damage = 0;
                attackSpeed = 0;
                walkingSpeed = 0;
            }
        }
    }

    public void passiveActivity(){
        switch (id){
            case 0 -> {
                //Every 3 Seconds they will produce resources
            }
            default -> {
                //Do Nothing
                //Play Idle Animation
            }
        }
    }

    public void activeActivity(){
        switch (id){
            case 1 -> {
                //Attack
                //Attack recharge 4 seconds - 2
            }
            case 2 -> {
                //Attack
                //Attack recharge 4 seconds - 1
            }
        }
    }

    public int getRole() {
        return role;
    }
}
