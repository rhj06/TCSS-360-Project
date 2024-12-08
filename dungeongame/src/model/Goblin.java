package dungeongame.src.model;

public final class Goblin extends AbstractMonster{

    /***/
    private static final String DEFAULT_NAME = "Goblin";
    /***/
    private final String myName;

    public Goblin(final int theMaxHealth,
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
            if (randomNumber < 5.01) {
                item = new VisionPotion();
            } else if(randomNumber < 7.01) {
                item = new SpeedPotion();
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
//
}
