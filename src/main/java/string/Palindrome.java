package string;
//You are given a string s consisting only of lowercase English letters.
  //      In one move, you can select any two adjacent characters of s and swap them.
    //    Return the minimum number of moves needed to make s a palindrome.
      //  Note that the input will be generated such that s can always be converted to a palindrome.
public class Palindrome {
    public int minMovesToMakePalindrome(String s) {
        var start=0;
        var end=s.length()-1;
        var n=s.length();
        var res=0;
        var charArray=s.toCharArray();
        while(end>start) {
            var k=end;

            if(charArray[start]!=charArray[end]) {
                while (charArray[k]!=charArray[start]) {
                    k--;
                }
                if(start == k){
                    swapChar(charArray, start, start + 1);
                    res++;
                }else{
                    while(k < end){
                        swapChar(charArray, k, k + 1);
                        res++;
                        k++;
                    }
                }
            } else {
                start++;
                end--;
            }
        }
        return res;
    }

    public static void swapChar(char[] arr,int index,int swapIndex) {
        var temp=arr[index];
        arr[index]=arr[swapIndex];
        arr[swapIndex]=temp;
    }
}
