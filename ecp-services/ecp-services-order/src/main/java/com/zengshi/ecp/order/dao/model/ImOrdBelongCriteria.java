package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ImOrdBelongCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public ImOrdBelongCriteria() {
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

        public Criteria andOrdIdIsNull() {
            addCriterion("ORD_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrdIdIsNotNull() {
            addCriterion("ORD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrdIdEqualTo(String value) {
            addCriterion("ORD_ID =", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdNotEqualTo(String value) {
            addCriterion("ORD_ID <>", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdGreaterThan(String value) {
            addCriterion("ORD_ID >", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORD_ID >=", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdLessThan(String value) {
            addCriterion("ORD_ID <", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdLessThanOrEqualTo(String value) {
            addCriterion("ORD_ID <=", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdLike(String value) {
            addCriterion("ORD_ID like", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdNotLike(String value) {
            addCriterion("ORD_ID not like", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdIn(List<String> values) {
            addCriterion("ORD_ID in", values, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdNotIn(List<String> values) {
            addCriterion("ORD_ID not in", values, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdBetween(String value1, String value2) {
            addCriterion("ORD_ID between", value1, value2, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdNotBetween(String value1, String value2) {
            addCriterion("ORD_ID not between", value1, value2, "ordId");
            return (Criteria) this;
        }

        public Criteria andRealMoneyIsNull() {
            addCriterion("REAL_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andRealMoneyIsNotNull() {
            addCriterion("REAL_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andRealMoneyEqualTo(Long value) {
            addCriterion("REAL_MONEY =", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyNotEqualTo(Long value) {
            addCriterion("REAL_MONEY <>", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyGreaterThan(Long value) {
            addCriterion("REAL_MONEY >", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("REAL_MONEY >=", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyLessThan(Long value) {
            addCriterion("REAL_MONEY <", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyLessThanOrEqualTo(Long value) {
            addCriterion("REAL_MONEY <=", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyIn(List<Long> values) {
            addCriterion("REAL_MONEY in", values, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyNotIn(List<Long> values) {
            addCriterion("REAL_MONEY not in", values, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyBetween(Long value1, Long value2) {
            addCriterion("REAL_MONEY between", value1, value2, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyNotBetween(Long value1, Long value2) {
            addCriterion("REAL_MONEY not between", value1, value2, "realMoney");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNull() {
            addCriterion("CONTACT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNotNull() {
            addCriterion("CONTACT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andContactNameEqualTo(String value) {
            addCriterion("CONTACT_NAME =", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotEqualTo(String value) {
            addCriterion("CONTACT_NAME <>", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThan(String value) {
            addCriterion("CONTACT_NAME >", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_NAME >=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThan(String value) {
            addCriterion("CONTACT_NAME <", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_NAME <=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLike(String value) {
            addCriterion("CONTACT_NAME like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotLike(String value) {
            addCriterion("CONTACT_NAME not like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameIn(List<String> values) {
            addCriterion("CONTACT_NAME in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotIn(List<String> values) {
            addCriterion("CONTACT_NAME not in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameBetween(String value1, String value2) {
            addCriterion("CONTACT_NAME between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotBetween(String value1, String value2) {
            addCriterion("CONTACT_NAME not between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andMobileNumberIsNull() {
            addCriterion("MOBILE_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andMobileNumberIsNotNull() {
            addCriterion("MOBILE_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andMobileNumberEqualTo(String value) {
            addCriterion("MOBILE_NUMBER =", value, "mobileNumber");
            return (Criteria) this;
        }

        public Criteria andMobileNumberNotEqualTo(String value) {
            addCriterion("MOBILE_NUMBER <>", value, "mobileNumber");
            return (Criteria) this;
        }

        public Criteria andMobileNumberGreaterThan(String value) {
            addCriterion("MOBILE_NUMBER >", value, "mobileNumber");
            return (Criteria) this;
        }

        public Criteria andMobileNumberGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE_NUMBER >=", value, "mobileNumber");
            return (Criteria) this;
        }

        public Criteria andMobileNumberLessThan(String value) {
            addCriterion("MOBILE_NUMBER <", value, "mobileNumber");
            return (Criteria) this;
        }

        public Criteria andMobileNumberLessThanOrEqualTo(String value) {
            addCriterion("MOBILE_NUMBER <=", value, "mobileNumber");
            return (Criteria) this;
        }

        public Criteria andMobileNumberLike(String value) {
            addCriterion("MOBILE_NUMBER like", value, "mobileNumber");
            return (Criteria) this;
        }

        public Criteria andMobileNumberNotLike(String value) {
            addCriterion("MOBILE_NUMBER not like", value, "mobileNumber");
            return (Criteria) this;
        }

        public Criteria andMobileNumberIn(List<String> values) {
            addCriterion("MOBILE_NUMBER in", values, "mobileNumber");
            return (Criteria) this;
        }

        public Criteria andMobileNumberNotIn(List<String> values) {
            addCriterion("MOBILE_NUMBER not in", values, "mobileNumber");
            return (Criteria) this;
        }

        public Criteria andMobileNumberBetween(String value1, String value2) {
            addCriterion("MOBILE_NUMBER between", value1, value2, "mobileNumber");
            return (Criteria) this;
        }

        public Criteria andMobileNumberNotBetween(String value1, String value2) {
            addCriterion("MOBILE_NUMBER not between", value1, value2, "mobileNumber");
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

        public Criteria andStaffCodeIsNull() {
            addCriterion("STAFF_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStaffCodeIsNotNull() {
            addCriterion("STAFF_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStaffCodeEqualTo(String value) {
            addCriterion("STAFF_CODE =", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotEqualTo(String value) {
            addCriterion("STAFF_CODE <>", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeGreaterThan(String value) {
            addCriterion("STAFF_CODE >", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_CODE >=", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLessThan(String value) {
            addCriterion("STAFF_CODE <", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLessThanOrEqualTo(String value) {
            addCriterion("STAFF_CODE <=", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLike(String value) {
            addCriterion("STAFF_CODE like", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotLike(String value) {
            addCriterion("STAFF_CODE not like", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeIn(List<String> values) {
            addCriterion("STAFF_CODE in", values, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotIn(List<String> values) {
            addCriterion("STAFF_CODE not in", values, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeBetween(String value1, String value2) {
            addCriterion("STAFF_CODE between", value1, value2, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotBetween(String value1, String value2) {
            addCriterion("STAFF_CODE not between", value1, value2, "staffCode");
            return (Criteria) this;
        }

        public Criteria andOrdTimeIsNull() {
            addCriterion("ORD_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOrdTimeIsNotNull() {
            addCriterion("ORD_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOrdTimeEqualTo(Timestamp value) {
            addCriterion("ORD_TIME =", value, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeNotEqualTo(Timestamp value) {
            addCriterion("ORD_TIME <>", value, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeGreaterThan(Timestamp value) {
            addCriterion("ORD_TIME >", value, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("ORD_TIME >=", value, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeLessThan(Timestamp value) {
            addCriterion("ORD_TIME <", value, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("ORD_TIME <=", value, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeIn(List<Timestamp> values) {
            addCriterion("ORD_TIME in", values, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeNotIn(List<Timestamp> values) {
            addCriterion("ORD_TIME not in", values, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ORD_TIME between", value1, value2, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ORD_TIME not between", value1, value2, "ordTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("PAY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("PAY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Timestamp value) {
            addCriterion("PAY_TIME =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Timestamp value) {
            addCriterion("PAY_TIME <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Timestamp value) {
            addCriterion("PAY_TIME >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("PAY_TIME >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Timestamp value) {
            addCriterion("PAY_TIME <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("PAY_TIME <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Timestamp> values) {
            addCriterion("PAY_TIME in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Timestamp> values) {
            addCriterion("PAY_TIME not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PAY_TIME between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PAY_TIME not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andBelongSerCodeIsNull() {
            addCriterion("BELONG_SER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBelongSerCodeIsNotNull() {
            addCriterion("BELONG_SER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBelongSerCodeEqualTo(String value) {
            addCriterion("BELONG_SER_CODE =", value, "belongSerCode");
            return (Criteria) this;
        }

        public Criteria andBelongSerCodeNotEqualTo(String value) {
            addCriterion("BELONG_SER_CODE <>", value, "belongSerCode");
            return (Criteria) this;
        }

        public Criteria andBelongSerCodeGreaterThan(String value) {
            addCriterion("BELONG_SER_CODE >", value, "belongSerCode");
            return (Criteria) this;
        }

        public Criteria andBelongSerCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BELONG_SER_CODE >=", value, "belongSerCode");
            return (Criteria) this;
        }

        public Criteria andBelongSerCodeLessThan(String value) {
            addCriterion("BELONG_SER_CODE <", value, "belongSerCode");
            return (Criteria) this;
        }

        public Criteria andBelongSerCodeLessThanOrEqualTo(String value) {
            addCriterion("BELONG_SER_CODE <=", value, "belongSerCode");
            return (Criteria) this;
        }

        public Criteria andBelongSerCodeLike(String value) {
            addCriterion("BELONG_SER_CODE like", value, "belongSerCode");
            return (Criteria) this;
        }

        public Criteria andBelongSerCodeNotLike(String value) {
            addCriterion("BELONG_SER_CODE not like", value, "belongSerCode");
            return (Criteria) this;
        }

        public Criteria andBelongSerCodeIn(List<String> values) {
            addCriterion("BELONG_SER_CODE in", values, "belongSerCode");
            return (Criteria) this;
        }

        public Criteria andBelongSerCodeNotIn(List<String> values) {
            addCriterion("BELONG_SER_CODE not in", values, "belongSerCode");
            return (Criteria) this;
        }

        public Criteria andBelongSerCodeBetween(String value1, String value2) {
            addCriterion("BELONG_SER_CODE between", value1, value2, "belongSerCode");
            return (Criteria) this;
        }

        public Criteria andBelongSerCodeNotBetween(String value1, String value2) {
            addCriterion("BELONG_SER_CODE not between", value1, value2, "belongSerCode");
            return (Criteria) this;
        }

        public Criteria andBelongSerNameIsNull() {
            addCriterion("BELONG_SER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBelongSerNameIsNotNull() {
            addCriterion("BELONG_SER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBelongSerNameEqualTo(String value) {
            addCriterion("BELONG_SER_NAME =", value, "belongSerName");
            return (Criteria) this;
        }

        public Criteria andBelongSerNameNotEqualTo(String value) {
            addCriterion("BELONG_SER_NAME <>", value, "belongSerName");
            return (Criteria) this;
        }

        public Criteria andBelongSerNameGreaterThan(String value) {
            addCriterion("BELONG_SER_NAME >", value, "belongSerName");
            return (Criteria) this;
        }

        public Criteria andBelongSerNameGreaterThanOrEqualTo(String value) {
            addCriterion("BELONG_SER_NAME >=", value, "belongSerName");
            return (Criteria) this;
        }

        public Criteria andBelongSerNameLessThan(String value) {
            addCriterion("BELONG_SER_NAME <", value, "belongSerName");
            return (Criteria) this;
        }

        public Criteria andBelongSerNameLessThanOrEqualTo(String value) {
            addCriterion("BELONG_SER_NAME <=", value, "belongSerName");
            return (Criteria) this;
        }

        public Criteria andBelongSerNameLike(String value) {
            addCriterion("BELONG_SER_NAME like", value, "belongSerName");
            return (Criteria) this;
        }

        public Criteria andBelongSerNameNotLike(String value) {
            addCriterion("BELONG_SER_NAME not like", value, "belongSerName");
            return (Criteria) this;
        }

        public Criteria andBelongSerNameIn(List<String> values) {
            addCriterion("BELONG_SER_NAME in", values, "belongSerName");
            return (Criteria) this;
        }

        public Criteria andBelongSerNameNotIn(List<String> values) {
            addCriterion("BELONG_SER_NAME not in", values, "belongSerName");
            return (Criteria) this;
        }

        public Criteria andBelongSerNameBetween(String value1, String value2) {
            addCriterion("BELONG_SER_NAME between", value1, value2, "belongSerName");
            return (Criteria) this;
        }

        public Criteria andBelongSerNameNotBetween(String value1, String value2) {
            addCriterion("BELONG_SER_NAME not between", value1, value2, "belongSerName");
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