package AppGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import AppLogic.AppManager;

import java.util.Objects;

public class GuiController {
    @FXML
    private Label welcomeText;

    private AppManager appManager;

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

        //call szene switcher function to laod different scene
        appManager.SwitchScene(appManager.desiredScene,appManager.currentSzene);
    }

    //make better version
    //should take data given and take date from JSON to give back constructing UI based on that
    //make one function, takes button text searches in the JSON for its data and sends relevant data back
    @FXML
    protected void CaffeeButton() {
        if (appManager.isChoosingSize == true){
            welcomeText.setText(appManager.WelcomeButton());
        }
    }

    @FXML
    protected void MachiatoButton() {
        welcomeText.setText("Let the test works!");
    }

    public void SizeOrdered(ActionEvent actionEvent) {
    }
}