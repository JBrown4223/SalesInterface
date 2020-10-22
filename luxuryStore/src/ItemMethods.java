import org.junit.Test;

import java.io.*;
import java.util.Scanner;

public class ItemMethods implements SortingMethods  {
    Item[] itemArray;
    String[] monthNums = {"Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String[] brands = {"Armani", "Dior", "Valentino", "Louis Vuitton", "Gucci", "Burberry", "Saint Laurent"};
    Sales[] salesArray; // number sold, cost of sales, total sales

    //constructor
    public ItemMethods(int size_){  itemArray = new Item[size_];}


    //Methods
    //Annual Sales
    void brandSales(){
        int costSum = 0, sumSales = 0, totalSales = 0, totalCost = 0, itemsSold = 0;
        int i = 0;
        salesArray = new Sales[7];

            while(i <= 6){
                for(int j = 0; j < itemArray.length; j++){
                    if(itemArray[j].brand.equals(brands[i])){
                        costSum+= (itemArray[j].item_cost * itemArray[j].num_sold);
                        sumSales+= (itemArray[j].price * itemArray[j].num_sold);
                        itemsSold+= itemArray[j].num_sold;
                        totalCost+= costSum;
                        totalSales+= sumSales;
                    }
                }
                salesArray[i] = new Sales(brands[i], itemsSold, totalCost, (totalSales-totalCost));
                costSum = 0;
                sumSales = 0;
                totalSales = 0;
                totalCost = 0;
                itemsSold = 0;
                i++;
            }

        System.out.println("------- Yearly Totals For Each Brand --------");
        for(int k = 0; k <= 6; k++){
            System.out.println("Brand: " + salesArray[k].brand + "  Gross: " + salesArray[k].gross);
        }
        System.out.println("\n");
    }

    //Monthly Sales for specified month
    void monthlyBrandSales(int month_){
        int costSum = 0, sumSales = 0, totalSales = 0, totalCost = 0, itemsSold = 0;
        int i = 0;
        salesArray = new Sales[7];

        while(i <= 6){
            for(int j = 0; j < itemArray.length; j++){
                if(itemArray[j].month == month_+1 && itemArray[j].brand.equals(brands[i])){
                    costSum+= (itemArray[j].item_cost * itemArray[j].num_sold);
                    sumSales+= (itemArray[j].price * itemArray[j].num_sold);
                    itemsSold+= itemArray[j].num_sold;
                    totalCost+= costSum;
                    totalSales+= sumSales;
                }
            }
            salesArray[i] = new Sales(brands[i], itemsSold, totalCost, (totalSales-totalCost));
            costSum = 0;
            sumSales = 0;
            totalSales = 0;
            totalCost = 0;
            itemsSold = 0;
            i++;
        }

        System.out.println("------- Totals For Month - "+ monthNums[month_] + " --------");
        for(int k = 0; k <= 6; k++){
            System.out.println("Brand: " + salesArray[k].brand + " "+ salesArray[k].numSold + "  Gross: "
                    + salesArray[k].gross);

        }
        System.out.println("\n");
    }

    //Ranking each brand by annual sales
    void brandRankings(){

        System.out.println("------- Top Annual Sellers Sales--------");
        mergeSort(salesArray, 0, salesArray.length-1);
        for(int l = 0; l <= 6; l++){
            System.out.println("Brand: " + salesArray[l].brand + "  Items Sold: "+ salesArray[l].numSold) ;
        }
        System.out.println("\n");
    }

    //Ranking each brand for the specified month
    void monthlyBrandRankings(int month_){

        int costSum = 0, sumSales = 0, totalSales = 0, totalCost = 0, itemsSold = 0;
        int i = 0;
        salesArray = new Sales[7];

        while(i <= 6){
            for(int j = 0; j < itemArray.length; j++){
                if(itemArray[j].month == month_+1 && itemArray[j].brand.equals(brands[i])){
                    costSum+= (itemArray[j].item_cost * itemArray[j].num_sold);
                    sumSales+= (itemArray[j].price * itemArray[j].num_sold);
                    itemsSold+= itemArray[j].num_sold;
                    totalCost+= costSum;
                    totalSales+= sumSales;
                }
            }
            salesArray[i] = new Sales(brands[i], itemsSold, totalCost, (totalSales-totalCost));
            costSum = 0;
            sumSales = 0;
            totalSales = 0;
            totalCost = 0;
            itemsSold = 0;
            i++;
        }
        System.out.println("------- Top Monthly Sellers For:  "+ monthNums[month_] + "  --------");
        mergeSort(salesArray, 0, salesArray.length-1);
        for(int l = 0; l <= 6; l++){
            System.out.println("Brand: " + salesArray[l].brand + "  Items Sold: "+ salesArray[l].numSold ) ;
        }
        System.out.println("\n");
    }

    @Override
    public void merge(Sales[] array, int low, int mid, int high){

        // Creating temporary arrays
        Sales[] leftArray = new Sales[mid - low + 1];
        Sales[] rightArray = new Sales[high - mid];

        // Copying arrays into temporaries
        for (int i = 0; i < leftArray.length; i++)
            leftArray[i] = array[low + i];
        for (int i = 0; i < rightArray.length; i++)
            rightArray[i] = array[mid + i + 1];

        // Iterators containing current index of temp subarrays
        int leftIndex = 0;
        int rightIndex = 0;

        // Copying from leftArray and rightArray back into array
        for (int i = low; i < high + 1; i++) {
            // If there are still uncopied elements in R and L, copy minimum of the two
            if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (leftArray[leftIndex].gross > rightArray[rightIndex].gross) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < leftArray.length) {
                // If all elements have been copied from rightArray, copy rest of leftArray
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < rightArray.length) {
                // If all elements have been copied from leftArray, copy rest of rightArray
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }

    }


    @Override
    public void mergeSort(Sales[] array, int low, int high) {
        if (high <= low) return;

        int mid = (low+high)/2;
        mergeSort(array, low, mid);
        mergeSort(array, mid+1, high);
        merge(array, low, mid, high);
    }








}
