package zjg.marketplace.infrastructure.storage.utils;

public class StorageMessageUtils {
    private StorageMessageUtils() {}

    // * Uploading -----------------------------------------------------------------
    public static String infoUploadingFile(String path) {
        return "Uploading file on path=[%s]".formatted(path);
    }
    public static String successUploadingFile(String path) {
        return "File sent successfully on path=[%s]!".formatted(path);
    }
    public static String errorUploadingFile(String path) {
        return "An error occurred while loading this file at path=[%s]".formatted(path);
    }
    public static String errorUploadingFile(String path, String message) {
        return errorUploadingFile(path)
                .concat(" Error message=[%s]".formatted(message));
    }

    // * Searching -------------------------------------------------------------------
    public static String infoSearchingFile(String path) {
        return "Querying files at path=[%s]".formatted(path);
    }
    public static String successSearchingFile(String path) {
        return "Query completed success at path=[%s]".formatted(path);
    }
    public static String errorSearchingFile(String path) {
        return "Occurred an error on searching files in path=[%s]!".formatted(path);
    }
    public static String errorSearchingFile(String path, String message) {
        return errorUploadingFile(path).concat(" Error message=[%s].".formatted(message));
    }

   // * Deleting -------------------------------------------------------------------------
    public static String infoRemovingFile(String path) {
        return "Removing file in the path=[%s]!".formatted(path);
    }
    public static String successRemovingFile(String path) {
        return "File successfully removed at path=[%s]!".formatted(path);
    }
    public static String errorRemovingFile(String path) {
        return "An error occurred while removing file at path=[%s]!".formatted(path);
    }
    public static String errorRemovingFile(String path, String message) {
        return errorRemovingFile(path).concat(" Error message=[%s]".formatted(message));
    }
}
