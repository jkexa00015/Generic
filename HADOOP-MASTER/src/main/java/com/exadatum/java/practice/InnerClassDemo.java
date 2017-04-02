package com.exadatum.java.practice;

/**
 * Created by jksingh on 6/2/17.
 */
class InnerClassDemo {

    private String name;

    public int id;

    InnerClass innerClass;

    InnerClassDemo(String name,int id){
        this.name=name;
        this.id=id;
        this.innerClass = new InnerClass("jaskaran","khalsa");
    }

    class InnerClass{

        private String lastName;

        private String firstName;

        InnerClass(String firstName, String lastName){
            this.firstName=firstName;
            this.lastName=lastName;
        }

        private String concat(){

            String netString = firstName+name+lastName;
            return netString;
        }

    }

    public static void main(String args[]){

        InnerClassDemo innerClassDemo = new InnerClassDemo("singh",116);
        System.out.println(innerClassDemo.innerClass.concat());


    }
}

