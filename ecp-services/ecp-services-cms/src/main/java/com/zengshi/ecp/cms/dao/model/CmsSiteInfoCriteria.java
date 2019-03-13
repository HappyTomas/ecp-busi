package com.zengshi.ecp.cms.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CmsSiteInfoCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CmsSiteInfoCriteria() {
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

        public Criteria andSiteInfoNameIsNull() {
            addCriterion("SITE_INFO_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSiteInfoNameIsNotNull() {
            addCriterion("SITE_INFO_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSiteInfoNameEqualTo(String value) {
            addCriterion("SITE_INFO_NAME =", value, "siteInfoName");
            return (Criteria) this;
        }

        public Criteria andSiteInfoNameNotEqualTo(String value) {
            addCriterion("SITE_INFO_NAME <>", value, "siteInfoName");
            return (Criteria) this;
        }

        public Criteria andSiteInfoNameGreaterThan(String value) {
            addCriterion("SITE_INFO_NAME >", value, "siteInfoName");
            return (Criteria) this;
        }

        public Criteria andSiteInfoNameGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_INFO_NAME >=", value, "siteInfoName");
            return (Criteria) this;
        }

        public Criteria andSiteInfoNameLessThan(String value) {
            addCriterion("SITE_INFO_NAME <", value, "siteInfoName");
            return (Criteria) this;
        }

        public Criteria andSiteInfoNameLessThanOrEqualTo(String value) {
            addCriterion("SITE_INFO_NAME <=", value, "siteInfoName");
            return (Criteria) this;
        }

        public Criteria andSiteInfoNameLike(String value) {
            addCriterion("SITE_INFO_NAME like", value, "siteInfoName");
            return (Criteria) this;
        }

        public Criteria andSiteInfoNameNotLike(String value) {
            addCriterion("SITE_INFO_NAME not like", value, "siteInfoName");
            return (Criteria) this;
        }

        public Criteria andSiteInfoNameIn(List<String> values) {
            addCriterion("SITE_INFO_NAME in", values, "siteInfoName");
            return (Criteria) this;
        }

        public Criteria andSiteInfoNameNotIn(List<String> values) {
            addCriterion("SITE_INFO_NAME not in", values, "siteInfoName");
            return (Criteria) this;
        }

        public Criteria andSiteInfoNameBetween(String value1, String value2) {
            addCriterion("SITE_INFO_NAME between", value1, value2, "siteInfoName");
            return (Criteria) this;
        }

        public Criteria andSiteInfoNameNotBetween(String value1, String value2) {
            addCriterion("SITE_INFO_NAME not between", value1, value2, "siteInfoName");
            return (Criteria) this;
        }

        public Criteria andSiteIdIsNull() {
            addCriterion("SITE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSiteIdIsNotNull() {
            addCriterion("SITE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSiteIdEqualTo(Long value) {
            addCriterion("SITE_ID =", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotEqualTo(Long value) {
            addCriterion("SITE_ID <>", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThan(Long value) {
            addCriterion("SITE_ID >", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SITE_ID >=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThan(Long value) {
            addCriterion("SITE_ID <", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThanOrEqualTo(Long value) {
            addCriterion("SITE_ID <=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdIn(List<Long> values) {
            addCriterion("SITE_ID in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotIn(List<Long> values) {
            addCriterion("SITE_ID not in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdBetween(Long value1, Long value2) {
            addCriterion("SITE_ID between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotBetween(Long value1, Long value2) {
            addCriterion("SITE_ID not between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteInfoTypeIsNull() {
            addCriterion("SITE_INFO_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSiteInfoTypeIsNotNull() {
            addCriterion("SITE_INFO_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSiteInfoTypeEqualTo(String value) {
            addCriterion("SITE_INFO_TYPE =", value, "siteInfoType");
            return (Criteria) this;
        }

        public Criteria andSiteInfoTypeNotEqualTo(String value) {
            addCriterion("SITE_INFO_TYPE <>", value, "siteInfoType");
            return (Criteria) this;
        }

        public Criteria andSiteInfoTypeGreaterThan(String value) {
            addCriterion("SITE_INFO_TYPE >", value, "siteInfoType");
            return (Criteria) this;
        }

        public Criteria andSiteInfoTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_INFO_TYPE >=", value, "siteInfoType");
            return (Criteria) this;
        }

        public Criteria andSiteInfoTypeLessThan(String value) {
            addCriterion("SITE_INFO_TYPE <", value, "siteInfoType");
            return (Criteria) this;
        }

        public Criteria andSiteInfoTypeLessThanOrEqualTo(String value) {
            addCriterion("SITE_INFO_TYPE <=", value, "siteInfoType");
            return (Criteria) this;
        }

        public Criteria andSiteInfoTypeLike(String value) {
            addCriterion("SITE_INFO_TYPE like", value, "siteInfoType");
            return (Criteria) this;
        }

        public Criteria andSiteInfoTypeNotLike(String value) {
            addCriterion("SITE_INFO_TYPE not like", value, "siteInfoType");
            return (Criteria) this;
        }

        public Criteria andSiteInfoTypeIn(List<String> values) {
            addCriterion("SITE_INFO_TYPE in", values, "siteInfoType");
            return (Criteria) this;
        }

        public Criteria andSiteInfoTypeNotIn(List<String> values) {
            addCriterion("SITE_INFO_TYPE not in", values, "siteInfoType");
            return (Criteria) this;
        }

        public Criteria andSiteInfoTypeBetween(String value1, String value2) {
            addCriterion("SITE_INFO_TYPE between", value1, value2, "siteInfoType");
            return (Criteria) this;
        }

        public Criteria andSiteInfoTypeNotBetween(String value1, String value2) {
            addCriterion("SITE_INFO_TYPE not between", value1, value2, "siteInfoType");
            return (Criteria) this;
        }

        public Criteria andSiteInfoUrlIsNull() {
            addCriterion("SITE_INFO_URL is null");
            return (Criteria) this;
        }

        public Criteria andSiteInfoUrlIsNotNull() {
            addCriterion("SITE_INFO_URL is not null");
            return (Criteria) this;
        }

        public Criteria andSiteInfoUrlEqualTo(String value) {
            addCriterion("SITE_INFO_URL =", value, "siteInfoUrl");
            return (Criteria) this;
        }

        public Criteria andSiteInfoUrlNotEqualTo(String value) {
            addCriterion("SITE_INFO_URL <>", value, "siteInfoUrl");
            return (Criteria) this;
        }

        public Criteria andSiteInfoUrlGreaterThan(String value) {
            addCriterion("SITE_INFO_URL >", value, "siteInfoUrl");
            return (Criteria) this;
        }

        public Criteria andSiteInfoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_INFO_URL >=", value, "siteInfoUrl");
            return (Criteria) this;
        }

        public Criteria andSiteInfoUrlLessThan(String value) {
            addCriterion("SITE_INFO_URL <", value, "siteInfoUrl");
            return (Criteria) this;
        }

        public Criteria andSiteInfoUrlLessThanOrEqualTo(String value) {
            addCriterion("SITE_INFO_URL <=", value, "siteInfoUrl");
            return (Criteria) this;
        }

        public Criteria andSiteInfoUrlLike(String value) {
            addCriterion("SITE_INFO_URL like", value, "siteInfoUrl");
            return (Criteria) this;
        }

        public Criteria andSiteInfoUrlNotLike(String value) {
            addCriterion("SITE_INFO_URL not like", value, "siteInfoUrl");
            return (Criteria) this;
        }

        public Criteria andSiteInfoUrlIn(List<String> values) {
            addCriterion("SITE_INFO_URL in", values, "siteInfoUrl");
            return (Criteria) this;
        }

        public Criteria andSiteInfoUrlNotIn(List<String> values) {
            addCriterion("SITE_INFO_URL not in", values, "siteInfoUrl");
            return (Criteria) this;
        }

        public Criteria andSiteInfoUrlBetween(String value1, String value2) {
            addCriterion("SITE_INFO_URL between", value1, value2, "siteInfoUrl");
            return (Criteria) this;
        }

        public Criteria andSiteInfoUrlNotBetween(String value1, String value2) {
            addCriterion("SITE_INFO_URL not between", value1, value2, "siteInfoUrl");
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

        public Criteria andCreateStaffEqualTo(String value) {
            addCriterion("CREATE_STAFF =", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotEqualTo(String value) {
            addCriterion("CREATE_STAFF <>", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThan(String value) {
            addCriterion("CREATE_STAFF >", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_STAFF >=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThan(String value) {
            addCriterion("CREATE_STAFF <", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThanOrEqualTo(String value) {
            addCriterion("CREATE_STAFF <=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLike(String value) {
            addCriterion("CREATE_STAFF like", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotLike(String value) {
            addCriterion("CREATE_STAFF not like", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIn(List<String> values) {
            addCriterion("CREATE_STAFF in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotIn(List<String> values) {
            addCriterion("CREATE_STAFF not in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffBetween(String value1, String value2) {
            addCriterion("CREATE_STAFF between", value1, value2, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotBetween(String value1, String value2) {
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

        public Criteria andUpdateStaffEqualTo(String value) {
            addCriterion("UPDATE_STAFF =", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotEqualTo(String value) {
            addCriterion("UPDATE_STAFF <>", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThan(String value) {
            addCriterion("UPDATE_STAFF >", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_STAFF >=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThan(String value) {
            addCriterion("UPDATE_STAFF <", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_STAFF <=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLike(String value) {
            addCriterion("UPDATE_STAFF like", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotLike(String value) {
            addCriterion("UPDATE_STAFF not like", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIn(List<String> values) {
            addCriterion("UPDATE_STAFF in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotIn(List<String> values) {
            addCriterion("UPDATE_STAFF not in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffBetween(String value1, String value2) {
            addCriterion("UPDATE_STAFF between", value1, value2, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotBetween(String value1, String value2) {
            addCriterion("UPDATE_STAFF not between", value1, value2, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andStaticIdIsNull() {
            addCriterion("STATIC_ID is null");
            return (Criteria) this;
        }

        public Criteria andStaticIdIsNotNull() {
            addCriterion("STATIC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStaticIdEqualTo(String value) {
            addCriterion("STATIC_ID =", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdNotEqualTo(String value) {
            addCriterion("STATIC_ID <>", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdGreaterThan(String value) {
            addCriterion("STATIC_ID >", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdGreaterThanOrEqualTo(String value) {
            addCriterion("STATIC_ID >=", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdLessThan(String value) {
            addCriterion("STATIC_ID <", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdLessThanOrEqualTo(String value) {
            addCriterion("STATIC_ID <=", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdLike(String value) {
            addCriterion("STATIC_ID like", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdNotLike(String value) {
            addCriterion("STATIC_ID not like", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdIn(List<String> values) {
            addCriterion("STATIC_ID in", values, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdNotIn(List<String> values) {
            addCriterion("STATIC_ID not in", values, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdBetween(String value1, String value2) {
            addCriterion("STATIC_ID between", value1, value2, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdNotBetween(String value1, String value2) {
            addCriterion("STATIC_ID not between", value1, value2, "staticId");
            return (Criteria) this;
        }

        public Criteria andParentIsNull() {
            addCriterion("PARENT is null");
            return (Criteria) this;
        }

        public Criteria andParentIsNotNull() {
            addCriterion("PARENT is not null");
            return (Criteria) this;
        }

        public Criteria andParentEqualTo(Long value) {
            addCriterion("PARENT =", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotEqualTo(Long value) {
            addCriterion("PARENT <>", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentGreaterThan(Long value) {
            addCriterion("PARENT >", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentGreaterThanOrEqualTo(Long value) {
            addCriterion("PARENT >=", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentLessThan(Long value) {
            addCriterion("PARENT <", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentLessThanOrEqualTo(Long value) {
            addCriterion("PARENT <=", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentIn(List<Long> values) {
            addCriterion("PARENT in", values, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotIn(List<Long> values) {
            addCriterion("PARENT not in", values, "parent");
            return (Criteria) this;
        }

        public Criteria andParentBetween(Long value1, Long value2) {
            addCriterion("PARENT between", value1, value2, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotBetween(Long value1, Long value2) {
            addCriterion("PARENT not between", value1, value2, "parent");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNull() {
            addCriterion("CHANNEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("CHANNEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(String value) {
            addCriterion("CHANNEL_ID =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(String value) {
            addCriterion("CHANNEL_ID <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(String value) {
            addCriterion("CHANNEL_ID >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(String value) {
            addCriterion("CHANNEL_ID >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(String value) {
            addCriterion("CHANNEL_ID <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(String value) {
            addCriterion("CHANNEL_ID <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLike(String value) {
            addCriterion("CHANNEL_ID like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotLike(String value) {
            addCriterion("CHANNEL_ID not like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<String> values) {
            addCriterion("CHANNEL_ID in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<String> values) {
            addCriterion("CHANNEL_ID not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(String value1, String value2) {
            addCriterion("CHANNEL_ID between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(String value1, String value2) {
            addCriterion("CHANNEL_ID not between", value1, value2, "channelId");
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
