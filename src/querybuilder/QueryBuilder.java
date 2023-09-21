package querybuilder;

import helper.Constants;
import model.Query;

public class QueryBuilder {

    private final JoinQueryBuilder joinQueryBuilder;
    private final WhereConditionQueryBuilder whereConditionQueryBuilder;


    public QueryBuilder(JoinQueryBuilder joinQueryBuilder, WhereConditionQueryBuilder whereConditionQueryBuilder) {
        this.joinQueryBuilder = joinQueryBuilder;
        this.whereConditionQueryBuilder = whereConditionQueryBuilder;
    }

    /**
     * Parses the Query instance to create a SQL query string
     *
     * @param query An instance of [Query] created from the json file
     * @return Sql query string
     */
    public String getSqlQuery(Query query) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(query.getQueryType());
        stringBuilder.append(Constants.SPACE);
        for (String field : query.getFields()) {
            stringBuilder.append(field);
            stringBuilder.append(Constants.COMMA);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(Constants.SPACE).append(Constants.FROM).append(Constants.SPACE)
                .append(query.getTableName()).append(Constants.SPACE);

        joinQueryBuilder.buildJoinConditionQuery(query.getJoin(), stringBuilder);
        stringBuilder.append(Constants.SPACE);
        whereConditionQueryBuilder.buildWhereConditionQuery(query.getColumns(), stringBuilder);
        stringBuilder.append(Constants.SEMI_COLON);

        return stringBuilder.toString();
    }
}

