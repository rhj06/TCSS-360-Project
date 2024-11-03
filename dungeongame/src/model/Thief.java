package dungeongame.src.model;

import java.io.Serializable;

public final class Thief extends AbstractDungeonCharacter implements Player {

    /***/
    private static final String DEFAULT_NAME = "Thief";
    /***/
    private static final int SPEED_BONUS = 50;
    /***/
    private int myAttack;
    /***/
    private final String myName;
    /***/
    private boolean myChanceToAttackTwice = false;

    /**
     * @param theMaxHealth
     * @param theCurrHealth
     * @param theAttack
     * @param theSpeed
     * @param theDefense
     */
    public Thief(final int theMaxHealth, final int theCurrHealth, final int theAttack,
                  final int theSpeed, final int theDefense, final String theName) {
        super(theMaxHealth, theCurrHealth, theAttack, theSpeed, theDefense);
        myName = theName;
    }

    /**
     * Increase Speed by 50 units, adds an 80% chance to Attack Twice
     */
    @Override
    public void useSpecialAttack() {
        super.setSpeed(SPEED_BONUS);
        if(ableToAttackTwice()) {
            canAttack(super.getSpeed());
        }
    }

    @Override
    public String toString() {
        return myName + " the " + DEFAULT_NAME;
    }

    private boolean ableToAttackTwice() {
        if(Math.random() * 10 > 2) {
            myChanceToAttackTwice = true;
        }
        return myChanceToAttackTwice;
    }
}
