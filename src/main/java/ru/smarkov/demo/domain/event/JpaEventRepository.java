package ru.smarkov.demo.domain.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.smarkov.demo.domain.event.dto.Event;

import java.util.Optional;
import java.util.UUID;

public interface JpaEventRepository extends JpaRepository<Event, UUID> {
    Optional<Event> findById(UUID id);

    Event save(Event event);

    @Modifying
    @Query(value = "INSERT INTO smarkov.event (id, position) VALUES (:id, cast(:position as bit varying))", nativeQuery = true)
    void insertEvent(@Param("position") String position, @Param("id") UUID id);
}
