package com.zengshi.ecp.sys.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseSmsCfgCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public BaseSmsCfgCriteria() {
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

        public Criteria andSmsGatewayIsNull() {
            addCriterion("SMS_GATEWAY is null");
            return (Criteria) this;
        }

        public Criteria andSmsGatewayIsNotNull() {
            addCriterion("SMS_GATEWAY is not null");
            return (Criteria) this;
        }

        public Criteria andSmsGatewayEqualTo(String value) {
            addCriterion("SMS_GATEWAY =", value, "smsGateway");
            return (Criteria) this;
        }

        public Criteria andSmsGatewayNotEqualTo(String value) {
            addCriterion("SMS_GATEWAY <>", value, "smsGateway");
            return (Criteria) this;
        }

        public Criteria andSmsGatewayGreaterThan(String value) {
            addCriterion("SMS_GATEWAY >", value, "smsGateway");
            return (Criteria) this;
        }

        public Criteria andSmsGatewayGreaterThanOrEqualTo(String value) {
            addCriterion("SMS_GATEWAY >=", value, "smsGateway");
            return (Criteria) this;
        }

        public Criteria andSmsGatewayLessThan(String value) {
            addCriterion("SMS_GATEWAY <", value, "smsGateway");
            return (Criteria) this;
        }

        public Criteria andSmsGatewayLessThanOrEqualTo(String value) {
            addCriterion("SMS_GATEWAY <=", value, "smsGateway");
            return (Criteria) this;
        }

        public Criteria andSmsGatewayLike(String value) {
            addCriterion("SMS_GATEWAY like", value, "smsGateway");
            return (Criteria) this;
        }

        public Criteria andSmsGatewayNotLike(String value) {
            addCriterion("SMS_GATEWAY not like", value, "smsGateway");
            return (Criteria) this;
        }

        public Criteria andSmsGatewayIn(List<String> values) {
            addCriterion("SMS_GATEWAY in", values, "smsGateway");
            return (Criteria) this;
        }

        public Criteria andSmsGatewayNotIn(List<String> values) {
            addCriterion("SMS_GATEWAY not in", values, "smsGateway");
            return (Criteria) this;
        }

        public Criteria andSmsGatewayBetween(String value1, String value2) {
            addCriterion("SMS_GATEWAY between", value1, value2, "smsGateway");
            return (Criteria) this;
        }

        public Criteria andSmsGatewayNotBetween(String value1, String value2) {
            addCriterion("SMS_GATEWAY not between", value1, value2, "smsGateway");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("ACCOUNT =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("ACCOUNT <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("ACCOUNT >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("ACCOUNT <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("ACCOUNT like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("ACCOUNT not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("ACCOUNT in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("ACCOUNT not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("ACCOUNT between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAuthKeyIsNull() {
            addCriterion("AUTH_KEY is null");
            return (Criteria) this;
        }

        public Criteria andAuthKeyIsNotNull() {
            addCriterion("AUTH_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andAuthKeyEqualTo(String value) {
            addCriterion("AUTH_KEY =", value, "authKey");
            return (Criteria) this;
        }

        public Criteria andAuthKeyNotEqualTo(String value) {
            addCriterion("AUTH_KEY <>", value, "authKey");
            return (Criteria) this;
        }

        public Criteria andAuthKeyGreaterThan(String value) {
            addCriterion("AUTH_KEY >", value, "authKey");
            return (Criteria) this;
        }

        public Criteria andAuthKeyGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_KEY >=", value, "authKey");
            return (Criteria) this;
        }

        public Criteria andAuthKeyLessThan(String value) {
            addCriterion("AUTH_KEY <", value, "authKey");
            return (Criteria) this;
        }

        public Criteria andAuthKeyLessThanOrEqualTo(String value) {
            addCriterion("AUTH_KEY <=", value, "authKey");
            return (Criteria) this;
        }

        public Criteria andAuthKeyLike(String value) {
            addCriterion("AUTH_KEY like", value, "authKey");
            return (Criteria) this;
        }

        public Criteria andAuthKeyNotLike(String value) {
            addCriterion("AUTH_KEY not like", value, "authKey");
            return (Criteria) this;
        }

        public Criteria andAuthKeyIn(List<String> values) {
            addCriterion("AUTH_KEY in", values, "authKey");
            return (Criteria) this;
        }

        public Criteria andAuthKeyNotIn(List<String> values) {
            addCriterion("AUTH_KEY not in", values, "authKey");
            return (Criteria) this;
        }

        public Criteria andAuthKeyBetween(String value1, String value2) {
            addCriterion("AUTH_KEY between", value1, value2, "authKey");
            return (Criteria) this;
        }

        public Criteria andAuthKeyNotBetween(String value1, String value2) {
            addCriterion("AUTH_KEY not between", value1, value2, "authKey");
            return (Criteria) this;
        }

        public Criteria andOthParamsIsNull() {
            addCriterion("OTH_PARAMS is null");
            return (Criteria) this;
        }

        public Criteria andOthParamsIsNotNull() {
            addCriterion("OTH_PARAMS is not null");
            return (Criteria) this;
        }

        public Criteria andOthParamsEqualTo(String value) {
            addCriterion("OTH_PARAMS =", value, "othParams");
            return (Criteria) this;
        }

        public Criteria andOthParamsNotEqualTo(String value) {
            addCriterion("OTH_PARAMS <>", value, "othParams");
            return (Criteria) this;
        }

        public Criteria andOthParamsGreaterThan(String value) {
            addCriterion("OTH_PARAMS >", value, "othParams");
            return (Criteria) this;
        }

        public Criteria andOthParamsGreaterThanOrEqualTo(String value) {
            addCriterion("OTH_PARAMS >=", value, "othParams");
            return (Criteria) this;
        }

        public Criteria andOthParamsLessThan(String value) {
            addCriterion("OTH_PARAMS <", value, "othParams");
            return (Criteria) this;
        }

        public Criteria andOthParamsLessThanOrEqualTo(String value) {
            addCriterion("OTH_PARAMS <=", value, "othParams");
            return (Criteria) this;
        }

        public Criteria andOthParamsLike(String value) {
            addCriterion("OTH_PARAMS like", value, "othParams");
            return (Criteria) this;
        }

        public Criteria andOthParamsNotLike(String value) {
            addCriterion("OTH_PARAMS not like", value, "othParams");
            return (Criteria) this;
        }

        public Criteria andOthParamsIn(List<String> values) {
            addCriterion("OTH_PARAMS in", values, "othParams");
            return (Criteria) this;
        }

        public Criteria andOthParamsNotIn(List<String> values) {
            addCriterion("OTH_PARAMS not in", values, "othParams");
            return (Criteria) this;
        }

        public Criteria andOthParamsBetween(String value1, String value2) {
            addCriterion("OTH_PARAMS between", value1, value2, "othParams");
            return (Criteria) this;
        }

        public Criteria andOthParamsNotBetween(String value1, String value2) {
            addCriterion("OTH_PARAMS not between", value1, value2, "othParams");
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
