package querybuilder;

import helper.Constants;
import helper.ValidatorUtil;
import model.Join;

import java.util.List;

public class JoinQueryBuilder {

    private final String[] comparisonOperators = new String[]{"=", ">", "<", ">=", "<=", "<>"};

    /**
     * Build the join condition for the query if the json contains join data
     *
     * @param joinConditionList
     * @param builder
     */
    public void buildJoinConditionQuery(List<Join> joinConditionList, StringBuilder builder) {

        if (joinConditionList.size() > 0) {
            for (Join join : joinConditionList) {
                if (!ValidatorUtil.isValidOperator(comparisonOperators, join.getOperator())) {
                    throw new IllegalArgumentException("Exception: Not a valid comparison operator " + join.getOperator());
                }
                builder.append(Constants.SPACE).append(join.getJoinType()).append(Constants.SPACE).append(join.getJoinTable()).append(Constants.ON);
                builder.append(join.getField1()).append(Constants.SPACE).append(join.getOperator()).append(Constants.SPACE).append(join.getField2());
            }
        }
    }
}
