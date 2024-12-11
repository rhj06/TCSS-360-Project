package dungeongame.src.view;

import dungeongame.src.controller.Arena;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class CombatButtons {

    /** The maze object containing the rooms and player state. */
    private final Arena myArena;

    /**
     * Constructs a DirectionalButtons instance for the specified maze and room description.
     *
     *
     */
    public CombatButtons(Arena theArena) {
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
        attack.setOnAction(e -> myArena.setPlayerMove(0));

        Button heal = new Button("Use Health Potion");
        heal.setOnAction(e -> myArena.setPlayerMove(1));

        Button special = new Button("Use Special Ability");
        special.setOnAction(e -> myArena.setPlayerMove(2));

        Button murder = new Button("Murder Button");
        murder.setStyle("-fx-background-color: gold;");
        murder.setOnAction(e -> myArena.setPlayerMove(3));

        HBox movementButtons = new HBox(20, attack, heal, special, murder);
        movementButtons.setStyle("-fx-alignment: center; -fx-padding: 10;");
        return movementButtons;
    }

}
