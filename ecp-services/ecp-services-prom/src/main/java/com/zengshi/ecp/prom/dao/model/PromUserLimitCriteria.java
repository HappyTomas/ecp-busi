package com.zengshi.ecp.prom.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PromUserLimitCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PromUserLimitCriteria() {
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

        public Criteria andLimitTypeIsNull() {
            addCriterion("LIMIT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andLimitTypeIsNotNull() {
            addCriterion("LIMIT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andLimitTypeEqualTo(String value) {
            addCriterion("LIMIT_TYPE =", value, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeNotEqualTo(String value) {
            addCriterion("LIMIT_TYPE <>", value, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeGreaterThan(String value) {
            addCriterion("LIMIT_TYPE >", value, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeGreaterThanOrEqualTo(String value) {
            addCriterion("LIMIT_TYPE >=", value, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeLessThan(String value) {
            addCriterion("LIMIT_TYPE <", value, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeLessThanOrEqualTo(String value) {
            addCriterion("LIMIT_TYPE <=", value, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeLike(String value) {
            addCriterion("LIMIT_TYPE like", value, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeNotLike(String value) {
            addCriterion("LIMIT_TYPE not like", value, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeIn(List<String> values) {
            addCriterion("LIMIT_TYPE in", values, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeNotIn(List<String> values) {
            addCriterion("LIMIT_TYPE not in", values, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeBetween(String value1, String value2) {
            addCriterion("LIMIT_TYPE between", value1, value2, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeNotBetween(String value1, String value2) {
            addCriterion("LIMIT_TYPE not between", value1, value2, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeValueIsNull() {
            addCriterion("LIMIT_TYPE_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andLimitTypeValueIsNotNull() {
            addCriterion("LIMIT_TYPE_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andLimitTypeValueEqualTo(String value) {
            addCriterion("LIMIT_TYPE_VALUE =", value, "limitTypeValue");
            return (Criteria) this;
        }

        public Criteria andLimitTypeValueNotEqualTo(String value) {
            addCriterion("LIMIT_TYPE_VALUE <>", value, "limitTypeValue");
            return (Criteria) this;
        }

        public Criteria andLimitTypeValueGreaterThan(String value) {
            addCriterion("LIMIT_TYPE_VALUE >", value, "limitTypeValue");
            return (Criteria) this;
        }

        public Criteria andLimitTypeValueGreaterThanOrEqualTo(String value) {
            addCriterion("LIMIT_TYPE_VALUE >=", value, "limitTypeValue");
            return (Criteria) this;
        }

        public Criteria andLimitTypeValueLessThan(String value) {
            addCriterion("LIMIT_TYPE_VALUE <", value, "limitTypeValue");
            return (Criteria) this;
        }

        public Criteria andLimitTypeValueLessThanOrEqualTo(String value) {
            addCriterion("LIMIT_TYPE_VALUE <=", value, "limitTypeValue");
            return (Criteria) this;
        }

        public Criteria andLimitTypeValueLike(String value) {
            addCriterion("LIMIT_TYPE_VALUE like", value, "limitTypeValue");
            return (Criteria) this;
        }

        public Criteria andLimitTypeValueNotLike(String value) {
            addCriterion("LIMIT_TYPE_VALUE not like", value, "limitTypeValue");
            return (Criteria) this;
        }

        public Criteria andLimitTypeValueIn(List<String> values) {
            addCriterion("LIMIT_TYPE_VALUE in", values, "limitTypeValue");
            return (Criteria) this;
        }

        public Criteria andLimitTypeValueNotIn(List<String> values) {
            addCriterion("LIMIT_TYPE_VALUE not in", values, "limitTypeValue");
            return (Criteria) this;
        }

        public Criteria andLimitTypeValueBetween(String value1, String value2) {
            addCriterion("LIMIT_TYPE_VALUE between", value1, value2, "limitTypeValue");
            return (Criteria) this;
        }

        public Criteria andLimitTypeValueNotBetween(String value1, String value2) {
            addCriterion("LIMIT_TYPE_VALUE not between", value1, value2, "limitTypeValue");
            return (Criteria) this;
        }

        public Criteria andOptValueIsNull() {
            addCriterion("OPT_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andOptValueIsNotNull() {
            addCriterion("OPT_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andOptValueEqualTo(String value) {
            addCriterion("OPT_VALUE =", value, "optValue");
            return (Criteria) this;
        }

        public Criteria andOptValueNotEqualTo(String value) {
            addCriterion("OPT_VALUE <>", value, "optValue");
            return (Criteria) this;
        }

        public Criteria andOptValueGreaterThan(String value) {
            addCriterion("OPT_VALUE >", value, "optValue");
            return (Criteria) this;
        }

        public Criteria andOptValueGreaterThanOrEqualTo(String value) {
            addCriterion("OPT_VALUE >=", value, "optValue");
            return (Criteria) this;
        }

        public Criteria andOptValueLessThan(String value) {
            addCriterion("OPT_VALUE <", value, "optValue");
            return (Criteria) this;
        }

        public Criteria andOptValueLessThanOrEqualTo(String value) {
            addCriterion("OPT_VALUE <=", value, "optValue");
            return (Criteria) this;
        }

        public Criteria andOptValueLike(String value) {
            addCriterion("OPT_VALUE like", value, "optValue");
            return (Criteria) this;
        }

        public Criteria andOptValueNotLike(String value) {
            addCriterion("OPT_VALUE not like", value, "optValue");
            return (Criteria) this;
        }

        public Criteria andOptValueIn(List<String> values) {
            addCriterion("OPT_VALUE in", values, "optValue");
            return (Criteria) this;
        }

        public Criteria andOptValueNotIn(List<String> values) {
            addCriterion("OPT_VALUE not in", values, "optValue");
            return (Criteria) this;
        }

        public Criteria andOptValueBetween(String value1, String value2) {
            addCriterion("OPT_VALUE between", value1, value2, "optValue");
            return (Criteria) this;
        }

        public Criteria andOptValueNotBetween(String value1, String value2) {
            addCriterion("OPT_VALUE not between", value1, value2, "optValue");
            return (Criteria) this;
        }

        public Criteria andPromCntLimitIsNull() {
            addCriterion("PROM_CNT_LIMIT is null");
            return (Criteria) this;
        }

        public Criteria andPromCntLimitIsNotNull() {
            addCriterion("PROM_CNT_LIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andPromCntLimitEqualTo(Long value) {
            addCriterion("PROM_CNT_LIMIT =", value, "promCntLimit");
            return (Criteria) this;
        }

        public Criteria andPromCntLimitNotEqualTo(Long value) {
            addCriterion("PROM_CNT_LIMIT <>", value, "promCntLimit");
            return (Criteria) this;
        }

        public Criteria andPromCntLimitGreaterThan(Long value) {
            addCriterion("PROM_CNT_LIMIT >", value, "promCntLimit");
            return (Criteria) this;
        }

        public Criteria andPromCntLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("PROM_CNT_LIMIT >=", value, "promCntLimit");
            return (Criteria) this;
        }

        public Criteria andPromCntLimitLessThan(Long value) {
            addCriterion("PROM_CNT_LIMIT <", value, "promCntLimit");
            return (Criteria) this;
        }

        public Criteria andPromCntLimitLessThanOrEqualTo(Long value) {
            addCriterion("PROM_CNT_LIMIT <=", value, "promCntLimit");
            return (Criteria) this;
        }

        public Criteria andPromCntLimitIn(List<Long> values) {
            addCriterion("PROM_CNT_LIMIT in", values, "promCntLimit");
            return (Criteria) this;
        }

        public Criteria andPromCntLimitNotIn(List<Long> values) {
            addCriterion("PROM_CNT_LIMIT not in", values, "promCntLimit");
            return (Criteria) this;
        }

        public Criteria andPromCntLimitBetween(Long value1, Long value2) {
            addCriterion("PROM_CNT_LIMIT between", value1, value2, "promCntLimit");
            return (Criteria) this;
        }

        public Criteria andPromCntLimitNotBetween(Long value1, Long value2) {
            addCriterion("PROM_CNT_LIMIT not between", value1, value2, "promCntLimit");
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