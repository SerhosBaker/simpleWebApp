package ru.smarkov.demo.domain.eventcost.dto;

public record EventCost(String eventName, String skillName) implements EventCostKey {
    @Override
    public String eventName() {
        return eventName;
    }

    @Override
    public String skillName() {
        return skillName;
    }
}
