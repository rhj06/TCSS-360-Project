package dungeongame.src.model;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;
import java.util.Locale;
import java.util.Random;

public abstract class AbstractDungeonCharacter implements Character, Serializable {
    @Serial
    private static final long serialVersionUID = 1351355L;

    /***/
    private static final String DEFAULT_NAME = "Dungeon Character";

    private final IntegerProperty myCurHealthProperty = new SimpleIntegerProperty();

    private final PropertyChangeSupport myPCS;
    /***/
    private final int myMaxHealth;
    /***/
    private int myCurrHealth;
    /***/
    private final int myMinAttack;
    /***/
    private final int myMaxAttack;
    /***/
    private int myMinSpeed;
    /***/
    private int myMaxSpeed;
    /***/
    private int myDefense;
    /***/
    private final String myName;

    /**
     *
     * @param theMaxHealth
     * @param theMinAttack
     * @param theMaxAttack
     * @param theMinSpeed
     * @param theMaxSpeed
     * @param theDefense
     * @param theName
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

    /**
     *
     * @return
     */
    public int getHealth() {
        return myCurHealthProperty.get();
    }

    public IntegerProperty getCurHealthProperty() {
        return myCurHealthProperty;
    }

    public int getMaxHealth() {
        return myMaxHealth;
    }

    /**
     *
     * @param theHealth
     */
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

    /**
     *
     * @return
     */
    @Override
    public int getAttack() {
        int attackRange = new Random().nextInt(myMaxAttack-myMinAttack);
        return myMinAttack + attackRange;
    }

    /**
     *
     * @param theAttackBonus
     */
    @Override
    public void setAttack(int theAttackBonus) {
//         myMinAttack += theAttackBonus;
//         myMaxAttack += theAttackBonus;
    }

    /**
     *
     * @return
     */
    public int getDefense() {
        return myDefense;
    }

    /**
     *
     * @param theDefense
     */
    public void setDefense(final int theDefense) {
        myDefense += theDefense;
    }

    /**
     *
     * @return
     */
    public int getSpeed() {
        if (myMaxSpeed <= myMinSpeed) {
            return myMinSpeed;
        }
        int speedRange = new Random().nextInt(myMaxSpeed - myMinSpeed);
        return myMinSpeed + speedRange;
    }

    /**
     *
     * @param theSpeedBonus
     */
    public void setSpeed(final int theSpeedBonus) {
        myMinSpeed += theSpeedBonus;
        myMaxSpeed += theSpeedBonus;
    }

    /**
     *
     * @param theOtherSpeed
     * @return
     */
    public boolean canAttack(int theOtherSpeed) {
        return getSpeed() >= theOtherSpeed;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return myName + " the " + DEFAULT_NAME;
    }

}
