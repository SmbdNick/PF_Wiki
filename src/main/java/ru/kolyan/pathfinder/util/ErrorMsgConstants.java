package ru.kolyan.pathfinder.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class ErrorMsgConstants {
    private static final String ENTITY_NOT_FOUND_MSG_FORMAT = "%s с id: %s не найден";
    private static final String ENTITY_CONFLICT_MSG_FORMAT = "%s с именем %s уже существует";
    private static final String ENTITY_BAD_REQUEST_MSG_FORMAT = "Отсутствует такая сущность %s в связи с такой сущностью %s";


    public String notFound(String entity, UUID id) {
        return String.format(ENTITY_NOT_FOUND_MSG_FORMAT, entity, id.toString());
    }

    public String conflict(String entity, String name) {
        return String.format(ENTITY_CONFLICT_MSG_FORMAT, entity, name);
    }

    public String badRequest(String entity1, String entity2) {
        return String.format(ENTITY_BAD_REQUEST_MSG_FORMAT, entity1, entity2);
    }
}
