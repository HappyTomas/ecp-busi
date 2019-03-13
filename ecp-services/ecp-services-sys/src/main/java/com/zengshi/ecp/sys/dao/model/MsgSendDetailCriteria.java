package com.zengshi.ecp.sys.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MsgSendDetailCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public MsgSendDetailCriteria() {
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

        public Criteria andMsgDetailIdIsNull() {
            addCriterion("MSG_DETAIL_ID is null");
            return (Criteria) this;
        }

        public Criteria andMsgDetailIdIsNotNull() {
            addCriterion("MSG_DETAIL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMsgDetailIdEqualTo(Long value) {
            addCriterion("MSG_DETAIL_ID =", value, "msgDetailId");
            return (Criteria) this;
        }

        public Criteria andMsgDetailIdNotEqualTo(Long value) {
            addCriterion("MSG_DETAIL_ID <>", value, "msgDetailId");
            return (Criteria) this;
        }

        public Criteria andMsgDetailIdGreaterThan(Long value) {
            addCriterion("MSG_DETAIL_ID >", value, "msgDetailId");
            return (Criteria) this;
        }

        public Criteria andMsgDetailIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MSG_DETAIL_ID >=", value, "msgDetailId");
            return (Criteria) this;
        }

        public Criteria andMsgDetailIdLessThan(Long value) {
            addCriterion("MSG_DETAIL_ID <", value, "msgDetailId");
            return (Criteria) this;
        }

        public Criteria andMsgDetailIdLessThanOrEqualTo(Long value) {
            addCriterion("MSG_DETAIL_ID <=", value, "msgDetailId");
            return (Criteria) this;
        }

        public Criteria andMsgDetailIdIn(List<Long> values) {
            addCriterion("MSG_DETAIL_ID in", values, "msgDetailId");
            return (Criteria) this;
        }

        public Criteria andMsgDetailIdNotIn(List<Long> values) {
            addCriterion("MSG_DETAIL_ID not in", values, "msgDetailId");
            return (Criteria) this;
        }

        public Criteria andMsgDetailIdBetween(Long value1, Long value2) {
            addCriterion("MSG_DETAIL_ID between", value1, value2, "msgDetailId");
            return (Criteria) this;
        }

        public Criteria andMsgDetailIdNotBetween(Long value1, Long value2) {
            addCriterion("MSG_DETAIL_ID not between", value1, value2, "msgDetailId");
            return (Criteria) this;
        }

        public Criteria andMsgInfoIdIsNull() {
            addCriterion("MSG_INFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andMsgInfoIdIsNotNull() {
            addCriterion("MSG_INFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMsgInfoIdEqualTo(Long value) {
            addCriterion("MSG_INFO_ID =", value, "msgInfoId");
            return (Criteria) this;
        }

        public Criteria andMsgInfoIdNotEqualTo(Long value) {
            addCriterion("MSG_INFO_ID <>", value, "msgInfoId");
            return (Criteria) this;
        }

        public Criteria andMsgInfoIdGreaterThan(Long value) {
            addCriterion("MSG_INFO_ID >", value, "msgInfoId");
            return (Criteria) this;
        }

        public Criteria andMsgInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MSG_INFO_ID >=", value, "msgInfoId");
            return (Criteria) this;
        }

        public Criteria andMsgInfoIdLessThan(Long value) {
            addCriterion("MSG_INFO_ID <", value, "msgInfoId");
            return (Criteria) this;
        }

        public Criteria andMsgInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("MSG_INFO_ID <=", value, "msgInfoId");
            return (Criteria) this;
        }

        public Criteria andMsgInfoIdIn(List<Long> values) {
            addCriterion("MSG_INFO_ID in", values, "msgInfoId");
            return (Criteria) this;
        }

        public Criteria andMsgInfoIdNotIn(List<Long> values) {
            addCriterion("MSG_INFO_ID not in", values, "msgInfoId");
            return (Criteria) this;
        }

        public Criteria andMsgInfoIdBetween(Long value1, Long value2) {
            addCriterion("MSG_INFO_ID between", value1, value2, "msgInfoId");
            return (Criteria) this;
        }

        public Criteria andMsgInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("MSG_INFO_ID not between", value1, value2, "msgInfoId");
            return (Criteria) this;
        }

        public Criteria andMsgCodeIsNull() {
            addCriterion("MSG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMsgCodeIsNotNull() {
            addCriterion("MSG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMsgCodeEqualTo(String value) {
            addCriterion("MSG_CODE =", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotEqualTo(String value) {
            addCriterion("MSG_CODE <>", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeGreaterThan(String value) {
            addCriterion("MSG_CODE >", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_CODE >=", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeLessThan(String value) {
            addCriterion("MSG_CODE <", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeLessThanOrEqualTo(String value) {
            addCriterion("MSG_CODE <=", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeLike(String value) {
            addCriterion("MSG_CODE like", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotLike(String value) {
            addCriterion("MSG_CODE not like", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeIn(List<String> values) {
            addCriterion("MSG_CODE in", values, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotIn(List<String> values) {
            addCriterion("MSG_CODE not in", values, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeBetween(String value1, String value2) {
            addCriterion("MSG_CODE between", value1, value2, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotBetween(String value1, String value2) {
            addCriterion("MSG_CODE not between", value1, value2, "msgCode");
            return (Criteria) this;
        }

        public Criteria andRecUserIdIsNull() {
            addCriterion("REC_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andRecUserIdIsNotNull() {
            addCriterion("REC_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRecUserIdEqualTo(Long value) {
            addCriterion("REC_USER_ID =", value, "recUserId");
            return (Criteria) this;
        }

        public Criteria andRecUserIdNotEqualTo(Long value) {
            addCriterion("REC_USER_ID <>", value, "recUserId");
            return (Criteria) this;
        }

        public Criteria andRecUserIdGreaterThan(Long value) {
            addCriterion("REC_USER_ID >", value, "recUserId");
            return (Criteria) this;
        }

        public Criteria andRecUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("REC_USER_ID >=", value, "recUserId");
            return (Criteria) this;
        }

        public Criteria andRecUserIdLessThan(Long value) {
            addCriterion("REC_USER_ID <", value, "recUserId");
            return (Criteria) this;
        }

        public Criteria andRecUserIdLessThanOrEqualTo(Long value) {
            addCriterion("REC_USER_ID <=", value, "recUserId");
            return (Criteria) this;
        }

        public Criteria andRecUserIdIn(List<Long> values) {
            addCriterion("REC_USER_ID in", values, "recUserId");
            return (Criteria) this;
        }

        public Criteria andRecUserIdNotIn(List<Long> values) {
            addCriterion("REC_USER_ID not in", values, "recUserId");
            return (Criteria) this;
        }

        public Criteria andRecUserIdBetween(Long value1, Long value2) {
            addCriterion("REC_USER_ID between", value1, value2, "recUserId");
            return (Criteria) this;
        }

        public Criteria andRecUserIdNotBetween(Long value1, Long value2) {
            addCriterion("REC_USER_ID not between", value1, value2, "recUserId");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNull() {
            addCriterion("SEND_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNotNull() {
            addCriterion("SEND_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSendTypeEqualTo(String value) {
            addCriterion("SEND_TYPE =", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotEqualTo(String value) {
            addCriterion("SEND_TYPE <>", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThan(String value) {
            addCriterion("SEND_TYPE >", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_TYPE >=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThan(String value) {
            addCriterion("SEND_TYPE <", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThanOrEqualTo(String value) {
            addCriterion("SEND_TYPE <=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLike(String value) {
            addCriterion("SEND_TYPE like", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotLike(String value) {
            addCriterion("SEND_TYPE not like", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeIn(List<String> values) {
            addCriterion("SEND_TYPE in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotIn(List<String> values) {
            addCriterion("SEND_TYPE not in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeBetween(String value1, String value2) {
            addCriterion("SEND_TYPE between", value1, value2, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotBetween(String value1, String value2) {
            addCriterion("SEND_TYPE not between", value1, value2, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("SEND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("SEND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Timestamp value) {
            addCriterion("SEND_TIME =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Timestamp value) {
            addCriterion("SEND_TIME <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Timestamp value) {
            addCriterion("SEND_TIME >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("SEND_TIME >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Timestamp value) {
            addCriterion("SEND_TIME <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("SEND_TIME <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Timestamp> values) {
            addCriterion("SEND_TIME in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Timestamp> values) {
            addCriterion("SEND_TIME not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SEND_TIME between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SEND_TIME not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andRecCodeIsNull() {
            addCriterion("REC_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRecCodeIsNotNull() {
            addCriterion("REC_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRecCodeEqualTo(String value) {
            addCriterion("REC_CODE =", value, "recCode");
            return (Criteria) this;
        }

        public Criteria andRecCodeNotEqualTo(String value) {
            addCriterion("REC_CODE <>", value, "recCode");
            return (Criteria) this;
        }

        public Criteria andRecCodeGreaterThan(String value) {
            addCriterion("REC_CODE >", value, "recCode");
            return (Criteria) this;
        }

        public Criteria andRecCodeGreaterThanOrEqualTo(String value) {
            addCriterion("REC_CODE >=", value, "recCode");
            return (Criteria) this;
        }

        public Criteria andRecCodeLessThan(String value) {
            addCriterion("REC_CODE <", value, "recCode");
            return (Criteria) this;
        }

        public Criteria andRecCodeLessThanOrEqualTo(String value) {
            addCriterion("REC_CODE <=", value, "recCode");
            return (Criteria) this;
        }

        public Criteria andRecCodeLike(String value) {
            addCriterion("REC_CODE like", value, "recCode");
            return (Criteria) this;
        }

        public Criteria andRecCodeNotLike(String value) {
            addCriterion("REC_CODE not like", value, "recCode");
            return (Criteria) this;
        }

        public Criteria andRecCodeIn(List<String> values) {
            addCriterion("REC_CODE in", values, "recCode");
            return (Criteria) this;
        }

        public Criteria andRecCodeNotIn(List<String> values) {
            addCriterion("REC_CODE not in", values, "recCode");
            return (Criteria) this;
        }

        public Criteria andRecCodeBetween(String value1, String value2) {
            addCriterion("REC_CODE between", value1, value2, "recCode");
            return (Criteria) this;
        }

        public Criteria andRecCodeNotBetween(String value1, String value2) {
            addCriterion("REC_CODE not between", value1, value2, "recCode");
            return (Criteria) this;
        }

        public Criteria andMsgMemoIsNull() {
            addCriterion("MSG_MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMsgMemoIsNotNull() {
            addCriterion("MSG_MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMsgMemoEqualTo(String value) {
            addCriterion("MSG_MEMO =", value, "msgMemo");
            return (Criteria) this;
        }

        public Criteria andMsgMemoNotEqualTo(String value) {
            addCriterion("MSG_MEMO <>", value, "msgMemo");
            return (Criteria) this;
        }

        public Criteria andMsgMemoGreaterThan(String value) {
            addCriterion("MSG_MEMO >", value, "msgMemo");
            return (Criteria) this;
        }

        public Criteria andMsgMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_MEMO >=", value, "msgMemo");
            return (Criteria) this;
        }

        public Criteria andMsgMemoLessThan(String value) {
            addCriterion("MSG_MEMO <", value, "msgMemo");
            return (Criteria) this;
        }

        public Criteria andMsgMemoLessThanOrEqualTo(String value) {
            addCriterion("MSG_MEMO <=", value, "msgMemo");
            return (Criteria) this;
        }

        public Criteria andMsgMemoLike(String value) {
            addCriterion("MSG_MEMO like", value, "msgMemo");
            return (Criteria) this;
        }

        public Criteria andMsgMemoNotLike(String value) {
            addCriterion("MSG_MEMO not like", value, "msgMemo");
            return (Criteria) this;
        }

        public Criteria andMsgMemoIn(List<String> values) {
            addCriterion("MSG_MEMO in", values, "msgMemo");
            return (Criteria) this;
        }

        public Criteria andMsgMemoNotIn(List<String> values) {
            addCriterion("MSG_MEMO not in", values, "msgMemo");
            return (Criteria) this;
        }

        public Criteria andMsgMemoBetween(String value1, String value2) {
            addCriterion("MSG_MEMO between", value1, value2, "msgMemo");
            return (Criteria) this;
        }

        public Criteria andMsgMemoNotBetween(String value1, String value2) {
            addCriterion("MSG_MEMO not between", value1, value2, "msgMemo");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNull() {
            addCriterion("SEND_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNotNull() {
            addCriterion("SEND_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andSendStatusEqualTo(String value) {
            addCriterion("SEND_STATUS =", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotEqualTo(String value) {
            addCriterion("SEND_STATUS <>", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThan(String value) {
            addCriterion("SEND_STATUS >", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_STATUS >=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThan(String value) {
            addCriterion("SEND_STATUS <", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThanOrEqualTo(String value) {
            addCriterion("SEND_STATUS <=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLike(String value) {
            addCriterion("SEND_STATUS like", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotLike(String value) {
            addCriterion("SEND_STATUS not like", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusIn(List<String> values) {
            addCriterion("SEND_STATUS in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotIn(List<String> values) {
            addCriterion("SEND_STATUS not in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusBetween(String value1, String value2) {
            addCriterion("SEND_STATUS between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotBetween(String value1, String value2) {
            addCriterion("SEND_STATUS not between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendTagIsNull() {
            addCriterion("SEND_TAG is null");
            return (Criteria) this;
        }

        public Criteria andSendTagIsNotNull() {
            addCriterion("SEND_TAG is not null");
            return (Criteria) this;
        }

        public Criteria andSendTagEqualTo(String value) {
            addCriterion("SEND_TAG =", value, "sendTag");
            return (Criteria) this;
        }

        public Criteria andSendTagNotEqualTo(String value) {
            addCriterion("SEND_TAG <>", value, "sendTag");
            return (Criteria) this;
        }

        public Criteria andSendTagGreaterThan(String value) {
            addCriterion("SEND_TAG >", value, "sendTag");
            return (Criteria) this;
        }

        public Criteria andSendTagGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_TAG >=", value, "sendTag");
            return (Criteria) this;
        }

        public Criteria andSendTagLessThan(String value) {
            addCriterion("SEND_TAG <", value, "sendTag");
            return (Criteria) this;
        }

        public Criteria andSendTagLessThanOrEqualTo(String value) {
            addCriterion("SEND_TAG <=", value, "sendTag");
            return (Criteria) this;
        }

        public Criteria andSendTagLike(String value) {
            addCriterion("SEND_TAG like", value, "sendTag");
            return (Criteria) this;
        }

        public Criteria andSendTagNotLike(String value) {
            addCriterion("SEND_TAG not like", value, "sendTag");
            return (Criteria) this;
        }

        public Criteria andSendTagIn(List<String> values) {
            addCriterion("SEND_TAG in", values, "sendTag");
            return (Criteria) this;
        }

        public Criteria andSendTagNotIn(List<String> values) {
            addCriterion("SEND_TAG not in", values, "sendTag");
            return (Criteria) this;
        }

        public Criteria andSendTagBetween(String value1, String value2) {
            addCriterion("SEND_TAG between", value1, value2, "sendTag");
            return (Criteria) this;
        }

        public Criteria andSendTagNotBetween(String value1, String value2) {
            addCriterion("SEND_TAG not between", value1, value2, "sendTag");
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
