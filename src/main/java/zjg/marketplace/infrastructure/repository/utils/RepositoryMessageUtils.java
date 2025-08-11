package zjg.marketplace.infrastructure.repository.utils;

public class RepositoryMessageUtils {
    private RepositoryMessageUtils() {}

    public static String infoInsert(String id) {
        return "Inserting the entity with id=[%s] into the database!".formatted(id);
    }
    public static String errorInsert(String id, String message) {
        return  "Occurred an error on insert the entity into repository! EntityId=[%s]\nMessage=[%s]".formatted(id, message);
    }
    public static String successInsert(String id) {
        return "Success during entity insertion into the database! Id=[%s]".formatted(id);
    }

    public static String infoUpdate(String id) {
        return "Updating order with id=[%s]!".formatted(id);
    }

    public static String errorUpdate(String id, String message) {
        return "Occurred an error on update the entity into the repository! EntityId=[%s]\nMessage=[%s]".formatted(id, message);
    }

    public static String successUpdate(String id) {
        return "Success during entity update into the database! Id=[%s]".formatted(id);
    }

    public static String infoDelete(String id) {
        return "Deleting entity with id=[%s] into the database!".formatted(id);
    }

    public static String errorDelete(String id, String message) {
        return "Occurred an error on delete the entity into the database! EntityId=[%s]\nMessage=[%s]".formatted(id, message);
    }

    public static String successDelete(String id) {
        return "Success during entity removal into the database! Id=[%s]".formatted(id);
    }

    public static String infoQuery(String identifier) {
        return "Querying the entity by=[%s] when search parameter into the database!";
    }

    public static String errorQuery(String identifier) {
        return "Error on querying an entity where contains the [%s] when search parameter into the database!";
    }
    public static String errorQuery(String identifier, String message) {
        return errorQuery(identifier).concat("Error message=[%s]".formatted(message));
    }

    public static String successQuery(String id) {
        return "Successfully inserting the entity into the database";
    }
    public static String infoQueryByOffsetAndLimit(Long offset, Integer limit) {
        return "Querying by order pagination: Offset=[%s] Limit=[%s]".formatted(offset, limit);
    }
    public static String errorQueryByOffsetAndLimit(Long offset, Integer limit) {
        return "Error on querying pagination -> | Offset=[%s] - Limit=[%s] |".formatted(offset, limit);
    }
    public static String successQueryByOffsetAndLimit(Long offset, Integer limit) {
        return "Pagination successfully queried!";
    }
}
