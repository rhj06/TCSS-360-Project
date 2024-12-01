package dungeongame.src.controller;

import dungeongame.src.model.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

/**
 * Manages a fight between a player and a monster.
 *
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 * @version 11/10/2024
 */
public class Arena {
    private final PropertyChangeSupport myPCS = new PropertyChangeSupport(this);
    private Player myPlayer;
    private AbstractMonster myMonster;
    private PlayerInventory myInventory;
    private int myPlayerMove;

    /**
     * Constructor for the arena.
     *
     * @param thePlayer the player that will be fighting.
     * @param theMonster the monster that will be fighting.
     */
    public Arena(final Player thePlayer, final AbstractMonster theMonster) {
        myPlayer = thePlayer;
        myMonster = theMonster;
        myInventory = PlayerInventory.getInstance();
        myPlayerMove = -1;
    }

    /**
     * Adds a PropertyChangeListener to listen for player input changes.
     *
     * @param listener the PropertyChangeListener.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        myPCS.addPropertyChangeListener(listener);
    }

    /**
     * A combat loop that will continue until one of the entities is dead.
     */
    public void combat(){
        Random rand = new Random();
        boolean playerTurn = ((AbstractDungeonCharacter)myPlayer).canAttack(myMonster.getSpeed());
        while(((AbstractDungeonCharacter)myPlayer).getHealth() != 0 || myMonster.getHealth() != 0){
            if(playerTurn){
                //needs a listener to set playerMove
                // Wait for player input via PropertyChangeListener
                while (myPlayerMove == -1) {
                    try {
                        wait(); // Wait until playerMove is updated.
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Combat loop interrupted!", e);
                    }
                }


                if(myPlayerMove == 0){
                    myMonster.setHealth(-((AbstractDungeonCharacter)myPlayer).getAttack());
                } else if(myPlayerMove == 1){
                    //Player inventory contains potions increase health
                } else if(myPlayerMove == 2){
                    if(myPlayer instanceof TargetedSpecial) {
                        ((TargetedSpecial)myPlayer).useTargetedSpecialAttack(myMonster);
                    } else {
                        ((AbstractDungeonCharacter)myPlayer).useSpecialAttack();
                    }
                } else if(myPlayerMove == 3){
                    myMonster.setHealth(-myMonster.getHealth());
                }
                myPlayerMove = -1;

            } else {
                if(myMonster.canHeal()){
                    myMonster.setHealth(myMonster.getMaxHealth()/10);
                }

                ((AbstractDungeonCharacter)myPlayer).setHealth(-myMonster.getAttack());

            }

            playerTurn = false;

        }
    }

    /**
     * Updates the player's move and notifies the combat loop.
     *
     * @param move the player's chosen move.
     */
    public void setPlayerMove(int move) {
        int oldMove = myPlayerMove;
        myPlayerMove = move;
        myPCS.firePropertyChange("playerMove", oldMove, move);
    }
}
