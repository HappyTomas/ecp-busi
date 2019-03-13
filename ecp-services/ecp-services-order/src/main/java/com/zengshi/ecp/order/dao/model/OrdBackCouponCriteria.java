package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrdBackCouponCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OrdBackCouponCriteria() {
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

        public Criteria andProcTypeIsNull() {
            addCriterion("PROC_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andProcTypeIsNotNull() {
            addCriterion("PROC_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andProcTypeEqualTo(String value) {
            addCriterion("PROC_TYPE =", value, "procType");
            return (Criteria) this;
        }

        public Criteria andProcTypeNotEqualTo(String value) {
            addCriterion("PROC_TYPE <>", value, "procType");
            return (Criteria) this;
        }

        public Criteria andProcTypeGreaterThan(String value) {
            addCriterion("PROC_TYPE >", value, "procType");
            return (Criteria) this;
        }

        public Criteria andProcTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PROC_TYPE >=", value, "procType");
            return (Criteria) this;
        }

        public Criteria andProcTypeLessThan(String value) {
            addCriterion("PROC_TYPE <", value, "procType");
            return (Criteria) this;
        }

        public Criteria andProcTypeLessThanOrEqualTo(String value) {
            addCriterion("PROC_TYPE <=", value, "procType");
            return (Criteria) this;
        }

        public Criteria andProcTypeLike(String value) {
            addCriterion("PROC_TYPE like", value, "procType");
            return (Criteria) this;
        }

        public Criteria andProcTypeNotLike(String value) {
            addCriterion("PROC_TYPE not like", value, "procType");
            return (Criteria) this;
        }

        public Criteria andProcTypeIn(List<String> values) {
            addCriterion("PROC_TYPE in", values, "procType");
            return (Criteria) this;
        }

        public Criteria andProcTypeNotIn(List<String> values) {
            addCriterion("PROC_TYPE not in", values, "procType");
            return (Criteria) this;
        }

        public Criteria andProcTypeBetween(String value1, String value2) {
            addCriterion("PROC_TYPE between", value1, value2, "procType");
            return (Criteria) this;
        }

        public Criteria andProcTypeNotBetween(String value1, String value2) {
            addCriterion("PROC_TYPE not between", value1, value2, "procType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdIsNull() {
            addCriterion("COUPON_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdIsNotNull() {
            addCriterion("COUPON_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdEqualTo(Long value) {
            addCriterion("COUPON_TYPE_ID =", value, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdNotEqualTo(Long value) {
            addCriterion("COUPON_TYPE_ID <>", value, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdGreaterThan(Long value) {
            addCriterion("COUPON_TYPE_ID >", value, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("COUPON_TYPE_ID >=", value, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdLessThan(Long value) {
            addCriterion("COUPON_TYPE_ID <", value, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("COUPON_TYPE_ID <=", value, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdIn(List<Long> values) {
            addCriterion("COUPON_TYPE_ID in", values, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdNotIn(List<Long> values) {
            addCriterion("COUPON_TYPE_ID not in", values, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdBetween(Long value1, Long value2) {
            addCriterion("COUPON_TYPE_ID between", value1, value2, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("COUPON_TYPE_ID not between", value1, value2, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameIsNull() {
            addCriterion("COUPON_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameIsNotNull() {
            addCriterion("COUPON_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameEqualTo(String value) {
            addCriterion("COUPON_TYPE_NAME =", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameNotEqualTo(String value) {
            addCriterion("COUPON_TYPE_NAME <>", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameGreaterThan(String value) {
            addCriterion("COUPON_TYPE_NAME >", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_TYPE_NAME >=", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameLessThan(String value) {
            addCriterion("COUPON_TYPE_NAME <", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameLessThanOrEqualTo(String value) {
            addCriterion("COUPON_TYPE_NAME <=", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameLike(String value) {
            addCriterion("COUPON_TYPE_NAME like", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameNotLike(String value) {
            addCriterion("COUPON_TYPE_NAME not like", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameIn(List<String> values) {
            addCriterion("COUPON_TYPE_NAME in", values, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameNotIn(List<String> values) {
            addCriterion("COUPON_TYPE_NAME not in", values, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameBetween(String value1, String value2) {
            addCriterion("COUPON_TYPE_NAME between", value1, value2, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameNotBetween(String value1, String value2) {
            addCriterion("COUPON_TYPE_NAME not between", value1, value2, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponCntIsNull() {
            addCriterion("COUPON_CNT is null");
            return (Criteria) this;
        }

        public Criteria andCouponCntIsNotNull() {
            addCriterion("COUPON_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andCouponCntEqualTo(Long value) {
            addCriterion("COUPON_CNT =", value, "couponCnt");
            return (Criteria) this;
        }

        public Criteria andCouponCntNotEqualTo(Long value) {
            addCriterion("COUPON_CNT <>", value, "couponCnt");
            return (Criteria) this;
        }

        public Criteria andCouponCntGreaterThan(Long value) {
            addCriterion("COUPON_CNT >", value, "couponCnt");
            return (Criteria) this;
        }

        public Criteria andCouponCntGreaterThanOrEqualTo(Long value) {
            addCriterion("COUPON_CNT >=", value, "couponCnt");
            return (Criteria) this;
        }

        public Criteria andCouponCntLessThan(Long value) {
            addCriterion("COUPON_CNT <", value, "couponCnt");
            return (Criteria) this;
        }

        public Criteria andCouponCntLessThanOrEqualTo(Long value) {
            addCriterion("COUPON_CNT <=", value, "couponCnt");
            return (Criteria) this;
        }

        public Criteria andCouponCntIn(List<Long> values) {
            addCriterion("COUPON_CNT in", values, "couponCnt");
            return (Criteria) this;
        }

        public Criteria andCouponCntNotIn(List<Long> values) {
            addCriterion("COUPON_CNT not in", values, "couponCnt");
            return (Criteria) this;
        }

        public Criteria andCouponCntBetween(Long value1, Long value2) {
            addCriterion("COUPON_CNT between", value1, value2, "couponCnt");
            return (Criteria) this;
        }

        public Criteria andCouponCntNotBetween(Long value1, Long value2) {
            addCriterion("COUPON_CNT not between", value1, value2, "couponCnt");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIsNull() {
            addCriterion("COUPON_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIsNotNull() {
            addCriterion("COUPON_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCouponAmountEqualTo(Long value) {
            addCriterion("COUPON_AMOUNT =", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotEqualTo(Long value) {
            addCriterion("COUPON_AMOUNT <>", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountGreaterThan(Long value) {
            addCriterion("COUPON_AMOUNT >", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("COUPON_AMOUNT >=", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountLessThan(Long value) {
            addCriterion("COUPON_AMOUNT <", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountLessThanOrEqualTo(Long value) {
            addCriterion("COUPON_AMOUNT <=", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIn(List<Long> values) {
            addCriterion("COUPON_AMOUNT in", values, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotIn(List<Long> values) {
            addCriterion("COUPON_AMOUNT not in", values, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountBetween(Long value1, Long value2) {
            addCriterion("COUPON_AMOUNT between", value1, value2, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotBetween(Long value1, Long value2) {
            addCriterion("COUPON_AMOUNT not between", value1, value2, "couponAmount");
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