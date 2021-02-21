package pointToOffer;

import java.util.Stack;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class LinkList {
    public static void printReversingly(ListNode headNode){
        ListNode iter=headNode;
        Stack stack=new Stack<Integer>();
        while (iter!=null){
            stack.push(iter.val);
            iter=iter.next;
        }
        while(!stack.empty()){
            System.out.print(stack.pop()+" ");
        }
    }

    public static void printReversingly1(ListNode headNode){
        if(headNode.next!=null) {
            printReversingly1(headNode.next);
        }
        System.out.print(headNode.val+" ");
    }
    public static void main(String[] args) {
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(2);
        ListNode l3=new ListNode(3);
        ListNode l4=new ListNode(4);
        ListNode l5=new ListNode(5);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l5;
        printReversingly1(l1);
    }
}
