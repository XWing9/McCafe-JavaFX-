package AppLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RechnungsListe {

    public List<String> produktsInBill = new ArrayList<>();
    public float billPrice = 0;

    String tempNum;
    float tempBillPrice = 0;

    public void addProduktToRechnung(String currentButtonName, String size){
        String[] data = new String[3];
        data[0] = currentButtonName;
        data[1] = size;
        data[2] = String.valueOf(JSONReader.PriceJSONReader(currentButtonName,size));
        produktsInBill.add(Arrays.toString(data));
    }

    public float calculateBill(){
        billPrice = 0;
        for (int i = 0; i < produktsInBill.size(); i++){
            tempBillPrice = 0;
            tempNum = produktsInBill.get(i).split(",")[2];
            tempNum = tempNum.replace("]","").trim();
            tempBillPrice = Float.parseFloat(tempNum);
            billPrice += tempBillPrice;
        }
        return billPrice;
    }

    public void printBill(){
    }
    
    public void getProduktPrice(){
        
    }

    public void deleteProduktFromBill(String produktName,boolean isadmin){
        if (isadmin){
            produktsInBill.remove(produktName);
        }else{
            System.out.println("You are not allowed to delete this product");
        }
    }
}
