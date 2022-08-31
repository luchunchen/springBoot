package com.example.springbootmall.constant;

public class MyTest {

    public static void main(String[] args) {
        ProductCategroy categroy = ProductCategroy.FOOD;

        String c = categroy.name();

        System.out.println(c); //FOOD

        String c2 = "CAR";

        ProductCategroy categroy2 = ProductCategroy.valueOf(c2);

    }
}
