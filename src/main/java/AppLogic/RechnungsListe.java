package AppLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RechnungsListe {

    public List<String> produktsInBill = new ArrayList<>();
    public float billPrice = 0;

    String tempNum;
    int tempBillPrice = 0;

    public void addProduktToRechnung(String currentButtonName, int amount, String size){
        String[] data = new String[3];
        data[0] = currentButtonName;
        data[1] = size;
        data[2] = String.valueOf(amount);
        produktsInBill.add(Arrays.toString(data));
        System.out.println(produktsInBill);
    }

    public float calculateBill(){
        billPrice = 0;
        for (int i = 0; i < produktsInBill.size(); i++){
            tempBillPrice = 0;
            tempNum = produktsInBill.get(i).split(",")[2];
            tempNum = tempNum.replace("]","").trim();
            tempBillPrice = Integer.parseInt(tempNum);
            System.out.println(tempBillPrice);
            billPrice += tempBillPrice;
        }
        return billPrice;
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
