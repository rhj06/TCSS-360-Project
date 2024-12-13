package dungeongame.src.model;

import javafx.beans.property.IntegerProperty;

import java.beans.PropertyChangeSupport;

/**
 * Character interface for dungeon game
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 * @version 1.0
 */
public interface Character {

    /**
     * Property Change Support
     * @return myPCS
     */
    PropertyChangeSupport getMyPCS();

    /**
     * Get health of character
     * @return health
     */
    int getHealth();

    /**
     * get CurrentHealth Property
     * @return CurrentHealthProperty
     */
    IntegerProperty getCurHealthProperty();

    /**
     * get Max Health
     * @return myMaxHealth
     */
    int getMaxHealth();

    /**
     * set health of character to theHealth
     * @param theHealth health
     */
    void setHealth(final int theHealth);

    /**
     * Change health of Character by incrementing by theHealthChange
     * @param theHealthChange change in healtha
     */
    void changeHealth(final int theHealthChange);

    /**
     * get attack of character
     * @return myAttack
     */
    int getAttack();

    /**
     * get defence of character
     * @return myDefense
     */
    int getDefense();

    /**
     * get speed of character
     * @return mySpeed
     */
    int getSpeed();

    /**
     * set speed of character
     * @param theSpeed the change in speed
     */
    void setSpeed(final int theSpeed);

    /**
     * is character attacking?
     * @return true if mySpeed is faster then theOtherSpeed
     */
    boolean canAttack(final int theOtherSpeed);

    /**
     * Get image file name of dungeon character
     * @return get image file name
     */
    String getImageFileName();

    /**
     * get special attack of character
     */
    void useSpecialAttack();

    /**
     * is character dead?
     * @return true if Character health == 0
     */
    boolean isDead();


}