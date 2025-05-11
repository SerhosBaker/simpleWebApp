package ru.smarkov.demo.domain.track;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static ru.smarkov.demo.jooq_3b.Tables.TRACK;

@Component
public class TrackDao {
    private final DSLContext dsl;

    public TrackDao(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Result<Record2<UUID, String>> selectTracks() {

        return dsl.select(TRACK.ID, TRACK.TITLE)
                .from(TRACK)
                .fetch();
    }
}
