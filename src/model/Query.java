package model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Query {

    @SerializedName("queryType")
    @Expose
    private String queryType;
    @SerializedName("fields")
    @Expose
    private List<String> fields;
    @SerializedName("tableName")
    @Expose
    private String tableName;
    @SerializedName("columns")
    @Expose
    private List<Column> columns;
    @SerializedName("join")
    @Expose
    private List<Join> join;

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Join> getJoin() {
        return join;
    }

    public void setJoin(List<Join> join) {
        this.join = join;
    }

}