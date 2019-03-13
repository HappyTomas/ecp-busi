package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsCatg2PropCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsCatg2PropCriteria() {
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

        public Criteria andPropIdIsNull() {
            addCriterion("PROP_ID is null");
            return (Criteria) this;
        }

        public Criteria andPropIdIsNotNull() {
            addCriterion("PROP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPropIdEqualTo(Long value) {
            addCriterion("PROP_ID =", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdNotEqualTo(Long value) {
            addCriterion("PROP_ID <>", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdGreaterThan(Long value) {
            addCriterion("PROP_ID >", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROP_ID >=", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdLessThan(Long value) {
            addCriterion("PROP_ID <", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdLessThanOrEqualTo(Long value) {
            addCriterion("PROP_ID <=", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdIn(List<Long> values) {
            addCriterion("PROP_ID in", values, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdNotIn(List<Long> values) {
            addCriterion("PROP_ID not in", values, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdBetween(Long value1, Long value2) {
            addCriterion("PROP_ID between", value1, value2, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdNotBetween(Long value1, Long value2) {
            addCriterion("PROP_ID not between", value1, value2, "propId");
            return (Criteria) this;
        }

        public Criteria andIfBasicIsNull() {
            addCriterion("IF_BASIC is null");
            return (Criteria) this;
        }

        public Criteria andIfBasicIsNotNull() {
            addCriterion("IF_BASIC is not null");
            return (Criteria) this;
        }

        public Criteria andIfBasicEqualTo(String value) {
            addCriterion("IF_BASIC =", value, "ifBasic");
            return (Criteria) this;
        }

        public Criteria andIfBasicNotEqualTo(String value) {
            addCriterion("IF_BASIC <>", value, "ifBasic");
            return (Criteria) this;
        }

        public Criteria andIfBasicGreaterThan(String value) {
            addCriterion("IF_BASIC >", value, "ifBasic");
            return (Criteria) this;
        }

        public Criteria andIfBasicGreaterThanOrEqualTo(String value) {
            addCriterion("IF_BASIC >=", value, "ifBasic");
            return (Criteria) this;
        }

        public Criteria andIfBasicLessThan(String value) {
            addCriterion("IF_BASIC <", value, "ifBasic");
            return (Criteria) this;
        }

        public Criteria andIfBasicLessThanOrEqualTo(String value) {
            addCriterion("IF_BASIC <=", value, "ifBasic");
            return (Criteria) this;
        }

        public Criteria andIfBasicLike(String value) {
            addCriterion("IF_BASIC like", value, "ifBasic");
            return (Criteria) this;
        }

        public Criteria andIfBasicNotLike(String value) {
            addCriterion("IF_BASIC not like", value, "ifBasic");
            return (Criteria) this;
        }

        public Criteria andIfBasicIn(List<String> values) {
            addCriterion("IF_BASIC in", values, "ifBasic");
            return (Criteria) this;
        }

        public Criteria andIfBasicNotIn(List<String> values) {
            addCriterion("IF_BASIC not in", values, "ifBasic");
            return (Criteria) this;
        }

        public Criteria andIfBasicBetween(String value1, String value2) {
            addCriterion("IF_BASIC between", value1, value2, "ifBasic");
            return (Criteria) this;
        }

        public Criteria andIfBasicNotBetween(String value1, String value2) {
            addCriterion("IF_BASIC not between", value1, value2, "ifBasic");
            return (Criteria) this;
        }

        public Criteria andIfHavetoIsNull() {
            addCriterion("IF_HAVETO is null");
            return (Criteria) this;
        }

        public Criteria andIfHavetoIsNotNull() {
            addCriterion("IF_HAVETO is not null");
            return (Criteria) this;
        }

        public Criteria andIfHavetoEqualTo(String value) {
            addCriterion("IF_HAVETO =", value, "ifHaveto");
            return (Criteria) this;
        }

        public Criteria andIfHavetoNotEqualTo(String value) {
            addCriterion("IF_HAVETO <>", value, "ifHaveto");
            return (Criteria) this;
        }

        public Criteria andIfHavetoGreaterThan(String value) {
            addCriterion("IF_HAVETO >", value, "ifHaveto");
            return (Criteria) this;
        }

        public Criteria andIfHavetoGreaterThanOrEqualTo(String value) {
            addCriterion("IF_HAVETO >=", value, "ifHaveto");
            return (Criteria) this;
        }

        public Criteria andIfHavetoLessThan(String value) {
            addCriterion("IF_HAVETO <", value, "ifHaveto");
            return (Criteria) this;
        }

        public Criteria andIfHavetoLessThanOrEqualTo(String value) {
            addCriterion("IF_HAVETO <=", value, "ifHaveto");
            return (Criteria) this;
        }

        public Criteria andIfHavetoLike(String value) {
            addCriterion("IF_HAVETO like", value, "ifHaveto");
            return (Criteria) this;
        }

        public Criteria andIfHavetoNotLike(String value) {
            addCriterion("IF_HAVETO not like", value, "ifHaveto");
            return (Criteria) this;
        }

        public Criteria andIfHavetoIn(List<String> values) {
            addCriterion("IF_HAVETO in", values, "ifHaveto");
            return (Criteria) this;
        }

        public Criteria andIfHavetoNotIn(List<String> values) {
            addCriterion("IF_HAVETO not in", values, "ifHaveto");
            return (Criteria) this;
        }

        public Criteria andIfHavetoBetween(String value1, String value2) {
            addCriterion("IF_HAVETO between", value1, value2, "ifHaveto");
            return (Criteria) this;
        }

        public Criteria andIfHavetoNotBetween(String value1, String value2) {
            addCriterion("IF_HAVETO not between", value1, value2, "ifHaveto");
            return (Criteria) this;
        }

        public Criteria andIfSearchIsNull() {
            addCriterion("IF_SEARCH is null");
            return (Criteria) this;
        }

        public Criteria andIfSearchIsNotNull() {
            addCriterion("IF_SEARCH is not null");
            return (Criteria) this;
        }

        public Criteria andIfSearchEqualTo(String value) {
            addCriterion("IF_SEARCH =", value, "ifSearch");
            return (Criteria) this;
        }

        public Criteria andIfSearchNotEqualTo(String value) {
            addCriterion("IF_SEARCH <>", value, "ifSearch");
            return (Criteria) this;
        }

        public Criteria andIfSearchGreaterThan(String value) {
            addCriterion("IF_SEARCH >", value, "ifSearch");
            return (Criteria) this;
        }

        public Criteria andIfSearchGreaterThanOrEqualTo(String value) {
            addCriterion("IF_SEARCH >=", value, "ifSearch");
            return (Criteria) this;
        }

        public Criteria andIfSearchLessThan(String value) {
            addCriterion("IF_SEARCH <", value, "ifSearch");
            return (Criteria) this;
        }

        public Criteria andIfSearchLessThanOrEqualTo(String value) {
            addCriterion("IF_SEARCH <=", value, "ifSearch");
            return (Criteria) this;
        }

        public Criteria andIfSearchLike(String value) {
            addCriterion("IF_SEARCH like", value, "ifSearch");
            return (Criteria) this;
        }

        public Criteria andIfSearchNotLike(String value) {
            addCriterion("IF_SEARCH not like", value, "ifSearch");
            return (Criteria) this;
        }

        public Criteria andIfSearchIn(List<String> values) {
            addCriterion("IF_SEARCH in", values, "ifSearch");
            return (Criteria) this;
        }

        public Criteria andIfSearchNotIn(List<String> values) {
            addCriterion("IF_SEARCH not in", values, "ifSearch");
            return (Criteria) this;
        }

        public Criteria andIfSearchBetween(String value1, String value2) {
            addCriterion("IF_SEARCH between", value1, value2, "ifSearch");
            return (Criteria) this;
        }

        public Criteria andIfSearchNotBetween(String value1, String value2) {
            addCriterion("IF_SEARCH not between", value1, value2, "ifSearch");
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

        public Criteria andIfGdsInputIsNull() {
            addCriterion("IF_GDS_INPUT is null");
            return (Criteria) this;
        }

        public Criteria andIfGdsInputIsNotNull() {
            addCriterion("IF_GDS_INPUT is not null");
            return (Criteria) this;
        }

        public Criteria andIfGdsInputEqualTo(String value) {
            addCriterion("IF_GDS_INPUT =", value, "ifGdsInput");
            return (Criteria) this;
        }

        public Criteria andIfGdsInputNotEqualTo(String value) {
            addCriterion("IF_GDS_INPUT <>", value, "ifGdsInput");
            return (Criteria) this;
        }

        public Criteria andIfGdsInputGreaterThan(String value) {
            addCriterion("IF_GDS_INPUT >", value, "ifGdsInput");
            return (Criteria) this;
        }

        public Criteria andIfGdsInputGreaterThanOrEqualTo(String value) {
            addCriterion("IF_GDS_INPUT >=", value, "ifGdsInput");
            return (Criteria) this;
        }

        public Criteria andIfGdsInputLessThan(String value) {
            addCriterion("IF_GDS_INPUT <", value, "ifGdsInput");
            return (Criteria) this;
        }

        public Criteria andIfGdsInputLessThanOrEqualTo(String value) {
            addCriterion("IF_GDS_INPUT <=", value, "ifGdsInput");
            return (Criteria) this;
        }

        public Criteria andIfGdsInputLike(String value) {
            addCriterion("IF_GDS_INPUT like", value, "ifGdsInput");
            return (Criteria) this;
        }

        public Criteria andIfGdsInputNotLike(String value) {
            addCriterion("IF_GDS_INPUT not like", value, "ifGdsInput");
            return (Criteria) this;
        }

        public Criteria andIfGdsInputIn(List<String> values) {
            addCriterion("IF_GDS_INPUT in", values, "ifGdsInput");
            return (Criteria) this;
        }

        public Criteria andIfGdsInputNotIn(List<String> values) {
            addCriterion("IF_GDS_INPUT not in", values, "ifGdsInput");
            return (Criteria) this;
        }

        public Criteria andIfGdsInputBetween(String value1, String value2) {
            addCriterion("IF_GDS_INPUT between", value1, value2, "ifGdsInput");
            return (Criteria) this;
        }

        public Criteria andIfGdsInputNotBetween(String value1, String value2) {
            addCriterion("IF_GDS_INPUT not between", value1, value2, "ifGdsInput");
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
