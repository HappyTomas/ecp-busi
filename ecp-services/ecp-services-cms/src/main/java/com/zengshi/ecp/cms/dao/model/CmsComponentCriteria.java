package com.zengshi.ecp.cms.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CmsComponentCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CmsComponentCriteria() {
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

        public Criteria andComponentClassIsNull() {
            addCriterion("COMPONENT_CLASS is null");
            return (Criteria) this;
        }

        public Criteria andComponentClassIsNotNull() {
            addCriterion("COMPONENT_CLASS is not null");
            return (Criteria) this;
        }

        public Criteria andComponentClassEqualTo(String value) {
            addCriterion("COMPONENT_CLASS =", value, "componentClass");
            return (Criteria) this;
        }

        public Criteria andComponentClassNotEqualTo(String value) {
            addCriterion("COMPONENT_CLASS <>", value, "componentClass");
            return (Criteria) this;
        }

        public Criteria andComponentClassGreaterThan(String value) {
            addCriterion("COMPONENT_CLASS >", value, "componentClass");
            return (Criteria) this;
        }

        public Criteria andComponentClassGreaterThanOrEqualTo(String value) {
            addCriterion("COMPONENT_CLASS >=", value, "componentClass");
            return (Criteria) this;
        }

        public Criteria andComponentClassLessThan(String value) {
            addCriterion("COMPONENT_CLASS <", value, "componentClass");
            return (Criteria) this;
        }

        public Criteria andComponentClassLessThanOrEqualTo(String value) {
            addCriterion("COMPONENT_CLASS <=", value, "componentClass");
            return (Criteria) this;
        }

        public Criteria andComponentClassLike(String value) {
            addCriterion("COMPONENT_CLASS like", value, "componentClass");
            return (Criteria) this;
        }

        public Criteria andComponentClassNotLike(String value) {
            addCriterion("COMPONENT_CLASS not like", value, "componentClass");
            return (Criteria) this;
        }

        public Criteria andComponentClassIn(List<String> values) {
            addCriterion("COMPONENT_CLASS in", values, "componentClass");
            return (Criteria) this;
        }

        public Criteria andComponentClassNotIn(List<String> values) {
            addCriterion("COMPONENT_CLASS not in", values, "componentClass");
            return (Criteria) this;
        }

        public Criteria andComponentClassBetween(String value1, String value2) {
            addCriterion("COMPONENT_CLASS between", value1, value2, "componentClass");
            return (Criteria) this;
        }

        public Criteria andComponentClassNotBetween(String value1, String value2) {
            addCriterion("COMPONENT_CLASS not between", value1, value2, "componentClass");
            return (Criteria) this;
        }

        public Criteria andComponentNameIsNull() {
            addCriterion("COMPONENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andComponentNameIsNotNull() {
            addCriterion("COMPONENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andComponentNameEqualTo(String value) {
            addCriterion("COMPONENT_NAME =", value, "componentName");
            return (Criteria) this;
        }

        public Criteria andComponentNameNotEqualTo(String value) {
            addCriterion("COMPONENT_NAME <>", value, "componentName");
            return (Criteria) this;
        }

        public Criteria andComponentNameGreaterThan(String value) {
            addCriterion("COMPONENT_NAME >", value, "componentName");
            return (Criteria) this;
        }

        public Criteria andComponentNameGreaterThanOrEqualTo(String value) {
            addCriterion("COMPONENT_NAME >=", value, "componentName");
            return (Criteria) this;
        }

        public Criteria andComponentNameLessThan(String value) {
            addCriterion("COMPONENT_NAME <", value, "componentName");
            return (Criteria) this;
        }

        public Criteria andComponentNameLessThanOrEqualTo(String value) {
            addCriterion("COMPONENT_NAME <=", value, "componentName");
            return (Criteria) this;
        }

        public Criteria andComponentNameLike(String value) {
            addCriterion("COMPONENT_NAME like", value, "componentName");
            return (Criteria) this;
        }

        public Criteria andComponentNameNotLike(String value) {
            addCriterion("COMPONENT_NAME not like", value, "componentName");
            return (Criteria) this;
        }

        public Criteria andComponentNameIn(List<String> values) {
            addCriterion("COMPONENT_NAME in", values, "componentName");
            return (Criteria) this;
        }

        public Criteria andComponentNameNotIn(List<String> values) {
            addCriterion("COMPONENT_NAME not in", values, "componentName");
            return (Criteria) this;
        }

        public Criteria andComponentNameBetween(String value1, String value2) {
            addCriterion("COMPONENT_NAME between", value1, value2, "componentName");
            return (Criteria) this;
        }

        public Criteria andComponentNameNotBetween(String value1, String value2) {
            addCriterion("COMPONENT_NAME not between", value1, value2, "componentName");
            return (Criteria) this;
        }

        public Criteria andComponentMethodIsNull() {
            addCriterion("COMPONENT_METHOD is null");
            return (Criteria) this;
        }

        public Criteria andComponentMethodIsNotNull() {
            addCriterion("COMPONENT_METHOD is not null");
            return (Criteria) this;
        }

        public Criteria andComponentMethodEqualTo(String value) {
            addCriterion("COMPONENT_METHOD =", value, "componentMethod");
            return (Criteria) this;
        }

        public Criteria andComponentMethodNotEqualTo(String value) {
            addCriterion("COMPONENT_METHOD <>", value, "componentMethod");
            return (Criteria) this;
        }

        public Criteria andComponentMethodGreaterThan(String value) {
            addCriterion("COMPONENT_METHOD >", value, "componentMethod");
            return (Criteria) this;
        }

        public Criteria andComponentMethodGreaterThanOrEqualTo(String value) {
            addCriterion("COMPONENT_METHOD >=", value, "componentMethod");
            return (Criteria) this;
        }

        public Criteria andComponentMethodLessThan(String value) {
            addCriterion("COMPONENT_METHOD <", value, "componentMethod");
            return (Criteria) this;
        }

        public Criteria andComponentMethodLessThanOrEqualTo(String value) {
            addCriterion("COMPONENT_METHOD <=", value, "componentMethod");
            return (Criteria) this;
        }

        public Criteria andComponentMethodLike(String value) {
            addCriterion("COMPONENT_METHOD like", value, "componentMethod");
            return (Criteria) this;
        }

        public Criteria andComponentMethodNotLike(String value) {
            addCriterion("COMPONENT_METHOD not like", value, "componentMethod");
            return (Criteria) this;
        }

        public Criteria andComponentMethodIn(List<String> values) {
            addCriterion("COMPONENT_METHOD in", values, "componentMethod");
            return (Criteria) this;
        }

        public Criteria andComponentMethodNotIn(List<String> values) {
            addCriterion("COMPONENT_METHOD not in", values, "componentMethod");
            return (Criteria) this;
        }

        public Criteria andComponentMethodBetween(String value1, String value2) {
            addCriterion("COMPONENT_METHOD between", value1, value2, "componentMethod");
            return (Criteria) this;
        }

        public Criteria andComponentMethodNotBetween(String value1, String value2) {
            addCriterion("COMPONENT_METHOD not between", value1, value2, "componentMethod");
            return (Criteria) this;
        }

        public Criteria andShowPicIsNull() {
            addCriterion("SHOW_PIC is null");
            return (Criteria) this;
        }

        public Criteria andShowPicIsNotNull() {
            addCriterion("SHOW_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andShowPicEqualTo(String value) {
            addCriterion("SHOW_PIC =", value, "showPic");
            return (Criteria) this;
        }

        public Criteria andShowPicNotEqualTo(String value) {
            addCriterion("SHOW_PIC <>", value, "showPic");
            return (Criteria) this;
        }

        public Criteria andShowPicGreaterThan(String value) {
            addCriterion("SHOW_PIC >", value, "showPic");
            return (Criteria) this;
        }

        public Criteria andShowPicGreaterThanOrEqualTo(String value) {
            addCriterion("SHOW_PIC >=", value, "showPic");
            return (Criteria) this;
        }

        public Criteria andShowPicLessThan(String value) {
            addCriterion("SHOW_PIC <", value, "showPic");
            return (Criteria) this;
        }

        public Criteria andShowPicLessThanOrEqualTo(String value) {
            addCriterion("SHOW_PIC <=", value, "showPic");
            return (Criteria) this;
        }

        public Criteria andShowPicLike(String value) {
            addCriterion("SHOW_PIC like", value, "showPic");
            return (Criteria) this;
        }

        public Criteria andShowPicNotLike(String value) {
            addCriterion("SHOW_PIC not like", value, "showPic");
            return (Criteria) this;
        }

        public Criteria andShowPicIn(List<String> values) {
            addCriterion("SHOW_PIC in", values, "showPic");
            return (Criteria) this;
        }

        public Criteria andShowPicNotIn(List<String> values) {
            addCriterion("SHOW_PIC not in", values, "showPic");
            return (Criteria) this;
        }

        public Criteria andShowPicBetween(String value1, String value2) {
            addCriterion("SHOW_PIC between", value1, value2, "showPic");
            return (Criteria) this;
        }

        public Criteria andShowPicNotBetween(String value1, String value2) {
            addCriterion("SHOW_PIC not between", value1, value2, "showPic");
            return (Criteria) this;
        }

        public Criteria andComponentUrlIsNull() {
            addCriterion("COMPONENT_URL is null");
            return (Criteria) this;
        }

        public Criteria andComponentUrlIsNotNull() {
            addCriterion("COMPONENT_URL is not null");
            return (Criteria) this;
        }

        public Criteria andComponentUrlEqualTo(String value) {
            addCriterion("COMPONENT_URL =", value, "componentUrl");
            return (Criteria) this;
        }

        public Criteria andComponentUrlNotEqualTo(String value) {
            addCriterion("COMPONENT_URL <>", value, "componentUrl");
            return (Criteria) this;
        }

        public Criteria andComponentUrlGreaterThan(String value) {
            addCriterion("COMPONENT_URL >", value, "componentUrl");
            return (Criteria) this;
        }

        public Criteria andComponentUrlGreaterThanOrEqualTo(String value) {
            addCriterion("COMPONENT_URL >=", value, "componentUrl");
            return (Criteria) this;
        }

        public Criteria andComponentUrlLessThan(String value) {
            addCriterion("COMPONENT_URL <", value, "componentUrl");
            return (Criteria) this;
        }

        public Criteria andComponentUrlLessThanOrEqualTo(String value) {
            addCriterion("COMPONENT_URL <=", value, "componentUrl");
            return (Criteria) this;
        }

        public Criteria andComponentUrlLike(String value) {
            addCriterion("COMPONENT_URL like", value, "componentUrl");
            return (Criteria) this;
        }

        public Criteria andComponentUrlNotLike(String value) {
            addCriterion("COMPONENT_URL not like", value, "componentUrl");
            return (Criteria) this;
        }

        public Criteria andComponentUrlIn(List<String> values) {
            addCriterion("COMPONENT_URL in", values, "componentUrl");
            return (Criteria) this;
        }

        public Criteria andComponentUrlNotIn(List<String> values) {
            addCriterion("COMPONENT_URL not in", values, "componentUrl");
            return (Criteria) this;
        }

        public Criteria andComponentUrlBetween(String value1, String value2) {
            addCriterion("COMPONENT_URL between", value1, value2, "componentUrl");
            return (Criteria) this;
        }

        public Criteria andComponentUrlNotBetween(String value1, String value2) {
            addCriterion("COMPONENT_URL not between", value1, value2, "componentUrl");
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

        public Criteria andComponentVmUrlIsNull() {
            addCriterion("COMPONENT_VM_URL is null");
            return (Criteria) this;
        }

        public Criteria andComponentVmUrlIsNotNull() {
            addCriterion("COMPONENT_VM_URL is not null");
            return (Criteria) this;
        }

        public Criteria andComponentVmUrlEqualTo(String value) {
            addCriterion("COMPONENT_VM_URL =", value, "componentVmUrl");
            return (Criteria) this;
        }

        public Criteria andComponentVmUrlNotEqualTo(String value) {
            addCriterion("COMPONENT_VM_URL <>", value, "componentVmUrl");
            return (Criteria) this;
        }

        public Criteria andComponentVmUrlGreaterThan(String value) {
            addCriterion("COMPONENT_VM_URL >", value, "componentVmUrl");
            return (Criteria) this;
        }

        public Criteria andComponentVmUrlGreaterThanOrEqualTo(String value) {
            addCriterion("COMPONENT_VM_URL >=", value, "componentVmUrl");
            return (Criteria) this;
        }

        public Criteria andComponentVmUrlLessThan(String value) {
            addCriterion("COMPONENT_VM_URL <", value, "componentVmUrl");
            return (Criteria) this;
        }

        public Criteria andComponentVmUrlLessThanOrEqualTo(String value) {
            addCriterion("COMPONENT_VM_URL <=", value, "componentVmUrl");
            return (Criteria) this;
        }

        public Criteria andComponentVmUrlLike(String value) {
            addCriterion("COMPONENT_VM_URL like", value, "componentVmUrl");
            return (Criteria) this;
        }

        public Criteria andComponentVmUrlNotLike(String value) {
            addCriterion("COMPONENT_VM_URL not like", value, "componentVmUrl");
            return (Criteria) this;
        }

        public Criteria andComponentVmUrlIn(List<String> values) {
            addCriterion("COMPONENT_VM_URL in", values, "componentVmUrl");
            return (Criteria) this;
        }

        public Criteria andComponentVmUrlNotIn(List<String> values) {
            addCriterion("COMPONENT_VM_URL not in", values, "componentVmUrl");
            return (Criteria) this;
        }

        public Criteria andComponentVmUrlBetween(String value1, String value2) {
            addCriterion("COMPONENT_VM_URL between", value1, value2, "componentVmUrl");
            return (Criteria) this;
        }

        public Criteria andComponentVmUrlNotBetween(String value1, String value2) {
            addCriterion("COMPONENT_VM_URL not between", value1, value2, "componentVmUrl");
            return (Criteria) this;
        }

        public Criteria andComponentEditUrlIsNull() {
            addCriterion("COMPONENT_EDIT_URL is null");
            return (Criteria) this;
        }

        public Criteria andComponentEditUrlIsNotNull() {
            addCriterion("COMPONENT_EDIT_URL is not null");
            return (Criteria) this;
        }

        public Criteria andComponentEditUrlEqualTo(String value) {
            addCriterion("COMPONENT_EDIT_URL =", value, "componentEditUrl");
            return (Criteria) this;
        }

        public Criteria andComponentEditUrlNotEqualTo(String value) {
            addCriterion("COMPONENT_EDIT_URL <>", value, "componentEditUrl");
            return (Criteria) this;
        }

        public Criteria andComponentEditUrlGreaterThan(String value) {
            addCriterion("COMPONENT_EDIT_URL >", value, "componentEditUrl");
            return (Criteria) this;
        }

        public Criteria andComponentEditUrlGreaterThanOrEqualTo(String value) {
            addCriterion("COMPONENT_EDIT_URL >=", value, "componentEditUrl");
            return (Criteria) this;
        }

        public Criteria andComponentEditUrlLessThan(String value) {
            addCriterion("COMPONENT_EDIT_URL <", value, "componentEditUrl");
            return (Criteria) this;
        }

        public Criteria andComponentEditUrlLessThanOrEqualTo(String value) {
            addCriterion("COMPONENT_EDIT_URL <=", value, "componentEditUrl");
            return (Criteria) this;
        }

        public Criteria andComponentEditUrlLike(String value) {
            addCriterion("COMPONENT_EDIT_URL like", value, "componentEditUrl");
            return (Criteria) this;
        }

        public Criteria andComponentEditUrlNotLike(String value) {
            addCriterion("COMPONENT_EDIT_URL not like", value, "componentEditUrl");
            return (Criteria) this;
        }

        public Criteria andComponentEditUrlIn(List<String> values) {
            addCriterion("COMPONENT_EDIT_URL in", values, "componentEditUrl");
            return (Criteria) this;
        }

        public Criteria andComponentEditUrlNotIn(List<String> values) {
            addCriterion("COMPONENT_EDIT_URL not in", values, "componentEditUrl");
            return (Criteria) this;
        }

        public Criteria andComponentEditUrlBetween(String value1, String value2) {
            addCriterion("COMPONENT_EDIT_URL between", value1, value2, "componentEditUrl");
            return (Criteria) this;
        }

        public Criteria andComponentEditUrlNotBetween(String value1, String value2) {
            addCriterion("COMPONENT_EDIT_URL not between", value1, value2, "componentEditUrl");
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
