package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataFieldRuleCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public DataFieldRuleCriteria() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(Long value) {
            addCriterion("ITEM_ID =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(Long value) {
            addCriterion("ITEM_ID <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(Long value) {
            addCriterion("ITEM_ID >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ITEM_ID >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(Long value) {
            addCriterion("ITEM_ID <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(Long value) {
            addCriterion("ITEM_ID <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<Long> values) {
            addCriterion("ITEM_ID in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<Long> values) {
            addCriterion("ITEM_ID not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(Long value1, Long value2) {
            addCriterion("ITEM_ID between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(Long value1, Long value2) {
            addCriterion("ITEM_ID not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andAuthIdIsNull() {
            addCriterion("AUTH_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuthIdIsNotNull() {
            addCriterion("AUTH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuthIdEqualTo(Long value) {
            addCriterion("AUTH_ID =", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotEqualTo(Long value) {
            addCriterion("AUTH_ID <>", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdGreaterThan(Long value) {
            addCriterion("AUTH_ID >", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdGreaterThanOrEqualTo(Long value) {
            addCriterion("AUTH_ID >=", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdLessThan(Long value) {
            addCriterion("AUTH_ID <", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdLessThanOrEqualTo(Long value) {
            addCriterion("AUTH_ID <=", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdIn(List<Long> values) {
            addCriterion("AUTH_ID in", values, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotIn(List<Long> values) {
            addCriterion("AUTH_ID not in", values, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdBetween(Long value1, Long value2) {
            addCriterion("AUTH_ID between", value1, value2, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotBetween(Long value1, Long value2) {
            addCriterion("AUTH_ID not between", value1, value2, "authId");
            return (Criteria) this;
        }

        public Criteria andValueFormateIsNull() {
            addCriterion("VALUE_FORMATE is null");
            return (Criteria) this;
        }

        public Criteria andValueFormateIsNotNull() {
            addCriterion("VALUE_FORMATE is not null");
            return (Criteria) this;
        }

        public Criteria andValueFormateEqualTo(String value) {
            addCriterion("VALUE_FORMATE =", value, "valueFormate");
            return (Criteria) this;
        }

        public Criteria andValueFormateNotEqualTo(String value) {
            addCriterion("VALUE_FORMATE <>", value, "valueFormate");
            return (Criteria) this;
        }

        public Criteria andValueFormateGreaterThan(String value) {
            addCriterion("VALUE_FORMATE >", value, "valueFormate");
            return (Criteria) this;
        }

        public Criteria andValueFormateGreaterThanOrEqualTo(String value) {
            addCriterion("VALUE_FORMATE >=", value, "valueFormate");
            return (Criteria) this;
        }

        public Criteria andValueFormateLessThan(String value) {
            addCriterion("VALUE_FORMATE <", value, "valueFormate");
            return (Criteria) this;
        }

        public Criteria andValueFormateLessThanOrEqualTo(String value) {
            addCriterion("VALUE_FORMATE <=", value, "valueFormate");
            return (Criteria) this;
        }

        public Criteria andValueFormateLike(String value) {
            addCriterion("VALUE_FORMATE like", value, "valueFormate");
            return (Criteria) this;
        }

        public Criteria andValueFormateNotLike(String value) {
            addCriterion("VALUE_FORMATE not like", value, "valueFormate");
            return (Criteria) this;
        }

        public Criteria andValueFormateIn(List<String> values) {
            addCriterion("VALUE_FORMATE in", values, "valueFormate");
            return (Criteria) this;
        }

        public Criteria andValueFormateNotIn(List<String> values) {
            addCriterion("VALUE_FORMATE not in", values, "valueFormate");
            return (Criteria) this;
        }

        public Criteria andValueFormateBetween(String value1, String value2) {
            addCriterion("VALUE_FORMATE between", value1, value2, "valueFormate");
            return (Criteria) this;
        }

        public Criteria andValueFormateNotBetween(String value1, String value2) {
            addCriterion("VALUE_FORMATE not between", value1, value2, "valueFormate");
            return (Criteria) this;
        }

        public Criteria andInputValueIsNull() {
            addCriterion("INPUT_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andInputValueIsNotNull() {
            addCriterion("INPUT_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andInputValueEqualTo(String value) {
            addCriterion("INPUT_VALUE =", value, "inputValue");
            return (Criteria) this;
        }

        public Criteria andInputValueNotEqualTo(String value) {
            addCriterion("INPUT_VALUE <>", value, "inputValue");
            return (Criteria) this;
        }

        public Criteria andInputValueGreaterThan(String value) {
            addCriterion("INPUT_VALUE >", value, "inputValue");
            return (Criteria) this;
        }

        public Criteria andInputValueGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_VALUE >=", value, "inputValue");
            return (Criteria) this;
        }

        public Criteria andInputValueLessThan(String value) {
            addCriterion("INPUT_VALUE <", value, "inputValue");
            return (Criteria) this;
        }

        public Criteria andInputValueLessThanOrEqualTo(String value) {
            addCriterion("INPUT_VALUE <=", value, "inputValue");
            return (Criteria) this;
        }

        public Criteria andInputValueLike(String value) {
            addCriterion("INPUT_VALUE like", value, "inputValue");
            return (Criteria) this;
        }

        public Criteria andInputValueNotLike(String value) {
            addCriterion("INPUT_VALUE not like", value, "inputValue");
            return (Criteria) this;
        }

        public Criteria andInputValueIn(List<String> values) {
            addCriterion("INPUT_VALUE in", values, "inputValue");
            return (Criteria) this;
        }

        public Criteria andInputValueNotIn(List<String> values) {
            addCriterion("INPUT_VALUE not in", values, "inputValue");
            return (Criteria) this;
        }

        public Criteria andInputValueBetween(String value1, String value2) {
            addCriterion("INPUT_VALUE between", value1, value2, "inputValue");
            return (Criteria) this;
        }

        public Criteria andInputValueNotBetween(String value1, String value2) {
            addCriterion("INPUT_VALUE not between", value1, value2, "inputValue");
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