package dungeongame.src.model;

public final class Wizard extends AbstractDungeonCharacter implements Player, TargetedSpecial {

    /***/
    private static final String DEFAULT_NAME = "Wizard";
    /***/
    private static final int HEALTH_BONUS = 50;
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
    public Wizard(final int theMaxHealth, final int theMinAttack, final int theMaxAttack,
                  final int theMinSpeed, final int theMaxSpeed, final int theDefense, final String theName) {
        super(theMaxHealth, theMinAttack, theMaxAttack, theMinSpeed, theMaxSpeed, theDefense, theName);
        myName = theName;
    }

    /**
     * Increase health by 50 units, decrease attack power of monster
     */
    @Override
    public void useTargetedSpecialAttack(AbstractMonster theMonster) {
        super.setHealth(HEALTH_BONUS);
        // How to decrease Attack of monster?
    }

    @Override
    public String toString() {
        return myName + " the " + DEFAULT_NAME;
    }
}
