package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LoginAccessLogCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public LoginAccessLogCriteria() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
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

        public Criteria andInIpIsNull() {
            addCriterion("IN_IP is null");
            return (Criteria) this;
        }

        public Criteria andInIpIsNotNull() {
            addCriterion("IN_IP is not null");
            return (Criteria) this;
        }

        public Criteria andInIpEqualTo(String value) {
            addCriterion("IN_IP =", value, "inIp");
            return (Criteria) this;
        }

        public Criteria andInIpNotEqualTo(String value) {
            addCriterion("IN_IP <>", value, "inIp");
            return (Criteria) this;
        }

        public Criteria andInIpGreaterThan(String value) {
            addCriterion("IN_IP >", value, "inIp");
            return (Criteria) this;
        }

        public Criteria andInIpGreaterThanOrEqualTo(String value) {
            addCriterion("IN_IP >=", value, "inIp");
            return (Criteria) this;
        }

        public Criteria andInIpLessThan(String value) {
            addCriterion("IN_IP <", value, "inIp");
            return (Criteria) this;
        }

        public Criteria andInIpLessThanOrEqualTo(String value) {
            addCriterion("IN_IP <=", value, "inIp");
            return (Criteria) this;
        }

        public Criteria andInIpLike(String value) {
            addCriterion("IN_IP like", value, "inIp");
            return (Criteria) this;
        }

        public Criteria andInIpNotLike(String value) {
            addCriterion("IN_IP not like", value, "inIp");
            return (Criteria) this;
        }

        public Criteria andInIpIn(List<String> values) {
            addCriterion("IN_IP in", values, "inIp");
            return (Criteria) this;
        }

        public Criteria andInIpNotIn(List<String> values) {
            addCriterion("IN_IP not in", values, "inIp");
            return (Criteria) this;
        }

        public Criteria andInIpBetween(String value1, String value2) {
            addCriterion("IN_IP between", value1, value2, "inIp");
            return (Criteria) this;
        }

        public Criteria andInIpNotBetween(String value1, String value2) {
            addCriterion("IN_IP not between", value1, value2, "inIp");
            return (Criteria) this;
        }

        public Criteria andStaffLoginNameIsNull() {
            addCriterion("STAFF_LOGIN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStaffLoginNameIsNotNull() {
            addCriterion("STAFF_LOGIN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStaffLoginNameEqualTo(String value) {
            addCriterion("STAFF_LOGIN_NAME =", value, "staffLoginName");
            return (Criteria) this;
        }

        public Criteria andStaffLoginNameNotEqualTo(String value) {
            addCriterion("STAFF_LOGIN_NAME <>", value, "staffLoginName");
            return (Criteria) this;
        }

        public Criteria andStaffLoginNameGreaterThan(String value) {
            addCriterion("STAFF_LOGIN_NAME >", value, "staffLoginName");
            return (Criteria) this;
        }

        public Criteria andStaffLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_LOGIN_NAME >=", value, "staffLoginName");
            return (Criteria) this;
        }

        public Criteria andStaffLoginNameLessThan(String value) {
            addCriterion("STAFF_LOGIN_NAME <", value, "staffLoginName");
            return (Criteria) this;
        }

        public Criteria andStaffLoginNameLessThanOrEqualTo(String value) {
            addCriterion("STAFF_LOGIN_NAME <=", value, "staffLoginName");
            return (Criteria) this;
        }

        public Criteria andStaffLoginNameLike(String value) {
            addCriterion("STAFF_LOGIN_NAME like", value, "staffLoginName");
            return (Criteria) this;
        }

        public Criteria andStaffLoginNameNotLike(String value) {
            addCriterion("STAFF_LOGIN_NAME not like", value, "staffLoginName");
            return (Criteria) this;
        }

        public Criteria andStaffLoginNameIn(List<String> values) {
            addCriterion("STAFF_LOGIN_NAME in", values, "staffLoginName");
            return (Criteria) this;
        }

        public Criteria andStaffLoginNameNotIn(List<String> values) {
            addCriterion("STAFF_LOGIN_NAME not in", values, "staffLoginName");
            return (Criteria) this;
        }

        public Criteria andStaffLoginNameBetween(String value1, String value2) {
            addCriterion("STAFF_LOGIN_NAME between", value1, value2, "staffLoginName");
            return (Criteria) this;
        }

        public Criteria andStaffLoginNameNotBetween(String value1, String value2) {
            addCriterion("STAFF_LOGIN_NAME not between", value1, value2, "staffLoginName");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIsNull() {
            addCriterion("OPERATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIsNotNull() {
            addCriterion("OPERATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTimeEqualTo(Timestamp value) {
            addCriterion("OPERATE_TIME =", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotEqualTo(Timestamp value) {
            addCriterion("OPERATE_TIME <>", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeGreaterThan(Timestamp value) {
            addCriterion("OPERATE_TIME >", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("OPERATE_TIME >=", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLessThan(Timestamp value) {
            addCriterion("OPERATE_TIME <", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("OPERATE_TIME <=", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIn(List<Timestamp> values) {
            addCriterion("OPERATE_TIME in", values, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotIn(List<Timestamp> values) {
            addCriterion("OPERATE_TIME not in", values, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("OPERATE_TIME between", value1, value2, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("OPERATE_TIME not between", value1, value2, "operateTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andLoginFlagIsNull() {
            addCriterion("LOGIN_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andLoginFlagIsNotNull() {
            addCriterion("LOGIN_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andLoginFlagEqualTo(String value) {
            addCriterion("LOGIN_FLAG =", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagNotEqualTo(String value) {
            addCriterion("LOGIN_FLAG <>", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagGreaterThan(String value) {
            addCriterion("LOGIN_FLAG >", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_FLAG >=", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagLessThan(String value) {
            addCriterion("LOGIN_FLAG <", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_FLAG <=", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagLike(String value) {
            addCriterion("LOGIN_FLAG like", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagNotLike(String value) {
            addCriterion("LOGIN_FLAG not like", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagIn(List<String> values) {
            addCriterion("LOGIN_FLAG in", values, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagNotIn(List<String> values) {
            addCriterion("LOGIN_FLAG not in", values, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagBetween(String value1, String value2) {
            addCriterion("LOGIN_FLAG between", value1, value2, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagNotBetween(String value1, String value2) {
            addCriterion("LOGIN_FLAG not between", value1, value2, "loginFlag");
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

        public Criteria andLoginFromIsNull() {
            addCriterion("LOGIN_FROM is null");
            return (Criteria) this;
        }

        public Criteria andLoginFromIsNotNull() {
            addCriterion("LOGIN_FROM is not null");
            return (Criteria) this;
        }

        public Criteria andLoginFromEqualTo(String value) {
            addCriterion("LOGIN_FROM =", value, "loginFrom");
            return (Criteria) this;
        }

        public Criteria andLoginFromNotEqualTo(String value) {
            addCriterion("LOGIN_FROM <>", value, "loginFrom");
            return (Criteria) this;
        }

        public Criteria andLoginFromGreaterThan(String value) {
            addCriterion("LOGIN_FROM >", value, "loginFrom");
            return (Criteria) this;
        }

        public Criteria andLoginFromGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_FROM >=", value, "loginFrom");
            return (Criteria) this;
        }

        public Criteria andLoginFromLessThan(String value) {
            addCriterion("LOGIN_FROM <", value, "loginFrom");
            return (Criteria) this;
        }

        public Criteria andLoginFromLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_FROM <=", value, "loginFrom");
            return (Criteria) this;
        }

        public Criteria andLoginFromLike(String value) {
            addCriterion("LOGIN_FROM like", value, "loginFrom");
            return (Criteria) this;
        }

        public Criteria andLoginFromNotLike(String value) {
            addCriterion("LOGIN_FROM not like", value, "loginFrom");
            return (Criteria) this;
        }

        public Criteria andLoginFromIn(List<String> values) {
            addCriterion("LOGIN_FROM in", values, "loginFrom");
            return (Criteria) this;
        }

        public Criteria andLoginFromNotIn(List<String> values) {
            addCriterion("LOGIN_FROM not in", values, "loginFrom");
            return (Criteria) this;
        }

        public Criteria andLoginFromBetween(String value1, String value2) {
            addCriterion("LOGIN_FROM between", value1, value2, "loginFrom");
            return (Criteria) this;
        }

        public Criteria andLoginFromNotBetween(String value1, String value2) {
            addCriterion("LOGIN_FROM not between", value1, value2, "loginFrom");
            return (Criteria) this;
        }

        public Criteria andAppKeyIsNull() {
            addCriterion("APP_KEY is null");
            return (Criteria) this;
        }

        public Criteria andAppKeyIsNotNull() {
            addCriterion("APP_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andAppKeyEqualTo(String value) {
            addCriterion("APP_KEY =", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotEqualTo(String value) {
            addCriterion("APP_KEY <>", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyGreaterThan(String value) {
            addCriterion("APP_KEY >", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyGreaterThanOrEqualTo(String value) {
            addCriterion("APP_KEY >=", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyLessThan(String value) {
            addCriterion("APP_KEY <", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyLessThanOrEqualTo(String value) {
            addCriterion("APP_KEY <=", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyLike(String value) {
            addCriterion("APP_KEY like", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotLike(String value) {
            addCriterion("APP_KEY not like", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyIn(List<String> values) {
            addCriterion("APP_KEY in", values, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotIn(List<String> values) {
            addCriterion("APP_KEY not in", values, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyBetween(String value1, String value2) {
            addCriterion("APP_KEY between", value1, value2, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotBetween(String value1, String value2) {
            addCriterion("APP_KEY not between", value1, value2, "appKey");
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