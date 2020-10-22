import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Item {

    //Variables
    int sku,num_sold,month, item_cost,price;
    String brand, itemType;



    //Constructor
    public Item( String data_)
    {
        String delim = "[,]";
        String[] token = data_.split(delim);

        this.sku = parseInt(token[0]);
        this.brand = token[1];
        this.itemType = token[2];
        this.num_sold = parseInt(token[3]);
        this.item_cost = parseInt(token[4]);
        this.price = parseInt(token[5]);
        this.month = parseInt(token[6]);
    }
}
