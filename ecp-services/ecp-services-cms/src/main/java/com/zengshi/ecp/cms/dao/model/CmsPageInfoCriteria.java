package com.zengshi.ecp.cms.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CmsPageInfoCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CmsPageInfoCriteria() {
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

        public Criteria andPageTypeIdIsNull() {
            addCriterion("PAGE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPageTypeIdIsNotNull() {
            addCriterion("PAGE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPageTypeIdEqualTo(Long value) {
            addCriterion("PAGE_TYPE_ID =", value, "pageTypeId");
            return (Criteria) this;
        }

        public Criteria andPageTypeIdNotEqualTo(Long value) {
            addCriterion("PAGE_TYPE_ID <>", value, "pageTypeId");
            return (Criteria) this;
        }

        public Criteria andPageTypeIdGreaterThan(Long value) {
            addCriterion("PAGE_TYPE_ID >", value, "pageTypeId");
            return (Criteria) this;
        }

        public Criteria andPageTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PAGE_TYPE_ID >=", value, "pageTypeId");
            return (Criteria) this;
        }

        public Criteria andPageTypeIdLessThan(Long value) {
            addCriterion("PAGE_TYPE_ID <", value, "pageTypeId");
            return (Criteria) this;
        }

        public Criteria andPageTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("PAGE_TYPE_ID <=", value, "pageTypeId");
            return (Criteria) this;
        }

        public Criteria andPageTypeIdIn(List<Long> values) {
            addCriterion("PAGE_TYPE_ID in", values, "pageTypeId");
            return (Criteria) this;
        }

        public Criteria andPageTypeIdNotIn(List<Long> values) {
            addCriterion("PAGE_TYPE_ID not in", values, "pageTypeId");
            return (Criteria) this;
        }

        public Criteria andPageTypeIdBetween(Long value1, Long value2) {
            addCriterion("PAGE_TYPE_ID between", value1, value2, "pageTypeId");
            return (Criteria) this;
        }

        public Criteria andPageTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("PAGE_TYPE_ID not between", value1, value2, "pageTypeId");
            return (Criteria) this;
        }

        public Criteria andPageNameIsNull() {
            addCriterion("PAGE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPageNameIsNotNull() {
            addCriterion("PAGE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPageNameEqualTo(String value) {
            addCriterion("PAGE_NAME =", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotEqualTo(String value) {
            addCriterion("PAGE_NAME <>", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameGreaterThan(String value) {
            addCriterion("PAGE_NAME >", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameGreaterThanOrEqualTo(String value) {
            addCriterion("PAGE_NAME >=", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameLessThan(String value) {
            addCriterion("PAGE_NAME <", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameLessThanOrEqualTo(String value) {
            addCriterion("PAGE_NAME <=", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameLike(String value) {
            addCriterion("PAGE_NAME like", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotLike(String value) {
            addCriterion("PAGE_NAME not like", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameIn(List<String> values) {
            addCriterion("PAGE_NAME in", values, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotIn(List<String> values) {
            addCriterion("PAGE_NAME not in", values, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameBetween(String value1, String value2) {
            addCriterion("PAGE_NAME between", value1, value2, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotBetween(String value1, String value2) {
            addCriterion("PAGE_NAME not between", value1, value2, "pageName");
            return (Criteria) this;
        }

        public Criteria andSiteUrlIsNull() {
            addCriterion("SITE_URL is null");
            return (Criteria) this;
        }

        public Criteria andSiteUrlIsNotNull() {
            addCriterion("SITE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andSiteUrlEqualTo(String value) {
            addCriterion("SITE_URL =", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlNotEqualTo(String value) {
            addCriterion("SITE_URL <>", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlGreaterThan(String value) {
            addCriterion("SITE_URL >", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_URL >=", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlLessThan(String value) {
            addCriterion("SITE_URL <", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlLessThanOrEqualTo(String value) {
            addCriterion("SITE_URL <=", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlLike(String value) {
            addCriterion("SITE_URL like", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlNotLike(String value) {
            addCriterion("SITE_URL not like", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlIn(List<String> values) {
            addCriterion("SITE_URL in", values, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlNotIn(List<String> values) {
            addCriterion("SITE_URL not in", values, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlBetween(String value1, String value2) {
            addCriterion("SITE_URL between", value1, value2, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlNotBetween(String value1, String value2) {
            addCriterion("SITE_URL not between", value1, value2, "siteUrl");
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

        public Criteria andTimingTimeIsNull() {
            addCriterion("TIMING_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTimingTimeIsNotNull() {
            addCriterion("TIMING_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTimingTimeEqualTo(Timestamp value) {
            addCriterion("TIMING_TIME =", value, "timingTime");
            return (Criteria) this;
        }

        public Criteria andTimingTimeNotEqualTo(Timestamp value) {
            addCriterion("TIMING_TIME <>", value, "timingTime");
            return (Criteria) this;
        }

        public Criteria andTimingTimeGreaterThan(Timestamp value) {
            addCriterion("TIMING_TIME >", value, "timingTime");
            return (Criteria) this;
        }

        public Criteria andTimingTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("TIMING_TIME >=", value, "timingTime");
            return (Criteria) this;
        }

        public Criteria andTimingTimeLessThan(Timestamp value) {
            addCriterion("TIMING_TIME <", value, "timingTime");
            return (Criteria) this;
        }

        public Criteria andTimingTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("TIMING_TIME <=", value, "timingTime");
            return (Criteria) this;
        }

        public Criteria andTimingTimeIn(List<Timestamp> values) {
            addCriterion("TIMING_TIME in", values, "timingTime");
            return (Criteria) this;
        }

        public Criteria andTimingTimeNotIn(List<Timestamp> values) {
            addCriterion("TIMING_TIME not in", values, "timingTime");
            return (Criteria) this;
        }

        public Criteria andTimingTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("TIMING_TIME between", value1, value2, "timingTime");
            return (Criteria) this;
        }

        public Criteria andTimingTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("TIMING_TIME not between", value1, value2, "timingTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeIsNull() {
            addCriterion("VALID_TIME is null");
            return (Criteria) this;
        }

        public Criteria andValidTimeIsNotNull() {
            addCriterion("VALID_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andValidTimeEqualTo(Timestamp value) {
            addCriterion("VALID_TIME =", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeNotEqualTo(Timestamp value) {
            addCriterion("VALID_TIME <>", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeGreaterThan(Timestamp value) {
            addCriterion("VALID_TIME >", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("VALID_TIME >=", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeLessThan(Timestamp value) {
            addCriterion("VALID_TIME <", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("VALID_TIME <=", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeIn(List<Timestamp> values) {
            addCriterion("VALID_TIME in", values, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeNotIn(List<Timestamp> values) {
            addCriterion("VALID_TIME not in", values, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("VALID_TIME between", value1, value2, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("VALID_TIME not between", value1, value2, "validTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIsNull() {
            addCriterion("INVALID_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIsNotNull() {
            addCriterion("INVALID_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeEqualTo(Timestamp value) {
            addCriterion("INVALID_TIME =", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotEqualTo(Timestamp value) {
            addCriterion("INVALID_TIME <>", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeGreaterThan(Timestamp value) {
            addCriterion("INVALID_TIME >", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("INVALID_TIME >=", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeLessThan(Timestamp value) {
            addCriterion("INVALID_TIME <", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("INVALID_TIME <=", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIn(List<Timestamp> values) {
            addCriterion("INVALID_TIME in", values, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotIn(List<Timestamp> values) {
            addCriterion("INVALID_TIME not in", values, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INVALID_TIME between", value1, value2, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INVALID_TIME not between", value1, value2, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andIsUseTemplateIsNull() {
            addCriterion("IS_USE_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andIsUseTemplateIsNotNull() {
            addCriterion("IS_USE_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andIsUseTemplateEqualTo(String value) {
            addCriterion("IS_USE_TEMPLATE =", value, "isUseTemplate");
            return (Criteria) this;
        }

        public Criteria andIsUseTemplateNotEqualTo(String value) {
            addCriterion("IS_USE_TEMPLATE <>", value, "isUseTemplate");
            return (Criteria) this;
        }

        public Criteria andIsUseTemplateGreaterThan(String value) {
            addCriterion("IS_USE_TEMPLATE >", value, "isUseTemplate");
            return (Criteria) this;
        }

        public Criteria andIsUseTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("IS_USE_TEMPLATE >=", value, "isUseTemplate");
            return (Criteria) this;
        }

        public Criteria andIsUseTemplateLessThan(String value) {
            addCriterion("IS_USE_TEMPLATE <", value, "isUseTemplate");
            return (Criteria) this;
        }

        public Criteria andIsUseTemplateLessThanOrEqualTo(String value) {
            addCriterion("IS_USE_TEMPLATE <=", value, "isUseTemplate");
            return (Criteria) this;
        }

        public Criteria andIsUseTemplateLike(String value) {
            addCriterion("IS_USE_TEMPLATE like", value, "isUseTemplate");
            return (Criteria) this;
        }

        public Criteria andIsUseTemplateNotLike(String value) {
            addCriterion("IS_USE_TEMPLATE not like", value, "isUseTemplate");
            return (Criteria) this;
        }

        public Criteria andIsUseTemplateIn(List<String> values) {
            addCriterion("IS_USE_TEMPLATE in", values, "isUseTemplate");
            return (Criteria) this;
        }

        public Criteria andIsUseTemplateNotIn(List<String> values) {
            addCriterion("IS_USE_TEMPLATE not in", values, "isUseTemplate");
            return (Criteria) this;
        }

        public Criteria andIsUseTemplateBetween(String value1, String value2) {
            addCriterion("IS_USE_TEMPLATE between", value1, value2, "isUseTemplate");
            return (Criteria) this;
        }

        public Criteria andIsUseTemplateNotBetween(String value1, String value2) {
            addCriterion("IS_USE_TEMPLATE not between", value1, value2, "isUseTemplate");
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

        public Criteria andPlatformTypeIsNull() {
            addCriterion("PLATFORM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeIsNotNull() {
            addCriterion("PLATFORM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeEqualTo(String value) {
            addCriterion("PLATFORM_TYPE =", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeNotEqualTo(String value) {
            addCriterion("PLATFORM_TYPE <>", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeGreaterThan(String value) {
            addCriterion("PLATFORM_TYPE >", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PLATFORM_TYPE >=", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeLessThan(String value) {
            addCriterion("PLATFORM_TYPE <", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeLessThanOrEqualTo(String value) {
            addCriterion("PLATFORM_TYPE <=", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeLike(String value) {
            addCriterion("PLATFORM_TYPE like", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeNotLike(String value) {
            addCriterion("PLATFORM_TYPE not like", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeIn(List<String> values) {
            addCriterion("PLATFORM_TYPE in", values, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeNotIn(List<String> values) {
            addCriterion("PLATFORM_TYPE not in", values, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeBetween(String value1, String value2) {
            addCriterion("PLATFORM_TYPE between", value1, value2, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeNotBetween(String value1, String value2) {
            addCriterion("PLATFORM_TYPE not between", value1, value2, "platformType");
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

        public Criteria andQrcodePicIsNull() {
            addCriterion("QRCODE_PIC is null");
            return (Criteria) this;
        }

        public Criteria andQrcodePicIsNotNull() {
            addCriterion("QRCODE_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andQrcodePicEqualTo(String value) {
            addCriterion("QRCODE_PIC =", value, "qrcodePic");
            return (Criteria) this;
        }

        public Criteria andQrcodePicNotEqualTo(String value) {
            addCriterion("QRCODE_PIC <>", value, "qrcodePic");
            return (Criteria) this;
        }

        public Criteria andQrcodePicGreaterThan(String value) {
            addCriterion("QRCODE_PIC >", value, "qrcodePic");
            return (Criteria) this;
        }

        public Criteria andQrcodePicGreaterThanOrEqualTo(String value) {
            addCriterion("QRCODE_PIC >=", value, "qrcodePic");
            return (Criteria) this;
        }

        public Criteria andQrcodePicLessThan(String value) {
            addCriterion("QRCODE_PIC <", value, "qrcodePic");
            return (Criteria) this;
        }

        public Criteria andQrcodePicLessThanOrEqualTo(String value) {
            addCriterion("QRCODE_PIC <=", value, "qrcodePic");
            return (Criteria) this;
        }

        public Criteria andQrcodePicLike(String value) {
            addCriterion("QRCODE_PIC like", value, "qrcodePic");
            return (Criteria) this;
        }

        public Criteria andQrcodePicNotLike(String value) {
            addCriterion("QRCODE_PIC not like", value, "qrcodePic");
            return (Criteria) this;
        }

        public Criteria andQrcodePicIn(List<String> values) {
            addCriterion("QRCODE_PIC in", values, "qrcodePic");
            return (Criteria) this;
        }

        public Criteria andQrcodePicNotIn(List<String> values) {
            addCriterion("QRCODE_PIC not in", values, "qrcodePic");
            return (Criteria) this;
        }

        public Criteria andQrcodePicBetween(String value1, String value2) {
            addCriterion("QRCODE_PIC between", value1, value2, "qrcodePic");
            return (Criteria) this;
        }

        public Criteria andQrcodePicNotBetween(String value1, String value2) {
            addCriterion("QRCODE_PIC not between", value1, value2, "qrcodePic");
            return (Criteria) this;
        }

        public Criteria andViewQrcodePicIsNull() {
            addCriterion("VIEW_QRCODE_PIC is null");
            return (Criteria) this;
        }

        public Criteria andViewQrcodePicIsNotNull() {
            addCriterion("VIEW_QRCODE_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andViewQrcodePicEqualTo(String value) {
            addCriterion("VIEW_QRCODE_PIC =", value, "viewQrcodePic");
            return (Criteria) this;
        }

        public Criteria andViewQrcodePicNotEqualTo(String value) {
            addCriterion("VIEW_QRCODE_PIC <>", value, "viewQrcodePic");
            return (Criteria) this;
        }

        public Criteria andViewQrcodePicGreaterThan(String value) {
            addCriterion("VIEW_QRCODE_PIC >", value, "viewQrcodePic");
            return (Criteria) this;
        }

        public Criteria andViewQrcodePicGreaterThanOrEqualTo(String value) {
            addCriterion("VIEW_QRCODE_PIC >=", value, "viewQrcodePic");
            return (Criteria) this;
        }

        public Criteria andViewQrcodePicLessThan(String value) {
            addCriterion("VIEW_QRCODE_PIC <", value, "viewQrcodePic");
            return (Criteria) this;
        }

        public Criteria andViewQrcodePicLessThanOrEqualTo(String value) {
            addCriterion("VIEW_QRCODE_PIC <=", value, "viewQrcodePic");
            return (Criteria) this;
        }

        public Criteria andViewQrcodePicLike(String value) {
            addCriterion("VIEW_QRCODE_PIC like", value, "viewQrcodePic");
            return (Criteria) this;
        }

        public Criteria andViewQrcodePicNotLike(String value) {
            addCriterion("VIEW_QRCODE_PIC not like", value, "viewQrcodePic");
            return (Criteria) this;
        }

        public Criteria andViewQrcodePicIn(List<String> values) {
            addCriterion("VIEW_QRCODE_PIC in", values, "viewQrcodePic");
            return (Criteria) this;
        }

        public Criteria andViewQrcodePicNotIn(List<String> values) {
            addCriterion("VIEW_QRCODE_PIC not in", values, "viewQrcodePic");
            return (Criteria) this;
        }

        public Criteria andViewQrcodePicBetween(String value1, String value2) {
            addCriterion("VIEW_QRCODE_PIC between", value1, value2, "viewQrcodePic");
            return (Criteria) this;
        }

        public Criteria andViewQrcodePicNotBetween(String value1, String value2) {
            addCriterion("VIEW_QRCODE_PIC not between", value1, value2, "viewQrcodePic");
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
