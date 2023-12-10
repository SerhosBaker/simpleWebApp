package ru.smarkov.demo.domain.fruit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@ConfigurationProperties(prefix = "apple")
public class AppleConfigProps {
    private int id;
    private String name;
    private String userName;

    @ConstructorBinding
    public AppleConfigProps(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AppleConfigProps(int id, String name, String userName) {
        this.id = id;
        this.name = name;
        this.userName = userName;
    }
}
