package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CustSensitiveLogCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CustSensitiveLogCriteria() {
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

        public Criteria andActionTypeIsNull() {
            addCriterion("ACTION_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andActionTypeIsNotNull() {
            addCriterion("ACTION_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andActionTypeEqualTo(String value) {
            addCriterion("ACTION_TYPE =", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeNotEqualTo(String value) {
            addCriterion("ACTION_TYPE <>", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeGreaterThan(String value) {
            addCriterion("ACTION_TYPE >", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ACTION_TYPE >=", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeLessThan(String value) {
            addCriterion("ACTION_TYPE <", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeLessThanOrEqualTo(String value) {
            addCriterion("ACTION_TYPE <=", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeLike(String value) {
            addCriterion("ACTION_TYPE like", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeNotLike(String value) {
            addCriterion("ACTION_TYPE not like", value, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeIn(List<String> values) {
            addCriterion("ACTION_TYPE in", values, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeNotIn(List<String> values) {
            addCriterion("ACTION_TYPE not in", values, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeBetween(String value1, String value2) {
            addCriterion("ACTION_TYPE between", value1, value2, "actionType");
            return (Criteria) this;
        }

        public Criteria andActionTypeNotBetween(String value1, String value2) {
            addCriterion("ACTION_TYPE not between", value1, value2, "actionType");
            return (Criteria) this;
        }

        public Criteria andSensitiveTypeIsNull() {
            addCriterion("SENSITIVE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSensitiveTypeIsNotNull() {
            addCriterion("SENSITIVE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSensitiveTypeEqualTo(String value) {
            addCriterion("SENSITIVE_TYPE =", value, "sensitiveType");
            return (Criteria) this;
        }

        public Criteria andSensitiveTypeNotEqualTo(String value) {
            addCriterion("SENSITIVE_TYPE <>", value, "sensitiveType");
            return (Criteria) this;
        }

        public Criteria andSensitiveTypeGreaterThan(String value) {
            addCriterion("SENSITIVE_TYPE >", value, "sensitiveType");
            return (Criteria) this;
        }

        public Criteria andSensitiveTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SENSITIVE_TYPE >=", value, "sensitiveType");
            return (Criteria) this;
        }

        public Criteria andSensitiveTypeLessThan(String value) {
            addCriterion("SENSITIVE_TYPE <", value, "sensitiveType");
            return (Criteria) this;
        }

        public Criteria andSensitiveTypeLessThanOrEqualTo(String value) {
            addCriterion("SENSITIVE_TYPE <=", value, "sensitiveType");
            return (Criteria) this;
        }

        public Criteria andSensitiveTypeLike(String value) {
            addCriterion("SENSITIVE_TYPE like", value, "sensitiveType");
            return (Criteria) this;
        }

        public Criteria andSensitiveTypeNotLike(String value) {
            addCriterion("SENSITIVE_TYPE not like", value, "sensitiveType");
            return (Criteria) this;
        }

        public Criteria andSensitiveTypeIn(List<String> values) {
            addCriterion("SENSITIVE_TYPE in", values, "sensitiveType");
            return (Criteria) this;
        }

        public Criteria andSensitiveTypeNotIn(List<String> values) {
            addCriterion("SENSITIVE_TYPE not in", values, "sensitiveType");
            return (Criteria) this;
        }

        public Criteria andSensitiveTypeBetween(String value1, String value2) {
            addCriterion("SENSITIVE_TYPE between", value1, value2, "sensitiveType");
            return (Criteria) this;
        }

        public Criteria andSensitiveTypeNotBetween(String value1, String value2) {
            addCriterion("SENSITIVE_TYPE not between", value1, value2, "sensitiveType");
            return (Criteria) this;
        }

        public Criteria andSensitiveDescIsNull() {
            addCriterion("SENSITIVE_DESC is null");
            return (Criteria) this;
        }

        public Criteria andSensitiveDescIsNotNull() {
            addCriterion("SENSITIVE_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andSensitiveDescEqualTo(String value) {
            addCriterion("SENSITIVE_DESC =", value, "sensitiveDesc");
            return (Criteria) this;
        }

        public Criteria andSensitiveDescNotEqualTo(String value) {
            addCriterion("SENSITIVE_DESC <>", value, "sensitiveDesc");
            return (Criteria) this;
        }

        public Criteria andSensitiveDescGreaterThan(String value) {
            addCriterion("SENSITIVE_DESC >", value, "sensitiveDesc");
            return (Criteria) this;
        }

        public Criteria andSensitiveDescGreaterThanOrEqualTo(String value) {
            addCriterion("SENSITIVE_DESC >=", value, "sensitiveDesc");
            return (Criteria) this;
        }

        public Criteria andSensitiveDescLessThan(String value) {
            addCriterion("SENSITIVE_DESC <", value, "sensitiveDesc");
            return (Criteria) this;
        }

        public Criteria andSensitiveDescLessThanOrEqualTo(String value) {
            addCriterion("SENSITIVE_DESC <=", value, "sensitiveDesc");
            return (Criteria) this;
        }

        public Criteria andSensitiveDescLike(String value) {
            addCriterion("SENSITIVE_DESC like", value, "sensitiveDesc");
            return (Criteria) this;
        }

        public Criteria andSensitiveDescNotLike(String value) {
            addCriterion("SENSITIVE_DESC not like", value, "sensitiveDesc");
            return (Criteria) this;
        }

        public Criteria andSensitiveDescIn(List<String> values) {
            addCriterion("SENSITIVE_DESC in", values, "sensitiveDesc");
            return (Criteria) this;
        }

        public Criteria andSensitiveDescNotIn(List<String> values) {
            addCriterion("SENSITIVE_DESC not in", values, "sensitiveDesc");
            return (Criteria) this;
        }

        public Criteria andSensitiveDescBetween(String value1, String value2) {
            addCriterion("SENSITIVE_DESC between", value1, value2, "sensitiveDesc");
            return (Criteria) this;
        }

        public Criteria andSensitiveDescNotBetween(String value1, String value2) {
            addCriterion("SENSITIVE_DESC not between", value1, value2, "sensitiveDesc");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Timestamp value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Timestamp value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Timestamp> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Timestamp> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeIsNull() {
            addCriterion("CREATE_STAFF_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeIsNotNull() {
            addCriterion("CREATE_STAFF_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeEqualTo(String value) {
            addCriterion("CREATE_STAFF_CODE =", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeNotEqualTo(String value) {
            addCriterion("CREATE_STAFF_CODE <>", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeGreaterThan(String value) {
            addCriterion("CREATE_STAFF_CODE >", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_STAFF_CODE >=", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeLessThan(String value) {
            addCriterion("CREATE_STAFF_CODE <", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeLessThanOrEqualTo(String value) {
            addCriterion("CREATE_STAFF_CODE <=", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeLike(String value) {
            addCriterion("CREATE_STAFF_CODE like", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeNotLike(String value) {
            addCriterion("CREATE_STAFF_CODE not like", value, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeIn(List<String> values) {
            addCriterion("CREATE_STAFF_CODE in", values, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeNotIn(List<String> values) {
            addCriterion("CREATE_STAFF_CODE not in", values, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeBetween(String value1, String value2) {
            addCriterion("CREATE_STAFF_CODE between", value1, value2, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffCodeNotBetween(String value1, String value2) {
            addCriterion("CREATE_STAFF_CODE not between", value1, value2, "createStaffCode");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIsNull() {
            addCriterion("CREATE_STAFF is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIsNotNull() {
            addCriterion("CREATE_STAFF is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffEqualTo(Long value) {
            addCriterion("CREATE_STAFF =", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotEqualTo(Long value) {
            addCriterion("CREATE_STAFF <>", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThan(Long value) {
            addCriterion("CREATE_STAFF >", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATE_STAFF >=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThan(Long value) {
            addCriterion("CREATE_STAFF <", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThanOrEqualTo(Long value) {
            addCriterion("CREATE_STAFF <=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIn(List<Long> values) {
            addCriterion("CREATE_STAFF in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotIn(List<Long> values) {
            addCriterion("CREATE_STAFF not in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffBetween(Long value1, Long value2) {
            addCriterion("CREATE_STAFF between", value1, value2, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotBetween(Long value1, Long value2) {
            addCriterion("CREATE_STAFF not between", value1, value2, "createStaff");
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