package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PayBindCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PayBindCriteria() {
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

        public Criteria andPayWayIsNull() {
            addCriterion("PAY_WAY is null");
            return (Criteria) this;
        }

        public Criteria andPayWayIsNotNull() {
            addCriterion("PAY_WAY is not null");
            return (Criteria) this;
        }

        public Criteria andPayWayEqualTo(String value) {
            addCriterion("PAY_WAY =", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotEqualTo(String value) {
            addCriterion("PAY_WAY <>", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayGreaterThan(String value) {
            addCriterion("PAY_WAY >", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_WAY >=", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayLessThan(String value) {
            addCriterion("PAY_WAY <", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayLessThanOrEqualTo(String value) {
            addCriterion("PAY_WAY <=", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayLike(String value) {
            addCriterion("PAY_WAY like", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotLike(String value) {
            addCriterion("PAY_WAY not like", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayIn(List<String> values) {
            addCriterion("PAY_WAY in", values, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotIn(List<String> values) {
            addCriterion("PAY_WAY not in", values, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayBetween(String value1, String value2) {
            addCriterion("PAY_WAY between", value1, value2, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotBetween(String value1, String value2) {
            addCriterion("PAY_WAY not between", value1, value2, "payWay");
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

        public Criteria andBindBankAcctIsNull() {
            addCriterion("BIND_BANK_ACCT is null");
            return (Criteria) this;
        }

        public Criteria andBindBankAcctIsNotNull() {
            addCriterion("BIND_BANK_ACCT is not null");
            return (Criteria) this;
        }

        public Criteria andBindBankAcctEqualTo(String value) {
            addCriterion("BIND_BANK_ACCT =", value, "bindBankAcct");
            return (Criteria) this;
        }

        public Criteria andBindBankAcctNotEqualTo(String value) {
            addCriterion("BIND_BANK_ACCT <>", value, "bindBankAcct");
            return (Criteria) this;
        }

        public Criteria andBindBankAcctGreaterThan(String value) {
            addCriterion("BIND_BANK_ACCT >", value, "bindBankAcct");
            return (Criteria) this;
        }

        public Criteria andBindBankAcctGreaterThanOrEqualTo(String value) {
            addCriterion("BIND_BANK_ACCT >=", value, "bindBankAcct");
            return (Criteria) this;
        }

        public Criteria andBindBankAcctLessThan(String value) {
            addCriterion("BIND_BANK_ACCT <", value, "bindBankAcct");
            return (Criteria) this;
        }

        public Criteria andBindBankAcctLessThanOrEqualTo(String value) {
            addCriterion("BIND_BANK_ACCT <=", value, "bindBankAcct");
            return (Criteria) this;
        }

        public Criteria andBindBankAcctLike(String value) {
            addCriterion("BIND_BANK_ACCT like", value, "bindBankAcct");
            return (Criteria) this;
        }

        public Criteria andBindBankAcctNotLike(String value) {
            addCriterion("BIND_BANK_ACCT not like", value, "bindBankAcct");
            return (Criteria) this;
        }

        public Criteria andBindBankAcctIn(List<String> values) {
            addCriterion("BIND_BANK_ACCT in", values, "bindBankAcct");
            return (Criteria) this;
        }

        public Criteria andBindBankAcctNotIn(List<String> values) {
            addCriterion("BIND_BANK_ACCT not in", values, "bindBankAcct");
            return (Criteria) this;
        }

        public Criteria andBindBankAcctBetween(String value1, String value2) {
            addCriterion("BIND_BANK_ACCT between", value1, value2, "bindBankAcct");
            return (Criteria) this;
        }

        public Criteria andBindBankAcctNotBetween(String value1, String value2) {
            addCriterion("BIND_BANK_ACCT not between", value1, value2, "bindBankAcct");
            return (Criteria) this;
        }

        public Criteria andBindStatusIsNull() {
            addCriterion("BIND_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andBindStatusIsNotNull() {
            addCriterion("BIND_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andBindStatusEqualTo(String value) {
            addCriterion("BIND_STATUS =", value, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusNotEqualTo(String value) {
            addCriterion("BIND_STATUS <>", value, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusGreaterThan(String value) {
            addCriterion("BIND_STATUS >", value, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusGreaterThanOrEqualTo(String value) {
            addCriterion("BIND_STATUS >=", value, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusLessThan(String value) {
            addCriterion("BIND_STATUS <", value, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusLessThanOrEqualTo(String value) {
            addCriterion("BIND_STATUS <=", value, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusLike(String value) {
            addCriterion("BIND_STATUS like", value, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusNotLike(String value) {
            addCriterion("BIND_STATUS not like", value, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusIn(List<String> values) {
            addCriterion("BIND_STATUS in", values, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusNotIn(List<String> values) {
            addCriterion("BIND_STATUS not in", values, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusBetween(String value1, String value2) {
            addCriterion("BIND_STATUS between", value1, value2, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindStatusNotBetween(String value1, String value2) {
            addCriterion("BIND_STATUS not between", value1, value2, "bindStatus");
            return (Criteria) this;
        }

        public Criteria andBindCustNameIsNull() {
            addCriterion("BIND_CUST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBindCustNameIsNotNull() {
            addCriterion("BIND_CUST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBindCustNameEqualTo(String value) {
            addCriterion("BIND_CUST_NAME =", value, "bindCustName");
            return (Criteria) this;
        }

        public Criteria andBindCustNameNotEqualTo(String value) {
            addCriterion("BIND_CUST_NAME <>", value, "bindCustName");
            return (Criteria) this;
        }

        public Criteria andBindCustNameGreaterThan(String value) {
            addCriterion("BIND_CUST_NAME >", value, "bindCustName");
            return (Criteria) this;
        }

        public Criteria andBindCustNameGreaterThanOrEqualTo(String value) {
            addCriterion("BIND_CUST_NAME >=", value, "bindCustName");
            return (Criteria) this;
        }

        public Criteria andBindCustNameLessThan(String value) {
            addCriterion("BIND_CUST_NAME <", value, "bindCustName");
            return (Criteria) this;
        }

        public Criteria andBindCustNameLessThanOrEqualTo(String value) {
            addCriterion("BIND_CUST_NAME <=", value, "bindCustName");
            return (Criteria) this;
        }

        public Criteria andBindCustNameLike(String value) {
            addCriterion("BIND_CUST_NAME like", value, "bindCustName");
            return (Criteria) this;
        }

        public Criteria andBindCustNameNotLike(String value) {
            addCriterion("BIND_CUST_NAME not like", value, "bindCustName");
            return (Criteria) this;
        }

        public Criteria andBindCustNameIn(List<String> values) {
            addCriterion("BIND_CUST_NAME in", values, "bindCustName");
            return (Criteria) this;
        }

        public Criteria andBindCustNameNotIn(List<String> values) {
            addCriterion("BIND_CUST_NAME not in", values, "bindCustName");
            return (Criteria) this;
        }

        public Criteria andBindCustNameBetween(String value1, String value2) {
            addCriterion("BIND_CUST_NAME between", value1, value2, "bindCustName");
            return (Criteria) this;
        }

        public Criteria andBindCustNameNotBetween(String value1, String value2) {
            addCriterion("BIND_CUST_NAME not between", value1, value2, "bindCustName");
            return (Criteria) this;
        }

        public Criteria andBindCustPhoneIsNull() {
            addCriterion("BIND_CUST_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andBindCustPhoneIsNotNull() {
            addCriterion("BIND_CUST_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andBindCustPhoneEqualTo(String value) {
            addCriterion("BIND_CUST_PHONE =", value, "bindCustPhone");
            return (Criteria) this;
        }

        public Criteria andBindCustPhoneNotEqualTo(String value) {
            addCriterion("BIND_CUST_PHONE <>", value, "bindCustPhone");
            return (Criteria) this;
        }

        public Criteria andBindCustPhoneGreaterThan(String value) {
            addCriterion("BIND_CUST_PHONE >", value, "bindCustPhone");
            return (Criteria) this;
        }

        public Criteria andBindCustPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("BIND_CUST_PHONE >=", value, "bindCustPhone");
            return (Criteria) this;
        }

        public Criteria andBindCustPhoneLessThan(String value) {
            addCriterion("BIND_CUST_PHONE <", value, "bindCustPhone");
            return (Criteria) this;
        }

        public Criteria andBindCustPhoneLessThanOrEqualTo(String value) {
            addCriterion("BIND_CUST_PHONE <=", value, "bindCustPhone");
            return (Criteria) this;
        }

        public Criteria andBindCustPhoneLike(String value) {
            addCriterion("BIND_CUST_PHONE like", value, "bindCustPhone");
            return (Criteria) this;
        }

        public Criteria andBindCustPhoneNotLike(String value) {
            addCriterion("BIND_CUST_PHONE not like", value, "bindCustPhone");
            return (Criteria) this;
        }

        public Criteria andBindCustPhoneIn(List<String> values) {
            addCriterion("BIND_CUST_PHONE in", values, "bindCustPhone");
            return (Criteria) this;
        }

        public Criteria andBindCustPhoneNotIn(List<String> values) {
            addCriterion("BIND_CUST_PHONE not in", values, "bindCustPhone");
            return (Criteria) this;
        }

        public Criteria andBindCustPhoneBetween(String value1, String value2) {
            addCriterion("BIND_CUST_PHONE between", value1, value2, "bindCustPhone");
            return (Criteria) this;
        }

        public Criteria andBindCustPhoneNotBetween(String value1, String value2) {
            addCriterion("BIND_CUST_PHONE not between", value1, value2, "bindCustPhone");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Timestamp value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Timestamp value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Timestamp value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Timestamp value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Timestamp> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Timestamp> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
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