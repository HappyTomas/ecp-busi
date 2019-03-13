package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AuditAutoLogCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public AuditAutoLogCriteria() {
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

        public Criteria andExecMethodsIsNull() {
            addCriterion("EXEC_METHODS is null");
            return (Criteria) this;
        }

        public Criteria andExecMethodsIsNotNull() {
            addCriterion("EXEC_METHODS is not null");
            return (Criteria) this;
        }

        public Criteria andExecMethodsEqualTo(String value) {
            addCriterion("EXEC_METHODS =", value, "execMethods");
            return (Criteria) this;
        }

        public Criteria andExecMethodsNotEqualTo(String value) {
            addCriterion("EXEC_METHODS <>", value, "execMethods");
            return (Criteria) this;
        }

        public Criteria andExecMethodsGreaterThan(String value) {
            addCriterion("EXEC_METHODS >", value, "execMethods");
            return (Criteria) this;
        }

        public Criteria andExecMethodsGreaterThanOrEqualTo(String value) {
            addCriterion("EXEC_METHODS >=", value, "execMethods");
            return (Criteria) this;
        }

        public Criteria andExecMethodsLessThan(String value) {
            addCriterion("EXEC_METHODS <", value, "execMethods");
            return (Criteria) this;
        }

        public Criteria andExecMethodsLessThanOrEqualTo(String value) {
            addCriterion("EXEC_METHODS <=", value, "execMethods");
            return (Criteria) this;
        }

        public Criteria andExecMethodsLike(String value) {
            addCriterion("EXEC_METHODS like", value, "execMethods");
            return (Criteria) this;
        }

        public Criteria andExecMethodsNotLike(String value) {
            addCriterion("EXEC_METHODS not like", value, "execMethods");
            return (Criteria) this;
        }

        public Criteria andExecMethodsIn(List<String> values) {
            addCriterion("EXEC_METHODS in", values, "execMethods");
            return (Criteria) this;
        }

        public Criteria andExecMethodsNotIn(List<String> values) {
            addCriterion("EXEC_METHODS not in", values, "execMethods");
            return (Criteria) this;
        }

        public Criteria andExecMethodsBetween(String value1, String value2) {
            addCriterion("EXEC_METHODS between", value1, value2, "execMethods");
            return (Criteria) this;
        }

        public Criteria andExecMethodsNotBetween(String value1, String value2) {
            addCriterion("EXEC_METHODS not between", value1, value2, "execMethods");
            return (Criteria) this;
        }

        public Criteria andStepDescIsNull() {
            addCriterion("STEP_DESC is null");
            return (Criteria) this;
        }

        public Criteria andStepDescIsNotNull() {
            addCriterion("STEP_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andStepDescEqualTo(String value) {
            addCriterion("STEP_DESC =", value, "stepDesc");
            return (Criteria) this;
        }

        public Criteria andStepDescNotEqualTo(String value) {
            addCriterion("STEP_DESC <>", value, "stepDesc");
            return (Criteria) this;
        }

        public Criteria andStepDescGreaterThan(String value) {
            addCriterion("STEP_DESC >", value, "stepDesc");
            return (Criteria) this;
        }

        public Criteria andStepDescGreaterThanOrEqualTo(String value) {
            addCriterion("STEP_DESC >=", value, "stepDesc");
            return (Criteria) this;
        }

        public Criteria andStepDescLessThan(String value) {
            addCriterion("STEP_DESC <", value, "stepDesc");
            return (Criteria) this;
        }

        public Criteria andStepDescLessThanOrEqualTo(String value) {
            addCriterion("STEP_DESC <=", value, "stepDesc");
            return (Criteria) this;
        }

        public Criteria andStepDescLike(String value) {
            addCriterion("STEP_DESC like", value, "stepDesc");
            return (Criteria) this;
        }

        public Criteria andStepDescNotLike(String value) {
            addCriterion("STEP_DESC not like", value, "stepDesc");
            return (Criteria) this;
        }

        public Criteria andStepDescIn(List<String> values) {
            addCriterion("STEP_DESC in", values, "stepDesc");
            return (Criteria) this;
        }

        public Criteria andStepDescNotIn(List<String> values) {
            addCriterion("STEP_DESC not in", values, "stepDesc");
            return (Criteria) this;
        }

        public Criteria andStepDescBetween(String value1, String value2) {
            addCriterion("STEP_DESC between", value1, value2, "stepDesc");
            return (Criteria) this;
        }

        public Criteria andStepDescNotBetween(String value1, String value2) {
            addCriterion("STEP_DESC not between", value1, value2, "stepDesc");
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

        public Criteria andExecuteTimeIsNull() {
            addCriterion("EXECUTE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeIsNotNull() {
            addCriterion("EXECUTE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeEqualTo(Timestamp value) {
            addCriterion("EXECUTE_TIME =", value, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeNotEqualTo(Timestamp value) {
            addCriterion("EXECUTE_TIME <>", value, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeGreaterThan(Timestamp value) {
            addCriterion("EXECUTE_TIME >", value, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("EXECUTE_TIME >=", value, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeLessThan(Timestamp value) {
            addCriterion("EXECUTE_TIME <", value, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("EXECUTE_TIME <=", value, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeIn(List<Timestamp> values) {
            addCriterion("EXECUTE_TIME in", values, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeNotIn(List<Timestamp> values) {
            addCriterion("EXECUTE_TIME not in", values, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EXECUTE_TIME between", value1, value2, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EXECUTE_TIME not between", value1, value2, "executeTime");
            return (Criteria) this;
        }

        public Criteria andServerPidIsNull() {
            addCriterion("SERVER_PID is null");
            return (Criteria) this;
        }

        public Criteria andServerPidIsNotNull() {
            addCriterion("SERVER_PID is not null");
            return (Criteria) this;
        }

        public Criteria andServerPidEqualTo(String value) {
            addCriterion("SERVER_PID =", value, "serverPid");
            return (Criteria) this;
        }

        public Criteria andServerPidNotEqualTo(String value) {
            addCriterion("SERVER_PID <>", value, "serverPid");
            return (Criteria) this;
        }

        public Criteria andServerPidGreaterThan(String value) {
            addCriterion("SERVER_PID >", value, "serverPid");
            return (Criteria) this;
        }

        public Criteria andServerPidGreaterThanOrEqualTo(String value) {
            addCriterion("SERVER_PID >=", value, "serverPid");
            return (Criteria) this;
        }

        public Criteria andServerPidLessThan(String value) {
            addCriterion("SERVER_PID <", value, "serverPid");
            return (Criteria) this;
        }

        public Criteria andServerPidLessThanOrEqualTo(String value) {
            addCriterion("SERVER_PID <=", value, "serverPid");
            return (Criteria) this;
        }

        public Criteria andServerPidLike(String value) {
            addCriterion("SERVER_PID like", value, "serverPid");
            return (Criteria) this;
        }

        public Criteria andServerPidNotLike(String value) {
            addCriterion("SERVER_PID not like", value, "serverPid");
            return (Criteria) this;
        }

        public Criteria andServerPidIn(List<String> values) {
            addCriterion("SERVER_PID in", values, "serverPid");
            return (Criteria) this;
        }

        public Criteria andServerPidNotIn(List<String> values) {
            addCriterion("SERVER_PID not in", values, "serverPid");
            return (Criteria) this;
        }

        public Criteria andServerPidBetween(String value1, String value2) {
            addCriterion("SERVER_PID between", value1, value2, "serverPid");
            return (Criteria) this;
        }

        public Criteria andServerPidNotBetween(String value1, String value2) {
            addCriterion("SERVER_PID not between", value1, value2, "serverPid");
            return (Criteria) this;
        }

        public Criteria andServerHostipIsNull() {
            addCriterion("SERVER_HOSTIP is null");
            return (Criteria) this;
        }

        public Criteria andServerHostipIsNotNull() {
            addCriterion("SERVER_HOSTIP is not null");
            return (Criteria) this;
        }

        public Criteria andServerHostipEqualTo(String value) {
            addCriterion("SERVER_HOSTIP =", value, "serverHostip");
            return (Criteria) this;
        }

        public Criteria andServerHostipNotEqualTo(String value) {
            addCriterion("SERVER_HOSTIP <>", value, "serverHostip");
            return (Criteria) this;
        }

        public Criteria andServerHostipGreaterThan(String value) {
            addCriterion("SERVER_HOSTIP >", value, "serverHostip");
            return (Criteria) this;
        }

        public Criteria andServerHostipGreaterThanOrEqualTo(String value) {
            addCriterion("SERVER_HOSTIP >=", value, "serverHostip");
            return (Criteria) this;
        }

        public Criteria andServerHostipLessThan(String value) {
            addCriterion("SERVER_HOSTIP <", value, "serverHostip");
            return (Criteria) this;
        }

        public Criteria andServerHostipLessThanOrEqualTo(String value) {
            addCriterion("SERVER_HOSTIP <=", value, "serverHostip");
            return (Criteria) this;
        }

        public Criteria andServerHostipLike(String value) {
            addCriterion("SERVER_HOSTIP like", value, "serverHostip");
            return (Criteria) this;
        }

        public Criteria andServerHostipNotLike(String value) {
            addCriterion("SERVER_HOSTIP not like", value, "serverHostip");
            return (Criteria) this;
        }

        public Criteria andServerHostipIn(List<String> values) {
            addCriterion("SERVER_HOSTIP in", values, "serverHostip");
            return (Criteria) this;
        }

        public Criteria andServerHostipNotIn(List<String> values) {
            addCriterion("SERVER_HOSTIP not in", values, "serverHostip");
            return (Criteria) this;
        }

        public Criteria andServerHostipBetween(String value1, String value2) {
            addCriterion("SERVER_HOSTIP between", value1, value2, "serverHostip");
            return (Criteria) this;
        }

        public Criteria andServerHostipNotBetween(String value1, String value2) {
            addCriterion("SERVER_HOSTIP not between", value1, value2, "serverHostip");
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