package app;

import datastore.Data;
import concurrency.OrderProcessor;


public class Main {

    public static void main(String[] args) {
        Data d1 = new Data();
        d1.createData();
        Data.printAll();
        System.out.println();

        OrderProcessor.init();

    }
}
