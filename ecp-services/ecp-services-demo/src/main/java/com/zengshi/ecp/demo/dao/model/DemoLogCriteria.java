package com.zengshi.ecp.demo.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DemoLogCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public DemoLogCriteria() {
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

        public Criteria andLogIdIsNull() {
            addCriterion("LOG_ID is null");
            return (Criteria) this;
        }

        public Criteria andLogIdIsNotNull() {
            addCriterion("LOG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLogIdEqualTo(Long value) {
            addCriterion("LOG_ID =", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotEqualTo(Long value) {
            addCriterion("LOG_ID <>", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThan(Long value) {
            addCriterion("LOG_ID >", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThanOrEqualTo(Long value) {
            addCriterion("LOG_ID >=", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThan(Long value) {
            addCriterion("LOG_ID <", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThanOrEqualTo(Long value) {
            addCriterion("LOG_ID <=", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdIn(List<Long> values) {
            addCriterion("LOG_ID in", values, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotIn(List<Long> values) {
            addCriterion("LOG_ID not in", values, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdBetween(Long value1, Long value2) {
            addCriterion("LOG_ID between", value1, value2, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotBetween(Long value1, Long value2) {
            addCriterion("LOG_ID not between", value1, value2, "logId");
            return (Criteria) this;
        }

        public Criteria andLogInfoIsNull() {
            addCriterion("LOG_INFO is null");
            return (Criteria) this;
        }

        public Criteria andLogInfoIsNotNull() {
            addCriterion("LOG_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andLogInfoEqualTo(String value) {
            addCriterion("LOG_INFO =", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoNotEqualTo(String value) {
            addCriterion("LOG_INFO <>", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoGreaterThan(String value) {
            addCriterion("LOG_INFO >", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoGreaterThanOrEqualTo(String value) {
            addCriterion("LOG_INFO >=", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoLessThan(String value) {
            addCriterion("LOG_INFO <", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoLessThanOrEqualTo(String value) {
            addCriterion("LOG_INFO <=", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoLike(String value) {
            addCriterion("LOG_INFO like", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoNotLike(String value) {
            addCriterion("LOG_INFO not like", value, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoIn(List<String> values) {
            addCriterion("LOG_INFO in", values, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoNotIn(List<String> values) {
            addCriterion("LOG_INFO not in", values, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoBetween(String value1, String value2) {
            addCriterion("LOG_INFO between", value1, value2, "logInfo");
            return (Criteria) this;
        }

        public Criteria andLogInfoNotBetween(String value1, String value2) {
            addCriterion("LOG_INFO not between", value1, value2, "logInfo");
            return (Criteria) this;
        }

        public Criteria andDbCodeIsNull() {
            addCriterion("DB_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDbCodeIsNotNull() {
            addCriterion("DB_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDbCodeEqualTo(String value) {
            addCriterion("DB_CODE =", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeNotEqualTo(String value) {
            addCriterion("DB_CODE <>", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeGreaterThan(String value) {
            addCriterion("DB_CODE >", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DB_CODE >=", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeLessThan(String value) {
            addCriterion("DB_CODE <", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeLessThanOrEqualTo(String value) {
            addCriterion("DB_CODE <=", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeLike(String value) {
            addCriterion("DB_CODE like", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeNotLike(String value) {
            addCriterion("DB_CODE not like", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeIn(List<String> values) {
            addCriterion("DB_CODE in", values, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeNotIn(List<String> values) {
            addCriterion("DB_CODE not in", values, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeBetween(String value1, String value2) {
            addCriterion("DB_CODE between", value1, value2, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeNotBetween(String value1, String value2) {
            addCriterion("DB_CODE not between", value1, value2, "dbCode");
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
