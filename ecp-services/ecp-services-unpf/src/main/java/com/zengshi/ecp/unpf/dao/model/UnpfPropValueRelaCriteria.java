package com.zengshi.ecp.unpf.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UnpfPropValueRelaCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public UnpfPropValueRelaCriteria() {
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

        public Criteria andShopAuthIdIsNull() {
            addCriterion("SHOP_AUTH_ID is null");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdIsNotNull() {
            addCriterion("SHOP_AUTH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdEqualTo(Long value) {
            addCriterion("SHOP_AUTH_ID =", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdNotEqualTo(Long value) {
            addCriterion("SHOP_AUTH_ID <>", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdGreaterThan(Long value) {
            addCriterion("SHOP_AUTH_ID >", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SHOP_AUTH_ID >=", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdLessThan(Long value) {
            addCriterion("SHOP_AUTH_ID <", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdLessThanOrEqualTo(Long value) {
            addCriterion("SHOP_AUTH_ID <=", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdIn(List<Long> values) {
            addCriterion("SHOP_AUTH_ID in", values, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdNotIn(List<Long> values) {
            addCriterion("SHOP_AUTH_ID not in", values, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdBetween(Long value1, Long value2) {
            addCriterion("SHOP_AUTH_ID between", value1, value2, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdNotBetween(Long value1, Long value2) {
            addCriterion("SHOP_AUTH_ID not between", value1, value2, "shopAuthId");
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

        public Criteria andPlatTypeIsNull() {
            addCriterion("PLAT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPlatTypeIsNotNull() {
            addCriterion("PLAT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPlatTypeEqualTo(String value) {
            addCriterion("PLAT_TYPE =", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotEqualTo(String value) {
            addCriterion("PLAT_TYPE <>", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeGreaterThan(String value) {
            addCriterion("PLAT_TYPE >", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PLAT_TYPE >=", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeLessThan(String value) {
            addCriterion("PLAT_TYPE <", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeLessThanOrEqualTo(String value) {
            addCriterion("PLAT_TYPE <=", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeLike(String value) {
            addCriterion("PLAT_TYPE like", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotLike(String value) {
            addCriterion("PLAT_TYPE not like", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeIn(List<String> values) {
            addCriterion("PLAT_TYPE in", values, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotIn(List<String> values) {
            addCriterion("PLAT_TYPE not in", values, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeBetween(String value1, String value2) {
            addCriterion("PLAT_TYPE between", value1, value2, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotBetween(String value1, String value2) {
            addCriterion("PLAT_TYPE not between", value1, value2, "platType");
            return (Criteria) this;
        }

        public Criteria andOutPropIdIsNull() {
            addCriterion("OUT_PROP_ID is null");
            return (Criteria) this;
        }

        public Criteria andOutPropIdIsNotNull() {
            addCriterion("OUT_PROP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOutPropIdEqualTo(String value) {
            addCriterion("OUT_PROP_ID =", value, "outPropId");
            return (Criteria) this;
        }

        public Criteria andOutPropIdNotEqualTo(String value) {
            addCriterion("OUT_PROP_ID <>", value, "outPropId");
            return (Criteria) this;
        }

        public Criteria andOutPropIdGreaterThan(String value) {
            addCriterion("OUT_PROP_ID >", value, "outPropId");
            return (Criteria) this;
        }

        public Criteria andOutPropIdGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_PROP_ID >=", value, "outPropId");
            return (Criteria) this;
        }

        public Criteria andOutPropIdLessThan(String value) {
            addCriterion("OUT_PROP_ID <", value, "outPropId");
            return (Criteria) this;
        }

        public Criteria andOutPropIdLessThanOrEqualTo(String value) {
            addCriterion("OUT_PROP_ID <=", value, "outPropId");
            return (Criteria) this;
        }

        public Criteria andOutPropIdLike(String value) {
            addCriterion("OUT_PROP_ID like", value, "outPropId");
            return (Criteria) this;
        }

        public Criteria andOutPropIdNotLike(String value) {
            addCriterion("OUT_PROP_ID not like", value, "outPropId");
            return (Criteria) this;
        }

        public Criteria andOutPropIdIn(List<String> values) {
            addCriterion("OUT_PROP_ID in", values, "outPropId");
            return (Criteria) this;
        }

        public Criteria andOutPropIdNotIn(List<String> values) {
            addCriterion("OUT_PROP_ID not in", values, "outPropId");
            return (Criteria) this;
        }

        public Criteria andOutPropIdBetween(String value1, String value2) {
            addCriterion("OUT_PROP_ID between", value1, value2, "outPropId");
            return (Criteria) this;
        }

        public Criteria andOutPropIdNotBetween(String value1, String value2) {
            addCriterion("OUT_PROP_ID not between", value1, value2, "outPropId");
            return (Criteria) this;
        }

        public Criteria andOutVidIsNull() {
            addCriterion("OUT_VID is null");
            return (Criteria) this;
        }

        public Criteria andOutVidIsNotNull() {
            addCriterion("OUT_VID is not null");
            return (Criteria) this;
        }

        public Criteria andOutVidEqualTo(String value) {
            addCriterion("OUT_VID =", value, "outVid");
            return (Criteria) this;
        }

        public Criteria andOutVidNotEqualTo(String value) {
            addCriterion("OUT_VID <>", value, "outVid");
            return (Criteria) this;
        }

        public Criteria andOutVidGreaterThan(String value) {
            addCriterion("OUT_VID >", value, "outVid");
            return (Criteria) this;
        }

        public Criteria andOutVidGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_VID >=", value, "outVid");
            return (Criteria) this;
        }

        public Criteria andOutVidLessThan(String value) {
            addCriterion("OUT_VID <", value, "outVid");
            return (Criteria) this;
        }

        public Criteria andOutVidLessThanOrEqualTo(String value) {
            addCriterion("OUT_VID <=", value, "outVid");
            return (Criteria) this;
        }

        public Criteria andOutVidLike(String value) {
            addCriterion("OUT_VID like", value, "outVid");
            return (Criteria) this;
        }

        public Criteria andOutVidNotLike(String value) {
            addCriterion("OUT_VID not like", value, "outVid");
            return (Criteria) this;
        }

        public Criteria andOutVidIn(List<String> values) {
            addCriterion("OUT_VID in", values, "outVid");
            return (Criteria) this;
        }

        public Criteria andOutVidNotIn(List<String> values) {
            addCriterion("OUT_VID not in", values, "outVid");
            return (Criteria) this;
        }

        public Criteria andOutVidBetween(String value1, String value2) {
            addCriterion("OUT_VID between", value1, value2, "outVid");
            return (Criteria) this;
        }

        public Criteria andOutVidNotBetween(String value1, String value2) {
            addCriterion("OUT_VID not between", value1, value2, "outVid");
            return (Criteria) this;
        }

        public Criteria andOutVidNameIsNull() {
            addCriterion("OUT_VID_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOutVidNameIsNotNull() {
            addCriterion("OUT_VID_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOutVidNameEqualTo(String value) {
            addCriterion("OUT_VID_NAME =", value, "outVidName");
            return (Criteria) this;
        }

        public Criteria andOutVidNameNotEqualTo(String value) {
            addCriterion("OUT_VID_NAME <>", value, "outVidName");
            return (Criteria) this;
        }

        public Criteria andOutVidNameGreaterThan(String value) {
            addCriterion("OUT_VID_NAME >", value, "outVidName");
            return (Criteria) this;
        }

        public Criteria andOutVidNameGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_VID_NAME >=", value, "outVidName");
            return (Criteria) this;
        }

        public Criteria andOutVidNameLessThan(String value) {
            addCriterion("OUT_VID_NAME <", value, "outVidName");
            return (Criteria) this;
        }

        public Criteria andOutVidNameLessThanOrEqualTo(String value) {
            addCriterion("OUT_VID_NAME <=", value, "outVidName");
            return (Criteria) this;
        }

        public Criteria andOutVidNameLike(String value) {
            addCriterion("OUT_VID_NAME like", value, "outVidName");
            return (Criteria) this;
        }

        public Criteria andOutVidNameNotLike(String value) {
            addCriterion("OUT_VID_NAME not like", value, "outVidName");
            return (Criteria) this;
        }

        public Criteria andOutVidNameIn(List<String> values) {
            addCriterion("OUT_VID_NAME in", values, "outVidName");
            return (Criteria) this;
        }

        public Criteria andOutVidNameNotIn(List<String> values) {
            addCriterion("OUT_VID_NAME not in", values, "outVidName");
            return (Criteria) this;
        }

        public Criteria andOutVidNameBetween(String value1, String value2) {
            addCriterion("OUT_VID_NAME between", value1, value2, "outVidName");
            return (Criteria) this;
        }

        public Criteria andOutVidNameNotBetween(String value1, String value2) {
            addCriterion("OUT_VID_NAME not between", value1, value2, "outVidName");
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

        public Criteria andPropIdEqualTo(String value) {
            addCriterion("PROP_ID =", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdNotEqualTo(String value) {
            addCriterion("PROP_ID <>", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdGreaterThan(String value) {
            addCriterion("PROP_ID >", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_ID >=", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdLessThan(String value) {
            addCriterion("PROP_ID <", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdLessThanOrEqualTo(String value) {
            addCriterion("PROP_ID <=", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdLike(String value) {
            addCriterion("PROP_ID like", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdNotLike(String value) {
            addCriterion("PROP_ID not like", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdIn(List<String> values) {
            addCriterion("PROP_ID in", values, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdNotIn(List<String> values) {
            addCriterion("PROP_ID not in", values, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdBetween(String value1, String value2) {
            addCriterion("PROP_ID between", value1, value2, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdNotBetween(String value1, String value2) {
            addCriterion("PROP_ID not between", value1, value2, "propId");
            return (Criteria) this;
        }

        public Criteria andVidIsNull() {
            addCriterion("VID is null");
            return (Criteria) this;
        }

        public Criteria andVidIsNotNull() {
            addCriterion("VID is not null");
            return (Criteria) this;
        }

        public Criteria andVidEqualTo(String value) {
            addCriterion("VID =", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotEqualTo(String value) {
            addCriterion("VID <>", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidGreaterThan(String value) {
            addCriterion("VID >", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidGreaterThanOrEqualTo(String value) {
            addCriterion("VID >=", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidLessThan(String value) {
            addCriterion("VID <", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidLessThanOrEqualTo(String value) {
            addCriterion("VID <=", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidLike(String value) {
            addCriterion("VID like", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotLike(String value) {
            addCriterion("VID not like", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidIn(List<String> values) {
            addCriterion("VID in", values, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotIn(List<String> values) {
            addCriterion("VID not in", values, "vid");
            return (Criteria) this;
        }

        public Criteria andVidBetween(String value1, String value2) {
            addCriterion("VID between", value1, value2, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotBetween(String value1, String value2) {
            addCriterion("VID not between", value1, value2, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNameIsNull() {
            addCriterion("VID_NAME is null");
            return (Criteria) this;
        }

        public Criteria andVidNameIsNotNull() {
            addCriterion("VID_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andVidNameEqualTo(String value) {
            addCriterion("VID_NAME =", value, "vidName");
            return (Criteria) this;
        }

        public Criteria andVidNameNotEqualTo(String value) {
            addCriterion("VID_NAME <>", value, "vidName");
            return (Criteria) this;
        }

        public Criteria andVidNameGreaterThan(String value) {
            addCriterion("VID_NAME >", value, "vidName");
            return (Criteria) this;
        }

        public Criteria andVidNameGreaterThanOrEqualTo(String value) {
            addCriterion("VID_NAME >=", value, "vidName");
            return (Criteria) this;
        }

        public Criteria andVidNameLessThan(String value) {
            addCriterion("VID_NAME <", value, "vidName");
            return (Criteria) this;
        }

        public Criteria andVidNameLessThanOrEqualTo(String value) {
            addCriterion("VID_NAME <=", value, "vidName");
            return (Criteria) this;
        }

        public Criteria andVidNameLike(String value) {
            addCriterion("VID_NAME like", value, "vidName");
            return (Criteria) this;
        }

        public Criteria andVidNameNotLike(String value) {
            addCriterion("VID_NAME not like", value, "vidName");
            return (Criteria) this;
        }

        public Criteria andVidNameIn(List<String> values) {
            addCriterion("VID_NAME in", values, "vidName");
            return (Criteria) this;
        }

        public Criteria andVidNameNotIn(List<String> values) {
            addCriterion("VID_NAME not in", values, "vidName");
            return (Criteria) this;
        }

        public Criteria andVidNameBetween(String value1, String value2) {
            addCriterion("VID_NAME between", value1, value2, "vidName");
            return (Criteria) this;
        }

        public Criteria andVidNameNotBetween(String value1, String value2) {
            addCriterion("VID_NAME not between", value1, value2, "vidName");
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