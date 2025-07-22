package ru.smarkov.demo.domain.task;

import ru.smarkov.demo.domain.eventcost.dto.EventCostKey;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface TaskRepository {
    List<UUID> delete(Collection<? extends EventCostKey> eventCostKeys);
}
