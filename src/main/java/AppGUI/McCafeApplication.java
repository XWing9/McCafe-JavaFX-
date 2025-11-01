package AppGUI;

import AppLogic.AppManager;
import AppLogic.RechnungsListe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class McCafeApplication extends Application {
    private static AppManager appManager;
    private static RechnungsListe rechnungsManager;

    @Override
    public void init() {
        appManager = new AppManager();
        rechnungsManager = new RechnungsListe();
    }

    public static AppManager getAppManager() {
        return appManager;
    }
    public static RechnungsListe getRechnungsListe() {
        return rechnungsManager;
    }

    public void start(Stage stage) throws IOException {

        appManager.setPrimaryStage(stage);
        Parent initialRoot = FXMLLoader.load(
                McCafeApplication.class.getResource("Startingpage.fxml")
        );
        Scene scene = new Scene(initialRoot, 600, 650);

        //adding global stylesheet
        String globalStylesheet = McCafeApplication.class.getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(globalStylesheet);

        stage.setTitle("McCafe");
        stage.setResizable(true);

        GuiController controller = new GuiController();

        appManager.SwitchScene("StartingPage", "", controller, "");
    }
}
