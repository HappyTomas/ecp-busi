package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustThirdIdxCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CustThirdIdxCriteria() {
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

        public Criteria andThirdCodeIsNull() {
            addCriterion("THIRD_CODE is null");
            return (Criteria) this;
        }

        public Criteria andThirdCodeIsNotNull() {
            addCriterion("THIRD_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andThirdCodeEqualTo(String value) {
            addCriterion("THIRD_CODE =", value, "thirdCode");
            return (Criteria) this;
        }

        public Criteria andThirdCodeNotEqualTo(String value) {
            addCriterion("THIRD_CODE <>", value, "thirdCode");
            return (Criteria) this;
        }

        public Criteria andThirdCodeGreaterThan(String value) {
            addCriterion("THIRD_CODE >", value, "thirdCode");
            return (Criteria) this;
        }

        public Criteria andThirdCodeGreaterThanOrEqualTo(String value) {
            addCriterion("THIRD_CODE >=", value, "thirdCode");
            return (Criteria) this;
        }

        public Criteria andThirdCodeLessThan(String value) {
            addCriterion("THIRD_CODE <", value, "thirdCode");
            return (Criteria) this;
        }

        public Criteria andThirdCodeLessThanOrEqualTo(String value) {
            addCriterion("THIRD_CODE <=", value, "thirdCode");
            return (Criteria) this;
        }

        public Criteria andThirdCodeLike(String value) {
            addCriterion("THIRD_CODE like", value, "thirdCode");
            return (Criteria) this;
        }

        public Criteria andThirdCodeNotLike(String value) {
            addCriterion("THIRD_CODE not like", value, "thirdCode");
            return (Criteria) this;
        }

        public Criteria andThirdCodeIn(List<String> values) {
            addCriterion("THIRD_CODE in", values, "thirdCode");
            return (Criteria) this;
        }

        public Criteria andThirdCodeNotIn(List<String> values) {
            addCriterion("THIRD_CODE not in", values, "thirdCode");
            return (Criteria) this;
        }

        public Criteria andThirdCodeBetween(String value1, String value2) {
            addCriterion("THIRD_CODE between", value1, value2, "thirdCode");
            return (Criteria) this;
        }

        public Criteria andThirdCodeNotBetween(String value1, String value2) {
            addCriterion("THIRD_CODE not between", value1, value2, "thirdCode");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNull() {
            addCriterion("STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNotNull() {
            addCriterion("STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStaffIdEqualTo(Long value) {
            addCriterion("STAFF_ID =", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotEqualTo(Long value) {
            addCriterion("STAFF_ID <>", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThan(Long value) {
            addCriterion("STAFF_ID >", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThanOrEqualTo(Long value) {
            addCriterion("STAFF_ID >=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThan(Long value) {
            addCriterion("STAFF_ID <", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThanOrEqualTo(Long value) {
            addCriterion("STAFF_ID <=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdIn(List<Long> values) {
            addCriterion("STAFF_ID in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotIn(List<Long> values) {
            addCriterion("STAFF_ID not in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdBetween(Long value1, Long value2) {
            addCriterion("STAFF_ID between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotBetween(Long value1, Long value2) {
            addCriterion("STAFF_ID not between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andThirdCodeTypeIsNull() {
            addCriterion("THIRD_CODE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andThirdCodeTypeIsNotNull() {
            addCriterion("THIRD_CODE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andThirdCodeTypeEqualTo(String value) {
            addCriterion("THIRD_CODE_TYPE =", value, "thirdCodeType");
            return (Criteria) this;
        }

        public Criteria andThirdCodeTypeNotEqualTo(String value) {
            addCriterion("THIRD_CODE_TYPE <>", value, "thirdCodeType");
            return (Criteria) this;
        }

        public Criteria andThirdCodeTypeGreaterThan(String value) {
            addCriterion("THIRD_CODE_TYPE >", value, "thirdCodeType");
            return (Criteria) this;
        }

        public Criteria andThirdCodeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("THIRD_CODE_TYPE >=", value, "thirdCodeType");
            return (Criteria) this;
        }

        public Criteria andThirdCodeTypeLessThan(String value) {
            addCriterion("THIRD_CODE_TYPE <", value, "thirdCodeType");
            return (Criteria) this;
        }

        public Criteria andThirdCodeTypeLessThanOrEqualTo(String value) {
            addCriterion("THIRD_CODE_TYPE <=", value, "thirdCodeType");
            return (Criteria) this;
        }

        public Criteria andThirdCodeTypeLike(String value) {
            addCriterion("THIRD_CODE_TYPE like", value, "thirdCodeType");
            return (Criteria) this;
        }

        public Criteria andThirdCodeTypeNotLike(String value) {
            addCriterion("THIRD_CODE_TYPE not like", value, "thirdCodeType");
            return (Criteria) this;
        }

        public Criteria andThirdCodeTypeIn(List<String> values) {
            addCriterion("THIRD_CODE_TYPE in", values, "thirdCodeType");
            return (Criteria) this;
        }

        public Criteria andThirdCodeTypeNotIn(List<String> values) {
            addCriterion("THIRD_CODE_TYPE not in", values, "thirdCodeType");
            return (Criteria) this;
        }

        public Criteria andThirdCodeTypeBetween(String value1, String value2) {
            addCriterion("THIRD_CODE_TYPE between", value1, value2, "thirdCodeType");
            return (Criteria) this;
        }

        public Criteria andThirdCodeTypeNotBetween(String value1, String value2) {
            addCriterion("THIRD_CODE_TYPE not between", value1, value2, "thirdCodeType");
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