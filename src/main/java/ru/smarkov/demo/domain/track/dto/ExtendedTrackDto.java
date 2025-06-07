package ru.smarkov.demo.domain.track.dto;

import java.util.List;
import java.util.UUID;

public record ExtendedTrackDto(UUID id, String title, List<ExtendedTrackProgressDto> trackProgress) {
}
