package com.exadatum.java.practice;

/**
 * Created by jksingh on 7/2/17.
 */
public class StackDemo {

    public static void main(String args[]){
        Stack stack = new Stack();
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }
}


class Stack{

    public int tos;

    public int[] stck = new int[10];

    Stack(){
        tos=-1;
    }
    public void push(int input){
        if(tos==9)
            System.out.println("Stack Overflow");
        else{
            stck[++tos] = input;
        }
    }

    public int pop(){
        if(tos==-1)
            System.out.println("Stack Underflow");

        return stck[tos--];

    }
}