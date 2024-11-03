package dungeongame.src.model;

public abstract class AbstractDungeonCharacter implements Character{

    /***/
    private static final String DEFAULT_NAME = "Dungeon Character";
    /***/
    private final int myMaxHealth;
    /***/
    private int myCurrHealth;
    /***/
    private int myAttack;
    /***/
    private int mySpeed;
    /***/
    private int myDefense;

    /**
     *
     * @param theMaxHealth
     * @param theCurrHealth
     * @param theAttack
     * @param theSpeed
     * @param theDefense
     */
    public AbstractDungeonCharacter(final int theMaxHealth, final int theCurrHealth,
                                     final int theAttack, final int theSpeed, final int theDefense) {

        myMaxHealth = theMaxHealth;
        myCurrHealth = theCurrHealth;
        myAttack = theAttack;
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
        return myAttack;
    }

    /**
     *
     * @param theAttackBonus
     */
    @Override
    public void setAttack(int theAttackBonus) {
        myAttack += theAttackBonus;
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
