package palindrome;

import java.util.Stack;

public class LinkedListPalindrome {

    // if  there is multiple space you can use stack for that

    public LinkedList i;
    public boolean isPalindromeCalculate(LinkedList head) {
        i=head;
        return recurseList(i);
    }

    private boolean recurseList(LinkedList node) {
        if(node == null)
            return true;
        if(!recurseList(node.next))
            return false;
        if(node.value != i.value)
            return false;
        i=i.next;
        return true;
    }
    public boolean isPalindromeCalculateWithStack(LinkedList head) {
        Stack<Integer> stack= new Stack<>();
        stack.push(head.value);
        var temp=head;
        while(temp.next!=null) {
            temp =temp.next;
            stack.push(temp.value);

        }
        while(!stack.isEmpty() && head.next!=null) {
            var compareValue=stack.pop();
            if(head.value!=compareValue) {
                return false;
            }
            head=head.next;
        }
        return true;
    }
}
