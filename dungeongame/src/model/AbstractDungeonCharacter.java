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
    private int mySpecialAttack;
    /***/
    private int mySpeed;
    /***/
    private int myDefense;

    /**
     *
     * @param theMaxHealth
     * @param theCurrHealth
     * @param theAttack
     * @param theSpecialAttack
     * @param theSpeed
     * @param theDefense
     */
    private AbstractDungeonCharacter(final int theMaxHealth, final int theCurrHealth,
                                     final int theAttack, final int theSpecialAttack, final int theSpeed,
                                     final int theDefense) {

        myMaxHealth = theMaxHealth;
        myCurrHealth = theCurrHealth;
        myAttack = theAttack;
        mySpecialAttack = theSpecialAttack;
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
    public int getDefense() {
        return myDefense;
    }

    /**
     *
     * @param theDefense
     */
    public void setDefense(final int theDefense) {
        myDefense = theDefense;
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
        mySpeed = theSpeed;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return DEFAULT_NAME;
    }
}
