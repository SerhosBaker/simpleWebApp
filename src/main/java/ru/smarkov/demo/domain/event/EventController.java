package ru.smarkov.demo.domain.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smarkov.demo.domain.event.dto.Event;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/first")
    public String getFirst() {
        Optional<Event> event = eventRepository.findById(UUID.fromString("d6726e18-5d5c-460c-adf6-0bd78192b102"));
        return event.map(value -> String.valueOf(value.getPosition())).orElse("event not found");
    }

    @PostMapping("/postAny")
    @Transactional
    public void postEvent() {
        Event event = new Event();
        event.setId(UUID.randomUUID());
        event.setPosition("01110"); // 01110111111 упадет, тк длинна поля строго 5 бит

        eventRepository.insertEvent(event.getPosition(), event.getId());
    }
}
