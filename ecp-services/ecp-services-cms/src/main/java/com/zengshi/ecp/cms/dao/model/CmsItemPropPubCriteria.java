package com.zengshi.ecp.cms.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CmsItemPropPubCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CmsItemPropPubCriteria() {
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

        public Criteria andItemPropIdIsNull() {
            addCriterion("ITEM_PROP_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemPropIdIsNotNull() {
            addCriterion("ITEM_PROP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemPropIdEqualTo(Long value) {
            addCriterion("ITEM_PROP_ID =", value, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdNotEqualTo(Long value) {
            addCriterion("ITEM_PROP_ID <>", value, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdGreaterThan(Long value) {
            addCriterion("ITEM_PROP_ID >", value, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ITEM_PROP_ID >=", value, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdLessThan(Long value) {
            addCriterion("ITEM_PROP_ID <", value, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdLessThanOrEqualTo(Long value) {
            addCriterion("ITEM_PROP_ID <=", value, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdIn(List<Long> values) {
            addCriterion("ITEM_PROP_ID in", values, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdNotIn(List<Long> values) {
            addCriterion("ITEM_PROP_ID not in", values, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdBetween(Long value1, Long value2) {
            addCriterion("ITEM_PROP_ID between", value1, value2, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdNotBetween(Long value1, Long value2) {
            addCriterion("ITEM_PROP_ID not between", value1, value2, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andPageIdIsNull() {
            addCriterion("PAGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPageIdIsNotNull() {
            addCriterion("PAGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPageIdEqualTo(Long value) {
            addCriterion("PAGE_ID =", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotEqualTo(Long value) {
            addCriterion("PAGE_ID <>", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdGreaterThan(Long value) {
            addCriterion("PAGE_ID >", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PAGE_ID >=", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdLessThan(Long value) {
            addCriterion("PAGE_ID <", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdLessThanOrEqualTo(Long value) {
            addCriterion("PAGE_ID <=", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdIn(List<Long> values) {
            addCriterion("PAGE_ID in", values, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotIn(List<Long> values) {
            addCriterion("PAGE_ID not in", values, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdBetween(Long value1, Long value2) {
            addCriterion("PAGE_ID between", value1, value2, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotBetween(Long value1, Long value2) {
            addCriterion("PAGE_ID not between", value1, value2, "pageId");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(Long value) {
            addCriterion("ITEM_ID =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(Long value) {
            addCriterion("ITEM_ID <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(Long value) {
            addCriterion("ITEM_ID >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ITEM_ID >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(Long value) {
            addCriterion("ITEM_ID <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(Long value) {
            addCriterion("ITEM_ID <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<Long> values) {
            addCriterion("ITEM_ID in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<Long> values) {
            addCriterion("ITEM_ID not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(Long value1, Long value2) {
            addCriterion("ITEM_ID between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(Long value1, Long value2) {
            addCriterion("ITEM_ID not between", value1, value2, "itemId");
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

        public Criteria andPropValueIdEqualTo(String value) {
            addCriterion("PROP_VALUE_ID =", value, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdNotEqualTo(String value) {
            addCriterion("PROP_VALUE_ID <>", value, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdGreaterThan(String value) {
            addCriterion("PROP_VALUE_ID >", value, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_VALUE_ID >=", value, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdLessThan(String value) {
            addCriterion("PROP_VALUE_ID <", value, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdLessThanOrEqualTo(String value) {
            addCriterion("PROP_VALUE_ID <=", value, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdLike(String value) {
            addCriterion("PROP_VALUE_ID like", value, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdNotLike(String value) {
            addCriterion("PROP_VALUE_ID not like", value, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdIn(List<String> values) {
            addCriterion("PROP_VALUE_ID in", values, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdNotIn(List<String> values) {
            addCriterion("PROP_VALUE_ID not in", values, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdBetween(String value1, String value2) {
            addCriterion("PROP_VALUE_ID between", value1, value2, "propValueId");
            return (Criteria) this;
        }

        public Criteria andPropValueIdNotBetween(String value1, String value2) {
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

        public Criteria andControlPropIdIsNull() {
            addCriterion("CONTROL_PROP_ID is null");
            return (Criteria) this;
        }

        public Criteria andControlPropIdIsNotNull() {
            addCriterion("CONTROL_PROP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andControlPropIdEqualTo(Long value) {
            addCriterion("CONTROL_PROP_ID =", value, "controlPropId");
            return (Criteria) this;
        }

        public Criteria andControlPropIdNotEqualTo(Long value) {
            addCriterion("CONTROL_PROP_ID <>", value, "controlPropId");
            return (Criteria) this;
        }

        public Criteria andControlPropIdGreaterThan(Long value) {
            addCriterion("CONTROL_PROP_ID >", value, "controlPropId");
            return (Criteria) this;
        }

        public Criteria andControlPropIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CONTROL_PROP_ID >=", value, "controlPropId");
            return (Criteria) this;
        }

        public Criteria andControlPropIdLessThan(Long value) {
            addCriterion("CONTROL_PROP_ID <", value, "controlPropId");
            return (Criteria) this;
        }

        public Criteria andControlPropIdLessThanOrEqualTo(Long value) {
            addCriterion("CONTROL_PROP_ID <=", value, "controlPropId");
            return (Criteria) this;
        }

        public Criteria andControlPropIdIn(List<Long> values) {
            addCriterion("CONTROL_PROP_ID in", values, "controlPropId");
            return (Criteria) this;
        }

        public Criteria andControlPropIdNotIn(List<Long> values) {
            addCriterion("CONTROL_PROP_ID not in", values, "controlPropId");
            return (Criteria) this;
        }

        public Criteria andControlPropIdBetween(Long value1, Long value2) {
            addCriterion("CONTROL_PROP_ID between", value1, value2, "controlPropId");
            return (Criteria) this;
        }

        public Criteria andControlPropIdNotBetween(Long value1, Long value2) {
            addCriterion("CONTROL_PROP_ID not between", value1, value2, "controlPropId");
            return (Criteria) this;
        }

        public Criteria andPropGroupIdIsNull() {
            addCriterion("PROP_GROUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andPropGroupIdIsNotNull() {
            addCriterion("PROP_GROUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPropGroupIdEqualTo(Integer value) {
            addCriterion("PROP_GROUP_ID =", value, "propGroupId");
            return (Criteria) this;
        }

        public Criteria andPropGroupIdNotEqualTo(Integer value) {
            addCriterion("PROP_GROUP_ID <>", value, "propGroupId");
            return (Criteria) this;
        }

        public Criteria andPropGroupIdGreaterThan(Integer value) {
            addCriterion("PROP_GROUP_ID >", value, "propGroupId");
            return (Criteria) this;
        }

        public Criteria andPropGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PROP_GROUP_ID >=", value, "propGroupId");
            return (Criteria) this;
        }

        public Criteria andPropGroupIdLessThan(Integer value) {
            addCriterion("PROP_GROUP_ID <", value, "propGroupId");
            return (Criteria) this;
        }

        public Criteria andPropGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("PROP_GROUP_ID <=", value, "propGroupId");
            return (Criteria) this;
        }

        public Criteria andPropGroupIdIn(List<Integer> values) {
            addCriterion("PROP_GROUP_ID in", values, "propGroupId");
            return (Criteria) this;
        }

        public Criteria andPropGroupIdNotIn(List<Integer> values) {
            addCriterion("PROP_GROUP_ID not in", values, "propGroupId");
            return (Criteria) this;
        }

        public Criteria andPropGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("PROP_GROUP_ID between", value1, value2, "propGroupId");
            return (Criteria) this;
        }

        public Criteria andPropGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PROP_GROUP_ID not between", value1, value2, "propGroupId");
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

        public Criteria andIsAutobuildIsNull() {
            addCriterion("IS_AUTOBUILD is null");
            return (Criteria) this;
        }

        public Criteria andIsAutobuildIsNotNull() {
            addCriterion("IS_AUTOBUILD is not null");
            return (Criteria) this;
        }

        public Criteria andIsAutobuildEqualTo(String value) {
            addCriterion("IS_AUTOBUILD =", value, "isAutobuild");
            return (Criteria) this;
        }

        public Criteria andIsAutobuildNotEqualTo(String value) {
            addCriterion("IS_AUTOBUILD <>", value, "isAutobuild");
            return (Criteria) this;
        }

        public Criteria andIsAutobuildGreaterThan(String value) {
            addCriterion("IS_AUTOBUILD >", value, "isAutobuild");
            return (Criteria) this;
        }

        public Criteria andIsAutobuildGreaterThanOrEqualTo(String value) {
            addCriterion("IS_AUTOBUILD >=", value, "isAutobuild");
            return (Criteria) this;
        }

        public Criteria andIsAutobuildLessThan(String value) {
            addCriterion("IS_AUTOBUILD <", value, "isAutobuild");
            return (Criteria) this;
        }

        public Criteria andIsAutobuildLessThanOrEqualTo(String value) {
            addCriterion("IS_AUTOBUILD <=", value, "isAutobuild");
            return (Criteria) this;
        }

        public Criteria andIsAutobuildLike(String value) {
            addCriterion("IS_AUTOBUILD like", value, "isAutobuild");
            return (Criteria) this;
        }

        public Criteria andIsAutobuildNotLike(String value) {
            addCriterion("IS_AUTOBUILD not like", value, "isAutobuild");
            return (Criteria) this;
        }

        public Criteria andIsAutobuildIn(List<String> values) {
            addCriterion("IS_AUTOBUILD in", values, "isAutobuild");
            return (Criteria) this;
        }

        public Criteria andIsAutobuildNotIn(List<String> values) {
            addCriterion("IS_AUTOBUILD not in", values, "isAutobuild");
            return (Criteria) this;
        }

        public Criteria andIsAutobuildBetween(String value1, String value2) {
            addCriterion("IS_AUTOBUILD between", value1, value2, "isAutobuild");
            return (Criteria) this;
        }

        public Criteria andIsAutobuildNotBetween(String value1, String value2) {
            addCriterion("IS_AUTOBUILD not between", value1, value2, "isAutobuild");
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
