package AppGUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import AppLogic.AppManager;

public class HelloController {
    @FXML
    private Label welcomeText;

    private AppManager appManager;

    @FXML
    protected void initialize() {
        appManager  = HelloApplication.getAppManager();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(appManager.WelcomeButton());
    }

    @FXML
    protected void  letthetestwork() {
        welcomeText.setText("Let the test works!");
    }
}