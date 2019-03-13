package com.zengshi.ecp.cms.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CmsModularComponentCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CmsModularComponentCriteria() {
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

        public Criteria andModularIdIsNull() {
            addCriterion("MODULAR_ID is null");
            return (Criteria) this;
        }

        public Criteria andModularIdIsNotNull() {
            addCriterion("MODULAR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andModularIdEqualTo(Long value) {
            addCriterion("MODULAR_ID =", value, "modularId");
            return (Criteria) this;
        }

        public Criteria andModularIdNotEqualTo(Long value) {
            addCriterion("MODULAR_ID <>", value, "modularId");
            return (Criteria) this;
        }

        public Criteria andModularIdGreaterThan(Long value) {
            addCriterion("MODULAR_ID >", value, "modularId");
            return (Criteria) this;
        }

        public Criteria andModularIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MODULAR_ID >=", value, "modularId");
            return (Criteria) this;
        }

        public Criteria andModularIdLessThan(Long value) {
            addCriterion("MODULAR_ID <", value, "modularId");
            return (Criteria) this;
        }

        public Criteria andModularIdLessThanOrEqualTo(Long value) {
            addCriterion("MODULAR_ID <=", value, "modularId");
            return (Criteria) this;
        }

        public Criteria andModularIdIn(List<Long> values) {
            addCriterion("MODULAR_ID in", values, "modularId");
            return (Criteria) this;
        }

        public Criteria andModularIdNotIn(List<Long> values) {
            addCriterion("MODULAR_ID not in", values, "modularId");
            return (Criteria) this;
        }

        public Criteria andModularIdBetween(Long value1, Long value2) {
            addCriterion("MODULAR_ID between", value1, value2, "modularId");
            return (Criteria) this;
        }

        public Criteria andModularIdNotBetween(Long value1, Long value2) {
            addCriterion("MODULAR_ID not between", value1, value2, "modularId");
            return (Criteria) this;
        }

        public Criteria andApplyPageTypeIsNull() {
            addCriterion("APPLY_PAGE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andApplyPageTypeIsNotNull() {
            addCriterion("APPLY_PAGE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andApplyPageTypeEqualTo(String value) {
            addCriterion("APPLY_PAGE_TYPE =", value, "applyPageType");
            return (Criteria) this;
        }

        public Criteria andApplyPageTypeNotEqualTo(String value) {
            addCriterion("APPLY_PAGE_TYPE <>", value, "applyPageType");
            return (Criteria) this;
        }

        public Criteria andApplyPageTypeGreaterThan(String value) {
            addCriterion("APPLY_PAGE_TYPE >", value, "applyPageType");
            return (Criteria) this;
        }

        public Criteria andApplyPageTypeGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_PAGE_TYPE >=", value, "applyPageType");
            return (Criteria) this;
        }

        public Criteria andApplyPageTypeLessThan(String value) {
            addCriterion("APPLY_PAGE_TYPE <", value, "applyPageType");
            return (Criteria) this;
        }

        public Criteria andApplyPageTypeLessThanOrEqualTo(String value) {
            addCriterion("APPLY_PAGE_TYPE <=", value, "applyPageType");
            return (Criteria) this;
        }

        public Criteria andApplyPageTypeLike(String value) {
            addCriterion("APPLY_PAGE_TYPE like", value, "applyPageType");
            return (Criteria) this;
        }

        public Criteria andApplyPageTypeNotLike(String value) {
            addCriterion("APPLY_PAGE_TYPE not like", value, "applyPageType");
            return (Criteria) this;
        }

        public Criteria andApplyPageTypeIn(List<String> values) {
            addCriterion("APPLY_PAGE_TYPE in", values, "applyPageType");
            return (Criteria) this;
        }

        public Criteria andApplyPageTypeNotIn(List<String> values) {
            addCriterion("APPLY_PAGE_TYPE not in", values, "applyPageType");
            return (Criteria) this;
        }

        public Criteria andApplyPageTypeBetween(String value1, String value2) {
            addCriterion("APPLY_PAGE_TYPE between", value1, value2, "applyPageType");
            return (Criteria) this;
        }

        public Criteria andApplyPageTypeNotBetween(String value1, String value2) {
            addCriterion("APPLY_PAGE_TYPE not between", value1, value2, "applyPageType");
            return (Criteria) this;
        }

        public Criteria andApplyItemSizeIsNull() {
            addCriterion("APPLY_ITEM_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andApplyItemSizeIsNotNull() {
            addCriterion("APPLY_ITEM_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andApplyItemSizeEqualTo(String value) {
            addCriterion("APPLY_ITEM_SIZE =", value, "applyItemSize");
            return (Criteria) this;
        }

        public Criteria andApplyItemSizeNotEqualTo(String value) {
            addCriterion("APPLY_ITEM_SIZE <>", value, "applyItemSize");
            return (Criteria) this;
        }

        public Criteria andApplyItemSizeGreaterThan(String value) {
            addCriterion("APPLY_ITEM_SIZE >", value, "applyItemSize");
            return (Criteria) this;
        }

        public Criteria andApplyItemSizeGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_ITEM_SIZE >=", value, "applyItemSize");
            return (Criteria) this;
        }

        public Criteria andApplyItemSizeLessThan(String value) {
            addCriterion("APPLY_ITEM_SIZE <", value, "applyItemSize");
            return (Criteria) this;
        }

        public Criteria andApplyItemSizeLessThanOrEqualTo(String value) {
            addCriterion("APPLY_ITEM_SIZE <=", value, "applyItemSize");
            return (Criteria) this;
        }

        public Criteria andApplyItemSizeLike(String value) {
            addCriterion("APPLY_ITEM_SIZE like", value, "applyItemSize");
            return (Criteria) this;
        }

        public Criteria andApplyItemSizeNotLike(String value) {
            addCriterion("APPLY_ITEM_SIZE not like", value, "applyItemSize");
            return (Criteria) this;
        }

        public Criteria andApplyItemSizeIn(List<String> values) {
            addCriterion("APPLY_ITEM_SIZE in", values, "applyItemSize");
            return (Criteria) this;
        }

        public Criteria andApplyItemSizeNotIn(List<String> values) {
            addCriterion("APPLY_ITEM_SIZE not in", values, "applyItemSize");
            return (Criteria) this;
        }

        public Criteria andApplyItemSizeBetween(String value1, String value2) {
            addCriterion("APPLY_ITEM_SIZE between", value1, value2, "applyItemSize");
            return (Criteria) this;
        }

        public Criteria andApplyItemSizeNotBetween(String value1, String value2) {
            addCriterion("APPLY_ITEM_SIZE not between", value1, value2, "applyItemSize");
            return (Criteria) this;
        }

        public Criteria andModularClassIsNull() {
            addCriterion("MODULAR_CLASS is null");
            return (Criteria) this;
        }

        public Criteria andModularClassIsNotNull() {
            addCriterion("MODULAR_CLASS is not null");
            return (Criteria) this;
        }

        public Criteria andModularClassEqualTo(String value) {
            addCriterion("MODULAR_CLASS =", value, "modularClass");
            return (Criteria) this;
        }

        public Criteria andModularClassNotEqualTo(String value) {
            addCriterion("MODULAR_CLASS <>", value, "modularClass");
            return (Criteria) this;
        }

        public Criteria andModularClassGreaterThan(String value) {
            addCriterion("MODULAR_CLASS >", value, "modularClass");
            return (Criteria) this;
        }

        public Criteria andModularClassGreaterThanOrEqualTo(String value) {
            addCriterion("MODULAR_CLASS >=", value, "modularClass");
            return (Criteria) this;
        }

        public Criteria andModularClassLessThan(String value) {
            addCriterion("MODULAR_CLASS <", value, "modularClass");
            return (Criteria) this;
        }

        public Criteria andModularClassLessThanOrEqualTo(String value) {
            addCriterion("MODULAR_CLASS <=", value, "modularClass");
            return (Criteria) this;
        }

        public Criteria andModularClassLike(String value) {
            addCriterion("MODULAR_CLASS like", value, "modularClass");
            return (Criteria) this;
        }

        public Criteria andModularClassNotLike(String value) {
            addCriterion("MODULAR_CLASS not like", value, "modularClass");
            return (Criteria) this;
        }

        public Criteria andModularClassIn(List<String> values) {
            addCriterion("MODULAR_CLASS in", values, "modularClass");
            return (Criteria) this;
        }

        public Criteria andModularClassNotIn(List<String> values) {
            addCriterion("MODULAR_CLASS not in", values, "modularClass");
            return (Criteria) this;
        }

        public Criteria andModularClassBetween(String value1, String value2) {
            addCriterion("MODULAR_CLASS between", value1, value2, "modularClass");
            return (Criteria) this;
        }

        public Criteria andModularClassNotBetween(String value1, String value2) {
            addCriterion("MODULAR_CLASS not between", value1, value2, "modularClass");
            return (Criteria) this;
        }

        public Criteria andComponentIdIsNull() {
            addCriterion("COMPONENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andComponentIdIsNotNull() {
            addCriterion("COMPONENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andComponentIdEqualTo(Long value) {
            addCriterion("COMPONENT_ID =", value, "componentId");
            return (Criteria) this;
        }

        public Criteria andComponentIdNotEqualTo(Long value) {
            addCriterion("COMPONENT_ID <>", value, "componentId");
            return (Criteria) this;
        }

        public Criteria andComponentIdGreaterThan(Long value) {
            addCriterion("COMPONENT_ID >", value, "componentId");
            return (Criteria) this;
        }

        public Criteria andComponentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("COMPONENT_ID >=", value, "componentId");
            return (Criteria) this;
        }

        public Criteria andComponentIdLessThan(Long value) {
            addCriterion("COMPONENT_ID <", value, "componentId");
            return (Criteria) this;
        }

        public Criteria andComponentIdLessThanOrEqualTo(Long value) {
            addCriterion("COMPONENT_ID <=", value, "componentId");
            return (Criteria) this;
        }

        public Criteria andComponentIdIn(List<Long> values) {
            addCriterion("COMPONENT_ID in", values, "componentId");
            return (Criteria) this;
        }

        public Criteria andComponentIdNotIn(List<Long> values) {
            addCriterion("COMPONENT_ID not in", values, "componentId");
            return (Criteria) this;
        }

        public Criteria andComponentIdBetween(Long value1, Long value2) {
            addCriterion("COMPONENT_ID between", value1, value2, "componentId");
            return (Criteria) this;
        }

        public Criteria andComponentIdNotBetween(Long value1, Long value2) {
            addCriterion("COMPONENT_ID not between", value1, value2, "componentId");
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
