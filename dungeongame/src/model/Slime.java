package dungeongame.src.model;

public final class Slime extends AbstractMonster{

    /***/
    private static final String DEFAULT_NAME = "Slime";
    /***/
    private final String myName;

    public Slime(final int theMaxHealth,
                 final int theMinAttack, final int theMaxAttack, final int theSpeed,
                 final int theDefense, final double theItemDropRate, final String theName) {

        super(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, theName);
        myName = theName;
    }

    @Override
    public Item getRandomItem() {
        final double randomNumber = Math.random() * 10;
        Item item = null;
        if (randomNumber > 1.99) {
            if (randomNumber < 4.01) {
                item = new SpeedPotion();
            } else if(randomNumber < 6.01) {
                item = new VisionPotion();
            } else {
                item = new HealthPotion();
            }
        }
        return item;
    }


    @Override
    public String toString() {
        return myName + " the " + DEFAULT_NAME;
    }

}
