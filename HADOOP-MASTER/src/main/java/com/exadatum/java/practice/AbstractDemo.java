package com.exadatum.java.practice;
//Program demonstrating working of abstract classes and varargs
/**
 * Created by jksingh on 5/3/17.
 */
public class AbstractDemo {

    public static void main(String ...args){

        Triangle triangle = new Triangle();
        triangle.area(1,2,4);


    }
}

abstract class Figure {

    int numDim ;

    public void getDim(){
        System.out.println("The number of dimensions are "+numDim);
    }

    abstract void area(int ...args);


}

class Triangle extends Figure{

    void area(int ... args){
        int area =1;
        for (int arg : args){
            area = arg * area;
        }

        System.out.println("Area is "+ area);
    }

}
