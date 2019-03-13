package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AuthStaffCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public AuthStaffCriteria() {
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

        public Criteria andStaffClassIsNull() {
            addCriterion("STAFF_CLASS is null");
            return (Criteria) this;
        }

        public Criteria andStaffClassIsNotNull() {
            addCriterion("STAFF_CLASS is not null");
            return (Criteria) this;
        }

        public Criteria andStaffClassEqualTo(String value) {
            addCriterion("STAFF_CLASS =", value, "staffClass");
            return (Criteria) this;
        }

        public Criteria andStaffClassNotEqualTo(String value) {
            addCriterion("STAFF_CLASS <>", value, "staffClass");
            return (Criteria) this;
        }

        public Criteria andStaffClassGreaterThan(String value) {
            addCriterion("STAFF_CLASS >", value, "staffClass");
            return (Criteria) this;
        }

        public Criteria andStaffClassGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_CLASS >=", value, "staffClass");
            return (Criteria) this;
        }

        public Criteria andStaffClassLessThan(String value) {
            addCriterion("STAFF_CLASS <", value, "staffClass");
            return (Criteria) this;
        }

        public Criteria andStaffClassLessThanOrEqualTo(String value) {
            addCriterion("STAFF_CLASS <=", value, "staffClass");
            return (Criteria) this;
        }

        public Criteria andStaffClassLike(String value) {
            addCriterion("STAFF_CLASS like", value, "staffClass");
            return (Criteria) this;
        }

        public Criteria andStaffClassNotLike(String value) {
            addCriterion("STAFF_CLASS not like", value, "staffClass");
            return (Criteria) this;
        }

        public Criteria andStaffClassIn(List<String> values) {
            addCriterion("STAFF_CLASS in", values, "staffClass");
            return (Criteria) this;
        }

        public Criteria andStaffClassNotIn(List<String> values) {
            addCriterion("STAFF_CLASS not in", values, "staffClass");
            return (Criteria) this;
        }

        public Criteria andStaffClassBetween(String value1, String value2) {
            addCriterion("STAFF_CLASS between", value1, value2, "staffClass");
            return (Criteria) this;
        }

        public Criteria andStaffClassNotBetween(String value1, String value2) {
            addCriterion("STAFF_CLASS not between", value1, value2, "staffClass");
            return (Criteria) this;
        }

        public Criteria andStaffFlagIsNull() {
            addCriterion("STAFF_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andStaffFlagIsNotNull() {
            addCriterion("STAFF_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andStaffFlagEqualTo(String value) {
            addCriterion("STAFF_FLAG =", value, "staffFlag");
            return (Criteria) this;
        }

        public Criteria andStaffFlagNotEqualTo(String value) {
            addCriterion("STAFF_FLAG <>", value, "staffFlag");
            return (Criteria) this;
        }

        public Criteria andStaffFlagGreaterThan(String value) {
            addCriterion("STAFF_FLAG >", value, "staffFlag");
            return (Criteria) this;
        }

        public Criteria andStaffFlagGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_FLAG >=", value, "staffFlag");
            return (Criteria) this;
        }

        public Criteria andStaffFlagLessThan(String value) {
            addCriterion("STAFF_FLAG <", value, "staffFlag");
            return (Criteria) this;
        }

        public Criteria andStaffFlagLessThanOrEqualTo(String value) {
            addCriterion("STAFF_FLAG <=", value, "staffFlag");
            return (Criteria) this;
        }

        public Criteria andStaffFlagLike(String value) {
            addCriterion("STAFF_FLAG like", value, "staffFlag");
            return (Criteria) this;
        }

        public Criteria andStaffFlagNotLike(String value) {
            addCriterion("STAFF_FLAG not like", value, "staffFlag");
            return (Criteria) this;
        }

        public Criteria andStaffFlagIn(List<String> values) {
            addCriterion("STAFF_FLAG in", values, "staffFlag");
            return (Criteria) this;
        }

        public Criteria andStaffFlagNotIn(List<String> values) {
            addCriterion("STAFF_FLAG not in", values, "staffFlag");
            return (Criteria) this;
        }

        public Criteria andStaffFlagBetween(String value1, String value2) {
            addCriterion("STAFF_FLAG between", value1, value2, "staffFlag");
            return (Criteria) this;
        }

        public Criteria andStaffFlagNotBetween(String value1, String value2) {
            addCriterion("STAFF_FLAG not between", value1, value2, "staffFlag");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdIsNull() {
            addCriterion("STAFF_PASSWD is null");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdIsNotNull() {
            addCriterion("STAFF_PASSWD is not null");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdEqualTo(String value) {
            addCriterion("STAFF_PASSWD =", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdNotEqualTo(String value) {
            addCriterion("STAFF_PASSWD <>", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdGreaterThan(String value) {
            addCriterion("STAFF_PASSWD >", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_PASSWD >=", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdLessThan(String value) {
            addCriterion("STAFF_PASSWD <", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdLessThanOrEqualTo(String value) {
            addCriterion("STAFF_PASSWD <=", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdLike(String value) {
            addCriterion("STAFF_PASSWD like", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdNotLike(String value) {
            addCriterion("STAFF_PASSWD not like", value, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdIn(List<String> values) {
            addCriterion("STAFF_PASSWD in", values, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdNotIn(List<String> values) {
            addCriterion("STAFF_PASSWD not in", values, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdBetween(String value1, String value2) {
            addCriterion("STAFF_PASSWD between", value1, value2, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andStaffPasswdNotBetween(String value1, String value2) {
            addCriterion("STAFF_PASSWD not between", value1, value2, "staffPasswd");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIsNull() {
            addCriterion("INVALID_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIsNotNull() {
            addCriterion("INVALID_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeEqualTo(Timestamp value) {
            addCriterion("INVALID_TIME =", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotEqualTo(Timestamp value) {
            addCriterion("INVALID_TIME <>", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeGreaterThan(Timestamp value) {
            addCriterion("INVALID_TIME >", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("INVALID_TIME >=", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeLessThan(Timestamp value) {
            addCriterion("INVALID_TIME <", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("INVALID_TIME <=", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIn(List<Timestamp> values) {
            addCriterion("INVALID_TIME in", values, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotIn(List<Timestamp> values) {
            addCriterion("INVALID_TIME not in", values, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INVALID_TIME between", value1, value2, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INVALID_TIME not between", value1, value2, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Timestamp value) {
            addCriterion("START_DATE =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Timestamp value) {
            addCriterion("START_DATE <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Timestamp value) {
            addCriterion("START_DATE >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("START_DATE >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Timestamp value) {
            addCriterion("START_DATE <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("START_DATE <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Timestamp> values) {
            addCriterion("START_DATE in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Timestamp> values) {
            addCriterion("START_DATE not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("START_DATE between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("START_DATE not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIsNull() {
            addCriterion("LAST_LOGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIsNotNull() {
            addCriterion("LAST_LOGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeEqualTo(Timestamp value) {
            addCriterion("LAST_LOGIN_TIME =", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotEqualTo(Timestamp value) {
            addCriterion("LAST_LOGIN_TIME <>", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThan(Timestamp value) {
            addCriterion("LAST_LOGIN_TIME >", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("LAST_LOGIN_TIME >=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThan(Timestamp value) {
            addCriterion("LAST_LOGIN_TIME <", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("LAST_LOGIN_TIME <=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIn(List<Timestamp> values) {
            addCriterion("LAST_LOGIN_TIME in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotIn(List<Timestamp> values) {
            addCriterion("LAST_LOGIN_TIME not in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("LAST_LOGIN_TIME between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("LAST_LOGIN_TIME not between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLoginSuccessCntIsNull() {
            addCriterion("LOGIN_SUCCESS_CNT is null");
            return (Criteria) this;
        }

        public Criteria andLoginSuccessCntIsNotNull() {
            addCriterion("LOGIN_SUCCESS_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andLoginSuccessCntEqualTo(BigDecimal value) {
            addCriterion("LOGIN_SUCCESS_CNT =", value, "loginSuccessCnt");
            return (Criteria) this;
        }

        public Criteria andLoginSuccessCntNotEqualTo(BigDecimal value) {
            addCriterion("LOGIN_SUCCESS_CNT <>", value, "loginSuccessCnt");
            return (Criteria) this;
        }

        public Criteria andLoginSuccessCntGreaterThan(BigDecimal value) {
            addCriterion("LOGIN_SUCCESS_CNT >", value, "loginSuccessCnt");
            return (Criteria) this;
        }

        public Criteria andLoginSuccessCntGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LOGIN_SUCCESS_CNT >=", value, "loginSuccessCnt");
            return (Criteria) this;
        }

        public Criteria andLoginSuccessCntLessThan(BigDecimal value) {
            addCriterion("LOGIN_SUCCESS_CNT <", value, "loginSuccessCnt");
            return (Criteria) this;
        }

        public Criteria andLoginSuccessCntLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LOGIN_SUCCESS_CNT <=", value, "loginSuccessCnt");
            return (Criteria) this;
        }

        public Criteria andLoginSuccessCntIn(List<BigDecimal> values) {
            addCriterion("LOGIN_SUCCESS_CNT in", values, "loginSuccessCnt");
            return (Criteria) this;
        }

        public Criteria andLoginSuccessCntNotIn(List<BigDecimal> values) {
            addCriterion("LOGIN_SUCCESS_CNT not in", values, "loginSuccessCnt");
            return (Criteria) this;
        }

        public Criteria andLoginSuccessCntBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOGIN_SUCCESS_CNT between", value1, value2, "loginSuccessCnt");
            return (Criteria) this;
        }

        public Criteria andLoginSuccessCntNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOGIN_SUCCESS_CNT not between", value1, value2, "loginSuccessCnt");
            return (Criteria) this;
        }

        public Criteria andLoginFailureCntTodayIsNull() {
            addCriterion("LOGIN_FAILURE_CNT_TODAY is null");
            return (Criteria) this;
        }

        public Criteria andLoginFailureCntTodayIsNotNull() {
            addCriterion("LOGIN_FAILURE_CNT_TODAY is not null");
            return (Criteria) this;
        }

        public Criteria andLoginFailureCntTodayEqualTo(BigDecimal value) {
            addCriterion("LOGIN_FAILURE_CNT_TODAY =", value, "loginFailureCntToday");
            return (Criteria) this;
        }

        public Criteria andLoginFailureCntTodayNotEqualTo(BigDecimal value) {
            addCriterion("LOGIN_FAILURE_CNT_TODAY <>", value, "loginFailureCntToday");
            return (Criteria) this;
        }

        public Criteria andLoginFailureCntTodayGreaterThan(BigDecimal value) {
            addCriterion("LOGIN_FAILURE_CNT_TODAY >", value, "loginFailureCntToday");
            return (Criteria) this;
        }

        public Criteria andLoginFailureCntTodayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LOGIN_FAILURE_CNT_TODAY >=", value, "loginFailureCntToday");
            return (Criteria) this;
        }

        public Criteria andLoginFailureCntTodayLessThan(BigDecimal value) {
            addCriterion("LOGIN_FAILURE_CNT_TODAY <", value, "loginFailureCntToday");
            return (Criteria) this;
        }

        public Criteria andLoginFailureCntTodayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LOGIN_FAILURE_CNT_TODAY <=", value, "loginFailureCntToday");
            return (Criteria) this;
        }

        public Criteria andLoginFailureCntTodayIn(List<BigDecimal> values) {
            addCriterion("LOGIN_FAILURE_CNT_TODAY in", values, "loginFailureCntToday");
            return (Criteria) this;
        }

        public Criteria andLoginFailureCntTodayNotIn(List<BigDecimal> values) {
            addCriterion("LOGIN_FAILURE_CNT_TODAY not in", values, "loginFailureCntToday");
            return (Criteria) this;
        }

        public Criteria andLoginFailureCntTodayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOGIN_FAILURE_CNT_TODAY between", value1, value2, "loginFailureCntToday");
            return (Criteria) this;
        }

        public Criteria andLoginFailureCntTodayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOGIN_FAILURE_CNT_TODAY not between", value1, value2, "loginFailureCntToday");
            return (Criteria) this;
        }

        public Criteria andLastLoginFailureTimeIsNull() {
            addCriterion("LAST_LOGIN_FAILURE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginFailureTimeIsNotNull() {
            addCriterion("LAST_LOGIN_FAILURE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginFailureTimeEqualTo(Timestamp value) {
            addCriterion("LAST_LOGIN_FAILURE_TIME =", value, "lastLoginFailureTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginFailureTimeNotEqualTo(Timestamp value) {
            addCriterion("LAST_LOGIN_FAILURE_TIME <>", value, "lastLoginFailureTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginFailureTimeGreaterThan(Timestamp value) {
            addCriterion("LAST_LOGIN_FAILURE_TIME >", value, "lastLoginFailureTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginFailureTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("LAST_LOGIN_FAILURE_TIME >=", value, "lastLoginFailureTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginFailureTimeLessThan(Timestamp value) {
            addCriterion("LAST_LOGIN_FAILURE_TIME <", value, "lastLoginFailureTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginFailureTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("LAST_LOGIN_FAILURE_TIME <=", value, "lastLoginFailureTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginFailureTimeIn(List<Timestamp> values) {
            addCriterion("LAST_LOGIN_FAILURE_TIME in", values, "lastLoginFailureTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginFailureTimeNotIn(List<Timestamp> values) {
            addCriterion("LAST_LOGIN_FAILURE_TIME not in", values, "lastLoginFailureTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginFailureTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("LAST_LOGIN_FAILURE_TIME between", value1, value2, "lastLoginFailureTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginFailureTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("LAST_LOGIN_FAILURE_TIME not between", value1, value2, "lastLoginFailureTime");
            return (Criteria) this;
        }

        public Criteria andCreateFromIsNull() {
            addCriterion("CREATE_FROM is null");
            return (Criteria) this;
        }

        public Criteria andCreateFromIsNotNull() {
            addCriterion("CREATE_FROM is not null");
            return (Criteria) this;
        }

        public Criteria andCreateFromEqualTo(String value) {
            addCriterion("CREATE_FROM =", value, "createFrom");
            return (Criteria) this;
        }

        public Criteria andCreateFromNotEqualTo(String value) {
            addCriterion("CREATE_FROM <>", value, "createFrom");
            return (Criteria) this;
        }

        public Criteria andCreateFromGreaterThan(String value) {
            addCriterion("CREATE_FROM >", value, "createFrom");
            return (Criteria) this;
        }

        public Criteria andCreateFromGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_FROM >=", value, "createFrom");
            return (Criteria) this;
        }

        public Criteria andCreateFromLessThan(String value) {
            addCriterion("CREATE_FROM <", value, "createFrom");
            return (Criteria) this;
        }

        public Criteria andCreateFromLessThanOrEqualTo(String value) {
            addCriterion("CREATE_FROM <=", value, "createFrom");
            return (Criteria) this;
        }

        public Criteria andCreateFromLike(String value) {
            addCriterion("CREATE_FROM like", value, "createFrom");
            return (Criteria) this;
        }

        public Criteria andCreateFromNotLike(String value) {
            addCriterion("CREATE_FROM not like", value, "createFrom");
            return (Criteria) this;
        }

        public Criteria andCreateFromIn(List<String> values) {
            addCriterion("CREATE_FROM in", values, "createFrom");
            return (Criteria) this;
        }

        public Criteria andCreateFromNotIn(List<String> values) {
            addCriterion("CREATE_FROM not in", values, "createFrom");
            return (Criteria) this;
        }

        public Criteria andCreateFromBetween(String value1, String value2) {
            addCriterion("CREATE_FROM between", value1, value2, "createFrom");
            return (Criteria) this;
        }

        public Criteria andCreateFromNotBetween(String value1, String value2) {
            addCriterion("CREATE_FROM not between", value1, value2, "createFrom");
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

        public Criteria andUpdateStaffIsNull() {
            addCriterion("UPDATE_STAFF is null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIsNotNull() {
            addCriterion("UPDATE_STAFF is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffEqualTo(Long value) {
            addCriterion("UPDATE_STAFF =", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotEqualTo(Long value) {
            addCriterion("UPDATE_STAFF <>", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThan(Long value) {
            addCriterion("UPDATE_STAFF >", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThanOrEqualTo(Long value) {
            addCriterion("UPDATE_STAFF >=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThan(Long value) {
            addCriterion("UPDATE_STAFF <", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThanOrEqualTo(Long value) {
            addCriterion("UPDATE_STAFF <=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIn(List<Long> values) {
            addCriterion("UPDATE_STAFF in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotIn(List<Long> values) {
            addCriterion("UPDATE_STAFF not in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffBetween(Long value1, Long value2) {
            addCriterion("UPDATE_STAFF between", value1, value2, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotBetween(Long value1, Long value2) {
            addCriterion("UPDATE_STAFF not between", value1, value2, "updateStaff");
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