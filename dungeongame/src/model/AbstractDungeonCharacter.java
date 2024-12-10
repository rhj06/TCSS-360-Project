package dungeongame.src.model;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;
import java.util.Locale;
import java.util.Random;

/**
 * Abstract Class for Dungeon Character
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 * @version 1.0
 */
public abstract class AbstractDungeonCharacter implements Character, Serializable {
    @Serial
    private static final long serialVersionUID = 1351355L;

    /** Default Name */
    private static final String DEFAULT_NAME = "Dungeon Character";
    /** Communicate CurrentHealth with GUI */
    private transient final IntegerProperty myCurHealthProperty = new SimpleIntegerProperty();
    /** Property Change Listener */
    private final PropertyChangeSupport myPCS;
    /** My Maximum Health */
    private final int myMaxHealth;
    /** My Minimum possible attack */
    private final int myMinAttack;
    /** my maximum possible attack */
    private final int myMaxAttack;
    /** my defense for incoming attacks */
    private final int myDefense;
    /** Name of Character */
    private final String myName;
    /** my Current Health */
    private int myCurrHealth;
    /** My minimum speed */
    private int myMinSpeed;
    /** My maximum speed */
    private int myMaxSpeed;


    /**
     * Abstract Class for a Dungeon Character
     * @param theMaxHealth The Max Health
     * @param theMinAttack The Min Attack
     * @param theMaxAttack The Max Attack
     * @param theMinSpeed the Min Speed
     * @param theMaxSpeed The Max Speed
     * @param theDefense The Defence
     * @param theName The Name
     */
    public AbstractDungeonCharacter(final int theMaxHealth,
                                     final int theMinAttack, final int theMaxAttack,
                                    final int theMinSpeed, final int theMaxSpeed, final int theDefense, final String theName) {

        myPCS = new PropertyChangeSupport(this);
        myMaxHealth = theMaxHealth;
        myCurrHealth = theMaxHealth;
        myCurHealthProperty.set(myCurrHealth);
        myMinAttack = theMinAttack;
        myMaxAttack = theMaxAttack;
        myMinSpeed = theMinSpeed;
        myMaxSpeed = theMaxSpeed;
        myDefense = theDefense;
        myName = theName;
    }

    public PropertyChangeSupport getMyPCS() {
        return myPCS;
    }

    public int getHealth() {
        return myCurHealthProperty.get();
    }

    public IntegerProperty getCurHealthProperty() {
        return myCurHealthProperty;
    }

    public int getMaxHealth() {
        return myMaxHealth;
    }

    public void setHealth(final int theHealth) {
        if (theHealth > myMaxHealth) {
            myCurrHealth = myMaxHealth;
        } else {
            myCurrHealth = theHealth;
        }
        myPCS.firePropertyChange("Health Changed", null, myCurrHealth);
    }

    public void changeHealth(final int theHealthChange) {
        myCurrHealth += theHealthChange;

        if (myCurrHealth > myMaxHealth) {
            myCurrHealth = myMaxHealth;
        } else if (myCurrHealth < 0) {
            myCurrHealth = 0;
        }

        Platform.runLater(() -> myCurHealthProperty.set(myCurrHealth));

    }

    @Override
    public int getAttack() {
        int attackRange = new Random().nextInt(myMaxAttack-myMinAttack);
        return myMinAttack + attackRange;
    }

    public int getDefense() {
        return myDefense;
    }

    public int getSpeed() {
        if (myMaxSpeed <= myMinSpeed) {
            return myMinSpeed;
        }
        int speedRange = new Random().nextInt(myMaxSpeed - myMinSpeed);
        return myMinSpeed + speedRange;
    }

    public void setSpeed(final int theSpeedBonus) {
        myMinSpeed += theSpeedBonus;
        myMaxSpeed += theSpeedBonus;
    }

    public boolean canAttack(int theOtherSpeed) {
        return getSpeed() >= theOtherSpeed;
    }

    public String getImageFileName() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName().toLowerCase(Locale.ENGLISH));
        sb.append(".png");

        return sb.toString();
    }
    public void useSpecialAttack() {}


    public boolean isDead() {
        return myCurrHealth <= 0;
    }

    @Override
    public String toString() {
        return myName + " the " + DEFAULT_NAME;
    }

}
