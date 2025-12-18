package AppGUI;

import AppLogic.JSONReader;
import AppLogic.RechnungsListe;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class GenerateGUI{

    public void GenerateStartingGUI(TilePane container, VBox orderBill,
                                    GuiController controller, RechnungsListe rechnungsManager,
                                    Label billLabel, Label billPriceLabel) {
        container.getChildren().clear();

        String[] buttonsText = JSONReader.NamesJSONReader();
        String labelId = billLabel.getId();
        String billPriceId = billPriceLabel.getId();

        for (String buttonText : buttonsText) {
            Button button = new Button(buttonText);
            button.setOnAction(controller::StartingOrder);
            container.getChildren().add(button);
        }

        for (int i = 0; i < rechnungsManager.produktsInBill.size(); i++) {
            // Get the array at position i
            Object item = rechnungsManager.produktsInBill.get(i);


            String firstElement = item.toString().replace("[","").replace("]","");
            billLabel.setText(billLabel.getText() + "\n" + firstElement);
            billLabel.setWrapText(true);
            billPriceLabel.setText("Enbetrag: " + rechnungsManager.calculateBill() + " €");
        }
    }

    public void GenerateSizeChoosingGUI(TilePane choosingSize, GuiController controller, String produktName){
        String[] sizes = JSONReader.sizesJSONReader(produktName);

        for (String buttonText : sizes){
            Button button = new Button(buttonText);
            //scine back button is linked sep i dont need to have it as its startiong controller
            button.setOnAction(controller::addProdukttoList);
            choosingSize.getChildren().add(button);
        }
    }

    public void GenerateBill(){
        //get info from calc class and gen GUI accordingly
    }
}