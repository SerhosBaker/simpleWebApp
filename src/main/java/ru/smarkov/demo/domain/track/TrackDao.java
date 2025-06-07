package ru.smarkov.demo.domain.track;

import lombok.extern.slf4j.Slf4j;
import org.jooq.Cursor;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.stereotype.Component;
import ru.smarkov.demo.domain.track.dto.ExtendedTrackDto;
import ru.smarkov.demo.domain.track.dto.ExtendedTrackProgressDto;
import ru.smarkov.demo.domain.track.dto.TrackAndProgressDto;
import ru.smarkov.demo.jooq_3b.tables.records.TrackRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.select;
import static ru.smarkov.demo.jooq_3b.Tables.TRACK;
import static ru.smarkov.demo.jooq_3b.Tables.TRACK_PROGRESS;

@Component
@Slf4j
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

    public List<UUID> selectAllTracksLazy() {

        List<UUID> result = new ArrayList<>();
        try (Cursor<TrackRecord> cursor = dsl.selectFrom(TRACK).fetchLazy()) {

            while (cursor.hasNext()) {
                TrackRecord book = cursor.fetchNext();
                if (book != null) {
                    result.add(book.getId());
                }
            }
        }
        return result;
    }

    public List<TrackAndProgressDto> selectTrackAndProgresses(UUID investId) {

        return dsl
                .select(TRACK_PROGRESS.TRACK_ID, TRACK_PROGRESS.VISIBILITY, TRACK_PROGRESS.track().TITLE)
                .from(TRACK_PROGRESS)
                .where(TRACK_PROGRESS.INVEST_ID.eq(investId))
                .fetch(
                        record -> new TrackAndProgressDto(
                                record.value1(),
                                record.value2(),
                                record.value3()
                        )
                );
    }

    public List<ExtendedTrackDto> selectAllWithProgress(UUID investId) {
        return dsl.select(TRACK.ID, TRACK.TITLE,
                        multiset(
                                select(TRACK_PROGRESS.VISIBILITY)
                                        .from(TRACK_PROGRESS)
                                        .innerJoin(TRACK)
                                        .on(TRACK_PROGRESS.TRACK_ID.eq(TRACK.ID))
                                        .where(TRACK_PROGRESS.INVEST_ID.eq(investId))
                        )
                                .as("tags")
                                .convertFrom(it->it.map(mapping(ExtendedTrackProgressDto::new)))
                )
                .from(TRACK)
                .fetch(mapping(ExtendedTrackDto::new));
    }
}
