import helper.Constants;
import helper.Validator;
import model.Column;
import model.Join;
import model.Query;

import java.util.List;
import java.util.Locale;

public class QueryBuilder {

    private String[] supportedLogicalOperators = new String[]{"IN", "BETWEEN", "LIKE",};
    private String[] supportedComparisonOperators = new String[]{"=", ">", "<", ">=", "<=", "<>"};
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
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(getJoinConditions(query.getJoin()));
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(getWhereConditions(query.getColumns()));
        stringBuilder.append(Constants.SEMI_COLON);

//        return queryParser.parseQueryObjectToSql();

        return stringBuilder.toString();
    }


    private String getWhereConditions(List<Column> columnList) {

        StringBuilder builder = new StringBuilder();
        builder.append(Constants.WHERE);

        for (Column column : columnList) {
            if (!(Validator.isValidOperator(supportedLogicalOperators, column.getOperator()) || Validator.isValidOperator(supportedComparisonOperators, column.getOperator()))) {
                throw new RuntimeException("Invalid operator passed");
            }
            switch (column.getOperator().toUpperCase(Locale.ROOT)) {
                case "BETWEEN":
                    builder.append(betweenOperator(column));
                    break;
                case "LIKE":
                    builder.append(likeOperator(column));
                    break;
                case "IN":
                    builder.append(inOperator(column));
                    break;
                default:
                    builder.append(comparisonOperator(column));
            }

            builder.append(Constants.AND);

        }
        builder.delete(builder.length() - 5, builder.length() - 1);
        return builder.toString();
    }

    private String getJoinConditions(List<Join> joinList) {

        StringBuilder builder = new StringBuilder();

        if (joinList.size() > 0) {

            // Handling only one join functionality for now.
            Join join = joinList.get(0);

            // Check if operator is a comparison operator
            if (Validator.isValidOperator(supportedComparisonOperators, join.getOperator())) {
                if (join.getJoinType() == null) {
                    builder.append(", ").append(join.getJoinTableName()).append(" WHERE ");
                    builder.append(join.getField1() + " " + join.getOperator() + " " + join.getField2());
                } else {
                    builder.append(" ").append(join.getJoinType()).append(" ").append(join.getJoinTableName()).append(" ON ");
                    builder.append(join.getField1()).append(" ").append(join.getOperator()).append(" ").append(join.getField2());
                }
            }
        }

        return builder.toString();

    }


    private String betweenOperator(Column column) {
        StringBuilder builder = new StringBuilder();
        if (column.getFieldValue().size() != 2) {
            throw new RuntimeException("Exception: Between operator needs two fields to compare ");
        }
        builder.append(column.getFieldName()).append(Constants.SPACE).append(supportedLogicalOperators[1]).append(column.getFieldValue().get(0)).append(Constants.AND).append(column.getFieldValue().get(1));
        return builder.toString();
    }

    private String likeOperator(Column column) {
        return column.getFieldName() + Constants.LIKE + column.getFieldValue().get(0);
    }

    private String inOperator(Column column) {

        if (column.getFieldValue().size() < 1) {
            throw new RuntimeException("Exception: IN Operation expects atleast 1 fieldValue");
        }
        StringBuilder builder = new StringBuilder();
        builder.append(column.getFieldName() + " IN (");

        for (int i = 0; i < column.getFieldValue().size(); ++i) {
            Object value = column.getFieldValue().get(i);
            if (value instanceof Integer) {
                builder.append((Integer) value);
            } else if (value instanceof Long) {
                builder.append((Long) value);
            } else if (value instanceof Double) {
                builder.append((Double) value);
            } else if (value instanceof String) {
                builder.append(" \'" + (String) value + "\'");
            } else {
                throw new RuntimeException(" IN Parser encountered Unsupported type ");
            }
            if (i != (column.getFieldValue().size() - 1)) {
                builder.append(",");
            }
        }
        // Close the bracket
        builder.append(")");
        return builder.toString();
    }

    private String comparisonOperator(Column column) {
        Object value = column.getFieldValue().get(0);
        String miniQuery = column.getFieldName() + " " + column.getOperator() + " ";
        if (value instanceof String) {
            miniQuery += "\'" + (String) value + "\'";
        } else {
            miniQuery += (String) value;
        }

        return miniQuery;
    }
}

