package com.exadatum.java.practice;
//Program illustrating various concepts of inheritance
/**
 * Created by jksingh on 5/3/17.
 */
public class Box {
    int width;
    int length;
    int height;

    Box(int width,int length, int height){
        this.height  = height;
        this.length = length;
        this.width = width;
    }

 /*   Box(){
        width =-1;
        length = -1;
        height = -1;
    }*/

    void volume(){
        System.out.println("Volume is "+ (height*length*width));
        System.out.println("calling super class method");
    }
}
//while extending a class one should make sure that super class has dafault constructor implemented or sub class must use super

class BoxWeight extends Box {

    int weight;

    BoxWeight(int width, int length , int height , int weight){
        super(width,length,height);
        this.weight = weight;
    }

    void volume(){
        super.volume();
        System.out.println("calling overriden method");
    }


}

class BoxDemo{

    public static void main(String ... args){

        Box box = new Box(2,4,6);
        BoxWeight boxWeight = new BoxWeight(2,4,6,1);
        box.volume();
        box = boxWeight;
        box.volume();
        boxWeight.volume();


    }
}