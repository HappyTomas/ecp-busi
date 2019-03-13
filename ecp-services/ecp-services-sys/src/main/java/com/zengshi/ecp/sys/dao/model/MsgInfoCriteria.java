package com.zengshi.ecp.sys.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MsgInfoCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public MsgInfoCriteria() {
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

        public Criteria andMsgParamsIsNull() {
            addCriterion("MSG_PARAMS is null");
            return (Criteria) this;
        }

        public Criteria andMsgParamsIsNotNull() {
            addCriterion("MSG_PARAMS is not null");
            return (Criteria) this;
        }

        public Criteria andMsgParamsEqualTo(String value) {
            addCriterion("MSG_PARAMS =", value, "msgParams");
            return (Criteria) this;
        }

        public Criteria andMsgParamsNotEqualTo(String value) {
            addCriterion("MSG_PARAMS <>", value, "msgParams");
            return (Criteria) this;
        }

        public Criteria andMsgParamsGreaterThan(String value) {
            addCriterion("MSG_PARAMS >", value, "msgParams");
            return (Criteria) this;
        }

        public Criteria andMsgParamsGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_PARAMS >=", value, "msgParams");
            return (Criteria) this;
        }

        public Criteria andMsgParamsLessThan(String value) {
            addCriterion("MSG_PARAMS <", value, "msgParams");
            return (Criteria) this;
        }

        public Criteria andMsgParamsLessThanOrEqualTo(String value) {
            addCriterion("MSG_PARAMS <=", value, "msgParams");
            return (Criteria) this;
        }

        public Criteria andMsgParamsLike(String value) {
            addCriterion("MSG_PARAMS like", value, "msgParams");
            return (Criteria) this;
        }

        public Criteria andMsgParamsNotLike(String value) {
            addCriterion("MSG_PARAMS not like", value, "msgParams");
            return (Criteria) this;
        }

        public Criteria andMsgParamsIn(List<String> values) {
            addCriterion("MSG_PARAMS in", values, "msgParams");
            return (Criteria) this;
        }

        public Criteria andMsgParamsNotIn(List<String> values) {
            addCriterion("MSG_PARAMS not in", values, "msgParams");
            return (Criteria) this;
        }

        public Criteria andMsgParamsBetween(String value1, String value2) {
            addCriterion("MSG_PARAMS between", value1, value2, "msgParams");
            return (Criteria) this;
        }

        public Criteria andMsgParamsNotBetween(String value1, String value2) {
            addCriterion("MSG_PARAMS not between", value1, value2, "msgParams");
            return (Criteria) this;
        }

        public Criteria andSendUserIdIsNull() {
            addCriterion("SEND_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andSendUserIdIsNotNull() {
            addCriterion("SEND_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSendUserIdEqualTo(Long value) {
            addCriterion("SEND_USER_ID =", value, "sendUserId");
            return (Criteria) this;
        }

        public Criteria andSendUserIdNotEqualTo(Long value) {
            addCriterion("SEND_USER_ID <>", value, "sendUserId");
            return (Criteria) this;
        }

        public Criteria andSendUserIdGreaterThan(Long value) {
            addCriterion("SEND_USER_ID >", value, "sendUserId");
            return (Criteria) this;
        }

        public Criteria andSendUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SEND_USER_ID >=", value, "sendUserId");
            return (Criteria) this;
        }

        public Criteria andSendUserIdLessThan(Long value) {
            addCriterion("SEND_USER_ID <", value, "sendUserId");
            return (Criteria) this;
        }

        public Criteria andSendUserIdLessThanOrEqualTo(Long value) {
            addCriterion("SEND_USER_ID <=", value, "sendUserId");
            return (Criteria) this;
        }

        public Criteria andSendUserIdIn(List<Long> values) {
            addCriterion("SEND_USER_ID in", values, "sendUserId");
            return (Criteria) this;
        }

        public Criteria andSendUserIdNotIn(List<Long> values) {
            addCriterion("SEND_USER_ID not in", values, "sendUserId");
            return (Criteria) this;
        }

        public Criteria andSendUserIdBetween(Long value1, Long value2) {
            addCriterion("SEND_USER_ID between", value1, value2, "sendUserId");
            return (Criteria) this;
        }

        public Criteria andSendUserIdNotBetween(Long value1, Long value2) {
            addCriterion("SEND_USER_ID not between", value1, value2, "sendUserId");
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

        public Criteria andRecTimeIsNull() {
            addCriterion("REC_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRecTimeIsNotNull() {
            addCriterion("REC_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRecTimeEqualTo(Timestamp value) {
            addCriterion("REC_TIME =", value, "recTime");
            return (Criteria) this;
        }

        public Criteria andRecTimeNotEqualTo(Timestamp value) {
            addCriterion("REC_TIME <>", value, "recTime");
            return (Criteria) this;
        }

        public Criteria andRecTimeGreaterThan(Timestamp value) {
            addCriterion("REC_TIME >", value, "recTime");
            return (Criteria) this;
        }

        public Criteria andRecTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("REC_TIME >=", value, "recTime");
            return (Criteria) this;
        }

        public Criteria andRecTimeLessThan(Timestamp value) {
            addCriterion("REC_TIME <", value, "recTime");
            return (Criteria) this;
        }

        public Criteria andRecTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("REC_TIME <=", value, "recTime");
            return (Criteria) this;
        }

        public Criteria andRecTimeIn(List<Timestamp> values) {
            addCriterion("REC_TIME in", values, "recTime");
            return (Criteria) this;
        }

        public Criteria andRecTimeNotIn(List<Timestamp> values) {
            addCriterion("REC_TIME not in", values, "recTime");
            return (Criteria) this;
        }

        public Criteria andRecTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("REC_TIME between", value1, value2, "recTime");
            return (Criteria) this;
        }

        public Criteria andRecTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("REC_TIME not between", value1, value2, "recTime");
            return (Criteria) this;
        }

        public Criteria andMsgInfoStatusIsNull() {
            addCriterion("MSG_INFO_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andMsgInfoStatusIsNotNull() {
            addCriterion("MSG_INFO_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andMsgInfoStatusEqualTo(String value) {
            addCriterion("MSG_INFO_STATUS =", value, "msgInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMsgInfoStatusNotEqualTo(String value) {
            addCriterion("MSG_INFO_STATUS <>", value, "msgInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMsgInfoStatusGreaterThan(String value) {
            addCriterion("MSG_INFO_STATUS >", value, "msgInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMsgInfoStatusGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_INFO_STATUS >=", value, "msgInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMsgInfoStatusLessThan(String value) {
            addCriterion("MSG_INFO_STATUS <", value, "msgInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMsgInfoStatusLessThanOrEqualTo(String value) {
            addCriterion("MSG_INFO_STATUS <=", value, "msgInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMsgInfoStatusLike(String value) {
            addCriterion("MSG_INFO_STATUS like", value, "msgInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMsgInfoStatusNotLike(String value) {
            addCriterion("MSG_INFO_STATUS not like", value, "msgInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMsgInfoStatusIn(List<String> values) {
            addCriterion("MSG_INFO_STATUS in", values, "msgInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMsgInfoStatusNotIn(List<String> values) {
            addCriterion("MSG_INFO_STATUS not in", values, "msgInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMsgInfoStatusBetween(String value1, String value2) {
            addCriterion("MSG_INFO_STATUS between", value1, value2, "msgInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMsgInfoStatusNotBetween(String value1, String value2) {
            addCriterion("MSG_INFO_STATUS not between", value1, value2, "msgInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMsgCommenIsNull() {
            addCriterion("MSG_COMMEN is null");
            return (Criteria) this;
        }

        public Criteria andMsgCommenIsNotNull() {
            addCriterion("MSG_COMMEN is not null");
            return (Criteria) this;
        }

        public Criteria andMsgCommenEqualTo(String value) {
            addCriterion("MSG_COMMEN =", value, "msgCommen");
            return (Criteria) this;
        }

        public Criteria andMsgCommenNotEqualTo(String value) {
            addCriterion("MSG_COMMEN <>", value, "msgCommen");
            return (Criteria) this;
        }

        public Criteria andMsgCommenGreaterThan(String value) {
            addCriterion("MSG_COMMEN >", value, "msgCommen");
            return (Criteria) this;
        }

        public Criteria andMsgCommenGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_COMMEN >=", value, "msgCommen");
            return (Criteria) this;
        }

        public Criteria andMsgCommenLessThan(String value) {
            addCriterion("MSG_COMMEN <", value, "msgCommen");
            return (Criteria) this;
        }

        public Criteria andMsgCommenLessThanOrEqualTo(String value) {
            addCriterion("MSG_COMMEN <=", value, "msgCommen");
            return (Criteria) this;
        }

        public Criteria andMsgCommenLike(String value) {
            addCriterion("MSG_COMMEN like", value, "msgCommen");
            return (Criteria) this;
        }

        public Criteria andMsgCommenNotLike(String value) {
            addCriterion("MSG_COMMEN not like", value, "msgCommen");
            return (Criteria) this;
        }

        public Criteria andMsgCommenIn(List<String> values) {
            addCriterion("MSG_COMMEN in", values, "msgCommen");
            return (Criteria) this;
        }

        public Criteria andMsgCommenNotIn(List<String> values) {
            addCriterion("MSG_COMMEN not in", values, "msgCommen");
            return (Criteria) this;
        }

        public Criteria andMsgCommenBetween(String value1, String value2) {
            addCriterion("MSG_COMMEN between", value1, value2, "msgCommen");
            return (Criteria) this;
        }

        public Criteria andMsgCommenNotBetween(String value1, String value2) {
            addCriterion("MSG_COMMEN not between", value1, value2, "msgCommen");
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
