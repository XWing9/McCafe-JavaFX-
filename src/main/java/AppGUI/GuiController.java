package AppGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import AppLogic.AppManager;
import AppLogic.RechnungsListe;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class GuiController {
    @FXML
    private Label welcomeText;

    @FXML
    private TilePane startingContainer;
    @FXML
    private VBox orderBill;
    @FXML
    private TilePane choosingSize;

    @FXML
    private Label billLabel;
    @FXML
    private Label billPrice;

    private AppManager appManager;
    private RechnungsListe rechnungsManager;
    //maybe put in appmanager
    public String currentButtonName;

    @FXML
    protected void initialize() {
        //delcare that this ref goes to the instanze in application
        //so it only uses one instantze in the whole projekt
        appManager  = McCafeApplication.getAppManager();
        rechnungsManager = McCafeApplication.getRechnungsListe();
    }

    //controls produkt buttons
    @FXML
    protected void StartingOrder(javafx.event.ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        currentButtonName = btn.getText();

        if (Objects.equals(currentButtonName, "Back")) {
            appManager.isChoosingSize = false;
            appManager.isDoingOrder = false;
            appManager.currentSzene = "StartingPage";
            appManager.desiredScene = "StartingPage";
        } else {
            appManager.isChoosingSize = true;
            appManager.isDoingOrder = true;
            appManager.currentSzene = "ChoosingSize";
            appManager.desiredScene = "ChoosingSize";
        }

        // Reuse this controller instance during the switch
        appManager.SwitchScene(appManager.desiredScene, appManager.currentSzene, this, currentButtonName);
    }

    //controls choosing size buttons
    public void addProdukttoList(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String size = btn.getText();
        //add real amount
        //add text current button clicked for size
        int amount = 1;
        rechnungsManager.addProduktToRechnung(currentButtonName,amount,size);
    }

    public void switchGui(String produktName){
        if (appManager.isChoosingSize){
            new GenerateGUI().GenerateSizeChoosingGUI(choosingSize, this, produktName);
        } else{
            new GenerateGUI().GenerateStartingGUI(
                    startingContainer, orderBill,
                    this,rechnungsManager,billLabel,
                    billPrice);
        }
    }
}