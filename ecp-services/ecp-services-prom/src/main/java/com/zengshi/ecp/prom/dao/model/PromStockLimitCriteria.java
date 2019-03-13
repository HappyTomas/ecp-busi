package com.zengshi.ecp.prom.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PromStockLimitCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PromStockLimitCriteria() {
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

        public Criteria andPromCntIsNull() {
            addCriterion("PROM_CNT is null");
            return (Criteria) this;
        }

        public Criteria andPromCntIsNotNull() {
            addCriterion("PROM_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andPromCntEqualTo(Long value) {
            addCriterion("PROM_CNT =", value, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntNotEqualTo(Long value) {
            addCriterion("PROM_CNT <>", value, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntGreaterThan(Long value) {
            addCriterion("PROM_CNT >", value, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntGreaterThanOrEqualTo(Long value) {
            addCriterion("PROM_CNT >=", value, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntLessThan(Long value) {
            addCriterion("PROM_CNT <", value, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntLessThanOrEqualTo(Long value) {
            addCriterion("PROM_CNT <=", value, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntIn(List<Long> values) {
            addCriterion("PROM_CNT in", values, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntNotIn(List<Long> values) {
            addCriterion("PROM_CNT not in", values, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntBetween(Long value1, Long value2) {
            addCriterion("PROM_CNT between", value1, value2, "promCnt");
            return (Criteria) this;
        }

        public Criteria andPromCntNotBetween(Long value1, Long value2) {
            addCriterion("PROM_CNT not between", value1, value2, "promCnt");
            return (Criteria) this;
        }

        public Criteria andBuyCntIsNull() {
            addCriterion("BUY_CNT is null");
            return (Criteria) this;
        }

        public Criteria andBuyCntIsNotNull() {
            addCriterion("BUY_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andBuyCntEqualTo(Long value) {
            addCriterion("BUY_CNT =", value, "buyCnt");
            return (Criteria) this;
        }

        public Criteria andBuyCntNotEqualTo(Long value) {
            addCriterion("BUY_CNT <>", value, "buyCnt");
            return (Criteria) this;
        }

        public Criteria andBuyCntGreaterThan(Long value) {
            addCriterion("BUY_CNT >", value, "buyCnt");
            return (Criteria) this;
        }

        public Criteria andBuyCntGreaterThanOrEqualTo(Long value) {
            addCriterion("BUY_CNT >=", value, "buyCnt");
            return (Criteria) this;
        }

        public Criteria andBuyCntLessThan(Long value) {
            addCriterion("BUY_CNT <", value, "buyCnt");
            return (Criteria) this;
        }

        public Criteria andBuyCntLessThanOrEqualTo(Long value) {
            addCriterion("BUY_CNT <=", value, "buyCnt");
            return (Criteria) this;
        }

        public Criteria andBuyCntIn(List<Long> values) {
            addCriterion("BUY_CNT in", values, "buyCnt");
            return (Criteria) this;
        }

        public Criteria andBuyCntNotIn(List<Long> values) {
            addCriterion("BUY_CNT not in", values, "buyCnt");
            return (Criteria) this;
        }

        public Criteria andBuyCntBetween(Long value1, Long value2) {
            addCriterion("BUY_CNT between", value1, value2, "buyCnt");
            return (Criteria) this;
        }

        public Criteria andBuyCntNotBetween(Long value1, Long value2) {
            addCriterion("BUY_CNT not between", value1, value2, "buyCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntIsNull() {
            addCriterion("REMAIND_CNT is null");
            return (Criteria) this;
        }

        public Criteria andRemaindCntIsNotNull() {
            addCriterion("REMAIND_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andRemaindCntEqualTo(Long value) {
            addCriterion("REMAIND_CNT =", value, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntNotEqualTo(Long value) {
            addCriterion("REMAIND_CNT <>", value, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntGreaterThan(Long value) {
            addCriterion("REMAIND_CNT >", value, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntGreaterThanOrEqualTo(Long value) {
            addCriterion("REMAIND_CNT >=", value, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntLessThan(Long value) {
            addCriterion("REMAIND_CNT <", value, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntLessThanOrEqualTo(Long value) {
            addCriterion("REMAIND_CNT <=", value, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntIn(List<Long> values) {
            addCriterion("REMAIND_CNT in", values, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntNotIn(List<Long> values) {
            addCriterion("REMAIND_CNT not in", values, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntBetween(Long value1, Long value2) {
            addCriterion("REMAIND_CNT between", value1, value2, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntNotBetween(Long value1, Long value2) {
            addCriterion("REMAIND_CNT not between", value1, value2, "remaindCnt");
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