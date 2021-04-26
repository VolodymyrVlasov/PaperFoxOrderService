package com.paperfox.order;

import com.paperfox.order.fakeDB.MaterialsFakeDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);

        System.out.println(Arrays.asList(MaterialsFakeDB.materials));


    }

}
