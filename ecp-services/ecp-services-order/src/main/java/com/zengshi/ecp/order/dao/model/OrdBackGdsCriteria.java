package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrdBackGdsCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OrdBackGdsCriteria() {
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

        public Criteria andBackIdIsNull() {
            addCriterion("BACK_ID is null");
            return (Criteria) this;
        }

        public Criteria andBackIdIsNotNull() {
            addCriterion("BACK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBackIdEqualTo(Long value) {
            addCriterion("BACK_ID =", value, "backId");
            return (Criteria) this;
        }

        public Criteria andBackIdNotEqualTo(Long value) {
            addCriterion("BACK_ID <>", value, "backId");
            return (Criteria) this;
        }

        public Criteria andBackIdGreaterThan(Long value) {
            addCriterion("BACK_ID >", value, "backId");
            return (Criteria) this;
        }

        public Criteria andBackIdGreaterThanOrEqualTo(Long value) {
            addCriterion("BACK_ID >=", value, "backId");
            return (Criteria) this;
        }

        public Criteria andBackIdLessThan(Long value) {
            addCriterion("BACK_ID <", value, "backId");
            return (Criteria) this;
        }

        public Criteria andBackIdLessThanOrEqualTo(Long value) {
            addCriterion("BACK_ID <=", value, "backId");
            return (Criteria) this;
        }

        public Criteria andBackIdIn(List<Long> values) {
            addCriterion("BACK_ID in", values, "backId");
            return (Criteria) this;
        }

        public Criteria andBackIdNotIn(List<Long> values) {
            addCriterion("BACK_ID not in", values, "backId");
            return (Criteria) this;
        }

        public Criteria andBackIdBetween(Long value1, Long value2) {
            addCriterion("BACK_ID between", value1, value2, "backId");
            return (Criteria) this;
        }

        public Criteria andBackIdNotBetween(Long value1, Long value2) {
            addCriterion("BACK_ID not between", value1, value2, "backId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("ORDER_ID like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("ORDER_ID not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdIsNull() {
            addCriterion("ORDER_SUB_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdIsNotNull() {
            addCriterion("ORDER_SUB_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdEqualTo(String value) {
            addCriterion("ORDER_SUB_ID =", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdNotEqualTo(String value) {
            addCriterion("ORDER_SUB_ID <>", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdGreaterThan(String value) {
            addCriterion("ORDER_SUB_ID >", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_SUB_ID >=", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdLessThan(String value) {
            addCriterion("ORDER_SUB_ID <", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_SUB_ID <=", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdLike(String value) {
            addCriterion("ORDER_SUB_ID like", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdNotLike(String value) {
            addCriterion("ORDER_SUB_ID not like", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdIn(List<String> values) {
            addCriterion("ORDER_SUB_ID in", values, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdNotIn(List<String> values) {
            addCriterion("ORDER_SUB_ID not in", values, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdBetween(String value1, String value2) {
            addCriterion("ORDER_SUB_ID between", value1, value2, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_SUB_ID not between", value1, value2, "orderSubId");
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

        public Criteria andNumIsNull() {
            addCriterion("NUM is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("NUM is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Long value) {
            addCriterion("NUM =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Long value) {
            addCriterion("NUM <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Long value) {
            addCriterion("NUM >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Long value) {
            addCriterion("NUM >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Long value) {
            addCriterion("NUM <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Long value) {
            addCriterion("NUM <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Long> values) {
            addCriterion("NUM in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Long> values) {
            addCriterion("NUM not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Long value1, Long value2) {
            addCriterion("NUM between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Long value1, Long value2) {
            addCriterion("NUM not between", value1, value2, "num");
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

        public Criteria andBackMoneyIsNull() {
            addCriterion("BACK_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andBackMoneyIsNotNull() {
            addCriterion("BACK_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andBackMoneyEqualTo(Long value) {
            addCriterion("BACK_MONEY =", value, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyNotEqualTo(Long value) {
            addCriterion("BACK_MONEY <>", value, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyGreaterThan(Long value) {
            addCriterion("BACK_MONEY >", value, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("BACK_MONEY >=", value, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyLessThan(Long value) {
            addCriterion("BACK_MONEY <", value, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyLessThanOrEqualTo(Long value) {
            addCriterion("BACK_MONEY <=", value, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyIn(List<Long> values) {
            addCriterion("BACK_MONEY in", values, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyNotIn(List<Long> values) {
            addCriterion("BACK_MONEY not in", values, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyBetween(Long value1, Long value2) {
            addCriterion("BACK_MONEY between", value1, value2, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyNotBetween(Long value1, Long value2) {
            addCriterion("BACK_MONEY not between", value1, value2, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackScoreIsNull() {
            addCriterion("BACK_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andBackScoreIsNotNull() {
            addCriterion("BACK_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andBackScoreEqualTo(Long value) {
            addCriterion("BACK_SCORE =", value, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreNotEqualTo(Long value) {
            addCriterion("BACK_SCORE <>", value, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreGreaterThan(Long value) {
            addCriterion("BACK_SCORE >", value, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("BACK_SCORE >=", value, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreLessThan(Long value) {
            addCriterion("BACK_SCORE <", value, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreLessThanOrEqualTo(Long value) {
            addCriterion("BACK_SCORE <=", value, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreIn(List<Long> values) {
            addCriterion("BACK_SCORE in", values, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreNotIn(List<Long> values) {
            addCriterion("BACK_SCORE not in", values, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreBetween(Long value1, Long value2) {
            addCriterion("BACK_SCORE between", value1, value2, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreNotBetween(Long value1, Long value2) {
            addCriterion("BACK_SCORE not between", value1, value2, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackAccountIsNull() {
            addCriterion("BACK_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andBackAccountIsNotNull() {
            addCriterion("BACK_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andBackAccountEqualTo(Long value) {
            addCriterion("BACK_ACCOUNT =", value, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountNotEqualTo(Long value) {
            addCriterion("BACK_ACCOUNT <>", value, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountGreaterThan(Long value) {
            addCriterion("BACK_ACCOUNT >", value, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountGreaterThanOrEqualTo(Long value) {
            addCriterion("BACK_ACCOUNT >=", value, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountLessThan(Long value) {
            addCriterion("BACK_ACCOUNT <", value, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountLessThanOrEqualTo(Long value) {
            addCriterion("BACK_ACCOUNT <=", value, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountIn(List<Long> values) {
            addCriterion("BACK_ACCOUNT in", values, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountNotIn(List<Long> values) {
            addCriterion("BACK_ACCOUNT not in", values, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountBetween(Long value1, Long value2) {
            addCriterion("BACK_ACCOUNT between", value1, value2, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountNotBetween(Long value1, Long value2) {
            addCriterion("BACK_ACCOUNT not between", value1, value2, "backAccount");
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