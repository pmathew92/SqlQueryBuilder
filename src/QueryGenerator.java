import data.JsonDataSourceImpl;
import interfaces.JsonDataSource;
import model.Query;

import java.util.Scanner;

public class QueryGenerator {

    public static void main(String[] args) {
        JsonDataSource dataSource = new JsonDataSourceImpl();
        QueryBuilder queryBuilder = new QueryBuilder(new QueryParser());
        //TODO
//        System.out.println("Enter the json file path:");
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine();
        Query query = dataSource.fetchJsonData("input2.json");
        if (query == null) {
            throw new RuntimeException("Error while generating query");
        }

        System.out.println(queryBuilder.getSqlQuery(query));
    }
}