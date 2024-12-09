package dungeongame.src.model;

import java.io.Serializable;

public final class Thief extends AbstractDungeonCharacter implements Player, TargetedSpecial {

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
     *
     * @param theMaxHealth
     * @param theMinAttack
     * @param theMaxAttack
     * @param theMinSpeed
     * @param theMaxSpeed
     * @param theDefense
     * @param theName
     */
    public Thief(final int theMaxHealth, final int theMinAttack, final int theMaxAttack,
                 final int theMinSpeed, final int theMaxSpeed, final int theDefense, final String theName) {
        super(theMaxHealth, theMinAttack, theMaxAttack,  Math.min(theMinSpeed, theMaxSpeed), Math.max(theMinSpeed, theMaxSpeed),
                theDefense, theName);
        myName = theName;
    }


    /**
     * Increase Speed by 50 units, adds an 80% chance to Attack Twice
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
