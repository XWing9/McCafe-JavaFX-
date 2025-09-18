package AppGUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import AppLogic.AppManager;
import AppLogic.RechnungsListe;
import javafx.scene.layout.TilePane;

import java.util.Objects;

public class GuiController {
    @FXML
    private Label welcomeText;

    @FXML
    private TilePane container;
    @FXML
    private TilePane choosingSize;

    private AppManager appManager;
    private RechnungsListe rechnungsManager;

    @FXML
    protected void initialize() {
        appManager  = McCafeApplication.getAppManager();
    }

    @FXML
    protected void StartingOrder(javafx.event.ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String buttonName = btn.getText();

        if (Objects.equals(buttonName, "Back")) {
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
        appManager.SwitchScene(appManager.desiredScene, appManager.currentSzene, this, buttonName);
    }

    public void switchGui(String produktName){
        if (appManager.isChoosingSize){
            new GenerateGUI().GenerateSizeChoosingGUI(choosingSize, this, produktName);
        } else{
            new GenerateGUI().GenerateStartingGUI(container, this);
        }
    }

    public void addProdukttoList(String produktName, int amount){
        rechnungsManager.addProduktToRechnung(produktName, amount);
        System.out.println("produkt added:" + produktName + amount);
    }
}