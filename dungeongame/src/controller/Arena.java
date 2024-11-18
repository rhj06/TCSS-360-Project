package dungeongame.src.controller;

import dungeongame.src.model.*;

import java.util.Random;

/**
 * Manages a fight between a player and a monster.
 *
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 * @version 11/10/2024
 */
public class Arena {
    Player myPlayer;
    AbstractMonster myMonster;
    PlayerInventory myInventory;

    /**
     * Constructor for the arena.
     *
     * @param thePlayer the player that will be fighting.
     * @param theMonster the monster that will be fighting.
     */
    public Arena(final Player thePlayer, final AbstractMonster theMonster, final PlayerInventory theInventory) {
        myPlayer = thePlayer;
        myMonster = theMonster;
        myInventory = theInventory;

    }

    /**
     * A combat loop that will continue until one of the entities is dead.
     */
    public void combat(){
        Random rand = new Random();
        boolean playerTurn = ((AbstractDungeonCharacter)myPlayer).canAttack(myMonster.getSpeed());
        while(((AbstractDungeonCharacter)myPlayer).getHealth() != 0 || myMonster.getHealth() != 0){
            int playerMove = 0;
            if(playerTurn){
                //needs a listener to set playerMove
                if(playerMove == 0){
                    myMonster.setHealth(-((AbstractDungeonCharacter)myPlayer).getAttack());
                } else if(playerMove == 1){
                    //Player inventory contains potions increase health
                } else if(playerMove == 2){
                    if(myPlayer instanceof TargetedSpecial) {
                        ((TargetedSpecial)myPlayer).useTargetedSpecialAttack(myMonster);
                    } else {
                        ((AbstractDungeonCharacter)myPlayer).useSpecialAttack();
                    }
                } else if(playerMove == 3){
                    myMonster.setHealth(-myMonster.getHealth());
                }

            } else {
                if(myMonster.canHeal()){
                    myMonster.setHealth(myMonster.getMaxHealth()/10);
                }

                ((AbstractDungeonCharacter)myPlayer).setHealth(-myMonster.getAttack());

            }

            playerTurn = false;

        }
    }
}
