package AppGUI;

import AppLogic.AppManager;
import AppLogic.JSONReader;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

public class GenerateGUI extends GuiController{

    AppManager appManager;
    JSONReader JSONReader = new JSONReader();


    public void GenerateStartingGUI(AppManager appManager) {

        String[] buttonsText = JSONReader.JSONReader("names");
        
        for (String buttonText : buttonsText) {
            Button button = new Button(buttonText);
            // Route clicks to existing handler in GuiController
            button.setOnAction(this::StartingOrder);
            container.getChildren().add(button);
        }

        //create buttons through a for loop
    }
}