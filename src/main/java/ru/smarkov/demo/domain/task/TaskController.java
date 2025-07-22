package ru.smarkov.demo.domain.task;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smarkov.demo.domain.eventcost.dto.EventCost;
import ru.smarkov.demo.domain.eventcost.dto.EventCostKey;
import ru.smarkov.demo.domain.track.TrackRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/all/delete")
    public List<UUID> getAllTaskIds() {
        String eventName = "event1";
        String skillName = "skill1";

        return taskRepository.delete(List.of(new EventCost(eventName, skillName)));
    }
}
