package ad211.smyk;
import java.util.Scanner;
public class Main {
    private static int typeOfCustomer;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Bill bill = new Bill(10);
        SubMain subMain = new SubMain();
        int menu;
        String[] books = {"Comics & Graphic Novels", "Horror", "Romance", "Historical Fiction", "Literature & Fiction", "Manga", "Poetry", "Science Fiction", "Fantasy", "Mystery, Thrillers & Crime"};
        double[] priceOfBook = new double[10];
        double[] bookDiscount = new double[10];
        for (int i=0; i<10; i++){
            priceOfBook[i] = 1 + Math.random() * 100;
            if (typeOfCustomer == 1)
                bookDiscount[i] = 0;
            else
                bookDiscount[i] = (int)(Math.random()*5);
        }
        System.out.println("Welcome to our BOOKS store!");
        do {
            System.out.print("What type of customer are you?\n1 - General\n2 - Regular\nChoose the number of option:  ");
           typeOfCustomer = in.nextInt();
        }while(typeOfCustomer!=2 && typeOfCustomer!=1);

        do {
            if (typeOfCustomer==1)
                output(bill);
            else
                subMain.output(bill);
            System.out.print("\n\n1 - Add a new book\n2 - Delete a book from the bill\n3 - Print the bill \n4- Exit\nChoose the number of option:");
            menu = in.nextInt();
            switch (menu){
                case 1:
                    if (typeOfCustomer==1)
                        input(bill, books, priceOfBook, bookDiscount);
                    else
                        subMain.input(bill, books, priceOfBook, bookDiscount);
                    break;
                case 2:
                    bill.deleteItem();
                    break;
                case 3:
                    if(bill.getCurrentItem()!=0) {
                        if(typeOfCustomer == 1)
                            output(bill);
                        else
                            subMain.output(bill);
                        bill.priceSummary();
                    }
                    else
                        System.out.println("Your bill is empty! ");
                    break;
                case 4:
                    System.out.println("Good bye!!! ");
                    break;
                default:
                    System.out.println("Oops! Option undefined! Try again;) ");
                    break;
            }
        }while (menu!=4);
    }

    public int getTypeOfCustomer(){
        return typeOfCustomer;
    }
    public static void input(Bill bill, String[] books, double[] priceOfBook, double[] bookDiscount){
        System.out.println("Books:");
        for(int i = 0; i < 10;i++) {
            System.out.print((i + 1) + " - " + books[i]);
            System.out.printf(" - %.2f$ per 1\n", priceOfBook[i]);
        }
        inputPart2(bill, books, priceOfBook, bookDiscount);
    }

    private static Bill inputPart2 (Bill bill, String[] books, double[] priceOfBook, double[] bookDiscount){
        Scanner in = new Scanner(System.in);
        double numOfBook;
        int positionOfBook;
        do {
            System.out.print("Input the book that you want to buy(number of position): ");
            positionOfBook = in.nextInt();
        } while (positionOfBook<1 || positionOfBook>10);
        do {
            System.out.print("\nHow much would you like to buy: ");
            numOfBook = in.nextDouble();
        }while(numOfBook<=0);
        double truePrice = priceOfBook[positionOfBook-1] * numOfBook;
        double trueDiscount = truePrice * (bookDiscount[positionOfBook-1]/10);
        bill.addItem(new Item(books[positionOfBook-1], truePrice, numOfBook, trueDiscount));
        return bill;
    }

    public static void output(Bill bill) {
        System.out.println("*\t*\t*\t*\t*");
        System.out.println("Your bill: ");
        for (int i=0; i<bill.getCurrentItem();i++){
            System.out.println("*\t*\t*\t*\t*");
            System.out.print((i+1) + " - " + bill.getStack()[i].getBooks());
            System.out.print(" - " + bill.getStack()[i].getNumber());
            System.out.printf(" -  %.2f$\n", bill.getStack()[i].getPrice());
        }
    }

    public static class SubMain extends Main{
        public static void input(Bill bill, String[] books, double[] priceOfBook, double[] bookDiscount){
            System.out.println("Book:");
            for (int i = 0; i < 10; i++) {
                System.out.print((i + 1) + " - " + books[i]);
                System.out.printf(" -  %.2f$", priceOfBook[i]);
                System.out.printf(" -  %.2f$ discount per 1\n", priceOfBook[i]*(bookDiscount[i]/10));
            }
            inputPart2(bill, books, priceOfBook, bookDiscount);
        }
        public static void output(Bill bill) {
            System.out.println("*\t*\t*\t*\t*");
            System.out.println("Your bill: ");
            for (int i=0; i<bill.getCurrentItem(); i++){
                System.out.println("*\t*\t*\t*\t*");
                System.out.print((i+1) + " - " + bill.getStack()[i].getBooks());
                System.out.print(" - " + bill.getStack()[i].getNumber());
                System.out.printf("\t-\t %.2f$", bill.getStack()[i].getPrice());
                System.out.printf(" (-%.2f$ discount)", bill.getStack()[i].getDiscount());
                System.out.printf(" - %.2f$\n", (bill.getStack()[i].getPrice()-bill.getStack()[i].getDiscount()));
            }
        }
    }
}