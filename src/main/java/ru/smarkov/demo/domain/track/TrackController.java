package ru.smarkov.demo.domain.track;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smarkov.demo.domain.track.dto.TrackDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/track")
public class TrackController {

    private final TrackRepository trackRepository;

    public TrackController(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @GetMapping("/first")
    public String getFirst() {
        List<TrackDto> tracks = trackRepository.getAll();
        return tracks.stream()
                .findFirst()
                .map(TrackDto::title)
                .orElse("track not found");
    }

    @GetMapping("/all/ids")
    public List<UUID> getAllTrackIds(){
        return trackRepository.getAllTrackIds();
    }
}
