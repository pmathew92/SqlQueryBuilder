package querybuilder;

import helper.Constants;
import helper.ValidatorUtil;
import model.Column;

import java.util.List;
import java.util.Locale;

public class WhereConditionQueryBuilder {

    private final String[] logicalOperators = new String[]{"IN", "BETWEEN", "LIKE",};
    private final String[] comparisonOperators = new String[]{"=", ">", "<", ">=", "<=", "<>"};


    /**
     * Build the where condition for the query.
     *
     * @param columnList
     * @param builder
     */
    public void buildWhereConditionQuery(List<Column> columnList, StringBuilder builder) {

        builder.append(Constants.WHERE);

        for (int i = 0; i < columnList.size(); i++) {
            Column column = columnList.get(i);
            if (!(ValidatorUtil.isValidOperator(logicalOperators, column.getOperator()) || ValidatorUtil.isValidOperator(comparisonOperators, column.getOperator()))) {
                throw new IllegalArgumentException("Exception : Invalid operator passed for where condition " + column.getOperator());
            }
            switch (column.getOperator().toUpperCase(Locale.ROOT)) {
                case "BETWEEN":
                    buildBetweenOperatorQuery(column, builder);
                    break;
                case "LIKE":
                    buildLikeOperatorQuery(column, builder);
                    break;
                case "IN":
                    buildInOperatorQuery(column, builder);
                    break;
                default:
                    buildComparisonOperatorQuery(column, builder);
            }

            if (i != columnList.size() - 1) {
                builder.append(Constants.AND);
            }
        }
    }

    private void buildBetweenOperatorQuery(Column column, StringBuilder builder) {
        if (column.getFieldValue().size() != 2) {
            throw new IllegalArgumentException("Exception: BETWEEN operator needs two fields to compare. Current fields size is " + column.getFieldValue().size());
        }
        builder.append(column.getFieldName()).append(Constants.SPACE).append(logicalOperators[1]).append(Constants.SPACE).append(column.getFieldValue().get(0)).append(Constants.AND).append(column.getFieldValue().get(1));
    }

    private void buildLikeOperatorQuery(Column column, StringBuilder builder) {
        if (column.getFieldValue().size() == 0) {
            throw new IllegalArgumentException("Exception: No fields passed to perform LIKE operation");
        }
        builder.append(column.getFieldName()).append(Constants.LIKE).append(column.getFieldValue().get(0));
    }

    private void buildInOperatorQuery(Column column, StringBuilder builder) {
        if (column.getFieldValue().size() == 0) {
            throw new IllegalArgumentException("Exception: No fields passed to perform IN operation");
        }
        builder.append(column.getFieldName()).append(Constants.IN).append(Constants.OPEN_BRACKET);

        for (int i = 0; i < column.getFieldValue().size(); ++i) {
            Object value = column.getFieldValue().get(i);
            if (value instanceof Integer) {
                builder.append(value);
            } else if (value instanceof Double) {
                builder.append(value);
            } else if (value instanceof String) {
                builder.append("'").append(value).append("'");
            } else {
                throw new RuntimeException(" IN Parser encountered Unsupported type ");
            }
            if (i != (column.getFieldValue().size() - 1)) {
                builder.append(Constants.COMMA);
            }
        }
        builder.append(Constants.CLOSED_BRACKET);
    }

    private void buildComparisonOperatorQuery(Column column, StringBuilder builder) {
        if (column.getFieldValue().size() == 0) {
            throw new IllegalArgumentException("Exception: No fields passed to perform COMPARISON operation");
        }
        Object value = column.getFieldValue().get(0);
        builder.append(column.getFieldName()).append(Constants.SPACE).append(column.getOperator()).append(Constants.SPACE);
        if (value instanceof String) {
            builder.append("'").append((String) value).append("'");
        } else {
            builder.append(value);
        }
    }
}
