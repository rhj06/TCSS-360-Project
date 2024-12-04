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

    public String getRandomItem() {
        final double randomNumber = Math.random() * 10;
        String item = "none";
        if (randomNumber > 1.99) {
            if (randomNumber < 4.01) {
                item = "health";
            } else if(randomNumber < 6.01) {
                item = "speed";
            } else {
                item = "vision";
            }
        }
        return item;
    }


    @Override
    public String toString() {
        return myName + " the " + myType;
    }
}
