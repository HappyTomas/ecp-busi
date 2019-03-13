package com.zengshi.ecp.unpf.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UnpfPropValueCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public UnpfPropValueCriteria() {
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

        public Criteria andPropVCodeIsNull() {
            addCriterion("PROP_V_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPropVCodeIsNotNull() {
            addCriterion("PROP_V_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPropVCodeEqualTo(String value) {
            addCriterion("PROP_V_CODE =", value, "propVCode");
            return (Criteria) this;
        }

        public Criteria andPropVCodeNotEqualTo(String value) {
            addCriterion("PROP_V_CODE <>", value, "propVCode");
            return (Criteria) this;
        }

        public Criteria andPropVCodeGreaterThan(String value) {
            addCriterion("PROP_V_CODE >", value, "propVCode");
            return (Criteria) this;
        }

        public Criteria andPropVCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_V_CODE >=", value, "propVCode");
            return (Criteria) this;
        }

        public Criteria andPropVCodeLessThan(String value) {
            addCriterion("PROP_V_CODE <", value, "propVCode");
            return (Criteria) this;
        }

        public Criteria andPropVCodeLessThanOrEqualTo(String value) {
            addCriterion("PROP_V_CODE <=", value, "propVCode");
            return (Criteria) this;
        }

        public Criteria andPropVCodeLike(String value) {
            addCriterion("PROP_V_CODE like", value, "propVCode");
            return (Criteria) this;
        }

        public Criteria andPropVCodeNotLike(String value) {
            addCriterion("PROP_V_CODE not like", value, "propVCode");
            return (Criteria) this;
        }

        public Criteria andPropVCodeIn(List<String> values) {
            addCriterion("PROP_V_CODE in", values, "propVCode");
            return (Criteria) this;
        }

        public Criteria andPropVCodeNotIn(List<String> values) {
            addCriterion("PROP_V_CODE not in", values, "propVCode");
            return (Criteria) this;
        }

        public Criteria andPropVCodeBetween(String value1, String value2) {
            addCriterion("PROP_V_CODE between", value1, value2, "propVCode");
            return (Criteria) this;
        }

        public Criteria andPropVCodeNotBetween(String value1, String value2) {
            addCriterion("PROP_V_CODE not between", value1, value2, "propVCode");
            return (Criteria) this;
        }

        public Criteria andPropVNameIsNull() {
            addCriterion("PROP_V_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPropVNameIsNotNull() {
            addCriterion("PROP_V_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPropVNameEqualTo(String value) {
            addCriterion("PROP_V_NAME =", value, "propVName");
            return (Criteria) this;
        }

        public Criteria andPropVNameNotEqualTo(String value) {
            addCriterion("PROP_V_NAME <>", value, "propVName");
            return (Criteria) this;
        }

        public Criteria andPropVNameGreaterThan(String value) {
            addCriterion("PROP_V_NAME >", value, "propVName");
            return (Criteria) this;
        }

        public Criteria andPropVNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_V_NAME >=", value, "propVName");
            return (Criteria) this;
        }

        public Criteria andPropVNameLessThan(String value) {
            addCriterion("PROP_V_NAME <", value, "propVName");
            return (Criteria) this;
        }

        public Criteria andPropVNameLessThanOrEqualTo(String value) {
            addCriterion("PROP_V_NAME <=", value, "propVName");
            return (Criteria) this;
        }

        public Criteria andPropVNameLike(String value) {
            addCriterion("PROP_V_NAME like", value, "propVName");
            return (Criteria) this;
        }

        public Criteria andPropVNameNotLike(String value) {
            addCriterion("PROP_V_NAME not like", value, "propVName");
            return (Criteria) this;
        }

        public Criteria andPropVNameIn(List<String> values) {
            addCriterion("PROP_V_NAME in", values, "propVName");
            return (Criteria) this;
        }

        public Criteria andPropVNameNotIn(List<String> values) {
            addCriterion("PROP_V_NAME not in", values, "propVName");
            return (Criteria) this;
        }

        public Criteria andPropVNameBetween(String value1, String value2) {
            addCriterion("PROP_V_NAME between", value1, value2, "propVName");
            return (Criteria) this;
        }

        public Criteria andPropVNameNotBetween(String value1, String value2) {
            addCriterion("PROP_V_NAME not between", value1, value2, "propVName");
            return (Criteria) this;
        }

        public Criteria andPropVSnameIsNull() {
            addCriterion("PROP_V_SNAME is null");
            return (Criteria) this;
        }

        public Criteria andPropVSnameIsNotNull() {
            addCriterion("PROP_V_SNAME is not null");
            return (Criteria) this;
        }

        public Criteria andPropVSnameEqualTo(String value) {
            addCriterion("PROP_V_SNAME =", value, "propVSname");
            return (Criteria) this;
        }

        public Criteria andPropVSnameNotEqualTo(String value) {
            addCriterion("PROP_V_SNAME <>", value, "propVSname");
            return (Criteria) this;
        }

        public Criteria andPropVSnameGreaterThan(String value) {
            addCriterion("PROP_V_SNAME >", value, "propVSname");
            return (Criteria) this;
        }

        public Criteria andPropVSnameGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_V_SNAME >=", value, "propVSname");
            return (Criteria) this;
        }

        public Criteria andPropVSnameLessThan(String value) {
            addCriterion("PROP_V_SNAME <", value, "propVSname");
            return (Criteria) this;
        }

        public Criteria andPropVSnameLessThanOrEqualTo(String value) {
            addCriterion("PROP_V_SNAME <=", value, "propVSname");
            return (Criteria) this;
        }

        public Criteria andPropVSnameLike(String value) {
            addCriterion("PROP_V_SNAME like", value, "propVSname");
            return (Criteria) this;
        }

        public Criteria andPropVSnameNotLike(String value) {
            addCriterion("PROP_V_SNAME not like", value, "propVSname");
            return (Criteria) this;
        }

        public Criteria andPropVSnameIn(List<String> values) {
            addCriterion("PROP_V_SNAME in", values, "propVSname");
            return (Criteria) this;
        }

        public Criteria andPropVSnameNotIn(List<String> values) {
            addCriterion("PROP_V_SNAME not in", values, "propVSname");
            return (Criteria) this;
        }

        public Criteria andPropVSnameBetween(String value1, String value2) {
            addCriterion("PROP_V_SNAME between", value1, value2, "propVSname");
            return (Criteria) this;
        }

        public Criteria andPropVSnameNotBetween(String value1, String value2) {
            addCriterion("PROP_V_SNAME not between", value1, value2, "propVSname");
            return (Criteria) this;
        }

        public Criteria andPropVDescIsNull() {
            addCriterion("PROP_V_DESC is null");
            return (Criteria) this;
        }

        public Criteria andPropVDescIsNotNull() {
            addCriterion("PROP_V_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andPropVDescEqualTo(String value) {
            addCriterion("PROP_V_DESC =", value, "propVDesc");
            return (Criteria) this;
        }

        public Criteria andPropVDescNotEqualTo(String value) {
            addCriterion("PROP_V_DESC <>", value, "propVDesc");
            return (Criteria) this;
        }

        public Criteria andPropVDescGreaterThan(String value) {
            addCriterion("PROP_V_DESC >", value, "propVDesc");
            return (Criteria) this;
        }

        public Criteria andPropVDescGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_V_DESC >=", value, "propVDesc");
            return (Criteria) this;
        }

        public Criteria andPropVDescLessThan(String value) {
            addCriterion("PROP_V_DESC <", value, "propVDesc");
            return (Criteria) this;
        }

        public Criteria andPropVDescLessThanOrEqualTo(String value) {
            addCriterion("PROP_V_DESC <=", value, "propVDesc");
            return (Criteria) this;
        }

        public Criteria andPropVDescLike(String value) {
            addCriterion("PROP_V_DESC like", value, "propVDesc");
            return (Criteria) this;
        }

        public Criteria andPropVDescNotLike(String value) {
            addCriterion("PROP_V_DESC not like", value, "propVDesc");
            return (Criteria) this;
        }

        public Criteria andPropVDescIn(List<String> values) {
            addCriterion("PROP_V_DESC in", values, "propVDesc");
            return (Criteria) this;
        }

        public Criteria andPropVDescNotIn(List<String> values) {
            addCriterion("PROP_V_DESC not in", values, "propVDesc");
            return (Criteria) this;
        }

        public Criteria andPropVDescBetween(String value1, String value2) {
            addCriterion("PROP_V_DESC between", value1, value2, "propVDesc");
            return (Criteria) this;
        }

        public Criteria andPropVDescNotBetween(String value1, String value2) {
            addCriterion("PROP_V_DESC not between", value1, value2, "propVDesc");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNull() {
            addCriterion("SORT_NO is null");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNotNull() {
            addCriterion("SORT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andSortNoEqualTo(Integer value) {
            addCriterion("SORT_NO =", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotEqualTo(Integer value) {
            addCriterion("SORT_NO <>", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThan(Integer value) {
            addCriterion("SORT_NO >", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("SORT_NO >=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThan(Integer value) {
            addCriterion("SORT_NO <", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThanOrEqualTo(Integer value) {
            addCriterion("SORT_NO <=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoIn(List<Integer> values) {
            addCriterion("SORT_NO in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotIn(List<Integer> values) {
            addCriterion("SORT_NO not in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoBetween(Integer value1, Integer value2) {
            addCriterion("SORT_NO between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotBetween(Integer value1, Integer value2) {
            addCriterion("SORT_NO not between", value1, value2, "sortNo");
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

        public Criteria andCatgCodeIsNull() {
            addCriterion("CATG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCatgCodeIsNotNull() {
            addCriterion("CATG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCatgCodeEqualTo(String value) {
            addCriterion("CATG_CODE =", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeNotEqualTo(String value) {
            addCriterion("CATG_CODE <>", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeGreaterThan(String value) {
            addCriterion("CATG_CODE >", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CATG_CODE >=", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeLessThan(String value) {
            addCriterion("CATG_CODE <", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeLessThanOrEqualTo(String value) {
            addCriterion("CATG_CODE <=", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeLike(String value) {
            addCriterion("CATG_CODE like", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeNotLike(String value) {
            addCriterion("CATG_CODE not like", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeIn(List<String> values) {
            addCriterion("CATG_CODE in", values, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeNotIn(List<String> values) {
            addCriterion("CATG_CODE not in", values, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeBetween(String value1, String value2) {
            addCriterion("CATG_CODE between", value1, value2, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeNotBetween(String value1, String value2) {
            addCriterion("CATG_CODE not between", value1, value2, "catgCode");
            return (Criteria) this;
        }

        public Criteria andPlatTypeIsNull() {
            addCriterion("PLAT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPlatTypeIsNotNull() {
            addCriterion("PLAT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPlatTypeEqualTo(String value) {
            addCriterion("PLAT_TYPE =", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotEqualTo(String value) {
            addCriterion("PLAT_TYPE <>", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeGreaterThan(String value) {
            addCriterion("PLAT_TYPE >", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PLAT_TYPE >=", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeLessThan(String value) {
            addCriterion("PLAT_TYPE <", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeLessThanOrEqualTo(String value) {
            addCriterion("PLAT_TYPE <=", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeLike(String value) {
            addCriterion("PLAT_TYPE like", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotLike(String value) {
            addCriterion("PLAT_TYPE not like", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeIn(List<String> values) {
            addCriterion("PLAT_TYPE in", values, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotIn(List<String> values) {
            addCriterion("PLAT_TYPE not in", values, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeBetween(String value1, String value2) {
            addCriterion("PLAT_TYPE between", value1, value2, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotBetween(String value1, String value2) {
            addCriterion("PLAT_TYPE not between", value1, value2, "platType");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNull() {
            addCriterion("SHOP_ID is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("SHOP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Long value) {
            addCriterion("SHOP_ID =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Long value) {
            addCriterion("SHOP_ID <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Long value) {
            addCriterion("SHOP_ID >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SHOP_ID >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Long value) {
            addCriterion("SHOP_ID <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Long value) {
            addCriterion("SHOP_ID <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Long> values) {
            addCriterion("SHOP_ID in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Long> values) {
            addCriterion("SHOP_ID not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Long value1, Long value2) {
            addCriterion("SHOP_ID between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Long value1, Long value2) {
            addCriterion("SHOP_ID not between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdIsNull() {
            addCriterion("SHOP_AUTH_ID is null");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdIsNotNull() {
            addCriterion("SHOP_AUTH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdEqualTo(Long value) {
            addCriterion("SHOP_AUTH_ID =", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdNotEqualTo(Long value) {
            addCriterion("SHOP_AUTH_ID <>", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdGreaterThan(Long value) {
            addCriterion("SHOP_AUTH_ID >", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SHOP_AUTH_ID >=", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdLessThan(Long value) {
            addCriterion("SHOP_AUTH_ID <", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdLessThanOrEqualTo(Long value) {
            addCriterion("SHOP_AUTH_ID <=", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdIn(List<Long> values) {
            addCriterion("SHOP_AUTH_ID in", values, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdNotIn(List<Long> values) {
            addCriterion("SHOP_AUTH_ID not in", values, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdBetween(Long value1, Long value2) {
            addCriterion("SHOP_AUTH_ID between", value1, value2, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdNotBetween(Long value1, Long value2) {
            addCriterion("SHOP_AUTH_ID not between", value1, value2, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andPropRelaIdIsNull() {
            addCriterion("PROP_RELA_ID is null");
            return (Criteria) this;
        }

        public Criteria andPropRelaIdIsNotNull() {
            addCriterion("PROP_RELA_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPropRelaIdEqualTo(Long value) {
            addCriterion("PROP_RELA_ID =", value, "propRelaId");
            return (Criteria) this;
        }

        public Criteria andPropRelaIdNotEqualTo(Long value) {
            addCriterion("PROP_RELA_ID <>", value, "propRelaId");
            return (Criteria) this;
        }

        public Criteria andPropRelaIdGreaterThan(Long value) {
            addCriterion("PROP_RELA_ID >", value, "propRelaId");
            return (Criteria) this;
        }

        public Criteria andPropRelaIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROP_RELA_ID >=", value, "propRelaId");
            return (Criteria) this;
        }

        public Criteria andPropRelaIdLessThan(Long value) {
            addCriterion("PROP_RELA_ID <", value, "propRelaId");
            return (Criteria) this;
        }

        public Criteria andPropRelaIdLessThanOrEqualTo(Long value) {
            addCriterion("PROP_RELA_ID <=", value, "propRelaId");
            return (Criteria) this;
        }

        public Criteria andPropRelaIdIn(List<Long> values) {
            addCriterion("PROP_RELA_ID in", values, "propRelaId");
            return (Criteria) this;
        }

        public Criteria andPropRelaIdNotIn(List<Long> values) {
            addCriterion("PROP_RELA_ID not in", values, "propRelaId");
            return (Criteria) this;
        }

        public Criteria andPropRelaIdBetween(Long value1, Long value2) {
            addCriterion("PROP_RELA_ID between", value1, value2, "propRelaId");
            return (Criteria) this;
        }

        public Criteria andPropRelaIdNotBetween(Long value1, Long value2) {
            addCriterion("PROP_RELA_ID not between", value1, value2, "propRelaId");
            return (Criteria) this;
        }

        public Criteria andPropCodeIsNull() {
            addCriterion("PROP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPropCodeIsNotNull() {
            addCriterion("PROP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPropCodeEqualTo(String value) {
            addCriterion("PROP_CODE =", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeNotEqualTo(String value) {
            addCriterion("PROP_CODE <>", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeGreaterThan(String value) {
            addCriterion("PROP_CODE >", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_CODE >=", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeLessThan(String value) {
            addCriterion("PROP_CODE <", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeLessThanOrEqualTo(String value) {
            addCriterion("PROP_CODE <=", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeLike(String value) {
            addCriterion("PROP_CODE like", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeNotLike(String value) {
            addCriterion("PROP_CODE not like", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeIn(List<String> values) {
            addCriterion("PROP_CODE in", values, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeNotIn(List<String> values) {
            addCriterion("PROP_CODE not in", values, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeBetween(String value1, String value2) {
            addCriterion("PROP_CODE between", value1, value2, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeNotBetween(String value1, String value2) {
            addCriterion("PROP_CODE not between", value1, value2, "propCode");
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