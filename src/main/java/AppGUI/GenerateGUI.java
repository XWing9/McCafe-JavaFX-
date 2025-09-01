package AppGUI;

import AppLogic.AppManager;
import AppLogic.JSONReader;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.fxml.FXML;

public class GenerateGUI extends GuiController{

    //remove fxml out of here and just call it from the gui controller?
    AppManager appManager;
    JSONReader JSONReader = new JSONReader();
    @FXML
    private TilePane container;

    public void GenerateStartingGUI(TilePane container, GuiController controller) {

        String[] buttonsText = JSONReader.JSONReader("names");

        for (String buttonText : buttonsText) {
            Button button = new Button(buttonText);
            // Route clicks to existing handler in GuiController
            button.setOnAction(controller::StartingOrder);
            container.getChildren().add(button);
        }

    }
}
