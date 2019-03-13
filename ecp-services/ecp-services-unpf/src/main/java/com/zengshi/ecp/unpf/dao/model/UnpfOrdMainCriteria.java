package com.zengshi.ecp.unpf.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UnpfOrdMainCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public UnpfOrdMainCriteria() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOuterIdIsNull() {
            addCriterion("OUTER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOuterIdIsNotNull() {
            addCriterion("OUTER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOuterIdEqualTo(String value) {
            addCriterion("OUTER_ID =", value, "outerId");
            return (Criteria) this;
        }

        public Criteria andOuterIdNotEqualTo(String value) {
            addCriterion("OUTER_ID <>", value, "outerId");
            return (Criteria) this;
        }

        public Criteria andOuterIdGreaterThan(String value) {
            addCriterion("OUTER_ID >", value, "outerId");
            return (Criteria) this;
        }

        public Criteria andOuterIdGreaterThanOrEqualTo(String value) {
            addCriterion("OUTER_ID >=", value, "outerId");
            return (Criteria) this;
        }

        public Criteria andOuterIdLessThan(String value) {
            addCriterion("OUTER_ID <", value, "outerId");
            return (Criteria) this;
        }

        public Criteria andOuterIdLessThanOrEqualTo(String value) {
            addCriterion("OUTER_ID <=", value, "outerId");
            return (Criteria) this;
        }

        public Criteria andOuterIdLike(String value) {
            addCriterion("OUTER_ID like", value, "outerId");
            return (Criteria) this;
        }

        public Criteria andOuterIdNotLike(String value) {
            addCriterion("OUTER_ID not like", value, "outerId");
            return (Criteria) this;
        }

        public Criteria andOuterIdIn(List<String> values) {
            addCriterion("OUTER_ID in", values, "outerId");
            return (Criteria) this;
        }

        public Criteria andOuterIdNotIn(List<String> values) {
            addCriterion("OUTER_ID not in", values, "outerId");
            return (Criteria) this;
        }

        public Criteria andOuterIdBetween(String value1, String value2) {
            addCriterion("OUTER_ID between", value1, value2, "outerId");
            return (Criteria) this;
        }

        public Criteria andOuterIdNotBetween(String value1, String value2) {
            addCriterion("OUTER_ID not between", value1, value2, "outerId");
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

        public Criteria andShopNameIsNull() {
            addCriterion("SHOP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("SHOP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("SHOP_NAME =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("SHOP_NAME <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("SHOP_NAME >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_NAME >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("SHOP_NAME <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("SHOP_NAME <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("SHOP_NAME like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("SHOP_NAME not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("SHOP_NAME in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("SHOP_NAME not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("SHOP_NAME between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("SHOP_NAME not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andParamidIsNull() {
            addCriterion("PARAMID is null");
            return (Criteria) this;
        }

        public Criteria andParamidIsNotNull() {
            addCriterion("PARAMID is not null");
            return (Criteria) this;
        }

        public Criteria andParamidEqualTo(String value) {
            addCriterion("PARAMID =", value, "paramid");
            return (Criteria) this;
        }

        public Criteria andParamidNotEqualTo(String value) {
            addCriterion("PARAMID <>", value, "paramid");
            return (Criteria) this;
        }

        public Criteria andParamidGreaterThan(String value) {
            addCriterion("PARAMID >", value, "paramid");
            return (Criteria) this;
        }

        public Criteria andParamidGreaterThanOrEqualTo(String value) {
            addCriterion("PARAMID >=", value, "paramid");
            return (Criteria) this;
        }

        public Criteria andParamidLessThan(String value) {
            addCriterion("PARAMID <", value, "paramid");
            return (Criteria) this;
        }

        public Criteria andParamidLessThanOrEqualTo(String value) {
            addCriterion("PARAMID <=", value, "paramid");
            return (Criteria) this;
        }

        public Criteria andParamidLike(String value) {
            addCriterion("PARAMID like", value, "paramid");
            return (Criteria) this;
        }

        public Criteria andParamidNotLike(String value) {
            addCriterion("PARAMID not like", value, "paramid");
            return (Criteria) this;
        }

        public Criteria andParamidIn(List<String> values) {
            addCriterion("PARAMID in", values, "paramid");
            return (Criteria) this;
        }

        public Criteria andParamidNotIn(List<String> values) {
            addCriterion("PARAMID not in", values, "paramid");
            return (Criteria) this;
        }

        public Criteria andParamidBetween(String value1, String value2) {
            addCriterion("PARAMID between", value1, value2, "paramid");
            return (Criteria) this;
        }

        public Criteria andParamidNotBetween(String value1, String value2) {
            addCriterion("PARAMID not between", value1, value2, "paramid");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNull() {
            addCriterion("ORDER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("ORDER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("ORDER_CODE =", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("ORDER_CODE <>", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("ORDER_CODE >", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_CODE >=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("ORDER_CODE <", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("ORDER_CODE <=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("ORDER_CODE like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("ORDER_CODE not like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIn(List<String> values) {
            addCriterion("ORDER_CODE in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotIn(List<String> values) {
            addCriterion("ORDER_CODE not in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("ORDER_CODE between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("ORDER_CODE not between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeIsNull() {
            addCriterion("STAFF_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStaffCodeIsNotNull() {
            addCriterion("STAFF_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStaffCodeEqualTo(String value) {
            addCriterion("STAFF_CODE =", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotEqualTo(String value) {
            addCriterion("STAFF_CODE <>", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeGreaterThan(String value) {
            addCriterion("STAFF_CODE >", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_CODE >=", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLessThan(String value) {
            addCriterion("STAFF_CODE <", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLessThanOrEqualTo(String value) {
            addCriterion("STAFF_CODE <=", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLike(String value) {
            addCriterion("STAFF_CODE like", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotLike(String value) {
            addCriterion("STAFF_CODE not like", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeIn(List<String> values) {
            addCriterion("STAFF_CODE in", values, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotIn(List<String> values) {
            addCriterion("STAFF_CODE not in", values, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeBetween(String value1, String value2) {
            addCriterion("STAFF_CODE between", value1, value2, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotBetween(String value1, String value2) {
            addCriterion("STAFF_CODE not between", value1, value2, "staffCode");
            return (Criteria) this;
        }

        public Criteria andRealExpressFeeIsNull() {
            addCriterion("REAL_EXPRESS_FEE is null");
            return (Criteria) this;
        }

        public Criteria andRealExpressFeeIsNotNull() {
            addCriterion("REAL_EXPRESS_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andRealExpressFeeEqualTo(Long value) {
            addCriterion("REAL_EXPRESS_FEE =", value, "realExpressFee");
            return (Criteria) this;
        }

        public Criteria andRealExpressFeeNotEqualTo(Long value) {
            addCriterion("REAL_EXPRESS_FEE <>", value, "realExpressFee");
            return (Criteria) this;
        }

        public Criteria andRealExpressFeeGreaterThan(Long value) {
            addCriterion("REAL_EXPRESS_FEE >", value, "realExpressFee");
            return (Criteria) this;
        }

        public Criteria andRealExpressFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("REAL_EXPRESS_FEE >=", value, "realExpressFee");
            return (Criteria) this;
        }

        public Criteria andRealExpressFeeLessThan(Long value) {
            addCriterion("REAL_EXPRESS_FEE <", value, "realExpressFee");
            return (Criteria) this;
        }

        public Criteria andRealExpressFeeLessThanOrEqualTo(Long value) {
            addCriterion("REAL_EXPRESS_FEE <=", value, "realExpressFee");
            return (Criteria) this;
        }

        public Criteria andRealExpressFeeIn(List<Long> values) {
            addCriterion("REAL_EXPRESS_FEE in", values, "realExpressFee");
            return (Criteria) this;
        }

        public Criteria andRealExpressFeeNotIn(List<Long> values) {
            addCriterion("REAL_EXPRESS_FEE not in", values, "realExpressFee");
            return (Criteria) this;
        }

        public Criteria andRealExpressFeeBetween(Long value1, Long value2) {
            addCriterion("REAL_EXPRESS_FEE between", value1, value2, "realExpressFee");
            return (Criteria) this;
        }

        public Criteria andRealExpressFeeNotBetween(Long value1, Long value2) {
            addCriterion("REAL_EXPRESS_FEE not between", value1, value2, "realExpressFee");
            return (Criteria) this;
        }

        public Criteria andRealMoneyIsNull() {
            addCriterion("REAL_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andRealMoneyIsNotNull() {
            addCriterion("REAL_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andRealMoneyEqualTo(Long value) {
            addCriterion("REAL_MONEY =", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyNotEqualTo(Long value) {
            addCriterion("REAL_MONEY <>", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyGreaterThan(Long value) {
            addCriterion("REAL_MONEY >", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("REAL_MONEY >=", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyLessThan(Long value) {
            addCriterion("REAL_MONEY <", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyLessThanOrEqualTo(Long value) {
            addCriterion("REAL_MONEY <=", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyIn(List<Long> values) {
            addCriterion("REAL_MONEY in", values, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyNotIn(List<Long> values) {
            addCriterion("REAL_MONEY not in", values, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyBetween(Long value1, Long value2) {
            addCriterion("REAL_MONEY between", value1, value2, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyNotBetween(Long value1, Long value2) {
            addCriterion("REAL_MONEY not between", value1, value2, "realMoney");
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

        public Criteria andBuyerMsgIsNull() {
            addCriterion("BUYER_MSG is null");
            return (Criteria) this;
        }

        public Criteria andBuyerMsgIsNotNull() {
            addCriterion("BUYER_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerMsgEqualTo(String value) {
            addCriterion("BUYER_MSG =", value, "buyerMsg");
            return (Criteria) this;
        }

        public Criteria andBuyerMsgNotEqualTo(String value) {
            addCriterion("BUYER_MSG <>", value, "buyerMsg");
            return (Criteria) this;
        }

        public Criteria andBuyerMsgGreaterThan(String value) {
            addCriterion("BUYER_MSG >", value, "buyerMsg");
            return (Criteria) this;
        }

        public Criteria andBuyerMsgGreaterThanOrEqualTo(String value) {
            addCriterion("BUYER_MSG >=", value, "buyerMsg");
            return (Criteria) this;
        }

        public Criteria andBuyerMsgLessThan(String value) {
            addCriterion("BUYER_MSG <", value, "buyerMsg");
            return (Criteria) this;
        }

        public Criteria andBuyerMsgLessThanOrEqualTo(String value) {
            addCriterion("BUYER_MSG <=", value, "buyerMsg");
            return (Criteria) this;
        }

        public Criteria andBuyerMsgLike(String value) {
            addCriterion("BUYER_MSG like", value, "buyerMsg");
            return (Criteria) this;
        }

        public Criteria andBuyerMsgNotLike(String value) {
            addCriterion("BUYER_MSG not like", value, "buyerMsg");
            return (Criteria) this;
        }

        public Criteria andBuyerMsgIn(List<String> values) {
            addCriterion("BUYER_MSG in", values, "buyerMsg");
            return (Criteria) this;
        }

        public Criteria andBuyerMsgNotIn(List<String> values) {
            addCriterion("BUYER_MSG not in", values, "buyerMsg");
            return (Criteria) this;
        }

        public Criteria andBuyerMsgBetween(String value1, String value2) {
            addCriterion("BUYER_MSG between", value1, value2, "buyerMsg");
            return (Criteria) this;
        }

        public Criteria andBuyerMsgNotBetween(String value1, String value2) {
            addCriterion("BUYER_MSG not between", value1, value2, "buyerMsg");
            return (Criteria) this;
        }

        public Criteria andContractProvinceIsNull() {
            addCriterion("CONTRACT_PROVINCE is null");
            return (Criteria) this;
        }

        public Criteria andContractProvinceIsNotNull() {
            addCriterion("CONTRACT_PROVINCE is not null");
            return (Criteria) this;
        }

        public Criteria andContractProvinceEqualTo(String value) {
            addCriterion("CONTRACT_PROVINCE =", value, "contractProvince");
            return (Criteria) this;
        }

        public Criteria andContractProvinceNotEqualTo(String value) {
            addCriterion("CONTRACT_PROVINCE <>", value, "contractProvince");
            return (Criteria) this;
        }

        public Criteria andContractProvinceGreaterThan(String value) {
            addCriterion("CONTRACT_PROVINCE >", value, "contractProvince");
            return (Criteria) this;
        }

        public Criteria andContractProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_PROVINCE >=", value, "contractProvince");
            return (Criteria) this;
        }

        public Criteria andContractProvinceLessThan(String value) {
            addCriterion("CONTRACT_PROVINCE <", value, "contractProvince");
            return (Criteria) this;
        }

        public Criteria andContractProvinceLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_PROVINCE <=", value, "contractProvince");
            return (Criteria) this;
        }

        public Criteria andContractProvinceLike(String value) {
            addCriterion("CONTRACT_PROVINCE like", value, "contractProvince");
            return (Criteria) this;
        }

        public Criteria andContractProvinceNotLike(String value) {
            addCriterion("CONTRACT_PROVINCE not like", value, "contractProvince");
            return (Criteria) this;
        }

        public Criteria andContractProvinceIn(List<String> values) {
            addCriterion("CONTRACT_PROVINCE in", values, "contractProvince");
            return (Criteria) this;
        }

        public Criteria andContractProvinceNotIn(List<String> values) {
            addCriterion("CONTRACT_PROVINCE not in", values, "contractProvince");
            return (Criteria) this;
        }

        public Criteria andContractProvinceBetween(String value1, String value2) {
            addCriterion("CONTRACT_PROVINCE between", value1, value2, "contractProvince");
            return (Criteria) this;
        }

        public Criteria andContractProvinceNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_PROVINCE not between", value1, value2, "contractProvince");
            return (Criteria) this;
        }

        public Criteria andContractCityIsNull() {
            addCriterion("CONTRACT_CITY is null");
            return (Criteria) this;
        }

        public Criteria andContractCityIsNotNull() {
            addCriterion("CONTRACT_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andContractCityEqualTo(String value) {
            addCriterion("CONTRACT_CITY =", value, "contractCity");
            return (Criteria) this;
        }

        public Criteria andContractCityNotEqualTo(String value) {
            addCriterion("CONTRACT_CITY <>", value, "contractCity");
            return (Criteria) this;
        }

        public Criteria andContractCityGreaterThan(String value) {
            addCriterion("CONTRACT_CITY >", value, "contractCity");
            return (Criteria) this;
        }

        public Criteria andContractCityGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_CITY >=", value, "contractCity");
            return (Criteria) this;
        }

        public Criteria andContractCityLessThan(String value) {
            addCriterion("CONTRACT_CITY <", value, "contractCity");
            return (Criteria) this;
        }

        public Criteria andContractCityLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_CITY <=", value, "contractCity");
            return (Criteria) this;
        }

        public Criteria andContractCityLike(String value) {
            addCriterion("CONTRACT_CITY like", value, "contractCity");
            return (Criteria) this;
        }

        public Criteria andContractCityNotLike(String value) {
            addCriterion("CONTRACT_CITY not like", value, "contractCity");
            return (Criteria) this;
        }

        public Criteria andContractCityIn(List<String> values) {
            addCriterion("CONTRACT_CITY in", values, "contractCity");
            return (Criteria) this;
        }

        public Criteria andContractCityNotIn(List<String> values) {
            addCriterion("CONTRACT_CITY not in", values, "contractCity");
            return (Criteria) this;
        }

        public Criteria andContractCityBetween(String value1, String value2) {
            addCriterion("CONTRACT_CITY between", value1, value2, "contractCity");
            return (Criteria) this;
        }

        public Criteria andContractCityNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_CITY not between", value1, value2, "contractCity");
            return (Criteria) this;
        }

        public Criteria andContractDistrictIsNull() {
            addCriterion("CONTRACT_DISTRICT is null");
            return (Criteria) this;
        }

        public Criteria andContractDistrictIsNotNull() {
            addCriterion("CONTRACT_DISTRICT is not null");
            return (Criteria) this;
        }

        public Criteria andContractDistrictEqualTo(String value) {
            addCriterion("CONTRACT_DISTRICT =", value, "contractDistrict");
            return (Criteria) this;
        }

        public Criteria andContractDistrictNotEqualTo(String value) {
            addCriterion("CONTRACT_DISTRICT <>", value, "contractDistrict");
            return (Criteria) this;
        }

        public Criteria andContractDistrictGreaterThan(String value) {
            addCriterion("CONTRACT_DISTRICT >", value, "contractDistrict");
            return (Criteria) this;
        }

        public Criteria andContractDistrictGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_DISTRICT >=", value, "contractDistrict");
            return (Criteria) this;
        }

        public Criteria andContractDistrictLessThan(String value) {
            addCriterion("CONTRACT_DISTRICT <", value, "contractDistrict");
            return (Criteria) this;
        }

        public Criteria andContractDistrictLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_DISTRICT <=", value, "contractDistrict");
            return (Criteria) this;
        }

        public Criteria andContractDistrictLike(String value) {
            addCriterion("CONTRACT_DISTRICT like", value, "contractDistrict");
            return (Criteria) this;
        }

        public Criteria andContractDistrictNotLike(String value) {
            addCriterion("CONTRACT_DISTRICT not like", value, "contractDistrict");
            return (Criteria) this;
        }

        public Criteria andContractDistrictIn(List<String> values) {
            addCriterion("CONTRACT_DISTRICT in", values, "contractDistrict");
            return (Criteria) this;
        }

        public Criteria andContractDistrictNotIn(List<String> values) {
            addCriterion("CONTRACT_DISTRICT not in", values, "contractDistrict");
            return (Criteria) this;
        }

        public Criteria andContractDistrictBetween(String value1, String value2) {
            addCriterion("CONTRACT_DISTRICT between", value1, value2, "contractDistrict");
            return (Criteria) this;
        }

        public Criteria andContractDistrictNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_DISTRICT not between", value1, value2, "contractDistrict");
            return (Criteria) this;
        }

        public Criteria andContractZipcodeIsNull() {
            addCriterion("CONTRACT_ZIPCODE is null");
            return (Criteria) this;
        }

        public Criteria andContractZipcodeIsNotNull() {
            addCriterion("CONTRACT_ZIPCODE is not null");
            return (Criteria) this;
        }

        public Criteria andContractZipcodeEqualTo(String value) {
            addCriterion("CONTRACT_ZIPCODE =", value, "contractZipcode");
            return (Criteria) this;
        }

        public Criteria andContractZipcodeNotEqualTo(String value) {
            addCriterion("CONTRACT_ZIPCODE <>", value, "contractZipcode");
            return (Criteria) this;
        }

        public Criteria andContractZipcodeGreaterThan(String value) {
            addCriterion("CONTRACT_ZIPCODE >", value, "contractZipcode");
            return (Criteria) this;
        }

        public Criteria andContractZipcodeGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_ZIPCODE >=", value, "contractZipcode");
            return (Criteria) this;
        }

        public Criteria andContractZipcodeLessThan(String value) {
            addCriterion("CONTRACT_ZIPCODE <", value, "contractZipcode");
            return (Criteria) this;
        }

        public Criteria andContractZipcodeLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_ZIPCODE <=", value, "contractZipcode");
            return (Criteria) this;
        }

        public Criteria andContractZipcodeLike(String value) {
            addCriterion("CONTRACT_ZIPCODE like", value, "contractZipcode");
            return (Criteria) this;
        }

        public Criteria andContractZipcodeNotLike(String value) {
            addCriterion("CONTRACT_ZIPCODE not like", value, "contractZipcode");
            return (Criteria) this;
        }

        public Criteria andContractZipcodeIn(List<String> values) {
            addCriterion("CONTRACT_ZIPCODE in", values, "contractZipcode");
            return (Criteria) this;
        }

        public Criteria andContractZipcodeNotIn(List<String> values) {
            addCriterion("CONTRACT_ZIPCODE not in", values, "contractZipcode");
            return (Criteria) this;
        }

        public Criteria andContractZipcodeBetween(String value1, String value2) {
            addCriterion("CONTRACT_ZIPCODE between", value1, value2, "contractZipcode");
            return (Criteria) this;
        }

        public Criteria andContractZipcodeNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_ZIPCODE not between", value1, value2, "contractZipcode");
            return (Criteria) this;
        }

        public Criteria andContractNameIsNull() {
            addCriterion("CONTRACT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andContractNameIsNotNull() {
            addCriterion("CONTRACT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andContractNameEqualTo(String value) {
            addCriterion("CONTRACT_NAME =", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotEqualTo(String value) {
            addCriterion("CONTRACT_NAME <>", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameGreaterThan(String value) {
            addCriterion("CONTRACT_NAME >", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_NAME >=", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLessThan(String value) {
            addCriterion("CONTRACT_NAME <", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_NAME <=", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLike(String value) {
            addCriterion("CONTRACT_NAME like", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotLike(String value) {
            addCriterion("CONTRACT_NAME not like", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameIn(List<String> values) {
            addCriterion("CONTRACT_NAME in", values, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotIn(List<String> values) {
            addCriterion("CONTRACT_NAME not in", values, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameBetween(String value1, String value2) {
            addCriterion("CONTRACT_NAME between", value1, value2, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_NAME not between", value1, value2, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractAddrIsNull() {
            addCriterion("CONTRACT_ADDR is null");
            return (Criteria) this;
        }

        public Criteria andContractAddrIsNotNull() {
            addCriterion("CONTRACT_ADDR is not null");
            return (Criteria) this;
        }

        public Criteria andContractAddrEqualTo(String value) {
            addCriterion("CONTRACT_ADDR =", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrNotEqualTo(String value) {
            addCriterion("CONTRACT_ADDR <>", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrGreaterThan(String value) {
            addCriterion("CONTRACT_ADDR >", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_ADDR >=", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrLessThan(String value) {
            addCriterion("CONTRACT_ADDR <", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_ADDR <=", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrLike(String value) {
            addCriterion("CONTRACT_ADDR like", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrNotLike(String value) {
            addCriterion("CONTRACT_ADDR not like", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrIn(List<String> values) {
            addCriterion("CONTRACT_ADDR in", values, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrNotIn(List<String> values) {
            addCriterion("CONTRACT_ADDR not in", values, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrBetween(String value1, String value2) {
            addCriterion("CONTRACT_ADDR between", value1, value2, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_ADDR not between", value1, value2, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andDispatchTypeIsNull() {
            addCriterion("DISPATCH_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDispatchTypeIsNotNull() {
            addCriterion("DISPATCH_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDispatchTypeEqualTo(String value) {
            addCriterion("DISPATCH_TYPE =", value, "dispatchType");
            return (Criteria) this;
        }

        public Criteria andDispatchTypeNotEqualTo(String value) {
            addCriterion("DISPATCH_TYPE <>", value, "dispatchType");
            return (Criteria) this;
        }

        public Criteria andDispatchTypeGreaterThan(String value) {
            addCriterion("DISPATCH_TYPE >", value, "dispatchType");
            return (Criteria) this;
        }

        public Criteria andDispatchTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DISPATCH_TYPE >=", value, "dispatchType");
            return (Criteria) this;
        }

        public Criteria andDispatchTypeLessThan(String value) {
            addCriterion("DISPATCH_TYPE <", value, "dispatchType");
            return (Criteria) this;
        }

        public Criteria andDispatchTypeLessThanOrEqualTo(String value) {
            addCriterion("DISPATCH_TYPE <=", value, "dispatchType");
            return (Criteria) this;
        }

        public Criteria andDispatchTypeLike(String value) {
            addCriterion("DISPATCH_TYPE like", value, "dispatchType");
            return (Criteria) this;
        }

        public Criteria andDispatchTypeNotLike(String value) {
            addCriterion("DISPATCH_TYPE not like", value, "dispatchType");
            return (Criteria) this;
        }

        public Criteria andDispatchTypeIn(List<String> values) {
            addCriterion("DISPATCH_TYPE in", values, "dispatchType");
            return (Criteria) this;
        }

        public Criteria andDispatchTypeNotIn(List<String> values) {
            addCriterion("DISPATCH_TYPE not in", values, "dispatchType");
            return (Criteria) this;
        }

        public Criteria andDispatchTypeBetween(String value1, String value2) {
            addCriterion("DISPATCH_TYPE between", value1, value2, "dispatchType");
            return (Criteria) this;
        }

        public Criteria andDispatchTypeNotBetween(String value1, String value2) {
            addCriterion("DISPATCH_TYPE not between", value1, value2, "dispatchType");
            return (Criteria) this;
        }

        public Criteria andContractTelIsNull() {
            addCriterion("CONTRACT_TEL is null");
            return (Criteria) this;
        }

        public Criteria andContractTelIsNotNull() {
            addCriterion("CONTRACT_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andContractTelEqualTo(String value) {
            addCriterion("CONTRACT_TEL =", value, "contractTel");
            return (Criteria) this;
        }

        public Criteria andContractTelNotEqualTo(String value) {
            addCriterion("CONTRACT_TEL <>", value, "contractTel");
            return (Criteria) this;
        }

        public Criteria andContractTelGreaterThan(String value) {
            addCriterion("CONTRACT_TEL >", value, "contractTel");
            return (Criteria) this;
        }

        public Criteria andContractTelGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_TEL >=", value, "contractTel");
            return (Criteria) this;
        }

        public Criteria andContractTelLessThan(String value) {
            addCriterion("CONTRACT_TEL <", value, "contractTel");
            return (Criteria) this;
        }

        public Criteria andContractTelLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_TEL <=", value, "contractTel");
            return (Criteria) this;
        }

        public Criteria andContractTelLike(String value) {
            addCriterion("CONTRACT_TEL like", value, "contractTel");
            return (Criteria) this;
        }

        public Criteria andContractTelNotLike(String value) {
            addCriterion("CONTRACT_TEL not like", value, "contractTel");
            return (Criteria) this;
        }

        public Criteria andContractTelIn(List<String> values) {
            addCriterion("CONTRACT_TEL in", values, "contractTel");
            return (Criteria) this;
        }

        public Criteria andContractTelNotIn(List<String> values) {
            addCriterion("CONTRACT_TEL not in", values, "contractTel");
            return (Criteria) this;
        }

        public Criteria andContractTelBetween(String value1, String value2) {
            addCriterion("CONTRACT_TEL between", value1, value2, "contractTel");
            return (Criteria) this;
        }

        public Criteria andContractTelNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_TEL not between", value1, value2, "contractTel");
            return (Criteria) this;
        }

        public Criteria andContractNumIsNull() {
            addCriterion("CONTRACT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andContractNumIsNotNull() {
            addCriterion("CONTRACT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andContractNumEqualTo(String value) {
            addCriterion("CONTRACT_NUM =", value, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumNotEqualTo(String value) {
            addCriterion("CONTRACT_NUM <>", value, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumGreaterThan(String value) {
            addCriterion("CONTRACT_NUM >", value, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_NUM >=", value, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumLessThan(String value) {
            addCriterion("CONTRACT_NUM <", value, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_NUM <=", value, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumLike(String value) {
            addCriterion("CONTRACT_NUM like", value, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumNotLike(String value) {
            addCriterion("CONTRACT_NUM not like", value, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumIn(List<String> values) {
            addCriterion("CONTRACT_NUM in", values, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumNotIn(List<String> values) {
            addCriterion("CONTRACT_NUM not in", values, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumBetween(String value1, String value2) {
            addCriterion("CONTRACT_NUM between", value1, value2, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_NUM not between", value1, value2, "contractNum");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNull() {
            addCriterion("ORDER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNotNull() {
            addCriterion("ORDER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeEqualTo(Timestamp value) {
            addCriterion("ORDER_TIME =", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotEqualTo(Timestamp value) {
            addCriterion("ORDER_TIME <>", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThan(Timestamp value) {
            addCriterion("ORDER_TIME >", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("ORDER_TIME >=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThan(Timestamp value) {
            addCriterion("ORDER_TIME <", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("ORDER_TIME <=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIn(List<Timestamp> values) {
            addCriterion("ORDER_TIME in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotIn(List<Timestamp> values) {
            addCriterion("ORDER_TIME not in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ORDER_TIME between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ORDER_TIME not between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("PAY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("PAY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Timestamp value) {
            addCriterion("PAY_TIME =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Timestamp value) {
            addCriterion("PAY_TIME <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Timestamp value) {
            addCriterion("PAY_TIME >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("PAY_TIME >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Timestamp value) {
            addCriterion("PAY_TIME <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("PAY_TIME <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Timestamp> values) {
            addCriterion("PAY_TIME in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Timestamp> values) {
            addCriterion("PAY_TIME not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PAY_TIME between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PAY_TIME not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeIsNull() {
            addCriterion("DISPATCH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeIsNotNull() {
            addCriterion("DISPATCH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeEqualTo(Timestamp value) {
            addCriterion("DISPATCH_TIME =", value, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeNotEqualTo(Timestamp value) {
            addCriterion("DISPATCH_TIME <>", value, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeGreaterThan(Timestamp value) {
            addCriterion("DISPATCH_TIME >", value, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("DISPATCH_TIME >=", value, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeLessThan(Timestamp value) {
            addCriterion("DISPATCH_TIME <", value, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("DISPATCH_TIME <=", value, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeIn(List<Timestamp> values) {
            addCriterion("DISPATCH_TIME in", values, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeNotIn(List<Timestamp> values) {
            addCriterion("DISPATCH_TIME not in", values, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("DISPATCH_TIME between", value1, value2, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andDispatchTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("DISPATCH_TIME not between", value1, value2, "dispatchTime");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNull() {
            addCriterion("EXPRESS_NO is null");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNotNull() {
            addCriterion("EXPRESS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andExpressNoEqualTo(String value) {
            addCriterion("EXPRESS_NO =", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotEqualTo(String value) {
            addCriterion("EXPRESS_NO <>", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThan(String value) {
            addCriterion("EXPRESS_NO >", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("EXPRESS_NO >=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThan(String value) {
            addCriterion("EXPRESS_NO <", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThanOrEqualTo(String value) {
            addCriterion("EXPRESS_NO <=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLike(String value) {
            addCriterion("EXPRESS_NO like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotLike(String value) {
            addCriterion("EXPRESS_NO not like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoIn(List<String> values) {
            addCriterion("EXPRESS_NO in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotIn(List<String> values) {
            addCriterion("EXPRESS_NO not in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoBetween(String value1, String value2) {
            addCriterion("EXPRESS_NO between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotBetween(String value1, String value2) {
            addCriterion("EXPRESS_NO not between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIsNull() {
            addCriterion("EXPRESS_COMPANY is null");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIsNotNull() {
            addCriterion("EXPRESS_COMPANY is not null");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyEqualTo(String value) {
            addCriterion("EXPRESS_COMPANY =", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotEqualTo(String value) {
            addCriterion("EXPRESS_COMPANY <>", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyGreaterThan(String value) {
            addCriterion("EXPRESS_COMPANY >", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("EXPRESS_COMPANY >=", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyLessThan(String value) {
            addCriterion("EXPRESS_COMPANY <", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyLessThanOrEqualTo(String value) {
            addCriterion("EXPRESS_COMPANY <=", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyLike(String value) {
            addCriterion("EXPRESS_COMPANY like", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotLike(String value) {
            addCriterion("EXPRESS_COMPANY not like", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIn(List<String> values) {
            addCriterion("EXPRESS_COMPANY in", values, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotIn(List<String> values) {
            addCriterion("EXPRESS_COMPANY not in", values, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyBetween(String value1, String value2) {
            addCriterion("EXPRESS_COMPANY between", value1, value2, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotBetween(String value1, String value2) {
            addCriterion("EXPRESS_COMPANY not between", value1, value2, "expressCompany");
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

        public Criteria andOrderAmountIsNull() {
            addCriterion("ORDER_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNotNull() {
            addCriterion("ORDER_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountEqualTo(String value) {
            addCriterion("ORDER_AMOUNT =", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotEqualTo(String value) {
            addCriterion("ORDER_AMOUNT <>", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThan(String value) {
            addCriterion("ORDER_AMOUNT >", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_AMOUNT >=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThan(String value) {
            addCriterion("ORDER_AMOUNT <", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThanOrEqualTo(String value) {
            addCriterion("ORDER_AMOUNT <=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLike(String value) {
            addCriterion("ORDER_AMOUNT like", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotLike(String value) {
            addCriterion("ORDER_AMOUNT not like", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIn(List<String> values) {
            addCriterion("ORDER_AMOUNT in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotIn(List<String> values) {
            addCriterion("ORDER_AMOUNT not in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountBetween(String value1, String value2) {
            addCriterion("ORDER_AMOUNT between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotBetween(String value1, String value2) {
            addCriterion("ORDER_AMOUNT not between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andCloseReasonIsNull() {
            addCriterion("CLOSE_REASON is null");
            return (Criteria) this;
        }

        public Criteria andCloseReasonIsNotNull() {
            addCriterion("CLOSE_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andCloseReasonEqualTo(String value) {
            addCriterion("CLOSE_REASON =", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotEqualTo(String value) {
            addCriterion("CLOSE_REASON <>", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonGreaterThan(String value) {
            addCriterion("CLOSE_REASON >", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonGreaterThanOrEqualTo(String value) {
            addCriterion("CLOSE_REASON >=", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonLessThan(String value) {
            addCriterion("CLOSE_REASON <", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonLessThanOrEqualTo(String value) {
            addCriterion("CLOSE_REASON <=", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonLike(String value) {
            addCriterion("CLOSE_REASON like", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotLike(String value) {
            addCriterion("CLOSE_REASON not like", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonIn(List<String> values) {
            addCriterion("CLOSE_REASON in", values, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotIn(List<String> values) {
            addCriterion("CLOSE_REASON not in", values, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonBetween(String value1, String value2) {
            addCriterion("CLOSE_REASON between", value1, value2, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotBetween(String value1, String value2) {
            addCriterion("CLOSE_REASON not between", value1, value2, "closeReason");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleIsNull() {
            addCriterion("INVOICE_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleIsNotNull() {
            addCriterion("INVOICE_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleEqualTo(String value) {
            addCriterion("INVOICE_TITLE =", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleNotEqualTo(String value) {
            addCriterion("INVOICE_TITLE <>", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleGreaterThan(String value) {
            addCriterion("INVOICE_TITLE >", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_TITLE >=", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleLessThan(String value) {
            addCriterion("INVOICE_TITLE <", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_TITLE <=", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleLike(String value) {
            addCriterion("INVOICE_TITLE like", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleNotLike(String value) {
            addCriterion("INVOICE_TITLE not like", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleIn(List<String> values) {
            addCriterion("INVOICE_TITLE in", values, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleNotIn(List<String> values) {
            addCriterion("INVOICE_TITLE not in", values, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleBetween(String value1, String value2) {
            addCriterion("INVOICE_TITLE between", value1, value2, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleNotBetween(String value1, String value2) {
            addCriterion("INVOICE_TITLE not between", value1, value2, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andAppFlagIsNull() {
            addCriterion("APP_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAppFlagIsNotNull() {
            addCriterion("APP_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAppFlagEqualTo(String value) {
            addCriterion("APP_FLAG =", value, "appFlag");
            return (Criteria) this;
        }

        public Criteria andAppFlagNotEqualTo(String value) {
            addCriterion("APP_FLAG <>", value, "appFlag");
            return (Criteria) this;
        }

        public Criteria andAppFlagGreaterThan(String value) {
            addCriterion("APP_FLAG >", value, "appFlag");
            return (Criteria) this;
        }

        public Criteria andAppFlagGreaterThanOrEqualTo(String value) {
            addCriterion("APP_FLAG >=", value, "appFlag");
            return (Criteria) this;
        }

        public Criteria andAppFlagLessThan(String value) {
            addCriterion("APP_FLAG <", value, "appFlag");
            return (Criteria) this;
        }

        public Criteria andAppFlagLessThanOrEqualTo(String value) {
            addCriterion("APP_FLAG <=", value, "appFlag");
            return (Criteria) this;
        }

        public Criteria andAppFlagLike(String value) {
            addCriterion("APP_FLAG like", value, "appFlag");
            return (Criteria) this;
        }

        public Criteria andAppFlagNotLike(String value) {
            addCriterion("APP_FLAG not like", value, "appFlag");
            return (Criteria) this;
        }

        public Criteria andAppFlagIn(List<String> values) {
            addCriterion("APP_FLAG in", values, "appFlag");
            return (Criteria) this;
        }

        public Criteria andAppFlagNotIn(List<String> values) {
            addCriterion("APP_FLAG not in", values, "appFlag");
            return (Criteria) this;
        }

        public Criteria andAppFlagBetween(String value1, String value2) {
            addCriterion("APP_FLAG between", value1, value2, "appFlag");
            return (Criteria) this;
        }

        public Criteria andAppFlagNotBetween(String value1, String value2) {
            addCriterion("APP_FLAG not between", value1, value2, "appFlag");
            return (Criteria) this;
        }

        public Criteria andEcpStaffCodeIsNull() {
            addCriterion("ECP_STAFF_CODE is null");
            return (Criteria) this;
        }

        public Criteria andEcpStaffCodeIsNotNull() {
            addCriterion("ECP_STAFF_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andEcpStaffCodeEqualTo(String value) {
            addCriterion("ECP_STAFF_CODE =", value, "ecpStaffCode");
            return (Criteria) this;
        }

        public Criteria andEcpStaffCodeNotEqualTo(String value) {
            addCriterion("ECP_STAFF_CODE <>", value, "ecpStaffCode");
            return (Criteria) this;
        }

        public Criteria andEcpStaffCodeGreaterThan(String value) {
            addCriterion("ECP_STAFF_CODE >", value, "ecpStaffCode");
            return (Criteria) this;
        }

        public Criteria andEcpStaffCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ECP_STAFF_CODE >=", value, "ecpStaffCode");
            return (Criteria) this;
        }

        public Criteria andEcpStaffCodeLessThan(String value) {
            addCriterion("ECP_STAFF_CODE <", value, "ecpStaffCode");
            return (Criteria) this;
        }

        public Criteria andEcpStaffCodeLessThanOrEqualTo(String value) {
            addCriterion("ECP_STAFF_CODE <=", value, "ecpStaffCode");
            return (Criteria) this;
        }

        public Criteria andEcpStaffCodeLike(String value) {
            addCriterion("ECP_STAFF_CODE like", value, "ecpStaffCode");
            return (Criteria) this;
        }

        public Criteria andEcpStaffCodeNotLike(String value) {
            addCriterion("ECP_STAFF_CODE not like", value, "ecpStaffCode");
            return (Criteria) this;
        }

        public Criteria andEcpStaffCodeIn(List<String> values) {
            addCriterion("ECP_STAFF_CODE in", values, "ecpStaffCode");
            return (Criteria) this;
        }

        public Criteria andEcpStaffCodeNotIn(List<String> values) {
            addCriterion("ECP_STAFF_CODE not in", values, "ecpStaffCode");
            return (Criteria) this;
        }

        public Criteria andEcpStaffCodeBetween(String value1, String value2) {
            addCriterion("ECP_STAFF_CODE between", value1, value2, "ecpStaffCode");
            return (Criteria) this;
        }

        public Criteria andEcpStaffCodeNotBetween(String value1, String value2) {
            addCriterion("ECP_STAFF_CODE not between", value1, value2, "ecpStaffCode");
            return (Criteria) this;
        }

        public Criteria andEcpStaffIdIsNull() {
            addCriterion("ECP_STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andEcpStaffIdIsNotNull() {
            addCriterion("ECP_STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEcpStaffIdEqualTo(Long value) {
            addCriterion("ECP_STAFF_ID =", value, "ecpStaffId");
            return (Criteria) this;
        }

        public Criteria andEcpStaffIdNotEqualTo(Long value) {
            addCriterion("ECP_STAFF_ID <>", value, "ecpStaffId");
            return (Criteria) this;
        }

        public Criteria andEcpStaffIdGreaterThan(Long value) {
            addCriterion("ECP_STAFF_ID >", value, "ecpStaffId");
            return (Criteria) this;
        }

        public Criteria andEcpStaffIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ECP_STAFF_ID >=", value, "ecpStaffId");
            return (Criteria) this;
        }

        public Criteria andEcpStaffIdLessThan(Long value) {
            addCriterion("ECP_STAFF_ID <", value, "ecpStaffId");
            return (Criteria) this;
        }

        public Criteria andEcpStaffIdLessThanOrEqualTo(Long value) {
            addCriterion("ECP_STAFF_ID <=", value, "ecpStaffId");
            return (Criteria) this;
        }

        public Criteria andEcpStaffIdIn(List<Long> values) {
            addCriterion("ECP_STAFF_ID in", values, "ecpStaffId");
            return (Criteria) this;
        }

        public Criteria andEcpStaffIdNotIn(List<Long> values) {
            addCriterion("ECP_STAFF_ID not in", values, "ecpStaffId");
            return (Criteria) this;
        }

        public Criteria andEcpStaffIdBetween(Long value1, Long value2) {
            addCriterion("ECP_STAFF_ID between", value1, value2, "ecpStaffId");
            return (Criteria) this;
        }

        public Criteria andEcpStaffIdNotBetween(Long value1, Long value2) {
            addCriterion("ECP_STAFF_ID not between", value1, value2, "ecpStaffId");
            return (Criteria) this;
        }

        public Criteria andEcpScoreFlagIsNull() {
            addCriterion("ECP_SCORE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andEcpScoreFlagIsNotNull() {
            addCriterion("ECP_SCORE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andEcpScoreFlagEqualTo(String value) {
            addCriterion("ECP_SCORE_FLAG =", value, "ecpScoreFlag");
            return (Criteria) this;
        }

        public Criteria andEcpScoreFlagNotEqualTo(String value) {
            addCriterion("ECP_SCORE_FLAG <>", value, "ecpScoreFlag");
            return (Criteria) this;
        }

        public Criteria andEcpScoreFlagGreaterThan(String value) {
            addCriterion("ECP_SCORE_FLAG >", value, "ecpScoreFlag");
            return (Criteria) this;
        }

        public Criteria andEcpScoreFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ECP_SCORE_FLAG >=", value, "ecpScoreFlag");
            return (Criteria) this;
        }

        public Criteria andEcpScoreFlagLessThan(String value) {
            addCriterion("ECP_SCORE_FLAG <", value, "ecpScoreFlag");
            return (Criteria) this;
        }

        public Criteria andEcpScoreFlagLessThanOrEqualTo(String value) {
            addCriterion("ECP_SCORE_FLAG <=", value, "ecpScoreFlag");
            return (Criteria) this;
        }

        public Criteria andEcpScoreFlagLike(String value) {
            addCriterion("ECP_SCORE_FLAG like", value, "ecpScoreFlag");
            return (Criteria) this;
        }

        public Criteria andEcpScoreFlagNotLike(String value) {
            addCriterion("ECP_SCORE_FLAG not like", value, "ecpScoreFlag");
            return (Criteria) this;
        }

        public Criteria andEcpScoreFlagIn(List<String> values) {
            addCriterion("ECP_SCORE_FLAG in", values, "ecpScoreFlag");
            return (Criteria) this;
        }

        public Criteria andEcpScoreFlagNotIn(List<String> values) {
            addCriterion("ECP_SCORE_FLAG not in", values, "ecpScoreFlag");
            return (Criteria) this;
        }

        public Criteria andEcpScoreFlagBetween(String value1, String value2) {
            addCriterion("ECP_SCORE_FLAG between", value1, value2, "ecpScoreFlag");
            return (Criteria) this;
        }

        public Criteria andEcpScoreFlagNotBetween(String value1, String value2) {
            addCriterion("ECP_SCORE_FLAG not between", value1, value2, "ecpScoreFlag");
            return (Criteria) this;
        }

        public Criteria andEcpScoreIsNull() {
            addCriterion("ECP_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andEcpScoreIsNotNull() {
            addCriterion("ECP_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andEcpScoreEqualTo(Long value) {
            addCriterion("ECP_SCORE =", value, "ecpScore");
            return (Criteria) this;
        }

        public Criteria andEcpScoreNotEqualTo(Long value) {
            addCriterion("ECP_SCORE <>", value, "ecpScore");
            return (Criteria) this;
        }

        public Criteria andEcpScoreGreaterThan(Long value) {
            addCriterion("ECP_SCORE >", value, "ecpScore");
            return (Criteria) this;
        }

        public Criteria andEcpScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("ECP_SCORE >=", value, "ecpScore");
            return (Criteria) this;
        }

        public Criteria andEcpScoreLessThan(Long value) {
            addCriterion("ECP_SCORE <", value, "ecpScore");
            return (Criteria) this;
        }

        public Criteria andEcpScoreLessThanOrEqualTo(Long value) {
            addCriterion("ECP_SCORE <=", value, "ecpScore");
            return (Criteria) this;
        }

        public Criteria andEcpScoreIn(List<Long> values) {
            addCriterion("ECP_SCORE in", values, "ecpScore");
            return (Criteria) this;
        }

        public Criteria andEcpScoreNotIn(List<Long> values) {
            addCriterion("ECP_SCORE not in", values, "ecpScore");
            return (Criteria) this;
        }

        public Criteria andEcpScoreBetween(Long value1, Long value2) {
            addCriterion("ECP_SCORE between", value1, value2, "ecpScore");
            return (Criteria) this;
        }

        public Criteria andEcpScoreNotBetween(Long value1, Long value2) {
            addCriterion("ECP_SCORE not between", value1, value2, "ecpScore");
            return (Criteria) this;
        }

        public Criteria andImportTimeIsNull() {
            addCriterion("IMPORT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andImportTimeIsNotNull() {
            addCriterion("IMPORT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andImportTimeEqualTo(Timestamp value) {
            addCriterion("IMPORT_TIME =", value, "importTime");
            return (Criteria) this;
        }

        public Criteria andImportTimeNotEqualTo(Timestamp value) {
            addCriterion("IMPORT_TIME <>", value, "importTime");
            return (Criteria) this;
        }

        public Criteria andImportTimeGreaterThan(Timestamp value) {
            addCriterion("IMPORT_TIME >", value, "importTime");
            return (Criteria) this;
        }

        public Criteria andImportTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("IMPORT_TIME >=", value, "importTime");
            return (Criteria) this;
        }

        public Criteria andImportTimeLessThan(Timestamp value) {
            addCriterion("IMPORT_TIME <", value, "importTime");
            return (Criteria) this;
        }

        public Criteria andImportTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("IMPORT_TIME <=", value, "importTime");
            return (Criteria) this;
        }

        public Criteria andImportTimeIn(List<Timestamp> values) {
            addCriterion("IMPORT_TIME in", values, "importTime");
            return (Criteria) this;
        }

        public Criteria andImportTimeNotIn(List<Timestamp> values) {
            addCriterion("IMPORT_TIME not in", values, "importTime");
            return (Criteria) this;
        }

        public Criteria andImportTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("IMPORT_TIME between", value1, value2, "importTime");
            return (Criteria) this;
        }

        public Criteria andImportTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("IMPORT_TIME not between", value1, value2, "importTime");
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

        public Criteria andSysFlagIsNull() {
            addCriterion("SYS_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSysFlagIsNotNull() {
            addCriterion("SYS_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSysFlagEqualTo(String value) {
            addCriterion("SYS_FLAG =", value, "sysFlag");
            return (Criteria) this;
        }

        public Criteria andSysFlagNotEqualTo(String value) {
            addCriterion("SYS_FLAG <>", value, "sysFlag");
            return (Criteria) this;
        }

        public Criteria andSysFlagGreaterThan(String value) {
            addCriterion("SYS_FLAG >", value, "sysFlag");
            return (Criteria) this;
        }

        public Criteria andSysFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_FLAG >=", value, "sysFlag");
            return (Criteria) this;
        }

        public Criteria andSysFlagLessThan(String value) {
            addCriterion("SYS_FLAG <", value, "sysFlag");
            return (Criteria) this;
        }

        public Criteria andSysFlagLessThanOrEqualTo(String value) {
            addCriterion("SYS_FLAG <=", value, "sysFlag");
            return (Criteria) this;
        }

        public Criteria andSysFlagLike(String value) {
            addCriterion("SYS_FLAG like", value, "sysFlag");
            return (Criteria) this;
        }

        public Criteria andSysFlagNotLike(String value) {
            addCriterion("SYS_FLAG not like", value, "sysFlag");
            return (Criteria) this;
        }

        public Criteria andSysFlagIn(List<String> values) {
            addCriterion("SYS_FLAG in", values, "sysFlag");
            return (Criteria) this;
        }

        public Criteria andSysFlagNotIn(List<String> values) {
            addCriterion("SYS_FLAG not in", values, "sysFlag");
            return (Criteria) this;
        }

        public Criteria andSysFlagBetween(String value1, String value2) {
            addCriterion("SYS_FLAG between", value1, value2, "sysFlag");
            return (Criteria) this;
        }

        public Criteria andSysFlagNotBetween(String value1, String value2) {
            addCriterion("SYS_FLAG not between", value1, value2, "sysFlag");
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