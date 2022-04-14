import java.util.Arrays;

// For this challenge you will determine when a specific sequence terminates.
/*
have the function KaprekarsConstant(num) take the num parameter being passed which will be a 4-digit number
with at least two  digits. Your program should perform the following routine on the number:
 Arrange the digits in descending order and in ascending order (adding zeroes to fit it to a 4-digit number),
  and subtract the smaller number from the bigger number. Then repeat the previous step.
  Performing this routine will always cause you to reach a fixed number: 6174. Then performing the routine on 6174 will always give you 6174
   (7641 - 1467 = 6174). Your program should return the number of times this routine must be performed until 6174 is reached. For example: if num is 3524 your program should return 3 because of the following steps: (1) 5432 - 2345 = 3087, (2) 8730 - 0378 = 8352, (3) 8532 - 2358 = 6174.
*/
public class KaprekarsConstant {

    int performKaprekars(int num) {
        var digits=new Integer[4];
        var count=0;
        while(num!=6174) {
            var value=num;
            for(var i=0;i<4;i++) {
                digits[i]=value%10;
                value= value/10;
            }
            Arrays.sort(digits);
            var largestNumber=0;
            var lowestNumber=0;
            for(var i=0;i<4;i++) {
                lowestNumber=lowestNumber*10+digits[i];
                largestNumber=largestNumber*10+digits[3-i];
            }
            count++;
            num=Math.abs(largestNumber-lowestNumber);
        }
        return count;
    }
}
