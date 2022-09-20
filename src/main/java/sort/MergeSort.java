package sort;

public class MergeSort {

    public void mergeSort(int[] arr) {
        var length=arr.length;
        sort(arr,0,length-1);

    }

    public void sort(int[] arr,int start,int end){
        if(end>start) {
            int middle=start+ (end-1)/2;
            sort(arr,start,middle);
            sort(arr,middle+1,end);
            merge(arr,start,middle,end);

        }
    }
    public void merge(int[] array,int start,int middle,int end) {

    }
}
