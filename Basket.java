package laba_6;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
public class Basket {

    private List<Product>pdr;
    private String color;
    private String name_buyer;
    public Basket() {
        pdr = null;
        color = "";
        name_buyer = "";
    }
    public Basket(List<Product> p, String c, String nb) {

        pdr = p;
        color = c;
        name_buyer = nb;
    }

    public String getColor(){
        return color;
    }
    public void setColor(String colr){
        this.color = colr;
    }
    public String getName_buyer() {
        return name_buyer;
    }
    public void setName_buyer(String name_buyer) {
        this.name_buyer = name_buyer;
    }
    public void setPdr(List<Product> pdr) {
        this.pdr = pdr;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pdr == null) ? 0 : pdr.hashCode());
        return result;
    }
    public List<Product> getPdr() {
        return pdr;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj==this) {
            return true;
        }
        if(obj.getClass()!=this.getClass()) {
            return false;
        }
        Basket p =(Basket)obj;
        return (this.pdr==p.pdr);
    }
    public int func_Count() {
        int count = 0;
        for (int i=0; i<pdr.size(); i++) {
            for (int j=0; j<pdr.size(); i++)
                if (pdr.get(i).getName().compareTo(pdr.get(j).getName())!=0){
                    count++;
                }
        }
        return count;
    }
    public  double Sum () {
        double common_sum=0;
        for (int i=0; i<pdr.size(); i++) {
            common_sum+=pdr.get(i).getPrice();
        }
        return common_sum;
    }

    public static void main(String[] args) {


    }

}


