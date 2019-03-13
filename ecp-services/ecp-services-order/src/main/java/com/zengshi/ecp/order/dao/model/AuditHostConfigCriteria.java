package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AuditHostConfigCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public AuditHostConfigCriteria() {
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

        public Criteria andAuditAcctnameIsNull() {
            addCriterion("AUDIT_ACCTNAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditAcctnameIsNotNull() {
            addCriterion("AUDIT_ACCTNAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditAcctnameEqualTo(String value) {
            addCriterion("AUDIT_ACCTNAME =", value, "auditAcctname");
            return (Criteria) this;
        }

        public Criteria andAuditAcctnameNotEqualTo(String value) {
            addCriterion("AUDIT_ACCTNAME <>", value, "auditAcctname");
            return (Criteria) this;
        }

        public Criteria andAuditAcctnameGreaterThan(String value) {
            addCriterion("AUDIT_ACCTNAME >", value, "auditAcctname");
            return (Criteria) this;
        }

        public Criteria andAuditAcctnameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_ACCTNAME >=", value, "auditAcctname");
            return (Criteria) this;
        }

        public Criteria andAuditAcctnameLessThan(String value) {
            addCriterion("AUDIT_ACCTNAME <", value, "auditAcctname");
            return (Criteria) this;
        }

        public Criteria andAuditAcctnameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_ACCTNAME <=", value, "auditAcctname");
            return (Criteria) this;
        }

        public Criteria andAuditAcctnameLike(String value) {
            addCriterion("AUDIT_ACCTNAME like", value, "auditAcctname");
            return (Criteria) this;
        }

        public Criteria andAuditAcctnameNotLike(String value) {
            addCriterion("AUDIT_ACCTNAME not like", value, "auditAcctname");
            return (Criteria) this;
        }

        public Criteria andAuditAcctnameIn(List<String> values) {
            addCriterion("AUDIT_ACCTNAME in", values, "auditAcctname");
            return (Criteria) this;
        }

        public Criteria andAuditAcctnameNotIn(List<String> values) {
            addCriterion("AUDIT_ACCTNAME not in", values, "auditAcctname");
            return (Criteria) this;
        }

        public Criteria andAuditAcctnameBetween(String value1, String value2) {
            addCriterion("AUDIT_ACCTNAME between", value1, value2, "auditAcctname");
            return (Criteria) this;
        }

        public Criteria andAuditAcctnameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_ACCTNAME not between", value1, value2, "auditAcctname");
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

        public Criteria andConnTypeIsNull() {
            addCriterion("CONN_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andConnTypeIsNotNull() {
            addCriterion("CONN_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andConnTypeEqualTo(String value) {
            addCriterion("CONN_TYPE =", value, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeNotEqualTo(String value) {
            addCriterion("CONN_TYPE <>", value, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeGreaterThan(String value) {
            addCriterion("CONN_TYPE >", value, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CONN_TYPE >=", value, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeLessThan(String value) {
            addCriterion("CONN_TYPE <", value, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeLessThanOrEqualTo(String value) {
            addCriterion("CONN_TYPE <=", value, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeLike(String value) {
            addCriterion("CONN_TYPE like", value, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeNotLike(String value) {
            addCriterion("CONN_TYPE not like", value, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeIn(List<String> values) {
            addCriterion("CONN_TYPE in", values, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeNotIn(List<String> values) {
            addCriterion("CONN_TYPE not in", values, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeBetween(String value1, String value2) {
            addCriterion("CONN_TYPE between", value1, value2, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeNotBetween(String value1, String value2) {
            addCriterion("CONN_TYPE not between", value1, value2, "connType");
            return (Criteria) this;
        }

        public Criteria andServerHostIsNull() {
            addCriterion("SERVER_HOST is null");
            return (Criteria) this;
        }

        public Criteria andServerHostIsNotNull() {
            addCriterion("SERVER_HOST is not null");
            return (Criteria) this;
        }

        public Criteria andServerHostEqualTo(String value) {
            addCriterion("SERVER_HOST =", value, "serverHost");
            return (Criteria) this;
        }

        public Criteria andServerHostNotEqualTo(String value) {
            addCriterion("SERVER_HOST <>", value, "serverHost");
            return (Criteria) this;
        }

        public Criteria andServerHostGreaterThan(String value) {
            addCriterion("SERVER_HOST >", value, "serverHost");
            return (Criteria) this;
        }

        public Criteria andServerHostGreaterThanOrEqualTo(String value) {
            addCriterion("SERVER_HOST >=", value, "serverHost");
            return (Criteria) this;
        }

        public Criteria andServerHostLessThan(String value) {
            addCriterion("SERVER_HOST <", value, "serverHost");
            return (Criteria) this;
        }

        public Criteria andServerHostLessThanOrEqualTo(String value) {
            addCriterion("SERVER_HOST <=", value, "serverHost");
            return (Criteria) this;
        }

        public Criteria andServerHostLike(String value) {
            addCriterion("SERVER_HOST like", value, "serverHost");
            return (Criteria) this;
        }

        public Criteria andServerHostNotLike(String value) {
            addCriterion("SERVER_HOST not like", value, "serverHost");
            return (Criteria) this;
        }

        public Criteria andServerHostIn(List<String> values) {
            addCriterion("SERVER_HOST in", values, "serverHost");
            return (Criteria) this;
        }

        public Criteria andServerHostNotIn(List<String> values) {
            addCriterion("SERVER_HOST not in", values, "serverHost");
            return (Criteria) this;
        }

        public Criteria andServerHostBetween(String value1, String value2) {
            addCriterion("SERVER_HOST between", value1, value2, "serverHost");
            return (Criteria) this;
        }

        public Criteria andServerHostNotBetween(String value1, String value2) {
            addCriterion("SERVER_HOST not between", value1, value2, "serverHost");
            return (Criteria) this;
        }

        public Criteria andServerPortIsNull() {
            addCriterion("SERVER_PORT is null");
            return (Criteria) this;
        }

        public Criteria andServerPortIsNotNull() {
            addCriterion("SERVER_PORT is not null");
            return (Criteria) this;
        }

        public Criteria andServerPortEqualTo(Integer value) {
            addCriterion("SERVER_PORT =", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortNotEqualTo(Integer value) {
            addCriterion("SERVER_PORT <>", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortGreaterThan(Integer value) {
            addCriterion("SERVER_PORT >", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("SERVER_PORT >=", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortLessThan(Integer value) {
            addCriterion("SERVER_PORT <", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortLessThanOrEqualTo(Integer value) {
            addCriterion("SERVER_PORT <=", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortIn(List<Integer> values) {
            addCriterion("SERVER_PORT in", values, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortNotIn(List<Integer> values) {
            addCriterion("SERVER_PORT not in", values, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortBetween(Integer value1, Integer value2) {
            addCriterion("SERVER_PORT between", value1, value2, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortNotBetween(Integer value1, Integer value2) {
            addCriterion("SERVER_PORT not between", value1, value2, "serverPort");
            return (Criteria) this;
        }

        public Criteria andLoginUsernameIsNull() {
            addCriterion("LOGIN_USERNAME is null");
            return (Criteria) this;
        }

        public Criteria andLoginUsernameIsNotNull() {
            addCriterion("LOGIN_USERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andLoginUsernameEqualTo(String value) {
            addCriterion("LOGIN_USERNAME =", value, "loginUsername");
            return (Criteria) this;
        }

        public Criteria andLoginUsernameNotEqualTo(String value) {
            addCriterion("LOGIN_USERNAME <>", value, "loginUsername");
            return (Criteria) this;
        }

        public Criteria andLoginUsernameGreaterThan(String value) {
            addCriterion("LOGIN_USERNAME >", value, "loginUsername");
            return (Criteria) this;
        }

        public Criteria andLoginUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_USERNAME >=", value, "loginUsername");
            return (Criteria) this;
        }

        public Criteria andLoginUsernameLessThan(String value) {
            addCriterion("LOGIN_USERNAME <", value, "loginUsername");
            return (Criteria) this;
        }

        public Criteria andLoginUsernameLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_USERNAME <=", value, "loginUsername");
            return (Criteria) this;
        }

        public Criteria andLoginUsernameLike(String value) {
            addCriterion("LOGIN_USERNAME like", value, "loginUsername");
            return (Criteria) this;
        }

        public Criteria andLoginUsernameNotLike(String value) {
            addCriterion("LOGIN_USERNAME not like", value, "loginUsername");
            return (Criteria) this;
        }

        public Criteria andLoginUsernameIn(List<String> values) {
            addCriterion("LOGIN_USERNAME in", values, "loginUsername");
            return (Criteria) this;
        }

        public Criteria andLoginUsernameNotIn(List<String> values) {
            addCriterion("LOGIN_USERNAME not in", values, "loginUsername");
            return (Criteria) this;
        }

        public Criteria andLoginUsernameBetween(String value1, String value2) {
            addCriterion("LOGIN_USERNAME between", value1, value2, "loginUsername");
            return (Criteria) this;
        }

        public Criteria andLoginUsernameNotBetween(String value1, String value2) {
            addCriterion("LOGIN_USERNAME not between", value1, value2, "loginUsername");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordIsNull() {
            addCriterion("LOGIN_PASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordIsNotNull() {
            addCriterion("LOGIN_PASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordEqualTo(String value) {
            addCriterion("LOGIN_PASSWORD =", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotEqualTo(String value) {
            addCriterion("LOGIN_PASSWORD <>", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordGreaterThan(String value) {
            addCriterion("LOGIN_PASSWORD >", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_PASSWORD >=", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordLessThan(String value) {
            addCriterion("LOGIN_PASSWORD <", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_PASSWORD <=", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordLike(String value) {
            addCriterion("LOGIN_PASSWORD like", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotLike(String value) {
            addCriterion("LOGIN_PASSWORD not like", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordIn(List<String> values) {
            addCriterion("LOGIN_PASSWORD in", values, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotIn(List<String> values) {
            addCriterion("LOGIN_PASSWORD not in", values, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordBetween(String value1, String value2) {
            addCriterion("LOGIN_PASSWORD between", value1, value2, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotBetween(String value1, String value2) {
            addCriterion("LOGIN_PASSWORD not between", value1, value2, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andRemotePathIsNull() {
            addCriterion("REMOTE_PATH is null");
            return (Criteria) this;
        }

        public Criteria andRemotePathIsNotNull() {
            addCriterion("REMOTE_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andRemotePathEqualTo(String value) {
            addCriterion("REMOTE_PATH =", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathNotEqualTo(String value) {
            addCriterion("REMOTE_PATH <>", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathGreaterThan(String value) {
            addCriterion("REMOTE_PATH >", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathGreaterThanOrEqualTo(String value) {
            addCriterion("REMOTE_PATH >=", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathLessThan(String value) {
            addCriterion("REMOTE_PATH <", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathLessThanOrEqualTo(String value) {
            addCriterion("REMOTE_PATH <=", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathLike(String value) {
            addCriterion("REMOTE_PATH like", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathNotLike(String value) {
            addCriterion("REMOTE_PATH not like", value, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathIn(List<String> values) {
            addCriterion("REMOTE_PATH in", values, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathNotIn(List<String> values) {
            addCriterion("REMOTE_PATH not in", values, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathBetween(String value1, String value2) {
            addCriterion("REMOTE_PATH between", value1, value2, "remotePath");
            return (Criteria) this;
        }

        public Criteria andRemotePathNotBetween(String value1, String value2) {
            addCriterion("REMOTE_PATH not between", value1, value2, "remotePath");
            return (Criteria) this;
        }

        public Criteria andFilenameCodeIsNull() {
            addCriterion("FILENAME_CODE is null");
            return (Criteria) this;
        }

        public Criteria andFilenameCodeIsNotNull() {
            addCriterion("FILENAME_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andFilenameCodeEqualTo(String value) {
            addCriterion("FILENAME_CODE =", value, "filenameCode");
            return (Criteria) this;
        }

        public Criteria andFilenameCodeNotEqualTo(String value) {
            addCriterion("FILENAME_CODE <>", value, "filenameCode");
            return (Criteria) this;
        }

        public Criteria andFilenameCodeGreaterThan(String value) {
            addCriterion("FILENAME_CODE >", value, "filenameCode");
            return (Criteria) this;
        }

        public Criteria andFilenameCodeGreaterThanOrEqualTo(String value) {
            addCriterion("FILENAME_CODE >=", value, "filenameCode");
            return (Criteria) this;
        }

        public Criteria andFilenameCodeLessThan(String value) {
            addCriterion("FILENAME_CODE <", value, "filenameCode");
            return (Criteria) this;
        }

        public Criteria andFilenameCodeLessThanOrEqualTo(String value) {
            addCriterion("FILENAME_CODE <=", value, "filenameCode");
            return (Criteria) this;
        }

        public Criteria andFilenameCodeLike(String value) {
            addCriterion("FILENAME_CODE like", value, "filenameCode");
            return (Criteria) this;
        }

        public Criteria andFilenameCodeNotLike(String value) {
            addCriterion("FILENAME_CODE not like", value, "filenameCode");
            return (Criteria) this;
        }

        public Criteria andFilenameCodeIn(List<String> values) {
            addCriterion("FILENAME_CODE in", values, "filenameCode");
            return (Criteria) this;
        }

        public Criteria andFilenameCodeNotIn(List<String> values) {
            addCriterion("FILENAME_CODE not in", values, "filenameCode");
            return (Criteria) this;
        }

        public Criteria andFilenameCodeBetween(String value1, String value2) {
            addCriterion("FILENAME_CODE between", value1, value2, "filenameCode");
            return (Criteria) this;
        }

        public Criteria andFilenameCodeNotBetween(String value1, String value2) {
            addCriterion("FILENAME_CODE not between", value1, value2, "filenameCode");
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