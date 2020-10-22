import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class mainInterface {

    boolean isRunning = true;
    int response = 0;
    String response_;
    String[] monthNums = {"Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    void run() throws IOException{
        ItemMethods tester;
        HelperClass helper = new HelperClass();
        int size= helper.countLines("Store_data.csv");
        int i = 0;

        //Allocation
        tester = new ItemMethods(size-1);

        try {
            File file = new File("Store_data.csv");    //creates a new file instance
            Scanner sc = new Scanner(file);
            while (sc.hasNext())
            {
                String str = sc.nextLine();
                tester.itemArray[i] = new Item(str);
                i++;
            }
            sc.close();
        }
        catch (IOException e)
        { // Auto-generated catch block
            e.printStackTrace();
        }
            menu(tester);
    }

    public void menu(ItemMethods obj){
        Scanner input = new Scanner(System.in);
        int monthResp = 0;
        while(isRunning){
            System.out.println("\n");
            System.out.println("Select A Menu Option");
            System.out.println(" 1 - Annual Sales Data\n" + " 2 - Monthly Sales Data\n"+ " 3 - Quit\n");
            response = input.nextInt();

            switch (response){
                case 1:
                    obj.brandSales();
                    obj.brandRankings();
                    break;
                case 2:
                    monthlyMenu(obj);

                     break;

                case 3:
                    System.exit(0);
            }
        }
    }

    public int getMonth(String month_){
        int m = 0;

        if(month_.equals("Jan")){
            return m;
        }

        for (int i = 0; i < monthNums.length; i++) {
            if (monthNums[i].equals(month_))
                m += i;
        }
        return m;
    }

    public void monthlyMenu(ItemMethods obj){
        Scanner input = new Scanner(System.in);
        int monthResp = 0;
        System.out.println("\n");
        System.out.println("Select A Month [Jan]: ");
        response_ = input.nextLine();
        monthResp = getMonth(response_);
        obj.monthlyBrandRankings(monthResp);
        obj.monthlyBrandSales(monthResp);
    }




    public static void main(String[] args) throws IOException {

        mainInterface main = new mainInterface();

        main.run();

    }
}
