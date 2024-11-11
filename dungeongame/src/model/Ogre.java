package dungeongame.src.model;

public final class Ogre extends AbstractMonster{

    /***/
    private static final String DEFAULT_NAME = "Ogre";
    /***/
    private final String myName;

    public Ogre(final int theMaxHealth,
                final int theMinAttack, final int theMaxAttack, final int theSpeed,
                final int theDefense, final double theItemDropRate, final String theName) {

        super(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, theName);
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