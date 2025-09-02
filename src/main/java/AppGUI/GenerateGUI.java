package AppGUI;

import AppLogic.JSONReader;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

public class GenerateGUI{

    JSONReader JSONReader = new JSONReader();

    public void GenerateStartingGUI(TilePane container, GuiController controller) {

        String[] buttonsText = JSONReader.JSONReader("names");

        for (String buttonText : buttonsText) {
            Button button = new Button(buttonText);
            button.setOnAction(controller::StartingOrder);
            container.getChildren().add(button);
        }
    }

    public void GenerateSizeChoosingGUI(TilePane container, GuiController controller){
        String[] sizes = JSONReader.getProduktSizes("sizes");

        for (String buttonText : sizes) {
            Button button = new Button(buttonText);
            button.setOnAction(controller::StartingOrder);
            container.getChildren().add(button);
        }
    }
}
