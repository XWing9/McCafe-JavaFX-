package AppLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RechnungsListe {

    List<String> produktsInBill = new ArrayList<>();

    public void addProduktToRechnung(String currentButtonName, int amount, String size){
        String[] data = new String[3];
        data[0] = currentButtonName;
        data[1] = size;
        data[2] = String.valueOf(amount);
        produktsInBill.add(Arrays.toString(data));
        System.out.println(produktsInBill);
    }

    public void calculateBill(){
    }

    public void printBill(){
    }

    public void deleteProduktFromBill(String produktName,boolean isadmin){
        if (isadmin){
            produktsInBill.remove(produktName);
        }else{
            System.out.println("You are not allowed to delete this product");
        }
    }
}
