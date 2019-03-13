package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsSkuSnapCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsSkuSnapCriteria() {
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

        public Criteria andSkuPropsIsNull() {
            addCriterion("SKU_PROPS is null");
            return (Criteria) this;
        }

        public Criteria andSkuPropsIsNotNull() {
            addCriterion("SKU_PROPS is not null");
            return (Criteria) this;
        }

        public Criteria andSkuPropsEqualTo(String value) {
            addCriterion("SKU_PROPS =", value, "skuProps");
            return (Criteria) this;
        }

        public Criteria andSkuPropsNotEqualTo(String value) {
            addCriterion("SKU_PROPS <>", value, "skuProps");
            return (Criteria) this;
        }

        public Criteria andSkuPropsGreaterThan(String value) {
            addCriterion("SKU_PROPS >", value, "skuProps");
            return (Criteria) this;
        }

        public Criteria andSkuPropsGreaterThanOrEqualTo(String value) {
            addCriterion("SKU_PROPS >=", value, "skuProps");
            return (Criteria) this;
        }

        public Criteria andSkuPropsLessThan(String value) {
            addCriterion("SKU_PROPS <", value, "skuProps");
            return (Criteria) this;
        }

        public Criteria andSkuPropsLessThanOrEqualTo(String value) {
            addCriterion("SKU_PROPS <=", value, "skuProps");
            return (Criteria) this;
        }

        public Criteria andSkuPropsLike(String value) {
            addCriterion("SKU_PROPS like", value, "skuProps");
            return (Criteria) this;
        }

        public Criteria andSkuPropsNotLike(String value) {
            addCriterion("SKU_PROPS not like", value, "skuProps");
            return (Criteria) this;
        }

        public Criteria andSkuPropsIn(List<String> values) {
            addCriterion("SKU_PROPS in", values, "skuProps");
            return (Criteria) this;
        }

        public Criteria andSkuPropsNotIn(List<String> values) {
            addCriterion("SKU_PROPS not in", values, "skuProps");
            return (Criteria) this;
        }

        public Criteria andSkuPropsBetween(String value1, String value2) {
            addCriterion("SKU_PROPS between", value1, value2, "skuProps");
            return (Criteria) this;
        }

        public Criteria andSkuPropsNotBetween(String value1, String value2) {
            addCriterion("SKU_PROPS not between", value1, value2, "skuProps");
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

        public Criteria andGdsNameIsNull() {
            addCriterion("GDS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGdsNameIsNotNull() {
            addCriterion("GDS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGdsNameEqualTo(String value) {
            addCriterion("GDS_NAME =", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameNotEqualTo(String value) {
            addCriterion("GDS_NAME <>", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameGreaterThan(String value) {
            addCriterion("GDS_NAME >", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_NAME >=", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameLessThan(String value) {
            addCriterion("GDS_NAME <", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameLessThanOrEqualTo(String value) {
            addCriterion("GDS_NAME <=", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameLike(String value) {
            addCriterion("GDS_NAME like", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameNotLike(String value) {
            addCriterion("GDS_NAME not like", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameIn(List<String> values) {
            addCriterion("GDS_NAME in", values, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameNotIn(List<String> values) {
            addCriterion("GDS_NAME not in", values, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameBetween(String value1, String value2) {
            addCriterion("GDS_NAME between", value1, value2, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameNotBetween(String value1, String value2) {
            addCriterion("GDS_NAME not between", value1, value2, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadIsNull() {
            addCriterion("GDS_SUB_HEAD is null");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadIsNotNull() {
            addCriterion("GDS_SUB_HEAD is not null");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadEqualTo(String value) {
            addCriterion("GDS_SUB_HEAD =", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadNotEqualTo(String value) {
            addCriterion("GDS_SUB_HEAD <>", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadGreaterThan(String value) {
            addCriterion("GDS_SUB_HEAD >", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_SUB_HEAD >=", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadLessThan(String value) {
            addCriterion("GDS_SUB_HEAD <", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadLessThanOrEqualTo(String value) {
            addCriterion("GDS_SUB_HEAD <=", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadLike(String value) {
            addCriterion("GDS_SUB_HEAD like", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadNotLike(String value) {
            addCriterion("GDS_SUB_HEAD not like", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadIn(List<String> values) {
            addCriterion("GDS_SUB_HEAD in", values, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadNotIn(List<String> values) {
            addCriterion("GDS_SUB_HEAD not in", values, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadBetween(String value1, String value2) {
            addCriterion("GDS_SUB_HEAD between", value1, value2, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadNotBetween(String value1, String value2) {
            addCriterion("GDS_SUB_HEAD not between", value1, value2, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGuidePriceIsNull() {
            addCriterion("GUIDE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andGuidePriceIsNotNull() {
            addCriterion("GUIDE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andGuidePriceEqualTo(BigDecimal value) {
            addCriterion("GUIDE_PRICE =", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceNotEqualTo(BigDecimal value) {
            addCriterion("GUIDE_PRICE <>", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceGreaterThan(BigDecimal value) {
            addCriterion("GUIDE_PRICE >", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("GUIDE_PRICE >=", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceLessThan(BigDecimal value) {
            addCriterion("GUIDE_PRICE <", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("GUIDE_PRICE <=", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceIn(List<BigDecimal> values) {
            addCriterion("GUIDE_PRICE in", values, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceNotIn(List<BigDecimal> values) {
            addCriterion("GUIDE_PRICE not in", values, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GUIDE_PRICE between", value1, value2, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GUIDE_PRICE not between", value1, value2, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGdsDescIsNull() {
            addCriterion("GDS_DESC is null");
            return (Criteria) this;
        }

        public Criteria andGdsDescIsNotNull() {
            addCriterion("GDS_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andGdsDescEqualTo(String value) {
            addCriterion("GDS_DESC =", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescNotEqualTo(String value) {
            addCriterion("GDS_DESC <>", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescGreaterThan(String value) {
            addCriterion("GDS_DESC >", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_DESC >=", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescLessThan(String value) {
            addCriterion("GDS_DESC <", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescLessThanOrEqualTo(String value) {
            addCriterion("GDS_DESC <=", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescLike(String value) {
            addCriterion("GDS_DESC like", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescNotLike(String value) {
            addCriterion("GDS_DESC not like", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescIn(List<String> values) {
            addCriterion("GDS_DESC in", values, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescNotIn(List<String> values) {
            addCriterion("GDS_DESC not in", values, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescBetween(String value1, String value2) {
            addCriterion("GDS_DESC between", value1, value2, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescNotBetween(String value1, String value2) {
            addCriterion("GDS_DESC not between", value1, value2, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andMainCatgsIsNull() {
            addCriterion("MAIN_CATGS is null");
            return (Criteria) this;
        }

        public Criteria andMainCatgsIsNotNull() {
            addCriterion("MAIN_CATGS is not null");
            return (Criteria) this;
        }

        public Criteria andMainCatgsEqualTo(String value) {
            addCriterion("MAIN_CATGS =", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsNotEqualTo(String value) {
            addCriterion("MAIN_CATGS <>", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsGreaterThan(String value) {
            addCriterion("MAIN_CATGS >", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsGreaterThanOrEqualTo(String value) {
            addCriterion("MAIN_CATGS >=", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsLessThan(String value) {
            addCriterion("MAIN_CATGS <", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsLessThanOrEqualTo(String value) {
            addCriterion("MAIN_CATGS <=", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsLike(String value) {
            addCriterion("MAIN_CATGS like", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsNotLike(String value) {
            addCriterion("MAIN_CATGS not like", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsIn(List<String> values) {
            addCriterion("MAIN_CATGS in", values, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsNotIn(List<String> values) {
            addCriterion("MAIN_CATGS not in", values, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsBetween(String value1, String value2) {
            addCriterion("MAIN_CATGS between", value1, value2, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsNotBetween(String value1, String value2) {
            addCriterion("MAIN_CATGS not between", value1, value2, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMediaUuidIsNull() {
            addCriterion("MEDIA_UUID is null");
            return (Criteria) this;
        }

        public Criteria andMediaUuidIsNotNull() {
            addCriterion("MEDIA_UUID is not null");
            return (Criteria) this;
        }

        public Criteria andMediaUuidEqualTo(String value) {
            addCriterion("MEDIA_UUID =", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidNotEqualTo(String value) {
            addCriterion("MEDIA_UUID <>", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidGreaterThan(String value) {
            addCriterion("MEDIA_UUID >", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidGreaterThanOrEqualTo(String value) {
            addCriterion("MEDIA_UUID >=", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidLessThan(String value) {
            addCriterion("MEDIA_UUID <", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidLessThanOrEqualTo(String value) {
            addCriterion("MEDIA_UUID <=", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidLike(String value) {
            addCriterion("MEDIA_UUID like", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidNotLike(String value) {
            addCriterion("MEDIA_UUID not like", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidIn(List<String> values) {
            addCriterion("MEDIA_UUID in", values, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidNotIn(List<String> values) {
            addCriterion("MEDIA_UUID not in", values, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidBetween(String value1, String value2) {
            addCriterion("MEDIA_UUID between", value1, value2, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidNotBetween(String value1, String value2) {
            addCriterion("MEDIA_UUID not between", value1, value2, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistIsNull() {
            addCriterion("GDS_PARTLIST is null");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistIsNotNull() {
            addCriterion("GDS_PARTLIST is not null");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistEqualTo(String value) {
            addCriterion("GDS_PARTLIST =", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistNotEqualTo(String value) {
            addCriterion("GDS_PARTLIST <>", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistGreaterThan(String value) {
            addCriterion("GDS_PARTLIST >", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_PARTLIST >=", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistLessThan(String value) {
            addCriterion("GDS_PARTLIST <", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistLessThanOrEqualTo(String value) {
            addCriterion("GDS_PARTLIST <=", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistLike(String value) {
            addCriterion("GDS_PARTLIST like", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistNotLike(String value) {
            addCriterion("GDS_PARTLIST not like", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistIn(List<String> values) {
            addCriterion("GDS_PARTLIST in", values, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistNotIn(List<String> values) {
            addCriterion("GDS_PARTLIST not in", values, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistBetween(String value1, String value2) {
            addCriterion("GDS_PARTLIST between", value1, value2, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistNotBetween(String value1, String value2) {
            addCriterion("GDS_PARTLIST not between", value1, value2, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdIsNull() {
            addCriterion("GDS_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdIsNotNull() {
            addCriterion("GDS_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdEqualTo(String value) {
            addCriterion("GDS_TYPE_ID =", value, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdNotEqualTo(String value) {
            addCriterion("GDS_TYPE_ID <>", value, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdGreaterThan(String value) {
            addCriterion("GDS_TYPE_ID >", value, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_TYPE_ID >=", value, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdLessThan(String value) {
            addCriterion("GDS_TYPE_ID <", value, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdLessThanOrEqualTo(String value) {
            addCriterion("GDS_TYPE_ID <=", value, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdLike(String value) {
            addCriterion("GDS_TYPE_ID like", value, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdNotLike(String value) {
            addCriterion("GDS_TYPE_ID not like", value, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdIn(List<String> values) {
            addCriterion("GDS_TYPE_ID in", values, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdNotIn(List<String> values) {
            addCriterion("GDS_TYPE_ID not in", values, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdBetween(String value1, String value2) {
            addCriterion("GDS_TYPE_ID between", value1, value2, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdNotBetween(String value1, String value2) {
            addCriterion("GDS_TYPE_ID not between", value1, value2, "gdsTypeId");
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

        public Criteria andSkuPriceIsNull() {
            addCriterion("SKU_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andSkuPriceIsNotNull() {
            addCriterion("SKU_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andSkuPriceEqualTo(Long value) {
            addCriterion("SKU_PRICE =", value, "skuPrice");
            return (Criteria) this;
        }

        public Criteria andSkuPriceNotEqualTo(Long value) {
            addCriterion("SKU_PRICE <>", value, "skuPrice");
            return (Criteria) this;
        }

        public Criteria andSkuPriceGreaterThan(Long value) {
            addCriterion("SKU_PRICE >", value, "skuPrice");
            return (Criteria) this;
        }

        public Criteria andSkuPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("SKU_PRICE >=", value, "skuPrice");
            return (Criteria) this;
        }

        public Criteria andSkuPriceLessThan(Long value) {
            addCriterion("SKU_PRICE <", value, "skuPrice");
            return (Criteria) this;
        }

        public Criteria andSkuPriceLessThanOrEqualTo(Long value) {
            addCriterion("SKU_PRICE <=", value, "skuPrice");
            return (Criteria) this;
        }

        public Criteria andSkuPriceIn(List<Long> values) {
            addCriterion("SKU_PRICE in", values, "skuPrice");
            return (Criteria) this;
        }

        public Criteria andSkuPriceNotIn(List<Long> values) {
            addCriterion("SKU_PRICE not in", values, "skuPrice");
            return (Criteria) this;
        }

        public Criteria andSkuPriceBetween(Long value1, Long value2) {
            addCriterion("SKU_PRICE between", value1, value2, "skuPrice");
            return (Criteria) this;
        }

        public Criteria andSkuPriceNotBetween(Long value1, Long value2) {
            addCriterion("SKU_PRICE not between", value1, value2, "skuPrice");
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
