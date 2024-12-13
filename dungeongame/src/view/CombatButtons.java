package dungeongame.src.view;

import dungeongame.src.controller.Arena;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Combat Buttons used in the Arena
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 * @version 1.0
 */
public final class CombatButtons {

    /** Constant of 2 */
    private static final int TWO = 2;

    /** Constant of 3 */
    private static final int THREE = 3;

    /** The maze object containing the rooms and player state. */
    private final Arena myArena;

    /**
     * Constructs a DirectionalButtons instance for the specified maze and room description.
     *
     *
     */
    public CombatButtons(final Arena theArena) {
        myArena = theArena;
    }

    /**
     * Creates and returns an HBox containing combat movement buttons.
     * The buttons allow the player to Basic Attack, Use Health Potion, Use Special Ability, Debug Attack
     *
     * @return An HBox containing combat movement buttons.
     */
    public HBox createCombatButtons() {
        Button attack = new Button("Basic Attack");
        attack.setOnAction(_ -> myArena.setPlayerMove(0));

        Button heal = new Button("Use Health Potion");
        heal.setOnAction(_ -> myArena.setPlayerMove(1));

        Button special = new Button("Use Special Ability");
        special.setOnAction(_ -> myArena.setPlayerMove(TWO));

        Button murder = new Button("Murder Button");
        murder.setStyle("-fx-background-color: gold;");
        murder.setOnAction(_ -> myArena.setPlayerMove(THREE));

        HBox movementButtons = new HBox(20, attack, heal, special, murder);
        movementButtons.setStyle("-fx-alignment: center; -fx-padding: 10;");
        return movementButtons;
    }

}
