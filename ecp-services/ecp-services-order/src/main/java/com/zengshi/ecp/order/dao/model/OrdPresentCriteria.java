package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrdPresentCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OrdPresentCriteria() {
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

        public Criteria andSubOrderIsNull() {
            addCriterion("SUB_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIsNotNull() {
            addCriterion("SUB_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderEqualTo(String value) {
            addCriterion("SUB_ORDER =", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderNotEqualTo(String value) {
            addCriterion("SUB_ORDER <>", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderGreaterThan(String value) {
            addCriterion("SUB_ORDER >", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderGreaterThanOrEqualTo(String value) {
            addCriterion("SUB_ORDER >=", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderLessThan(String value) {
            addCriterion("SUB_ORDER <", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderLessThanOrEqualTo(String value) {
            addCriterion("SUB_ORDER <=", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderLike(String value) {
            addCriterion("SUB_ORDER like", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderNotLike(String value) {
            addCriterion("SUB_ORDER not like", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderIn(List<String> values) {
            addCriterion("SUB_ORDER in", values, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderNotIn(List<String> values) {
            addCriterion("SUB_ORDER not in", values, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderBetween(String value1, String value2) {
            addCriterion("SUB_ORDER between", value1, value2, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderNotBetween(String value1, String value2) {
            addCriterion("SUB_ORDER not between", value1, value2, "subOrder");
            return (Criteria) this;
        }

        public Criteria andPromIdIsNull() {
            addCriterion("PROM_ID is null");
            return (Criteria) this;
        }

        public Criteria andPromIdIsNotNull() {
            addCriterion("PROM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPromIdEqualTo(Long value) {
            addCriterion("PROM_ID =", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotEqualTo(Long value) {
            addCriterion("PROM_ID <>", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdGreaterThan(Long value) {
            addCriterion("PROM_ID >", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROM_ID >=", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdLessThan(Long value) {
            addCriterion("PROM_ID <", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdLessThanOrEqualTo(Long value) {
            addCriterion("PROM_ID <=", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdIn(List<Long> values) {
            addCriterion("PROM_ID in", values, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotIn(List<Long> values) {
            addCriterion("PROM_ID not in", values, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdBetween(Long value1, Long value2) {
            addCriterion("PROM_ID between", value1, value2, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotBetween(Long value1, Long value2) {
            addCriterion("PROM_ID not between", value1, value2, "promId");
            return (Criteria) this;
        }

        public Criteria andCreditsIsNull() {
            addCriterion("CREDITS is null");
            return (Criteria) this;
        }

        public Criteria andCreditsIsNotNull() {
            addCriterion("CREDITS is not null");
            return (Criteria) this;
        }

        public Criteria andCreditsEqualTo(Long value) {
            addCriterion("CREDITS =", value, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsNotEqualTo(Long value) {
            addCriterion("CREDITS <>", value, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsGreaterThan(Long value) {
            addCriterion("CREDITS >", value, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsGreaterThanOrEqualTo(Long value) {
            addCriterion("CREDITS >=", value, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsLessThan(Long value) {
            addCriterion("CREDITS <", value, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsLessThanOrEqualTo(Long value) {
            addCriterion("CREDITS <=", value, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsIn(List<Long> values) {
            addCriterion("CREDITS in", values, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsNotIn(List<Long> values) {
            addCriterion("CREDITS not in", values, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsBetween(Long value1, Long value2) {
            addCriterion("CREDITS between", value1, value2, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsNotBetween(Long value1, Long value2) {
            addCriterion("CREDITS not between", value1, value2, "credits");
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

        public Criteria andCouponTypeIdEqualTo(String value) {
            addCriterion("COUPON_TYPE_ID =", value, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdNotEqualTo(String value) {
            addCriterion("COUPON_TYPE_ID <>", value, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdGreaterThan(String value) {
            addCriterion("COUPON_TYPE_ID >", value, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_TYPE_ID >=", value, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdLessThan(String value) {
            addCriterion("COUPON_TYPE_ID <", value, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdLessThanOrEqualTo(String value) {
            addCriterion("COUPON_TYPE_ID <=", value, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdLike(String value) {
            addCriterion("COUPON_TYPE_ID like", value, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdNotLike(String value) {
            addCriterion("COUPON_TYPE_ID not like", value, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdIn(List<String> values) {
            addCriterion("COUPON_TYPE_ID in", values, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdNotIn(List<String> values) {
            addCriterion("COUPON_TYPE_ID not in", values, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdBetween(String value1, String value2) {
            addCriterion("COUPON_TYPE_ID between", value1, value2, "couponTypeId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIdNotBetween(String value1, String value2) {
            addCriterion("COUPON_TYPE_ID not between", value1, value2, "couponTypeId");
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

        public Criteria andCreditTimesIsNull() {
            addCriterion("CREDIT_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andCreditTimesIsNotNull() {
            addCriterion("CREDIT_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andCreditTimesEqualTo(BigDecimal value) {
            addCriterion("CREDIT_TIMES =", value, "creditTimes");
            return (Criteria) this;
        }

        public Criteria andCreditTimesNotEqualTo(BigDecimal value) {
            addCriterion("CREDIT_TIMES <>", value, "creditTimes");
            return (Criteria) this;
        }

        public Criteria andCreditTimesGreaterThan(BigDecimal value) {
            addCriterion("CREDIT_TIMES >", value, "creditTimes");
            return (Criteria) this;
        }

        public Criteria andCreditTimesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CREDIT_TIMES >=", value, "creditTimes");
            return (Criteria) this;
        }

        public Criteria andCreditTimesLessThan(BigDecimal value) {
            addCriterion("CREDIT_TIMES <", value, "creditTimes");
            return (Criteria) this;
        }

        public Criteria andCreditTimesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CREDIT_TIMES <=", value, "creditTimes");
            return (Criteria) this;
        }

        public Criteria andCreditTimesIn(List<BigDecimal> values) {
            addCriterion("CREDIT_TIMES in", values, "creditTimes");
            return (Criteria) this;
        }

        public Criteria andCreditTimesNotIn(List<BigDecimal> values) {
            addCriterion("CREDIT_TIMES not in", values, "creditTimes");
            return (Criteria) this;
        }

        public Criteria andCreditTimesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CREDIT_TIMES between", value1, value2, "creditTimes");
            return (Criteria) this;
        }

        public Criteria andCreditTimesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CREDIT_TIMES not between", value1, value2, "creditTimes");
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