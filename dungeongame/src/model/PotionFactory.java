package dungeongame.src.model;

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