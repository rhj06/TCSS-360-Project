package dungeongame.src.model;

public final class Warrior extends AbstractDungeonCharacter implements Player {

    /***/
    private static final String DEFAULT_NAME = "Warrior";
    /***/
    private static final int ATTACK_AND_DEFENSE_BONUS = 50;
    /***/
    private int myAttackBonus = 0;
    /***/
    private int myDefenseBonus = 0;
    /***/
    private final String myName;
    /***/
    private boolean myAttackBonusActive = false;

    private boolean myDefBonusActive = false;

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

    @Override
    public int getAttack() {
        int baseAttack = super.getAttack();
        if (myAttackBonusActive) {
            myAttackBonusActive = false; // Reset the bonus after one use
            return baseAttack + ATTACK_AND_DEFENSE_BONUS;
        }
        return baseAttack;
    }

    @Override
    public int getDefense() {
        int baseDefense = super.getDefense();
        if (myDefBonusActive) {
            myDefBonusActive = false; // Reset the bonus after one use
            return baseDefense + ATTACK_AND_DEFENSE_BONUS;
        }
        return baseDefense;
    }

    /**
     * Increase Attack and Defense by 50 units
     */
    @Override
    public void useSpecialAttack() {
        myAttackBonusActive = true; // Activate the bonus for one turn
        myDefBonusActive = true;
    }


    @Override
    public String toString() {
        return myName + " the " + DEFAULT_NAME;
    }
}
