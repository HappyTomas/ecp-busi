package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsGds2PropSnapCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsGds2PropSnapCriteria() {
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

        public Criteria andSnapIdIsNull() {
            addCriterion("SNAP_ID is null");
            return (Criteria) this;
        }

        public Criteria andSnapIdIsNotNull() {
            addCriterion("SNAP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSnapIdEqualTo(Long value) {
            addCriterion("SNAP_ID =", value, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdNotEqualTo(Long value) {
            addCriterion("SNAP_ID <>", value, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdGreaterThan(Long value) {
            addCriterion("SNAP_ID >", value, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SNAP_ID >=", value, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdLessThan(Long value) {
            addCriterion("SNAP_ID <", value, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdLessThanOrEqualTo(Long value) {
            addCriterion("SNAP_ID <=", value, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdIn(List<Long> values) {
            addCriterion("SNAP_ID in", values, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdNotIn(List<Long> values) {
            addCriterion("SNAP_ID not in", values, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdBetween(Long value1, Long value2) {
            addCriterion("SNAP_ID between", value1, value2, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdNotBetween(Long value1, Long value2) {
            addCriterion("SNAP_ID not between", value1, value2, "snapId");
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

        public Criteria andPropNameIsNull() {
            addCriterion("PROP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPropNameIsNotNull() {
            addCriterion("PROP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPropNameEqualTo(String value) {
            addCriterion("PROP_NAME =", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameNotEqualTo(String value) {
            addCriterion("PROP_NAME <>", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameGreaterThan(String value) {
            addCriterion("PROP_NAME >", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_NAME >=", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameLessThan(String value) {
            addCriterion("PROP_NAME <", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameLessThanOrEqualTo(String value) {
            addCriterion("PROP_NAME <=", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameLike(String value) {
            addCriterion("PROP_NAME like", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameNotLike(String value) {
            addCriterion("PROP_NAME not like", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameIn(List<String> values) {
            addCriterion("PROP_NAME in", values, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameNotIn(List<String> values) {
            addCriterion("PROP_NAME not in", values, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameBetween(String value1, String value2) {
            addCriterion("PROP_NAME between", value1, value2, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameNotBetween(String value1, String value2) {
            addCriterion("PROP_NAME not between", value1, value2, "propName");
            return (Criteria) this;
        }

        public Criteria andPropValueIdIsNull() {
            addCriterion("PROP_VALUE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPropValueIdIsNotNull() {
            addCriterion("PROP_VALUE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPropValueIdEqualTo(Long value) {
            addCriterion("PROP_VALUE_ID =", value, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdNotEqualTo(Long value) {
            addCriterion("PROP_VALUE_ID <>", value, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdGreaterThan(Long value) {
            addCriterion("PROP_VALUE_ID >", value, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROP_VALUE_ID >=", value, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdLessThan(Long value) {
            addCriterion("PROP_VALUE_ID <", value, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdLessThanOrEqualTo(Long value) {
            addCriterion("PROP_VALUE_ID <=", value, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdIn(List<Long> values) {
            addCriterion("PROP_VALUE_ID in", values, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdNotIn(List<Long> values) {
            addCriterion("PROP_VALUE_ID not in", values, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdBetween(Long value1, Long value2) {
            addCriterion("PROP_VALUE_ID between", value1, value2, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdNotBetween(Long value1, Long value2) {
            addCriterion("PROP_VALUE_ID not between", value1, value2, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIsNull() {
            addCriterion("PROP_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andPropValueIsNotNull() {
            addCriterion("PROP_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andPropValueEqualTo(String value) {
            addCriterion("PROP_VALUE =", value, "propValue");
            return (Criteria) this;
        }

        public Criteria andPropValueNotEqualTo(String value) {
            addCriterion("PROP_VALUE <>", value, "propValue");
            return (Criteria) this;
        }

        public Criteria andPropValueGreaterThan(String value) {
            addCriterion("PROP_VALUE >", value, "propValue");
            return (Criteria) this;
        }

        public Criteria andPropValueGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_VALUE >=", value, "propValue");
            return (Criteria) this;
        }

        public Criteria andPropValueLessThan(String value) {
            addCriterion("PROP_VALUE <", value, "propValue");
            return (Criteria) this;
        }

        public Criteria andPropValueLessThanOrEqualTo(String value) {
            addCriterion("PROP_VALUE <=", value, "propValue");
            return (Criteria) this;
        }

        public Criteria andPropValueLike(String value) {
            addCriterion("PROP_VALUE like", value, "propValue");
            return (Criteria) this;
        }

        public Criteria andPropValueNotLike(String value) {
            addCriterion("PROP_VALUE not like", value, "propValue");
            return (Criteria) this;
        }

        public Criteria andPropValueIn(List<String> values) {
            addCriterion("PROP_VALUE in", values, "propValue");
            return (Criteria) this;
        }

        public Criteria andPropValueNotIn(List<String> values) {
            addCriterion("PROP_VALUE not in", values, "propValue");
            return (Criteria) this;
        }

        public Criteria andPropValueBetween(String value1, String value2) {
            addCriterion("PROP_VALUE between", value1, value2, "propValue");
            return (Criteria) this;
        }

        public Criteria andPropValueNotBetween(String value1, String value2) {
            addCriterion("PROP_VALUE not between", value1, value2, "propValue");
            return (Criteria) this;
        }

        public Criteria andPropTypeIsNull() {
            addCriterion("PROP_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPropTypeIsNotNull() {
            addCriterion("PROP_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPropTypeEqualTo(String value) {
            addCriterion("PROP_TYPE =", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeNotEqualTo(String value) {
            addCriterion("PROP_TYPE <>", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeGreaterThan(String value) {
            addCriterion("PROP_TYPE >", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_TYPE >=", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeLessThan(String value) {
            addCriterion("PROP_TYPE <", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeLessThanOrEqualTo(String value) {
            addCriterion("PROP_TYPE <=", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeLike(String value) {
            addCriterion("PROP_TYPE like", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeNotLike(String value) {
            addCriterion("PROP_TYPE not like", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeIn(List<String> values) {
            addCriterion("PROP_TYPE in", values, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeNotIn(List<String> values) {
            addCriterion("PROP_TYPE not in", values, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeBetween(String value1, String value2) {
            addCriterion("PROP_TYPE between", value1, value2, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeNotBetween(String value1, String value2) {
            addCriterion("PROP_TYPE not between", value1, value2, "propType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeIsNull() {
            addCriterion("PROP_VALUE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeIsNotNull() {
            addCriterion("PROP_VALUE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeEqualTo(String value) {
            addCriterion("PROP_VALUE_TYPE =", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeNotEqualTo(String value) {
            addCriterion("PROP_VALUE_TYPE <>", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeGreaterThan(String value) {
            addCriterion("PROP_VALUE_TYPE >", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_VALUE_TYPE >=", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeLessThan(String value) {
            addCriterion("PROP_VALUE_TYPE <", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeLessThanOrEqualTo(String value) {
            addCriterion("PROP_VALUE_TYPE <=", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeLike(String value) {
            addCriterion("PROP_VALUE_TYPE like", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeNotLike(String value) {
            addCriterion("PROP_VALUE_TYPE not like", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeIn(List<String> values) {
            addCriterion("PROP_VALUE_TYPE in", values, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeNotIn(List<String> values) {
            addCriterion("PROP_VALUE_TYPE not in", values, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeBetween(String value1, String value2) {
            addCriterion("PROP_VALUE_TYPE between", value1, value2, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeNotBetween(String value1, String value2) {
            addCriterion("PROP_VALUE_TYPE not between", value1, value2, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropRtypeIsNull() {
            addCriterion("PROP_RTYPE is null");
            return (Criteria) this;
        }

        public Criteria andPropRtypeIsNotNull() {
            addCriterion("PROP_RTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPropRtypeEqualTo(String value) {
            addCriterion("PROP_RTYPE =", value, "propRtype");
            return (Criteria) this;
        }

        public Criteria andPropRtypeNotEqualTo(String value) {
            addCriterion("PROP_RTYPE <>", value, "propRtype");
            return (Criteria) this;
        }

        public Criteria andPropRtypeGreaterThan(String value) {
            addCriterion("PROP_RTYPE >", value, "propRtype");
            return (Criteria) this;
        }

        public Criteria andPropRtypeGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_RTYPE >=", value, "propRtype");
            return (Criteria) this;
        }

        public Criteria andPropRtypeLessThan(String value) {
            addCriterion("PROP_RTYPE <", value, "propRtype");
            return (Criteria) this;
        }

        public Criteria andPropRtypeLessThanOrEqualTo(String value) {
            addCriterion("PROP_RTYPE <=", value, "propRtype");
            return (Criteria) this;
        }

        public Criteria andPropRtypeLike(String value) {
            addCriterion("PROP_RTYPE like", value, "propRtype");
            return (Criteria) this;
        }

        public Criteria andPropRtypeNotLike(String value) {
            addCriterion("PROP_RTYPE not like", value, "propRtype");
            return (Criteria) this;
        }

        public Criteria andPropRtypeIn(List<String> values) {
            addCriterion("PROP_RTYPE in", values, "propRtype");
            return (Criteria) this;
        }

        public Criteria andPropRtypeNotIn(List<String> values) {
            addCriterion("PROP_RTYPE not in", values, "propRtype");
            return (Criteria) this;
        }

        public Criteria andPropRtypeBetween(String value1, String value2) {
            addCriterion("PROP_RTYPE between", value1, value2, "propRtype");
            return (Criteria) this;
        }

        public Criteria andPropRtypeNotBetween(String value1, String value2) {
            addCriterion("PROP_RTYPE not between", value1, value2, "propRtype");
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

        public Criteria andIfCheckIsNull() {
            addCriterion("IF_CHECK is null");
            return (Criteria) this;
        }

        public Criteria andIfCheckIsNotNull() {
            addCriterion("IF_CHECK is not null");
            return (Criteria) this;
        }

        public Criteria andIfCheckEqualTo(String value) {
            addCriterion("IF_CHECK =", value, "ifCheck");
            return (Criteria) this;
        }

        public Criteria andIfCheckNotEqualTo(String value) {
            addCriterion("IF_CHECK <>", value, "ifCheck");
            return (Criteria) this;
        }

        public Criteria andIfCheckGreaterThan(String value) {
            addCriterion("IF_CHECK >", value, "ifCheck");
            return (Criteria) this;
        }

        public Criteria andIfCheckGreaterThanOrEqualTo(String value) {
            addCriterion("IF_CHECK >=", value, "ifCheck");
            return (Criteria) this;
        }

        public Criteria andIfCheckLessThan(String value) {
            addCriterion("IF_CHECK <", value, "ifCheck");
            return (Criteria) this;
        }

        public Criteria andIfCheckLessThanOrEqualTo(String value) {
            addCriterion("IF_CHECK <=", value, "ifCheck");
            return (Criteria) this;
        }

        public Criteria andIfCheckLike(String value) {
            addCriterion("IF_CHECK like", value, "ifCheck");
            return (Criteria) this;
        }

        public Criteria andIfCheckNotLike(String value) {
            addCriterion("IF_CHECK not like", value, "ifCheck");
            return (Criteria) this;
        }

        public Criteria andIfCheckIn(List<String> values) {
            addCriterion("IF_CHECK in", values, "ifCheck");
            return (Criteria) this;
        }

        public Criteria andIfCheckNotIn(List<String> values) {
            addCriterion("IF_CHECK not in", values, "ifCheck");
            return (Criteria) this;
        }

        public Criteria andIfCheckBetween(String value1, String value2) {
            addCriterion("IF_CHECK between", value1, value2, "ifCheck");
            return (Criteria) this;
        }

        public Criteria andIfCheckNotBetween(String value1, String value2) {
            addCriterion("IF_CHECK not between", value1, value2, "ifCheck");
            return (Criteria) this;
        }

        public Criteria andIfBasticIsNull() {
            addCriterion("IF_BASTIC is null");
            return (Criteria) this;
        }

        public Criteria andIfBasticIsNotNull() {
            addCriterion("IF_BASTIC is not null");
            return (Criteria) this;
        }

        public Criteria andIfBasticEqualTo(String value) {
            addCriterion("IF_BASTIC =", value, "ifBastic");
            return (Criteria) this;
        }

        public Criteria andIfBasticNotEqualTo(String value) {
            addCriterion("IF_BASTIC <>", value, "ifBastic");
            return (Criteria) this;
        }

        public Criteria andIfBasticGreaterThan(String value) {
            addCriterion("IF_BASTIC >", value, "ifBastic");
            return (Criteria) this;
        }

        public Criteria andIfBasticGreaterThanOrEqualTo(String value) {
            addCriterion("IF_BASTIC >=", value, "ifBastic");
            return (Criteria) this;
        }

        public Criteria andIfBasticLessThan(String value) {
            addCriterion("IF_BASTIC <", value, "ifBastic");
            return (Criteria) this;
        }

        public Criteria andIfBasticLessThanOrEqualTo(String value) {
            addCriterion("IF_BASTIC <=", value, "ifBastic");
            return (Criteria) this;
        }

        public Criteria andIfBasticLike(String value) {
            addCriterion("IF_BASTIC like", value, "ifBastic");
            return (Criteria) this;
        }

        public Criteria andIfBasticNotLike(String value) {
            addCriterion("IF_BASTIC not like", value, "ifBastic");
            return (Criteria) this;
        }

        public Criteria andIfBasticIn(List<String> values) {
            addCriterion("IF_BASTIC in", values, "ifBastic");
            return (Criteria) this;
        }

        public Criteria andIfBasticNotIn(List<String> values) {
            addCriterion("IF_BASTIC not in", values, "ifBastic");
            return (Criteria) this;
        }

        public Criteria andIfBasticBetween(String value1, String value2) {
            addCriterion("IF_BASTIC between", value1, value2, "ifBastic");
            return (Criteria) this;
        }

        public Criteria andIfBasticNotBetween(String value1, String value2) {
            addCriterion("IF_BASTIC not between", value1, value2, "ifBastic");
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
