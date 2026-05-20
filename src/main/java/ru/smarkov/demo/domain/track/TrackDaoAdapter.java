package ru.smarkov.demo.domain.track;

import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.stereotype.Component;
import ru.smarkov.demo.domain.track.dto.TrackDto;

import java.util.List;
import java.util.UUID;

import static ru.smarkov.demo.jooq_3b.Tables.TRACK;

@Component
public class TrackDaoAdapter implements TrackRepository {

    private final TrackDao trackDao;

    public TrackDaoAdapter(TrackDao trackDao) {
        this.trackDao = trackDao;
    }

    @Override
    public List<TrackDto> getAll() {
        Result<Record2<UUID, String>> result = trackDao.selectTracks();

        return result.map(record -> {
            UUID v = record.get(TRACK.ID);
            String v2 = record.get(TRACK.TITLE);
            return new TrackDto(v, v2);
        });
    }
}
