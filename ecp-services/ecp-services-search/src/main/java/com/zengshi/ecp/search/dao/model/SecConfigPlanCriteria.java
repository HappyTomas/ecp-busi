package com.zengshi.ecp.search.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SecConfigPlanCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public SecConfigPlanCriteria() {
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

        public Criteria andConfigIdIsNull() {
            addCriterion("CONFIG_ID is null");
            return (Criteria) this;
        }

        public Criteria andConfigIdIsNotNull() {
            addCriterion("CONFIG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andConfigIdEqualTo(Long value) {
            addCriterion("CONFIG_ID =", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdNotEqualTo(Long value) {
            addCriterion("CONFIG_ID <>", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdGreaterThan(Long value) {
            addCriterion("CONFIG_ID >", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CONFIG_ID >=", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdLessThan(Long value) {
            addCriterion("CONFIG_ID <", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdLessThanOrEqualTo(Long value) {
            addCriterion("CONFIG_ID <=", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdIn(List<Long> values) {
            addCriterion("CONFIG_ID in", values, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdNotIn(List<Long> values) {
            addCriterion("CONFIG_ID not in", values, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdBetween(Long value1, Long value2) {
            addCriterion("CONFIG_ID between", value1, value2, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdNotBetween(Long value1, Long value2) {
            addCriterion("CONFIG_ID not between", value1, value2, "configId");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNull() {
            addCriterion("PLAN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNotNull() {
            addCriterion("PLAN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPlanNameEqualTo(String value) {
            addCriterion("PLAN_NAME =", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotEqualTo(String value) {
            addCriterion("PLAN_NAME <>", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThan(String value) {
            addCriterion("PLAN_NAME >", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_NAME >=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThan(String value) {
            addCriterion("PLAN_NAME <", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThanOrEqualTo(String value) {
            addCriterion("PLAN_NAME <=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLike(String value) {
            addCriterion("PLAN_NAME like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotLike(String value) {
            addCriterion("PLAN_NAME not like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameIn(List<String> values) {
            addCriterion("PLAN_NAME in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotIn(List<String> values) {
            addCriterion("PLAN_NAME not in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameBetween(String value1, String value2) {
            addCriterion("PLAN_NAME between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotBetween(String value1, String value2) {
            addCriterion("PLAN_NAME not between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanCommentIsNull() {
            addCriterion("PLAN_COMMENT is null");
            return (Criteria) this;
        }

        public Criteria andPlanCommentIsNotNull() {
            addCriterion("PLAN_COMMENT is not null");
            return (Criteria) this;
        }

        public Criteria andPlanCommentEqualTo(String value) {
            addCriterion("PLAN_COMMENT =", value, "planComment");
            return (Criteria) this;
        }

        public Criteria andPlanCommentNotEqualTo(String value) {
            addCriterion("PLAN_COMMENT <>", value, "planComment");
            return (Criteria) this;
        }

        public Criteria andPlanCommentGreaterThan(String value) {
            addCriterion("PLAN_COMMENT >", value, "planComment");
            return (Criteria) this;
        }

        public Criteria andPlanCommentGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_COMMENT >=", value, "planComment");
            return (Criteria) this;
        }

        public Criteria andPlanCommentLessThan(String value) {
            addCriterion("PLAN_COMMENT <", value, "planComment");
            return (Criteria) this;
        }

        public Criteria andPlanCommentLessThanOrEqualTo(String value) {
            addCriterion("PLAN_COMMENT <=", value, "planComment");
            return (Criteria) this;
        }

        public Criteria andPlanCommentLike(String value) {
            addCriterion("PLAN_COMMENT like", value, "planComment");
            return (Criteria) this;
        }

        public Criteria andPlanCommentNotLike(String value) {
            addCriterion("PLAN_COMMENT not like", value, "planComment");
            return (Criteria) this;
        }

        public Criteria andPlanCommentIn(List<String> values) {
            addCriterion("PLAN_COMMENT in", values, "planComment");
            return (Criteria) this;
        }

        public Criteria andPlanCommentNotIn(List<String> values) {
            addCriterion("PLAN_COMMENT not in", values, "planComment");
            return (Criteria) this;
        }

        public Criteria andPlanCommentBetween(String value1, String value2) {
            addCriterion("PLAN_COMMENT between", value1, value2, "planComment");
            return (Criteria) this;
        }

        public Criteria andPlanCommentNotBetween(String value1, String value2) {
            addCriterion("PLAN_COMMENT not between", value1, value2, "planComment");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIsNull() {
            addCriterion("PLAN_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIsNotNull() {
            addCriterion("PLAN_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeEqualTo(String value) {
            addCriterion("PLAN_TYPE =", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotEqualTo(String value) {
            addCriterion("PLAN_TYPE <>", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeGreaterThan(String value) {
            addCriterion("PLAN_TYPE >", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_TYPE >=", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeLessThan(String value) {
            addCriterion("PLAN_TYPE <", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeLessThanOrEqualTo(String value) {
            addCriterion("PLAN_TYPE <=", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeLike(String value) {
            addCriterion("PLAN_TYPE like", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotLike(String value) {
            addCriterion("PLAN_TYPE not like", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIn(List<String> values) {
            addCriterion("PLAN_TYPE in", values, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotIn(List<String> values) {
            addCriterion("PLAN_TYPE not in", values, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeBetween(String value1, String value2) {
            addCriterion("PLAN_TYPE between", value1, value2, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotBetween(String value1, String value2) {
            addCriterion("PLAN_TYPE not between", value1, value2, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanIfCleanIsNull() {
            addCriterion("PLAN_IF_CLEAN is null");
            return (Criteria) this;
        }

        public Criteria andPlanIfCleanIsNotNull() {
            addCriterion("PLAN_IF_CLEAN is not null");
            return (Criteria) this;
        }

        public Criteria andPlanIfCleanEqualTo(String value) {
            addCriterion("PLAN_IF_CLEAN =", value, "planIfClean");
            return (Criteria) this;
        }

        public Criteria andPlanIfCleanNotEqualTo(String value) {
            addCriterion("PLAN_IF_CLEAN <>", value, "planIfClean");
            return (Criteria) this;
        }

        public Criteria andPlanIfCleanGreaterThan(String value) {
            addCriterion("PLAN_IF_CLEAN >", value, "planIfClean");
            return (Criteria) this;
        }

        public Criteria andPlanIfCleanGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_IF_CLEAN >=", value, "planIfClean");
            return (Criteria) this;
        }

        public Criteria andPlanIfCleanLessThan(String value) {
            addCriterion("PLAN_IF_CLEAN <", value, "planIfClean");
            return (Criteria) this;
        }

        public Criteria andPlanIfCleanLessThanOrEqualTo(String value) {
            addCriterion("PLAN_IF_CLEAN <=", value, "planIfClean");
            return (Criteria) this;
        }

        public Criteria andPlanIfCleanLike(String value) {
            addCriterion("PLAN_IF_CLEAN like", value, "planIfClean");
            return (Criteria) this;
        }

        public Criteria andPlanIfCleanNotLike(String value) {
            addCriterion("PLAN_IF_CLEAN not like", value, "planIfClean");
            return (Criteria) this;
        }

        public Criteria andPlanIfCleanIn(List<String> values) {
            addCriterion("PLAN_IF_CLEAN in", values, "planIfClean");
            return (Criteria) this;
        }

        public Criteria andPlanIfCleanNotIn(List<String> values) {
            addCriterion("PLAN_IF_CLEAN not in", values, "planIfClean");
            return (Criteria) this;
        }

        public Criteria andPlanIfCleanBetween(String value1, String value2) {
            addCriterion("PLAN_IF_CLEAN between", value1, value2, "planIfClean");
            return (Criteria) this;
        }

        public Criteria andPlanIfCleanNotBetween(String value1, String value2) {
            addCriterion("PLAN_IF_CLEAN not between", value1, value2, "planIfClean");
            return (Criteria) this;
        }

        public Criteria andPlanDispatcherruleIsNull() {
            addCriterion("PLAN_DISPATCHERRULE is null");
            return (Criteria) this;
        }

        public Criteria andPlanDispatcherruleIsNotNull() {
            addCriterion("PLAN_DISPATCHERRULE is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDispatcherruleEqualTo(String value) {
            addCriterion("PLAN_DISPATCHERRULE =", value, "planDispatcherrule");
            return (Criteria) this;
        }

        public Criteria andPlanDispatcherruleNotEqualTo(String value) {
            addCriterion("PLAN_DISPATCHERRULE <>", value, "planDispatcherrule");
            return (Criteria) this;
        }

        public Criteria andPlanDispatcherruleGreaterThan(String value) {
            addCriterion("PLAN_DISPATCHERRULE >", value, "planDispatcherrule");
            return (Criteria) this;
        }

        public Criteria andPlanDispatcherruleGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_DISPATCHERRULE >=", value, "planDispatcherrule");
            return (Criteria) this;
        }

        public Criteria andPlanDispatcherruleLessThan(String value) {
            addCriterion("PLAN_DISPATCHERRULE <", value, "planDispatcherrule");
            return (Criteria) this;
        }

        public Criteria andPlanDispatcherruleLessThanOrEqualTo(String value) {
            addCriterion("PLAN_DISPATCHERRULE <=", value, "planDispatcherrule");
            return (Criteria) this;
        }

        public Criteria andPlanDispatcherruleLike(String value) {
            addCriterion("PLAN_DISPATCHERRULE like", value, "planDispatcherrule");
            return (Criteria) this;
        }

        public Criteria andPlanDispatcherruleNotLike(String value) {
            addCriterion("PLAN_DISPATCHERRULE not like", value, "planDispatcherrule");
            return (Criteria) this;
        }

        public Criteria andPlanDispatcherruleIn(List<String> values) {
            addCriterion("PLAN_DISPATCHERRULE in", values, "planDispatcherrule");
            return (Criteria) this;
        }

        public Criteria andPlanDispatcherruleNotIn(List<String> values) {
            addCriterion("PLAN_DISPATCHERRULE not in", values, "planDispatcherrule");
            return (Criteria) this;
        }

        public Criteria andPlanDispatcherruleBetween(String value1, String value2) {
            addCriterion("PLAN_DISPATCHERRULE between", value1, value2, "planDispatcherrule");
            return (Criteria) this;
        }

        public Criteria andPlanDispatcherruleNotBetween(String value1, String value2) {
            addCriterion("PLAN_DISPATCHERRULE not between", value1, value2, "planDispatcherrule");
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