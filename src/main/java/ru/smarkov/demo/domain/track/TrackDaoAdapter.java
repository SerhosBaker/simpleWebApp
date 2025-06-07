package ru.smarkov.demo.domain.track;

import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.stereotype.Component;
import ru.smarkov.demo.domain.track.dto.ExtendedTrackDto;
import ru.smarkov.demo.domain.track.dto.TrackAndProgressDto;
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

        return result.map(record -> new TrackDto(record.get(TRACK.ID), record.get(TRACK.TITLE)));
    }

    public List<UUID> getAllTrackIds() {
        return trackDao.selectAllTrackIds();
    }

    @Override
    public List<UUID> getAllTrackIdsLazy() {
        return trackDao.selectAllTracksLazy();
    }

    public List<TrackAndProgressDto> getTrackAndProgresses(UUID investId) {
        return trackDao.selectTrackAndProgresses(investId);
    }

    public List<ExtendedTrackDto> getAllWithProgress(UUID investId) {
        return trackDao.selectAllWithProgress(investId);
    }
}
