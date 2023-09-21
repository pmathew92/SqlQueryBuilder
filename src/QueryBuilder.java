import helper.Constants;
import model.Query;

public class QueryBuilder {

    private QueryParser queryParser;

    public QueryBuilder(QueryParser parser) {
        queryParser = parser;
    }

    public String getSqlQuery(Query query) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(query.getQueryType());
        stringBuilder.append(Constants.SPACE);
        for (String field : query.getFields()) {
            stringBuilder.append(field);
            stringBuilder.append(Constants.COMMA);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(Constants.FROM);
        stringBuilder.append(Constants.SPACE);

        stringBuilder.append(query.getTableName());
        stringBuilder.append(Constants.SEMI_COLON);

//        return queryParser.parseQueryObjectToSql();

        return stringBuilder.toString();
    }
}
