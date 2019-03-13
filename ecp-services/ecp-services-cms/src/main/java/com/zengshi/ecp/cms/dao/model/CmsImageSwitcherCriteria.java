package com.zengshi.ecp.cms.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CmsImageSwitcherCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CmsImageSwitcherCriteria() {
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

        public Criteria andImNameIsNull() {
            addCriterion("IM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andImNameIsNotNull() {
            addCriterion("IM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andImNameEqualTo(String value) {
            addCriterion("IM_NAME =", value, "imName");
            return (Criteria) this;
        }

        public Criteria andImNameNotEqualTo(String value) {
            addCriterion("IM_NAME <>", value, "imName");
            return (Criteria) this;
        }

        public Criteria andImNameGreaterThan(String value) {
            addCriterion("IM_NAME >", value, "imName");
            return (Criteria) this;
        }

        public Criteria andImNameGreaterThanOrEqualTo(String value) {
            addCriterion("IM_NAME >=", value, "imName");
            return (Criteria) this;
        }

        public Criteria andImNameLessThan(String value) {
            addCriterion("IM_NAME <", value, "imName");
            return (Criteria) this;
        }

        public Criteria andImNameLessThanOrEqualTo(String value) {
            addCriterion("IM_NAME <=", value, "imName");
            return (Criteria) this;
        }

        public Criteria andImNameLike(String value) {
            addCriterion("IM_NAME like", value, "imName");
            return (Criteria) this;
        }

        public Criteria andImNameNotLike(String value) {
            addCriterion("IM_NAME not like", value, "imName");
            return (Criteria) this;
        }

        public Criteria andImNameIn(List<String> values) {
            addCriterion("IM_NAME in", values, "imName");
            return (Criteria) this;
        }

        public Criteria andImNameNotIn(List<String> values) {
            addCriterion("IM_NAME not in", values, "imName");
            return (Criteria) this;
        }

        public Criteria andImNameBetween(String value1, String value2) {
            addCriterion("IM_NAME between", value1, value2, "imName");
            return (Criteria) this;
        }

        public Criteria andImNameNotBetween(String value1, String value2) {
            addCriterion("IM_NAME not between", value1, value2, "imName");
            return (Criteria) this;
        }

        public Criteria andDescribeInfoIsNull() {
            addCriterion("DESCRIBE_INFO is null");
            return (Criteria) this;
        }

        public Criteria andDescribeInfoIsNotNull() {
            addCriterion("DESCRIBE_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andDescribeInfoEqualTo(String value) {
            addCriterion("DESCRIBE_INFO =", value, "describeInfo");
            return (Criteria) this;
        }

        public Criteria andDescribeInfoNotEqualTo(String value) {
            addCriterion("DESCRIBE_INFO <>", value, "describeInfo");
            return (Criteria) this;
        }

        public Criteria andDescribeInfoGreaterThan(String value) {
            addCriterion("DESCRIBE_INFO >", value, "describeInfo");
            return (Criteria) this;
        }

        public Criteria andDescribeInfoGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIBE_INFO >=", value, "describeInfo");
            return (Criteria) this;
        }

        public Criteria andDescribeInfoLessThan(String value) {
            addCriterion("DESCRIBE_INFO <", value, "describeInfo");
            return (Criteria) this;
        }

        public Criteria andDescribeInfoLessThanOrEqualTo(String value) {
            addCriterion("DESCRIBE_INFO <=", value, "describeInfo");
            return (Criteria) this;
        }

        public Criteria andDescribeInfoLike(String value) {
            addCriterion("DESCRIBE_INFO like", value, "describeInfo");
            return (Criteria) this;
        }

        public Criteria andDescribeInfoNotLike(String value) {
            addCriterion("DESCRIBE_INFO not like", value, "describeInfo");
            return (Criteria) this;
        }

        public Criteria andDescribeInfoIn(List<String> values) {
            addCriterion("DESCRIBE_INFO in", values, "describeInfo");
            return (Criteria) this;
        }

        public Criteria andDescribeInfoNotIn(List<String> values) {
            addCriterion("DESCRIBE_INFO not in", values, "describeInfo");
            return (Criteria) this;
        }

        public Criteria andDescribeInfoBetween(String value1, String value2) {
            addCriterion("DESCRIBE_INFO between", value1, value2, "describeInfo");
            return (Criteria) this;
        }

        public Criteria andDescribeInfoNotBetween(String value1, String value2) {
            addCriterion("DESCRIBE_INFO not between", value1, value2, "describeInfo");
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

        public Criteria andOnePicIsNull() {
            addCriterion("ONE_PIC is null");
            return (Criteria) this;
        }

        public Criteria andOnePicIsNotNull() {
            addCriterion("ONE_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andOnePicEqualTo(String value) {
            addCriterion("ONE_PIC =", value, "onePic");
            return (Criteria) this;
        }

        public Criteria andOnePicNotEqualTo(String value) {
            addCriterion("ONE_PIC <>", value, "onePic");
            return (Criteria) this;
        }

        public Criteria andOnePicGreaterThan(String value) {
            addCriterion("ONE_PIC >", value, "onePic");
            return (Criteria) this;
        }

        public Criteria andOnePicGreaterThanOrEqualTo(String value) {
            addCriterion("ONE_PIC >=", value, "onePic");
            return (Criteria) this;
        }

        public Criteria andOnePicLessThan(String value) {
            addCriterion("ONE_PIC <", value, "onePic");
            return (Criteria) this;
        }

        public Criteria andOnePicLessThanOrEqualTo(String value) {
            addCriterion("ONE_PIC <=", value, "onePic");
            return (Criteria) this;
        }

        public Criteria andOnePicLike(String value) {
            addCriterion("ONE_PIC like", value, "onePic");
            return (Criteria) this;
        }

        public Criteria andOnePicNotLike(String value) {
            addCriterion("ONE_PIC not like", value, "onePic");
            return (Criteria) this;
        }

        public Criteria andOnePicIn(List<String> values) {
            addCriterion("ONE_PIC in", values, "onePic");
            return (Criteria) this;
        }

        public Criteria andOnePicNotIn(List<String> values) {
            addCriterion("ONE_PIC not in", values, "onePic");
            return (Criteria) this;
        }

        public Criteria andOnePicBetween(String value1, String value2) {
            addCriterion("ONE_PIC between", value1, value2, "onePic");
            return (Criteria) this;
        }

        public Criteria andOnePicNotBetween(String value1, String value2) {
            addCriterion("ONE_PIC not between", value1, value2, "onePic");
            return (Criteria) this;
        }

        public Criteria andTwicePicIsNull() {
            addCriterion("TWICE_PIC is null");
            return (Criteria) this;
        }

        public Criteria andTwicePicIsNotNull() {
            addCriterion("TWICE_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andTwicePicEqualTo(String value) {
            addCriterion("TWICE_PIC =", value, "twicePic");
            return (Criteria) this;
        }

        public Criteria andTwicePicNotEqualTo(String value) {
            addCriterion("TWICE_PIC <>", value, "twicePic");
            return (Criteria) this;
        }

        public Criteria andTwicePicGreaterThan(String value) {
            addCriterion("TWICE_PIC >", value, "twicePic");
            return (Criteria) this;
        }

        public Criteria andTwicePicGreaterThanOrEqualTo(String value) {
            addCriterion("TWICE_PIC >=", value, "twicePic");
            return (Criteria) this;
        }

        public Criteria andTwicePicLessThan(String value) {
            addCriterion("TWICE_PIC <", value, "twicePic");
            return (Criteria) this;
        }

        public Criteria andTwicePicLessThanOrEqualTo(String value) {
            addCriterion("TWICE_PIC <=", value, "twicePic");
            return (Criteria) this;
        }

        public Criteria andTwicePicLike(String value) {
            addCriterion("TWICE_PIC like", value, "twicePic");
            return (Criteria) this;
        }

        public Criteria andTwicePicNotLike(String value) {
            addCriterion("TWICE_PIC not like", value, "twicePic");
            return (Criteria) this;
        }

        public Criteria andTwicePicIn(List<String> values) {
            addCriterion("TWICE_PIC in", values, "twicePic");
            return (Criteria) this;
        }

        public Criteria andTwicePicNotIn(List<String> values) {
            addCriterion("TWICE_PIC not in", values, "twicePic");
            return (Criteria) this;
        }

        public Criteria andTwicePicBetween(String value1, String value2) {
            addCriterion("TWICE_PIC between", value1, value2, "twicePic");
            return (Criteria) this;
        }

        public Criteria andTwicePicNotBetween(String value1, String value2) {
            addCriterion("TWICE_PIC not between", value1, value2, "twicePic");
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

        public Criteria andTemplateIdIsNull() {
            addCriterion("TEMPLATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("TEMPLATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(Long value) {
            addCriterion("TEMPLATE_ID =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(Long value) {
            addCriterion("TEMPLATE_ID <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(Long value) {
            addCriterion("TEMPLATE_ID >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TEMPLATE_ID >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(Long value) {
            addCriterion("TEMPLATE_ID <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(Long value) {
            addCriterion("TEMPLATE_ID <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<Long> values) {
            addCriterion("TEMPLATE_ID in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<Long> values) {
            addCriterion("TEMPLATE_ID not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(Long value1, Long value2) {
            addCriterion("TEMPLATE_ID between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(Long value1, Long value2) {
            addCriterion("TEMPLATE_ID not between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdIsNull() {
            addCriterion("PLACE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPlaceIdIsNotNull() {
            addCriterion("PLACE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceIdEqualTo(Long value) {
            addCriterion("PLACE_ID =", value, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdNotEqualTo(Long value) {
            addCriterion("PLACE_ID <>", value, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdGreaterThan(Long value) {
            addCriterion("PLACE_ID >", value, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PLACE_ID >=", value, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdLessThan(Long value) {
            addCriterion("PLACE_ID <", value, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdLessThanOrEqualTo(Long value) {
            addCriterion("PLACE_ID <=", value, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdIn(List<Long> values) {
            addCriterion("PLACE_ID in", values, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdNotIn(List<Long> values) {
            addCriterion("PLACE_ID not in", values, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdBetween(Long value1, Long value2) {
            addCriterion("PLACE_ID between", value1, value2, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdNotBetween(Long value1, Long value2) {
            addCriterion("PLACE_ID not between", value1, value2, "placeId");
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

        public Criteria andTwicePicNameIsNull() {
            addCriterion("TWICE_PIC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTwicePicNameIsNotNull() {
            addCriterion("TWICE_PIC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTwicePicNameEqualTo(String value) {
            addCriterion("TWICE_PIC_NAME =", value, "twicePicName");
            return (Criteria) this;
        }

        public Criteria andTwicePicNameNotEqualTo(String value) {
            addCriterion("TWICE_PIC_NAME <>", value, "twicePicName");
            return (Criteria) this;
        }

        public Criteria andTwicePicNameGreaterThan(String value) {
            addCriterion("TWICE_PIC_NAME >", value, "twicePicName");
            return (Criteria) this;
        }

        public Criteria andTwicePicNameGreaterThanOrEqualTo(String value) {
            addCriterion("TWICE_PIC_NAME >=", value, "twicePicName");
            return (Criteria) this;
        }

        public Criteria andTwicePicNameLessThan(String value) {
            addCriterion("TWICE_PIC_NAME <", value, "twicePicName");
            return (Criteria) this;
        }

        public Criteria andTwicePicNameLessThanOrEqualTo(String value) {
            addCriterion("TWICE_PIC_NAME <=", value, "twicePicName");
            return (Criteria) this;
        }

        public Criteria andTwicePicNameLike(String value) {
            addCriterion("TWICE_PIC_NAME like", value, "twicePicName");
            return (Criteria) this;
        }

        public Criteria andTwicePicNameNotLike(String value) {
            addCriterion("TWICE_PIC_NAME not like", value, "twicePicName");
            return (Criteria) this;
        }

        public Criteria andTwicePicNameIn(List<String> values) {
            addCriterion("TWICE_PIC_NAME in", values, "twicePicName");
            return (Criteria) this;
        }

        public Criteria andTwicePicNameNotIn(List<String> values) {
            addCriterion("TWICE_PIC_NAME not in", values, "twicePicName");
            return (Criteria) this;
        }

        public Criteria andTwicePicNameBetween(String value1, String value2) {
            addCriterion("TWICE_PIC_NAME between", value1, value2, "twicePicName");
            return (Criteria) this;
        }

        public Criteria andTwicePicNameNotBetween(String value1, String value2) {
            addCriterion("TWICE_PIC_NAME not between", value1, value2, "twicePicName");
            return (Criteria) this;
        }

        public Criteria andOnePicNameIsNull() {
            addCriterion("ONE_PIC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOnePicNameIsNotNull() {
            addCriterion("ONE_PIC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOnePicNameEqualTo(String value) {
            addCriterion("ONE_PIC_NAME =", value, "onePicName");
            return (Criteria) this;
        }

        public Criteria andOnePicNameNotEqualTo(String value) {
            addCriterion("ONE_PIC_NAME <>", value, "onePicName");
            return (Criteria) this;
        }

        public Criteria andOnePicNameGreaterThan(String value) {
            addCriterion("ONE_PIC_NAME >", value, "onePicName");
            return (Criteria) this;
        }

        public Criteria andOnePicNameGreaterThanOrEqualTo(String value) {
            addCriterion("ONE_PIC_NAME >=", value, "onePicName");
            return (Criteria) this;
        }

        public Criteria andOnePicNameLessThan(String value) {
            addCriterion("ONE_PIC_NAME <", value, "onePicName");
            return (Criteria) this;
        }

        public Criteria andOnePicNameLessThanOrEqualTo(String value) {
            addCriterion("ONE_PIC_NAME <=", value, "onePicName");
            return (Criteria) this;
        }

        public Criteria andOnePicNameLike(String value) {
            addCriterion("ONE_PIC_NAME like", value, "onePicName");
            return (Criteria) this;
        }

        public Criteria andOnePicNameNotLike(String value) {
            addCriterion("ONE_PIC_NAME not like", value, "onePicName");
            return (Criteria) this;
        }

        public Criteria andOnePicNameIn(List<String> values) {
            addCriterion("ONE_PIC_NAME in", values, "onePicName");
            return (Criteria) this;
        }

        public Criteria andOnePicNameNotIn(List<String> values) {
            addCriterion("ONE_PIC_NAME not in", values, "onePicName");
            return (Criteria) this;
        }

        public Criteria andOnePicNameBetween(String value1, String value2) {
            addCriterion("ONE_PIC_NAME between", value1, value2, "onePicName");
            return (Criteria) this;
        }

        public Criteria andOnePicNameNotBetween(String value1, String value2) {
            addCriterion("ONE_PIC_NAME not between", value1, value2, "onePicName");
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
