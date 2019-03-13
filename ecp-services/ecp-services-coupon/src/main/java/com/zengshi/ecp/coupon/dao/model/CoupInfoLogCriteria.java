package com.zengshi.ecp.coupon.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CoupInfoLogCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CoupInfoLogCriteria() {
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

        public Criteria andCoupIdIsNull() {
            addCriterion("COUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andCoupIdIsNotNull() {
            addCriterion("COUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCoupIdEqualTo(Long value) {
            addCriterion("COUP_ID =", value, "coupId");
            return (Criteria) this;
        }

        public Criteria andCoupIdNotEqualTo(Long value) {
            addCriterion("COUP_ID <>", value, "coupId");
            return (Criteria) this;
        }

        public Criteria andCoupIdGreaterThan(Long value) {
            addCriterion("COUP_ID >", value, "coupId");
            return (Criteria) this;
        }

        public Criteria andCoupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("COUP_ID >=", value, "coupId");
            return (Criteria) this;
        }

        public Criteria andCoupIdLessThan(Long value) {
            addCriterion("COUP_ID <", value, "coupId");
            return (Criteria) this;
        }

        public Criteria andCoupIdLessThanOrEqualTo(Long value) {
            addCriterion("COUP_ID <=", value, "coupId");
            return (Criteria) this;
        }

        public Criteria andCoupIdIn(List<Long> values) {
            addCriterion("COUP_ID in", values, "coupId");
            return (Criteria) this;
        }

        public Criteria andCoupIdNotIn(List<Long> values) {
            addCriterion("COUP_ID not in", values, "coupId");
            return (Criteria) this;
        }

        public Criteria andCoupIdBetween(Long value1, Long value2) {
            addCriterion("COUP_ID between", value1, value2, "coupId");
            return (Criteria) this;
        }

        public Criteria andCoupIdNotBetween(Long value1, Long value2) {
            addCriterion("COUP_ID not between", value1, value2, "coupId");
            return (Criteria) this;
        }

        public Criteria andCoupCodeIsNull() {
            addCriterion("COUP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCoupCodeIsNotNull() {
            addCriterion("COUP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCoupCodeEqualTo(String value) {
            addCriterion("COUP_CODE =", value, "coupCode");
            return (Criteria) this;
        }

        public Criteria andCoupCodeNotEqualTo(String value) {
            addCriterion("COUP_CODE <>", value, "coupCode");
            return (Criteria) this;
        }

        public Criteria andCoupCodeGreaterThan(String value) {
            addCriterion("COUP_CODE >", value, "coupCode");
            return (Criteria) this;
        }

        public Criteria andCoupCodeGreaterThanOrEqualTo(String value) {
            addCriterion("COUP_CODE >=", value, "coupCode");
            return (Criteria) this;
        }

        public Criteria andCoupCodeLessThan(String value) {
            addCriterion("COUP_CODE <", value, "coupCode");
            return (Criteria) this;
        }

        public Criteria andCoupCodeLessThanOrEqualTo(String value) {
            addCriterion("COUP_CODE <=", value, "coupCode");
            return (Criteria) this;
        }

        public Criteria andCoupCodeLike(String value) {
            addCriterion("COUP_CODE like", value, "coupCode");
            return (Criteria) this;
        }

        public Criteria andCoupCodeNotLike(String value) {
            addCriterion("COUP_CODE not like", value, "coupCode");
            return (Criteria) this;
        }

        public Criteria andCoupCodeIn(List<String> values) {
            addCriterion("COUP_CODE in", values, "coupCode");
            return (Criteria) this;
        }

        public Criteria andCoupCodeNotIn(List<String> values) {
            addCriterion("COUP_CODE not in", values, "coupCode");
            return (Criteria) this;
        }

        public Criteria andCoupCodeBetween(String value1, String value2) {
            addCriterion("COUP_CODE between", value1, value2, "coupCode");
            return (Criteria) this;
        }

        public Criteria andCoupCodeNotBetween(String value1, String value2) {
            addCriterion("COUP_CODE not between", value1, value2, "coupCode");
            return (Criteria) this;
        }

        public Criteria andCoupTypeIdIsNull() {
            addCriterion("COUP_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCoupTypeIdIsNotNull() {
            addCriterion("COUP_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCoupTypeIdEqualTo(Long value) {
            addCriterion("COUP_TYPE_ID =", value, "coupTypeId");
            return (Criteria) this;
        }

        public Criteria andCoupTypeIdNotEqualTo(Long value) {
            addCriterion("COUP_TYPE_ID <>", value, "coupTypeId");
            return (Criteria) this;
        }

        public Criteria andCoupTypeIdGreaterThan(Long value) {
            addCriterion("COUP_TYPE_ID >", value, "coupTypeId");
            return (Criteria) this;
        }

        public Criteria andCoupTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("COUP_TYPE_ID >=", value, "coupTypeId");
            return (Criteria) this;
        }

        public Criteria andCoupTypeIdLessThan(Long value) {
            addCriterion("COUP_TYPE_ID <", value, "coupTypeId");
            return (Criteria) this;
        }

        public Criteria andCoupTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("COUP_TYPE_ID <=", value, "coupTypeId");
            return (Criteria) this;
        }

        public Criteria andCoupTypeIdIn(List<Long> values) {
            addCriterion("COUP_TYPE_ID in", values, "coupTypeId");
            return (Criteria) this;
        }

        public Criteria andCoupTypeIdNotIn(List<Long> values) {
            addCriterion("COUP_TYPE_ID not in", values, "coupTypeId");
            return (Criteria) this;
        }

        public Criteria andCoupTypeIdBetween(Long value1, Long value2) {
            addCriterion("COUP_TYPE_ID between", value1, value2, "coupTypeId");
            return (Criteria) this;
        }

        public Criteria andCoupTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("COUP_TYPE_ID not between", value1, value2, "coupTypeId");
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

        public Criteria andCoupNameIsNull() {
            addCriterion("COUP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCoupNameIsNotNull() {
            addCriterion("COUP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCoupNameEqualTo(String value) {
            addCriterion("COUP_NAME =", value, "coupName");
            return (Criteria) this;
        }

        public Criteria andCoupNameNotEqualTo(String value) {
            addCriterion("COUP_NAME <>", value, "coupName");
            return (Criteria) this;
        }

        public Criteria andCoupNameGreaterThan(String value) {
            addCriterion("COUP_NAME >", value, "coupName");
            return (Criteria) this;
        }

        public Criteria andCoupNameGreaterThanOrEqualTo(String value) {
            addCriterion("COUP_NAME >=", value, "coupName");
            return (Criteria) this;
        }

        public Criteria andCoupNameLessThan(String value) {
            addCriterion("COUP_NAME <", value, "coupName");
            return (Criteria) this;
        }

        public Criteria andCoupNameLessThanOrEqualTo(String value) {
            addCriterion("COUP_NAME <=", value, "coupName");
            return (Criteria) this;
        }

        public Criteria andCoupNameLike(String value) {
            addCriterion("COUP_NAME like", value, "coupName");
            return (Criteria) this;
        }

        public Criteria andCoupNameNotLike(String value) {
            addCriterion("COUP_NAME not like", value, "coupName");
            return (Criteria) this;
        }

        public Criteria andCoupNameIn(List<String> values) {
            addCriterion("COUP_NAME in", values, "coupName");
            return (Criteria) this;
        }

        public Criteria andCoupNameNotIn(List<String> values) {
            addCriterion("COUP_NAME not in", values, "coupName");
            return (Criteria) this;
        }

        public Criteria andCoupNameBetween(String value1, String value2) {
            addCriterion("COUP_NAME between", value1, value2, "coupName");
            return (Criteria) this;
        }

        public Criteria andCoupNameNotBetween(String value1, String value2) {
            addCriterion("COUP_NAME not between", value1, value2, "coupName");
            return (Criteria) this;
        }

        public Criteria andCoupValueIsNull() {
            addCriterion("COUP_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andCoupValueIsNotNull() {
            addCriterion("COUP_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andCoupValueEqualTo(Long value) {
            addCriterion("COUP_VALUE =", value, "coupValue");
            return (Criteria) this;
        }

        public Criteria andCoupValueNotEqualTo(Long value) {
            addCriterion("COUP_VALUE <>", value, "coupValue");
            return (Criteria) this;
        }

        public Criteria andCoupValueGreaterThan(Long value) {
            addCriterion("COUP_VALUE >", value, "coupValue");
            return (Criteria) this;
        }

        public Criteria andCoupValueGreaterThanOrEqualTo(Long value) {
            addCriterion("COUP_VALUE >=", value, "coupValue");
            return (Criteria) this;
        }

        public Criteria andCoupValueLessThan(Long value) {
            addCriterion("COUP_VALUE <", value, "coupValue");
            return (Criteria) this;
        }

        public Criteria andCoupValueLessThanOrEqualTo(Long value) {
            addCriterion("COUP_VALUE <=", value, "coupValue");
            return (Criteria) this;
        }

        public Criteria andCoupValueIn(List<Long> values) {
            addCriterion("COUP_VALUE in", values, "coupValue");
            return (Criteria) this;
        }

        public Criteria andCoupValueNotIn(List<Long> values) {
            addCriterion("COUP_VALUE not in", values, "coupValue");
            return (Criteria) this;
        }

        public Criteria andCoupValueBetween(Long value1, Long value2) {
            addCriterion("COUP_VALUE between", value1, value2, "coupValue");
            return (Criteria) this;
        }

        public Criteria andCoupValueNotBetween(Long value1, Long value2) {
            addCriterion("COUP_VALUE not between", value1, value2, "coupValue");
            return (Criteria) this;
        }

        public Criteria andTypeLimitIsNull() {
            addCriterion("TYPE_LIMIT is null");
            return (Criteria) this;
        }

        public Criteria andTypeLimitIsNotNull() {
            addCriterion("TYPE_LIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andTypeLimitEqualTo(String value) {
            addCriterion("TYPE_LIMIT =", value, "typeLimit");
            return (Criteria) this;
        }

        public Criteria andTypeLimitNotEqualTo(String value) {
            addCriterion("TYPE_LIMIT <>", value, "typeLimit");
            return (Criteria) this;
        }

        public Criteria andTypeLimitGreaterThan(String value) {
            addCriterion("TYPE_LIMIT >", value, "typeLimit");
            return (Criteria) this;
        }

        public Criteria andTypeLimitGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_LIMIT >=", value, "typeLimit");
            return (Criteria) this;
        }

        public Criteria andTypeLimitLessThan(String value) {
            addCriterion("TYPE_LIMIT <", value, "typeLimit");
            return (Criteria) this;
        }

        public Criteria andTypeLimitLessThanOrEqualTo(String value) {
            addCriterion("TYPE_LIMIT <=", value, "typeLimit");
            return (Criteria) this;
        }

        public Criteria andTypeLimitLike(String value) {
            addCriterion("TYPE_LIMIT like", value, "typeLimit");
            return (Criteria) this;
        }

        public Criteria andTypeLimitNotLike(String value) {
            addCriterion("TYPE_LIMIT not like", value, "typeLimit");
            return (Criteria) this;
        }

        public Criteria andTypeLimitIn(List<String> values) {
            addCriterion("TYPE_LIMIT in", values, "typeLimit");
            return (Criteria) this;
        }

        public Criteria andTypeLimitNotIn(List<String> values) {
            addCriterion("TYPE_LIMIT not in", values, "typeLimit");
            return (Criteria) this;
        }

        public Criteria andTypeLimitBetween(String value1, String value2) {
            addCriterion("TYPE_LIMIT between", value1, value2, "typeLimit");
            return (Criteria) this;
        }

        public Criteria andTypeLimitNotBetween(String value1, String value2) {
            addCriterion("TYPE_LIMIT not between", value1, value2, "typeLimit");
            return (Criteria) this;
        }

        public Criteria andUseRuleCodeIsNull() {
            addCriterion("USE_RULE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andUseRuleCodeIsNotNull() {
            addCriterion("USE_RULE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andUseRuleCodeEqualTo(String value) {
            addCriterion("USE_RULE_CODE =", value, "useRuleCode");
            return (Criteria) this;
        }

        public Criteria andUseRuleCodeNotEqualTo(String value) {
            addCriterion("USE_RULE_CODE <>", value, "useRuleCode");
            return (Criteria) this;
        }

        public Criteria andUseRuleCodeGreaterThan(String value) {
            addCriterion("USE_RULE_CODE >", value, "useRuleCode");
            return (Criteria) this;
        }

        public Criteria andUseRuleCodeGreaterThanOrEqualTo(String value) {
            addCriterion("USE_RULE_CODE >=", value, "useRuleCode");
            return (Criteria) this;
        }

        public Criteria andUseRuleCodeLessThan(String value) {
            addCriterion("USE_RULE_CODE <", value, "useRuleCode");
            return (Criteria) this;
        }

        public Criteria andUseRuleCodeLessThanOrEqualTo(String value) {
            addCriterion("USE_RULE_CODE <=", value, "useRuleCode");
            return (Criteria) this;
        }

        public Criteria andUseRuleCodeLike(String value) {
            addCriterion("USE_RULE_CODE like", value, "useRuleCode");
            return (Criteria) this;
        }

        public Criteria andUseRuleCodeNotLike(String value) {
            addCriterion("USE_RULE_CODE not like", value, "useRuleCode");
            return (Criteria) this;
        }

        public Criteria andUseRuleCodeIn(List<String> values) {
            addCriterion("USE_RULE_CODE in", values, "useRuleCode");
            return (Criteria) this;
        }

        public Criteria andUseRuleCodeNotIn(List<String> values) {
            addCriterion("USE_RULE_CODE not in", values, "useRuleCode");
            return (Criteria) this;
        }

        public Criteria andUseRuleCodeBetween(String value1, String value2) {
            addCriterion("USE_RULE_CODE between", value1, value2, "useRuleCode");
            return (Criteria) this;
        }

        public Criteria andUseRuleCodeNotBetween(String value1, String value2) {
            addCriterion("USE_RULE_CODE not between", value1, value2, "useRuleCode");
            return (Criteria) this;
        }

        public Criteria andGainRuleCodeIsNull() {
            addCriterion("GAIN_RULE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andGainRuleCodeIsNotNull() {
            addCriterion("GAIN_RULE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andGainRuleCodeEqualTo(String value) {
            addCriterion("GAIN_RULE_CODE =", value, "gainRuleCode");
            return (Criteria) this;
        }

        public Criteria andGainRuleCodeNotEqualTo(String value) {
            addCriterion("GAIN_RULE_CODE <>", value, "gainRuleCode");
            return (Criteria) this;
        }

        public Criteria andGainRuleCodeGreaterThan(String value) {
            addCriterion("GAIN_RULE_CODE >", value, "gainRuleCode");
            return (Criteria) this;
        }

        public Criteria andGainRuleCodeGreaterThanOrEqualTo(String value) {
            addCriterion("GAIN_RULE_CODE >=", value, "gainRuleCode");
            return (Criteria) this;
        }

        public Criteria andGainRuleCodeLessThan(String value) {
            addCriterion("GAIN_RULE_CODE <", value, "gainRuleCode");
            return (Criteria) this;
        }

        public Criteria andGainRuleCodeLessThanOrEqualTo(String value) {
            addCriterion("GAIN_RULE_CODE <=", value, "gainRuleCode");
            return (Criteria) this;
        }

        public Criteria andGainRuleCodeLike(String value) {
            addCriterion("GAIN_RULE_CODE like", value, "gainRuleCode");
            return (Criteria) this;
        }

        public Criteria andGainRuleCodeNotLike(String value) {
            addCriterion("GAIN_RULE_CODE not like", value, "gainRuleCode");
            return (Criteria) this;
        }

        public Criteria andGainRuleCodeIn(List<String> values) {
            addCriterion("GAIN_RULE_CODE in", values, "gainRuleCode");
            return (Criteria) this;
        }

        public Criteria andGainRuleCodeNotIn(List<String> values) {
            addCriterion("GAIN_RULE_CODE not in", values, "gainRuleCode");
            return (Criteria) this;
        }

        public Criteria andGainRuleCodeBetween(String value1, String value2) {
            addCriterion("GAIN_RULE_CODE between", value1, value2, "gainRuleCode");
            return (Criteria) this;
        }

        public Criteria andGainRuleCodeNotBetween(String value1, String value2) {
            addCriterion("GAIN_RULE_CODE not between", value1, value2, "gainRuleCode");
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

        public Criteria andIfCodeIsNull() {
            addCriterion("IF_CODE is null");
            return (Criteria) this;
        }

        public Criteria andIfCodeIsNotNull() {
            addCriterion("IF_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andIfCodeEqualTo(String value) {
            addCriterion("IF_CODE =", value, "ifCode");
            return (Criteria) this;
        }

        public Criteria andIfCodeNotEqualTo(String value) {
            addCriterion("IF_CODE <>", value, "ifCode");
            return (Criteria) this;
        }

        public Criteria andIfCodeGreaterThan(String value) {
            addCriterion("IF_CODE >", value, "ifCode");
            return (Criteria) this;
        }

        public Criteria andIfCodeGreaterThanOrEqualTo(String value) {
            addCriterion("IF_CODE >=", value, "ifCode");
            return (Criteria) this;
        }

        public Criteria andIfCodeLessThan(String value) {
            addCriterion("IF_CODE <", value, "ifCode");
            return (Criteria) this;
        }

        public Criteria andIfCodeLessThanOrEqualTo(String value) {
            addCriterion("IF_CODE <=", value, "ifCode");
            return (Criteria) this;
        }

        public Criteria andIfCodeLike(String value) {
            addCriterion("IF_CODE like", value, "ifCode");
            return (Criteria) this;
        }

        public Criteria andIfCodeNotLike(String value) {
            addCriterion("IF_CODE not like", value, "ifCode");
            return (Criteria) this;
        }

        public Criteria andIfCodeIn(List<String> values) {
            addCriterion("IF_CODE in", values, "ifCode");
            return (Criteria) this;
        }

        public Criteria andIfCodeNotIn(List<String> values) {
            addCriterion("IF_CODE not in", values, "ifCode");
            return (Criteria) this;
        }

        public Criteria andIfCodeBetween(String value1, String value2) {
            addCriterion("IF_CODE between", value1, value2, "ifCode");
            return (Criteria) this;
        }

        public Criteria andIfCodeNotBetween(String value1, String value2) {
            addCriterion("IF_CODE not between", value1, value2, "ifCode");
            return (Criteria) this;
        }

        public Criteria andIfBackIsNull() {
            addCriterion("IF_BACK is null");
            return (Criteria) this;
        }

        public Criteria andIfBackIsNotNull() {
            addCriterion("IF_BACK is not null");
            return (Criteria) this;
        }

        public Criteria andIfBackEqualTo(String value) {
            addCriterion("IF_BACK =", value, "ifBack");
            return (Criteria) this;
        }

        public Criteria andIfBackNotEqualTo(String value) {
            addCriterion("IF_BACK <>", value, "ifBack");
            return (Criteria) this;
        }

        public Criteria andIfBackGreaterThan(String value) {
            addCriterion("IF_BACK >", value, "ifBack");
            return (Criteria) this;
        }

        public Criteria andIfBackGreaterThanOrEqualTo(String value) {
            addCriterion("IF_BACK >=", value, "ifBack");
            return (Criteria) this;
        }

        public Criteria andIfBackLessThan(String value) {
            addCriterion("IF_BACK <", value, "ifBack");
            return (Criteria) this;
        }

        public Criteria andIfBackLessThanOrEqualTo(String value) {
            addCriterion("IF_BACK <=", value, "ifBack");
            return (Criteria) this;
        }

        public Criteria andIfBackLike(String value) {
            addCriterion("IF_BACK like", value, "ifBack");
            return (Criteria) this;
        }

        public Criteria andIfBackNotLike(String value) {
            addCriterion("IF_BACK not like", value, "ifBack");
            return (Criteria) this;
        }

        public Criteria andIfBackIn(List<String> values) {
            addCriterion("IF_BACK in", values, "ifBack");
            return (Criteria) this;
        }

        public Criteria andIfBackNotIn(List<String> values) {
            addCriterion("IF_BACK not in", values, "ifBack");
            return (Criteria) this;
        }

        public Criteria andIfBackBetween(String value1, String value2) {
            addCriterion("IF_BACK between", value1, value2, "ifBack");
            return (Criteria) this;
        }

        public Criteria andIfBackNotBetween(String value1, String value2) {
            addCriterion("IF_BACK not between", value1, value2, "ifBack");
            return (Criteria) this;
        }

        public Criteria andCoupNumIsNull() {
            addCriterion("COUP_NUM is null");
            return (Criteria) this;
        }

        public Criteria andCoupNumIsNotNull() {
            addCriterion("COUP_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andCoupNumEqualTo(Long value) {
            addCriterion("COUP_NUM =", value, "coupNum");
            return (Criteria) this;
        }

        public Criteria andCoupNumNotEqualTo(Long value) {
            addCriterion("COUP_NUM <>", value, "coupNum");
            return (Criteria) this;
        }

        public Criteria andCoupNumGreaterThan(Long value) {
            addCriterion("COUP_NUM >", value, "coupNum");
            return (Criteria) this;
        }

        public Criteria andCoupNumGreaterThanOrEqualTo(Long value) {
            addCriterion("COUP_NUM >=", value, "coupNum");
            return (Criteria) this;
        }

        public Criteria andCoupNumLessThan(Long value) {
            addCriterion("COUP_NUM <", value, "coupNum");
            return (Criteria) this;
        }

        public Criteria andCoupNumLessThanOrEqualTo(Long value) {
            addCriterion("COUP_NUM <=", value, "coupNum");
            return (Criteria) this;
        }

        public Criteria andCoupNumIn(List<Long> values) {
            addCriterion("COUP_NUM in", values, "coupNum");
            return (Criteria) this;
        }

        public Criteria andCoupNumNotIn(List<Long> values) {
            addCriterion("COUP_NUM not in", values, "coupNum");
            return (Criteria) this;
        }

        public Criteria andCoupNumBetween(Long value1, Long value2) {
            addCriterion("COUP_NUM between", value1, value2, "coupNum");
            return (Criteria) this;
        }

        public Criteria andCoupNumNotBetween(Long value1, Long value2) {
            addCriterion("COUP_NUM not between", value1, value2, "coupNum");
            return (Criteria) this;
        }

        public Criteria andGetNumIsNull() {
            addCriterion("GET_NUM is null");
            return (Criteria) this;
        }

        public Criteria andGetNumIsNotNull() {
            addCriterion("GET_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andGetNumEqualTo(Long value) {
            addCriterion("GET_NUM =", value, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumNotEqualTo(Long value) {
            addCriterion("GET_NUM <>", value, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumGreaterThan(Long value) {
            addCriterion("GET_NUM >", value, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumGreaterThanOrEqualTo(Long value) {
            addCriterion("GET_NUM >=", value, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumLessThan(Long value) {
            addCriterion("GET_NUM <", value, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumLessThanOrEqualTo(Long value) {
            addCriterion("GET_NUM <=", value, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumIn(List<Long> values) {
            addCriterion("GET_NUM in", values, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumNotIn(List<Long> values) {
            addCriterion("GET_NUM not in", values, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumBetween(Long value1, Long value2) {
            addCriterion("GET_NUM between", value1, value2, "getNum");
            return (Criteria) this;
        }

        public Criteria andGetNumNotBetween(Long value1, Long value2) {
            addCriterion("GET_NUM not between", value1, value2, "getNum");
            return (Criteria) this;
        }

        public Criteria andEffTypeIsNull() {
            addCriterion("EFF_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andEffTypeIsNotNull() {
            addCriterion("EFF_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andEffTypeEqualTo(String value) {
            addCriterion("EFF_TYPE =", value, "effType");
            return (Criteria) this;
        }

        public Criteria andEffTypeNotEqualTo(String value) {
            addCriterion("EFF_TYPE <>", value, "effType");
            return (Criteria) this;
        }

        public Criteria andEffTypeGreaterThan(String value) {
            addCriterion("EFF_TYPE >", value, "effType");
            return (Criteria) this;
        }

        public Criteria andEffTypeGreaterThanOrEqualTo(String value) {
            addCriterion("EFF_TYPE >=", value, "effType");
            return (Criteria) this;
        }

        public Criteria andEffTypeLessThan(String value) {
            addCriterion("EFF_TYPE <", value, "effType");
            return (Criteria) this;
        }

        public Criteria andEffTypeLessThanOrEqualTo(String value) {
            addCriterion("EFF_TYPE <=", value, "effType");
            return (Criteria) this;
        }

        public Criteria andEffTypeLike(String value) {
            addCriterion("EFF_TYPE like", value, "effType");
            return (Criteria) this;
        }

        public Criteria andEffTypeNotLike(String value) {
            addCriterion("EFF_TYPE not like", value, "effType");
            return (Criteria) this;
        }

        public Criteria andEffTypeIn(List<String> values) {
            addCriterion("EFF_TYPE in", values, "effType");
            return (Criteria) this;
        }

        public Criteria andEffTypeNotIn(List<String> values) {
            addCriterion("EFF_TYPE not in", values, "effType");
            return (Criteria) this;
        }

        public Criteria andEffTypeBetween(String value1, String value2) {
            addCriterion("EFF_TYPE between", value1, value2, "effType");
            return (Criteria) this;
        }

        public Criteria andEffTypeNotBetween(String value1, String value2) {
            addCriterion("EFF_TYPE not between", value1, value2, "effType");
            return (Criteria) this;
        }

        public Criteria andFixedTimeIsNull() {
            addCriterion("FIXED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andFixedTimeIsNotNull() {
            addCriterion("FIXED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andFixedTimeEqualTo(Integer value) {
            addCriterion("FIXED_TIME =", value, "fixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedTimeNotEqualTo(Integer value) {
            addCriterion("FIXED_TIME <>", value, "fixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedTimeGreaterThan(Integer value) {
            addCriterion("FIXED_TIME >", value, "fixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("FIXED_TIME >=", value, "fixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedTimeLessThan(Integer value) {
            addCriterion("FIXED_TIME <", value, "fixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedTimeLessThanOrEqualTo(Integer value) {
            addCriterion("FIXED_TIME <=", value, "fixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedTimeIn(List<Integer> values) {
            addCriterion("FIXED_TIME in", values, "fixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedTimeNotIn(List<Integer> values) {
            addCriterion("FIXED_TIME not in", values, "fixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedTimeBetween(Integer value1, Integer value2) {
            addCriterion("FIXED_TIME between", value1, value2, "fixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("FIXED_TIME not between", value1, value2, "fixedTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIsNull() {
            addCriterion("ACTIVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIsNotNull() {
            addCriterion("ACTIVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andActiveTimeEqualTo(Timestamp value) {
            addCriterion("ACTIVE_TIME =", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotEqualTo(Timestamp value) {
            addCriterion("ACTIVE_TIME <>", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeGreaterThan(Timestamp value) {
            addCriterion("ACTIVE_TIME >", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("ACTIVE_TIME >=", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeLessThan(Timestamp value) {
            addCriterion("ACTIVE_TIME <", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("ACTIVE_TIME <=", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIn(List<Timestamp> values) {
            addCriterion("ACTIVE_TIME in", values, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotIn(List<Timestamp> values) {
            addCriterion("ACTIVE_TIME not in", values, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ACTIVE_TIME between", value1, value2, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ACTIVE_TIME not between", value1, value2, "activeTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeIsNull() {
            addCriterion("INACTIVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeIsNotNull() {
            addCriterion("INACTIVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeEqualTo(Timestamp value) {
            addCriterion("INACTIVE_TIME =", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeNotEqualTo(Timestamp value) {
            addCriterion("INACTIVE_TIME <>", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeGreaterThan(Timestamp value) {
            addCriterion("INACTIVE_TIME >", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("INACTIVE_TIME >=", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeLessThan(Timestamp value) {
            addCriterion("INACTIVE_TIME <", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("INACTIVE_TIME <=", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeIn(List<Timestamp> values) {
            addCriterion("INACTIVE_TIME in", values, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeNotIn(List<Timestamp> values) {
            addCriterion("INACTIVE_TIME not in", values, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INACTIVE_TIME between", value1, value2, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INACTIVE_TIME not between", value1, value2, "inactiveTime");
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

        public Criteria andCreateTimeLogIsNull() {
            addCriterion("CREATE_TIME_LOG is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLogIsNotNull() {
            addCriterion("CREATE_TIME_LOG is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLogEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME_LOG =", value, "createTimeLog");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLogNotEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME_LOG <>", value, "createTimeLog");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLogGreaterThan(Timestamp value) {
            addCriterion("CREATE_TIME_LOG >", value, "createTimeLog");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLogGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME_LOG >=", value, "createTimeLog");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLogLessThan(Timestamp value) {
            addCriterion("CREATE_TIME_LOG <", value, "createTimeLog");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLogLessThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME_LOG <=", value, "createTimeLog");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLogIn(List<Timestamp> values) {
            addCriterion("CREATE_TIME_LOG in", values, "createTimeLog");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLogNotIn(List<Timestamp> values) {
            addCriterion("CREATE_TIME_LOG not in", values, "createTimeLog");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLogBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TIME_LOG between", value1, value2, "createTimeLog");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLogNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TIME_LOG not between", value1, value2, "createTimeLog");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLogIsNull() {
            addCriterion("CREATE_STAFF_LOG is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLogIsNotNull() {
            addCriterion("CREATE_STAFF_LOG is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLogEqualTo(Long value) {
            addCriterion("CREATE_STAFF_LOG =", value, "createStaffLog");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLogNotEqualTo(Long value) {
            addCriterion("CREATE_STAFF_LOG <>", value, "createStaffLog");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLogGreaterThan(Long value) {
            addCriterion("CREATE_STAFF_LOG >", value, "createStaffLog");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLogGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATE_STAFF_LOG >=", value, "createStaffLog");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLogLessThan(Long value) {
            addCriterion("CREATE_STAFF_LOG <", value, "createStaffLog");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLogLessThanOrEqualTo(Long value) {
            addCriterion("CREATE_STAFF_LOG <=", value, "createStaffLog");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLogIn(List<Long> values) {
            addCriterion("CREATE_STAFF_LOG in", values, "createStaffLog");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLogNotIn(List<Long> values) {
            addCriterion("CREATE_STAFF_LOG not in", values, "createStaffLog");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLogBetween(Long value1, Long value2) {
            addCriterion("CREATE_STAFF_LOG between", value1, value2, "createStaffLog");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLogNotBetween(Long value1, Long value2) {
            addCriterion("CREATE_STAFF_LOG not between", value1, value2, "createStaffLog");
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
