package AppGUI;

import AppLogic.AppManager;

public class GenerateGUI<JSONArray, JSONObject> {

    AppManager appManager;


    public void GenerateStartingGUI(AppManager appManager) {
        appManager.JSONReader();

        String buttonsText = appManager.getMenuJson();
    }
}