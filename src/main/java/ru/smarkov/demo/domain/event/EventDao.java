package ru.smarkov.demo.domain.event;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static ru.smarkov.demo.jooq.Tables.EVENT;

@Component
public class EventDao {
    private final DSLContext dsl;

    public EventDao(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Result<Record2<UUID, String>> getAll() {
        return dsl.select(EVENT.ID, EVENT.TITLE)
                .from(EVENT)
                .fetch();
    }
}
