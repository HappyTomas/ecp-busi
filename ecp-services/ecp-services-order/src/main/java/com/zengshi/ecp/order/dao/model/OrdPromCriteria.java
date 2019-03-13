package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrdPromCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OrdPromCriteria() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("ORDER_ID like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("ORDER_ID not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdIsNull() {
            addCriterion("ORDER_SUB_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdIsNotNull() {
            addCriterion("ORDER_SUB_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdEqualTo(String value) {
            addCriterion("ORDER_SUB_ID =", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdNotEqualTo(String value) {
            addCriterion("ORDER_SUB_ID <>", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdGreaterThan(String value) {
            addCriterion("ORDER_SUB_ID >", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_SUB_ID >=", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdLessThan(String value) {
            addCriterion("ORDER_SUB_ID <", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_SUB_ID <=", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdLike(String value) {
            addCriterion("ORDER_SUB_ID like", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdNotLike(String value) {
            addCriterion("ORDER_SUB_ID not like", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdIn(List<String> values) {
            addCriterion("ORDER_SUB_ID in", values, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdNotIn(List<String> values) {
            addCriterion("ORDER_SUB_ID not in", values, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdBetween(String value1, String value2) {
            addCriterion("ORDER_SUB_ID between", value1, value2, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_SUB_ID not between", value1, value2, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeIsNull() {
            addCriterion("PROM_TYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeIsNotNull() {
            addCriterion("PROM_TYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeEqualTo(String value) {
            addCriterion("PROM_TYPE_CODE =", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeNotEqualTo(String value) {
            addCriterion("PROM_TYPE_CODE <>", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeGreaterThan(String value) {
            addCriterion("PROM_TYPE_CODE >", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PROM_TYPE_CODE >=", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeLessThan(String value) {
            addCriterion("PROM_TYPE_CODE <", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("PROM_TYPE_CODE <=", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeLike(String value) {
            addCriterion("PROM_TYPE_CODE like", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeNotLike(String value) {
            addCriterion("PROM_TYPE_CODE not like", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeIn(List<String> values) {
            addCriterion("PROM_TYPE_CODE in", values, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeNotIn(List<String> values) {
            addCriterion("PROM_TYPE_CODE not in", values, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeBetween(String value1, String value2) {
            addCriterion("PROM_TYPE_CODE between", value1, value2, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeNotBetween(String value1, String value2) {
            addCriterion("PROM_TYPE_CODE not between", value1, value2, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameIsNull() {
            addCriterion("PROM_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameIsNotNull() {
            addCriterion("PROM_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameEqualTo(String value) {
            addCriterion("PROM_TYPE_NAME =", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameNotEqualTo(String value) {
            addCriterion("PROM_TYPE_NAME <>", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameGreaterThan(String value) {
            addCriterion("PROM_TYPE_NAME >", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROM_TYPE_NAME >=", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameLessThan(String value) {
            addCriterion("PROM_TYPE_NAME <", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameLessThanOrEqualTo(String value) {
            addCriterion("PROM_TYPE_NAME <=", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameLike(String value) {
            addCriterion("PROM_TYPE_NAME like", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameNotLike(String value) {
            addCriterion("PROM_TYPE_NAME not like", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameIn(List<String> values) {
            addCriterion("PROM_TYPE_NAME in", values, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameNotIn(List<String> values) {
            addCriterion("PROM_TYPE_NAME not in", values, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameBetween(String value1, String value2) {
            addCriterion("PROM_TYPE_NAME between", value1, value2, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameNotBetween(String value1, String value2) {
            addCriterion("PROM_TYPE_NAME not between", value1, value2, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromIdIsNull() {
            addCriterion("PROM_ID is null");
            return (Criteria) this;
        }

        public Criteria andPromIdIsNotNull() {
            addCriterion("PROM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPromIdEqualTo(Long value) {
            addCriterion("PROM_ID =", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotEqualTo(Long value) {
            addCriterion("PROM_ID <>", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdGreaterThan(Long value) {
            addCriterion("PROM_ID >", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROM_ID >=", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdLessThan(Long value) {
            addCriterion("PROM_ID <", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdLessThanOrEqualTo(Long value) {
            addCriterion("PROM_ID <=", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdIn(List<Long> values) {
            addCriterion("PROM_ID in", values, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotIn(List<Long> values) {
            addCriterion("PROM_ID not in", values, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdBetween(Long value1, Long value2) {
            addCriterion("PROM_ID between", value1, value2, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotBetween(Long value1, Long value2) {
            addCriterion("PROM_ID not between", value1, value2, "promId");
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

        public Criteria andRelaMainPromIdIsNull() {
            addCriterion("RELA_MAIN_PROM_ID is null");
            return (Criteria) this;
        }

        public Criteria andRelaMainPromIdIsNotNull() {
            addCriterion("RELA_MAIN_PROM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRelaMainPromIdEqualTo(Long value) {
            addCriterion("RELA_MAIN_PROM_ID =", value, "relaMainPromId");
            return (Criteria) this;
        }

        public Criteria andRelaMainPromIdNotEqualTo(Long value) {
            addCriterion("RELA_MAIN_PROM_ID <>", value, "relaMainPromId");
            return (Criteria) this;
        }

        public Criteria andRelaMainPromIdGreaterThan(Long value) {
            addCriterion("RELA_MAIN_PROM_ID >", value, "relaMainPromId");
            return (Criteria) this;
        }

        public Criteria andRelaMainPromIdGreaterThanOrEqualTo(Long value) {
            addCriterion("RELA_MAIN_PROM_ID >=", value, "relaMainPromId");
            return (Criteria) this;
        }

        public Criteria andRelaMainPromIdLessThan(Long value) {
            addCriterion("RELA_MAIN_PROM_ID <", value, "relaMainPromId");
            return (Criteria) this;
        }

        public Criteria andRelaMainPromIdLessThanOrEqualTo(Long value) {
            addCriterion("RELA_MAIN_PROM_ID <=", value, "relaMainPromId");
            return (Criteria) this;
        }

        public Criteria andRelaMainPromIdIn(List<Long> values) {
            addCriterion("RELA_MAIN_PROM_ID in", values, "relaMainPromId");
            return (Criteria) this;
        }

        public Criteria andRelaMainPromIdNotIn(List<Long> values) {
            addCriterion("RELA_MAIN_PROM_ID not in", values, "relaMainPromId");
            return (Criteria) this;
        }

        public Criteria andRelaMainPromIdBetween(Long value1, Long value2) {
            addCriterion("RELA_MAIN_PROM_ID between", value1, value2, "relaMainPromId");
            return (Criteria) this;
        }

        public Criteria andRelaMainPromIdNotBetween(Long value1, Long value2) {
            addCriterion("RELA_MAIN_PROM_ID not between", value1, value2, "relaMainPromId");
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