import data.JsonDataSourceImpl;
import interfaces.JsonDataSource;
import model.Query;
import querybuilder.JoinQueryBuilder;
import querybuilder.QueryBuilder;
import querybuilder.WhereConditionQueryBuilder;

import java.util.Scanner;

public class QueryGenerator {

    public static void main(String[] args) {
        JsonDataSource dataSource = new JsonDataSourceImpl();
        QueryBuilder queryBuilder = new QueryBuilder(new JoinQueryBuilder(), new WhereConditionQueryBuilder());

        System.out.println("Enter the json file path:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        Query query = dataSource.fetchJsonData(s);
        if (query == null) {
            throw new NullPointerException("Exception: No query instance created while parsing the json");
        }

        System.out.println(queryBuilder.getSqlQuery(query));
    }
}