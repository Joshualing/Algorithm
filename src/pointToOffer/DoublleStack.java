package pointToOffer;

import java.util.Stack;

/**
 * 扩展为泛型
 */
public class DoublleStack<T> {
    private Stack<T> stackIn=new Stack();
    private Stack<T> stackOut=new Stack();

    public T pop(){
        if(!stackOut.isEmpty())
            return stackOut.pop();

        if(stackOut.isEmpty()&&stackIn.isEmpty()){
            System.out.println("空..");
            return null;
        }

        //将stackIn的元素都押入stackOut
        while(!stackIn.isEmpty()){
            stackOut.push(stackIn.pop());
        }
        return pop();
    }

    public void push(T element){
        stackIn.push(element);
    }



    public static void main(String[] args) {
        DoublleStack doublleStack=new DoublleStack();
        doublleStack.push(1);
        doublleStack.push(2);
        doublleStack.push(3);
        System.out.println(doublleStack.pop());
        doublleStack.push(4);
        System.out.println(doublleStack.pop());
        System.out.println(doublleStack.pop());
        System.out.println(doublleStack.pop());
        //System.out.println(doublleStack.pop());
    }
}
