package AppGUI;

import AppLogic.JSONReader;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

import java.util.Arrays;


public class GenerateGUI{

    public void GenerateStartingGUI(TilePane container, GuiController controller) {
        String[] buttonsText = JSONReader.getProduktNames();

        for (String buttonText : buttonsText) {
            Button button = new Button(buttonText);
            button.setOnAction(controller::StartingOrder);
            container.getChildren().add(button);
        }
    }

    public void GenerateSizeChoosingGUI(TilePane choosingsize, GuiController controller, String produktName){
        String[] sizes = JSONReader.JSONReader("sizes",produktName);
        //somehow call the JSON reader 2 time so array is empty so no buttons
        System.out.println(Arrays.toString(sizes));

        //doesnt gen buttons maybe need to konvert it?
        //change targeting container to fix bug
        for (String buttonText : sizes){
            System.out.println("buttonsgenereated");
            Button button = new Button(buttonText);
            button.setOnAction(controller::StartingOrder);
            choosingsize.getChildren().add(button);
        }
    }

    public void GenerateAmountGUI(){

    }
}
