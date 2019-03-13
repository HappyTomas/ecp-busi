package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CustLevelInfoCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CustLevelInfoCriteria() {
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

        public Criteria andCustLevelCodeIsNull() {
            addCriterion("CUST_LEVEL_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCustLevelCodeIsNotNull() {
            addCriterion("CUST_LEVEL_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCustLevelCodeEqualTo(String value) {
            addCriterion("CUST_LEVEL_CODE =", value, "custLevelCode");
            return (Criteria) this;
        }

        public Criteria andCustLevelCodeNotEqualTo(String value) {
            addCriterion("CUST_LEVEL_CODE <>", value, "custLevelCode");
            return (Criteria) this;
        }

        public Criteria andCustLevelCodeGreaterThan(String value) {
            addCriterion("CUST_LEVEL_CODE >", value, "custLevelCode");
            return (Criteria) this;
        }

        public Criteria andCustLevelCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_LEVEL_CODE >=", value, "custLevelCode");
            return (Criteria) this;
        }

        public Criteria andCustLevelCodeLessThan(String value) {
            addCriterion("CUST_LEVEL_CODE <", value, "custLevelCode");
            return (Criteria) this;
        }

        public Criteria andCustLevelCodeLessThanOrEqualTo(String value) {
            addCriterion("CUST_LEVEL_CODE <=", value, "custLevelCode");
            return (Criteria) this;
        }

        public Criteria andCustLevelCodeLike(String value) {
            addCriterion("CUST_LEVEL_CODE like", value, "custLevelCode");
            return (Criteria) this;
        }

        public Criteria andCustLevelCodeNotLike(String value) {
            addCriterion("CUST_LEVEL_CODE not like", value, "custLevelCode");
            return (Criteria) this;
        }

        public Criteria andCustLevelCodeIn(List<String> values) {
            addCriterion("CUST_LEVEL_CODE in", values, "custLevelCode");
            return (Criteria) this;
        }

        public Criteria andCustLevelCodeNotIn(List<String> values) {
            addCriterion("CUST_LEVEL_CODE not in", values, "custLevelCode");
            return (Criteria) this;
        }

        public Criteria andCustLevelCodeBetween(String value1, String value2) {
            addCriterion("CUST_LEVEL_CODE between", value1, value2, "custLevelCode");
            return (Criteria) this;
        }

        public Criteria andCustLevelCodeNotBetween(String value1, String value2) {
            addCriterion("CUST_LEVEL_CODE not between", value1, value2, "custLevelCode");
            return (Criteria) this;
        }

        public Criteria andCustLevelNameIsNull() {
            addCriterion("CUST_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCustLevelNameIsNotNull() {
            addCriterion("CUST_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustLevelNameEqualTo(String value) {
            addCriterion("CUST_LEVEL_NAME =", value, "custLevelName");
            return (Criteria) this;
        }

        public Criteria andCustLevelNameNotEqualTo(String value) {
            addCriterion("CUST_LEVEL_NAME <>", value, "custLevelName");
            return (Criteria) this;
        }

        public Criteria andCustLevelNameGreaterThan(String value) {
            addCriterion("CUST_LEVEL_NAME >", value, "custLevelName");
            return (Criteria) this;
        }

        public Criteria andCustLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_LEVEL_NAME >=", value, "custLevelName");
            return (Criteria) this;
        }

        public Criteria andCustLevelNameLessThan(String value) {
            addCriterion("CUST_LEVEL_NAME <", value, "custLevelName");
            return (Criteria) this;
        }

        public Criteria andCustLevelNameLessThanOrEqualTo(String value) {
            addCriterion("CUST_LEVEL_NAME <=", value, "custLevelName");
            return (Criteria) this;
        }

        public Criteria andCustLevelNameLike(String value) {
            addCriterion("CUST_LEVEL_NAME like", value, "custLevelName");
            return (Criteria) this;
        }

        public Criteria andCustLevelNameNotLike(String value) {
            addCriterion("CUST_LEVEL_NAME not like", value, "custLevelName");
            return (Criteria) this;
        }

        public Criteria andCustLevelNameIn(List<String> values) {
            addCriterion("CUST_LEVEL_NAME in", values, "custLevelName");
            return (Criteria) this;
        }

        public Criteria andCustLevelNameNotIn(List<String> values) {
            addCriterion("CUST_LEVEL_NAME not in", values, "custLevelName");
            return (Criteria) this;
        }

        public Criteria andCustLevelNameBetween(String value1, String value2) {
            addCriterion("CUST_LEVEL_NAME between", value1, value2, "custLevelName");
            return (Criteria) this;
        }

        public Criteria andCustLevelNameNotBetween(String value1, String value2) {
            addCriterion("CUST_LEVEL_NAME not between", value1, value2, "custLevelName");
            return (Criteria) this;
        }

        public Criteria andCustLevelSnIsNull() {
            addCriterion("CUST_LEVEL_SN is null");
            return (Criteria) this;
        }

        public Criteria andCustLevelSnIsNotNull() {
            addCriterion("CUST_LEVEL_SN is not null");
            return (Criteria) this;
        }

        public Criteria andCustLevelSnEqualTo(BigDecimal value) {
            addCriterion("CUST_LEVEL_SN =", value, "custLevelSn");
            return (Criteria) this;
        }

        public Criteria andCustLevelSnNotEqualTo(BigDecimal value) {
            addCriterion("CUST_LEVEL_SN <>", value, "custLevelSn");
            return (Criteria) this;
        }

        public Criteria andCustLevelSnGreaterThan(BigDecimal value) {
            addCriterion("CUST_LEVEL_SN >", value, "custLevelSn");
            return (Criteria) this;
        }

        public Criteria andCustLevelSnGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CUST_LEVEL_SN >=", value, "custLevelSn");
            return (Criteria) this;
        }

        public Criteria andCustLevelSnLessThan(BigDecimal value) {
            addCriterion("CUST_LEVEL_SN <", value, "custLevelSn");
            return (Criteria) this;
        }

        public Criteria andCustLevelSnLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CUST_LEVEL_SN <=", value, "custLevelSn");
            return (Criteria) this;
        }

        public Criteria andCustLevelSnIn(List<BigDecimal> values) {
            addCriterion("CUST_LEVEL_SN in", values, "custLevelSn");
            return (Criteria) this;
        }

        public Criteria andCustLevelSnNotIn(List<BigDecimal> values) {
            addCriterion("CUST_LEVEL_SN not in", values, "custLevelSn");
            return (Criteria) this;
        }

        public Criteria andCustLevelSnBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CUST_LEVEL_SN between", value1, value2, "custLevelSn");
            return (Criteria) this;
        }

        public Criteria andCustLevelSnNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CUST_LEVEL_SN not between", value1, value2, "custLevelSn");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("GROUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("GROUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Long value) {
            addCriterion("GROUP_ID =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Long value) {
            addCriterion("GROUP_ID <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Long value) {
            addCriterion("GROUP_ID >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("GROUP_ID >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Long value) {
            addCriterion("GROUP_ID <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Long value) {
            addCriterion("GROUP_ID <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Long> values) {
            addCriterion("GROUP_ID in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Long> values) {
            addCriterion("GROUP_ID not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Long value1, Long value2) {
            addCriterion("GROUP_ID between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Long value1, Long value2) {
            addCriterion("GROUP_ID not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andCustLevelPicIsNull() {
            addCriterion("CUST_LEVEL_PIC is null");
            return (Criteria) this;
        }

        public Criteria andCustLevelPicIsNotNull() {
            addCriterion("CUST_LEVEL_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andCustLevelPicEqualTo(String value) {
            addCriterion("CUST_LEVEL_PIC =", value, "custLevelPic");
            return (Criteria) this;
        }

        public Criteria andCustLevelPicNotEqualTo(String value) {
            addCriterion("CUST_LEVEL_PIC <>", value, "custLevelPic");
            return (Criteria) this;
        }

        public Criteria andCustLevelPicGreaterThan(String value) {
            addCriterion("CUST_LEVEL_PIC >", value, "custLevelPic");
            return (Criteria) this;
        }

        public Criteria andCustLevelPicGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_LEVEL_PIC >=", value, "custLevelPic");
            return (Criteria) this;
        }

        public Criteria andCustLevelPicLessThan(String value) {
            addCriterion("CUST_LEVEL_PIC <", value, "custLevelPic");
            return (Criteria) this;
        }

        public Criteria andCustLevelPicLessThanOrEqualTo(String value) {
            addCriterion("CUST_LEVEL_PIC <=", value, "custLevelPic");
            return (Criteria) this;
        }

        public Criteria andCustLevelPicLike(String value) {
            addCriterion("CUST_LEVEL_PIC like", value, "custLevelPic");
            return (Criteria) this;
        }

        public Criteria andCustLevelPicNotLike(String value) {
            addCriterion("CUST_LEVEL_PIC not like", value, "custLevelPic");
            return (Criteria) this;
        }

        public Criteria andCustLevelPicIn(List<String> values) {
            addCriterion("CUST_LEVEL_PIC in", values, "custLevelPic");
            return (Criteria) this;
        }

        public Criteria andCustLevelPicNotIn(List<String> values) {
            addCriterion("CUST_LEVEL_PIC not in", values, "custLevelPic");
            return (Criteria) this;
        }

        public Criteria andCustLevelPicBetween(String value1, String value2) {
            addCriterion("CUST_LEVEL_PIC between", value1, value2, "custLevelPic");
            return (Criteria) this;
        }

        public Criteria andCustLevelPicNotBetween(String value1, String value2) {
            addCriterion("CUST_LEVEL_PIC not between", value1, value2, "custLevelPic");
            return (Criteria) this;
        }

        public Criteria andCustLevelRemarkIsNull() {
            addCriterion("CUST_LEVEL_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andCustLevelRemarkIsNotNull() {
            addCriterion("CUST_LEVEL_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andCustLevelRemarkEqualTo(String value) {
            addCriterion("CUST_LEVEL_REMARK =", value, "custLevelRemark");
            return (Criteria) this;
        }

        public Criteria andCustLevelRemarkNotEqualTo(String value) {
            addCriterion("CUST_LEVEL_REMARK <>", value, "custLevelRemark");
            return (Criteria) this;
        }

        public Criteria andCustLevelRemarkGreaterThan(String value) {
            addCriterion("CUST_LEVEL_REMARK >", value, "custLevelRemark");
            return (Criteria) this;
        }

        public Criteria andCustLevelRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_LEVEL_REMARK >=", value, "custLevelRemark");
            return (Criteria) this;
        }

        public Criteria andCustLevelRemarkLessThan(String value) {
            addCriterion("CUST_LEVEL_REMARK <", value, "custLevelRemark");
            return (Criteria) this;
        }

        public Criteria andCustLevelRemarkLessThanOrEqualTo(String value) {
            addCriterion("CUST_LEVEL_REMARK <=", value, "custLevelRemark");
            return (Criteria) this;
        }

        public Criteria andCustLevelRemarkLike(String value) {
            addCriterion("CUST_LEVEL_REMARK like", value, "custLevelRemark");
            return (Criteria) this;
        }

        public Criteria andCustLevelRemarkNotLike(String value) {
            addCriterion("CUST_LEVEL_REMARK not like", value, "custLevelRemark");
            return (Criteria) this;
        }

        public Criteria andCustLevelRemarkIn(List<String> values) {
            addCriterion("CUST_LEVEL_REMARK in", values, "custLevelRemark");
            return (Criteria) this;
        }

        public Criteria andCustLevelRemarkNotIn(List<String> values) {
            addCriterion("CUST_LEVEL_REMARK not in", values, "custLevelRemark");
            return (Criteria) this;
        }

        public Criteria andCustLevelRemarkBetween(String value1, String value2) {
            addCriterion("CUST_LEVEL_REMARK between", value1, value2, "custLevelRemark");
            return (Criteria) this;
        }

        public Criteria andCustLevelRemarkNotBetween(String value1, String value2) {
            addCriterion("CUST_LEVEL_REMARK not between", value1, value2, "custLevelRemark");
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

        public Criteria andCustCardNumIsNull() {
            addCriterion("CUST_CARD_NUM is null");
            return (Criteria) this;
        }

        public Criteria andCustCardNumIsNotNull() {
            addCriterion("CUST_CARD_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andCustCardNumEqualTo(String value) {
            addCriterion("CUST_CARD_NUM =", value, "custCardNum");
            return (Criteria) this;
        }

        public Criteria andCustCardNumNotEqualTo(String value) {
            addCriterion("CUST_CARD_NUM <>", value, "custCardNum");
            return (Criteria) this;
        }

        public Criteria andCustCardNumGreaterThan(String value) {
            addCriterion("CUST_CARD_NUM >", value, "custCardNum");
            return (Criteria) this;
        }

        public Criteria andCustCardNumGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_CARD_NUM >=", value, "custCardNum");
            return (Criteria) this;
        }

        public Criteria andCustCardNumLessThan(String value) {
            addCriterion("CUST_CARD_NUM <", value, "custCardNum");
            return (Criteria) this;
        }

        public Criteria andCustCardNumLessThanOrEqualTo(String value) {
            addCriterion("CUST_CARD_NUM <=", value, "custCardNum");
            return (Criteria) this;
        }

        public Criteria andCustCardNumLike(String value) {
            addCriterion("CUST_CARD_NUM like", value, "custCardNum");
            return (Criteria) this;
        }

        public Criteria andCustCardNumNotLike(String value) {
            addCriterion("CUST_CARD_NUM not like", value, "custCardNum");
            return (Criteria) this;
        }

        public Criteria andCustCardNumIn(List<String> values) {
            addCriterion("CUST_CARD_NUM in", values, "custCardNum");
            return (Criteria) this;
        }

        public Criteria andCustCardNumNotIn(List<String> values) {
            addCriterion("CUST_CARD_NUM not in", values, "custCardNum");
            return (Criteria) this;
        }

        public Criteria andCustCardNumBetween(String value1, String value2) {
            addCriterion("CUST_CARD_NUM between", value1, value2, "custCardNum");
            return (Criteria) this;
        }

        public Criteria andCustCardNumNotBetween(String value1, String value2) {
            addCriterion("CUST_CARD_NUM not between", value1, value2, "custCardNum");
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