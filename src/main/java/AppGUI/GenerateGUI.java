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
            Button button = new Button(buttonText);//button.setOnAction(e -> controller.addProdukttoList(produktName,2));
            button.setOnAction(controller::StartingOrder);
            choosingSize.getChildren().add(button);
        }
    }

    public void GenerateAmountGUI(){
    }
}