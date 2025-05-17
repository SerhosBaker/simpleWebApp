package ru.smarkov.demo.domain.event;

import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import ru.smarkov.demo.domain.event.dto.EventDto;
import ru.smarkov.demo.jooq_3b.tables.records.EventRecord;

import java.util.List;
import java.util.UUID;

@Repository
public class EventDaoAdapter implements EventRepository {
    private final EventDao eventDao;

    public EventDaoAdapter(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public List<EventDto> getAll() {
        Result<Record2<Short, String>> v = eventDao.getAll();
        List<EventRecord> v2 = v.into(EventRecord.class);
        return v2.stream()
                .map(s -> new EventDto(s.getId(), s.getTitle()))
                .toList();
    }

    @Override
    public void printEventAndTask(UUID taskId) {
        eventDao.getEventAndTask(taskId);
    }
}
