package ru.smarkov.demo.domain.track;

import ru.smarkov.demo.domain.track.dto.TrackDto;

import java.util.List;

public interface TrackRepository {
    List<TrackDto> getAll();
}
