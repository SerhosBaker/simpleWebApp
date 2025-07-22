package ru.smarkov.demo.domain.track;

import ru.smarkov.demo.domain.track.dto.ExtendedTrackDto;
import ru.smarkov.demo.domain.track.dto.TrackAndProgressDto;
import ru.smarkov.demo.domain.track.dto.TrackDto;

import java.util.List;
import java.util.UUID;

public interface TrackRepository {
    List<TrackDto> getAll();

    List<UUID> getAllTrackIds();

    List<UUID> getAllTrackIdsLazy();

    List<TrackAndProgressDto> getTrackAndProgresses(UUID investId);

    List<ExtendedTrackDto> getAllWithProgress(UUID investId);

    List<UUID> getWithMap(List<UUID> trackIds);
}
