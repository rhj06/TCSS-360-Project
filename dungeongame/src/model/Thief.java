package dungeongame.src.model;


/**
 * Represents a Thief character in the dungeon game.
 *The Thief is a player-controlled character with the ability to temporarily boost speed and has an 80% chance to
 * attack twice using its special ability. This class extends AbstractDungeonCharacter and implements the Player and
 * TargetedSpecial interfaces.
 *
 * @version 1.0
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 */
public final class Thief extends AbstractDungeonCharacter implements Player, TargetedSpecial {

    /** Default name for a Thief character. */
    private static final String DEFAULT_NAME = "Thief";

    /** Speed bonus applied during the Thief's special attack. */
    private static final int SPEED_BONUS = 50;

    /** Indicates if the Thief has a chance to attack twice. */
    private boolean myChanceToAttackTwice = false;

    /**
     * Constructs a new Thief instance with the specified attributes.
     *
     * @param theMaxHealth the maximum health of the Thief
     * @param theMinAttack the minimum attack value of the Thief
     * @param theMaxAttack the maximum attack value of the Thief
     * @param theMinSpeed  the minimum speed of the Thief
     * @param theMaxSpeed  the maximum speed of the Thief
     * @param theDefense   the defense value of the Thief
     * @param theName      the name of the Thief
     */
    public Thief(final int theMaxHealth, final int theMinAttack, final int theMaxAttack,
                 final int theMinSpeed, final int theMaxSpeed, final int theDefense, final String theName) {
        super(theMaxHealth, theMinAttack, theMaxAttack,  Math.min(theMinSpeed, theMaxSpeed), Math.max(theMinSpeed, theMaxSpeed),
                theDefense, theName);
    }


    /**
     * Executes the Thief's targeted special attack increasing Speed by 50 units, adds an 80% chance to Attack Twice.
     */
    @Override
    public void useTargetedSpecialAttack(AbstractMonster theMonster) {
        super.setSpeed(SPEED_BONUS);
        if(ableToAttackTwice()) {
            int attack = this.getAttack();
            int damage = Math.max(attack - theMonster.getDefense(), 0);
            theMonster.changeHealth(-damage * 2);
        }
    }

    /**
     * Provides a string representation of the Thief.
     * @return a string describing the Thief.
     */
    @Override
    public String toString() {
        return this.getMyName() + " the " + DEFAULT_NAME;
    }

    private boolean ableToAttackTwice() {
        if(Math.random() * 10 > 2) {
            myChanceToAttackTwice = true;
        }
        return myChanceToAttackTwice;
    }
}
