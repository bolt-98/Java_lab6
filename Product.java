package laba_6;
import java.time.LocalDate;

public class Product implements Comparable<Product>{

    private double price;
    private String name;
    private LocalDate timeToFalse;
    public Product () {
        price = 0.0;
        name = "";
        timeToFalse = null;
    }


    public  Product(String n, double c, LocalDate t) {
        name = n;
        price = c;
        timeToFalse = t;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getTimeToFalse() {
        return timeToFalse;
    }
    public void setTimeToFalse(LocalDate timeToFalse) {
        this.timeToFalse = timeToFalse;
    }
    @Override
    public int compareTo(Product p) {
        if(this.name.charAt(0)>p.name.charAt(0)) {
            return 1;
        }
        else if(this.name.charAt(0)<p.name.charAt(0)) {
            return -1;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product d = (Product) obj;
        return this.name.equals(d.name);
    }


    public  boolean dateOff() {
        return (LocalDate.now().compareTo(timeToFalse)==-1) ? true :  false;

    }
    public static void main(String[] args) {

    }
}
