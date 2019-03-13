package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AuthPrivilegeCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public AuthPrivilegeCriteria() {
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

        public Criteria andRoleAdminIsNull() {
            addCriterion("ROLE_ADMIN is null");
            return (Criteria) this;
        }

        public Criteria andRoleAdminIsNotNull() {
            addCriterion("ROLE_ADMIN is not null");
            return (Criteria) this;
        }

        public Criteria andRoleAdminEqualTo(String value) {
            addCriterion("ROLE_ADMIN =", value, "roleAdmin");
            return (Criteria) this;
        }

        public Criteria andRoleAdminNotEqualTo(String value) {
            addCriterion("ROLE_ADMIN <>", value, "roleAdmin");
            return (Criteria) this;
        }

        public Criteria andRoleAdminGreaterThan(String value) {
            addCriterion("ROLE_ADMIN >", value, "roleAdmin");
            return (Criteria) this;
        }

        public Criteria andRoleAdminGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_ADMIN >=", value, "roleAdmin");
            return (Criteria) this;
        }

        public Criteria andRoleAdminLessThan(String value) {
            addCriterion("ROLE_ADMIN <", value, "roleAdmin");
            return (Criteria) this;
        }

        public Criteria andRoleAdminLessThanOrEqualTo(String value) {
            addCriterion("ROLE_ADMIN <=", value, "roleAdmin");
            return (Criteria) this;
        }

        public Criteria andRoleAdminLike(String value) {
            addCriterion("ROLE_ADMIN like", value, "roleAdmin");
            return (Criteria) this;
        }

        public Criteria andRoleAdminNotLike(String value) {
            addCriterion("ROLE_ADMIN not like", value, "roleAdmin");
            return (Criteria) this;
        }

        public Criteria andRoleAdminIn(List<String> values) {
            addCriterion("ROLE_ADMIN in", values, "roleAdmin");
            return (Criteria) this;
        }

        public Criteria andRoleAdminNotIn(List<String> values) {
            addCriterion("ROLE_ADMIN not in", values, "roleAdmin");
            return (Criteria) this;
        }

        public Criteria andRoleAdminBetween(String value1, String value2) {
            addCriterion("ROLE_ADMIN between", value1, value2, "roleAdmin");
            return (Criteria) this;
        }

        public Criteria andRoleAdminNotBetween(String value1, String value2) {
            addCriterion("ROLE_ADMIN not between", value1, value2, "roleAdmin");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeIsNull() {
            addCriterion("PRIVILEGE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeIsNotNull() {
            addCriterion("PRIVILEGE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeEqualTo(String value) {
            addCriterion("PRIVILEGE_TYPE =", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeNotEqualTo(String value) {
            addCriterion("PRIVILEGE_TYPE <>", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeGreaterThan(String value) {
            addCriterion("PRIVILEGE_TYPE >", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PRIVILEGE_TYPE >=", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeLessThan(String value) {
            addCriterion("PRIVILEGE_TYPE <", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeLessThanOrEqualTo(String value) {
            addCriterion("PRIVILEGE_TYPE <=", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeLike(String value) {
            addCriterion("PRIVILEGE_TYPE like", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeNotLike(String value) {
            addCriterion("PRIVILEGE_TYPE not like", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeIn(List<String> values) {
            addCriterion("PRIVILEGE_TYPE in", values, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeNotIn(List<String> values) {
            addCriterion("PRIVILEGE_TYPE not in", values, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeBetween(String value1, String value2) {
            addCriterion("PRIVILEGE_TYPE between", value1, value2, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeNotBetween(String value1, String value2) {
            addCriterion("PRIVILEGE_TYPE not between", value1, value2, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andSysCodeIsNull() {
            addCriterion("SYS_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSysCodeIsNotNull() {
            addCriterion("SYS_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSysCodeEqualTo(String value) {
            addCriterion("SYS_CODE =", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotEqualTo(String value) {
            addCriterion("SYS_CODE <>", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeGreaterThan(String value) {
            addCriterion("SYS_CODE >", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_CODE >=", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeLessThan(String value) {
            addCriterion("SYS_CODE <", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeLessThanOrEqualTo(String value) {
            addCriterion("SYS_CODE <=", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeLike(String value) {
            addCriterion("SYS_CODE like", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotLike(String value) {
            addCriterion("SYS_CODE not like", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeIn(List<String> values) {
            addCriterion("SYS_CODE in", values, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotIn(List<String> values) {
            addCriterion("SYS_CODE not in", values, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeBetween(String value1, String value2) {
            addCriterion("SYS_CODE between", value1, value2, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotBetween(String value1, String value2) {
            addCriterion("SYS_CODE not between", value1, value2, "sysCode");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
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