package com.zengshi.ecp.im.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ImCustservSatisfyInfoCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public ImCustservSatisfyInfoCriteria() {
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

        public Criteria andShopidIsNull() {
            addCriterion("SHOPID is null");
            return (Criteria) this;
        }

        public Criteria andShopidIsNotNull() {
            addCriterion("SHOPID is not null");
            return (Criteria) this;
        }

        public Criteria andShopidEqualTo(Long value) {
            addCriterion("SHOPID =", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotEqualTo(Long value) {
            addCriterion("SHOPID <>", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThan(Long value) {
            addCriterion("SHOPID >", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThanOrEqualTo(Long value) {
            addCriterion("SHOPID >=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThan(Long value) {
            addCriterion("SHOPID <", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThanOrEqualTo(Long value) {
            addCriterion("SHOPID <=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidIn(List<Long> values) {
            addCriterion("SHOPID in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotIn(List<Long> values) {
            addCriterion("SHOPID not in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidBetween(Long value1, Long value2) {
            addCriterion("SHOPID between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotBetween(Long value1, Long value2) {
            addCriterion("SHOPID not between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andOfStaffCodeIsNull() {
            addCriterion("OF_STAFF_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOfStaffCodeIsNotNull() {
            addCriterion("OF_STAFF_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOfStaffCodeEqualTo(String value) {
            addCriterion("OF_STAFF_CODE =", value, "ofStaffCode");
            return (Criteria) this;
        }

        public Criteria andOfStaffCodeNotEqualTo(String value) {
            addCriterion("OF_STAFF_CODE <>", value, "ofStaffCode");
            return (Criteria) this;
        }

        public Criteria andOfStaffCodeGreaterThan(String value) {
            addCriterion("OF_STAFF_CODE >", value, "ofStaffCode");
            return (Criteria) this;
        }

        public Criteria andOfStaffCodeGreaterThanOrEqualTo(String value) {
            addCriterion("OF_STAFF_CODE >=", value, "ofStaffCode");
            return (Criteria) this;
        }

        public Criteria andOfStaffCodeLessThan(String value) {
            addCriterion("OF_STAFF_CODE <", value, "ofStaffCode");
            return (Criteria) this;
        }

        public Criteria andOfStaffCodeLessThanOrEqualTo(String value) {
            addCriterion("OF_STAFF_CODE <=", value, "ofStaffCode");
            return (Criteria) this;
        }

        public Criteria andOfStaffCodeLike(String value) {
            addCriterion("OF_STAFF_CODE like", value, "ofStaffCode");
            return (Criteria) this;
        }

        public Criteria andOfStaffCodeNotLike(String value) {
            addCriterion("OF_STAFF_CODE not like", value, "ofStaffCode");
            return (Criteria) this;
        }

        public Criteria andOfStaffCodeIn(List<String> values) {
            addCriterion("OF_STAFF_CODE in", values, "ofStaffCode");
            return (Criteria) this;
        }

        public Criteria andOfStaffCodeNotIn(List<String> values) {
            addCriterion("OF_STAFF_CODE not in", values, "ofStaffCode");
            return (Criteria) this;
        }

        public Criteria andOfStaffCodeBetween(String value1, String value2) {
            addCriterion("OF_STAFF_CODE between", value1, value2, "ofStaffCode");
            return (Criteria) this;
        }

        public Criteria andOfStaffCodeNotBetween(String value1, String value2) {
            addCriterion("OF_STAFF_CODE not between", value1, value2, "ofStaffCode");
            return (Criteria) this;
        }

        public Criteria andCsaCodeIsNull() {
            addCriterion("CSA_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCsaCodeIsNotNull() {
            addCriterion("CSA_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCsaCodeEqualTo(String value) {
            addCriterion("CSA_CODE =", value, "csaCode");
            return (Criteria) this;
        }

        public Criteria andCsaCodeNotEqualTo(String value) {
            addCriterion("CSA_CODE <>", value, "csaCode");
            return (Criteria) this;
        }

        public Criteria andCsaCodeGreaterThan(String value) {
            addCriterion("CSA_CODE >", value, "csaCode");
            return (Criteria) this;
        }

        public Criteria andCsaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CSA_CODE >=", value, "csaCode");
            return (Criteria) this;
        }

        public Criteria andCsaCodeLessThan(String value) {
            addCriterion("CSA_CODE <", value, "csaCode");
            return (Criteria) this;
        }

        public Criteria andCsaCodeLessThanOrEqualTo(String value) {
            addCriterion("CSA_CODE <=", value, "csaCode");
            return (Criteria) this;
        }

        public Criteria andCsaCodeLike(String value) {
            addCriterion("CSA_CODE like", value, "csaCode");
            return (Criteria) this;
        }

        public Criteria andCsaCodeNotLike(String value) {
            addCriterion("CSA_CODE not like", value, "csaCode");
            return (Criteria) this;
        }

        public Criteria andCsaCodeIn(List<String> values) {
            addCriterion("CSA_CODE in", values, "csaCode");
            return (Criteria) this;
        }

        public Criteria andCsaCodeNotIn(List<String> values) {
            addCriterion("CSA_CODE not in", values, "csaCode");
            return (Criteria) this;
        }

        public Criteria andCsaCodeBetween(String value1, String value2) {
            addCriterion("CSA_CODE between", value1, value2, "csaCode");
            return (Criteria) this;
        }

        public Criteria andCsaCodeNotBetween(String value1, String value2) {
            addCriterion("CSA_CODE not between", value1, value2, "csaCode");
            return (Criteria) this;
        }

        public Criteria andSessionIdIsNull() {
            addCriterion("SESSION_ID is null");
            return (Criteria) this;
        }

        public Criteria andSessionIdIsNotNull() {
            addCriterion("SESSION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSessionIdEqualTo(String value) {
            addCriterion("SESSION_ID =", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotEqualTo(String value) {
            addCriterion("SESSION_ID <>", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdGreaterThan(String value) {
            addCriterion("SESSION_ID >", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_ID >=", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLessThan(String value) {
            addCriterion("SESSION_ID <", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLessThanOrEqualTo(String value) {
            addCriterion("SESSION_ID <=", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLike(String value) {
            addCriterion("SESSION_ID like", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotLike(String value) {
            addCriterion("SESSION_ID not like", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdIn(List<String> values) {
            addCriterion("SESSION_ID in", values, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotIn(List<String> values) {
            addCriterion("SESSION_ID not in", values, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdBetween(String value1, String value2) {
            addCriterion("SESSION_ID between", value1, value2, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotBetween(String value1, String value2) {
            addCriterion("SESSION_ID not between", value1, value2, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSatisfyTypeIsNull() {
            addCriterion("SATISFY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSatisfyTypeIsNotNull() {
            addCriterion("SATISFY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSatisfyTypeEqualTo(String value) {
            addCriterion("SATISFY_TYPE =", value, "satisfyType");
            return (Criteria) this;
        }

        public Criteria andSatisfyTypeNotEqualTo(String value) {
            addCriterion("SATISFY_TYPE <>", value, "satisfyType");
            return (Criteria) this;
        }

        public Criteria andSatisfyTypeGreaterThan(String value) {
            addCriterion("SATISFY_TYPE >", value, "satisfyType");
            return (Criteria) this;
        }

        public Criteria andSatisfyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SATISFY_TYPE >=", value, "satisfyType");
            return (Criteria) this;
        }

        public Criteria andSatisfyTypeLessThan(String value) {
            addCriterion("SATISFY_TYPE <", value, "satisfyType");
            return (Criteria) this;
        }

        public Criteria andSatisfyTypeLessThanOrEqualTo(String value) {
            addCriterion("SATISFY_TYPE <=", value, "satisfyType");
            return (Criteria) this;
        }

        public Criteria andSatisfyTypeLike(String value) {
            addCriterion("SATISFY_TYPE like", value, "satisfyType");
            return (Criteria) this;
        }

        public Criteria andSatisfyTypeNotLike(String value) {
            addCriterion("SATISFY_TYPE not like", value, "satisfyType");
            return (Criteria) this;
        }

        public Criteria andSatisfyTypeIn(List<String> values) {
            addCriterion("SATISFY_TYPE in", values, "satisfyType");
            return (Criteria) this;
        }

        public Criteria andSatisfyTypeNotIn(List<String> values) {
            addCriterion("SATISFY_TYPE not in", values, "satisfyType");
            return (Criteria) this;
        }

        public Criteria andSatisfyTypeBetween(String value1, String value2) {
            addCriterion("SATISFY_TYPE between", value1, value2, "satisfyType");
            return (Criteria) this;
        }

        public Criteria andSatisfyTypeNotBetween(String value1, String value2) {
            addCriterion("SATISFY_TYPE not between", value1, value2, "satisfyType");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyTypeIsNull() {
            addCriterion("NOT_SATISFY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyTypeIsNotNull() {
            addCriterion("NOT_SATISFY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyTypeEqualTo(String value) {
            addCriterion("NOT_SATISFY_TYPE =", value, "notSatisfyType");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyTypeNotEqualTo(String value) {
            addCriterion("NOT_SATISFY_TYPE <>", value, "notSatisfyType");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyTypeGreaterThan(String value) {
            addCriterion("NOT_SATISFY_TYPE >", value, "notSatisfyType");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("NOT_SATISFY_TYPE >=", value, "notSatisfyType");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyTypeLessThan(String value) {
            addCriterion("NOT_SATISFY_TYPE <", value, "notSatisfyType");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyTypeLessThanOrEqualTo(String value) {
            addCriterion("NOT_SATISFY_TYPE <=", value, "notSatisfyType");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyTypeLike(String value) {
            addCriterion("NOT_SATISFY_TYPE like", value, "notSatisfyType");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyTypeNotLike(String value) {
            addCriterion("NOT_SATISFY_TYPE not like", value, "notSatisfyType");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyTypeIn(List<String> values) {
            addCriterion("NOT_SATISFY_TYPE in", values, "notSatisfyType");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyTypeNotIn(List<String> values) {
            addCriterion("NOT_SATISFY_TYPE not in", values, "notSatisfyType");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyTypeBetween(String value1, String value2) {
            addCriterion("NOT_SATISFY_TYPE between", value1, value2, "notSatisfyType");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyTypeNotBetween(String value1, String value2) {
            addCriterion("NOT_SATISFY_TYPE not between", value1, value2, "notSatisfyType");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyReasonIsNull() {
            addCriterion("NOT_SATISFY_REASON is null");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyReasonIsNotNull() {
            addCriterion("NOT_SATISFY_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyReasonEqualTo(String value) {
            addCriterion("NOT_SATISFY_REASON =", value, "notSatisfyReason");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyReasonNotEqualTo(String value) {
            addCriterion("NOT_SATISFY_REASON <>", value, "notSatisfyReason");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyReasonGreaterThan(String value) {
            addCriterion("NOT_SATISFY_REASON >", value, "notSatisfyReason");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyReasonGreaterThanOrEqualTo(String value) {
            addCriterion("NOT_SATISFY_REASON >=", value, "notSatisfyReason");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyReasonLessThan(String value) {
            addCriterion("NOT_SATISFY_REASON <", value, "notSatisfyReason");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyReasonLessThanOrEqualTo(String value) {
            addCriterion("NOT_SATISFY_REASON <=", value, "notSatisfyReason");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyReasonLike(String value) {
            addCriterion("NOT_SATISFY_REASON like", value, "notSatisfyReason");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyReasonNotLike(String value) {
            addCriterion("NOT_SATISFY_REASON not like", value, "notSatisfyReason");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyReasonIn(List<String> values) {
            addCriterion("NOT_SATISFY_REASON in", values, "notSatisfyReason");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyReasonNotIn(List<String> values) {
            addCriterion("NOT_SATISFY_REASON not in", values, "notSatisfyReason");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyReasonBetween(String value1, String value2) {
            addCriterion("NOT_SATISFY_REASON between", value1, value2, "notSatisfyReason");
            return (Criteria) this;
        }

        public Criteria andNotSatisfyReasonNotBetween(String value1, String value2) {
            addCriterion("NOT_SATISFY_REASON not between", value1, value2, "notSatisfyReason");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Timestamp value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Timestamp value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Timestamp> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Timestamp> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
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