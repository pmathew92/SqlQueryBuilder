package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Column {

    @SerializedName("operator")
    @Expose
    private String operator;
    @SerializedName("fieldName")
    @Expose
    private String fieldName;
    @SerializedName("fieldValue")
    @Expose
    private List<Object> fieldValue;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<Object> getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(List<Object> fieldValue) {
        this.fieldValue = fieldValue;
    }

}
