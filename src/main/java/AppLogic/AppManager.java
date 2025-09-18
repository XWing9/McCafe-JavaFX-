package AppLogic;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AppManager {

    public Boolean isAdmin = false;
    public Boolean isChoosingSize = false;
    public Boolean isDoingOrder = false;

    public String currentSzene = "";
    public String desiredScene = "";

    public int minSzeneHeight = 0;
    public int minSzeneWidth = 0;

    public void SwitchScene(String desiredScene, String currentScene, AppGUI.GuiController controller, String produktName) {

        if (primaryStage == null) {
            System.err.println("Primary stage not set.");
            return;
        }
        if (!desiredScene.endsWith(".fxml")) {
            desiredScene += ".fxml";
        }
        String resourcePath = "/AppGUI/" + desiredScene;

        Platform.runLater(() -> {
            try {
                URL fxmlUrl = getClass().getResource(resourcePath);
                if (fxmlUrl == null) {
                    throw new IOException("FXML not found: " + resourcePath);
                }

                FXMLLoader loader = new FXMLLoader(fxmlUrl);
                // Keep fx:controller in FXML, but force using the existing controller instance
                loader.setControllerFactory(type -> controller);

                Parent newRoot = loader.load(); // @FXML fields injected into 'controller' here

                Scene scene = primaryStage.getScene();
                if (scene == null) {
                    Scene newScene = new Scene(newRoot, 900, 600);
                    String css = getClass().getResource("/AppGUI/styles.css").toExternalForm();
                    newScene.getStylesheets().add(css);
                    primaryStage.setScene(newScene);
                } else {
                    scene.setRoot(newRoot);
                }
                primaryStage.show();

                // Now it's safe: choosingSize/container are injected
                controller.switchGui(produktName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Stage primaryStage;
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }
}