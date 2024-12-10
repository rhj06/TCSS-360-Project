package dungeongame.src.model;

public class Boss extends AbstractMonster {
    private final String myName;
    private final String myType;

    public Boss(final int theMaxHealth,
                final int theMinAttack, final int theMaxAttack, final int theSpeed,
                final int theDefense, final double theItemDropRate, final String theName, final String theType) {
        super(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, theName);

        myName = theName;
        myType = theType;
    }

    public String getType() {
        return myType;
    }

    @Override
    public Item getRandomItem() {
        return new HealthPotion();
    }


    @Override
    public String toString() {
        return myName + " the " + myType;
    }
}
