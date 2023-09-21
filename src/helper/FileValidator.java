package helper;

import java.io.File;

public class FileValidator {

    public static boolean isValidJsonFile(String path) {
        return path.endsWith(Constants.JSON_EXTENSION);
    }
}
