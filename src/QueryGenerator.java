import helper.FileValidator;
import interfaces.JsonDataSource;
import data.JsonDataSourceImpl;

import java.util.Scanner;

public class QueryGenerator {

    public static void main(String[] args) {
        JsonDataSource dataSource = new JsonDataSourceImpl(new FileValidator());
        System.out.println("Enter the json file path:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        dataSource.fetchJsonData(s);
    }
}