package ru.smarkov.demo.domain.task;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Row2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.smarkov.demo.domain.eventcost.dto.EventCostKey;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.jooq.impl.DSL.row;
import static ru.smarkov.demo.jooq_3b.Tables.EVENT_COST;

@Component
public class TaskDao implements TaskRepository {
    @Autowired
    private DSLContext dsl;

    @Override
    public List<UUID> delete(Collection<? extends EventCostKey> eventCostKeys) {
        List<Row2<String, String>> keyRows = eventCostKeys.stream()
                .map(key -> row(key.eventName(), key.skillName()))
                .toList();

        dsl
                .deleteFrom(EVENT_COST)
                .where(row(EVENT_COST.EVENT_NAME, EVENT_COST.SKILL_NAME).in(keyRows))
                .execute();
        return null;
    }
}
