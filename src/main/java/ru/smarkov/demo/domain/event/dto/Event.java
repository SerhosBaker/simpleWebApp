package ru.smarkov.demo.domain.event.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "event", schema = "smarkov")
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    private UUID id;

    private String position;

    public UUID getId() {
        return this.id;
    }

    public String getPosition() {
        return this.position;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
//
//        CREATE SCHEMA smarkov;
//
//        create table if not exists smarkov.event
//        (
//        id       uuid,
//        position bit varying(5) not null
//        );
//
//        select *
//        from smarkov.event;
//
//        insert into smarkov.event (id, "position")
//        values ('320dfdc4-a7d4-4ab6-bf2e-28ca7dc6cfd2', B'10101');
