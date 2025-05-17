package ru.smarkov.demo.domain.event;

import org.jooq.Record2;
import org.jooq.Result;
import ru.smarkov.demo.domain.event.dto.Event;
import ru.smarkov.demo.domain.event.dto.EventDto;

import java.util.List;
import java.util.UUID;

public interface EventRepository {
    public List<EventDto> getAll();
    public void printEventAndTask(UUID taskId) ;
}
