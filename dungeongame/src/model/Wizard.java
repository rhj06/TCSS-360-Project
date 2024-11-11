package dungeongame.src.model;

public final class Wizard extends AbstractDungeonCharacter implements Player{

    /***/
    private static final String DEFAULT_NAME = "Wizard";
    /***/
    private static final int HEALTH_BONUS = 50;
    /***/
    private int myAttack;
    /***/
    private final String myName;

    /**
     * @param theMaxHealth
     * @param theCurrHealth
     * @param theAttack
     * @param theSpeed
     * @param theDefense
     */
    public Wizard(final int theMaxHealth, final int theCurrHealth, final int theAttack,
                  final int theSpeed, final int theDefense, final String theName) {
        super(theMaxHealth, theCurrHealth, theAttack, theSpeed, theDefense, theName);
        myName = theName;
    }

    /**
     * Increase health by 50 units, decrease attack power of monster
     */
    @Override
    public void useSpecialAttack() {
        super.setHealth(HEALTH_BONUS);
        // How to decrease Attack of monster?
    }

    @Override
    public String toString() {
        return myName + " the " + DEFAULT_NAME;
    }
}
