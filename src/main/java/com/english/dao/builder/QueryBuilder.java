package com.english.dao.builder;

import java.util.ArrayList;

public class QueryBuilder {
    private String sql = "";
    private ArrayList<Object> parameters = new ArrayList<>();

    public static class Builder {
        private QueryBuilder newQueryBuilder;

        public Builder() {
            newQueryBuilder = new QueryBuilder();
        }

        public Builder addSql(String sql) {
            newQueryBuilder.sql += sql;
            return this;
        }

        public Builder where() {
            newQueryBuilder.sql += " WHERE ";
            return this;
        }

        public Builder and() {
            if (!newQueryBuilder.sql.contains("WHERE")) {
                return where();
            }
            newQueryBuilder.sql += " AND ";
            return this;
        }


        public void addParameter(Object parameter) {
            newQueryBuilder.parameters.add(parameter);
        }

        public QueryBuilder build() {
            return newQueryBuilder;
        }
    }

    public String getSql() {
        return sql;
    }

    public Object[] getParameters() {
        return parameters.toArray();
    }
}
