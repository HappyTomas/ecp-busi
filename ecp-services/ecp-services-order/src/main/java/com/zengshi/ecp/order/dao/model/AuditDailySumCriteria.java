package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AuditDailySumCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public AuditDailySumCriteria() {
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

        public Criteria andCheckDateIsNull() {
            addCriterion("CHECK_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNotNull() {
            addCriterion("CHECK_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCheckDateEqualTo(Timestamp value) {
            addCriterion("CHECK_DATE =", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotEqualTo(Timestamp value) {
            addCriterion("CHECK_DATE <>", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThan(Timestamp value) {
            addCriterion("CHECK_DATE >", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CHECK_DATE >=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThan(Timestamp value) {
            addCriterion("CHECK_DATE <", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("CHECK_DATE <=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateIn(List<Timestamp> values) {
            addCriterion("CHECK_DATE in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotIn(List<Timestamp> values) {
            addCriterion("CHECK_DATE not in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CHECK_DATE between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CHECK_DATE not between", value1, value2, "checkDate");
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

        public Criteria andTotalNumIsNull() {
            addCriterion("TOTAL_NUM is null");
            return (Criteria) this;
        }

        public Criteria andTotalNumIsNotNull() {
            addCriterion("TOTAL_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andTotalNumEqualTo(Long value) {
            addCriterion("TOTAL_NUM =", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotEqualTo(Long value) {
            addCriterion("TOTAL_NUM <>", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumGreaterThan(Long value) {
            addCriterion("TOTAL_NUM >", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumGreaterThanOrEqualTo(Long value) {
            addCriterion("TOTAL_NUM >=", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumLessThan(Long value) {
            addCriterion("TOTAL_NUM <", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumLessThanOrEqualTo(Long value) {
            addCriterion("TOTAL_NUM <=", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumIn(List<Long> values) {
            addCriterion("TOTAL_NUM in", values, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotIn(List<Long> values) {
            addCriterion("TOTAL_NUM not in", values, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumBetween(Long value1, Long value2) {
            addCriterion("TOTAL_NUM between", value1, value2, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotBetween(Long value1, Long value2) {
            addCriterion("TOTAL_NUM not between", value1, value2, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalTransAmountIsNull() {
            addCriterion("TOTAL_TRANS_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andTotalTransAmountIsNotNull() {
            addCriterion("TOTAL_TRANS_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTotalTransAmountEqualTo(Long value) {
            addCriterion("TOTAL_TRANS_AMOUNT =", value, "totalTransAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTransAmountNotEqualTo(Long value) {
            addCriterion("TOTAL_TRANS_AMOUNT <>", value, "totalTransAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTransAmountGreaterThan(Long value) {
            addCriterion("TOTAL_TRANS_AMOUNT >", value, "totalTransAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTransAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("TOTAL_TRANS_AMOUNT >=", value, "totalTransAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTransAmountLessThan(Long value) {
            addCriterion("TOTAL_TRANS_AMOUNT <", value, "totalTransAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTransAmountLessThanOrEqualTo(Long value) {
            addCriterion("TOTAL_TRANS_AMOUNT <=", value, "totalTransAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTransAmountIn(List<Long> values) {
            addCriterion("TOTAL_TRANS_AMOUNT in", values, "totalTransAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTransAmountNotIn(List<Long> values) {
            addCriterion("TOTAL_TRANS_AMOUNT not in", values, "totalTransAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTransAmountBetween(Long value1, Long value2) {
            addCriterion("TOTAL_TRANS_AMOUNT between", value1, value2, "totalTransAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTransAmountNotBetween(Long value1, Long value2) {
            addCriterion("TOTAL_TRANS_AMOUNT not between", value1, value2, "totalTransAmount");
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

        public Criteria andFileNameIsNull() {
            addCriterion("FILE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("FILE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("FILE_NAME =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("FILE_NAME <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("FILE_NAME >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_NAME >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("FILE_NAME <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("FILE_NAME <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("FILE_NAME like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("FILE_NAME not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("FILE_NAME in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("FILE_NAME not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("FILE_NAME between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("FILE_NAME not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andRefundTotalNumIsNull() {
            addCriterion("REFUND_TOTAL_NUM is null");
            return (Criteria) this;
        }

        public Criteria andRefundTotalNumIsNotNull() {
            addCriterion("REFUND_TOTAL_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andRefundTotalNumEqualTo(Long value) {
            addCriterion("REFUND_TOTAL_NUM =", value, "refundTotalNum");
            return (Criteria) this;
        }

        public Criteria andRefundTotalNumNotEqualTo(Long value) {
            addCriterion("REFUND_TOTAL_NUM <>", value, "refundTotalNum");
            return (Criteria) this;
        }

        public Criteria andRefundTotalNumGreaterThan(Long value) {
            addCriterion("REFUND_TOTAL_NUM >", value, "refundTotalNum");
            return (Criteria) this;
        }

        public Criteria andRefundTotalNumGreaterThanOrEqualTo(Long value) {
            addCriterion("REFUND_TOTAL_NUM >=", value, "refundTotalNum");
            return (Criteria) this;
        }

        public Criteria andRefundTotalNumLessThan(Long value) {
            addCriterion("REFUND_TOTAL_NUM <", value, "refundTotalNum");
            return (Criteria) this;
        }

        public Criteria andRefundTotalNumLessThanOrEqualTo(Long value) {
            addCriterion("REFUND_TOTAL_NUM <=", value, "refundTotalNum");
            return (Criteria) this;
        }

        public Criteria andRefundTotalNumIn(List<Long> values) {
            addCriterion("REFUND_TOTAL_NUM in", values, "refundTotalNum");
            return (Criteria) this;
        }

        public Criteria andRefundTotalNumNotIn(List<Long> values) {
            addCriterion("REFUND_TOTAL_NUM not in", values, "refundTotalNum");
            return (Criteria) this;
        }

        public Criteria andRefundTotalNumBetween(Long value1, Long value2) {
            addCriterion("REFUND_TOTAL_NUM between", value1, value2, "refundTotalNum");
            return (Criteria) this;
        }

        public Criteria andRefundTotalNumNotBetween(Long value1, Long value2) {
            addCriterion("REFUND_TOTAL_NUM not between", value1, value2, "refundTotalNum");
            return (Criteria) this;
        }

        public Criteria andRefundTotalTransAmountIsNull() {
            addCriterion("REFUND_TOTAL_TRANS_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andRefundTotalTransAmountIsNotNull() {
            addCriterion("REFUND_TOTAL_TRANS_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRefundTotalTransAmountEqualTo(Long value) {
            addCriterion("REFUND_TOTAL_TRANS_AMOUNT =", value, "refundTotalTransAmount");
            return (Criteria) this;
        }

        public Criteria andRefundTotalTransAmountNotEqualTo(Long value) {
            addCriterion("REFUND_TOTAL_TRANS_AMOUNT <>", value, "refundTotalTransAmount");
            return (Criteria) this;
        }

        public Criteria andRefundTotalTransAmountGreaterThan(Long value) {
            addCriterion("REFUND_TOTAL_TRANS_AMOUNT >", value, "refundTotalTransAmount");
            return (Criteria) this;
        }

        public Criteria andRefundTotalTransAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("REFUND_TOTAL_TRANS_AMOUNT >=", value, "refundTotalTransAmount");
            return (Criteria) this;
        }

        public Criteria andRefundTotalTransAmountLessThan(Long value) {
            addCriterion("REFUND_TOTAL_TRANS_AMOUNT <", value, "refundTotalTransAmount");
            return (Criteria) this;
        }

        public Criteria andRefundTotalTransAmountLessThanOrEqualTo(Long value) {
            addCriterion("REFUND_TOTAL_TRANS_AMOUNT <=", value, "refundTotalTransAmount");
            return (Criteria) this;
        }

        public Criteria andRefundTotalTransAmountIn(List<Long> values) {
            addCriterion("REFUND_TOTAL_TRANS_AMOUNT in", values, "refundTotalTransAmount");
            return (Criteria) this;
        }

        public Criteria andRefundTotalTransAmountNotIn(List<Long> values) {
            addCriterion("REFUND_TOTAL_TRANS_AMOUNT not in", values, "refundTotalTransAmount");
            return (Criteria) this;
        }

        public Criteria andRefundTotalTransAmountBetween(Long value1, Long value2) {
            addCriterion("REFUND_TOTAL_TRANS_AMOUNT between", value1, value2, "refundTotalTransAmount");
            return (Criteria) this;
        }

        public Criteria andRefundTotalTransAmountNotBetween(Long value1, Long value2) {
            addCriterion("REFUND_TOTAL_TRANS_AMOUNT not between", value1, value2, "refundTotalTransAmount");
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