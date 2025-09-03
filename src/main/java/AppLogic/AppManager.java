package AppLogic;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class AppManager {

    public Boolean isAdmin = false;
    public Boolean isChoosingSize = false;
    public Boolean isDoingOrder = false;

    public String currentSzene = "";
    public String desiredScene = "";

    public int minSzeneHeight = 0;
    public int minSzeneWidth = 0;

    public AppManager() {
        JSONReader();
    }

    public void SwitchScene(String desiredScene,String currentScene) {
        System.out.println("Switching scene to " + desiredScene);

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
                Parent newRoot = FXMLLoader.load(fxmlUrl);

                Scene scene = primaryStage.getScene();
                if (scene == null) {
                    // Fallback: first-time navigation creates the Scene
                    scene = new Scene(newRoot, 900, 600);
                    String css = getClass().getResource("/AppGUI/styles.css").toExternalForm();
                    scene.getStylesheets().add(css);
                    primaryStage.setScene(scene);
                } else {
                    scene.setRoot(newRoot); // keep same Scene, CSS persists
                }
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    //temp JSON read test function
    public static void JSONReader(){
        String cachedMenuJson;
        cachedMenuJson = Arrays.toString(JSONReader.JSONReader("names",null));
        System.out.println("Read JSON: " + cachedMenuJson);
    }

    private Stage primaryStage;
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }
}

//maybe make into a singelton? dont see reason for now

//holds all data in variables
//only call functions when a button or so is pressed
//function either redirects to another function
//or function is here probably first