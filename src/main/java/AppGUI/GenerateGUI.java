package AppGUI;

import AppLogic.JSONReader;
import AppLogic.RechnungsListe;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class GenerateGUI{

    public void GenerateStartingGUI(TilePane container, TilePane orderBill, GuiController controller, RechnungsListe rechnungsManager) {
        container.getChildren().clear();

        String[] buttonsText = JSONReader.getProduktNames();

        for (String buttonText : buttonsText) {
            Button button = new Button(buttonText);
            button.setOnAction(controller::StartingOrder);
            container.getChildren().add(button);
        }

        for (int i = 0; i < rechnungsManager.produktsInBill.size(); i++) {
            // Get the array at position i
            Object item = rechnungsManager.produktsInBill.get(i);


            String firstElement = item.toString().replace("[","").replace("]","");

            Label textField = new Label(firstElement);
            orderBill.getChildren().add(textField);
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