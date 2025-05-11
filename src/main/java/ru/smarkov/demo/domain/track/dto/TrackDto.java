package ru.smarkov.demo.domain.track.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

public record TrackDto(UUID id, String title) {
}
