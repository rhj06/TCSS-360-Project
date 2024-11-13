package dungeongame.src.model;

/**
 *
 */
public class PotionFactory {

    /**
     * Creates a potion instance based on the specified potion class.
     *
     * @param thePotionClass The class of the potion to create (e.g., HealthPotion.class).
     * @return A new instance of the specified potion type.
     */
    public static AbstractItem createPotion(Class<? extends AbstractItem> thePotionClass) {
        if (thePotionClass == HealthPotion.class) {
            return new HealthPotion();
        } else if (thePotionClass == SpeedPotion.class) {
            return new SpeedPotion();
        } else if (thePotionClass == VisionPotion.class) {
            return new VisionPotion();
        } else {
            throw new IllegalArgumentException("Unknown potion type: " + thePotionClass);
        }
    }
}