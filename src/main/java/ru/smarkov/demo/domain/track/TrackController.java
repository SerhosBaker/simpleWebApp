package ru.smarkov.demo.domain.track;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smarkov.demo.domain.track.dto.TrackDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/track")
public class TrackController {

    private final TrackRepository trackRepository;

    @GetMapping("/first")
    public String getFirst() {
        List<TrackDto> tracks = trackRepository.getAll();
        return tracks.stream()
                .findFirst()
                .map(TrackDto::getTitle)
                .orElse("track not found");
    }
}
