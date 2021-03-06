package com.zengshi.ecp.sys.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseParamDynamicCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public BaseParamDynamicCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andParamLinkKeyIsNull() {
            addCriterion("PARAM_LINK_KEY is null");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyIsNotNull() {
            addCriterion("PARAM_LINK_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyEqualTo(String value) {
            addCriterion("PARAM_LINK_KEY =", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyNotEqualTo(String value) {
            addCriterion("PARAM_LINK_KEY <>", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyGreaterThan(String value) {
            addCriterion("PARAM_LINK_KEY >", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_LINK_KEY >=", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyLessThan(String value) {
            addCriterion("PARAM_LINK_KEY <", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyLessThanOrEqualTo(String value) {
            addCriterion("PARAM_LINK_KEY <=", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyLike(String value) {
            addCriterion("PARAM_LINK_KEY like", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyNotLike(String value) {
            addCriterion("PARAM_LINK_KEY not like", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyIn(List<String> values) {
            addCriterion("PARAM_LINK_KEY in", values, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyNotIn(List<String> values) {
            addCriterion("PARAM_LINK_KEY not in", values, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyBetween(String value1, String value2) {
            addCriterion("PARAM_LINK_KEY between", value1, value2, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyNotBetween(String value1, String value2) {
            addCriterion("PARAM_LINK_KEY not between", value1, value2, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamNameIsNull() {
            addCriterion("PARAM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andParamNameIsNotNull() {
            addCriterion("PARAM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andParamNameEqualTo(String value) {
            addCriterion("PARAM_NAME =", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotEqualTo(String value) {
            addCriterion("PARAM_NAME <>", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameGreaterThan(String value) {
            addCriterion("PARAM_NAME >", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_NAME >=", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLessThan(String value) {
            addCriterion("PARAM_NAME <", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLessThanOrEqualTo(String value) {
            addCriterion("PARAM_NAME <=", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLike(String value) {
            addCriterion("PARAM_NAME like", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotLike(String value) {
            addCriterion("PARAM_NAME not like", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameIn(List<String> values) {
            addCriterion("PARAM_NAME in", values, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotIn(List<String> values) {
            addCriterion("PARAM_NAME not in", values, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameBetween(String value1, String value2) {
            addCriterion("PARAM_NAME between", value1, value2, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotBetween(String value1, String value2) {
            addCriterion("PARAM_NAME not between", value1, value2, "paramName");
            return (Criteria) this;
        }

        public Criteria andSqlLoadIsNull() {
            addCriterion("SQL_LOAD is null");
            return (Criteria) this;
        }

        public Criteria andSqlLoadIsNotNull() {
            addCriterion("SQL_LOAD is not null");
            return (Criteria) this;
        }

        public Criteria andSqlLoadEqualTo(String value) {
            addCriterion("SQL_LOAD =", value, "sqlLoad");
            return (Criteria) this;
        }

        public Criteria andSqlLoadNotEqualTo(String value) {
            addCriterion("SQL_LOAD <>", value, "sqlLoad");
            return (Criteria) this;
        }

        public Criteria andSqlLoadGreaterThan(String value) {
            addCriterion("SQL_LOAD >", value, "sqlLoad");
            return (Criteria) this;
        }

        public Criteria andSqlLoadGreaterThanOrEqualTo(String value) {
            addCriterion("SQL_LOAD >=", value, "sqlLoad");
            return (Criteria) this;
        }

        public Criteria andSqlLoadLessThan(String value) {
            addCriterion("SQL_LOAD <", value, "sqlLoad");
            return (Criteria) this;
        }

        public Criteria andSqlLoadLessThanOrEqualTo(String value) {
            addCriterion("SQL_LOAD <=", value, "sqlLoad");
            return (Criteria) this;
        }

        public Criteria andSqlLoadLike(String value) {
            addCriterion("SQL_LOAD like", value, "sqlLoad");
            return (Criteria) this;
        }

        public Criteria andSqlLoadNotLike(String value) {
            addCriterion("SQL_LOAD not like", value, "sqlLoad");
            return (Criteria) this;
        }

        public Criteria andSqlLoadIn(List<String> values) {
            addCriterion("SQL_LOAD in", values, "sqlLoad");
            return (Criteria) this;
        }

        public Criteria andSqlLoadNotIn(List<String> values) {
            addCriterion("SQL_LOAD not in", values, "sqlLoad");
            return (Criteria) this;
        }

        public Criteria andSqlLoadBetween(String value1, String value2) {
            addCriterion("SQL_LOAD between", value1, value2, "sqlLoad");
            return (Criteria) this;
        }

        public Criteria andSqlLoadNotBetween(String value1, String value2) {
            addCriterion("SQL_LOAD not between", value1, value2, "sqlLoad");
            return (Criteria) this;
        }

        public Criteria andDbNameIsNull() {
            addCriterion("DB_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDbNameIsNotNull() {
            addCriterion("DB_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDbNameEqualTo(String value) {
            addCriterion("DB_NAME =", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameNotEqualTo(String value) {
            addCriterion("DB_NAME <>", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameGreaterThan(String value) {
            addCriterion("DB_NAME >", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameGreaterThanOrEqualTo(String value) {
            addCriterion("DB_NAME >=", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameLessThan(String value) {
            addCriterion("DB_NAME <", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameLessThanOrEqualTo(String value) {
            addCriterion("DB_NAME <=", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameLike(String value) {
            addCriterion("DB_NAME like", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameNotLike(String value) {
            addCriterion("DB_NAME not like", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameIn(List<String> values) {
            addCriterion("DB_NAME in", values, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameNotIn(List<String> values) {
            addCriterion("DB_NAME not in", values, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameBetween(String value1, String value2) {
            addCriterion("DB_NAME between", value1, value2, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameNotBetween(String value1, String value2) {
            addCriterion("DB_NAME not between", value1, value2, "dbName");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("TABLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("TABLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("TABLE_NAME =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("TABLE_NAME <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("TABLE_NAME >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("TABLE_NAME >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("TABLE_NAME <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("TABLE_NAME <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("TABLE_NAME like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("TABLE_NAME not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("TABLE_NAME in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("TABLE_NAME not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("TABLE_NAME between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("TABLE_NAME not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andLoadTypeIsNull() {
            addCriterion("LOAD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andLoadTypeIsNotNull() {
            addCriterion("LOAD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andLoadTypeEqualTo(String value) {
            addCriterion("LOAD_TYPE =", value, "loadType");
            return (Criteria) this;
        }

        public Criteria andLoadTypeNotEqualTo(String value) {
            addCriterion("LOAD_TYPE <>", value, "loadType");
            return (Criteria) this;
        }

        public Criteria andLoadTypeGreaterThan(String value) {
            addCriterion("LOAD_TYPE >", value, "loadType");
            return (Criteria) this;
        }

        public Criteria andLoadTypeGreaterThanOrEqualTo(String value) {
            addCriterion("LOAD_TYPE >=", value, "loadType");
            return (Criteria) this;
        }

        public Criteria andLoadTypeLessThan(String value) {
            addCriterion("LOAD_TYPE <", value, "loadType");
            return (Criteria) this;
        }

        public Criteria andLoadTypeLessThanOrEqualTo(String value) {
            addCriterion("LOAD_TYPE <=", value, "loadType");
            return (Criteria) this;
        }

        public Criteria andLoadTypeLike(String value) {
            addCriterion("LOAD_TYPE like", value, "loadType");
            return (Criteria) this;
        }

        public Criteria andLoadTypeNotLike(String value) {
            addCriterion("LOAD_TYPE not like", value, "loadType");
            return (Criteria) this;
        }

        public Criteria andLoadTypeIn(List<String> values) {
            addCriterion("LOAD_TYPE in", values, "loadType");
            return (Criteria) this;
        }

        public Criteria andLoadTypeNotIn(List<String> values) {
            addCriterion("LOAD_TYPE not in", values, "loadType");
            return (Criteria) this;
        }

        public Criteria andLoadTypeBetween(String value1, String value2) {
            addCriterion("LOAD_TYPE between", value1, value2, "loadType");
            return (Criteria) this;
        }

        public Criteria andLoadTypeNotBetween(String value1, String value2) {
            addCriterion("LOAD_TYPE not between", value1, value2, "loadType");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andSpDescIsNull() {
            addCriterion("SP_DESC is null");
            return (Criteria) this;
        }

        public Criteria andSpDescIsNotNull() {
            addCriterion("SP_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andSpDescEqualTo(String value) {
            addCriterion("SP_DESC =", value, "spDesc");
            return (Criteria) this;
        }

        public Criteria andSpDescNotEqualTo(String value) {
            addCriterion("SP_DESC <>", value, "spDesc");
            return (Criteria) this;
        }

        public Criteria andSpDescGreaterThan(String value) {
            addCriterion("SP_DESC >", value, "spDesc");
            return (Criteria) this;
        }

        public Criteria andSpDescGreaterThanOrEqualTo(String value) {
            addCriterion("SP_DESC >=", value, "spDesc");
            return (Criteria) this;
        }

        public Criteria andSpDescLessThan(String value) {
            addCriterion("SP_DESC <", value, "spDesc");
            return (Criteria) this;
        }

        public Criteria andSpDescLessThanOrEqualTo(String value) {
            addCriterion("SP_DESC <=", value, "spDesc");
            return (Criteria) this;
        }

        public Criteria andSpDescLike(String value) {
            addCriterion("SP_DESC like", value, "spDesc");
            return (Criteria) this;
        }

        public Criteria andSpDescNotLike(String value) {
            addCriterion("SP_DESC not like", value, "spDesc");
            return (Criteria) this;
        }

        public Criteria andSpDescIn(List<String> values) {
            addCriterion("SP_DESC in", values, "spDesc");
            return (Criteria) this;
        }

        public Criteria andSpDescNotIn(List<String> values) {
            addCriterion("SP_DESC not in", values, "spDesc");
            return (Criteria) this;
        }

        public Criteria andSpDescBetween(String value1, String value2) {
            addCriterion("SP_DESC between", value1, value2, "spDesc");
            return (Criteria) this;
        }

        public Criteria andSpDescNotBetween(String value1, String value2) {
            addCriterion("SP_DESC not between", value1, value2, "spDesc");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
