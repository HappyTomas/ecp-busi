package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsPresaleCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsPresaleCriteria() {
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

        public Criteria andAdvanceStateIsNull() {
            addCriterion("ADVANCE_STATE is null");
            return (Criteria) this;
        }

        public Criteria andAdvanceStateIsNotNull() {
            addCriterion("ADVANCE_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andAdvanceStateEqualTo(String value) {
            addCriterion("ADVANCE_STATE =", value, "advanceState");
            return (Criteria) this;
        }

        public Criteria andAdvanceStateNotEqualTo(String value) {
            addCriterion("ADVANCE_STATE <>", value, "advanceState");
            return (Criteria) this;
        }

        public Criteria andAdvanceStateGreaterThan(String value) {
            addCriterion("ADVANCE_STATE >", value, "advanceState");
            return (Criteria) this;
        }

        public Criteria andAdvanceStateGreaterThanOrEqualTo(String value) {
            addCriterion("ADVANCE_STATE >=", value, "advanceState");
            return (Criteria) this;
        }

        public Criteria andAdvanceStateLessThan(String value) {
            addCriterion("ADVANCE_STATE <", value, "advanceState");
            return (Criteria) this;
        }

        public Criteria andAdvanceStateLessThanOrEqualTo(String value) {
            addCriterion("ADVANCE_STATE <=", value, "advanceState");
            return (Criteria) this;
        }

        public Criteria andAdvanceStateLike(String value) {
            addCriterion("ADVANCE_STATE like", value, "advanceState");
            return (Criteria) this;
        }

        public Criteria andAdvanceStateNotLike(String value) {
            addCriterion("ADVANCE_STATE not like", value, "advanceState");
            return (Criteria) this;
        }

        public Criteria andAdvanceStateIn(List<String> values) {
            addCriterion("ADVANCE_STATE in", values, "advanceState");
            return (Criteria) this;
        }

        public Criteria andAdvanceStateNotIn(List<String> values) {
            addCriterion("ADVANCE_STATE not in", values, "advanceState");
            return (Criteria) this;
        }

        public Criteria andAdvanceStateBetween(String value1, String value2) {
            addCriterion("ADVANCE_STATE between", value1, value2, "advanceState");
            return (Criteria) this;
        }

        public Criteria andAdvanceStateNotBetween(String value1, String value2) {
            addCriterion("ADVANCE_STATE not between", value1, value2, "advanceState");
            return (Criteria) this;
        }

        public Criteria andPresailStartTimeIsNull() {
            addCriterion("PRESAIL_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPresailStartTimeIsNotNull() {
            addCriterion("PRESAIL_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPresailStartTimeEqualTo(Timestamp value) {
            addCriterion("PRESAIL_START_TIME =", value, "presailStartTime");
            return (Criteria) this;
        }

        public Criteria andPresailStartTimeNotEqualTo(Timestamp value) {
            addCriterion("PRESAIL_START_TIME <>", value, "presailStartTime");
            return (Criteria) this;
        }

        public Criteria andPresailStartTimeGreaterThan(Timestamp value) {
            addCriterion("PRESAIL_START_TIME >", value, "presailStartTime");
            return (Criteria) this;
        }

        public Criteria andPresailStartTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("PRESAIL_START_TIME >=", value, "presailStartTime");
            return (Criteria) this;
        }

        public Criteria andPresailStartTimeLessThan(Timestamp value) {
            addCriterion("PRESAIL_START_TIME <", value, "presailStartTime");
            return (Criteria) this;
        }

        public Criteria andPresailStartTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("PRESAIL_START_TIME <=", value, "presailStartTime");
            return (Criteria) this;
        }

        public Criteria andPresailStartTimeIn(List<Timestamp> values) {
            addCriterion("PRESAIL_START_TIME in", values, "presailStartTime");
            return (Criteria) this;
        }

        public Criteria andPresailStartTimeNotIn(List<Timestamp> values) {
            addCriterion("PRESAIL_START_TIME not in", values, "presailStartTime");
            return (Criteria) this;
        }

        public Criteria andPresailStartTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PRESAIL_START_TIME between", value1, value2, "presailStartTime");
            return (Criteria) this;
        }

        public Criteria andPresailStartTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PRESAIL_START_TIME not between", value1, value2, "presailStartTime");
            return (Criteria) this;
        }

        public Criteria andPresailEndTimeIsNull() {
            addCriterion("PRESAIL_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPresailEndTimeIsNotNull() {
            addCriterion("PRESAIL_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPresailEndTimeEqualTo(Timestamp value) {
            addCriterion("PRESAIL_END_TIME =", value, "presailEndTime");
            return (Criteria) this;
        }

        public Criteria andPresailEndTimeNotEqualTo(Timestamp value) {
            addCriterion("PRESAIL_END_TIME <>", value, "presailEndTime");
            return (Criteria) this;
        }

        public Criteria andPresailEndTimeGreaterThan(Timestamp value) {
            addCriterion("PRESAIL_END_TIME >", value, "presailEndTime");
            return (Criteria) this;
        }

        public Criteria andPresailEndTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("PRESAIL_END_TIME >=", value, "presailEndTime");
            return (Criteria) this;
        }

        public Criteria andPresailEndTimeLessThan(Timestamp value) {
            addCriterion("PRESAIL_END_TIME <", value, "presailEndTime");
            return (Criteria) this;
        }

        public Criteria andPresailEndTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("PRESAIL_END_TIME <=", value, "presailEndTime");
            return (Criteria) this;
        }

        public Criteria andPresailEndTimeIn(List<Timestamp> values) {
            addCriterion("PRESAIL_END_TIME in", values, "presailEndTime");
            return (Criteria) this;
        }

        public Criteria andPresailEndTimeNotIn(List<Timestamp> values) {
            addCriterion("PRESAIL_END_TIME not in", values, "presailEndTime");
            return (Criteria) this;
        }

        public Criteria andPresailEndTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PRESAIL_END_TIME between", value1, value2, "presailEndTime");
            return (Criteria) this;
        }

        public Criteria andPresailEndTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PRESAIL_END_TIME not between", value1, value2, "presailEndTime");
            return (Criteria) this;
        }

        public Criteria andPresailModeIsNull() {
            addCriterion("PRESAIL_MODE is null");
            return (Criteria) this;
        }

        public Criteria andPresailModeIsNotNull() {
            addCriterion("PRESAIL_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andPresailModeEqualTo(String value) {
            addCriterion("PRESAIL_MODE =", value, "presailMode");
            return (Criteria) this;
        }

        public Criteria andPresailModeNotEqualTo(String value) {
            addCriterion("PRESAIL_MODE <>", value, "presailMode");
            return (Criteria) this;
        }

        public Criteria andPresailModeGreaterThan(String value) {
            addCriterion("PRESAIL_MODE >", value, "presailMode");
            return (Criteria) this;
        }

        public Criteria andPresailModeGreaterThanOrEqualTo(String value) {
            addCriterion("PRESAIL_MODE >=", value, "presailMode");
            return (Criteria) this;
        }

        public Criteria andPresailModeLessThan(String value) {
            addCriterion("PRESAIL_MODE <", value, "presailMode");
            return (Criteria) this;
        }

        public Criteria andPresailModeLessThanOrEqualTo(String value) {
            addCriterion("PRESAIL_MODE <=", value, "presailMode");
            return (Criteria) this;
        }

        public Criteria andPresailModeLike(String value) {
            addCriterion("PRESAIL_MODE like", value, "presailMode");
            return (Criteria) this;
        }

        public Criteria andPresailModeNotLike(String value) {
            addCriterion("PRESAIL_MODE not like", value, "presailMode");
            return (Criteria) this;
        }

        public Criteria andPresailModeIn(List<String> values) {
            addCriterion("PRESAIL_MODE in", values, "presailMode");
            return (Criteria) this;
        }

        public Criteria andPresailModeNotIn(List<String> values) {
            addCriterion("PRESAIL_MODE not in", values, "presailMode");
            return (Criteria) this;
        }

        public Criteria andPresailModeBetween(String value1, String value2) {
            addCriterion("PRESAIL_MODE between", value1, value2, "presailMode");
            return (Criteria) this;
        }

        public Criteria andPresailModeNotBetween(String value1, String value2) {
            addCriterion("PRESAIL_MODE not between", value1, value2, "presailMode");
            return (Criteria) this;
        }

        public Criteria andBargainMoneyIsNull() {
            addCriterion("BARGAIN_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andBargainMoneyIsNotNull() {
            addCriterion("BARGAIN_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andBargainMoneyEqualTo(Long value) {
            addCriterion("BARGAIN_MONEY =", value, "bargainMoney");
            return (Criteria) this;
        }

        public Criteria andBargainMoneyNotEqualTo(Long value) {
            addCriterion("BARGAIN_MONEY <>", value, "bargainMoney");
            return (Criteria) this;
        }

        public Criteria andBargainMoneyGreaterThan(Long value) {
            addCriterion("BARGAIN_MONEY >", value, "bargainMoney");
            return (Criteria) this;
        }

        public Criteria andBargainMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("BARGAIN_MONEY >=", value, "bargainMoney");
            return (Criteria) this;
        }

        public Criteria andBargainMoneyLessThan(Long value) {
            addCriterion("BARGAIN_MONEY <", value, "bargainMoney");
            return (Criteria) this;
        }

        public Criteria andBargainMoneyLessThanOrEqualTo(Long value) {
            addCriterion("BARGAIN_MONEY <=", value, "bargainMoney");
            return (Criteria) this;
        }

        public Criteria andBargainMoneyIn(List<Long> values) {
            addCriterion("BARGAIN_MONEY in", values, "bargainMoney");
            return (Criteria) this;
        }

        public Criteria andBargainMoneyNotIn(List<Long> values) {
            addCriterion("BARGAIN_MONEY not in", values, "bargainMoney");
            return (Criteria) this;
        }

        public Criteria andBargainMoneyBetween(Long value1, Long value2) {
            addCriterion("BARGAIN_MONEY between", value1, value2, "bargainMoney");
            return (Criteria) this;
        }

        public Criteria andBargainMoneyNotBetween(Long value1, Long value2) {
            addCriterion("BARGAIN_MONEY not between", value1, value2, "bargainMoney");
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

        public Criteria andPreSendTimeIsNull() {
            addCriterion("PRE_SEND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPreSendTimeIsNotNull() {
            addCriterion("PRE_SEND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPreSendTimeEqualTo(Timestamp value) {
            addCriterion("PRE_SEND_TIME =", value, "preSendTime");
            return (Criteria) this;
        }

        public Criteria andPreSendTimeNotEqualTo(Timestamp value) {
            addCriterion("PRE_SEND_TIME <>", value, "preSendTime");
            return (Criteria) this;
        }

        public Criteria andPreSendTimeGreaterThan(Timestamp value) {
            addCriterion("PRE_SEND_TIME >", value, "preSendTime");
            return (Criteria) this;
        }

        public Criteria andPreSendTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("PRE_SEND_TIME >=", value, "preSendTime");
            return (Criteria) this;
        }

        public Criteria andPreSendTimeLessThan(Timestamp value) {
            addCriterion("PRE_SEND_TIME <", value, "preSendTime");
            return (Criteria) this;
        }

        public Criteria andPreSendTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("PRE_SEND_TIME <=", value, "preSendTime");
            return (Criteria) this;
        }

        public Criteria andPreSendTimeIn(List<Timestamp> values) {
            addCriterion("PRE_SEND_TIME in", values, "preSendTime");
            return (Criteria) this;
        }

        public Criteria andPreSendTimeNotIn(List<Timestamp> values) {
            addCriterion("PRE_SEND_TIME not in", values, "preSendTime");
            return (Criteria) this;
        }

        public Criteria andPreSendTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PRE_SEND_TIME between", value1, value2, "preSendTime");
            return (Criteria) this;
        }

        public Criteria andPreSendTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PRE_SEND_TIME not between", value1, value2, "preSendTime");
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
