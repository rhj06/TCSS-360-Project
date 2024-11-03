package dungeongame.src.model;

public final class Ogre extends AbstractMonster{

    /***/
    private static final String DEFAULT_NAME = "Ogre";
    /***/
    private final String myName;

    public Ogre(final int theMaxHealth, final int theCurrHealth,
                    final int theAttack, final int theSpeed,
                    final int theDefense, final String theName, final double theItemDropRate) {

        super(theMaxHealth, theCurrHealth, theAttack, theSpeed, theDefense, theName, theItemDropRate);
        myName = theName;
    }

    public String getRandomItem() {
        final double randomNumber = Math.random() * 10;
        String item = "none";
        if (randomNumber > 1.99) {
            if (randomNumber < 4.01) {
                item = "vision";
            } else if(randomNumber < 6.01) {
                item = "speed";
            } else {
                item = "health";
            }
        }
        return item;
    }

    @Override
    public String toString() {
        return myName + " the " + DEFAULT_NAME;
    }

}
