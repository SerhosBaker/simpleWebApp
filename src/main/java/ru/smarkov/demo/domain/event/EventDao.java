package ru.smarkov.demo.domain.event;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.stereotype.Component;
import ru.smarkov.demo.jooq_3b.tables.records.TaskRecord;
import ru.smarkov.demo.jooq_3b.tables.records.EventRecord;

import java.util.UUID;

import static ru.smarkov.demo.jooq_3b.Tables.EVENT;
import static ru.smarkov.demo.jooq_3b.Tables.TASK;

@Component
public class EventDao {
    private final DSLContext dsl;

    public EventDao(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Result<Record2<Short, String>> getAll() {
        return dsl.select(EVENT.ID, EVENT.TITLE)
                .from(EVENT)
                .fetch();
    }

    public void getEventAndTask(UUID taskId) {

        Record twoTablesResult = dsl.select()
                .from(EVENT)
                .join(TASK).on(EVENT.ID.eq(TASK.EVENT_ID))
                .where(TASK.ID.eq(taskId))
                .fetchOne();

        TaskRecord taskRecord = twoTablesResult.into(TASK);
        EventRecord eventRecord = twoTablesResult.into(EVENT);

        System.out.println("My task is: " + taskRecord.getTitle());
        System.out.println("My event is: " + eventRecord.getTitle());
    }
}
