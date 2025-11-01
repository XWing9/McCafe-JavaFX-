package AppLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RechnungsListe {

    List<String> produktsInBill = new ArrayList<>();

    public void addProduktToRechnung(String currentButtonName, int amount){
        String[] data = new String[2];
        data[0] = currentButtonName;
        data[1] = String.valueOf(amount);
        produktsInBill.add(Arrays.toString(data));
        System.out.println(produktsInBill);
    }
}
