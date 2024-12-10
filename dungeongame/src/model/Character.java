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
     * @return
     */
    PropertyChangeSupport getMyPCS();

    /**
     * Get health of character
     * @return
     */
    int getHealth();

    /**
     * get CurrentHealth Property
     * @return
     */
    IntegerProperty getCurHealthProperty();

    /**
     * get Max Health
     * @return
     */
    int getMaxHealth();

    /**
     * set health of character to theHealth
     * @param theHealth
     * @return
     */
    void setHealth(final int theHealth);

    /**
     * Change health of Character by incrementing by theHealthChange
     * @param theHealthChange
     */
    void changeHealth(final int theHealthChange);

    /**
     * get attack of character
     * @return
     */
    int getAttack();

    /**
     * get defence of character
     * @return
     */
    int getDefense();

    /**
     * get speed of character
     * @return
     */
    int getSpeed();

    /**
     * set speed of character
     * @param theSpeed
     * @return
     */
    void setSpeed(final int theSpeed);

    /**
     * is character attacking?
     * @return
     */
    boolean canAttack(final int theOtherSpeed);

    /**
     * Get image file name of dungeon character
     * @return
     */
    String getImageFileName();

    /**
     * get special attack of character
     */
    void useSpecialAttack();

    /**
     * is character dead?
     * @return
     */
    boolean isDead();


}