package ru.smarkov.demo.domain.track;

import ru.smarkov.demo.domain.track.dto.TrackDto;

import java.util.List;
import java.util.UUID;

public interface TrackRepository {
    List<TrackDto> getAll();

    List<UUID> getAllTrackIds();
}
