package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsEvalStaffIdxCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsEvalStaffIdxCriteria() {
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

        public Criteria andEvalIdIsNull() {
            addCriterion("EVAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andEvalIdIsNotNull() {
            addCriterion("EVAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEvalIdEqualTo(Long value) {
            addCriterion("EVAL_ID =", value, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdNotEqualTo(Long value) {
            addCriterion("EVAL_ID <>", value, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdGreaterThan(Long value) {
            addCriterion("EVAL_ID >", value, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdGreaterThanOrEqualTo(Long value) {
            addCriterion("EVAL_ID >=", value, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdLessThan(Long value) {
            addCriterion("EVAL_ID <", value, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdLessThanOrEqualTo(Long value) {
            addCriterion("EVAL_ID <=", value, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdIn(List<Long> values) {
            addCriterion("EVAL_ID in", values, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdNotIn(List<Long> values) {
            addCriterion("EVAL_ID not in", values, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdBetween(Long value1, Long value2) {
            addCriterion("EVAL_ID between", value1, value2, "evalId");
            return (Criteria) this;
        }

        public Criteria andEvalIdNotBetween(Long value1, Long value2) {
            addCriterion("EVAL_ID not between", value1, value2, "evalId");
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

        public Criteria andEvaluationTimeIsNull() {
            addCriterion("EVALUATION_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationTimeIsNotNull() {
            addCriterion("EVALUATION_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationTimeEqualTo(Timestamp value) {
            addCriterion("EVALUATION_TIME =", value, "evaluationTime");
            return (Criteria) this;
        }

        public Criteria andEvaluationTimeNotEqualTo(Timestamp value) {
            addCriterion("EVALUATION_TIME <>", value, "evaluationTime");
            return (Criteria) this;
        }

        public Criteria andEvaluationTimeGreaterThan(Timestamp value) {
            addCriterion("EVALUATION_TIME >", value, "evaluationTime");
            return (Criteria) this;
        }

        public Criteria andEvaluationTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("EVALUATION_TIME >=", value, "evaluationTime");
            return (Criteria) this;
        }

        public Criteria andEvaluationTimeLessThan(Timestamp value) {
            addCriterion("EVALUATION_TIME <", value, "evaluationTime");
            return (Criteria) this;
        }

        public Criteria andEvaluationTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("EVALUATION_TIME <=", value, "evaluationTime");
            return (Criteria) this;
        }

        public Criteria andEvaluationTimeIn(List<Timestamp> values) {
            addCriterion("EVALUATION_TIME in", values, "evaluationTime");
            return (Criteria) this;
        }

        public Criteria andEvaluationTimeNotIn(List<Timestamp> values) {
            addCriterion("EVALUATION_TIME not in", values, "evaluationTime");
            return (Criteria) this;
        }

        public Criteria andEvaluationTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EVALUATION_TIME between", value1, value2, "evaluationTime");
            return (Criteria) this;
        }

        public Criteria andEvaluationTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EVALUATION_TIME not between", value1, value2, "evaluationTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeIsNull() {
            addCriterion("BUY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBuyTimeIsNotNull() {
            addCriterion("BUY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBuyTimeEqualTo(Timestamp value) {
            addCriterion("BUY_TIME =", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotEqualTo(Timestamp value) {
            addCriterion("BUY_TIME <>", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeGreaterThan(Timestamp value) {
            addCriterion("BUY_TIME >", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("BUY_TIME >=", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeLessThan(Timestamp value) {
            addCriterion("BUY_TIME <", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("BUY_TIME <=", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeIn(List<Timestamp> values) {
            addCriterion("BUY_TIME in", values, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotIn(List<Timestamp> values) {
            addCriterion("BUY_TIME not in", values, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("BUY_TIME between", value1, value2, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("BUY_TIME not between", value1, value2, "buyTime");
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

        public Criteria andScoreIsNull() {
            addCriterion("SCORE is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Short value) {
            addCriterion("SCORE =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Short value) {
            addCriterion("SCORE <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Short value) {
            addCriterion("SCORE >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Short value) {
            addCriterion("SCORE >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Short value) {
            addCriterion("SCORE <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Short value) {
            addCriterion("SCORE <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Short> values) {
            addCriterion("SCORE in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Short> values) {
            addCriterion("SCORE not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Short value1, Short value2) {
            addCriterion("SCORE between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Short value1, Short value2) {
            addCriterion("SCORE not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andCorrespondencyScoreIsNull() {
            addCriterion("CORRESPONDENCY_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andCorrespondencyScoreIsNotNull() {
            addCriterion("CORRESPONDENCY_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andCorrespondencyScoreEqualTo(Short value) {
            addCriterion("CORRESPONDENCY_SCORE =", value, "correspondencyScore");
            return (Criteria) this;
        }

        public Criteria andCorrespondencyScoreNotEqualTo(Short value) {
            addCriterion("CORRESPONDENCY_SCORE <>", value, "correspondencyScore");
            return (Criteria) this;
        }

        public Criteria andCorrespondencyScoreGreaterThan(Short value) {
            addCriterion("CORRESPONDENCY_SCORE >", value, "correspondencyScore");
            return (Criteria) this;
        }

        public Criteria andCorrespondencyScoreGreaterThanOrEqualTo(Short value) {
            addCriterion("CORRESPONDENCY_SCORE >=", value, "correspondencyScore");
            return (Criteria) this;
        }

        public Criteria andCorrespondencyScoreLessThan(Short value) {
            addCriterion("CORRESPONDENCY_SCORE <", value, "correspondencyScore");
            return (Criteria) this;
        }

        public Criteria andCorrespondencyScoreLessThanOrEqualTo(Short value) {
            addCriterion("CORRESPONDENCY_SCORE <=", value, "correspondencyScore");
            return (Criteria) this;
        }

        public Criteria andCorrespondencyScoreIn(List<Short> values) {
            addCriterion("CORRESPONDENCY_SCORE in", values, "correspondencyScore");
            return (Criteria) this;
        }

        public Criteria andCorrespondencyScoreNotIn(List<Short> values) {
            addCriterion("CORRESPONDENCY_SCORE not in", values, "correspondencyScore");
            return (Criteria) this;
        }

        public Criteria andCorrespondencyScoreBetween(Short value1, Short value2) {
            addCriterion("CORRESPONDENCY_SCORE between", value1, value2, "correspondencyScore");
            return (Criteria) this;
        }

        public Criteria andCorrespondencyScoreNotBetween(Short value1, Short value2) {
            addCriterion("CORRESPONDENCY_SCORE not between", value1, value2, "correspondencyScore");
            return (Criteria) this;
        }

        public Criteria andServiceattitudeScoreIsNull() {
            addCriterion("SERVICEATTITUDE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andServiceattitudeScoreIsNotNull() {
            addCriterion("SERVICEATTITUDE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andServiceattitudeScoreEqualTo(Short value) {
            addCriterion("SERVICEATTITUDE_SCORE =", value, "serviceattitudeScore");
            return (Criteria) this;
        }

        public Criteria andServiceattitudeScoreNotEqualTo(Short value) {
            addCriterion("SERVICEATTITUDE_SCORE <>", value, "serviceattitudeScore");
            return (Criteria) this;
        }

        public Criteria andServiceattitudeScoreGreaterThan(Short value) {
            addCriterion("SERVICEATTITUDE_SCORE >", value, "serviceattitudeScore");
            return (Criteria) this;
        }

        public Criteria andServiceattitudeScoreGreaterThanOrEqualTo(Short value) {
            addCriterion("SERVICEATTITUDE_SCORE >=", value, "serviceattitudeScore");
            return (Criteria) this;
        }

        public Criteria andServiceattitudeScoreLessThan(Short value) {
            addCriterion("SERVICEATTITUDE_SCORE <", value, "serviceattitudeScore");
            return (Criteria) this;
        }

        public Criteria andServiceattitudeScoreLessThanOrEqualTo(Short value) {
            addCriterion("SERVICEATTITUDE_SCORE <=", value, "serviceattitudeScore");
            return (Criteria) this;
        }

        public Criteria andServiceattitudeScoreIn(List<Short> values) {
            addCriterion("SERVICEATTITUDE_SCORE in", values, "serviceattitudeScore");
            return (Criteria) this;
        }

        public Criteria andServiceattitudeScoreNotIn(List<Short> values) {
            addCriterion("SERVICEATTITUDE_SCORE not in", values, "serviceattitudeScore");
            return (Criteria) this;
        }

        public Criteria andServiceattitudeScoreBetween(Short value1, Short value2) {
            addCriterion("SERVICEATTITUDE_SCORE between", value1, value2, "serviceattitudeScore");
            return (Criteria) this;
        }

        public Criteria andServiceattitudeScoreNotBetween(Short value1, Short value2) {
            addCriterion("SERVICEATTITUDE_SCORE not between", value1, value2, "serviceattitudeScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryspeedScoreIsNull() {
            addCriterion("DELIVERYSPEED_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryspeedScoreIsNotNull() {
            addCriterion("DELIVERYSPEED_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryspeedScoreEqualTo(Short value) {
            addCriterion("DELIVERYSPEED_SCORE =", value, "deliveryspeedScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryspeedScoreNotEqualTo(Short value) {
            addCriterion("DELIVERYSPEED_SCORE <>", value, "deliveryspeedScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryspeedScoreGreaterThan(Short value) {
            addCriterion("DELIVERYSPEED_SCORE >", value, "deliveryspeedScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryspeedScoreGreaterThanOrEqualTo(Short value) {
            addCriterion("DELIVERYSPEED_SCORE >=", value, "deliveryspeedScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryspeedScoreLessThan(Short value) {
            addCriterion("DELIVERYSPEED_SCORE <", value, "deliveryspeedScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryspeedScoreLessThanOrEqualTo(Short value) {
            addCriterion("DELIVERYSPEED_SCORE <=", value, "deliveryspeedScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryspeedScoreIn(List<Short> values) {
            addCriterion("DELIVERYSPEED_SCORE in", values, "deliveryspeedScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryspeedScoreNotIn(List<Short> values) {
            addCriterion("DELIVERYSPEED_SCORE not in", values, "deliveryspeedScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryspeedScoreBetween(Short value1, Short value2) {
            addCriterion("DELIVERYSPEED_SCORE between", value1, value2, "deliveryspeedScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryspeedScoreNotBetween(Short value1, Short value2) {
            addCriterion("DELIVERYSPEED_SCORE not between", value1, value2, "deliveryspeedScore");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("CONTENT =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("CONTENT <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("CONTENT >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("CONTENT <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("CONTENT <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("CONTENT like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("CONTENT not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("CONTENT in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("CONTENT not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("CONTENT between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("CONTENT not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousIsNull() {
            addCriterion("IS_ANONYMOUS is null");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousIsNotNull() {
            addCriterion("IS_ANONYMOUS is not null");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousEqualTo(String value) {
            addCriterion("IS_ANONYMOUS =", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousNotEqualTo(String value) {
            addCriterion("IS_ANONYMOUS <>", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousGreaterThan(String value) {
            addCriterion("IS_ANONYMOUS >", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ANONYMOUS >=", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousLessThan(String value) {
            addCriterion("IS_ANONYMOUS <", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousLessThanOrEqualTo(String value) {
            addCriterion("IS_ANONYMOUS <=", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousLike(String value) {
            addCriterion("IS_ANONYMOUS like", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousNotLike(String value) {
            addCriterion("IS_ANONYMOUS not like", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousIn(List<String> values) {
            addCriterion("IS_ANONYMOUS in", values, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousNotIn(List<String> values) {
            addCriterion("IS_ANONYMOUS not in", values, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousBetween(String value1, String value2) {
            addCriterion("IS_ANONYMOUS between", value1, value2, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousNotBetween(String value1, String value2) {
            addCriterion("IS_ANONYMOUS not between", value1, value2, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsReplyIsNull() {
            addCriterion("IS_REPLY is null");
            return (Criteria) this;
        }

        public Criteria andIsReplyIsNotNull() {
            addCriterion("IS_REPLY is not null");
            return (Criteria) this;
        }

        public Criteria andIsReplyEqualTo(Short value) {
            addCriterion("IS_REPLY =", value, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyNotEqualTo(Short value) {
            addCriterion("IS_REPLY <>", value, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyGreaterThan(Short value) {
            addCriterion("IS_REPLY >", value, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyGreaterThanOrEqualTo(Short value) {
            addCriterion("IS_REPLY >=", value, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyLessThan(Short value) {
            addCriterion("IS_REPLY <", value, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyLessThanOrEqualTo(Short value) {
            addCriterion("IS_REPLY <=", value, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyIn(List<Short> values) {
            addCriterion("IS_REPLY in", values, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyNotIn(List<Short> values) {
            addCriterion("IS_REPLY not in", values, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyBetween(Short value1, Short value2) {
            addCriterion("IS_REPLY between", value1, value2, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyNotBetween(Short value1, Short value2) {
            addCriterion("IS_REPLY not between", value1, value2, "isReply");
            return (Criteria) this;
        }

        public Criteria andLabelNamesIsNull() {
            addCriterion("LABEL_NAMES is null");
            return (Criteria) this;
        }

        public Criteria andLabelNamesIsNotNull() {
            addCriterion("LABEL_NAMES is not null");
            return (Criteria) this;
        }

        public Criteria andLabelNamesEqualTo(String value) {
            addCriterion("LABEL_NAMES =", value, "labelNames");
            return (Criteria) this;
        }

        public Criteria andLabelNamesNotEqualTo(String value) {
            addCriterion("LABEL_NAMES <>", value, "labelNames");
            return (Criteria) this;
        }

        public Criteria andLabelNamesGreaterThan(String value) {
            addCriterion("LABEL_NAMES >", value, "labelNames");
            return (Criteria) this;
        }

        public Criteria andLabelNamesGreaterThanOrEqualTo(String value) {
            addCriterion("LABEL_NAMES >=", value, "labelNames");
            return (Criteria) this;
        }

        public Criteria andLabelNamesLessThan(String value) {
            addCriterion("LABEL_NAMES <", value, "labelNames");
            return (Criteria) this;
        }

        public Criteria andLabelNamesLessThanOrEqualTo(String value) {
            addCriterion("LABEL_NAMES <=", value, "labelNames");
            return (Criteria) this;
        }

        public Criteria andLabelNamesLike(String value) {
            addCriterion("LABEL_NAMES like", value, "labelNames");
            return (Criteria) this;
        }

        public Criteria andLabelNamesNotLike(String value) {
            addCriterion("LABEL_NAMES not like", value, "labelNames");
            return (Criteria) this;
        }

        public Criteria andLabelNamesIn(List<String> values) {
            addCriterion("LABEL_NAMES in", values, "labelNames");
            return (Criteria) this;
        }

        public Criteria andLabelNamesNotIn(List<String> values) {
            addCriterion("LABEL_NAMES not in", values, "labelNames");
            return (Criteria) this;
        }

        public Criteria andLabelNamesBetween(String value1, String value2) {
            addCriterion("LABEL_NAMES between", value1, value2, "labelNames");
            return (Criteria) this;
        }

        public Criteria andLabelNamesNotBetween(String value1, String value2) {
            addCriterion("LABEL_NAMES not between", value1, value2, "labelNames");
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
