package AppGUI;

import AppLogic.AppManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static AppManager appManager;

    @Override
    public void init() {
        appManager = new AppManager();
    }

    public static AppManager getAppManager() {
        return appManager;
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Startingpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        //adding global stylesheet
        String globalStylesheet = HelloApplication.class.getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(globalStylesheet);

        stage.setTitle("McCafe");
        stage.setScene(scene);
        stage.show();
    }
}
