package ru.smarkov.demo.domain.person;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Subzero {

    @Value("#{19 + 1}")
    private int live;
    @Value("#{\"19\" + \"1\"}")
    private String name;

    @Autowired
    public Subzero(@Value("123") String name) {
        this.name = name;
    }

    public int getLive() {
        return live;
    }

    public String getName() {
        return name;
    }
}
