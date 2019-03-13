package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrdMainCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OrdMainCriteria() {
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

        public Criteria andOrderAmountIsNull() {
            addCriterion("ORDER_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNotNull() {
            addCriterion("ORDER_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountEqualTo(Long value) {
            addCriterion("ORDER_AMOUNT =", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotEqualTo(Long value) {
            addCriterion("ORDER_AMOUNT <>", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThan(Long value) {
            addCriterion("ORDER_AMOUNT >", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_AMOUNT >=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThan(Long value) {
            addCriterion("ORDER_AMOUNT <", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_AMOUNT <=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIn(List<Long> values) {
            addCriterion("ORDER_AMOUNT in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotIn(List<Long> values) {
            addCriterion("ORDER_AMOUNT not in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountBetween(Long value1, Long value2) {
            addCriterion("ORDER_AMOUNT between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_AMOUNT not between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyIsNull() {
            addCriterion("ORDER_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyIsNotNull() {
            addCriterion("ORDER_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyEqualTo(Long value) {
            addCriterion("ORDER_MONEY =", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyNotEqualTo(Long value) {
            addCriterion("ORDER_MONEY <>", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyGreaterThan(Long value) {
            addCriterion("ORDER_MONEY >", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_MONEY >=", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyLessThan(Long value) {
            addCriterion("ORDER_MONEY <", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_MONEY <=", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyIn(List<Long> values) {
            addCriterion("ORDER_MONEY in", values, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyNotIn(List<Long> values) {
            addCriterion("ORDER_MONEY not in", values, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyBetween(Long value1, Long value2) {
            addCriterion("ORDER_MONEY between", value1, value2, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_MONEY not between", value1, value2, "orderMoney");
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

        public Criteria andDeliverDateIsNull() {
            addCriterion("DELIVER_DATE is null");
            return (Criteria) this;
        }

        public Criteria andDeliverDateIsNotNull() {
            addCriterion("DELIVER_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverDateEqualTo(Timestamp value) {
            addCriterion("DELIVER_DATE =", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateNotEqualTo(Timestamp value) {
            addCriterion("DELIVER_DATE <>", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateGreaterThan(Timestamp value) {
            addCriterion("DELIVER_DATE >", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("DELIVER_DATE >=", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateLessThan(Timestamp value) {
            addCriterion("DELIVER_DATE <", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("DELIVER_DATE <=", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateIn(List<Timestamp> values) {
            addCriterion("DELIVER_DATE in", values, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateNotIn(List<Timestamp> values) {
            addCriterion("DELIVER_DATE not in", values, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("DELIVER_DATE between", value1, value2, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("DELIVER_DATE not between", value1, value2, "deliverDate");
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

        public Criteria andOrderTwoStatusIsNull() {
            addCriterion("ORDER_TWO_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andOrderTwoStatusIsNotNull() {
            addCriterion("ORDER_TWO_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTwoStatusEqualTo(String value) {
            addCriterion("ORDER_TWO_STATUS =", value, "orderTwoStatus");
            return (Criteria) this;
        }

        public Criteria andOrderTwoStatusNotEqualTo(String value) {
            addCriterion("ORDER_TWO_STATUS <>", value, "orderTwoStatus");
            return (Criteria) this;
        }

        public Criteria andOrderTwoStatusGreaterThan(String value) {
            addCriterion("ORDER_TWO_STATUS >", value, "orderTwoStatus");
            return (Criteria) this;
        }

        public Criteria andOrderTwoStatusGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_TWO_STATUS >=", value, "orderTwoStatus");
            return (Criteria) this;
        }

        public Criteria andOrderTwoStatusLessThan(String value) {
            addCriterion("ORDER_TWO_STATUS <", value, "orderTwoStatus");
            return (Criteria) this;
        }

        public Criteria andOrderTwoStatusLessThanOrEqualTo(String value) {
            addCriterion("ORDER_TWO_STATUS <=", value, "orderTwoStatus");
            return (Criteria) this;
        }

        public Criteria andOrderTwoStatusLike(String value) {
            addCriterion("ORDER_TWO_STATUS like", value, "orderTwoStatus");
            return (Criteria) this;
        }

        public Criteria andOrderTwoStatusNotLike(String value) {
            addCriterion("ORDER_TWO_STATUS not like", value, "orderTwoStatus");
            return (Criteria) this;
        }

        public Criteria andOrderTwoStatusIn(List<String> values) {
            addCriterion("ORDER_TWO_STATUS in", values, "orderTwoStatus");
            return (Criteria) this;
        }

        public Criteria andOrderTwoStatusNotIn(List<String> values) {
            addCriterion("ORDER_TWO_STATUS not in", values, "orderTwoStatus");
            return (Criteria) this;
        }

        public Criteria andOrderTwoStatusBetween(String value1, String value2) {
            addCriterion("ORDER_TWO_STATUS between", value1, value2, "orderTwoStatus");
            return (Criteria) this;
        }

        public Criteria andOrderTwoStatusNotBetween(String value1, String value2) {
            addCriterion("ORDER_TWO_STATUS not between", value1, value2, "orderTwoStatus");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNull() {
            addCriterion("ORDER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("ORDER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(String value) {
            addCriterion("ORDER_TYPE =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(String value) {
            addCriterion("ORDER_TYPE <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(String value) {
            addCriterion("ORDER_TYPE >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_TYPE >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(String value) {
            addCriterion("ORDER_TYPE <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(String value) {
            addCriterion("ORDER_TYPE <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLike(String value) {
            addCriterion("ORDER_TYPE like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotLike(String value) {
            addCriterion("ORDER_TYPE not like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<String> values) {
            addCriterion("ORDER_TYPE in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<String> values) {
            addCriterion("ORDER_TYPE not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(String value1, String value2) {
            addCriterion("ORDER_TYPE between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(String value1, String value2) {
            addCriterion("ORDER_TYPE not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("PAY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("PAY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(String value) {
            addCriterion("PAY_TYPE =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(String value) {
            addCriterion("PAY_TYPE <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(String value) {
            addCriterion("PAY_TYPE >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_TYPE >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(String value) {
            addCriterion("PAY_TYPE <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(String value) {
            addCriterion("PAY_TYPE <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLike(String value) {
            addCriterion("PAY_TYPE like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotLike(String value) {
            addCriterion("PAY_TYPE not like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<String> values) {
            addCriterion("PAY_TYPE in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<String> values) {
            addCriterion("PAY_TYPE not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(String value1, String value2) {
            addCriterion("PAY_TYPE between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(String value1, String value2) {
            addCriterion("PAY_TYPE not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayFlagIsNull() {
            addCriterion("PAY_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andPayFlagIsNotNull() {
            addCriterion("PAY_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andPayFlagEqualTo(String value) {
            addCriterion("PAY_FLAG =", value, "payFlag");
            return (Criteria) this;
        }

        public Criteria andPayFlagNotEqualTo(String value) {
            addCriterion("PAY_FLAG <>", value, "payFlag");
            return (Criteria) this;
        }

        public Criteria andPayFlagGreaterThan(String value) {
            addCriterion("PAY_FLAG >", value, "payFlag");
            return (Criteria) this;
        }

        public Criteria andPayFlagGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_FLAG >=", value, "payFlag");
            return (Criteria) this;
        }

        public Criteria andPayFlagLessThan(String value) {
            addCriterion("PAY_FLAG <", value, "payFlag");
            return (Criteria) this;
        }

        public Criteria andPayFlagLessThanOrEqualTo(String value) {
            addCriterion("PAY_FLAG <=", value, "payFlag");
            return (Criteria) this;
        }

        public Criteria andPayFlagLike(String value) {
            addCriterion("PAY_FLAG like", value, "payFlag");
            return (Criteria) this;
        }

        public Criteria andPayFlagNotLike(String value) {
            addCriterion("PAY_FLAG not like", value, "payFlag");
            return (Criteria) this;
        }

        public Criteria andPayFlagIn(List<String> values) {
            addCriterion("PAY_FLAG in", values, "payFlag");
            return (Criteria) this;
        }

        public Criteria andPayFlagNotIn(List<String> values) {
            addCriterion("PAY_FLAG not in", values, "payFlag");
            return (Criteria) this;
        }

        public Criteria andPayFlagBetween(String value1, String value2) {
            addCriterion("PAY_FLAG between", value1, value2, "payFlag");
            return (Criteria) this;
        }

        public Criteria andPayFlagNotBetween(String value1, String value2) {
            addCriterion("PAY_FLAG not between", value1, value2, "payFlag");
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

        public Criteria andSourceIsNull() {
            addCriterion("SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("SOURCE =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("SOURCE <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("SOURCE >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("SOURCE <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("SOURCE <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("SOURCE like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("SOURCE not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("SOURCE in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("SOURCE not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("SOURCE between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("SOURCE not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andPayLockIsNull() {
            addCriterion("PAY_LOCK is null");
            return (Criteria) this;
        }

        public Criteria andPayLockIsNotNull() {
            addCriterion("PAY_LOCK is not null");
            return (Criteria) this;
        }

        public Criteria andPayLockEqualTo(String value) {
            addCriterion("PAY_LOCK =", value, "payLock");
            return (Criteria) this;
        }

        public Criteria andPayLockNotEqualTo(String value) {
            addCriterion("PAY_LOCK <>", value, "payLock");
            return (Criteria) this;
        }

        public Criteria andPayLockGreaterThan(String value) {
            addCriterion("PAY_LOCK >", value, "payLock");
            return (Criteria) this;
        }

        public Criteria andPayLockGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_LOCK >=", value, "payLock");
            return (Criteria) this;
        }

        public Criteria andPayLockLessThan(String value) {
            addCriterion("PAY_LOCK <", value, "payLock");
            return (Criteria) this;
        }

        public Criteria andPayLockLessThanOrEqualTo(String value) {
            addCriterion("PAY_LOCK <=", value, "payLock");
            return (Criteria) this;
        }

        public Criteria andPayLockLike(String value) {
            addCriterion("PAY_LOCK like", value, "payLock");
            return (Criteria) this;
        }

        public Criteria andPayLockNotLike(String value) {
            addCriterion("PAY_LOCK not like", value, "payLock");
            return (Criteria) this;
        }

        public Criteria andPayLockIn(List<String> values) {
            addCriterion("PAY_LOCK in", values, "payLock");
            return (Criteria) this;
        }

        public Criteria andPayLockNotIn(List<String> values) {
            addCriterion("PAY_LOCK not in", values, "payLock");
            return (Criteria) this;
        }

        public Criteria andPayLockBetween(String value1, String value2) {
            addCriterion("PAY_LOCK between", value1, value2, "payLock");
            return (Criteria) this;
        }

        public Criteria andPayLockNotBetween(String value1, String value2) {
            addCriterion("PAY_LOCK not between", value1, value2, "payLock");
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

        public Criteria andSysTypeIsNull() {
            addCriterion("SYS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSysTypeIsNotNull() {
            addCriterion("SYS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSysTypeEqualTo(String value) {
            addCriterion("SYS_TYPE =", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotEqualTo(String value) {
            addCriterion("SYS_TYPE <>", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeGreaterThan(String value) {
            addCriterion("SYS_TYPE >", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_TYPE >=", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeLessThan(String value) {
            addCriterion("SYS_TYPE <", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeLessThanOrEqualTo(String value) {
            addCriterion("SYS_TYPE <=", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeLike(String value) {
            addCriterion("SYS_TYPE like", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotLike(String value) {
            addCriterion("SYS_TYPE not like", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeIn(List<String> values) {
            addCriterion("SYS_TYPE in", values, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotIn(List<String> values) {
            addCriterion("SYS_TYPE not in", values, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeBetween(String value1, String value2) {
            addCriterion("SYS_TYPE between", value1, value2, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotBetween(String value1, String value2) {
            addCriterion("SYS_TYPE not between", value1, value2, "sysType");
            return (Criteria) this;
        }

        public Criteria andPayWayIsNull() {
            addCriterion("PAY_WAY is null");
            return (Criteria) this;
        }

        public Criteria andPayWayIsNotNull() {
            addCriterion("PAY_WAY is not null");
            return (Criteria) this;
        }

        public Criteria andPayWayEqualTo(String value) {
            addCriterion("PAY_WAY =", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotEqualTo(String value) {
            addCriterion("PAY_WAY <>", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayGreaterThan(String value) {
            addCriterion("PAY_WAY >", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_WAY >=", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayLessThan(String value) {
            addCriterion("PAY_WAY <", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayLessThanOrEqualTo(String value) {
            addCriterion("PAY_WAY <=", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayLike(String value) {
            addCriterion("PAY_WAY like", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotLike(String value) {
            addCriterion("PAY_WAY not like", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayIn(List<String> values) {
            addCriterion("PAY_WAY in", values, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotIn(List<String> values) {
            addCriterion("PAY_WAY not in", values, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayBetween(String value1, String value2) {
            addCriterion("PAY_WAY between", value1, value2, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotBetween(String value1, String value2) {
            addCriterion("PAY_WAY not between", value1, value2, "payWay");
            return (Criteria) this;
        }

        public Criteria andOrderScoreIsNull() {
            addCriterion("ORDER_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andOrderScoreIsNotNull() {
            addCriterion("ORDER_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andOrderScoreEqualTo(Long value) {
            addCriterion("ORDER_SCORE =", value, "orderScore");
            return (Criteria) this;
        }

        public Criteria andOrderScoreNotEqualTo(Long value) {
            addCriterion("ORDER_SCORE <>", value, "orderScore");
            return (Criteria) this;
        }

        public Criteria andOrderScoreGreaterThan(Long value) {
            addCriterion("ORDER_SCORE >", value, "orderScore");
            return (Criteria) this;
        }

        public Criteria andOrderScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_SCORE >=", value, "orderScore");
            return (Criteria) this;
        }

        public Criteria andOrderScoreLessThan(Long value) {
            addCriterion("ORDER_SCORE <", value, "orderScore");
            return (Criteria) this;
        }

        public Criteria andOrderScoreLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_SCORE <=", value, "orderScore");
            return (Criteria) this;
        }

        public Criteria andOrderScoreIn(List<Long> values) {
            addCriterion("ORDER_SCORE in", values, "orderScore");
            return (Criteria) this;
        }

        public Criteria andOrderScoreNotIn(List<Long> values) {
            addCriterion("ORDER_SCORE not in", values, "orderScore");
            return (Criteria) this;
        }

        public Criteria andOrderScoreBetween(Long value1, Long value2) {
            addCriterion("ORDER_SCORE between", value1, value2, "orderScore");
            return (Criteria) this;
        }

        public Criteria andOrderScoreNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_SCORE not between", value1, value2, "orderScore");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNull() {
            addCriterion("INVOICE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNotNull() {
            addCriterion("INVOICE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeEqualTo(String value) {
            addCriterion("INVOICE_TYPE =", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotEqualTo(String value) {
            addCriterion("INVOICE_TYPE <>", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThan(String value) {
            addCriterion("INVOICE_TYPE >", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_TYPE >=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThan(String value) {
            addCriterion("INVOICE_TYPE <", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_TYPE <=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLike(String value) {
            addCriterion("INVOICE_TYPE like", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotLike(String value) {
            addCriterion("INVOICE_TYPE not like", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIn(List<String> values) {
            addCriterion("INVOICE_TYPE in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotIn(List<String> values) {
            addCriterion("INVOICE_TYPE not in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeBetween(String value1, String value2) {
            addCriterion("INVOICE_TYPE between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotBetween(String value1, String value2) {
            addCriterion("INVOICE_TYPE not between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNull() {
            addCriterion("CONTACT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNotNull() {
            addCriterion("CONTACT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andContactNameEqualTo(String value) {
            addCriterion("CONTACT_NAME =", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotEqualTo(String value) {
            addCriterion("CONTACT_NAME <>", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThan(String value) {
            addCriterion("CONTACT_NAME >", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_NAME >=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThan(String value) {
            addCriterion("CONTACT_NAME <", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_NAME <=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLike(String value) {
            addCriterion("CONTACT_NAME like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotLike(String value) {
            addCriterion("CONTACT_NAME not like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameIn(List<String> values) {
            addCriterion("CONTACT_NAME in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotIn(List<String> values) {
            addCriterion("CONTACT_NAME not in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameBetween(String value1, String value2) {
            addCriterion("CONTACT_NAME between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotBetween(String value1, String value2) {
            addCriterion("CONTACT_NAME not between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNull() {
            addCriterion("CONTACT_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNotNull() {
            addCriterion("CONTACT_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneEqualTo(String value) {
            addCriterion("CONTACT_PHONE =", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotEqualTo(String value) {
            addCriterion("CONTACT_PHONE <>", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThan(String value) {
            addCriterion("CONTACT_PHONE >", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_PHONE >=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThan(String value) {
            addCriterion("CONTACT_PHONE <", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_PHONE <=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLike(String value) {
            addCriterion("CONTACT_PHONE like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotLike(String value) {
            addCriterion("CONTACT_PHONE not like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIn(List<String> values) {
            addCriterion("CONTACT_PHONE in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotIn(List<String> values) {
            addCriterion("CONTACT_PHONE not in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneBetween(String value1, String value2) {
            addCriterion("CONTACT_PHONE between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotBetween(String value1, String value2) {
            addCriterion("CONTACT_PHONE not between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andChnlAddressIsNull() {
            addCriterion("CHNL_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andChnlAddressIsNotNull() {
            addCriterion("CHNL_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andChnlAddressEqualTo(String value) {
            addCriterion("CHNL_ADDRESS =", value, "chnlAddress");
            return (Criteria) this;
        }

        public Criteria andChnlAddressNotEqualTo(String value) {
            addCriterion("CHNL_ADDRESS <>", value, "chnlAddress");
            return (Criteria) this;
        }

        public Criteria andChnlAddressGreaterThan(String value) {
            addCriterion("CHNL_ADDRESS >", value, "chnlAddress");
            return (Criteria) this;
        }

        public Criteria andChnlAddressGreaterThanOrEqualTo(String value) {
            addCriterion("CHNL_ADDRESS >=", value, "chnlAddress");
            return (Criteria) this;
        }

        public Criteria andChnlAddressLessThan(String value) {
            addCriterion("CHNL_ADDRESS <", value, "chnlAddress");
            return (Criteria) this;
        }

        public Criteria andChnlAddressLessThanOrEqualTo(String value) {
            addCriterion("CHNL_ADDRESS <=", value, "chnlAddress");
            return (Criteria) this;
        }

        public Criteria andChnlAddressLike(String value) {
            addCriterion("CHNL_ADDRESS like", value, "chnlAddress");
            return (Criteria) this;
        }

        public Criteria andChnlAddressNotLike(String value) {
            addCriterion("CHNL_ADDRESS not like", value, "chnlAddress");
            return (Criteria) this;
        }

        public Criteria andChnlAddressIn(List<String> values) {
            addCriterion("CHNL_ADDRESS in", values, "chnlAddress");
            return (Criteria) this;
        }

        public Criteria andChnlAddressNotIn(List<String> values) {
            addCriterion("CHNL_ADDRESS not in", values, "chnlAddress");
            return (Criteria) this;
        }

        public Criteria andChnlAddressBetween(String value1, String value2) {
            addCriterion("CHNL_ADDRESS between", value1, value2, "chnlAddress");
            return (Criteria) this;
        }

        public Criteria andChnlAddressNotBetween(String value1, String value2) {
            addCriterion("CHNL_ADDRESS not between", value1, value2, "chnlAddress");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNull() {
            addCriterion("POST_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNotNull() {
            addCriterion("POST_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPostCodeEqualTo(String value) {
            addCriterion("POST_CODE =", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotEqualTo(String value) {
            addCriterion("POST_CODE <>", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThan(String value) {
            addCriterion("POST_CODE >", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThanOrEqualTo(String value) {
            addCriterion("POST_CODE >=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThan(String value) {
            addCriterion("POST_CODE <", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThanOrEqualTo(String value) {
            addCriterion("POST_CODE <=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLike(String value) {
            addCriterion("POST_CODE like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotLike(String value) {
            addCriterion("POST_CODE not like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeIn(List<String> values) {
            addCriterion("POST_CODE in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotIn(List<String> values) {
            addCriterion("POST_CODE not in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeBetween(String value1, String value2) {
            addCriterion("POST_CODE between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotBetween(String value1, String value2) {
            addCriterion("POST_CODE not between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andContactNumberIsNull() {
            addCriterion("CONTACT_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andContactNumberIsNotNull() {
            addCriterion("CONTACT_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andContactNumberEqualTo(String value) {
            addCriterion("CONTACT_NUMBER =", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotEqualTo(String value) {
            addCriterion("CONTACT_NUMBER <>", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberGreaterThan(String value) {
            addCriterion("CONTACT_NUMBER >", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_NUMBER >=", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberLessThan(String value) {
            addCriterion("CONTACT_NUMBER <", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_NUMBER <=", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberLike(String value) {
            addCriterion("CONTACT_NUMBER like", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotLike(String value) {
            addCriterion("CONTACT_NUMBER not like", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberIn(List<String> values) {
            addCriterion("CONTACT_NUMBER in", values, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotIn(List<String> values) {
            addCriterion("CONTACT_NUMBER not in", values, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberBetween(String value1, String value2) {
            addCriterion("CONTACT_NUMBER between", value1, value2, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotBetween(String value1, String value2) {
            addCriterion("CONTACT_NUMBER not between", value1, value2, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("PROVINCE is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("PROVINCE is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("PROVINCE =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("PROVINCE <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("PROVINCE >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("PROVINCE >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("PROVINCE <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("PROVINCE <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("PROVINCE like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("PROVINCE not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("PROVINCE in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("PROVINCE not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("PROVINCE between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("PROVINCE not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNull() {
            addCriterion("CITY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNotNull() {
            addCriterion("CITY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCityCodeEqualTo(String value) {
            addCriterion("CITY_CODE =", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotEqualTo(String value) {
            addCriterion("CITY_CODE <>", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThan(String value) {
            addCriterion("CITY_CODE >", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_CODE >=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThan(String value) {
            addCriterion("CITY_CODE <", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThanOrEqualTo(String value) {
            addCriterion("CITY_CODE <=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLike(String value) {
            addCriterion("CITY_CODE like", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotLike(String value) {
            addCriterion("CITY_CODE not like", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeIn(List<String> values) {
            addCriterion("CITY_CODE in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotIn(List<String> values) {
            addCriterion("CITY_CODE not in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeBetween(String value1, String value2) {
            addCriterion("CITY_CODE between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotBetween(String value1, String value2) {
            addCriterion("CITY_CODE not between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeIsNull() {
            addCriterion("COUNTY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCountyCodeIsNotNull() {
            addCriterion("COUNTY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCountyCodeEqualTo(String value) {
            addCriterion("COUNTY_CODE =", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeNotEqualTo(String value) {
            addCriterion("COUNTY_CODE <>", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeGreaterThan(String value) {
            addCriterion("COUNTY_CODE >", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("COUNTY_CODE >=", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeLessThan(String value) {
            addCriterion("COUNTY_CODE <", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeLessThanOrEqualTo(String value) {
            addCriterion("COUNTY_CODE <=", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeLike(String value) {
            addCriterion("COUNTY_CODE like", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeNotLike(String value) {
            addCriterion("COUNTY_CODE not like", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeIn(List<String> values) {
            addCriterion("COUNTY_CODE in", values, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeNotIn(List<String> values) {
            addCriterion("COUNTY_CODE not in", values, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeBetween(String value1, String value2) {
            addCriterion("COUNTY_CODE between", value1, value2, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeNotBetween(String value1, String value2) {
            addCriterion("COUNTY_CODE not between", value1, value2, "countyCode");
            return (Criteria) this;
        }

        public Criteria andBringNameIsNull() {
            addCriterion("BRING_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBringNameIsNotNull() {
            addCriterion("BRING_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBringNameEqualTo(String value) {
            addCriterion("BRING_NAME =", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameNotEqualTo(String value) {
            addCriterion("BRING_NAME <>", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameGreaterThan(String value) {
            addCriterion("BRING_NAME >", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameGreaterThanOrEqualTo(String value) {
            addCriterion("BRING_NAME >=", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameLessThan(String value) {
            addCriterion("BRING_NAME <", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameLessThanOrEqualTo(String value) {
            addCriterion("BRING_NAME <=", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameLike(String value) {
            addCriterion("BRING_NAME like", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameNotLike(String value) {
            addCriterion("BRING_NAME not like", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameIn(List<String> values) {
            addCriterion("BRING_NAME in", values, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameNotIn(List<String> values) {
            addCriterion("BRING_NAME not in", values, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameBetween(String value1, String value2) {
            addCriterion("BRING_NAME between", value1, value2, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameNotBetween(String value1, String value2) {
            addCriterion("BRING_NAME not between", value1, value2, "bringName");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNull() {
            addCriterion("CARD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNotNull() {
            addCriterion("CARD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCardTypeEqualTo(String value) {
            addCriterion("CARD_TYPE =", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotEqualTo(String value) {
            addCriterion("CARD_TYPE <>", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThan(String value) {
            addCriterion("CARD_TYPE >", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_TYPE >=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThan(String value) {
            addCriterion("CARD_TYPE <", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThanOrEqualTo(String value) {
            addCriterion("CARD_TYPE <=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLike(String value) {
            addCriterion("CARD_TYPE like", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotLike(String value) {
            addCriterion("CARD_TYPE not like", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeIn(List<String> values) {
            addCriterion("CARD_TYPE in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotIn(List<String> values) {
            addCriterion("CARD_TYPE not in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeBetween(String value1, String value2) {
            addCriterion("CARD_TYPE between", value1, value2, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotBetween(String value1, String value2) {
            addCriterion("CARD_TYPE not between", value1, value2, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNull() {
            addCriterion("CARD_ID is null");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNotNull() {
            addCriterion("CARD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCardIdEqualTo(String value) {
            addCriterion("CARD_ID =", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotEqualTo(String value) {
            addCriterion("CARD_ID <>", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThan(String value) {
            addCriterion("CARD_ID >", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_ID >=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThan(String value) {
            addCriterion("CARD_ID <", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThanOrEqualTo(String value) {
            addCriterion("CARD_ID <=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLike(String value) {
            addCriterion("CARD_ID like", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotLike(String value) {
            addCriterion("CARD_ID not like", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdIn(List<String> values) {
            addCriterion("CARD_ID in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotIn(List<String> values) {
            addCriterion("CARD_ID not in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdBetween(String value1, String value2) {
            addCriterion("CARD_ID between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotBetween(String value1, String value2) {
            addCriterion("CARD_ID not between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andBringNumberIsNull() {
            addCriterion("BRING_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andBringNumberIsNotNull() {
            addCriterion("BRING_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andBringNumberEqualTo(String value) {
            addCriterion("BRING_NUMBER =", value, "bringNumber");
            return (Criteria) this;
        }

        public Criteria andBringNumberNotEqualTo(String value) {
            addCriterion("BRING_NUMBER <>", value, "bringNumber");
            return (Criteria) this;
        }

        public Criteria andBringNumberGreaterThan(String value) {
            addCriterion("BRING_NUMBER >", value, "bringNumber");
            return (Criteria) this;
        }

        public Criteria andBringNumberGreaterThanOrEqualTo(String value) {
            addCriterion("BRING_NUMBER >=", value, "bringNumber");
            return (Criteria) this;
        }

        public Criteria andBringNumberLessThan(String value) {
            addCriterion("BRING_NUMBER <", value, "bringNumber");
            return (Criteria) this;
        }

        public Criteria andBringNumberLessThanOrEqualTo(String value) {
            addCriterion("BRING_NUMBER <=", value, "bringNumber");
            return (Criteria) this;
        }

        public Criteria andBringNumberLike(String value) {
            addCriterion("BRING_NUMBER like", value, "bringNumber");
            return (Criteria) this;
        }

        public Criteria andBringNumberNotLike(String value) {
            addCriterion("BRING_NUMBER not like", value, "bringNumber");
            return (Criteria) this;
        }

        public Criteria andBringNumberIn(List<String> values) {
            addCriterion("BRING_NUMBER in", values, "bringNumber");
            return (Criteria) this;
        }

        public Criteria andBringNumberNotIn(List<String> values) {
            addCriterion("BRING_NUMBER not in", values, "bringNumber");
            return (Criteria) this;
        }

        public Criteria andBringNumberBetween(String value1, String value2) {
            addCriterion("BRING_NUMBER between", value1, value2, "bringNumber");
            return (Criteria) this;
        }

        public Criteria andBringNumberNotBetween(String value1, String value2) {
            addCriterion("BRING_NUMBER not between", value1, value2, "bringNumber");
            return (Criteria) this;
        }

        public Criteria andBringPhoneIsNull() {
            addCriterion("BRING_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andBringPhoneIsNotNull() {
            addCriterion("BRING_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andBringPhoneEqualTo(String value) {
            addCriterion("BRING_PHONE =", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneNotEqualTo(String value) {
            addCriterion("BRING_PHONE <>", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneGreaterThan(String value) {
            addCriterion("BRING_PHONE >", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("BRING_PHONE >=", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneLessThan(String value) {
            addCriterion("BRING_PHONE <", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneLessThanOrEqualTo(String value) {
            addCriterion("BRING_PHONE <=", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneLike(String value) {
            addCriterion("BRING_PHONE like", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneNotLike(String value) {
            addCriterion("BRING_PHONE not like", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneIn(List<String> values) {
            addCriterion("BRING_PHONE in", values, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneNotIn(List<String> values) {
            addCriterion("BRING_PHONE not in", values, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneBetween(String value1, String value2) {
            addCriterion("BRING_PHONE between", value1, value2, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneNotBetween(String value1, String value2) {
            addCriterion("BRING_PHONE not between", value1, value2, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBillingFlagIsNull() {
            addCriterion("BILLING_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andBillingFlagIsNotNull() {
            addCriterion("BILLING_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andBillingFlagEqualTo(String value) {
            addCriterion("BILLING_FLAG =", value, "billingFlag");
            return (Criteria) this;
        }

        public Criteria andBillingFlagNotEqualTo(String value) {
            addCriterion("BILLING_FLAG <>", value, "billingFlag");
            return (Criteria) this;
        }

        public Criteria andBillingFlagGreaterThan(String value) {
            addCriterion("BILLING_FLAG >", value, "billingFlag");
            return (Criteria) this;
        }

        public Criteria andBillingFlagGreaterThanOrEqualTo(String value) {
            addCriterion("BILLING_FLAG >=", value, "billingFlag");
            return (Criteria) this;
        }

        public Criteria andBillingFlagLessThan(String value) {
            addCriterion("BILLING_FLAG <", value, "billingFlag");
            return (Criteria) this;
        }

        public Criteria andBillingFlagLessThanOrEqualTo(String value) {
            addCriterion("BILLING_FLAG <=", value, "billingFlag");
            return (Criteria) this;
        }

        public Criteria andBillingFlagLike(String value) {
            addCriterion("BILLING_FLAG like", value, "billingFlag");
            return (Criteria) this;
        }

        public Criteria andBillingFlagNotLike(String value) {
            addCriterion("BILLING_FLAG not like", value, "billingFlag");
            return (Criteria) this;
        }

        public Criteria andBillingFlagIn(List<String> values) {
            addCriterion("BILLING_FLAG in", values, "billingFlag");
            return (Criteria) this;
        }

        public Criteria andBillingFlagNotIn(List<String> values) {
            addCriterion("BILLING_FLAG not in", values, "billingFlag");
            return (Criteria) this;
        }

        public Criteria andBillingFlagBetween(String value1, String value2) {
            addCriterion("BILLING_FLAG between", value1, value2, "billingFlag");
            return (Criteria) this;
        }

        public Criteria andBillingFlagNotBetween(String value1, String value2) {
            addCriterion("BILLING_FLAG not between", value1, value2, "billingFlag");
            return (Criteria) this;
        }

        public Criteria andSendInvoiceTypeIsNull() {
            addCriterion("SEND_INVOICE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSendInvoiceTypeIsNotNull() {
            addCriterion("SEND_INVOICE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSendInvoiceTypeEqualTo(String value) {
            addCriterion("SEND_INVOICE_TYPE =", value, "sendInvoiceType");
            return (Criteria) this;
        }

        public Criteria andSendInvoiceTypeNotEqualTo(String value) {
            addCriterion("SEND_INVOICE_TYPE <>", value, "sendInvoiceType");
            return (Criteria) this;
        }

        public Criteria andSendInvoiceTypeGreaterThan(String value) {
            addCriterion("SEND_INVOICE_TYPE >", value, "sendInvoiceType");
            return (Criteria) this;
        }

        public Criteria andSendInvoiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_INVOICE_TYPE >=", value, "sendInvoiceType");
            return (Criteria) this;
        }

        public Criteria andSendInvoiceTypeLessThan(String value) {
            addCriterion("SEND_INVOICE_TYPE <", value, "sendInvoiceType");
            return (Criteria) this;
        }

        public Criteria andSendInvoiceTypeLessThanOrEqualTo(String value) {
            addCriterion("SEND_INVOICE_TYPE <=", value, "sendInvoiceType");
            return (Criteria) this;
        }

        public Criteria andSendInvoiceTypeLike(String value) {
            addCriterion("SEND_INVOICE_TYPE like", value, "sendInvoiceType");
            return (Criteria) this;
        }

        public Criteria andSendInvoiceTypeNotLike(String value) {
            addCriterion("SEND_INVOICE_TYPE not like", value, "sendInvoiceType");
            return (Criteria) this;
        }

        public Criteria andSendInvoiceTypeIn(List<String> values) {
            addCriterion("SEND_INVOICE_TYPE in", values, "sendInvoiceType");
            return (Criteria) this;
        }

        public Criteria andSendInvoiceTypeNotIn(List<String> values) {
            addCriterion("SEND_INVOICE_TYPE not in", values, "sendInvoiceType");
            return (Criteria) this;
        }

        public Criteria andSendInvoiceTypeBetween(String value1, String value2) {
            addCriterion("SEND_INVOICE_TYPE between", value1, value2, "sendInvoiceType");
            return (Criteria) this;
        }

        public Criteria andSendInvoiceTypeNotBetween(String value1, String value2) {
            addCriterion("SEND_INVOICE_TYPE not between", value1, value2, "sendInvoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceExpressNoIsNull() {
            addCriterion("INVOICE_EXPRESS_NO is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceExpressNoIsNotNull() {
            addCriterion("INVOICE_EXPRESS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceExpressNoEqualTo(String value) {
            addCriterion("INVOICE_EXPRESS_NO =", value, "invoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceExpressNoNotEqualTo(String value) {
            addCriterion("INVOICE_EXPRESS_NO <>", value, "invoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceExpressNoGreaterThan(String value) {
            addCriterion("INVOICE_EXPRESS_NO >", value, "invoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_EXPRESS_NO >=", value, "invoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceExpressNoLessThan(String value) {
            addCriterion("INVOICE_EXPRESS_NO <", value, "invoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceExpressNoLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_EXPRESS_NO <=", value, "invoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceExpressNoLike(String value) {
            addCriterion("INVOICE_EXPRESS_NO like", value, "invoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceExpressNoNotLike(String value) {
            addCriterion("INVOICE_EXPRESS_NO not like", value, "invoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceExpressNoIn(List<String> values) {
            addCriterion("INVOICE_EXPRESS_NO in", values, "invoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceExpressNoNotIn(List<String> values) {
            addCriterion("INVOICE_EXPRESS_NO not in", values, "invoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceExpressNoBetween(String value1, String value2) {
            addCriterion("INVOICE_EXPRESS_NO between", value1, value2, "invoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceExpressNoNotBetween(String value1, String value2) {
            addCriterion("INVOICE_EXPRESS_NO not between", value1, value2, "invoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andBasicMoneyIsNull() {
            addCriterion("BASIC_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andBasicMoneyIsNotNull() {
            addCriterion("BASIC_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andBasicMoneyEqualTo(Long value) {
            addCriterion("BASIC_MONEY =", value, "basicMoney");
            return (Criteria) this;
        }

        public Criteria andBasicMoneyNotEqualTo(Long value) {
            addCriterion("BASIC_MONEY <>", value, "basicMoney");
            return (Criteria) this;
        }

        public Criteria andBasicMoneyGreaterThan(Long value) {
            addCriterion("BASIC_MONEY >", value, "basicMoney");
            return (Criteria) this;
        }

        public Criteria andBasicMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("BASIC_MONEY >=", value, "basicMoney");
            return (Criteria) this;
        }

        public Criteria andBasicMoneyLessThan(Long value) {
            addCriterion("BASIC_MONEY <", value, "basicMoney");
            return (Criteria) this;
        }

        public Criteria andBasicMoneyLessThanOrEqualTo(Long value) {
            addCriterion("BASIC_MONEY <=", value, "basicMoney");
            return (Criteria) this;
        }

        public Criteria andBasicMoneyIn(List<Long> values) {
            addCriterion("BASIC_MONEY in", values, "basicMoney");
            return (Criteria) this;
        }

        public Criteria andBasicMoneyNotIn(List<Long> values) {
            addCriterion("BASIC_MONEY not in", values, "basicMoney");
            return (Criteria) this;
        }

        public Criteria andBasicMoneyBetween(Long value1, Long value2) {
            addCriterion("BASIC_MONEY between", value1, value2, "basicMoney");
            return (Criteria) this;
        }

        public Criteria andBasicMoneyNotBetween(Long value1, Long value2) {
            addCriterion("BASIC_MONEY not between", value1, value2, "basicMoney");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileIsNull() {
            addCriterion("IS_IN_AUDIT_FILE is null");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileIsNotNull() {
            addCriterion("IS_IN_AUDIT_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileEqualTo(String value) {
            addCriterion("IS_IN_AUDIT_FILE =", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileNotEqualTo(String value) {
            addCriterion("IS_IN_AUDIT_FILE <>", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileGreaterThan(String value) {
            addCriterion("IS_IN_AUDIT_FILE >", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileGreaterThanOrEqualTo(String value) {
            addCriterion("IS_IN_AUDIT_FILE >=", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileLessThan(String value) {
            addCriterion("IS_IN_AUDIT_FILE <", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileLessThanOrEqualTo(String value) {
            addCriterion("IS_IN_AUDIT_FILE <=", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileLike(String value) {
            addCriterion("IS_IN_AUDIT_FILE like", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileNotLike(String value) {
            addCriterion("IS_IN_AUDIT_FILE not like", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileIn(List<String> values) {
            addCriterion("IS_IN_AUDIT_FILE in", values, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileNotIn(List<String> values) {
            addCriterion("IS_IN_AUDIT_FILE not in", values, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileBetween(String value1, String value2) {
            addCriterion("IS_IN_AUDIT_FILE between", value1, value2, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileNotBetween(String value1, String value2) {
            addCriterion("IS_IN_AUDIT_FILE not between", value1, value2, "isInAuditFile");
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

        public Criteria andStaffNameIsNull() {
            addCriterion("STAFF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStaffNameIsNotNull() {
            addCriterion("STAFF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStaffNameEqualTo(String value) {
            addCriterion("STAFF_NAME =", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotEqualTo(String value) {
            addCriterion("STAFF_NAME <>", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameGreaterThan(String value) {
            addCriterion("STAFF_NAME >", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_NAME >=", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameLessThan(String value) {
            addCriterion("STAFF_NAME <", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameLessThanOrEqualTo(String value) {
            addCriterion("STAFF_NAME <=", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameLike(String value) {
            addCriterion("STAFF_NAME like", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotLike(String value) {
            addCriterion("STAFF_NAME not like", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameIn(List<String> values) {
            addCriterion("STAFF_NAME in", values, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotIn(List<String> values) {
            addCriterion("STAFF_NAME not in", values, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameBetween(String value1, String value2) {
            addCriterion("STAFF_NAME between", value1, value2, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotBetween(String value1, String value2) {
            addCriterion("STAFF_NAME not between", value1, value2, "staffName");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("COMPANY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("COMPANY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Long value) {
            addCriterion("COMPANY_ID =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Long value) {
            addCriterion("COMPANY_ID <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Long value) {
            addCriterion("COMPANY_ID >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("COMPANY_ID >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Long value) {
            addCriterion("COMPANY_ID <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Long value) {
            addCriterion("COMPANY_ID <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Long> values) {
            addCriterion("COMPANY_ID in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Long> values) {
            addCriterion("COMPANY_ID not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Long value1, Long value2) {
            addCriterion("COMPANY_ID between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Long value1, Long value2) {
            addCriterion("COMPANY_ID not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("COMPANY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("COMPANY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("COMPANY_NAME =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("COMPANY_NAME <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("COMPANY_NAME >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_NAME >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("COMPANY_NAME <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_NAME <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("COMPANY_NAME like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("COMPANY_NAME not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("COMPANY_NAME in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("COMPANY_NAME not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("COMPANY_NAME between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("COMPANY_NAME not between", value1, value2, "companyName");
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

        public Criteria andPayTranNoIsNull() {
            addCriterion("PAY_TRAN_NO is null");
            return (Criteria) this;
        }

        public Criteria andPayTranNoIsNotNull() {
            addCriterion("PAY_TRAN_NO is not null");
            return (Criteria) this;
        }

        public Criteria andPayTranNoEqualTo(String value) {
            addCriterion("PAY_TRAN_NO =", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoNotEqualTo(String value) {
            addCriterion("PAY_TRAN_NO <>", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoGreaterThan(String value) {
            addCriterion("PAY_TRAN_NO >", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_TRAN_NO >=", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoLessThan(String value) {
            addCriterion("PAY_TRAN_NO <", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoLessThanOrEqualTo(String value) {
            addCriterion("PAY_TRAN_NO <=", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoLike(String value) {
            addCriterion("PAY_TRAN_NO like", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoNotLike(String value) {
            addCriterion("PAY_TRAN_NO not like", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoIn(List<String> values) {
            addCriterion("PAY_TRAN_NO in", values, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoNotIn(List<String> values) {
            addCriterion("PAY_TRAN_NO not in", values, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoBetween(String value1, String value2) {
            addCriterion("PAY_TRAN_NO between", value1, value2, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoNotBetween(String value1, String value2) {
            addCriterion("PAY_TRAN_NO not between", value1, value2, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andSellerMsgIsNull() {
            addCriterion("SELLER_MSG is null");
            return (Criteria) this;
        }

        public Criteria andSellerMsgIsNotNull() {
            addCriterion("SELLER_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andSellerMsgEqualTo(String value) {
            addCriterion("SELLER_MSG =", value, "sellerMsg");
            return (Criteria) this;
        }

        public Criteria andSellerMsgNotEqualTo(String value) {
            addCriterion("SELLER_MSG <>", value, "sellerMsg");
            return (Criteria) this;
        }

        public Criteria andSellerMsgGreaterThan(String value) {
            addCriterion("SELLER_MSG >", value, "sellerMsg");
            return (Criteria) this;
        }

        public Criteria andSellerMsgGreaterThanOrEqualTo(String value) {
            addCriterion("SELLER_MSG >=", value, "sellerMsg");
            return (Criteria) this;
        }

        public Criteria andSellerMsgLessThan(String value) {
            addCriterion("SELLER_MSG <", value, "sellerMsg");
            return (Criteria) this;
        }

        public Criteria andSellerMsgLessThanOrEqualTo(String value) {
            addCriterion("SELLER_MSG <=", value, "sellerMsg");
            return (Criteria) this;
        }

        public Criteria andSellerMsgLike(String value) {
            addCriterion("SELLER_MSG like", value, "sellerMsg");
            return (Criteria) this;
        }

        public Criteria andSellerMsgNotLike(String value) {
            addCriterion("SELLER_MSG not like", value, "sellerMsg");
            return (Criteria) this;
        }

        public Criteria andSellerMsgIn(List<String> values) {
            addCriterion("SELLER_MSG in", values, "sellerMsg");
            return (Criteria) this;
        }

        public Criteria andSellerMsgNotIn(List<String> values) {
            addCriterion("SELLER_MSG not in", values, "sellerMsg");
            return (Criteria) this;
        }

        public Criteria andSellerMsgBetween(String value1, String value2) {
            addCriterion("SELLER_MSG between", value1, value2, "sellerMsg");
            return (Criteria) this;
        }

        public Criteria andSellerMsgNotBetween(String value1, String value2) {
            addCriterion("SELLER_MSG not between", value1, value2, "sellerMsg");
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