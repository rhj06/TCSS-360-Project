package dungeongame.src.model;

/**
 * Represents a Vision Potion item in the dungeon adventure game.
 * When used, this potion reveals the surrounding area for the player.
 * It inherits common item properties from the AbstractItem class.
 *
 * @author Ryan Johnsom, David Bessex, Kaleb Anagnostou
 */
public class VisionPotion extends AbstractItem {

    /**
     * Constructs a Vision Potion with a predefined name, description, and value.
     * The Vision Potion has a single use and reveals the surrounding area when used.
     */
    public VisionPotion() {

        super("Vision Potion", "Reveals surrounding area", 5, 1);
    }

    /**
     * Uses the Vision Potion, revealing the surrounding area to the player.
     * This method is specific to the Vision Potion's behavior in the game.
     */
    @Override
    public void useItem(Player thePlayer) {

    }
}
