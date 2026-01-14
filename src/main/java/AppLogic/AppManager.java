package AppLogic;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

//beh frage
//dürfen schüler atmen oder nicht?

public class AppManager {

    public Boolean isAdmin = false;
    public Boolean isChoosingSize = false;
    public Boolean isDoingOrder = false;

    public String currentSzene = "";
    public String desiredScene = "StartingPage";
    private String resourcePath = "";

    public int minSzeneHeight = 0;
    public int minSzeneWidth = 0;

    private Stage primaryStage;

    public void SwitchScene(AppGUI.GuiController controller, String produktName) {

        if (primaryStage == null) {
            System.err.println("Primary stage not set.");
            return;
        }
        if (!desiredScene.endsWith(".fxml")) {
            resourcePath = "/AppGUI/" + (desiredScene + ".fxml");
        }

        Platform.runLater(() -> {
            try {
                URL fxmlUrl = getClass().getResource(resourcePath);
                if (fxmlUrl == null) {
                    throw new IOException("FXML not found: " + resourcePath);
                }

                FXMLLoader loader = new FXMLLoader(fxmlUrl);
                loader.setControllerFactory(type -> controller);

                Parent newRoot = loader.load();

                Scene scene = primaryStage.getScene();
                if (scene == null) {
                    //Größe des Fensters festlegen
                    Scene newScene = new Scene(newRoot, 1500, 900);
                    String css = getClass().getResource("/AppGUI/styles.css").toExternalForm();
                    newScene.getStylesheets().add(css);
                    primaryStage.setScene(newScene);
                } else {
                    scene.setRoot(newRoot);
                }
                primaryStage.show();

                controller.switchGui(produktName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }
}