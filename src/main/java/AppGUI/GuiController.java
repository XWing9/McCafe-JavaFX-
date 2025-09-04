package AppGUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import AppLogic.AppManager;
import javafx.scene.layout.TilePane;

import java.util.Objects;

public class GuiController {
    @FXML
    private Label welcomeText;
    @FXML
    private TilePane container;

    private AppManager appManager;

    @FXML
    protected void initialize() {
        appManager  = McCafeApplication.getAppManager();
        switchGui("");
    }

    public void switchGui(String produktName){
        //arsch methode raus damit
        if (appManager.isChoosingSize){
            new GenerateGUI().GenerateSizeChoosingGUI(container, this,produktName);
        } else{
            new GenerateGUI().GenerateStartingGUI(container, this);
        }
    }

    @FXML
    protected void StartingOrder(javafx.event.ActionEvent actionEvent) {
        //switch the GUI to early
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

        //call szene switcher function to laod different scene
        appManager.SwitchScene(appManager.desiredScene,appManager.currentSzene);
        System.out.println("szene switched");
        switchGui(buttonName);
    }

    public void sizeOrdered(){

    }

    //make better version
    //should take data given and take date from JSON to give back constructing UI based on that
    //make one function, takes button text searches in the JSON for its data and sends relevant data back
}