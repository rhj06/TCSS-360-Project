package dungeongame.src.model;

/**
 *
 * Factory class for creating potion instances.
 * This class provides a static method to create potions of different types such as health, speed, and vision. It
 * ensures a consistent way to instantiate potions based on their type.
 *
 * @version 1.0
 * author Ryan Johnson, David Bessex, Kaleb Anagnostou
 */
public final class PotionFactory {

    /**
     * Creates a potion instance based on the specified potion type.
     *
     * @param thePotionType The type of the potion to create (e.g., "health", "speed", "vision").
     * @return A new instance of the specified potion type.
     * @throws IllegalArgumentException If the potion type is invalid.
     */
    public static AbstractItem createPotion(final String thePotionType) {
        if (thePotionType.equalsIgnoreCase("health")) {

            return new HealthPotion();
        } else if (thePotionType.equalsIgnoreCase("speed")) {

            return new SpeedPotion();
        } else if (thePotionType.equalsIgnoreCase("vision")) {

            return new VisionPotion();
        } else {
            throw new IllegalArgumentException("Invalid potion type: " + thePotionType);
        }
    }
}