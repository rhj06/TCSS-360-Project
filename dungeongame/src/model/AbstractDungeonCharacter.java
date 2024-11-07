package dungeongame.src.model;

import java.util.Random;

public abstract class AbstractDungeonCharacter implements Character{

    /***/
    private static final String DEFAULT_NAME = "Dungeon Character";
    /***/
    private final int myMaxHealth;
    /***/
    private int myCurrHealth;
    /***/
    private final int myMinAttack;
    /***/
    private final int myMaxAttack;
    /***/
    private int mySpeed;
    /***/
    private int myDefense;

    /**
     *
     * @param theMaxHealth
     * @param theMinAttack
     * @param theSpeed
     * @param theDefense
     */
    public AbstractDungeonCharacter(final int theMaxHealth,
                                     final int theMinAttack, final int theMaxAttack,
                                    final int theSpeed, final int theDefense) {

        myMaxHealth = theMaxHealth;
        myCurrHealth = theMaxHealth;
        myMinAttack = theMinAttack;
        myMaxAttack = theMaxAttack;
        mySpeed = theSpeed;
        myDefense = theDefense;
    }



    /**
     *
     * @return
     */
    public int getHealth() {
        return myCurrHealth;
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
        }
        myCurrHealth = theHealth;
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
        return mySpeed;
    }

    /**
     *
     * @param theSpeed
     */
    public void setSpeed(final int theSpeed) {
        mySpeed += theSpeed;
    }

    /**
     *
     * @param theOtherSpeed
     * @return
     */
    public boolean canAttack(int theOtherSpeed) {
        return mySpeed >= theOtherSpeed;
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
        return DEFAULT_NAME;
    }

}
