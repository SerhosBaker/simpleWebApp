package ru.smarkov.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import ru.smarkov.demo.domain.instrument.dto.InstrumentController;
import ru.smarkov.demo.domain.instrument.dto.InstrumentDto;

import java.util.List;

@SpringBootTest(
        classes = {
                InstrumentController.class,
                ValidationAutoConfiguration.class,
        }
)
class DemoApplicationTests {
    @Autowired
    InstrumentController instrumentController;

    @Test
    void contextLoads() {
        List<InstrumentDto> instruments = instrumentController.getInstruments();

        assert instruments.size() == 2;
    }
}
