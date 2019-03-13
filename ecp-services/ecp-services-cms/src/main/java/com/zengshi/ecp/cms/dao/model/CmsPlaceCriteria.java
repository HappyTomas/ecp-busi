package com.zengshi.ecp.cms.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CmsPlaceCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CmsPlaceCriteria() {
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

        public Criteria andPlaceNameIsNull() {
            addCriterion("PLACE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPlaceNameIsNotNull() {
            addCriterion("PLACE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceNameEqualTo(String value) {
            addCriterion("PLACE_NAME =", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameNotEqualTo(String value) {
            addCriterion("PLACE_NAME <>", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameGreaterThan(String value) {
            addCriterion("PLACE_NAME >", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameGreaterThanOrEqualTo(String value) {
            addCriterion("PLACE_NAME >=", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameLessThan(String value) {
            addCriterion("PLACE_NAME <", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameLessThanOrEqualTo(String value) {
            addCriterion("PLACE_NAME <=", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameLike(String value) {
            addCriterion("PLACE_NAME like", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameNotLike(String value) {
            addCriterion("PLACE_NAME not like", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameIn(List<String> values) {
            addCriterion("PLACE_NAME in", values, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameNotIn(List<String> values) {
            addCriterion("PLACE_NAME not in", values, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameBetween(String value1, String value2) {
            addCriterion("PLACE_NAME between", value1, value2, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameNotBetween(String value1, String value2) {
            addCriterion("PLACE_NAME not between", value1, value2, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceTypeIsNull() {
            addCriterion("PLACE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPlaceTypeIsNotNull() {
            addCriterion("PLACE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceTypeEqualTo(String value) {
            addCriterion("PLACE_TYPE =", value, "placeType");
            return (Criteria) this;
        }

        public Criteria andPlaceTypeNotEqualTo(String value) {
            addCriterion("PLACE_TYPE <>", value, "placeType");
            return (Criteria) this;
        }

        public Criteria andPlaceTypeGreaterThan(String value) {
            addCriterion("PLACE_TYPE >", value, "placeType");
            return (Criteria) this;
        }

        public Criteria andPlaceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PLACE_TYPE >=", value, "placeType");
            return (Criteria) this;
        }

        public Criteria andPlaceTypeLessThan(String value) {
            addCriterion("PLACE_TYPE <", value, "placeType");
            return (Criteria) this;
        }

        public Criteria andPlaceTypeLessThanOrEqualTo(String value) {
            addCriterion("PLACE_TYPE <=", value, "placeType");
            return (Criteria) this;
        }

        public Criteria andPlaceTypeLike(String value) {
            addCriterion("PLACE_TYPE like", value, "placeType");
            return (Criteria) this;
        }

        public Criteria andPlaceTypeNotLike(String value) {
            addCriterion("PLACE_TYPE not like", value, "placeType");
            return (Criteria) this;
        }

        public Criteria andPlaceTypeIn(List<String> values) {
            addCriterion("PLACE_TYPE in", values, "placeType");
            return (Criteria) this;
        }

        public Criteria andPlaceTypeNotIn(List<String> values) {
            addCriterion("PLACE_TYPE not in", values, "placeType");
            return (Criteria) this;
        }

        public Criteria andPlaceTypeBetween(String value1, String value2) {
            addCriterion("PLACE_TYPE between", value1, value2, "placeType");
            return (Criteria) this;
        }

        public Criteria andPlaceTypeNotBetween(String value1, String value2) {
            addCriterion("PLACE_TYPE not between", value1, value2, "placeType");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIsNull() {
            addCriterion("LINK_URL is null");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIsNotNull() {
            addCriterion("LINK_URL is not null");
            return (Criteria) this;
        }

        public Criteria andLinkUrlEqualTo(String value) {
            addCriterion("LINK_URL =", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotEqualTo(String value) {
            addCriterion("LINK_URL <>", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlGreaterThan(String value) {
            addCriterion("LINK_URL >", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_URL >=", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLessThan(String value) {
            addCriterion("LINK_URL <", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLessThanOrEqualTo(String value) {
            addCriterion("LINK_URL <=", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLike(String value) {
            addCriterion("LINK_URL like", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotLike(String value) {
            addCriterion("LINK_URL not like", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIn(List<String> values) {
            addCriterion("LINK_URL in", values, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotIn(List<String> values) {
            addCriterion("LINK_URL not in", values, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlBetween(String value1, String value2) {
            addCriterion("LINK_URL between", value1, value2, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotBetween(String value1, String value2) {
            addCriterion("LINK_URL not between", value1, value2, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andPlaceCountIsNull() {
            addCriterion("PLACE_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andPlaceCountIsNotNull() {
            addCriterion("PLACE_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceCountEqualTo(Integer value) {
            addCriterion("PLACE_COUNT =", value, "placeCount");
            return (Criteria) this;
        }

        public Criteria andPlaceCountNotEqualTo(Integer value) {
            addCriterion("PLACE_COUNT <>", value, "placeCount");
            return (Criteria) this;
        }

        public Criteria andPlaceCountGreaterThan(Integer value) {
            addCriterion("PLACE_COUNT >", value, "placeCount");
            return (Criteria) this;
        }

        public Criteria andPlaceCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("PLACE_COUNT >=", value, "placeCount");
            return (Criteria) this;
        }

        public Criteria andPlaceCountLessThan(Integer value) {
            addCriterion("PLACE_COUNT <", value, "placeCount");
            return (Criteria) this;
        }

        public Criteria andPlaceCountLessThanOrEqualTo(Integer value) {
            addCriterion("PLACE_COUNT <=", value, "placeCount");
            return (Criteria) this;
        }

        public Criteria andPlaceCountIn(List<Integer> values) {
            addCriterion("PLACE_COUNT in", values, "placeCount");
            return (Criteria) this;
        }

        public Criteria andPlaceCountNotIn(List<Integer> values) {
            addCriterion("PLACE_COUNT not in", values, "placeCount");
            return (Criteria) this;
        }

        public Criteria andPlaceCountBetween(Integer value1, Integer value2) {
            addCriterion("PLACE_COUNT between", value1, value2, "placeCount");
            return (Criteria) this;
        }

        public Criteria andPlaceCountNotBetween(Integer value1, Integer value2) {
            addCriterion("PLACE_COUNT not between", value1, value2, "placeCount");
            return (Criteria) this;
        }

        public Criteria andPlaceBgcolorIsNull() {
            addCriterion("PLACE_BGCOLOR is null");
            return (Criteria) this;
        }

        public Criteria andPlaceBgcolorIsNotNull() {
            addCriterion("PLACE_BGCOLOR is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceBgcolorEqualTo(String value) {
            addCriterion("PLACE_BGCOLOR =", value, "placeBgcolor");
            return (Criteria) this;
        }

        public Criteria andPlaceBgcolorNotEqualTo(String value) {
            addCriterion("PLACE_BGCOLOR <>", value, "placeBgcolor");
            return (Criteria) this;
        }

        public Criteria andPlaceBgcolorGreaterThan(String value) {
            addCriterion("PLACE_BGCOLOR >", value, "placeBgcolor");
            return (Criteria) this;
        }

        public Criteria andPlaceBgcolorGreaterThanOrEqualTo(String value) {
            addCriterion("PLACE_BGCOLOR >=", value, "placeBgcolor");
            return (Criteria) this;
        }

        public Criteria andPlaceBgcolorLessThan(String value) {
            addCriterion("PLACE_BGCOLOR <", value, "placeBgcolor");
            return (Criteria) this;
        }

        public Criteria andPlaceBgcolorLessThanOrEqualTo(String value) {
            addCriterion("PLACE_BGCOLOR <=", value, "placeBgcolor");
            return (Criteria) this;
        }

        public Criteria andPlaceBgcolorLike(String value) {
            addCriterion("PLACE_BGCOLOR like", value, "placeBgcolor");
            return (Criteria) this;
        }

        public Criteria andPlaceBgcolorNotLike(String value) {
            addCriterion("PLACE_BGCOLOR not like", value, "placeBgcolor");
            return (Criteria) this;
        }

        public Criteria andPlaceBgcolorIn(List<String> values) {
            addCriterion("PLACE_BGCOLOR in", values, "placeBgcolor");
            return (Criteria) this;
        }

        public Criteria andPlaceBgcolorNotIn(List<String> values) {
            addCriterion("PLACE_BGCOLOR not in", values, "placeBgcolor");
            return (Criteria) this;
        }

        public Criteria andPlaceBgcolorBetween(String value1, String value2) {
            addCriterion("PLACE_BGCOLOR between", value1, value2, "placeBgcolor");
            return (Criteria) this;
        }

        public Criteria andPlaceBgcolorNotBetween(String value1, String value2) {
            addCriterion("PLACE_BGCOLOR not between", value1, value2, "placeBgcolor");
            return (Criteria) this;
        }

        public Criteria andPlaceWidthIsNull() {
            addCriterion("PLACE_WIDTH is null");
            return (Criteria) this;
        }

        public Criteria andPlaceWidthIsNotNull() {
            addCriterion("PLACE_WIDTH is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceWidthEqualTo(Long value) {
            addCriterion("PLACE_WIDTH =", value, "placeWidth");
            return (Criteria) this;
        }

        public Criteria andPlaceWidthNotEqualTo(Long value) {
            addCriterion("PLACE_WIDTH <>", value, "placeWidth");
            return (Criteria) this;
        }

        public Criteria andPlaceWidthGreaterThan(Long value) {
            addCriterion("PLACE_WIDTH >", value, "placeWidth");
            return (Criteria) this;
        }

        public Criteria andPlaceWidthGreaterThanOrEqualTo(Long value) {
            addCriterion("PLACE_WIDTH >=", value, "placeWidth");
            return (Criteria) this;
        }

        public Criteria andPlaceWidthLessThan(Long value) {
            addCriterion("PLACE_WIDTH <", value, "placeWidth");
            return (Criteria) this;
        }

        public Criteria andPlaceWidthLessThanOrEqualTo(Long value) {
            addCriterion("PLACE_WIDTH <=", value, "placeWidth");
            return (Criteria) this;
        }

        public Criteria andPlaceWidthIn(List<Long> values) {
            addCriterion("PLACE_WIDTH in", values, "placeWidth");
            return (Criteria) this;
        }

        public Criteria andPlaceWidthNotIn(List<Long> values) {
            addCriterion("PLACE_WIDTH not in", values, "placeWidth");
            return (Criteria) this;
        }

        public Criteria andPlaceWidthBetween(Long value1, Long value2) {
            addCriterion("PLACE_WIDTH between", value1, value2, "placeWidth");
            return (Criteria) this;
        }

        public Criteria andPlaceWidthNotBetween(Long value1, Long value2) {
            addCriterion("PLACE_WIDTH not between", value1, value2, "placeWidth");
            return (Criteria) this;
        }

        public Criteria andPlaceSizeIsNull() {
            addCriterion("PLACE_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andPlaceSizeIsNotNull() {
            addCriterion("PLACE_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceSizeEqualTo(Long value) {
            addCriterion("PLACE_SIZE =", value, "placeSize");
            return (Criteria) this;
        }

        public Criteria andPlaceSizeNotEqualTo(Long value) {
            addCriterion("PLACE_SIZE <>", value, "placeSize");
            return (Criteria) this;
        }

        public Criteria andPlaceSizeGreaterThan(Long value) {
            addCriterion("PLACE_SIZE >", value, "placeSize");
            return (Criteria) this;
        }

        public Criteria andPlaceSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("PLACE_SIZE >=", value, "placeSize");
            return (Criteria) this;
        }

        public Criteria andPlaceSizeLessThan(Long value) {
            addCriterion("PLACE_SIZE <", value, "placeSize");
            return (Criteria) this;
        }

        public Criteria andPlaceSizeLessThanOrEqualTo(Long value) {
            addCriterion("PLACE_SIZE <=", value, "placeSize");
            return (Criteria) this;
        }

        public Criteria andPlaceSizeIn(List<Long> values) {
            addCriterion("PLACE_SIZE in", values, "placeSize");
            return (Criteria) this;
        }

        public Criteria andPlaceSizeNotIn(List<Long> values) {
            addCriterion("PLACE_SIZE not in", values, "placeSize");
            return (Criteria) this;
        }

        public Criteria andPlaceSizeBetween(Long value1, Long value2) {
            addCriterion("PLACE_SIZE between", value1, value2, "placeSize");
            return (Criteria) this;
        }

        public Criteria andPlaceSizeNotBetween(Long value1, Long value2) {
            addCriterion("PLACE_SIZE not between", value1, value2, "placeSize");
            return (Criteria) this;
        }

        public Criteria andPlaceHeightIsNull() {
            addCriterion("PLACE_HEIGHT is null");
            return (Criteria) this;
        }

        public Criteria andPlaceHeightIsNotNull() {
            addCriterion("PLACE_HEIGHT is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceHeightEqualTo(Long value) {
            addCriterion("PLACE_HEIGHT =", value, "placeHeight");
            return (Criteria) this;
        }

        public Criteria andPlaceHeightNotEqualTo(Long value) {
            addCriterion("PLACE_HEIGHT <>", value, "placeHeight");
            return (Criteria) this;
        }

        public Criteria andPlaceHeightGreaterThan(Long value) {
            addCriterion("PLACE_HEIGHT >", value, "placeHeight");
            return (Criteria) this;
        }

        public Criteria andPlaceHeightGreaterThanOrEqualTo(Long value) {
            addCriterion("PLACE_HEIGHT >=", value, "placeHeight");
            return (Criteria) this;
        }

        public Criteria andPlaceHeightLessThan(Long value) {
            addCriterion("PLACE_HEIGHT <", value, "placeHeight");
            return (Criteria) this;
        }

        public Criteria andPlaceHeightLessThanOrEqualTo(Long value) {
            addCriterion("PLACE_HEIGHT <=", value, "placeHeight");
            return (Criteria) this;
        }

        public Criteria andPlaceHeightIn(List<Long> values) {
            addCriterion("PLACE_HEIGHT in", values, "placeHeight");
            return (Criteria) this;
        }

        public Criteria andPlaceHeightNotIn(List<Long> values) {
            addCriterion("PLACE_HEIGHT not in", values, "placeHeight");
            return (Criteria) this;
        }

        public Criteria andPlaceHeightBetween(Long value1, Long value2) {
            addCriterion("PLACE_HEIGHT between", value1, value2, "placeHeight");
            return (Criteria) this;
        }

        public Criteria andPlaceHeightNotBetween(Long value1, Long value2) {
            addCriterion("PLACE_HEIGHT not between", value1, value2, "placeHeight");
            return (Criteria) this;
        }

        public Criteria andIsscrollIsNull() {
            addCriterion("ISSCROLL is null");
            return (Criteria) this;
        }

        public Criteria andIsscrollIsNotNull() {
            addCriterion("ISSCROLL is not null");
            return (Criteria) this;
        }

        public Criteria andIsscrollEqualTo(String value) {
            addCriterion("ISSCROLL =", value, "isscroll");
            return (Criteria) this;
        }

        public Criteria andIsscrollNotEqualTo(String value) {
            addCriterion("ISSCROLL <>", value, "isscroll");
            return (Criteria) this;
        }

        public Criteria andIsscrollGreaterThan(String value) {
            addCriterion("ISSCROLL >", value, "isscroll");
            return (Criteria) this;
        }

        public Criteria andIsscrollGreaterThanOrEqualTo(String value) {
            addCriterion("ISSCROLL >=", value, "isscroll");
            return (Criteria) this;
        }

        public Criteria andIsscrollLessThan(String value) {
            addCriterion("ISSCROLL <", value, "isscroll");
            return (Criteria) this;
        }

        public Criteria andIsscrollLessThanOrEqualTo(String value) {
            addCriterion("ISSCROLL <=", value, "isscroll");
            return (Criteria) this;
        }

        public Criteria andIsscrollLike(String value) {
            addCriterion("ISSCROLL like", value, "isscroll");
            return (Criteria) this;
        }

        public Criteria andIsscrollNotLike(String value) {
            addCriterion("ISSCROLL not like", value, "isscroll");
            return (Criteria) this;
        }

        public Criteria andIsscrollIn(List<String> values) {
            addCriterion("ISSCROLL in", values, "isscroll");
            return (Criteria) this;
        }

        public Criteria andIsscrollNotIn(List<String> values) {
            addCriterion("ISSCROLL not in", values, "isscroll");
            return (Criteria) this;
        }

        public Criteria andIsscrollBetween(String value1, String value2) {
            addCriterion("ISSCROLL between", value1, value2, "isscroll");
            return (Criteria) this;
        }

        public Criteria andIsscrollNotBetween(String value1, String value2) {
            addCriterion("ISSCROLL not between", value1, value2, "isscroll");
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
