package helper;

public class ValidatorUtil {

    public static boolean isValidJsonFile(String path) {
        return path.endsWith(Constants.JSON_EXTENSION);
    }


    public static boolean isValidOperator(String[] arr, String target) {
        for (String item : arr) {
            if (item.equals(target)) return true;
        }
        return false;
    }
}
