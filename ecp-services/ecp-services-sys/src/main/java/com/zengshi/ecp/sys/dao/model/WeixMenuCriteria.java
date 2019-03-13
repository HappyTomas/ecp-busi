package com.zengshi.ecp.sys.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class WeixMenuCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public WeixMenuCriteria() {
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

        public Criteria andButtonTitleIsNull() {
            addCriterion("BUTTON_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andButtonTitleIsNotNull() {
            addCriterion("BUTTON_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andButtonTitleEqualTo(String value) {
            addCriterion("BUTTON_TITLE =", value, "buttonTitle");
            return (Criteria) this;
        }

        public Criteria andButtonTitleNotEqualTo(String value) {
            addCriterion("BUTTON_TITLE <>", value, "buttonTitle");
            return (Criteria) this;
        }

        public Criteria andButtonTitleGreaterThan(String value) {
            addCriterion("BUTTON_TITLE >", value, "buttonTitle");
            return (Criteria) this;
        }

        public Criteria andButtonTitleGreaterThanOrEqualTo(String value) {
            addCriterion("BUTTON_TITLE >=", value, "buttonTitle");
            return (Criteria) this;
        }

        public Criteria andButtonTitleLessThan(String value) {
            addCriterion("BUTTON_TITLE <", value, "buttonTitle");
            return (Criteria) this;
        }

        public Criteria andButtonTitleLessThanOrEqualTo(String value) {
            addCriterion("BUTTON_TITLE <=", value, "buttonTitle");
            return (Criteria) this;
        }

        public Criteria andButtonTitleLike(String value) {
            addCriterion("BUTTON_TITLE like", value, "buttonTitle");
            return (Criteria) this;
        }

        public Criteria andButtonTitleNotLike(String value) {
            addCriterion("BUTTON_TITLE not like", value, "buttonTitle");
            return (Criteria) this;
        }

        public Criteria andButtonTitleIn(List<String> values) {
            addCriterion("BUTTON_TITLE in", values, "buttonTitle");
            return (Criteria) this;
        }

        public Criteria andButtonTitleNotIn(List<String> values) {
            addCriterion("BUTTON_TITLE not in", values, "buttonTitle");
            return (Criteria) this;
        }

        public Criteria andButtonTitleBetween(String value1, String value2) {
            addCriterion("BUTTON_TITLE between", value1, value2, "buttonTitle");
            return (Criteria) this;
        }

        public Criteria andButtonTitleNotBetween(String value1, String value2) {
            addCriterion("BUTTON_TITLE not between", value1, value2, "buttonTitle");
            return (Criteria) this;
        }

        public Criteria andButtonUrlIsNull() {
            addCriterion("BUTTON_URL is null");
            return (Criteria) this;
        }

        public Criteria andButtonUrlIsNotNull() {
            addCriterion("BUTTON_URL is not null");
            return (Criteria) this;
        }

        public Criteria andButtonUrlEqualTo(String value) {
            addCriterion("BUTTON_URL =", value, "buttonUrl");
            return (Criteria) this;
        }

        public Criteria andButtonUrlNotEqualTo(String value) {
            addCriterion("BUTTON_URL <>", value, "buttonUrl");
            return (Criteria) this;
        }

        public Criteria andButtonUrlGreaterThan(String value) {
            addCriterion("BUTTON_URL >", value, "buttonUrl");
            return (Criteria) this;
        }

        public Criteria andButtonUrlGreaterThanOrEqualTo(String value) {
            addCriterion("BUTTON_URL >=", value, "buttonUrl");
            return (Criteria) this;
        }

        public Criteria andButtonUrlLessThan(String value) {
            addCriterion("BUTTON_URL <", value, "buttonUrl");
            return (Criteria) this;
        }

        public Criteria andButtonUrlLessThanOrEqualTo(String value) {
            addCriterion("BUTTON_URL <=", value, "buttonUrl");
            return (Criteria) this;
        }

        public Criteria andButtonUrlLike(String value) {
            addCriterion("BUTTON_URL like", value, "buttonUrl");
            return (Criteria) this;
        }

        public Criteria andButtonUrlNotLike(String value) {
            addCriterion("BUTTON_URL not like", value, "buttonUrl");
            return (Criteria) this;
        }

        public Criteria andButtonUrlIn(List<String> values) {
            addCriterion("BUTTON_URL in", values, "buttonUrl");
            return (Criteria) this;
        }

        public Criteria andButtonUrlNotIn(List<String> values) {
            addCriterion("BUTTON_URL not in", values, "buttonUrl");
            return (Criteria) this;
        }

        public Criteria andButtonUrlBetween(String value1, String value2) {
            addCriterion("BUTTON_URL between", value1, value2, "buttonUrl");
            return (Criteria) this;
        }

        public Criteria andButtonUrlNotBetween(String value1, String value2) {
            addCriterion("BUTTON_URL not between", value1, value2, "buttonUrl");
            return (Criteria) this;
        }

        public Criteria andParentButtonIdIsNull() {
            addCriterion("PARENT_BUTTON_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentButtonIdIsNotNull() {
            addCriterion("PARENT_BUTTON_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentButtonIdEqualTo(Long value) {
            addCriterion("PARENT_BUTTON_ID =", value, "parentButtonId");
            return (Criteria) this;
        }

        public Criteria andParentButtonIdNotEqualTo(Long value) {
            addCriterion("PARENT_BUTTON_ID <>", value, "parentButtonId");
            return (Criteria) this;
        }

        public Criteria andParentButtonIdGreaterThan(Long value) {
            addCriterion("PARENT_BUTTON_ID >", value, "parentButtonId");
            return (Criteria) this;
        }

        public Criteria andParentButtonIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PARENT_BUTTON_ID >=", value, "parentButtonId");
            return (Criteria) this;
        }

        public Criteria andParentButtonIdLessThan(Long value) {
            addCriterion("PARENT_BUTTON_ID <", value, "parentButtonId");
            return (Criteria) this;
        }

        public Criteria andParentButtonIdLessThanOrEqualTo(Long value) {
            addCriterion("PARENT_BUTTON_ID <=", value, "parentButtonId");
            return (Criteria) this;
        }

        public Criteria andParentButtonIdIn(List<Long> values) {
            addCriterion("PARENT_BUTTON_ID in", values, "parentButtonId");
            return (Criteria) this;
        }

        public Criteria andParentButtonIdNotIn(List<Long> values) {
            addCriterion("PARENT_BUTTON_ID not in", values, "parentButtonId");
            return (Criteria) this;
        }

        public Criteria andParentButtonIdBetween(Long value1, Long value2) {
            addCriterion("PARENT_BUTTON_ID between", value1, value2, "parentButtonId");
            return (Criteria) this;
        }

        public Criteria andParentButtonIdNotBetween(Long value1, Long value2) {
            addCriterion("PARENT_BUTTON_ID not between", value1, value2, "parentButtonId");
            return (Criteria) this;
        }

        public Criteria andButtonTypeIsNull() {
            addCriterion("BUTTON_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andButtonTypeIsNotNull() {
            addCriterion("BUTTON_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andButtonTypeEqualTo(String value) {
            addCriterion("BUTTON_TYPE =", value, "buttonType");
            return (Criteria) this;
        }

        public Criteria andButtonTypeNotEqualTo(String value) {
            addCriterion("BUTTON_TYPE <>", value, "buttonType");
            return (Criteria) this;
        }

        public Criteria andButtonTypeGreaterThan(String value) {
            addCriterion("BUTTON_TYPE >", value, "buttonType");
            return (Criteria) this;
        }

        public Criteria andButtonTypeGreaterThanOrEqualTo(String value) {
            addCriterion("BUTTON_TYPE >=", value, "buttonType");
            return (Criteria) this;
        }

        public Criteria andButtonTypeLessThan(String value) {
            addCriterion("BUTTON_TYPE <", value, "buttonType");
            return (Criteria) this;
        }

        public Criteria andButtonTypeLessThanOrEqualTo(String value) {
            addCriterion("BUTTON_TYPE <=", value, "buttonType");
            return (Criteria) this;
        }

        public Criteria andButtonTypeLike(String value) {
            addCriterion("BUTTON_TYPE like", value, "buttonType");
            return (Criteria) this;
        }

        public Criteria andButtonTypeNotLike(String value) {
            addCriterion("BUTTON_TYPE not like", value, "buttonType");
            return (Criteria) this;
        }

        public Criteria andButtonTypeIn(List<String> values) {
            addCriterion("BUTTON_TYPE in", values, "buttonType");
            return (Criteria) this;
        }

        public Criteria andButtonTypeNotIn(List<String> values) {
            addCriterion("BUTTON_TYPE not in", values, "buttonType");
            return (Criteria) this;
        }

        public Criteria andButtonTypeBetween(String value1, String value2) {
            addCriterion("BUTTON_TYPE between", value1, value2, "buttonType");
            return (Criteria) this;
        }

        public Criteria andButtonTypeNotBetween(String value1, String value2) {
            addCriterion("BUTTON_TYPE not between", value1, value2, "buttonType");
            return (Criteria) this;
        }

        public Criteria andButtonDescIsNull() {
            addCriterion("BUTTON_DESC is null");
            return (Criteria) this;
        }

        public Criteria andButtonDescIsNotNull() {
            addCriterion("BUTTON_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andButtonDescEqualTo(String value) {
            addCriterion("BUTTON_DESC =", value, "buttonDesc");
            return (Criteria) this;
        }

        public Criteria andButtonDescNotEqualTo(String value) {
            addCriterion("BUTTON_DESC <>", value, "buttonDesc");
            return (Criteria) this;
        }

        public Criteria andButtonDescGreaterThan(String value) {
            addCriterion("BUTTON_DESC >", value, "buttonDesc");
            return (Criteria) this;
        }

        public Criteria andButtonDescGreaterThanOrEqualTo(String value) {
            addCriterion("BUTTON_DESC >=", value, "buttonDesc");
            return (Criteria) this;
        }

        public Criteria andButtonDescLessThan(String value) {
            addCriterion("BUTTON_DESC <", value, "buttonDesc");
            return (Criteria) this;
        }

        public Criteria andButtonDescLessThanOrEqualTo(String value) {
            addCriterion("BUTTON_DESC <=", value, "buttonDesc");
            return (Criteria) this;
        }

        public Criteria andButtonDescLike(String value) {
            addCriterion("BUTTON_DESC like", value, "buttonDesc");
            return (Criteria) this;
        }

        public Criteria andButtonDescNotLike(String value) {
            addCriterion("BUTTON_DESC not like", value, "buttonDesc");
            return (Criteria) this;
        }

        public Criteria andButtonDescIn(List<String> values) {
            addCriterion("BUTTON_DESC in", values, "buttonDesc");
            return (Criteria) this;
        }

        public Criteria andButtonDescNotIn(List<String> values) {
            addCriterion("BUTTON_DESC not in", values, "buttonDesc");
            return (Criteria) this;
        }

        public Criteria andButtonDescBetween(String value1, String value2) {
            addCriterion("BUTTON_DESC between", value1, value2, "buttonDesc");
            return (Criteria) this;
        }

        public Criteria andButtonDescNotBetween(String value1, String value2) {
            addCriterion("BUTTON_DESC not between", value1, value2, "buttonDesc");
            return (Criteria) this;
        }

        public Criteria andSortOrderIsNull() {
            addCriterion("SORT_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andSortOrderIsNotNull() {
            addCriterion("SORT_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andSortOrderEqualTo(Short value) {
            addCriterion("SORT_ORDER =", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderNotEqualTo(Short value) {
            addCriterion("SORT_ORDER <>", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderGreaterThan(Short value) {
            addCriterion("SORT_ORDER >", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderGreaterThanOrEqualTo(Short value) {
            addCriterion("SORT_ORDER >=", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderLessThan(Short value) {
            addCriterion("SORT_ORDER <", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderLessThanOrEqualTo(Short value) {
            addCriterion("SORT_ORDER <=", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderIn(List<Short> values) {
            addCriterion("SORT_ORDER in", values, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderNotIn(List<Short> values) {
            addCriterion("SORT_ORDER not in", values, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderBetween(Short value1, Short value2) {
            addCriterion("SORT_ORDER between", value1, value2, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderNotBetween(Short value1, Short value2) {
            addCriterion("SORT_ORDER not between", value1, value2, "sortOrder");
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
