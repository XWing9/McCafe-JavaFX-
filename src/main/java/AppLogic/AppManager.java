package AppLogic;

public class AppManager {

    //maybe make into a singelton? dont see reason for now

    //holds all data in variables
    //only call functions when a button or so is pressed
    //function either redirects to another function
    //or function is here probably first
    public AppManager() {
        JSONReader();
    }

    public String WelcomeButton(){
        return "Welcome to the App!";
    }

    private static String cachedMenuJson;

    public static void JSONReader(){
        //works, but remove function under this one if possible
        cachedMenuJson = JSONReader.readMenuJson();
        System.out.println("Loaded menu JSON (" + (cachedMenuJson != null ? cachedMenuJson.length() : 0) + " chars)");
    }

    public static String getMenuJson() {
        return cachedMenuJson;
    }
}