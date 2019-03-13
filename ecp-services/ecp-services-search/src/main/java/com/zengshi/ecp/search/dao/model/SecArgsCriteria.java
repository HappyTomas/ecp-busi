package com.zengshi.ecp.search.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecArgsCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public SecArgsCriteria() {
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

        public Criteria andArgsKeyIsNull() {
            addCriterion("ARGS_KEY is null");
            return (Criteria) this;
        }

        public Criteria andArgsKeyIsNotNull() {
            addCriterion("ARGS_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andArgsKeyEqualTo(String value) {
            addCriterion("ARGS_KEY =", value, "argsKey");
            return (Criteria) this;
        }

        public Criteria andArgsKeyNotEqualTo(String value) {
            addCriterion("ARGS_KEY <>", value, "argsKey");
            return (Criteria) this;
        }

        public Criteria andArgsKeyGreaterThan(String value) {
            addCriterion("ARGS_KEY >", value, "argsKey");
            return (Criteria) this;
        }

        public Criteria andArgsKeyGreaterThanOrEqualTo(String value) {
            addCriterion("ARGS_KEY >=", value, "argsKey");
            return (Criteria) this;
        }

        public Criteria andArgsKeyLessThan(String value) {
            addCriterion("ARGS_KEY <", value, "argsKey");
            return (Criteria) this;
        }

        public Criteria andArgsKeyLessThanOrEqualTo(String value) {
            addCriterion("ARGS_KEY <=", value, "argsKey");
            return (Criteria) this;
        }

        public Criteria andArgsKeyLike(String value) {
            addCriterion("ARGS_KEY like", value, "argsKey");
            return (Criteria) this;
        }

        public Criteria andArgsKeyNotLike(String value) {
            addCriterion("ARGS_KEY not like", value, "argsKey");
            return (Criteria) this;
        }

        public Criteria andArgsKeyIn(List<String> values) {
            addCriterion("ARGS_KEY in", values, "argsKey");
            return (Criteria) this;
        }

        public Criteria andArgsKeyNotIn(List<String> values) {
            addCriterion("ARGS_KEY not in", values, "argsKey");
            return (Criteria) this;
        }

        public Criteria andArgsKeyBetween(String value1, String value2) {
            addCriterion("ARGS_KEY between", value1, value2, "argsKey");
            return (Criteria) this;
        }

        public Criteria andArgsKeyNotBetween(String value1, String value2) {
            addCriterion("ARGS_KEY not between", value1, value2, "argsKey");
            return (Criteria) this;
        }

        public Criteria andArgsNameIsNull() {
            addCriterion("ARGS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andArgsNameIsNotNull() {
            addCriterion("ARGS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andArgsNameEqualTo(String value) {
            addCriterion("ARGS_NAME =", value, "argsName");
            return (Criteria) this;
        }

        public Criteria andArgsNameNotEqualTo(String value) {
            addCriterion("ARGS_NAME <>", value, "argsName");
            return (Criteria) this;
        }

        public Criteria andArgsNameGreaterThan(String value) {
            addCriterion("ARGS_NAME >", value, "argsName");
            return (Criteria) this;
        }

        public Criteria andArgsNameGreaterThanOrEqualTo(String value) {
            addCriterion("ARGS_NAME >=", value, "argsName");
            return (Criteria) this;
        }

        public Criteria andArgsNameLessThan(String value) {
            addCriterion("ARGS_NAME <", value, "argsName");
            return (Criteria) this;
        }

        public Criteria andArgsNameLessThanOrEqualTo(String value) {
            addCriterion("ARGS_NAME <=", value, "argsName");
            return (Criteria) this;
        }

        public Criteria andArgsNameLike(String value) {
            addCriterion("ARGS_NAME like", value, "argsName");
            return (Criteria) this;
        }

        public Criteria andArgsNameNotLike(String value) {
            addCriterion("ARGS_NAME not like", value, "argsName");
            return (Criteria) this;
        }

        public Criteria andArgsNameIn(List<String> values) {
            addCriterion("ARGS_NAME in", values, "argsName");
            return (Criteria) this;
        }

        public Criteria andArgsNameNotIn(List<String> values) {
            addCriterion("ARGS_NAME not in", values, "argsName");
            return (Criteria) this;
        }

        public Criteria andArgsNameBetween(String value1, String value2) {
            addCriterion("ARGS_NAME between", value1, value2, "argsName");
            return (Criteria) this;
        }

        public Criteria andArgsNameNotBetween(String value1, String value2) {
            addCriterion("ARGS_NAME not between", value1, value2, "argsName");
            return (Criteria) this;
        }

        public Criteria andArgsValueIsNull() {
            addCriterion("ARGS_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andArgsValueIsNotNull() {
            addCriterion("ARGS_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andArgsValueEqualTo(String value) {
            addCriterion("ARGS_VALUE =", value, "argsValue");
            return (Criteria) this;
        }

        public Criteria andArgsValueNotEqualTo(String value) {
            addCriterion("ARGS_VALUE <>", value, "argsValue");
            return (Criteria) this;
        }

        public Criteria andArgsValueGreaterThan(String value) {
            addCriterion("ARGS_VALUE >", value, "argsValue");
            return (Criteria) this;
        }

        public Criteria andArgsValueGreaterThanOrEqualTo(String value) {
            addCriterion("ARGS_VALUE >=", value, "argsValue");
            return (Criteria) this;
        }

        public Criteria andArgsValueLessThan(String value) {
            addCriterion("ARGS_VALUE <", value, "argsValue");
            return (Criteria) this;
        }

        public Criteria andArgsValueLessThanOrEqualTo(String value) {
            addCriterion("ARGS_VALUE <=", value, "argsValue");
            return (Criteria) this;
        }

        public Criteria andArgsValueLike(String value) {
            addCriterion("ARGS_VALUE like", value, "argsValue");
            return (Criteria) this;
        }

        public Criteria andArgsValueNotLike(String value) {
            addCriterion("ARGS_VALUE not like", value, "argsValue");
            return (Criteria) this;
        }

        public Criteria andArgsValueIn(List<String> values) {
            addCriterion("ARGS_VALUE in", values, "argsValue");
            return (Criteria) this;
        }

        public Criteria andArgsValueNotIn(List<String> values) {
            addCriterion("ARGS_VALUE not in", values, "argsValue");
            return (Criteria) this;
        }

        public Criteria andArgsValueBetween(String value1, String value2) {
            addCriterion("ARGS_VALUE between", value1, value2, "argsValue");
            return (Criteria) this;
        }

        public Criteria andArgsValueNotBetween(String value1, String value2) {
            addCriterion("ARGS_VALUE not between", value1, value2, "argsValue");
            return (Criteria) this;
        }

        public Criteria andArgsDescIsNull() {
            addCriterion("ARGS_DESC is null");
            return (Criteria) this;
        }

        public Criteria andArgsDescIsNotNull() {
            addCriterion("ARGS_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andArgsDescEqualTo(String value) {
            addCriterion("ARGS_DESC =", value, "argsDesc");
            return (Criteria) this;
        }

        public Criteria andArgsDescNotEqualTo(String value) {
            addCriterion("ARGS_DESC <>", value, "argsDesc");
            return (Criteria) this;
        }

        public Criteria andArgsDescGreaterThan(String value) {
            addCriterion("ARGS_DESC >", value, "argsDesc");
            return (Criteria) this;
        }

        public Criteria andArgsDescGreaterThanOrEqualTo(String value) {
            addCriterion("ARGS_DESC >=", value, "argsDesc");
            return (Criteria) this;
        }

        public Criteria andArgsDescLessThan(String value) {
            addCriterion("ARGS_DESC <", value, "argsDesc");
            return (Criteria) this;
        }

        public Criteria andArgsDescLessThanOrEqualTo(String value) {
            addCriterion("ARGS_DESC <=", value, "argsDesc");
            return (Criteria) this;
        }

        public Criteria andArgsDescLike(String value) {
            addCriterion("ARGS_DESC like", value, "argsDesc");
            return (Criteria) this;
        }

        public Criteria andArgsDescNotLike(String value) {
            addCriterion("ARGS_DESC not like", value, "argsDesc");
            return (Criteria) this;
        }

        public Criteria andArgsDescIn(List<String> values) {
            addCriterion("ARGS_DESC in", values, "argsDesc");
            return (Criteria) this;
        }

        public Criteria andArgsDescNotIn(List<String> values) {
            addCriterion("ARGS_DESC not in", values, "argsDesc");
            return (Criteria) this;
        }

        public Criteria andArgsDescBetween(String value1, String value2) {
            addCriterion("ARGS_DESC between", value1, value2, "argsDesc");
            return (Criteria) this;
        }

        public Criteria andArgsDescNotBetween(String value1, String value2) {
            addCriterion("ARGS_DESC not between", value1, value2, "argsDesc");
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