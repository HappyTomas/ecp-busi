package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ScoreDetailSelCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public ScoreDetailSelCriteria() {
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

        public Criteria andScoreTypeOrigIsNull() {
            addCriterion("SCORE_TYPE_ORIG is null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeOrigIsNotNull() {
            addCriterion("SCORE_TYPE_ORIG is not null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeOrigEqualTo(String value) {
            addCriterion("SCORE_TYPE_ORIG =", value, "scoreTypeOrig");
            return (Criteria) this;
        }

        public Criteria andScoreTypeOrigNotEqualTo(String value) {
            addCriterion("SCORE_TYPE_ORIG <>", value, "scoreTypeOrig");
            return (Criteria) this;
        }

        public Criteria andScoreTypeOrigGreaterThan(String value) {
            addCriterion("SCORE_TYPE_ORIG >", value, "scoreTypeOrig");
            return (Criteria) this;
        }

        public Criteria andScoreTypeOrigGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_TYPE_ORIG >=", value, "scoreTypeOrig");
            return (Criteria) this;
        }

        public Criteria andScoreTypeOrigLessThan(String value) {
            addCriterion("SCORE_TYPE_ORIG <", value, "scoreTypeOrig");
            return (Criteria) this;
        }

        public Criteria andScoreTypeOrigLessThanOrEqualTo(String value) {
            addCriterion("SCORE_TYPE_ORIG <=", value, "scoreTypeOrig");
            return (Criteria) this;
        }

        public Criteria andScoreTypeOrigLike(String value) {
            addCriterion("SCORE_TYPE_ORIG like", value, "scoreTypeOrig");
            return (Criteria) this;
        }

        public Criteria andScoreTypeOrigNotLike(String value) {
            addCriterion("SCORE_TYPE_ORIG not like", value, "scoreTypeOrig");
            return (Criteria) this;
        }

        public Criteria andScoreTypeOrigIn(List<String> values) {
            addCriterion("SCORE_TYPE_ORIG in", values, "scoreTypeOrig");
            return (Criteria) this;
        }

        public Criteria andScoreTypeOrigNotIn(List<String> values) {
            addCriterion("SCORE_TYPE_ORIG not in", values, "scoreTypeOrig");
            return (Criteria) this;
        }

        public Criteria andScoreTypeOrigBetween(String value1, String value2) {
            addCriterion("SCORE_TYPE_ORIG between", value1, value2, "scoreTypeOrig");
            return (Criteria) this;
        }

        public Criteria andScoreTypeOrigNotBetween(String value1, String value2) {
            addCriterion("SCORE_TYPE_ORIG not between", value1, value2, "scoreTypeOrig");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIsNull() {
            addCriterion("SCORE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIsNotNull() {
            addCriterion("SCORE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeEqualTo(String value) {
            addCriterion("SCORE_TYPE =", value, "scoreType");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNotEqualTo(String value) {
            addCriterion("SCORE_TYPE <>", value, "scoreType");
            return (Criteria) this;
        }

        public Criteria andScoreTypeGreaterThan(String value) {
            addCriterion("SCORE_TYPE >", value, "scoreType");
            return (Criteria) this;
        }

        public Criteria andScoreTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_TYPE >=", value, "scoreType");
            return (Criteria) this;
        }

        public Criteria andScoreTypeLessThan(String value) {
            addCriterion("SCORE_TYPE <", value, "scoreType");
            return (Criteria) this;
        }

        public Criteria andScoreTypeLessThanOrEqualTo(String value) {
            addCriterion("SCORE_TYPE <=", value, "scoreType");
            return (Criteria) this;
        }

        public Criteria andScoreTypeLike(String value) {
            addCriterion("SCORE_TYPE like", value, "scoreType");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNotLike(String value) {
            addCriterion("SCORE_TYPE not like", value, "scoreType");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIn(List<String> values) {
            addCriterion("SCORE_TYPE in", values, "scoreType");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNotIn(List<String> values) {
            addCriterion("SCORE_TYPE not in", values, "scoreType");
            return (Criteria) this;
        }

        public Criteria andScoreTypeBetween(String value1, String value2) {
            addCriterion("SCORE_TYPE between", value1, value2, "scoreType");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNotBetween(String value1, String value2) {
            addCriterion("SCORE_TYPE not between", value1, value2, "scoreType");
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

        public Criteria andConsumeScoreIsNull() {
            addCriterion("CONSUME_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andConsumeScoreIsNotNull() {
            addCriterion("CONSUME_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeScoreEqualTo(Long value) {
            addCriterion("CONSUME_SCORE =", value, "consumeScore");
            return (Criteria) this;
        }

        public Criteria andConsumeScoreNotEqualTo(Long value) {
            addCriterion("CONSUME_SCORE <>", value, "consumeScore");
            return (Criteria) this;
        }

        public Criteria andConsumeScoreGreaterThan(Long value) {
            addCriterion("CONSUME_SCORE >", value, "consumeScore");
            return (Criteria) this;
        }

        public Criteria andConsumeScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("CONSUME_SCORE >=", value, "consumeScore");
            return (Criteria) this;
        }

        public Criteria andConsumeScoreLessThan(Long value) {
            addCriterion("CONSUME_SCORE <", value, "consumeScore");
            return (Criteria) this;
        }

        public Criteria andConsumeScoreLessThanOrEqualTo(Long value) {
            addCriterion("CONSUME_SCORE <=", value, "consumeScore");
            return (Criteria) this;
        }

        public Criteria andConsumeScoreIn(List<Long> values) {
            addCriterion("CONSUME_SCORE in", values, "consumeScore");
            return (Criteria) this;
        }

        public Criteria andConsumeScoreNotIn(List<Long> values) {
            addCriterion("CONSUME_SCORE not in", values, "consumeScore");
            return (Criteria) this;
        }

        public Criteria andConsumeScoreBetween(Long value1, Long value2) {
            addCriterion("CONSUME_SCORE between", value1, value2, "consumeScore");
            return (Criteria) this;
        }

        public Criteria andConsumeScoreNotBetween(Long value1, Long value2) {
            addCriterion("CONSUME_SCORE not between", value1, value2, "consumeScore");
            return (Criteria) this;
        }

        public Criteria andModifyAddScoreIsNull() {
            addCriterion("MODIFY_ADD_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andModifyAddScoreIsNotNull() {
            addCriterion("MODIFY_ADD_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andModifyAddScoreEqualTo(Long value) {
            addCriterion("MODIFY_ADD_SCORE =", value, "modifyAddScore");
            return (Criteria) this;
        }

        public Criteria andModifyAddScoreNotEqualTo(Long value) {
            addCriterion("MODIFY_ADD_SCORE <>", value, "modifyAddScore");
            return (Criteria) this;
        }

        public Criteria andModifyAddScoreGreaterThan(Long value) {
            addCriterion("MODIFY_ADD_SCORE >", value, "modifyAddScore");
            return (Criteria) this;
        }

        public Criteria andModifyAddScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("MODIFY_ADD_SCORE >=", value, "modifyAddScore");
            return (Criteria) this;
        }

        public Criteria andModifyAddScoreLessThan(Long value) {
            addCriterion("MODIFY_ADD_SCORE <", value, "modifyAddScore");
            return (Criteria) this;
        }

        public Criteria andModifyAddScoreLessThanOrEqualTo(Long value) {
            addCriterion("MODIFY_ADD_SCORE <=", value, "modifyAddScore");
            return (Criteria) this;
        }

        public Criteria andModifyAddScoreIn(List<Long> values) {
            addCriterion("MODIFY_ADD_SCORE in", values, "modifyAddScore");
            return (Criteria) this;
        }

        public Criteria andModifyAddScoreNotIn(List<Long> values) {
            addCriterion("MODIFY_ADD_SCORE not in", values, "modifyAddScore");
            return (Criteria) this;
        }

        public Criteria andModifyAddScoreBetween(Long value1, Long value2) {
            addCriterion("MODIFY_ADD_SCORE between", value1, value2, "modifyAddScore");
            return (Criteria) this;
        }

        public Criteria andModifyAddScoreNotBetween(Long value1, Long value2) {
            addCriterion("MODIFY_ADD_SCORE not between", value1, value2, "modifyAddScore");
            return (Criteria) this;
        }

        public Criteria andExchangeScoreIsNull() {
            addCriterion("EXCHANGE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andExchangeScoreIsNotNull() {
            addCriterion("EXCHANGE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeScoreEqualTo(Long value) {
            addCriterion("EXCHANGE_SCORE =", value, "exchangeScore");
            return (Criteria) this;
        }

        public Criteria andExchangeScoreNotEqualTo(Long value) {
            addCriterion("EXCHANGE_SCORE <>", value, "exchangeScore");
            return (Criteria) this;
        }

        public Criteria andExchangeScoreGreaterThan(Long value) {
            addCriterion("EXCHANGE_SCORE >", value, "exchangeScore");
            return (Criteria) this;
        }

        public Criteria andExchangeScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("EXCHANGE_SCORE >=", value, "exchangeScore");
            return (Criteria) this;
        }

        public Criteria andExchangeScoreLessThan(Long value) {
            addCriterion("EXCHANGE_SCORE <", value, "exchangeScore");
            return (Criteria) this;
        }

        public Criteria andExchangeScoreLessThanOrEqualTo(Long value) {
            addCriterion("EXCHANGE_SCORE <=", value, "exchangeScore");
            return (Criteria) this;
        }

        public Criteria andExchangeScoreIn(List<Long> values) {
            addCriterion("EXCHANGE_SCORE in", values, "exchangeScore");
            return (Criteria) this;
        }

        public Criteria andExchangeScoreNotIn(List<Long> values) {
            addCriterion("EXCHANGE_SCORE not in", values, "exchangeScore");
            return (Criteria) this;
        }

        public Criteria andExchangeScoreBetween(Long value1, Long value2) {
            addCriterion("EXCHANGE_SCORE between", value1, value2, "exchangeScore");
            return (Criteria) this;
        }

        public Criteria andExchangeScoreNotBetween(Long value1, Long value2) {
            addCriterion("EXCHANGE_SCORE not between", value1, value2, "exchangeScore");
            return (Criteria) this;
        }

        public Criteria andModifyReduceScoreIsNull() {
            addCriterion("MODIFY_REDUCE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andModifyReduceScoreIsNotNull() {
            addCriterion("MODIFY_REDUCE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andModifyReduceScoreEqualTo(Long value) {
            addCriterion("MODIFY_REDUCE_SCORE =", value, "modifyReduceScore");
            return (Criteria) this;
        }

        public Criteria andModifyReduceScoreNotEqualTo(Long value) {
            addCriterion("MODIFY_REDUCE_SCORE <>", value, "modifyReduceScore");
            return (Criteria) this;
        }

        public Criteria andModifyReduceScoreGreaterThan(Long value) {
            addCriterion("MODIFY_REDUCE_SCORE >", value, "modifyReduceScore");
            return (Criteria) this;
        }

        public Criteria andModifyReduceScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("MODIFY_REDUCE_SCORE >=", value, "modifyReduceScore");
            return (Criteria) this;
        }

        public Criteria andModifyReduceScoreLessThan(Long value) {
            addCriterion("MODIFY_REDUCE_SCORE <", value, "modifyReduceScore");
            return (Criteria) this;
        }

        public Criteria andModifyReduceScoreLessThanOrEqualTo(Long value) {
            addCriterion("MODIFY_REDUCE_SCORE <=", value, "modifyReduceScore");
            return (Criteria) this;
        }

        public Criteria andModifyReduceScoreIn(List<Long> values) {
            addCriterion("MODIFY_REDUCE_SCORE in", values, "modifyReduceScore");
            return (Criteria) this;
        }

        public Criteria andModifyReduceScoreNotIn(List<Long> values) {
            addCriterion("MODIFY_REDUCE_SCORE not in", values, "modifyReduceScore");
            return (Criteria) this;
        }

        public Criteria andModifyReduceScoreBetween(Long value1, Long value2) {
            addCriterion("MODIFY_REDUCE_SCORE between", value1, value2, "modifyReduceScore");
            return (Criteria) this;
        }

        public Criteria andModifyReduceScoreNotBetween(Long value1, Long value2) {
            addCriterion("MODIFY_REDUCE_SCORE not between", value1, value2, "modifyReduceScore");
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