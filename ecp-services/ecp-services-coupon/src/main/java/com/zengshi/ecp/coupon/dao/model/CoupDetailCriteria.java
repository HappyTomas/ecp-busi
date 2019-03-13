package com.zengshi.ecp.coupon.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CoupDetailCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CoupDetailCriteria() {
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

        public Criteria andCoupNoIsNull() {
            addCriterion("COUP_NO is null");
            return (Criteria) this;
        }

        public Criteria andCoupNoIsNotNull() {
            addCriterion("COUP_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCoupNoEqualTo(String value) {
            addCriterion("COUP_NO =", value, "coupNo");
            return (Criteria) this;
        }

        public Criteria andCoupNoNotEqualTo(String value) {
            addCriterion("COUP_NO <>", value, "coupNo");
            return (Criteria) this;
        }

        public Criteria andCoupNoGreaterThan(String value) {
            addCriterion("COUP_NO >", value, "coupNo");
            return (Criteria) this;
        }

        public Criteria andCoupNoGreaterThanOrEqualTo(String value) {
            addCriterion("COUP_NO >=", value, "coupNo");
            return (Criteria) this;
        }

        public Criteria andCoupNoLessThan(String value) {
            addCriterion("COUP_NO <", value, "coupNo");
            return (Criteria) this;
        }

        public Criteria andCoupNoLessThanOrEqualTo(String value) {
            addCriterion("COUP_NO <=", value, "coupNo");
            return (Criteria) this;
        }

        public Criteria andCoupNoLike(String value) {
            addCriterion("COUP_NO like", value, "coupNo");
            return (Criteria) this;
        }

        public Criteria andCoupNoNotLike(String value) {
            addCriterion("COUP_NO not like", value, "coupNo");
            return (Criteria) this;
        }

        public Criteria andCoupNoIn(List<String> values) {
            addCriterion("COUP_NO in", values, "coupNo");
            return (Criteria) this;
        }

        public Criteria andCoupNoNotIn(List<String> values) {
            addCriterion("COUP_NO not in", values, "coupNo");
            return (Criteria) this;
        }

        public Criteria andCoupNoBetween(String value1, String value2) {
            addCriterion("COUP_NO between", value1, value2, "coupNo");
            return (Criteria) this;
        }

        public Criteria andCoupNoNotBetween(String value1, String value2) {
            addCriterion("COUP_NO not between", value1, value2, "coupNo");
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

        public Criteria andStaffIdIsNull() {
            addCriterion("STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNotNull() {
            addCriterion("STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStaffIdEqualTo(Long value) {
            addCriterion("STAFF_ID =", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotEqualTo(Long value) {
            addCriterion("STAFF_ID <>", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThan(Long value) {
            addCriterion("STAFF_ID >", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThanOrEqualTo(Long value) {
            addCriterion("STAFF_ID >=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThan(Long value) {
            addCriterion("STAFF_ID <", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThanOrEqualTo(Long value) {
            addCriterion("STAFF_ID <=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdIn(List<Long> values) {
            addCriterion("STAFF_ID in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotIn(List<Long> values) {
            addCriterion("STAFF_ID not in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdBetween(Long value1, Long value2) {
            addCriterion("STAFF_ID between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotBetween(Long value1, Long value2) {
            addCriterion("STAFF_ID not between", value1, value2, "staffId");
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

        public Criteria andCoupValueShowIsNull() {
            addCriterion("COUP_VALUE_SHOW is null");
            return (Criteria) this;
        }

        public Criteria andCoupValueShowIsNotNull() {
            addCriterion("COUP_VALUE_SHOW is not null");
            return (Criteria) this;
        }

        public Criteria andCoupValueShowEqualTo(String value) {
            addCriterion("COUP_VALUE_SHOW =", value, "coupValueShow");
            return (Criteria) this;
        }

        public Criteria andCoupValueShowNotEqualTo(String value) {
            addCriterion("COUP_VALUE_SHOW <>", value, "coupValueShow");
            return (Criteria) this;
        }

        public Criteria andCoupValueShowGreaterThan(String value) {
            addCriterion("COUP_VALUE_SHOW >", value, "coupValueShow");
            return (Criteria) this;
        }

        public Criteria andCoupValueShowGreaterThanOrEqualTo(String value) {
            addCriterion("COUP_VALUE_SHOW >=", value, "coupValueShow");
            return (Criteria) this;
        }

        public Criteria andCoupValueShowLessThan(String value) {
            addCriterion("COUP_VALUE_SHOW <", value, "coupValueShow");
            return (Criteria) this;
        }

        public Criteria andCoupValueShowLessThanOrEqualTo(String value) {
            addCriterion("COUP_VALUE_SHOW <=", value, "coupValueShow");
            return (Criteria) this;
        }

        public Criteria andCoupValueShowLike(String value) {
            addCriterion("COUP_VALUE_SHOW like", value, "coupValueShow");
            return (Criteria) this;
        }

        public Criteria andCoupValueShowNotLike(String value) {
            addCriterion("COUP_VALUE_SHOW not like", value, "coupValueShow");
            return (Criteria) this;
        }

        public Criteria andCoupValueShowIn(List<String> values) {
            addCriterion("COUP_VALUE_SHOW in", values, "coupValueShow");
            return (Criteria) this;
        }

        public Criteria andCoupValueShowNotIn(List<String> values) {
            addCriterion("COUP_VALUE_SHOW not in", values, "coupValueShow");
            return (Criteria) this;
        }

        public Criteria andCoupValueShowBetween(String value1, String value2) {
            addCriterion("COUP_VALUE_SHOW between", value1, value2, "coupValueShow");
            return (Criteria) this;
        }

        public Criteria andCoupValueShowNotBetween(String value1, String value2) {
            addCriterion("COUP_VALUE_SHOW not between", value1, value2, "coupValueShow");
            return (Criteria) this;
        }

        public Criteria andConditionsShowIsNull() {
            addCriterion("CONDITIONS_SHOW is null");
            return (Criteria) this;
        }

        public Criteria andConditionsShowIsNotNull() {
            addCriterion("CONDITIONS_SHOW is not null");
            return (Criteria) this;
        }

        public Criteria andConditionsShowEqualTo(String value) {
            addCriterion("CONDITIONS_SHOW =", value, "conditionsShow");
            return (Criteria) this;
        }

        public Criteria andConditionsShowNotEqualTo(String value) {
            addCriterion("CONDITIONS_SHOW <>", value, "conditionsShow");
            return (Criteria) this;
        }

        public Criteria andConditionsShowGreaterThan(String value) {
            addCriterion("CONDITIONS_SHOW >", value, "conditionsShow");
            return (Criteria) this;
        }

        public Criteria andConditionsShowGreaterThanOrEqualTo(String value) {
            addCriterion("CONDITIONS_SHOW >=", value, "conditionsShow");
            return (Criteria) this;
        }

        public Criteria andConditionsShowLessThan(String value) {
            addCriterion("CONDITIONS_SHOW <", value, "conditionsShow");
            return (Criteria) this;
        }

        public Criteria andConditionsShowLessThanOrEqualTo(String value) {
            addCriterion("CONDITIONS_SHOW <=", value, "conditionsShow");
            return (Criteria) this;
        }

        public Criteria andConditionsShowLike(String value) {
            addCriterion("CONDITIONS_SHOW like", value, "conditionsShow");
            return (Criteria) this;
        }

        public Criteria andConditionsShowNotLike(String value) {
            addCriterion("CONDITIONS_SHOW not like", value, "conditionsShow");
            return (Criteria) this;
        }

        public Criteria andConditionsShowIn(List<String> values) {
            addCriterion("CONDITIONS_SHOW in", values, "conditionsShow");
            return (Criteria) this;
        }

        public Criteria andConditionsShowNotIn(List<String> values) {
            addCriterion("CONDITIONS_SHOW not in", values, "conditionsShow");
            return (Criteria) this;
        }

        public Criteria andConditionsShowBetween(String value1, String value2) {
            addCriterion("CONDITIONS_SHOW between", value1, value2, "conditionsShow");
            return (Criteria) this;
        }

        public Criteria andConditionsShowNotBetween(String value1, String value2) {
            addCriterion("CONDITIONS_SHOW not between", value1, value2, "conditionsShow");
            return (Criteria) this;
        }

        public Criteria andCoupSourceIsNull() {
            addCriterion("COUP_SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andCoupSourceIsNotNull() {
            addCriterion("COUP_SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andCoupSourceEqualTo(String value) {
            addCriterion("COUP_SOURCE =", value, "coupSource");
            return (Criteria) this;
        }

        public Criteria andCoupSourceNotEqualTo(String value) {
            addCriterion("COUP_SOURCE <>", value, "coupSource");
            return (Criteria) this;
        }

        public Criteria andCoupSourceGreaterThan(String value) {
            addCriterion("COUP_SOURCE >", value, "coupSource");
            return (Criteria) this;
        }

        public Criteria andCoupSourceGreaterThanOrEqualTo(String value) {
            addCriterion("COUP_SOURCE >=", value, "coupSource");
            return (Criteria) this;
        }

        public Criteria andCoupSourceLessThan(String value) {
            addCriterion("COUP_SOURCE <", value, "coupSource");
            return (Criteria) this;
        }

        public Criteria andCoupSourceLessThanOrEqualTo(String value) {
            addCriterion("COUP_SOURCE <=", value, "coupSource");
            return (Criteria) this;
        }

        public Criteria andCoupSourceLike(String value) {
            addCriterion("COUP_SOURCE like", value, "coupSource");
            return (Criteria) this;
        }

        public Criteria andCoupSourceNotLike(String value) {
            addCriterion("COUP_SOURCE not like", value, "coupSource");
            return (Criteria) this;
        }

        public Criteria andCoupSourceIn(List<String> values) {
            addCriterion("COUP_SOURCE in", values, "coupSource");
            return (Criteria) this;
        }

        public Criteria andCoupSourceNotIn(List<String> values) {
            addCriterion("COUP_SOURCE not in", values, "coupSource");
            return (Criteria) this;
        }

        public Criteria andCoupSourceBetween(String value1, String value2) {
            addCriterion("COUP_SOURCE between", value1, value2, "coupSource");
            return (Criteria) this;
        }

        public Criteria andCoupSourceNotBetween(String value1, String value2) {
            addCriterion("COUP_SOURCE not between", value1, value2, "coupSource");
            return (Criteria) this;
        }

        public Criteria andUseTimeIsNull() {
            addCriterion("USE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUseTimeIsNotNull() {
            addCriterion("USE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUseTimeEqualTo(Timestamp value) {
            addCriterion("USE_TIME =", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeNotEqualTo(Timestamp value) {
            addCriterion("USE_TIME <>", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeGreaterThan(Timestamp value) {
            addCriterion("USE_TIME >", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("USE_TIME >=", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeLessThan(Timestamp value) {
            addCriterion("USE_TIME <", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("USE_TIME <=", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeIn(List<Timestamp> values) {
            addCriterion("USE_TIME in", values, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeNotIn(List<Timestamp> values) {
            addCriterion("USE_TIME not in", values, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("USE_TIME between", value1, value2, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("USE_TIME not between", value1, value2, "useTime");
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

        public Criteria andBatchIdIsNull() {
            addCriterion("BATCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNotNull() {
            addCriterion("BATCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBatchIdEqualTo(Long value) {
            addCriterion("BATCH_ID =", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotEqualTo(Long value) {
            addCriterion("BATCH_ID <>", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThan(Long value) {
            addCriterion("BATCH_ID >", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThanOrEqualTo(Long value) {
            addCriterion("BATCH_ID >=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThan(Long value) {
            addCriterion("BATCH_ID <", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThanOrEqualTo(Long value) {
            addCriterion("BATCH_ID <=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdIn(List<Long> values) {
            addCriterion("BATCH_ID in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotIn(List<Long> values) {
            addCriterion("BATCH_ID not in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdBetween(Long value1, Long value2) {
            addCriterion("BATCH_ID between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotBetween(Long value1, Long value2) {
            addCriterion("BATCH_ID not between", value1, value2, "batchId");
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

        public Criteria andIfUseIsNull() {
            addCriterion("IF_USE is null");
            return (Criteria) this;
        }

        public Criteria andIfUseIsNotNull() {
            addCriterion("IF_USE is not null");
            return (Criteria) this;
        }

        public Criteria andIfUseEqualTo(String value) {
            addCriterion("IF_USE =", value, "ifUse");
            return (Criteria) this;
        }

        public Criteria andIfUseNotEqualTo(String value) {
            addCriterion("IF_USE <>", value, "ifUse");
            return (Criteria) this;
        }

        public Criteria andIfUseGreaterThan(String value) {
            addCriterion("IF_USE >", value, "ifUse");
            return (Criteria) this;
        }

        public Criteria andIfUseGreaterThanOrEqualTo(String value) {
            addCriterion("IF_USE >=", value, "ifUse");
            return (Criteria) this;
        }

        public Criteria andIfUseLessThan(String value) {
            addCriterion("IF_USE <", value, "ifUse");
            return (Criteria) this;
        }

        public Criteria andIfUseLessThanOrEqualTo(String value) {
            addCriterion("IF_USE <=", value, "ifUse");
            return (Criteria) this;
        }

        public Criteria andIfUseLike(String value) {
            addCriterion("IF_USE like", value, "ifUse");
            return (Criteria) this;
        }

        public Criteria andIfUseNotLike(String value) {
            addCriterion("IF_USE not like", value, "ifUse");
            return (Criteria) this;
        }

        public Criteria andIfUseIn(List<String> values) {
            addCriterion("IF_USE in", values, "ifUse");
            return (Criteria) this;
        }

        public Criteria andIfUseNotIn(List<String> values) {
            addCriterion("IF_USE not in", values, "ifUse");
            return (Criteria) this;
        }

        public Criteria andIfUseBetween(String value1, String value2) {
            addCriterion("IF_USE between", value1, value2, "ifUse");
            return (Criteria) this;
        }

        public Criteria andIfUseNotBetween(String value1, String value2) {
            addCriterion("IF_USE not between", value1, value2, "ifUse");
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
