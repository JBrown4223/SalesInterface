public interface SortingMethods {

    //These two methods work together to provide mergesort on the Sales Data
    void merge(Sales[] array, int low, int mid, int high);

    void mergeSort(Sales[] array, int low, int high);

}
