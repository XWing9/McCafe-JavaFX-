package AppGUI;

import AppLogic.JSONReader;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

public class GenerateGUI{

    public void GenerateStartingGUI(TilePane container, GuiController controller) {
        container.getChildren().clear();

        String[] buttonsText = JSONReader.getProduktNames();

        for (String buttonText : buttonsText) {
            Button button = new Button(buttonText);
            button.setOnAction(controller::StartingOrder);
            container.getChildren().add(button);
        }
    }

    public void GenerateSizeChoosingGUI(TilePane choosingSize, GuiController controller, String produktName){
        String[] sizes = JSONReader.JSONReader("sizes",produktName);

        for (String buttonText : sizes){
            Button button = new Button(buttonText);
            //scine back button is linked sep i dont need to have it as its startiong controller
            button.setOnAction(controller::addProdukttoList);
            choosingSize.getChildren().add(button);
        }
    }
}