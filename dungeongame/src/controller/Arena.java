package dungeongame.src.controller;

import dungeongame.src.model.*;
import javafx.application.Platform;

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
    private final PropertyChangeSupport myPCS;
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
        myPCS = new PropertyChangeSupport(this);
        myPlayer = thePlayer;
        myMonster = theMonster;
        myInventory = PlayerInventory.getInstance();
        myPlayerMove = -1;
    }

    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }

    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.removePropertyChangeListener(theListener);
    }

    public void monsterIsDead(boolean theMonsterState){
        Platform.runLater(() -> {
            myPCS.firePropertyChange("monsterIsDead", false, theMonsterState);
        });
    }

    public void notifyMessage(String theMessage) {
        //Platform.runLater(() -> addMessage(message));
        Platform.runLater(() -> {
            myPCS.firePropertyChange("message", null, theMessage);
        });
    }

    public void startCombatLoop(){
        new Thread(this::combat).start();
    }

    /**
     * A combat loop that will continue until one of the entities is dead.
     */
    public void combat(){
        //Random rand = new Random();
        boolean playerTurn = ((AbstractDungeonCharacter)myPlayer).getSpeed() > myMonster.getSpeed();

        while((((AbstractDungeonCharacter)myPlayer).getHealth() > 0) && (myMonster.getHealth() > 0)) {
            System.out.printf("Player: %d/%d HP | Monster: %d/%d HP\n",
                    ((AbstractDungeonCharacter) myPlayer).getHealth(),
                    ((AbstractDungeonCharacter) myPlayer).getMaxHealth(),
                    myMonster.getHealth(),
                    myMonster.getMaxHealth()
            );

            if(playerTurn){
                //needs a listener to set playerMove
                // Wait for player input via PropertyChangeListener
                while (myPlayerMove == -1) {
                    try {
                        synchronized (this){
                            System.out.println("Waiting for playerMove.");
                            wait(); // Wait until playerMove is updated.
                            //System.out.println("Resumed after wait.");
                        }

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Combat loop interrupted!", e);
                    }
                }

                if(myPlayerMove == 0) {
                    int attack = ((AbstractDungeonCharacter)myPlayer).getAttack();
                    int damage = Math.max(attack - myMonster.getDefense(), 0);
                    myMonster.changeHealth(-damage);
                    System.out.println("Player Attacked");
                    notifyMessage(myPlayer.toString() + " use a basic attacked for " + damage + " damage.");
                    myPlayerMove = -1;
                    playerTurn = false;

                } else if(myPlayerMove == 1) {
                    if(!myInventory.containsItem(new HealthPotion())){
                        notifyMessage("Inventory does not contain a health potion.");
                        myPlayerMove = -1;
                    } else {
                        //Player inventory contains potions increase health
                        myInventory.useItem(new HealthPotion());
                        System.out.println("Player used a health potion.");
                        notifyMessage(myPlayer.toString() + " used a health potion.");
                        myPlayerMove = -1;
                        playerTurn = false;
                    }

                } else if(myPlayerMove == 2){
                    if(myPlayer instanceof TargetedSpecial) {
                        ((TargetedSpecial)myPlayer).useTargetedSpecialAttack(myMonster);
                    } else {
                        ((AbstractDungeonCharacter)myPlayer).useSpecialAttack();
                    }
                    System.out.println("Player Used Special");
                    notifyMessage(myPlayer.toString() + " used a special ability.");
                    myPlayerMove = -1;
                    playerTurn = false;

                } else if(myPlayerMove == 3){
                    myMonster.changeHealth(-myMonster.getHealth());
                    System.out.println("Player used debug command");
                    notifyMessage(myPlayer.toString() + " used debug command attack.");
                    myPlayerMove = -1;
                    playerTurn = false;
                }

            } else if (myMonster.getHealth() > 0){
                if(myMonster.canHeal()){
                    myMonster.changeHealth(myMonster.getMaxHealth()/10);
                    System.out.println("Monster healed " + myMonster.getMaxHealth()/10 + " health.");
                    notifyMessage(myMonster.toString() + " healed " + myMonster.getMaxHealth()/10 + " health.");
                }

                int attack = myMonster.getAttack();
                int damage = Math.max(attack - ((AbstractDungeonCharacter)myPlayer).getDefense(), 0);
                ((AbstractDungeonCharacter)myPlayer).changeHealth(-damage);
                System.out.println("Monster Attacked");
                notifyMessage(myMonster.toString() + " attacked dealing " + damage + " damage.");
                Platform.runLater(() -> {
                    if (((AbstractDungeonCharacter)myPlayer).getHealth() <= 0) {
                        System.out.println("Game Over");
                        notifyMessage(myPlayer.toString() + " is dead. Game over.");
                        myPCS.firePropertyChange("player_killed", null, null);
                    }
                });


                playerTurn = true;
            }
        }

        if(myMonster.getHealth() == 0) {
            System.out.println(myMonster.toString() + " is dead");
            notifyMessage(myMonster.toString() + " is dead.");
            int playerI = Maze.getInstance().getPlayerCords().y;
            int playerJ = Maze.getInstance().getPlayerCords().x;
            Maze.getInstance().setRoomMonster(playerI, playerJ, null);

            if(myMonster instanceof Boss) {
                if(((Boss)myMonster).getType() == "Dragon") {
                    Platform.runLater(() -> {
                        myPCS.firePropertyChange("final_boss_killed", null, null);
                    });
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            monsterIsDead(true);

            if(Math.random() < myMonster.getItemDropRate()){
                System.out.println(myMonster.toString() + " dropped an item.");
                notifyMessage(myMonster.toString() + " dropped an item.");
                Item item = myMonster.getRandomItem();
                myInventory.addItem(item);
            }
        }
//        else {
//            System.out.println("Game Over");
//            notifyMessage(myPlayer.toString() + " is dead. Game over.");
//            Platform.runLater(() -> {
//                myPCS.firePropertyChange("player_killed", null, null);
//            });
//
//        }

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

    }
}
