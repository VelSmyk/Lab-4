package ad211.smyk;

public class Item {
    private String books;
    private double price;
    private double number;
    private double discount;

    public Item(String books, double price, double number, double discount){
        this.books = books;
        this.price = price;
        this.number = number;
        this.discount = discount;
    }

    public String getBooks(){
        return books;
    }
    public double getPrice(){
        return price;
    }
    public double getNumber(){
        return number;
    }

    public double getDiscount(){
        return discount;
    }
}