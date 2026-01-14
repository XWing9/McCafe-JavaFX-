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
    @FXML
    private Button GiveBill;

    private AppManager appManager;
    private RechnungsListe rechnungsManager;
    //maybe put in appmanager
    public String currentButtonName;

    Button btn;
    @FXML
    protected void initialize() {
        //delcare that this ref goes to the instanze in application
        //so it only uses one instantze in the whole projekt
        appManager  = McCafeApplication.getAppManager();
        rechnungsManager = McCafeApplication.getRechnungsListe();
    }

    //controls produkt buttons
    @FXML
    protected void StartingOrder(ActionEvent actionEvent) {
        btn = (Button) actionEvent.getSource();
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
        appManager.SwitchScene(this, currentButtonName);
    }

    @FXML
    protected void ShowBill(ActionEvent actionEvent) {
        btn = (Button) actionEvent.getSource();
        String size = btn.getText();
        System.out.println("button pressed");

        appManager.currentSzene = "StartingPage";
        appManager.desiredScene = "ShowBill";
        //gen new Gui thing for bill
        //change thing so i dont give redundant info to method thats already
        //inside the class
        appManager.SwitchScene(this, "");
    }

    @FXML
    protected void newBill(ActionEvent actionEvent) {
        appManager.currentSzene = "ShowBill";
        appManager.desiredScene = "StartingPage";

        appManager.SwitchScene(this, "");
    }

    @FXML
    protected void deleteItems(){
        if (appManager.isAdmin == false){
            System.out.println("you are no admin");
        } else {
            System.out.println("you are admin");
        }
    }

    @FXML
    protected void setToAdmin(){
        appManager.isAdmin = true;
        System.out.println(appManager.isAdmin);
    }

    //controls choosing size buttons
    public void addProdukttoList(ActionEvent actionEvent) {
        btn = (Button) actionEvent.getSource();
        String size = btn.getText();

        rechnungsManager.addProduktToRechnung(currentButtonName,size);
    }

    public void switchGui(String produktName){
        switch(appManager.desiredScene){
            case "StartingPage":
                new GenerateGUI().GenerateStartingGUI(
                        startingContainer, orderBill,
                        this,rechnungsManager,billLabel,
                        billPrice);
                break;
            case "ShowBill":
                new GenerateGUI().GenerateBill();
                break;
            case "ChoosingSize":
                new GenerateGUI().GenerateSizeChoosingGUI(
                        choosingSize, this, produktName
                );
                break;
        }
    }
}