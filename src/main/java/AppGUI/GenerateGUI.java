package AppGUI;

import AppLogic.AppManager;
import AppLogic.JSONReader;

import java.util.Arrays;

public class GenerateGUI<JSONArray, JSONObject> {

    AppManager appManager;
    JSONReader JSONReader;


    public void GenerateStartingGUI(AppManager appManager) {

        String[] buttonsText = JSONReader.JSONReader("names");

        //create buttons through a for loop
    }
}