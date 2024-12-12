package dungeongame.src.model;

/**
 * Represents a Wizard character in the dungeon game.
 * The Wizard is a player-controlled character with the ability to use a targeted special attack that increases its
 * health by a set amount. This class extends AbstractDungeonCharacter and implements the Player and TargetedSpecial
 * interfaces.
 *
 * @version 1.0
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 */
public final class Wizard extends AbstractDungeonCharacter implements Player, TargetedSpecial {

    /** Default name for a Wizard character. */
    private static final String DEFAULT_NAME = "Wizard";

    /** Health bonus applied during the Wizard's special attack. */
    private static final int HEALTH_BONUS = 50;

    /** Name of the specific Wizard instance. */
    private final String myName;

    /**
     * Constructs a new {@code Wizard} instance with the specified attributes.
     *
     * @param theMaxHealth the maximum health of the Wizard
     * @param theMinAttack the minimum attack value of the Wizard
     * @param theMaxAttack the maximum attack value of the Wizard
     * @param theMinSpeed  the minimum speed of the Wizard
     * @param theMaxSpeed  the maximum speed of the Wizard
     * @param theDefense   the defense value of the Wizard
     * @param theName      the name of the Wizard
     */
    public Wizard(final int theMaxHealth, final int theMinAttack, final int theMaxAttack,
                  final int theMinSpeed, final int theMaxSpeed, final int theDefense, final String theName) {
        super(theMaxHealth, theMinAttack, theMaxAttack, theMinSpeed, theMaxSpeed, theDefense, theName);
        myName = theName;
    }

    /**
     * Executes the Wizard's targeted special attack increasing health by 50 units, decrease attack power of monster
     *
     * @param theMonster the target monster
     */
    @Override
    public void useTargetedSpecialAttack(AbstractMonster theMonster) {
        int newHealth = Math.min(this.getHealth() + HEALTH_BONUS, this.getMaxHealth());
        this.getMyPCS().firePropertyChange("Health Changed", this.getHealth(), newHealth);
        super.setHealth(newHealth);
    }

    /**
     * Provides a string representation of the Wizard.
     * @return a string describing the Wizard.
     */
    @Override
    public String toString() {
        return myName + " the " + DEFAULT_NAME;
    }
}
