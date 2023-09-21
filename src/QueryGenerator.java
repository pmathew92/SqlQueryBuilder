import helper.FileValidator;
import interfaces.JsonDataSource;
import data.JsonDataSourceImpl;
import model.Query;

import java.util.Scanner;

public class QueryGenerator {

    public static void main(String[] args) {
        JsonDataSource dataSource = new JsonDataSourceImpl();
        System.out.println("Enter the json file path:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        Query query = dataSource.fetchJsonData(s);
        if (query == null) {
            throw new RuntimeException("Error while generating query");
        }


    }
}