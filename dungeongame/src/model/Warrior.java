package dungeongame.src.model;

public final class Warrior extends AbstractDungeonCharacter implements Player {

    /***/
    private static final String DEFAULT_NAME = "Warrior";
    /***/
    private static final int ATTACK_AND_DEFENSE_BONUS = 50;
    /***/
    private final int myAttack;
    /***/
    private final int myDefense;
    /***/
    private final String myName;

    /**
     * @param theMaxHealth
     * @param theCurrHealth
     * @param theAttack
     * @param theSpeed
     * @param theDefense
     */
    public Warrior(final int theMaxHealth, final int theCurrHealth, final int theAttack,
                   final int theSpeed, final int theDefense, final String theName) {
        super(theMaxHealth, theCurrHealth, theAttack, theSpeed, theDefense, theName);
        myAttack = theAttack;
        myDefense = theDefense;
        myName = theName;
    }

    /**
     * Increase Attack and Speed by 50 units
     */
    @Override
    public void useSpecialAttack() {
        super.setAttack(ATTACK_AND_DEFENSE_BONUS);
        super.setDefense(ATTACK_AND_DEFENSE_BONUS);
    }

    @Override
    public String toString() {
        return myName + " the " + DEFAULT_NAME;
    }
}
