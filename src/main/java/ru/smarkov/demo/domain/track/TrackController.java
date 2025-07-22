package ru.smarkov.demo.domain.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smarkov.demo.domain.track.dto.ExtendedTrackDto;
import ru.smarkov.demo.domain.track.dto.TrackAndProgressDto;
import ru.smarkov.demo.domain.track.dto.TrackDto;

import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/track")
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;

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
    public List<UUID> getAllTrackIds() {
        return trackRepository.getAllTrackIds();
    }

    @GetMapping("/all/ids/lazy")
    public List<UUID> getAllTrackIdsLazy() {
        return trackRepository.getAllTrackIdsLazy();
    }

    @GetMapping("/all/ids/trackandprogress")
    public List<TrackAndProgressDto> getTrackAndProgresses() {
        UUID investId = randomUUID();

        return trackRepository.getTrackAndProgresses(investId);
    }

    @GetMapping("/all/ids/trackandprogress/extended")
    public List<ExtendedTrackDto> getAllWithProgress() {
        UUID investId = UUID.fromString("f808d937-b396-47dc-bda4-48089c217301");

        return trackRepository.getAllWithProgress(investId);
    }

    @GetMapping("/getwithmap")
    public List<UUID> getWithMap() {
        List<UUID> trackIds = List.of(
                randomUUID(),
                UUID.fromString("320dfdc4-a7d4-4ab6-bf2e-28ca7dc6cfd3"));

        return trackRepository.getWithMap(trackIds);
    }
}
