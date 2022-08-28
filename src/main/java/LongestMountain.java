public class LongestMountain {

    public int findLongestMountain(int[] arr) {
        var length=arr.length;
        var inc=new int[length];
        var dec=new int[length];

        for(int i=1,j=length-2;i<length;j--,i++) {
            if(arr[i]>arr[i-1])
                inc[i]=inc[i-1] +1;
            if(arr[j]>arr[j+1])
                dec[j]=dec[j+1] +1;
        }
        var maxLong=0;
        for(int i=0;i<length;i++) {
            if(inc[i]>0 && dec[i]>0) {
                maxLong=Math.max(maxLong,inc[i]+dec[i]+1);
            }
        }
        return maxLong;
    }
}
