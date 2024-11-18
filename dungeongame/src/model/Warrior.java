package dungeongame.src.model;

public final class Warrior extends AbstractDungeonCharacter implements Player {

    /***/
    private static final String DEFAULT_NAME = "Warrior";
    /***/
    private static final int ATTACK_AND_DEFENSE_BONUS = 50;
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
    public Warrior(final int theMaxHealth, final int theMinAttack, final int theMaxAttack,
                  final int theMinSpeed, final int theMaxSpeed, final int theDefense, final String theName) {
        super(theMaxHealth, theMinAttack, theMaxAttack, theMinSpeed, theMaxSpeed, theDefense, theName);
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
