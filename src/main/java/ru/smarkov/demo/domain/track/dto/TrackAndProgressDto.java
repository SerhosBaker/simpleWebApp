package ru.smarkov.demo.domain.track.dto;

import java.util.UUID;

public record TrackAndProgressDto(UUID trackId, Boolean visibility, String title) {
}
