package dungeongame.src.controller;

import dungeongame.src.model.*;
import javafx.application.Platform;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

/**
 * Manages a fight between a player and a monster.
 *
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 * @version 11/10/2024
 */
public final class Arena {
    /** Constant of -1 */
    private static final int NEGATIVE_ONE = -1;

    /** Constant of 2 */
    private static final int TWO = 2;

    /** Constant of 3 */
    private static final int THREE = 3;

    /** Constant of 10 */
    private static final int TEN = 10;

    /** Constant of 500 */
    private static final int FIVE_HUNDRED = 500;

    /** Constant of 1000 */
    private static final int ONE_THOUSAND = 1000;

    /** Property change support to fire property change events. */
    private final PropertyChangeSupport myPCS;

    /** The player character. */
    private final Player myPlayer;

    /** The monster the player is fighting. */
    private final AbstractMonster myMonster;

    /** The inventory the player has. */
    private final PlayerInventory myInventory;

    /** An int representing the player's move choice. */
    private int myPlayerMove;

    /**
     * Constructor for the arena.
     *
     * @param thePlayer the player that will be fighting.
     * @param theMonster the monster that will be fighting.
     */
    public Arena(final Player thePlayer, final AbstractMonster theMonster) {
        if (thePlayer == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        if (theMonster == null) {
            throw new IllegalArgumentException("Monster cannot be null.");
        }

        myPCS = new PropertyChangeSupport(this);
        myPlayer = thePlayer;
        myMonster = theMonster;
        myInventory = PlayerInventory.getInstance();
        myPlayerMove = -1;
    }

    /**
     * Adds a property change listener to the arena.
     *
     * @param theListener the listener to be added.
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        if (theListener == null) {
            throw new IllegalArgumentException("Listener cannot be null.");
        }
        myPCS.addPropertyChangeListener(theListener);
    }

    /**
     * Fires a property change event, notifying listeners the monsters state has been changed.
     *
     * @param theMonsterState the true false value of the monster's isDead state.
     */
    public void monsterIsDead(final boolean theMonsterState){
        Platform.runLater(() -> myPCS.firePropertyChange("monsterIsDead", false, theMonsterState));
    }

    /**
     * Fires a property change event, notifying listeners the player's isDead state has been changed.
     *
     * @param thePlayerState the true false value of the player's isDead state.
     */
    public void playerIsDead(final boolean thePlayerState) {
        Platform.runLater(() -> myPCS.firePropertyChange("playerIsDead", false, thePlayerState));
        MazeTraverser.getInstance().firePlayerDeadEvent();
    }

    /**
     * Fires a property change event, notifying listeners that a message has been sent.
     *
     * @param theMessage message sent by the arena.
     */
    public void notifyMessage(final String theMessage) {
        Platform.runLater(() -> myPCS.firePropertyChange("message", null, theMessage));
    }

    /**
     * Creates a new thread a begins the combat loop.
     */
    public void startCombatLoop(){
        new Thread(this::combat).start();
    }

    /**
     * A combat loop that will continue until one of the entities is dead.
     */
    public void combat(){
        boolean playerTurn = ((AbstractDungeonCharacter)myPlayer).getSpeed() > myMonster.getSpeed();

        while((((AbstractDungeonCharacter)myPlayer).getHealth() > 0) && (myMonster.getHealth() > 0)) {
            try {
                Thread.sleep(FIVE_HUNDRED);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.printf("Player: %d/%d HP | Monster: %d/%d HP\n",
                    ((AbstractDungeonCharacter) myPlayer).getHealth(),
                    ((AbstractDungeonCharacter) myPlayer).getMaxHealth(),
                    myMonster.getHealth(),
                    myMonster.getMaxHealth()
            );

            if(playerTurn && ((AbstractDungeonCharacter) myPlayer).getHealth() != 0){
                while (myPlayerMove == NEGATIVE_ONE) {
                    try {
                        synchronized (this){
                            System.out.println("Waiting for playerMove.");
                            wait();
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
                    notifyMessage(myPlayer + " use a basic attacked for " + damage + " damage.");
                    myPlayerMove = NEGATIVE_ONE;
                    playerTurn = false;

                } else if(myPlayerMove == 1) {
                    if(!myInventory.containsItem(new HealthPotion())){
                        notifyMessage("Inventory does not contain a health potion.");
                        myPlayerMove = NEGATIVE_ONE;
                    } else {
                        myInventory.useItem(new HealthPotion());
                        System.out.println("Player used a health potion.");
                        notifyMessage(myPlayer + " used a health potion.");
                        myPlayerMove = NEGATIVE_ONE;
                        playerTurn = false;
                    }

                } else if(myPlayerMove == TWO){
                    if(myPlayer instanceof TargetedSpecial) {
                        ((TargetedSpecial)myPlayer).useTargetedSpecialAttack(myMonster);
                    } else {
                        ((AbstractDungeonCharacter)myPlayer).useSpecialAttack();
                    }
                    System.out.println("Player Used Special");
                    notifyMessage(myPlayer + " used a special ability.");
                    myPlayerMove = NEGATIVE_ONE;
                    playerTurn = false;

                } else if(myPlayerMove == THREE){
                    myMonster.changeHealth(-myMonster.getHealth());
                    System.out.println("Player used debug command");
                    notifyMessage(myPlayer + " used debug command attack.");
                    myPlayerMove = NEGATIVE_ONE;
                    playerTurn = false;
                }

            }

            try {
                Thread.sleep(FIVE_HUNDRED);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (!playerTurn && myMonster.getHealth() != 0){
                if(myMonster.canHeal()){
                    myMonster.changeHealth(myMonster.getMaxHealth()/TEN);
                    System.out.println("Monster healed " + myMonster.getMaxHealth()/TEN + " health.");
                    notifyMessage(myMonster + " healed " + myMonster.getMaxHealth()/TEN + " health.");
                }

                int attack = myMonster.getAttack();
                int damage = Math.max(attack - ((AbstractDungeonCharacter)myPlayer).getDefense(), 0);
                ((AbstractDungeonCharacter)myPlayer).changeHealth(-damage);
                System.out.println("Monster Attacked");
                notifyMessage(myMonster + " attacked dealing " + damage + " damage.");

                if(((AbstractDungeonCharacter)myPlayer).getHealth() > 0) {
                    playerTurn = true;
                }

                try {
                    Thread.sleep(FIVE_HUNDRED);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (myMonster.getHealth() == 0) {
            System.out.println(myMonster + " is dead");
            notifyMessage(myMonster + " is dead.");

            int playerI = Maze.getInstance().getPlayerCords().y;
            int playerJ = Maze.getInstance().getPlayerCords().x;
            Maze.getInstance().setRoomMonster(playerI, playerJ, null);

            if (myMonster instanceof Boss && Objects.equals(((Boss) myMonster).getType(), "Dragon")) {
                Platform.runLater(() -> myPCS.firePropertyChange("final_boss_killed", false, true));
            }

            monsterIsDead(true);

            if (Math.random() < myMonster.getItemDropRate()) {
                System.out.println(myMonster + " dropped an item.");
                notifyMessage(myMonster + " dropped an item.");
                Item item = myMonster.getRandomItem();
                myInventory.addItem(item);
            }
        }
        else {
            System.out.println("Game Over");
            notifyMessage(myPlayer + " is dead. Game over.");

            Platform.runLater(() -> playerIsDead(true));
            try {
                Thread.sleep(ONE_THOUSAND);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            playerIsDead(true);
        }
    }

    /**
     * Updates the player's move and notifies the combat loop.
     *
     * @param theMove the player's chosen move.
     */
    public synchronized void setPlayerMove(final int theMove) {
        myPlayerMove = theMove;
        notifyAll();
        System.out.println("Player Combat move set to " + theMove);
    }

}
