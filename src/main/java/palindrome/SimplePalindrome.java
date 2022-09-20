package palindrome;

public class SimplePalindrome {

    public boolean isPalindrome(String str) {
        var chars=str.toCharArray();
        var strLength=str.length();
        for(var i=0;i<strLength/2;i++) {
            if(chars[i] != chars[strLength-1-i]) {
                return false;
            }
        }
        return true;
    }
}
