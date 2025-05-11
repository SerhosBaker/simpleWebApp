package ru.smarkov.demo.domain.person;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@Hero(live = 20, name = "Little boy")
public class Scorpion {

    private String bestName;

    @Value("the best name")
    public String getBestName() {
        return bestName;
    }

    public void setBestName(String bestName) {
        this.bestName = bestName;
    }
}
