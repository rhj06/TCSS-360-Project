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

    public void startCombatLoop(){
        new Thread(this::combat).start();
    }

    /**
     * A combat loop that will continue until one of the entities is dead.
     */
    public void combat(){
        //Random rand = new Random();
        System.out.printf("Player: %d/%d HP | Monster: %d/%d HP\n",
                ((AbstractDungeonCharacter) myPlayer).getHealth(),
                ((AbstractDungeonCharacter) myPlayer).getMaxHealth(),
                myMonster.getHealth(),
                myMonster.getMaxHealth()
        );
        boolean playerTurn = ((AbstractDungeonCharacter)myPlayer).getSpeed() > myMonster.getSpeed();
        while(((AbstractDungeonCharacter)myPlayer).getHealth() > 0 && myMonster.getHealth() > 0){
            if(playerTurn){
                //needs a listener to set playerMove
                // Wait for player input via PropertyChangeListener
                while (myPlayerMove == -1) {
                    try {
                        synchronized (this){
                            System.out.println("Waiting for playerMove.");
                            wait(); // Wait until playerMove is updated.
                            System.out.println("Resumed after wait.");
                        }

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Combat loop interrupted!", e);
                    }
                }


                if(myPlayerMove == 0){
                    myMonster.changeHealth(-((AbstractDungeonCharacter)myPlayer).getAttack());
                    System.out.println("Player Attacked");
                } else if(myPlayerMove == 1){
                    //Player inventory contains potions increase health
                    System.out.println("Player Used Potion");
                } else if(myPlayerMove == 2){
                    if(myPlayer instanceof TargetedSpecial) {
                        ((TargetedSpecial)myPlayer).useTargetedSpecialAttack(myMonster);
                    } else {
                        ((AbstractDungeonCharacter)myPlayer).useSpecialAttack();
                    }
                    System.out.println("Player Used Special");
                } else if(myPlayerMove == 3){
                    myMonster.changeHealth(-myMonster.getHealth());
                    System.out.println("Player used debug command");
                }
                myPlayerMove = -1;
                playerTurn = false;

            } else {
                if(myMonster.canHeal()){
                    myMonster.changeHealth(myMonster.getMaxHealth()/10);
                    System.out.println("Monster Healed");
                }

                ((AbstractDungeonCharacter)myPlayer).changeHealth(-myMonster.getAttack());
                System.out.println("Monster Attacked");

                playerTurn = true;

            }

            System.out.printf("Player: %d/%d HP | Monster: %d/%d HP\n",
                    ((AbstractDungeonCharacter) myPlayer).getHealth(),
                    ((AbstractDungeonCharacter) myPlayer).getMaxHealth(),
                    myMonster.getHealth(),
                    myMonster.getMaxHealth()
            );
        }
    }

    /**
     * Updates the player's move and notifies the combat loop.
     *
     * @param theMove the player's chosen move.
     */
    public synchronized void setPlayerMove(int theMove) {
        myPlayerMove = theMove;
        notifyAll();
        System.out.println("Player Combat move set to " + theMove);

        //        int oldMove = myPlayerMove;
//        myPlayerMove = move;
//        myPCS.firePropertyChange("playerMove", oldMove, move);
    }
}
