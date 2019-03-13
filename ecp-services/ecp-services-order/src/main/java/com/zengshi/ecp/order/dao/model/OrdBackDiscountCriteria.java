package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrdBackDiscountCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OrdBackDiscountCriteria() {
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

        public Criteria andDiscountTypeIsNull() {
            addCriterion("DISCOUNT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIsNotNull() {
            addCriterion("DISCOUNT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeEqualTo(String value) {
            addCriterion("DISCOUNT_TYPE =", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotEqualTo(String value) {
            addCriterion("DISCOUNT_TYPE <>", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeGreaterThan(String value) {
            addCriterion("DISCOUNT_TYPE >", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DISCOUNT_TYPE >=", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLessThan(String value) {
            addCriterion("DISCOUNT_TYPE <", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLessThanOrEqualTo(String value) {
            addCriterion("DISCOUNT_TYPE <=", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLike(String value) {
            addCriterion("DISCOUNT_TYPE like", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotLike(String value) {
            addCriterion("DISCOUNT_TYPE not like", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIn(List<String> values) {
            addCriterion("DISCOUNT_TYPE in", values, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotIn(List<String> values) {
            addCriterion("DISCOUNT_TYPE not in", values, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeBetween(String value1, String value2) {
            addCriterion("DISCOUNT_TYPE between", value1, value2, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotBetween(String value1, String value2) {
            addCriterion("DISCOUNT_TYPE not between", value1, value2, "discountType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeIsNull() {
            addCriterion("ACCT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAcctTypeIsNotNull() {
            addCriterion("ACCT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAcctTypeEqualTo(String value) {
            addCriterion("ACCT_TYPE =", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotEqualTo(String value) {
            addCriterion("ACCT_TYPE <>", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeGreaterThan(String value) {
            addCriterion("ACCT_TYPE >", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ACCT_TYPE >=", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLessThan(String value) {
            addCriterion("ACCT_TYPE <", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLessThanOrEqualTo(String value) {
            addCriterion("ACCT_TYPE <=", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLike(String value) {
            addCriterion("ACCT_TYPE like", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotLike(String value) {
            addCriterion("ACCT_TYPE not like", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeIn(List<String> values) {
            addCriterion("ACCT_TYPE in", values, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotIn(List<String> values) {
            addCriterion("ACCT_TYPE not in", values, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeBetween(String value1, String value2) {
            addCriterion("ACCT_TYPE between", value1, value2, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotBetween(String value1, String value2) {
            addCriterion("ACCT_TYPE not between", value1, value2, "acctType");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Long value) {
            addCriterion("AMOUNT =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Long value) {
            addCriterion("AMOUNT <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Long value) {
            addCriterion("AMOUNT >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("AMOUNT >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Long value) {
            addCriterion("AMOUNT <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Long value) {
            addCriterion("AMOUNT <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Long> values) {
            addCriterion("AMOUNT in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Long> values) {
            addCriterion("AMOUNT not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Long value1, Long value2) {
            addCriterion("AMOUNT between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Long value1, Long value2) {
            addCriterion("AMOUNT not between", value1, value2, "amount");
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