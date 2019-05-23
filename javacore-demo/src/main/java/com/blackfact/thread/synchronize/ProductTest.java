package com.blackfact.thread.synchronize;

public class ProductTest {
    public static void main(String[] args) {
        Product product = new Product();
        for(int i=0;i<15;i++){
            product.produce();
        }
        for(int i=0;i<15;i++){
            product.consume();
        }

    }
}
