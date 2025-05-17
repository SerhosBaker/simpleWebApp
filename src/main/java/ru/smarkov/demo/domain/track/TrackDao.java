package ru.smarkov.demo.domain.track;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.stereotype.Component;
import ru.smarkov.demo.jooq_3b.tables.records.TrackRecord;

import java.util.List;
import java.util.UUID;

import static ru.smarkov.demo.jooq_3b.Tables.TRACK;
import static ru.smarkov.demo.jooq_3b.Tables.TRACK_COLLECTION;

@Component
public class TrackDao {
    private final DSLContext dsl;

    public TrackDao(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Result<Record2<UUID, String>> selectTracks() {
        // id, title
        return dsl.select(TRACK.ID, TRACK.TITLE)
                .from(TRACK)
                .fetch();
    }

    public List<UUID> selectAllTrackIds() {
        // id, title, description здесь вызываются
        return dsl.selectFrom(TRACK)
                .orderBy(TRACK.ID)
                .fetch(TrackRecord::getId);
    }
}
