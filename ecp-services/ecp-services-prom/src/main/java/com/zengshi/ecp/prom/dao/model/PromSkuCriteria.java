package com.zengshi.ecp.prom.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PromSkuCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PromSkuCriteria() {
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

        public Criteria andPromIdIsNull() {
            addCriterion("PROM_ID is null");
            return (Criteria) this;
        }

        public Criteria andPromIdIsNotNull() {
            addCriterion("PROM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPromIdEqualTo(Long value) {
            addCriterion("PROM_ID =", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotEqualTo(Long value) {
            addCriterion("PROM_ID <>", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdGreaterThan(Long value) {
            addCriterion("PROM_ID >", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROM_ID >=", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdLessThan(Long value) {
            addCriterion("PROM_ID <", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdLessThanOrEqualTo(Long value) {
            addCriterion("PROM_ID <=", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdIn(List<Long> values) {
            addCriterion("PROM_ID in", values, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotIn(List<Long> values) {
            addCriterion("PROM_ID not in", values, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdBetween(Long value1, Long value2) {
            addCriterion("PROM_ID between", value1, value2, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotBetween(Long value1, Long value2) {
            addCriterion("PROM_ID not between", value1, value2, "promId");
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

        public Criteria andJoinTypeIsNull() {
            addCriterion("JOIN_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andJoinTypeIsNotNull() {
            addCriterion("JOIN_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andJoinTypeEqualTo(String value) {
            addCriterion("JOIN_TYPE =", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeNotEqualTo(String value) {
            addCriterion("JOIN_TYPE <>", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeGreaterThan(String value) {
            addCriterion("JOIN_TYPE >", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeGreaterThanOrEqualTo(String value) {
            addCriterion("JOIN_TYPE >=", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeLessThan(String value) {
            addCriterion("JOIN_TYPE <", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeLessThanOrEqualTo(String value) {
            addCriterion("JOIN_TYPE <=", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeLike(String value) {
            addCriterion("JOIN_TYPE like", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeNotLike(String value) {
            addCriterion("JOIN_TYPE not like", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeIn(List<String> values) {
            addCriterion("JOIN_TYPE in", values, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeNotIn(List<String> values) {
            addCriterion("JOIN_TYPE not in", values, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeBetween(String value1, String value2) {
            addCriterion("JOIN_TYPE between", value1, value2, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeNotBetween(String value1, String value2) {
            addCriterion("JOIN_TYPE not between", value1, value2, "joinType");
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

        public Criteria andGdsIdIsNull() {
            addCriterion("GDS_ID is null");
            return (Criteria) this;
        }

        public Criteria andGdsIdIsNotNull() {
            addCriterion("GDS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGdsIdEqualTo(Long value) {
            addCriterion("GDS_ID =", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdNotEqualTo(Long value) {
            addCriterion("GDS_ID <>", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdGreaterThan(Long value) {
            addCriterion("GDS_ID >", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("GDS_ID >=", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdLessThan(Long value) {
            addCriterion("GDS_ID <", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdLessThanOrEqualTo(Long value) {
            addCriterion("GDS_ID <=", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdIn(List<Long> values) {
            addCriterion("GDS_ID in", values, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdNotIn(List<Long> values) {
            addCriterion("GDS_ID not in", values, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdBetween(Long value1, Long value2) {
            addCriterion("GDS_ID between", value1, value2, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdNotBetween(Long value1, Long value2) {
            addCriterion("GDS_ID not between", value1, value2, "gdsId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNull() {
            addCriterion("SKU_ID is null");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNotNull() {
            addCriterion("SKU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSkuIdEqualTo(Long value) {
            addCriterion("SKU_ID =", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotEqualTo(Long value) {
            addCriterion("SKU_ID <>", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThan(Long value) {
            addCriterion("SKU_ID >", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SKU_ID >=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThan(Long value) {
            addCriterion("SKU_ID <", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThanOrEqualTo(Long value) {
            addCriterion("SKU_ID <=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIn(List<Long> values) {
            addCriterion("SKU_ID in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotIn(List<Long> values) {
            addCriterion("SKU_ID not in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdBetween(Long value1, Long value2) {
            addCriterion("SKU_ID between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotBetween(Long value1, Long value2) {
            addCriterion("SKU_ID not between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("PRIORITY is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("PRIORITY is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Long value) {
            addCriterion("PRIORITY =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Long value) {
            addCriterion("PRIORITY <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Long value) {
            addCriterion("PRIORITY >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Long value) {
            addCriterion("PRIORITY >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Long value) {
            addCriterion("PRIORITY <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Long value) {
            addCriterion("PRIORITY <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Long> values) {
            addCriterion("PRIORITY in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Long> values) {
            addCriterion("PRIORITY not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Long value1, Long value2) {
            addCriterion("PRIORITY between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Long value1, Long value2) {
            addCriterion("PRIORITY not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPromClassIsNull() {
            addCriterion("PROM_CLASS is null");
            return (Criteria) this;
        }

        public Criteria andPromClassIsNotNull() {
            addCriterion("PROM_CLASS is not null");
            return (Criteria) this;
        }

        public Criteria andPromClassEqualTo(String value) {
            addCriterion("PROM_CLASS =", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassNotEqualTo(String value) {
            addCriterion("PROM_CLASS <>", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassGreaterThan(String value) {
            addCriterion("PROM_CLASS >", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassGreaterThanOrEqualTo(String value) {
            addCriterion("PROM_CLASS >=", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassLessThan(String value) {
            addCriterion("PROM_CLASS <", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassLessThanOrEqualTo(String value) {
            addCriterion("PROM_CLASS <=", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassLike(String value) {
            addCriterion("PROM_CLASS like", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassNotLike(String value) {
            addCriterion("PROM_CLASS not like", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassIn(List<String> values) {
            addCriterion("PROM_CLASS in", values, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassNotIn(List<String> values) {
            addCriterion("PROM_CLASS not in", values, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassBetween(String value1, String value2) {
            addCriterion("PROM_CLASS between", value1, value2, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassNotBetween(String value1, String value2) {
            addCriterion("PROM_CLASS not between", value1, value2, "promClass");
            return (Criteria) this;
        }

        public Criteria andMatchTypeIsNull() {
            addCriterion("MATCH_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andMatchTypeIsNotNull() {
            addCriterion("MATCH_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMatchTypeEqualTo(String value) {
            addCriterion("MATCH_TYPE =", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeNotEqualTo(String value) {
            addCriterion("MATCH_TYPE <>", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeGreaterThan(String value) {
            addCriterion("MATCH_TYPE >", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeGreaterThanOrEqualTo(String value) {
            addCriterion("MATCH_TYPE >=", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeLessThan(String value) {
            addCriterion("MATCH_TYPE <", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeLessThanOrEqualTo(String value) {
            addCriterion("MATCH_TYPE <=", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeLike(String value) {
            addCriterion("MATCH_TYPE like", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeNotLike(String value) {
            addCriterion("MATCH_TYPE not like", value, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeIn(List<String> values) {
            addCriterion("MATCH_TYPE in", values, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeNotIn(List<String> values) {
            addCriterion("MATCH_TYPE not in", values, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeBetween(String value1, String value2) {
            addCriterion("MATCH_TYPE between", value1, value2, "matchType");
            return (Criteria) this;
        }

        public Criteria andMatchTypeNotBetween(String value1, String value2) {
            addCriterion("MATCH_TYPE not between", value1, value2, "matchType");
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

        public Criteria andStartTimeIsNull() {
            addCriterion("START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Timestamp value) {
            addCriterion("START_TIME =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Timestamp value) {
            addCriterion("START_TIME <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Timestamp value) {
            addCriterion("START_TIME >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("START_TIME >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Timestamp value) {
            addCriterion("START_TIME <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("START_TIME <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Timestamp> values) {
            addCriterion("START_TIME in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Timestamp> values) {
            addCriterion("START_TIME not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("START_TIME between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("START_TIME not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Timestamp value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Timestamp value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Timestamp value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Timestamp value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Timestamp> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Timestamp> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andIfShowIsNull() {
            addCriterion("IF_SHOW is null");
            return (Criteria) this;
        }

        public Criteria andIfShowIsNotNull() {
            addCriterion("IF_SHOW is not null");
            return (Criteria) this;
        }

        public Criteria andIfShowEqualTo(String value) {
            addCriterion("IF_SHOW =", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowNotEqualTo(String value) {
            addCriterion("IF_SHOW <>", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowGreaterThan(String value) {
            addCriterion("IF_SHOW >", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowGreaterThanOrEqualTo(String value) {
            addCriterion("IF_SHOW >=", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowLessThan(String value) {
            addCriterion("IF_SHOW <", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowLessThanOrEqualTo(String value) {
            addCriterion("IF_SHOW <=", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowLike(String value) {
            addCriterion("IF_SHOW like", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowNotLike(String value) {
            addCriterion("IF_SHOW not like", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowIn(List<String> values) {
            addCriterion("IF_SHOW in", values, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowNotIn(List<String> values) {
            addCriterion("IF_SHOW not in", values, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowBetween(String value1, String value2) {
            addCriterion("IF_SHOW between", value1, value2, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowNotBetween(String value1, String value2) {
            addCriterion("IF_SHOW not between", value1, value2, "ifShow");
            return (Criteria) this;
        }

        public Criteria andPromCntIsNull() {
            addCriterion("PROM_CNT is null");
            return (Criteria) this;
        }

        public Criteria andPromCntIsNotNull() {
            addCriterion("PROM_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andPromCntEqualTo(Long value) {
            addCriterion("PROM_CNT =", value, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntNotEqualTo(Long value) {
            addCriterion("PROM_CNT <>", value, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntGreaterThan(Long value) {
            addCriterion("PROM_CNT >", value, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntGreaterThanOrEqualTo(Long value) {
            addCriterion("PROM_CNT >=", value, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntLessThan(Long value) {
            addCriterion("PROM_CNT <", value, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntLessThanOrEqualTo(Long value) {
            addCriterion("PROM_CNT <=", value, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntIn(List<Long> values) {
            addCriterion("PROM_CNT in", values, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntNotIn(List<Long> values) {
            addCriterion("PROM_CNT not in", values, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntBetween(Long value1, Long value2) {
            addCriterion("PROM_CNT between", value1, value2, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntNotBetween(Long value1, Long value2) {
            addCriterion("PROM_CNT not between", value1, value2, "promCnt");
            return (Criteria) this;
        }

        public Criteria andIfCompositIsNull() {
            addCriterion("IF_COMPOSIT is null");
            return (Criteria) this;
        }

        public Criteria andIfCompositIsNotNull() {
            addCriterion("IF_COMPOSIT is not null");
            return (Criteria) this;
        }

        public Criteria andIfCompositEqualTo(String value) {
            addCriterion("IF_COMPOSIT =", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositNotEqualTo(String value) {
            addCriterion("IF_COMPOSIT <>", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositGreaterThan(String value) {
            addCriterion("IF_COMPOSIT >", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositGreaterThanOrEqualTo(String value) {
            addCriterion("IF_COMPOSIT >=", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositLessThan(String value) {
            addCriterion("IF_COMPOSIT <", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositLessThanOrEqualTo(String value) {
            addCriterion("IF_COMPOSIT <=", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositLike(String value) {
            addCriterion("IF_COMPOSIT like", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositNotLike(String value) {
            addCriterion("IF_COMPOSIT not like", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositIn(List<String> values) {
            addCriterion("IF_COMPOSIT in", values, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositNotIn(List<String> values) {
            addCriterion("IF_COMPOSIT not in", values, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositBetween(String value1, String value2) {
            addCriterion("IF_COMPOSIT between", value1, value2, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositNotBetween(String value1, String value2) {
            addCriterion("IF_COMPOSIT not between", value1, value2, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeIsNull() {
            addCriterion("SHOW_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeIsNotNull() {
            addCriterion("SHOW_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeEqualTo(Timestamp value) {
            addCriterion("SHOW_START_TIME =", value, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeNotEqualTo(Timestamp value) {
            addCriterion("SHOW_START_TIME <>", value, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeGreaterThan(Timestamp value) {
            addCriterion("SHOW_START_TIME >", value, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("SHOW_START_TIME >=", value, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeLessThan(Timestamp value) {
            addCriterion("SHOW_START_TIME <", value, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("SHOW_START_TIME <=", value, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeIn(List<Timestamp> values) {
            addCriterion("SHOW_START_TIME in", values, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeNotIn(List<Timestamp> values) {
            addCriterion("SHOW_START_TIME not in", values, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SHOW_START_TIME between", value1, value2, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SHOW_START_TIME not between", value1, value2, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeIsNull() {
            addCriterion("SHOW_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeIsNotNull() {
            addCriterion("SHOW_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeEqualTo(Timestamp value) {
            addCriterion("SHOW_END_TIME =", value, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeNotEqualTo(Timestamp value) {
            addCriterion("SHOW_END_TIME <>", value, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeGreaterThan(Timestamp value) {
            addCriterion("SHOW_END_TIME >", value, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("SHOW_END_TIME >=", value, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeLessThan(Timestamp value) {
            addCriterion("SHOW_END_TIME <", value, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("SHOW_END_TIME <=", value, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeIn(List<Timestamp> values) {
            addCriterion("SHOW_END_TIME in", values, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeNotIn(List<Timestamp> values) {
            addCriterion("SHOW_END_TIME not in", values, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SHOW_END_TIME between", value1, value2, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SHOW_END_TIME not between", value1, value2, "showEndTime");
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

        public Criteria andIfValidIsNull() {
            addCriterion("IF_VALID is null");
            return (Criteria) this;
        }

        public Criteria andIfValidIsNotNull() {
            addCriterion("IF_VALID is not null");
            return (Criteria) this;
        }

        public Criteria andIfValidEqualTo(String value) {
            addCriterion("IF_VALID =", value, "ifValid");
            return (Criteria) this;
        }

        public Criteria andIfValidNotEqualTo(String value) {
            addCriterion("IF_VALID <>", value, "ifValid");
            return (Criteria) this;
        }

        public Criteria andIfValidGreaterThan(String value) {
            addCriterion("IF_VALID >", value, "ifValid");
            return (Criteria) this;
        }

        public Criteria andIfValidGreaterThanOrEqualTo(String value) {
            addCriterion("IF_VALID >=", value, "ifValid");
            return (Criteria) this;
        }

        public Criteria andIfValidLessThan(String value) {
            addCriterion("IF_VALID <", value, "ifValid");
            return (Criteria) this;
        }

        public Criteria andIfValidLessThanOrEqualTo(String value) {
            addCriterion("IF_VALID <=", value, "ifValid");
            return (Criteria) this;
        }

        public Criteria andIfValidLike(String value) {
            addCriterion("IF_VALID like", value, "ifValid");
            return (Criteria) this;
        }

        public Criteria andIfValidNotLike(String value) {
            addCriterion("IF_VALID not like", value, "ifValid");
            return (Criteria) this;
        }

        public Criteria andIfValidIn(List<String> values) {
            addCriterion("IF_VALID in", values, "ifValid");
            return (Criteria) this;
        }

        public Criteria andIfValidNotIn(List<String> values) {
            addCriterion("IF_VALID not in", values, "ifValid");
            return (Criteria) this;
        }

        public Criteria andIfValidBetween(String value1, String value2) {
            addCriterion("IF_VALID between", value1, value2, "ifValid");
            return (Criteria) this;
        }

        public Criteria andIfValidNotBetween(String value1, String value2) {
            addCriterion("IF_VALID not between", value1, value2, "ifValid");
            return (Criteria) this;
        }

        public Criteria andPriceTypeIsNull() {
            addCriterion("PRICE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPriceTypeIsNotNull() {
            addCriterion("PRICE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPriceTypeEqualTo(String value) {
            addCriterion("PRICE_TYPE =", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotEqualTo(String value) {
            addCriterion("PRICE_TYPE <>", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeGreaterThan(String value) {
            addCriterion("PRICE_TYPE >", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PRICE_TYPE >=", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeLessThan(String value) {
            addCriterion("PRICE_TYPE <", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeLessThanOrEqualTo(String value) {
            addCriterion("PRICE_TYPE <=", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeLike(String value) {
            addCriterion("PRICE_TYPE like", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotLike(String value) {
            addCriterion("PRICE_TYPE not like", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeIn(List<String> values) {
            addCriterion("PRICE_TYPE in", values, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotIn(List<String> values) {
            addCriterion("PRICE_TYPE not in", values, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeBetween(String value1, String value2) {
            addCriterion("PRICE_TYPE between", value1, value2, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotBetween(String value1, String value2) {
            addCriterion("PRICE_TYPE not between", value1, value2, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceValueIsNull() {
            addCriterion("PRICE_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andPriceValueIsNotNull() {
            addCriterion("PRICE_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andPriceValueEqualTo(Long value) {
            addCriterion("PRICE_VALUE =", value, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueNotEqualTo(Long value) {
            addCriterion("PRICE_VALUE <>", value, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueGreaterThan(Long value) {
            addCriterion("PRICE_VALUE >", value, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueGreaterThanOrEqualTo(Long value) {
            addCriterion("PRICE_VALUE >=", value, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueLessThan(Long value) {
            addCriterion("PRICE_VALUE <", value, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueLessThanOrEqualTo(Long value) {
            addCriterion("PRICE_VALUE <=", value, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueIn(List<Long> values) {
            addCriterion("PRICE_VALUE in", values, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueNotIn(List<Long> values) {
            addCriterion("PRICE_VALUE not in", values, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueBetween(Long value1, Long value2) {
            addCriterion("PRICE_VALUE between", value1, value2, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueNotBetween(Long value1, Long value2) {
            addCriterion("PRICE_VALUE not between", value1, value2, "priceValue");
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