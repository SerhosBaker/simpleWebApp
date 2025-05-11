package ru.smarkov.demo.util;

import org.springframework.beans.factory.annotation.Value;

public class TestClass {
    @Value("#{19 + 1}") // 20
    private double add;

    public double getValue() {
        return add;
    }
}
