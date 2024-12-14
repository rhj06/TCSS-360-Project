package dungeongame.src.model;

/**
 * Represents a Warrior character in the dungeon game.
 * The Warrior is a player-controlled character with the ability to temporarily boost attack and defense stats by
 * 50 units using a special attack. This class extends AbstractDungeonCharacter and implements the Player interface.
 *
 * @version 1.0
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 */
public final class Warrior extends AbstractDungeonCharacter implements Player {

    /** Default name for a Warrior character. */
    private static final String DEFAULT_NAME = "Warrior";

    /** Bonus value for attack and defense when the special attack is activated. */
    private static final int ATTACK_AND_DEFENSE_BONUS = 50;

    /** Indicates if the attack bonus is active. */
    private boolean myAttackBonusActive = false;

    /** Indicates if the defense bonus is active. */
    private boolean myDefBonusActive = false;

    /**
     *Constructs a new Warrior instance with the specified attributes.
     *
     * @param theMaxHealth the maximum health of the Warrior
     * @param theMinAttack the minimum attack value of the Warrior
     * @param theMaxAttack the maximum attack value of the Warrior
     * @param theMinSpeed  the minimum speed of the Warrior
     * @param theMaxSpeed  the maximum speed of the Warrior
     * @param theDefense   the defense value of the Warrior
     * @param theName      the name of the Warrior
     */
    public Warrior(final int theMaxHealth, final int theMinAttack, final int theMaxAttack,
                  final int theMinSpeed, final int theMaxSpeed, final int theDefense, final String theName) {
        super(theMaxHealth, theMinAttack, theMaxAttack, theMinSpeed, theMaxSpeed, theDefense, theName);
    }

    /**
     * Retrieves the Warrior's current attack value.
     * If the attack bonus is active, it adds 50 units to the base attack value for one turn and then deactivates
     * the bonus.
     * @return the Warrior's attack value
     */
    @Override
    public int getAttack() {
        int baseAttack = super.getAttack();
        if (myAttackBonusActive) {
            myAttackBonusActive = false; // Reset the bonus after one use
            return baseAttack + ATTACK_AND_DEFENSE_BONUS;
        }
        return baseAttack;
    }

    /**
     * Retrieves the Warrior's current defense value.
     * If the defense bonus is active, it adds 50 units to the base defense value for one turn and then deactivates
     * the bonus.
     * @return the Warrior's defense value
     */
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
     * Activates the Warrior's special attack increasing Attack and Defense by 50 units.
     */
    @Override
    public void useSpecialAttack() {
        myAttackBonusActive = true; // Activate the bonus for one turn
        myDefBonusActive = true;
    }

    /**
     * Provides a string representation of the Warrior.
     * @return a string describing the Warrior.
     */
    @Override
    public String toString() {
        return this.getMyName() + " the " + DEFAULT_NAME;
    }
}
