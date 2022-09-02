package com.example.springbootmall.constant;

public class MyTest {

    public static void main(String[] args) {
        ProductCategory category = ProductCategory.FOOD;

        String c = category.name();

        System.out.println(c); //FOOD

        String c2 = "CAR";

        ProductCategory category2 = ProductCategory.valueOf(c2);

    }
}
