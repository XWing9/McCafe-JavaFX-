package AppLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RechnungsListe {

    public List<String> produktsInBill = new ArrayList<>();
    public int billPrice = 1;

    String tempNum;

    public void addProduktToRechnung(String currentButtonName, int amount, String size){
        String[] data = new String[3];
        data[0] = currentButtonName;
        data[1] = size;
        data[2] = String.valueOf(amount);
        produktsInBill.add(Arrays.toString(data));
        calculateBill();
        System.out.println(produktsInBill);
    }

    public void calculateBill(){
        for (int i = 0; i < produktsInBill.size(); i++){
            tempNum = produktsInBill.get(i).split(",")[2];
            tempNum = tempNum.replace("]","").trim();
            billPrice = Integer.parseInt(tempNum);
            billPrice += billPrice;
        }
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
