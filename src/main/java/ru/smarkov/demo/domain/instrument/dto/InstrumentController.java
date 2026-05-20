package ru.smarkov.demo.domain.instrument.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/instrument")
public class InstrumentController {

    @Autowired
    private Validator validator;

    @GetMapping("/list")
    public List<InstrumentDto> getInstruments() {
        return getInstrumentsFromDao().stream()
                .peek(instrument -> {
                    Set<ConstraintViolation<InstrumentDto>> violations = validator.validate(instrument);
                    if (!violations.isEmpty()) {
                        throw new IllegalArgumentException(violations.toString());
                    }
                })
                .toList();
    }

    private List<InstrumentDto> getInstrumentsFromDao() {
        return List.of(new InstrumentDto(1L, "AAPL", 150.0), new InstrumentDto(2L, "MSFT", 250.0));
    }
}
