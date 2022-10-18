package ad211.smyk;
import java.util.Scanner;

public class Bill {
    private Item[] stack;
    private int currentItem;

    public Bill(int maxCurrentItem) {
        stack = new Item[maxCurrentItem];
        currentItem = 0;
    }
    public Item[] getStack() {
        return stack;
    }
    public int getCurrentItem() {
        return currentItem;
    }
    public void addItem(Item item) {
        if (stackFull())
            return;
        stack[currentItem] = item;
        currentItem++;
    }

    public void deleteItem() {
        if (stackEmpty())
            return;
        Scanner in = new Scanner(System.in);
        int deleteNumber;
        do {
            System.out.print("Choose a book to delete: ");
            deleteNumber = in.nextInt();
        } while (deleteNumber > currentItem);
        delete(deleteNumber);
    }

    private void delete(int deleteNumber) {
        for(int i = 0; i < currentItem - deleteNumber;i++)
            stack[deleteNumber + i - 1] = stack[deleteNumber + i];
        stack[currentItem - 1] = null;
        currentItem--;
    }

    public void priceSummary() {
        Main main = new Main();
        Sale sale = new Sale();
        if (main.getTypeOfCustomer()==1)
            summary();
        else
            sale.saleSummary();
    }

    private void summary() {
        double sum = 0;
        for (int i = 0; i < currentItem; i++)
            sum += stack[i].getPrice();
        System.out.print("*\t*\t*\t*\t*");
        System.out.printf("\n- Total amount: %.2f$", sum);
        System.out.print("*\t*\t*\t*\t*");
    }

    private boolean stackFull() {
        boolean check = currentItem == stack.length;
        if (check)
            System.out.println("\n\n\t\tYour bill is full");
        return check;
    }

    private boolean stackEmpty() {
        boolean check = currentItem == 0;
        if (check)
            System.out.println("\n\n\t\tYour bill is empty");
        return check;
    }

    public class Sale extends Bill {

        public Sale() {
            super(currentItem);
        }

        private void saleSummary() {
            double sum = 0;
            double discountSum = 0;
            int numOfdiscount = 0;
            for (int i = 0; i < currentItem;i++) {
                discountSum += stack[i].getDiscount();
                sum += stack[i].getPrice();
                if (stack[i].getDiscount() != 0)
                    numOfdiscount++;
            }
            System.out.println("*\t*\t*\t*\t*\nTotal number of items with discount: " + numOfdiscount);
            System.out.printf("*\t*\t*\t*\t*\nTotal amount without discount: %.2f$", sum);
            System.out.printf("\n*\t*\t*\t*\t*\nTotal discount amount: %.2f$", discountSum);
            System.out.printf("\n*\t*\t*\t*\t*\nTotal amount: %.2f$\n", sum-discountSum);


        }
    }
}