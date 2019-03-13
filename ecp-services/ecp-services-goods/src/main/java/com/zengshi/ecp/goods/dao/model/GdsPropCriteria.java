package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsPropCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsPropCriteria() {
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

        public Criteria andPropNameIsNull() {
            addCriterion("PROP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPropNameIsNotNull() {
            addCriterion("PROP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPropNameEqualTo(String value) {
            addCriterion("PROP_NAME =", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameNotEqualTo(String value) {
            addCriterion("PROP_NAME <>", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameGreaterThan(String value) {
            addCriterion("PROP_NAME >", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_NAME >=", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameLessThan(String value) {
            addCriterion("PROP_NAME <", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameLessThanOrEqualTo(String value) {
            addCriterion("PROP_NAME <=", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameLike(String value) {
            addCriterion("PROP_NAME like", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameNotLike(String value) {
            addCriterion("PROP_NAME not like", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameIn(List<String> values) {
            addCriterion("PROP_NAME in", values, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameNotIn(List<String> values) {
            addCriterion("PROP_NAME not in", values, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameBetween(String value1, String value2) {
            addCriterion("PROP_NAME between", value1, value2, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameNotBetween(String value1, String value2) {
            addCriterion("PROP_NAME not between", value1, value2, "propName");
            return (Criteria) this;
        }

        public Criteria andPropSnameIsNull() {
            addCriterion("PROP_SNAME is null");
            return (Criteria) this;
        }

        public Criteria andPropSnameIsNotNull() {
            addCriterion("PROP_SNAME is not null");
            return (Criteria) this;
        }

        public Criteria andPropSnameEqualTo(String value) {
            addCriterion("PROP_SNAME =", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameNotEqualTo(String value) {
            addCriterion("PROP_SNAME <>", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameGreaterThan(String value) {
            addCriterion("PROP_SNAME >", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_SNAME >=", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameLessThan(String value) {
            addCriterion("PROP_SNAME <", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameLessThanOrEqualTo(String value) {
            addCriterion("PROP_SNAME <=", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameLike(String value) {
            addCriterion("PROP_SNAME like", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameNotLike(String value) {
            addCriterion("PROP_SNAME not like", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameIn(List<String> values) {
            addCriterion("PROP_SNAME in", values, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameNotIn(List<String> values) {
            addCriterion("PROP_SNAME not in", values, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameBetween(String value1, String value2) {
            addCriterion("PROP_SNAME between", value1, value2, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameNotBetween(String value1, String value2) {
            addCriterion("PROP_SNAME not between", value1, value2, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeIsNull() {
            addCriterion("PROP_VALUE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeIsNotNull() {
            addCriterion("PROP_VALUE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeEqualTo(String value) {
            addCriterion("PROP_VALUE_TYPE =", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeNotEqualTo(String value) {
            addCriterion("PROP_VALUE_TYPE <>", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeGreaterThan(String value) {
            addCriterion("PROP_VALUE_TYPE >", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_VALUE_TYPE >=", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeLessThan(String value) {
            addCriterion("PROP_VALUE_TYPE <", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeLessThanOrEqualTo(String value) {
            addCriterion("PROP_VALUE_TYPE <=", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeLike(String value) {
            addCriterion("PROP_VALUE_TYPE like", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeNotLike(String value) {
            addCriterion("PROP_VALUE_TYPE not like", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeIn(List<String> values) {
            addCriterion("PROP_VALUE_TYPE in", values, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeNotIn(List<String> values) {
            addCriterion("PROP_VALUE_TYPE not in", values, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeBetween(String value1, String value2) {
            addCriterion("PROP_VALUE_TYPE between", value1, value2, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeNotBetween(String value1, String value2) {
            addCriterion("PROP_VALUE_TYPE not between", value1, value2, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropTypeIsNull() {
            addCriterion("PROP_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPropTypeIsNotNull() {
            addCriterion("PROP_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPropTypeEqualTo(String value) {
            addCriterion("PROP_TYPE =", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeNotEqualTo(String value) {
            addCriterion("PROP_TYPE <>", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeGreaterThan(String value) {
            addCriterion("PROP_TYPE >", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_TYPE >=", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeLessThan(String value) {
            addCriterion("PROP_TYPE <", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeLessThanOrEqualTo(String value) {
            addCriterion("PROP_TYPE <=", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeLike(String value) {
            addCriterion("PROP_TYPE like", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeNotLike(String value) {
            addCriterion("PROP_TYPE not like", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeIn(List<String> values) {
            addCriterion("PROP_TYPE in", values, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeNotIn(List<String> values) {
            addCriterion("PROP_TYPE not in", values, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeBetween(String value1, String value2) {
            addCriterion("PROP_TYPE between", value1, value2, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeNotBetween(String value1, String value2) {
            addCriterion("PROP_TYPE not between", value1, value2, "propType");
            return (Criteria) this;
        }

        public Criteria andPropDescIsNull() {
            addCriterion("PROP_DESC is null");
            return (Criteria) this;
        }

        public Criteria andPropDescIsNotNull() {
            addCriterion("PROP_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andPropDescEqualTo(String value) {
            addCriterion("PROP_DESC =", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescNotEqualTo(String value) {
            addCriterion("PROP_DESC <>", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescGreaterThan(String value) {
            addCriterion("PROP_DESC >", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_DESC >=", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescLessThan(String value) {
            addCriterion("PROP_DESC <", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescLessThanOrEqualTo(String value) {
            addCriterion("PROP_DESC <=", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescLike(String value) {
            addCriterion("PROP_DESC like", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescNotLike(String value) {
            addCriterion("PROP_DESC not like", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescIn(List<String> values) {
            addCriterion("PROP_DESC in", values, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescNotIn(List<String> values) {
            addCriterion("PROP_DESC not in", values, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescBetween(String value1, String value2) {
            addCriterion("PROP_DESC between", value1, value2, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescNotBetween(String value1, String value2) {
            addCriterion("PROP_DESC not between", value1, value2, "propDesc");
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

        public Criteria andPropInputTypeIsNull() {
            addCriterion("PROP_INPUT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeIsNotNull() {
            addCriterion("PROP_INPUT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeEqualTo(String value) {
            addCriterion("PROP_INPUT_TYPE =", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeNotEqualTo(String value) {
            addCriterion("PROP_INPUT_TYPE <>", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeGreaterThan(String value) {
            addCriterion("PROP_INPUT_TYPE >", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_INPUT_TYPE >=", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeLessThan(String value) {
            addCriterion("PROP_INPUT_TYPE <", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeLessThanOrEqualTo(String value) {
            addCriterion("PROP_INPUT_TYPE <=", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeLike(String value) {
            addCriterion("PROP_INPUT_TYPE like", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeNotLike(String value) {
            addCriterion("PROP_INPUT_TYPE not like", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeIn(List<String> values) {
            addCriterion("PROP_INPUT_TYPE in", values, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeNotIn(List<String> values) {
            addCriterion("PROP_INPUT_TYPE not in", values, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeBetween(String value1, String value2) {
            addCriterion("PROP_INPUT_TYPE between", value1, value2, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeNotBetween(String value1, String value2) {
            addCriterion("PROP_INPUT_TYPE not between", value1, value2, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleIsNull() {
            addCriterion("PROP_INPUT_RULE is null");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleIsNotNull() {
            addCriterion("PROP_INPUT_RULE is not null");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleEqualTo(String value) {
            addCriterion("PROP_INPUT_RULE =", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleNotEqualTo(String value) {
            addCriterion("PROP_INPUT_RULE <>", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleGreaterThan(String value) {
            addCriterion("PROP_INPUT_RULE >", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_INPUT_RULE >=", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleLessThan(String value) {
            addCriterion("PROP_INPUT_RULE <", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleLessThanOrEqualTo(String value) {
            addCriterion("PROP_INPUT_RULE <=", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleLike(String value) {
            addCriterion("PROP_INPUT_RULE like", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleNotLike(String value) {
            addCriterion("PROP_INPUT_RULE not like", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleIn(List<String> values) {
            addCriterion("PROP_INPUT_RULE in", values, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleNotIn(List<String> values) {
            addCriterion("PROP_INPUT_RULE not in", values, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleBetween(String value1, String value2) {
            addCriterion("PROP_INPUT_RULE between", value1, value2, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleNotBetween(String value1, String value2) {
            addCriterion("PROP_INPUT_RULE not between", value1, value2, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditIsNull() {
            addCriterion("IF_ABLE_EDIT is null");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditIsNotNull() {
            addCriterion("IF_ABLE_EDIT is not null");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditEqualTo(String value) {
            addCriterion("IF_ABLE_EDIT =", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditNotEqualTo(String value) {
            addCriterion("IF_ABLE_EDIT <>", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditGreaterThan(String value) {
            addCriterion("IF_ABLE_EDIT >", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditGreaterThanOrEqualTo(String value) {
            addCriterion("IF_ABLE_EDIT >=", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditLessThan(String value) {
            addCriterion("IF_ABLE_EDIT <", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditLessThanOrEqualTo(String value) {
            addCriterion("IF_ABLE_EDIT <=", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditLike(String value) {
            addCriterion("IF_ABLE_EDIT like", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditNotLike(String value) {
            addCriterion("IF_ABLE_EDIT not like", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditIn(List<String> values) {
            addCriterion("IF_ABLE_EDIT in", values, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditNotIn(List<String> values) {
            addCriterion("IF_ABLE_EDIT not in", values, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditBetween(String value1, String value2) {
            addCriterion("IF_ABLE_EDIT between", value1, value2, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditNotBetween(String value1, String value2) {
            addCriterion("IF_ABLE_EDIT not between", value1, value2, "ifAbleEdit");
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
