package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsTypeCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsTypeCriteria() {
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

        public Criteria andTypeNameIsNull() {
            addCriterion("TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("TYPE_NAME =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("TYPE_NAME <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("TYPE_NAME >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_NAME >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("TYPE_NAME <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TYPE_NAME <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("TYPE_NAME like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("TYPE_NAME not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("TYPE_NAME in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("TYPE_NAME not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("TYPE_NAME between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("TYPE_NAME not between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeCodeIsNull() {
            addCriterion("TYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTypeCodeIsNotNull() {
            addCriterion("TYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeCodeEqualTo(String value) {
            addCriterion("TYPE_CODE =", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotEqualTo(String value) {
            addCriterion("TYPE_CODE <>", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeGreaterThan(String value) {
            addCriterion("TYPE_CODE >", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_CODE >=", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLessThan(String value) {
            addCriterion("TYPE_CODE <", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("TYPE_CODE <=", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLike(String value) {
            addCriterion("TYPE_CODE like", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotLike(String value) {
            addCriterion("TYPE_CODE not like", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeIn(List<String> values) {
            addCriterion("TYPE_CODE in", values, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotIn(List<String> values) {
            addCriterion("TYPE_CODE not in", values, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeBetween(String value1, String value2) {
            addCriterion("TYPE_CODE between", value1, value2, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotBetween(String value1, String value2) {
            addCriterion("TYPE_CODE not between", value1, value2, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeDescIsNull() {
            addCriterion("TYPE_DESC is null");
            return (Criteria) this;
        }

        public Criteria andTypeDescIsNotNull() {
            addCriterion("TYPE_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andTypeDescEqualTo(String value) {
            addCriterion("TYPE_DESC =", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescNotEqualTo(String value) {
            addCriterion("TYPE_DESC <>", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescGreaterThan(String value) {
            addCriterion("TYPE_DESC >", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_DESC >=", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescLessThan(String value) {
            addCriterion("TYPE_DESC <", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescLessThanOrEqualTo(String value) {
            addCriterion("TYPE_DESC <=", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescLike(String value) {
            addCriterion("TYPE_DESC like", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescNotLike(String value) {
            addCriterion("TYPE_DESC not like", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescIn(List<String> values) {
            addCriterion("TYPE_DESC in", values, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescNotIn(List<String> values) {
            addCriterion("TYPE_DESC not in", values, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescBetween(String value1, String value2) {
            addCriterion("TYPE_DESC between", value1, value2, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescNotBetween(String value1, String value2) {
            addCriterion("TYPE_DESC not between", value1, value2, "typeDesc");
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

        public Criteria andIfEntitycodeIsNull() {
            addCriterion("IF_ENTITYCODE is null");
            return (Criteria) this;
        }

        public Criteria andIfEntitycodeIsNotNull() {
            addCriterion("IF_ENTITYCODE is not null");
            return (Criteria) this;
        }

        public Criteria andIfEntitycodeEqualTo(String value) {
            addCriterion("IF_ENTITYCODE =", value, "ifEntitycode");
            return (Criteria) this;
        }

        public Criteria andIfEntitycodeNotEqualTo(String value) {
            addCriterion("IF_ENTITYCODE <>", value, "ifEntitycode");
            return (Criteria) this;
        }

        public Criteria andIfEntitycodeGreaterThan(String value) {
            addCriterion("IF_ENTITYCODE >", value, "ifEntitycode");
            return (Criteria) this;
        }

        public Criteria andIfEntitycodeGreaterThanOrEqualTo(String value) {
            addCriterion("IF_ENTITYCODE >=", value, "ifEntitycode");
            return (Criteria) this;
        }

        public Criteria andIfEntitycodeLessThan(String value) {
            addCriterion("IF_ENTITYCODE <", value, "ifEntitycode");
            return (Criteria) this;
        }

        public Criteria andIfEntitycodeLessThanOrEqualTo(String value) {
            addCriterion("IF_ENTITYCODE <=", value, "ifEntitycode");
            return (Criteria) this;
        }

        public Criteria andIfEntitycodeLike(String value) {
            addCriterion("IF_ENTITYCODE like", value, "ifEntitycode");
            return (Criteria) this;
        }

        public Criteria andIfEntitycodeNotLike(String value) {
            addCriterion("IF_ENTITYCODE not like", value, "ifEntitycode");
            return (Criteria) this;
        }

        public Criteria andIfEntitycodeIn(List<String> values) {
            addCriterion("IF_ENTITYCODE in", values, "ifEntitycode");
            return (Criteria) this;
        }

        public Criteria andIfEntitycodeNotIn(List<String> values) {
            addCriterion("IF_ENTITYCODE not in", values, "ifEntitycode");
            return (Criteria) this;
        }

        public Criteria andIfEntitycodeBetween(String value1, String value2) {
            addCriterion("IF_ENTITYCODE between", value1, value2, "ifEntitycode");
            return (Criteria) this;
        }

        public Criteria andIfEntitycodeNotBetween(String value1, String value2) {
            addCriterion("IF_ENTITYCODE not between", value1, value2, "ifEntitycode");
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

        public Criteria andIfNeedstockIsNull() {
            addCriterion("IF_NEEDSTOCK is null");
            return (Criteria) this;
        }

        public Criteria andIfNeedstockIsNotNull() {
            addCriterion("IF_NEEDSTOCK is not null");
            return (Criteria) this;
        }

        public Criteria andIfNeedstockEqualTo(String value) {
            addCriterion("IF_NEEDSTOCK =", value, "ifNeedstock");
            return (Criteria) this;
        }

        public Criteria andIfNeedstockNotEqualTo(String value) {
            addCriterion("IF_NEEDSTOCK <>", value, "ifNeedstock");
            return (Criteria) this;
        }

        public Criteria andIfNeedstockGreaterThan(String value) {
            addCriterion("IF_NEEDSTOCK >", value, "ifNeedstock");
            return (Criteria) this;
        }

        public Criteria andIfNeedstockGreaterThanOrEqualTo(String value) {
            addCriterion("IF_NEEDSTOCK >=", value, "ifNeedstock");
            return (Criteria) this;
        }

        public Criteria andIfNeedstockLessThan(String value) {
            addCriterion("IF_NEEDSTOCK <", value, "ifNeedstock");
            return (Criteria) this;
        }

        public Criteria andIfNeedstockLessThanOrEqualTo(String value) {
            addCriterion("IF_NEEDSTOCK <=", value, "ifNeedstock");
            return (Criteria) this;
        }

        public Criteria andIfNeedstockLike(String value) {
            addCriterion("IF_NEEDSTOCK like", value, "ifNeedstock");
            return (Criteria) this;
        }

        public Criteria andIfNeedstockNotLike(String value) {
            addCriterion("IF_NEEDSTOCK not like", value, "ifNeedstock");
            return (Criteria) this;
        }

        public Criteria andIfNeedstockIn(List<String> values) {
            addCriterion("IF_NEEDSTOCK in", values, "ifNeedstock");
            return (Criteria) this;
        }

        public Criteria andIfNeedstockNotIn(List<String> values) {
            addCriterion("IF_NEEDSTOCK not in", values, "ifNeedstock");
            return (Criteria) this;
        }

        public Criteria andIfNeedstockBetween(String value1, String value2) {
            addCriterion("IF_NEEDSTOCK between", value1, value2, "ifNeedstock");
            return (Criteria) this;
        }

        public Criteria andIfNeedstockNotBetween(String value1, String value2) {
            addCriterion("IF_NEEDSTOCK not between", value1, value2, "ifNeedstock");
            return (Criteria) this;
        }

        public Criteria andIfFreeIsNull() {
            addCriterion("IF_FREE is null");
            return (Criteria) this;
        }

        public Criteria andIfFreeIsNotNull() {
            addCriterion("IF_FREE is not null");
            return (Criteria) this;
        }

        public Criteria andIfFreeEqualTo(String value) {
            addCriterion("IF_FREE =", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeNotEqualTo(String value) {
            addCriterion("IF_FREE <>", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeGreaterThan(String value) {
            addCriterion("IF_FREE >", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeGreaterThanOrEqualTo(String value) {
            addCriterion("IF_FREE >=", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeLessThan(String value) {
            addCriterion("IF_FREE <", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeLessThanOrEqualTo(String value) {
            addCriterion("IF_FREE <=", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeLike(String value) {
            addCriterion("IF_FREE like", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeNotLike(String value) {
            addCriterion("IF_FREE not like", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeIn(List<String> values) {
            addCriterion("IF_FREE in", values, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeNotIn(List<String> values) {
            addCriterion("IF_FREE not in", values, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeBetween(String value1, String value2) {
            addCriterion("IF_FREE between", value1, value2, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeNotBetween(String value1, String value2) {
            addCriterion("IF_FREE not between", value1, value2, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfBuyonceIsNull() {
            addCriterion("IF_BUYONCE is null");
            return (Criteria) this;
        }

        public Criteria andIfBuyonceIsNotNull() {
            addCriterion("IF_BUYONCE is not null");
            return (Criteria) this;
        }

        public Criteria andIfBuyonceEqualTo(String value) {
            addCriterion("IF_BUYONCE =", value, "ifBuyonce");
            return (Criteria) this;
        }

        public Criteria andIfBuyonceNotEqualTo(String value) {
            addCriterion("IF_BUYONCE <>", value, "ifBuyonce");
            return (Criteria) this;
        }

        public Criteria andIfBuyonceGreaterThan(String value) {
            addCriterion("IF_BUYONCE >", value, "ifBuyonce");
            return (Criteria) this;
        }

        public Criteria andIfBuyonceGreaterThanOrEqualTo(String value) {
            addCriterion("IF_BUYONCE >=", value, "ifBuyonce");
            return (Criteria) this;
        }

        public Criteria andIfBuyonceLessThan(String value) {
            addCriterion("IF_BUYONCE <", value, "ifBuyonce");
            return (Criteria) this;
        }

        public Criteria andIfBuyonceLessThanOrEqualTo(String value) {
            addCriterion("IF_BUYONCE <=", value, "ifBuyonce");
            return (Criteria) this;
        }

        public Criteria andIfBuyonceLike(String value) {
            addCriterion("IF_BUYONCE like", value, "ifBuyonce");
            return (Criteria) this;
        }

        public Criteria andIfBuyonceNotLike(String value) {
            addCriterion("IF_BUYONCE not like", value, "ifBuyonce");
            return (Criteria) this;
        }

        public Criteria andIfBuyonceIn(List<String> values) {
            addCriterion("IF_BUYONCE in", values, "ifBuyonce");
            return (Criteria) this;
        }

        public Criteria andIfBuyonceNotIn(List<String> values) {
            addCriterion("IF_BUYONCE not in", values, "ifBuyonce");
            return (Criteria) this;
        }

        public Criteria andIfBuyonceBetween(String value1, String value2) {
            addCriterion("IF_BUYONCE between", value1, value2, "ifBuyonce");
            return (Criteria) this;
        }

        public Criteria andIfBuyonceNotBetween(String value1, String value2) {
            addCriterion("IF_BUYONCE not between", value1, value2, "ifBuyonce");
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
