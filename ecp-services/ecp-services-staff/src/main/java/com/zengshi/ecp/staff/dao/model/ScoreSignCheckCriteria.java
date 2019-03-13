package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ScoreSignCheckCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public ScoreSignCheckCriteria() {
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

        public Criteria andSignDateBeginIsNull() {
            addCriterion("SIGN_DATE_BEGIN is null");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginIsNotNull() {
            addCriterion("SIGN_DATE_BEGIN is not null");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginEqualTo(Timestamp value) {
            addCriterion("SIGN_DATE_BEGIN =", value, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginNotEqualTo(Timestamp value) {
            addCriterion("SIGN_DATE_BEGIN <>", value, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginGreaterThan(Timestamp value) {
            addCriterion("SIGN_DATE_BEGIN >", value, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("SIGN_DATE_BEGIN >=", value, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginLessThan(Timestamp value) {
            addCriterion("SIGN_DATE_BEGIN <", value, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginLessThanOrEqualTo(Timestamp value) {
            addCriterion("SIGN_DATE_BEGIN <=", value, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginIn(List<Timestamp> values) {
            addCriterion("SIGN_DATE_BEGIN in", values, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginNotIn(List<Timestamp> values) {
            addCriterion("SIGN_DATE_BEGIN not in", values, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SIGN_DATE_BEGIN between", value1, value2, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SIGN_DATE_BEGIN not between", value1, value2, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateEndIsNull() {
            addCriterion("SIGN_DATE_END is null");
            return (Criteria) this;
        }

        public Criteria andSignDateEndIsNotNull() {
            addCriterion("SIGN_DATE_END is not null");
            return (Criteria) this;
        }

        public Criteria andSignDateEndEqualTo(Timestamp value) {
            addCriterion("SIGN_DATE_END =", value, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndNotEqualTo(Timestamp value) {
            addCriterion("SIGN_DATE_END <>", value, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndGreaterThan(Timestamp value) {
            addCriterion("SIGN_DATE_END >", value, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("SIGN_DATE_END >=", value, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndLessThan(Timestamp value) {
            addCriterion("SIGN_DATE_END <", value, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndLessThanOrEqualTo(Timestamp value) {
            addCriterion("SIGN_DATE_END <=", value, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndIn(List<Timestamp> values) {
            addCriterion("SIGN_DATE_END in", values, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndNotIn(List<Timestamp> values) {
            addCriterion("SIGN_DATE_END not in", values, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SIGN_DATE_END between", value1, value2, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SIGN_DATE_END not between", value1, value2, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignCntIsNull() {
            addCriterion("SIGN_CNT is null");
            return (Criteria) this;
        }

        public Criteria andSignCntIsNotNull() {
            addCriterion("SIGN_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andSignCntEqualTo(Long value) {
            addCriterion("SIGN_CNT =", value, "signCnt");
            return (Criteria) this;
        }

        public Criteria andSignCntNotEqualTo(Long value) {
            addCriterion("SIGN_CNT <>", value, "signCnt");
            return (Criteria) this;
        }

        public Criteria andSignCntGreaterThan(Long value) {
            addCriterion("SIGN_CNT >", value, "signCnt");
            return (Criteria) this;
        }

        public Criteria andSignCntGreaterThanOrEqualTo(Long value) {
            addCriterion("SIGN_CNT >=", value, "signCnt");
            return (Criteria) this;
        }

        public Criteria andSignCntLessThan(Long value) {
            addCriterion("SIGN_CNT <", value, "signCnt");
            return (Criteria) this;
        }

        public Criteria andSignCntLessThanOrEqualTo(Long value) {
            addCriterion("SIGN_CNT <=", value, "signCnt");
            return (Criteria) this;
        }

        public Criteria andSignCntIn(List<Long> values) {
            addCriterion("SIGN_CNT in", values, "signCnt");
            return (Criteria) this;
        }

        public Criteria andSignCntNotIn(List<Long> values) {
            addCriterion("SIGN_CNT not in", values, "signCnt");
            return (Criteria) this;
        }

        public Criteria andSignCntBetween(Long value1, Long value2) {
            addCriterion("SIGN_CNT between", value1, value2, "signCnt");
            return (Criteria) this;
        }

        public Criteria andSignCntNotBetween(Long value1, Long value2) {
            addCriterion("SIGN_CNT not between", value1, value2, "signCnt");
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