package aurafarmer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private AuraFarmer auraFarmer;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image auraImage = new Image(this.getClass().getResourceAsStream("/images/DaAura.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getAuraDialog("aura: hello! i'm aurafarmer\naura: what can i do for you?", auraImage)
        );
    }

    /**
     * Injects the AuraFarmer instance.
     */
    public void setAuraFarmer(AuraFarmer af) {
        auraFarmer = af;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = auraFarmer.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAuraDialog(response, auraImage)
        );
        userInput.clear();
        if (input.trim().equals("bye")) {
            Platform.exit();
        }
    }
}
