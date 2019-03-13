package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ScoreSourceCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public ScoreSourceCriteria() {
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

        public Criteria andSourceTypeIsNull() {
            addCriterion("SOURCE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIsNotNull() {
            addCriterion("SOURCE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeEqualTo(String value) {
            addCriterion("SOURCE_TYPE =", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotEqualTo(String value) {
            addCriterion("SOURCE_TYPE <>", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThan(String value) {
            addCriterion("SOURCE_TYPE >", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE_TYPE >=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThan(String value) {
            addCriterion("SOURCE_TYPE <", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThanOrEqualTo(String value) {
            addCriterion("SOURCE_TYPE <=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLike(String value) {
            addCriterion("SOURCE_TYPE like", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotLike(String value) {
            addCriterion("SOURCE_TYPE not like", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIn(List<String> values) {
            addCriterion("SOURCE_TYPE in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotIn(List<String> values) {
            addCriterion("SOURCE_TYPE not in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeBetween(String value1, String value2) {
            addCriterion("SOURCE_TYPE between", value1, value2, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotBetween(String value1, String value2) {
            addCriterion("SOURCE_TYPE not between", value1, value2, "sourceType");
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

        public Criteria andScoreIsNull() {
            addCriterion("SCORE is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Long value) {
            addCriterion("SCORE =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Long value) {
            addCriterion("SCORE <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Long value) {
            addCriterion("SCORE >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("SCORE >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Long value) {
            addCriterion("SCORE <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Long value) {
            addCriterion("SCORE <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Long> values) {
            addCriterion("SCORE in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Long> values) {
            addCriterion("SCORE not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Long value1, Long value2) {
            addCriterion("SCORE between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Long value1, Long value2) {
            addCriterion("SCORE not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIsNull() {
            addCriterion("SUB_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIsNotNull() {
            addCriterion("SUB_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdEqualTo(String value) {
            addCriterion("SUB_ORDER_ID =", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotEqualTo(String value) {
            addCriterion("SUB_ORDER_ID <>", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdGreaterThan(String value) {
            addCriterion("SUB_ORDER_ID >", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("SUB_ORDER_ID >=", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdLessThan(String value) {
            addCriterion("SUB_ORDER_ID <", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdLessThanOrEqualTo(String value) {
            addCriterion("SUB_ORDER_ID <=", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdLike(String value) {
            addCriterion("SUB_ORDER_ID like", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotLike(String value) {
            addCriterion("SUB_ORDER_ID not like", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIn(List<String> values) {
            addCriterion("SUB_ORDER_ID in", values, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotIn(List<String> values) {
            addCriterion("SUB_ORDER_ID not in", values, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdBetween(String value1, String value2) {
            addCriterion("SUB_ORDER_ID between", value1, value2, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotBetween(String value1, String value2) {
            addCriterion("SUB_ORDER_ID not between", value1, value2, "subOrderId");
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

        public Criteria andInureTimeIsNull() {
            addCriterion("INURE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInureTimeIsNotNull() {
            addCriterion("INURE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInureTimeEqualTo(Timestamp value) {
            addCriterion("INURE_TIME =", value, "inureTime");
            return (Criteria) this;
        }

        public Criteria andInureTimeNotEqualTo(Timestamp value) {
            addCriterion("INURE_TIME <>", value, "inureTime");
            return (Criteria) this;
        }

        public Criteria andInureTimeGreaterThan(Timestamp value) {
            addCriterion("INURE_TIME >", value, "inureTime");
            return (Criteria) this;
        }

        public Criteria andInureTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("INURE_TIME >=", value, "inureTime");
            return (Criteria) this;
        }

        public Criteria andInureTimeLessThan(Timestamp value) {
            addCriterion("INURE_TIME <", value, "inureTime");
            return (Criteria) this;
        }

        public Criteria andInureTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("INURE_TIME <=", value, "inureTime");
            return (Criteria) this;
        }

        public Criteria andInureTimeIn(List<Timestamp> values) {
            addCriterion("INURE_TIME in", values, "inureTime");
            return (Criteria) this;
        }

        public Criteria andInureTimeNotIn(List<Timestamp> values) {
            addCriterion("INURE_TIME not in", values, "inureTime");
            return (Criteria) this;
        }

        public Criteria andInureTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INURE_TIME between", value1, value2, "inureTime");
            return (Criteria) this;
        }

        public Criteria andInureTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INURE_TIME not between", value1, value2, "inureTime");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNull() {
            addCriterion("TOTAL_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNotNull() {
            addCriterion("TOTAL_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreEqualTo(Long value) {
            addCriterion("TOTAL_SCORE =", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotEqualTo(Long value) {
            addCriterion("TOTAL_SCORE <>", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThan(Long value) {
            addCriterion("TOTAL_SCORE >", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("TOTAL_SCORE >=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThan(Long value) {
            addCriterion("TOTAL_SCORE <", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThanOrEqualTo(Long value) {
            addCriterion("TOTAL_SCORE <=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIn(List<Long> values) {
            addCriterion("TOTAL_SCORE in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotIn(List<Long> values) {
            addCriterion("TOTAL_SCORE not in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreBetween(Long value1, Long value2) {
            addCriterion("TOTAL_SCORE between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotBetween(Long value1, Long value2) {
            addCriterion("TOTAL_SCORE not between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andFreezeScoreIsNull() {
            addCriterion("FREEZE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andFreezeScoreIsNotNull() {
            addCriterion("FREEZE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeScoreEqualTo(Long value) {
            addCriterion("FREEZE_SCORE =", value, "freezeScore");
            return (Criteria) this;
        }

        public Criteria andFreezeScoreNotEqualTo(Long value) {
            addCriterion("FREEZE_SCORE <>", value, "freezeScore");
            return (Criteria) this;
        }

        public Criteria andFreezeScoreGreaterThan(Long value) {
            addCriterion("FREEZE_SCORE >", value, "freezeScore");
            return (Criteria) this;
        }

        public Criteria andFreezeScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("FREEZE_SCORE >=", value, "freezeScore");
            return (Criteria) this;
        }

        public Criteria andFreezeScoreLessThan(Long value) {
            addCriterion("FREEZE_SCORE <", value, "freezeScore");
            return (Criteria) this;
        }

        public Criteria andFreezeScoreLessThanOrEqualTo(Long value) {
            addCriterion("FREEZE_SCORE <=", value, "freezeScore");
            return (Criteria) this;
        }

        public Criteria andFreezeScoreIn(List<Long> values) {
            addCriterion("FREEZE_SCORE in", values, "freezeScore");
            return (Criteria) this;
        }

        public Criteria andFreezeScoreNotIn(List<Long> values) {
            addCriterion("FREEZE_SCORE not in", values, "freezeScore");
            return (Criteria) this;
        }

        public Criteria andFreezeScoreBetween(Long value1, Long value2) {
            addCriterion("FREEZE_SCORE between", value1, value2, "freezeScore");
            return (Criteria) this;
        }

        public Criteria andFreezeScoreNotBetween(Long value1, Long value2) {
            addCriterion("FREEZE_SCORE not between", value1, value2, "freezeScore");
            return (Criteria) this;
        }

        public Criteria andUsedScoreIsNull() {
            addCriterion("USED_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andUsedScoreIsNotNull() {
            addCriterion("USED_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andUsedScoreEqualTo(Long value) {
            addCriterion("USED_SCORE =", value, "usedScore");
            return (Criteria) this;
        }

        public Criteria andUsedScoreNotEqualTo(Long value) {
            addCriterion("USED_SCORE <>", value, "usedScore");
            return (Criteria) this;
        }

        public Criteria andUsedScoreGreaterThan(Long value) {
            addCriterion("USED_SCORE >", value, "usedScore");
            return (Criteria) this;
        }

        public Criteria andUsedScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("USED_SCORE >=", value, "usedScore");
            return (Criteria) this;
        }

        public Criteria andUsedScoreLessThan(Long value) {
            addCriterion("USED_SCORE <", value, "usedScore");
            return (Criteria) this;
        }

        public Criteria andUsedScoreLessThanOrEqualTo(Long value) {
            addCriterion("USED_SCORE <=", value, "usedScore");
            return (Criteria) this;
        }

        public Criteria andUsedScoreIn(List<Long> values) {
            addCriterion("USED_SCORE in", values, "usedScore");
            return (Criteria) this;
        }

        public Criteria andUsedScoreNotIn(List<Long> values) {
            addCriterion("USED_SCORE not in", values, "usedScore");
            return (Criteria) this;
        }

        public Criteria andUsedScoreBetween(Long value1, Long value2) {
            addCriterion("USED_SCORE between", value1, value2, "usedScore");
            return (Criteria) this;
        }

        public Criteria andUsedScoreNotBetween(Long value1, Long value2) {
            addCriterion("USED_SCORE not between", value1, value2, "usedScore");
            return (Criteria) this;
        }

        public Criteria andBalanceScoreIsNull() {
            addCriterion("BALANCE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andBalanceScoreIsNotNull() {
            addCriterion("BALANCE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceScoreEqualTo(Long value) {
            addCriterion("BALANCE_SCORE =", value, "balanceScore");
            return (Criteria) this;
        }

        public Criteria andBalanceScoreNotEqualTo(Long value) {
            addCriterion("BALANCE_SCORE <>", value, "balanceScore");
            return (Criteria) this;
        }

        public Criteria andBalanceScoreGreaterThan(Long value) {
            addCriterion("BALANCE_SCORE >", value, "balanceScore");
            return (Criteria) this;
        }

        public Criteria andBalanceScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("BALANCE_SCORE >=", value, "balanceScore");
            return (Criteria) this;
        }

        public Criteria andBalanceScoreLessThan(Long value) {
            addCriterion("BALANCE_SCORE <", value, "balanceScore");
            return (Criteria) this;
        }

        public Criteria andBalanceScoreLessThanOrEqualTo(Long value) {
            addCriterion("BALANCE_SCORE <=", value, "balanceScore");
            return (Criteria) this;
        }

        public Criteria andBalanceScoreIn(List<Long> values) {
            addCriterion("BALANCE_SCORE in", values, "balanceScore");
            return (Criteria) this;
        }

        public Criteria andBalanceScoreNotIn(List<Long> values) {
            addCriterion("BALANCE_SCORE not in", values, "balanceScore");
            return (Criteria) this;
        }

        public Criteria andBalanceScoreBetween(Long value1, Long value2) {
            addCriterion("BALANCE_SCORE between", value1, value2, "balanceScore");
            return (Criteria) this;
        }

        public Criteria andBalanceScoreNotBetween(Long value1, Long value2) {
            addCriterion("BALANCE_SCORE not between", value1, value2, "balanceScore");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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

        public Criteria andBackFlagIsNull() {
            addCriterion("BACK_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andBackFlagIsNotNull() {
            addCriterion("BACK_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andBackFlagEqualTo(String value) {
            addCriterion("BACK_FLAG =", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagNotEqualTo(String value) {
            addCriterion("BACK_FLAG <>", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagGreaterThan(String value) {
            addCriterion("BACK_FLAG >", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagGreaterThanOrEqualTo(String value) {
            addCriterion("BACK_FLAG >=", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagLessThan(String value) {
            addCriterion("BACK_FLAG <", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagLessThanOrEqualTo(String value) {
            addCriterion("BACK_FLAG <=", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagLike(String value) {
            addCriterion("BACK_FLAG like", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagNotLike(String value) {
            addCriterion("BACK_FLAG not like", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagIn(List<String> values) {
            addCriterion("BACK_FLAG in", values, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagNotIn(List<String> values) {
            addCriterion("BACK_FLAG not in", values, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagBetween(String value1, String value2) {
            addCriterion("BACK_FLAG between", value1, value2, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagNotBetween(String value1, String value2) {
            addCriterion("BACK_FLAG not between", value1, value2, "backFlag");
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

        public Criteria andIsbnCodeIsNull() {
            addCriterion("ISBN_CODE is null");
            return (Criteria) this;
        }

        public Criteria andIsbnCodeIsNotNull() {
            addCriterion("ISBN_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andIsbnCodeEqualTo(String value) {
            addCriterion("ISBN_CODE =", value, "isbnCode");
            return (Criteria) this;
        }

        public Criteria andIsbnCodeNotEqualTo(String value) {
            addCriterion("ISBN_CODE <>", value, "isbnCode");
            return (Criteria) this;
        }

        public Criteria andIsbnCodeGreaterThan(String value) {
            addCriterion("ISBN_CODE >", value, "isbnCode");
            return (Criteria) this;
        }

        public Criteria andIsbnCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ISBN_CODE >=", value, "isbnCode");
            return (Criteria) this;
        }

        public Criteria andIsbnCodeLessThan(String value) {
            addCriterion("ISBN_CODE <", value, "isbnCode");
            return (Criteria) this;
        }

        public Criteria andIsbnCodeLessThanOrEqualTo(String value) {
            addCriterion("ISBN_CODE <=", value, "isbnCode");
            return (Criteria) this;
        }

        public Criteria andIsbnCodeLike(String value) {
            addCriterion("ISBN_CODE like", value, "isbnCode");
            return (Criteria) this;
        }

        public Criteria andIsbnCodeNotLike(String value) {
            addCriterion("ISBN_CODE not like", value, "isbnCode");
            return (Criteria) this;
        }

        public Criteria andIsbnCodeIn(List<String> values) {
            addCriterion("ISBN_CODE in", values, "isbnCode");
            return (Criteria) this;
        }

        public Criteria andIsbnCodeNotIn(List<String> values) {
            addCriterion("ISBN_CODE not in", values, "isbnCode");
            return (Criteria) this;
        }

        public Criteria andIsbnCodeBetween(String value1, String value2) {
            addCriterion("ISBN_CODE between", value1, value2, "isbnCode");
            return (Criteria) this;
        }

        public Criteria andIsbnCodeNotBetween(String value1, String value2) {
            addCriterion("ISBN_CODE not between", value1, value2, "isbnCode");
            return (Criteria) this;
        }

        public Criteria andBookCodeIsNull() {
            addCriterion("BOOK_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBookCodeIsNotNull() {
            addCriterion("BOOK_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBookCodeEqualTo(String value) {
            addCriterion("BOOK_CODE =", value, "bookCode");
            return (Criteria) this;
        }

        public Criteria andBookCodeNotEqualTo(String value) {
            addCriterion("BOOK_CODE <>", value, "bookCode");
            return (Criteria) this;
        }

        public Criteria andBookCodeGreaterThan(String value) {
            addCriterion("BOOK_CODE >", value, "bookCode");
            return (Criteria) this;
        }

        public Criteria andBookCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BOOK_CODE >=", value, "bookCode");
            return (Criteria) this;
        }

        public Criteria andBookCodeLessThan(String value) {
            addCriterion("BOOK_CODE <", value, "bookCode");
            return (Criteria) this;
        }

        public Criteria andBookCodeLessThanOrEqualTo(String value) {
            addCriterion("BOOK_CODE <=", value, "bookCode");
            return (Criteria) this;
        }

        public Criteria andBookCodeLike(String value) {
            addCriterion("BOOK_CODE like", value, "bookCode");
            return (Criteria) this;
        }

        public Criteria andBookCodeNotLike(String value) {
            addCriterion("BOOK_CODE not like", value, "bookCode");
            return (Criteria) this;
        }

        public Criteria andBookCodeIn(List<String> values) {
            addCriterion("BOOK_CODE in", values, "bookCode");
            return (Criteria) this;
        }

        public Criteria andBookCodeNotIn(List<String> values) {
            addCriterion("BOOK_CODE not in", values, "bookCode");
            return (Criteria) this;
        }

        public Criteria andBookCodeBetween(String value1, String value2) {
            addCriterion("BOOK_CODE between", value1, value2, "bookCode");
            return (Criteria) this;
        }

        public Criteria andBookCodeNotBetween(String value1, String value2) {
            addCriterion("BOOK_CODE not between", value1, value2, "bookCode");
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