package dungeongame.src.model;

public interface Character {

    /**
     * Get health of character
     * @return
     */
    int getHealth();

    /**
     * set health of character
     * @param theHealth
     * @return
     */
    void setHealth(int theHealth);

    /**
     * get defence of character
     * @return
     */
    int getDefense();

    /**
     * set defence of character
     * @param theDefense
     * @return
     */
    void setDefense(int theDefense);

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
    void setSpeed(int theSpeed);

    /**
     * get attack of character
     * @return
     */
    int getAttack();

    /**
     * set Attack of character
     * @param theAttackBonus
     * @return
     */
    void setAttack(int theAttackBonus);

    /**
     * get special attack of character
     * @return
     */
    int getSpecialAttack();

    /**
     * is character attacking?
     * @return
     */
    boolean isAttacking();

    /**
     * is character dead?
     * @return
     */
    boolean isDead();

    /**
     * toString of character
     * @return
     */
    String toString();


}